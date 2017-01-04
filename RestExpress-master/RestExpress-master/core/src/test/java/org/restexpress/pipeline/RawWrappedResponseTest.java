/*
    Copyright 2010, Strategic Gains, Inc.

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
package org.restexpress.pipeline;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import org.junit.Before;
import org.junit.Test;
import org.restexpress.response.DefaultHttpResponseWriter;
import org.restexpress.response.StringBufferHttpResponseWriter;
import org.restexpress.route.RouteDeclaration;
import org.restexpress.route.RouteResolver;
import org.restexpress.serialization.DefaultSerializationProvider;
import org.restexpress.serialization.SerializationProvider;
import org.restexpress.settings.RouteDefaults;

import java.nio.charset.Charset;

import static org.junit.Assert.assertEquals;


/**
* @author toddf
* @since Dec 15, 2010
*/
public class RawWrappedResponseTest
{
	private DefaultRequestHandler messageHandler;
	private WrappedResponseObserver observer;
	private Channel channel;
    private ChannelPipeline pl;
    private StringBuffer httpResponse;

	@Before
	public void initialize()
	throws Exception
	{
		SerializationProvider resolver = new DefaultSerializationProvider();
//		resolver.put(Format.JSON, new ResponseProcessor(new JacksonJsonProcessor(), new RawResponseWrapper()));
//		resolver.put(Format.XML, new ResponseProcessor(new XstreamXmlProcessor(), new RawResponseWrapper()));
//		resolver.setDefaultFormat(Format.JSON);

		DummyRoutes routes = new DummyRoutes();
		routes.defineRoutes();
		messageHandler = new DefaultRequestHandler(new RouteResolver(routes.createRouteMapping(new RouteDefaults())), resolver, new DefaultHttpResponseWriter(), true);
		observer = new WrappedResponseObserver();
		messageHandler.addMessageObserver(observer);
		httpResponse = new StringBuffer();
		messageHandler.setResponseWriter(new StringBufferHttpResponseWriter(httpResponse));
		PipelineInitializer pf = new PipelineInitializer()
			.addRequestHandler(messageHandler);
        channel = new EmbeddedChannel(messageHandler);
        pl = channel.pipeline();
	}

	@Test
	public void shouldWrapGetInRawJson()
	{
		sendEvent(HttpMethod.GET, "/normal_get.json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("\"Normal GET action\"", httpResponse.toString());
	}

	@Test
	public void shouldWrapGetInRawJsonUsingQueryString()
	{
		sendEvent(HttpMethod.GET, "/normal_get?format=json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("\"Normal GET action\"", httpResponse.toString());
	}

	@Test
	public void shouldWrapGetInRawXml()
	{
		sendEvent(HttpMethod.GET, "/normal_get.xml", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("<string>Normal GET action</string>", httpResponse.toString());
	}

	@Test
	public void shouldWrapGetInRawXmlUsingQueryString()
	{
		sendEvent(HttpMethod.GET, "/normal_get?format=xml", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("<string>Normal GET action</string>", httpResponse.toString());
	}

	@Test
	public void shouldWrapPutInRawJson()
	{
		sendEvent(HttpMethod.PUT, "/normal_put.json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("\"Normal PUT action\"", httpResponse.toString());
	}

	@Test
	public void shouldWrapPutInRawJsonUsingQueryString()
	{
		sendEvent(HttpMethod.PUT, "/normal_put?format=json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("\"Normal PUT action\"", httpResponse.toString());
	}

	@Test
	public void shouldWrapPutInRawXml()
	{
		sendEvent(HttpMethod.PUT, "/normal_put.xml", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("<string>Normal PUT action</string>", httpResponse.toString());
	}

	@Test
	public void shouldWrapPutInRawXmlUsingQueryString()
	{
		sendEvent(HttpMethod.PUT, "/normal_put?format=xml", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("<string>Normal PUT action</string>", httpResponse.toString());
	}

	@Test
	public void shouldWrapPostInRawJson()
	{
		sendEvent(HttpMethod.POST, "/normal_post.json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("\"Normal POST action\"", httpResponse.toString());
	}

	@Test
	public void shouldWrapPostInRawJsonUsingQueryString()
	{
		sendEvent(HttpMethod.POST, "/normal_post?format=json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("\"Normal POST action\"", httpResponse.toString());
	}

	@Test
	public void shouldWrapPostInRawXml()
	{
		sendEvent(HttpMethod.POST, "/normal_post.xml", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("<string>Normal POST action</string>", httpResponse.toString());
	}

	@Test
	public void shouldWrapPostInRawXmlUsingQueryString()
	{
		sendEvent(HttpMethod.POST, "/normal_post?format=xml", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("<string>Normal POST action</string>", httpResponse.toString());
	}

	@Test
	public void shouldWrapDeleteInRawJson()
	{
		sendEvent(HttpMethod.DELETE, "/normal_delete.json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("\"Normal DELETE action\"", httpResponse.toString());
	}

	@Test
	public void shouldWrapDeleteInRawJsonUsingQueryString()
	{
		sendEvent(HttpMethod.DELETE, "/normal_delete?format=json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("\"Normal DELETE action\"", httpResponse.toString());
	}

	@Test
	public void shouldWrapDeleteInRawXml()
	{
		sendEvent(HttpMethod.DELETE, "/normal_delete.xml", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("<string>Normal DELETE action</string>", httpResponse.toString());
	}

	@Test
	public void shouldWrapDeleteInRawXmlUsingQueryString()
	{
		sendEvent(HttpMethod.DELETE, "/normal_delete?format=xml", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("<string>Normal DELETE action</string>", httpResponse.toString());
	}

	@Test
	public void shouldWrapNotFoundInRawJson()
	{
		sendEvent(HttpMethod.GET, "/not_found.json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(0, observer.getSuccessCount());
		assertEquals(1, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("\"Item not found\"", httpResponse.toString());
	}

	@Test
	public void shouldWrapNotFoundInRawJsonUsingQueryString()
	{
		sendEvent(HttpMethod.GET, "/not_found?format=json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(0, observer.getSuccessCount());
		assertEquals(1, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("\"Item not found\"", httpResponse.toString());
	}

	@Test
	public void shouldWrapNotFoundInRawXml()
	{
		sendEvent(HttpMethod.GET, "/not_found.xml", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(0, observer.getSuccessCount());
		assertEquals(1, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("<string>Item not found</string>", httpResponse.toString());
	}

	@Test
	public void shouldWrapNotFoundInRawXmlUsingQueryString()
	{
		sendEvent(HttpMethod.GET, "/not_found?format=xml", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(0, observer.getSuccessCount());
		assertEquals(1, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("<string>Item not found</string>", httpResponse.toString());
	}

	@Test
	public void shouldWrapNullPointerInRawJson()
	{
		sendEvent(HttpMethod.GET, "/null_pointer.json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(0, observer.getSuccessCount());
		assertEquals(1, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("\"Null and void\"", httpResponse.toString());
	}

	@Test
	public void shouldWrapNullPointerInRawJsonUsingQueryString()
	{
		sendEvent(HttpMethod.GET, "/null_pointer?format=json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(0, observer.getSuccessCount());
		assertEquals(1, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("\"Null and void\"", httpResponse.toString());
	}

	@Test
	public void shouldWrapNullPointerInRawXml()
	{
		sendEvent(HttpMethod.GET, "/null_pointer.xml", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(0, observer.getSuccessCount());
		assertEquals(1, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("<string>Null and void</string>", httpResponse.toString());
	}

	@Test
	public void shouldWrapNullPointerInRawXmlUsingQueryString()
	{
		sendEvent(HttpMethod.GET, "/null_pointer?format=xml", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(0, observer.getSuccessCount());
		assertEquals(1, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("<string>Null and void</string>", httpResponse.toString());
	}

	@Test
	public void shouldWrapInvalidUrlWithRawJson()
	{
		sendEvent(HttpMethod.GET, "/xyzt.json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(0, observer.getSuccessCount());
		assertEquals(1, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("\"Unresolvable URL: http://null/xyzt.json\"", httpResponse.toString());
	}

	@Test
	public void shouldWrapInvalidUrlWithRawJsonUsingQueryString()
	{
		sendEvent(HttpMethod.GET, "/xyzt?format=json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(0, observer.getSuccessCount());
		assertEquals(1, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("\"Unresolvable URL: http://null/xyzt?format=json\"", httpResponse.toString());
	}

	@Test
	public void shouldWrapInvalidUrlWithRawXml()
	{
		sendEvent(HttpMethod.GET, "/xyzt.xml", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(0, observer.getSuccessCount());
		assertEquals(1, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("<string>Unresolvable URL: http://null/xyzt.xml</string>", httpResponse.toString());
	}

	@Test
	public void shouldWrapInvalidUrlWithXmlUsingQueryString()
	{
		sendEvent(HttpMethod.GET, "/xyzt?format=xml", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(0, observer.getSuccessCount());
		assertEquals(1, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("<string>Unresolvable URL: http://null/xyzt?format=xml</string>", httpResponse.toString());
	}

	@Test
	public void shouldDeleteWithoutContent()
	{
		sendEvent(HttpMethod.DELETE, "/no_content_delete.json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("null", httpResponse.toString());
	}

	@Test
	public void shouldThrowExceptionOnDeleteNoContentContainingBody()
	{
		sendEvent(HttpMethod.DELETE, "/no_content_with_body_delete.json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(0, observer.getSuccessCount());
		assertEquals(1, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
	}

	@Test
	public void shouldDeleteIgnoringJsonp()
	{
		sendEvent(HttpMethod.DELETE, "/normal_delete.json?jsonp=jsonp_callback", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("\"Normal DELETE action\"", httpResponse.toString());
	}

	private void sendEvent(HttpMethod method, String path, String body)
    {
        pl.fireChannelRead(new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, method, path, Unpooled.copiedBuffer(body, Charset.defaultCharset())));
    }

	public class DummyRoutes
	extends RouteDeclaration
	{
		private Object controller = new WrappedResponseController();
		private RouteDefaults defaults = new RouteDefaults();

        public void defineRoutes()
        {
        	uri("/normal_get.{format}", controller, defaults)
        		.action("normalGetAction", HttpMethod.GET);

        	uri("/normal_put.{format}", controller, defaults)
    		.action("normalPutAction", HttpMethod.PUT);

        	uri("/normal_post.{format}", controller, defaults)
    		.action("normalPostAction", HttpMethod.POST);

        	uri("/normal_delete.{format}", controller, defaults)
    		.action("normalDeleteAction", HttpMethod.DELETE);

        	uri("/no_content_delete.{format}", controller, defaults)
    		.action("noContentDeleteAction", HttpMethod.DELETE);

        	uri("/no_content_with_body_delete.{format}", controller, defaults)
    		.action("noContentWithBodyDeleteAction", HttpMethod.DELETE);

        	uri("/not_found.{format}", controller, defaults)
        		.action("notFoundAction", HttpMethod.GET);

        	uri("/null_pointer.{format}", controller, defaults)
        		.action("nullPointerAction", HttpMethod.GET);
        }
	}
}
