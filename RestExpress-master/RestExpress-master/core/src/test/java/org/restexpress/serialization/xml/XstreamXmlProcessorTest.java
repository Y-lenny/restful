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
package org.restexpress.serialization.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.nio.ByteBuffer;
import java.util.Calendar;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Before;
import org.junit.Test;
import org.restexpress.ContentType;
import org.restexpress.serialization.KnownObject;

/**
 * @author toddf
 * @since Aug 4, 2011
 */
public class XstreamXmlProcessorTest
{
	private static final String XML = "<KnownObject><integer>2</integer><string>another string value</string><date>1963-12-06T12:30:00.000Z</date></KnownObject>";

	private XstreamXmlProcessor processor = new XstreamXmlProcessor();
	
	@Before
	public void setup()
	{
		processor.alias("KnownObject", KnownObject.class);
	}

	@Test
	public void shouldSerializeObject()
	{
		ByteBuffer xmlBuf = processor.serialize(new KnownObject());
		String xml = new String(xmlBuf.array(), ContentType.CHARSET);
		assertNotNull(xml);
		assertTrue(xml.startsWith("<KnownObject>"));
		assertTrue(xml.contains("<integer>1</integer>"));
		assertTrue(xml.contains("<string>string value</string>"));
		assertTrue(xml.contains("<date>1964-12-17T"));
		assertTrue(xml.endsWith("</KnownObject>"));
	}

	@Test
	public void shouldSerializeNull()
	{
		ByteBuffer xmlBuf = processor.serialize(null);
		assertEquals("", new String(xmlBuf.array(), ContentType.CHARSET));
	}

	@Test
	public void shouldDeserializeObject()
	{
		KnownObject o = processor.deserialize(XML, KnownObject.class);
		assertNotNull(o);
		assertTrue(o.getClass().isAssignableFrom(KnownObject.class));
		assertEquals(2, o.integer);
		assertEquals("another string value", o.string);
		Calendar c = Calendar.getInstance();
		c.setTime(o.date);
		assertEquals(11, c.get(Calendar.MONTH));
		assertEquals(6, c.get(Calendar.DAY_OF_MONTH));
		assertEquals(1963, c.get(Calendar.YEAR));
	}

	@Test
	public void shouldDeserializeEmptyObject()
	{
		KnownObject o = processor.deserialize("<KnownObject/>", KnownObject.class);
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
		ByteBuf buf = Unpooled.copiedBuffer(XML, ContentType.CHARSET);
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
	public void shouldEncodeSerializedXssArray()
	{
		KnownObject ko = new KnownObject();
		ko.sa = new String[] {"this", "is", "an", "evil", "Json", "<script>alert(\'xss')</script>"};
		ByteBuffer xmlBuf = processor.serialize(ko);
		String xml = new String(xmlBuf.array(), ContentType.CHARSET);
		assertNotNull(xml);
		assertTrue(xml.startsWith("<KnownObject>"));
		assertTrue(xml.contains("<integer>1</integer>"));
		assertTrue(xml.contains("<date>1964-12-17T23:30:00.000Z</date>"));
		assertTrue(xml.contains("<p>something private</p>"));
		assertTrue(xml.contains("<sa>"));
		assertTrue(xml.contains("<string>&lt;script&gt;alert(&apos;xss&apos;)&lt;/script&gt;</string>"));
		assertTrue(xml.contains("</sa>"));
		assertFalse(xml.contains("<q>"));
		assertTrue(xml.endsWith("</KnownObject>"));
	}

	@Test
	public void shouldEncodeSerializedXssString()
	{
		KnownObject ko = new KnownObject();
		ko.string = "<script>alert('xss')</script>";
		ByteBuffer xmlBuf = processor.serialize(ko);
		String xml = new String(xmlBuf.array(), ContentType.CHARSET);
		assertNotNull(xml);
		assertTrue(xml.startsWith("<KnownObject>"));
		assertTrue(xml.contains("<integer>1</integer>"));
		assertTrue(xml.contains("<string>&lt;script&gt;alert(&apos;xss&apos;)&lt;/script&gt;</string>"));
		assertTrue(xml.contains("<date>1964-12-17T23:30:00.000Z</date>"));
		assertTrue(xml.contains("<p>something private</p>"));
		assertFalse(xml.contains("<q>"));
		assertFalse(xml.contains("<sa>"));
		assertTrue(xml.endsWith("</KnownObject>"));
	}
}
