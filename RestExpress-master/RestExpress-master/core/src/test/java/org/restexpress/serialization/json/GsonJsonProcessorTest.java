/*
    Copyright 2011, Strategic Gains, Inc.

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

		http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/
package org.restexpress.serialization.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;
import org.restexpress.ContentType;
import org.restexpress.serialization.KnownObject;
import org.restexpress.serialization.SerializationProcessor;

/**
 * @author toddf
 * @since Aug 4, 2011
 */
public class GsonJsonProcessorTest
{
	private static final String JSON = "{\"integer\":2,\"string\":\"another string value\",\"date\":\"1963-12-06T12:30:00.000Z\",\"p\":\"good stuff\"}";
	private static final String JSON_UTF8 = "{\"integer\":2,\"string\":\"我能吞下\",\"date\":\"1963-12-06T12:30:00.000Z\"}";

	private SerializationProcessor processor = new GsonJsonProcessor();

    @Before
    public void setup(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

	@Test
	public void shouldSerializeObject()
	{
		ByteBuffer jsonBuf = processor.serialize(new KnownObject());
		String json = new String(jsonBuf.array(), ContentType.CHARSET);
		assertNotNull(json);
		assertTrue(json.startsWith("{"));
		assertTrue(json.contains("\"integer\":1"));
		assertTrue(json.contains("\"string\":\"string value\""));
		assertTrue(json.contains("\"date\":\"1964-12-17T"));
		assertTrue(json.contains("\"p\":\"something private"));
		assertFalse(json.contains("\"q\":"));
		assertTrue(json.endsWith("}"));
	}

	@Test
	public void shouldSerializeNull()
	{
		ByteBuffer jsonBuf = processor.serialize(null);
		assertEquals("", new String(jsonBuf.array(), ContentType.CHARSET));
	}

	@Test
	public void shouldDeserializeObject()
	{
		KnownObject o = processor.deserialize(JSON, KnownObject.class);
		assertNotNull(o);
		assertTrue(o.getClass().isAssignableFrom(KnownObject.class));
		assertEquals(2, o.integer);
		assertEquals("another string value", o.string);
		Calendar c = Calendar.getInstance();
		c.setTime(o.date);
		assertEquals(11, c.get(Calendar.MONTH));
		assertEquals(6, c.get(Calendar.DAY_OF_MONTH));
		assertEquals(1963, c.get(Calendar.YEAR));
		assertEquals("good stuff", o.getP());
	}

	@Test
	public void shouldDeserializeEmptyObject()
	{
		KnownObject o = processor.deserialize("{}", KnownObject.class);
		assertNotNull(o);
		assertTrue(o.getClass().isAssignableFrom(KnownObject.class));
	}

	@Test
	public void shouldDeserializeEmptyString()
	{
		Object o = processor.deserialize("", KnownObject.class);
		assertNull(o);
	}

	@Test
	public void shouldDeserializeNullString()
	{
		Object o = processor.deserialize((String) null, KnownObject.class);
		assertNull(o);
	}

	@Test
	public void shouldDeserializeChannelBuffer()
	{
		ByteBuf buf = Unpooled.copiedBuffer(JSON, ContentType.CHARSET);
		Object o = processor.deserialize(buf, KnownObject.class);
		assertNotNull(o);
	}

	@Test
	public void shouldDeserializeEmptyChannelBuffer()
	{
		ByteBuf buf = Unpooled.EMPTY_BUFFER;
		Object o = processor.deserialize(buf, KnownObject.class);
		assertNull(o);
	}

	@Test
	public void shouldDeserializeUTF8ChannelBuffer()
	{
		KnownObject o = processor.deserialize(Unpooled.wrappedBuffer(JSON_UTF8.getBytes(ContentType.CHARSET)), KnownObject.class);
		assertNotNull(o);
		assertTrue(o.getClass().isAssignableFrom(KnownObject.class));
		assertEquals(2, o.integer);
		assertEquals("我能吞下", o.string);
		Calendar c = Calendar.getInstance();
		c.setTime(o.date);
		assertEquals(11, c.get(Calendar.MONTH));
		assertEquals(6, c.get(Calendar.DAY_OF_MONTH));
		assertEquals(1963, c.get(Calendar.YEAR));
	}

	@Test
	public void shouldEncodeSerializedXssJsonArray()
	{
		KnownObject ko = new KnownObject();
		ko.sa = new String[] {"this", "is", "an", "evil", "Json", "<script>alert(\'xss')</script>"};
		ByteBuffer jsonBuf = processor.serialize(ko);
		String json = new String(jsonBuf.array(), ContentType.CHARSET);
		assertNotNull(json);
		assertTrue(json.startsWith("{"));
		assertTrue(json.contains("\"integer\":1"));
		assertTrue(json.contains("\"string\":\"string value\""));
		assertTrue(json.contains("\"date\":\"1964-12-17T23:30:00.000Z"));
		assertTrue(json.contains("\"p\":\"something private"));
		assertFalse(json.contains("\"q\":"));
		assertTrue(json.contains("\"sa\":[\"this\",\"is\",\"an\",\"evil\",\"Json\",\"&lt;script&gt;alert('xss')&lt;/script&gt;\"]"));
		assertTrue(json.endsWith("}"));
	}

	@Test
	public void shouldEncodeSerializedXssJsonString()
	{
		KnownObject ko = new KnownObject();
		ko.string = "<script>alert('xss')</script>";
		ByteBuffer jsonBuf = processor.serialize(ko);
		String json = new String(jsonBuf.array(), ContentType.CHARSET);
		assertNotNull(json);
		assertTrue(json.startsWith("{"));
		assertTrue(json.contains("\"integer\":1"));
		assertTrue(json.contains("\"string\":\"&lt;script&gt;alert('xss')&lt;/script&gt;"));
		assertTrue(json.contains("\"date\":\"1964-12-17T23:30:00.000Z"));
		assertTrue(json.contains("\"p\":\"something private"));
		assertFalse(json.contains("\"q\":"));
		assertFalse(json.contains("\"sa\":"));
		assertTrue(json.endsWith("}"));
	}
}
