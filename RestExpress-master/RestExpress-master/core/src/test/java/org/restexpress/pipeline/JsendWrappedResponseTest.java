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
import org.restexpress.response.JsendResponseWrapper;
import org.restexpress.response.StringBufferHttpResponseWriter;
import org.restexpress.route.RouteDeclaration;
import org.restexpress.route.RouteResolver;
import org.restexpress.serialization.NullSerializationProvider;
import org.restexpress.serialization.SerializationProvider;
import org.restexpress.serialization.json.JacksonJsonProcessor;
import org.restexpress.serialization.xml.XstreamXmlProcessor;
import org.restexpress.settings.RouteDefaults;

import java.nio.charset.Charset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
* @author toddf
* @since Dec 15, 2010
*/
public class JsendWrappedResponseTest
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
		SerializationProvider provider = new NullSerializationProvider();
		provider.add(new JacksonJsonProcessor(), new JsendResponseWrapper(), true);
		provider.add(new XstreamXmlProcessor(), new JsendResponseWrapper());
		DummyRoutes routes = new DummyRoutes();
		routes.defineRoutes();
		messageHandler = new DefaultRequestHandler(new RouteResolver(routes.createRouteMapping(new RouteDefaults())), provider, new DefaultHttpResponseWriter(), false);
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
	public void shouldWrapGetInJsendJson()
	{
		sendEvent(HttpMethod.GET, "/normal_get.json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("{\"code\":200,\"status\":\"success\",\"data\":\"Normal GET action\"}", httpResponse.toString());
	}

	@Test
	public void shouldWrapGetInJsendJsonUsingQueryString()
	{
		sendEvent(HttpMethod.GET, "/normal_get?format=json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("{\"code\":200,\"status\":\"success\",\"data\":\"Normal GET action\"}", httpResponse.toString());
	}

	@Test
	public void shouldWrapGetInJsendXml()
	{
		sendEvent(HttpMethod.GET, "/normal_get.xml", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertTrue(httpResponse.toString().startsWith("<response>"));
		assertTrue(httpResponse.toString().contains("<code>200</code>"));
		assertTrue(httpResponse.toString().contains("<status>success</status>"));
		assertTrue(httpResponse.toString().contains("<data class=\"string\">Normal GET action</data>"));
		assertTrue(httpResponse.toString().endsWith("</response>"));
	}

	@Test
	public void shouldWrapGetInJsendXmlUsingQueryString()
	{
		sendEvent(HttpMethod.GET, "/normal_get?format=xml", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertTrue(httpResponse.toString().startsWith("<response>"));
		assertTrue(httpResponse.toString().contains("<code>200</code>"));
		assertTrue(httpResponse.toString().contains("<status>success</status>"));
		assertTrue(httpResponse.toString().contains("<data class=\"string\">Normal GET action</data>"));
		assertTrue(httpResponse.toString().endsWith("</response>"));
	}

	@Test
	public void shouldWrapPutInJsendJson()
	{
		sendEvent(HttpMethod.PUT, "/normal_put.json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("{\"code\":200,\"status\":\"success\",\"data\":\"Normal PUT action\"}", httpResponse.toString());
	}

	@Test
	public void shouldWrapPutInJsendJsonUsingQueryString()
	{
		sendEvent(HttpMethod.PUT, "/normal_put?format=json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("{\"code\":200,\"status\":\"success\",\"data\":\"Normal PUT action\"}", httpResponse.toString());
	}

	@Test
	public void shouldWrapPutInJsendXml()
	{
		sendEvent(HttpMethod.PUT, "/normal_put.xml", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertTrue(httpResponse.toString().startsWith("<response>"));
		assertTrue(httpResponse.toString().contains("<code>200</code>"));
		assertTrue(httpResponse.toString().contains("<status>success</status>"));
		assertTrue(httpResponse.toString().contains("<data class=\"string\">Normal PUT action</data>"));
		assertTrue(httpResponse.toString().endsWith("</response>"));
	}

	@Test
	public void shouldWrapPutInJsendXmlUsingQueryString()
	{
		sendEvent(HttpMethod.PUT, "/normal_put?format=xml", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertTrue(httpResponse.toString().startsWith("<response>"));
		assertTrue(httpResponse.toString().contains("<code>200</code>"));
		assertTrue(httpResponse.toString().contains("<status>success</status>"));
		assertTrue(httpResponse.toString().contains("<data class=\"string\">Normal PUT action</data>"));
		assertTrue(httpResponse.toString().endsWith("</response>"));
	}

	@Test
	public void shouldWrapPostInJsendJson()
	{
		sendEvent(HttpMethod.POST, "/normal_post.json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("{\"code\":200,\"status\":\"success\",\"data\":\"Normal POST action\"}", httpResponse.toString());
	}

	@Test
	public void shouldWrapPostInJsendJsonUsingQueryString()
	{
		sendEvent(HttpMethod.POST, "/normal_post?format=json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("{\"code\":200,\"status\":\"success\",\"data\":\"Normal POST action\"}", httpResponse.toString());
	}

	@Test
	public void shouldWrapPostInJsendXml()
	{
		sendEvent(HttpMethod.POST, "/normal_post.xml", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertTrue(httpResponse.toString().startsWith("<response>"));
		assertTrue(httpResponse.toString().contains("<code>200</code>"));
		assertTrue(httpResponse.toString().contains("<status>success</status>"));
		assertTrue(httpResponse.toString().contains("<data class=\"string\">Normal POST action</data>"));
		assertTrue(httpResponse.toString().endsWith("</response>"));
	}

	@Test
	public void shouldWrapPostInJsendXmlUsingQueryString()
	{
		sendEvent(HttpMethod.POST, "/normal_post?format=xml", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertTrue(httpResponse.toString().startsWith("<response>"));
		assertTrue(httpResponse.toString().contains("<code>200</code>"));
		assertTrue(httpResponse.toString().contains("<status>success</status>"));
		assertTrue(httpResponse.toString().contains("<data class=\"string\">Normal POST action</data>"));
		assertTrue(httpResponse.toString().endsWith("</response>"));
	}

	@Test
	public void shouldWrapDeleteInJsendJson()
	{
		sendEvent(HttpMethod.DELETE, "/normal_delete.json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("{\"code\":200,\"status\":\"success\",\"data\":\"Normal DELETE action\"}", httpResponse.toString());
	}

	@Test
	public void shouldWrapDeleteInJsendJsonUsingQueryString()
	{
		sendEvent(HttpMethod.DELETE, "/normal_delete?format=json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("{\"code\":200,\"status\":\"success\",\"data\":\"Normal DELETE action\"}", httpResponse.toString());
	}

	@Test
	public void shouldWrapDeleteInJsendXml()
	{
		sendEvent(HttpMethod.DELETE, "/normal_delete.xml", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertTrue(httpResponse.toString().startsWith("<response>"));
		assertTrue(httpResponse.toString().contains("<code>200</code>"));
		assertTrue(httpResponse.toString().contains("<status>success</status>"));
		assertTrue(httpResponse.toString().contains("<data class=\"string\">Normal DELETE action</data>"));
		assertTrue(httpResponse.toString().endsWith("</response>"));
	}

	@Test
	public void shouldWrapDeleteInJsendXmlUsingQueryString()
	{
		sendEvent(HttpMethod.DELETE, "/normal_delete?format=xml", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(1, observer.getSuccessCount());
		assertEquals(0, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertTrue(httpResponse.toString().startsWith("<response>"));
		assertTrue(httpResponse.toString().contains("<code>200</code>"));
		assertTrue(httpResponse.toString().contains("<status>success</status>"));
		assertTrue(httpResponse.toString().contains("<data class=\"string\">Normal DELETE action</data>"));
		assertTrue(httpResponse.toString().endsWith("</response>"));
	}

	@Test
	public void shouldWrapNotFoundInJsendJson()
	{
		sendEvent(HttpMethod.GET, "/not_found.json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(0, observer.getSuccessCount());
		assertEquals(1, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("{\"code\":404,\"status\":\"error\",\"message\":\"Item not found\",\"data\":\"NotFoundException\"}", httpResponse.toString());
	}

	@Test
	public void shouldWrapNotFoundInJsendJsonUsingQueryString()
	{
		sendEvent(HttpMethod.GET, "/not_found?format=json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(0, observer.getSuccessCount());
		assertEquals(1, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("{\"code\":404,\"status\":\"error\",\"message\":\"Item not found\",\"data\":\"NotFoundException\"}", httpResponse.toString());
	}

	@Test
	public void shouldWrapNotFoundInJsendXml()
	{
		sendEvent(HttpMethod.GET, "/not_found.xml", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(0, observer.getSuccessCount());
		assertEquals(1, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertTrue(httpResponse.toString().startsWith("<response>"));
		assertTrue(httpResponse.toString().contains("<code>404</code>"));
		assertTrue(httpResponse.toString().contains("<status>error</status>"));
		assertTrue(httpResponse.toString().contains("<message>Item not found</message>"));
		assertTrue(httpResponse.toString().contains("<data class=\"string\">NotFoundException</data>"));
		assertTrue(httpResponse.toString().endsWith("</response>"));
	}

	@Test
	public void shouldWrapNotFoundInJsendXmlUsingQueryString()
	{
		sendEvent(HttpMethod.GET, "/not_found?format=xml", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(0, observer.getSuccessCount());
		assertEquals(1, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertTrue(httpResponse.toString().startsWith("<response>"));
		assertTrue(httpResponse.toString().contains("<code>404</code>"));
		assertTrue(httpResponse.toString().contains("<status>error</status>"));
		assertTrue(httpResponse.toString().contains("<message>Item not found</message>"));
		assertTrue(httpResponse.toString().endsWith("</response>"));
	}

	@Test
	public void shouldWrapNullPointerInJsendJson()
	{
		sendEvent(HttpMethod.GET, "/null_pointer.json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(0, observer.getSuccessCount());
		assertEquals(1, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("{\"code\":500,\"status\":\"fail\",\"message\":\"Null and void\",\"data\":\"NullPointerException\"}", httpResponse.toString());
	}

	@Test
	public void shouldWrapNullPointerInJsendJsonUsingQueryString()
	{
		sendEvent(HttpMethod.GET, "/null_pointer?format=json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(0, observer.getSuccessCount());
		assertEquals(1, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("{\"code\":500,\"status\":\"fail\",\"message\":\"Null and void\",\"data\":\"NullPointerException\"}", httpResponse.toString());
	}

	@Test
	public void shouldWrapNullPointerInJsendXml()
	{
		sendEvent(HttpMethod.GET, "/null_pointer.xml", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(0, observer.getSuccessCount());
		assertEquals(1, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertTrue(httpResponse.toString().startsWith("<response>"));
		assertTrue(httpResponse.toString().contains("<code>500</code>"));
		assertTrue(httpResponse.toString().contains("<status>fail</status>"));
		assertTrue(httpResponse.toString().contains("<message>Null and void</message>"));
		assertTrue(httpResponse.toString().contains("<data class=\"string\">NullPointerException</data>"));
		assertTrue(httpResponse.toString().endsWith("</response>"));
	}

	@Test
	public void shouldWrapNullPointerInJsendXmlUsingQueryString()
	{
		sendEvent(HttpMethod.GET, "/null_pointer?format=xml", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(0, observer.getSuccessCount());
		assertEquals(1, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertTrue(httpResponse.toString().startsWith("<response>"));
		assertTrue(httpResponse.toString().contains("<code>500</code>"));
		assertTrue(httpResponse.toString().contains("<status>fail</status>"));
		assertTrue(httpResponse.toString().contains("<message>Null and void</message>"));
		assertTrue(httpResponse.toString().contains("<data class=\"string\">NullPointerException</data>"));
		assertTrue(httpResponse.toString().endsWith("</response>"));
	}

	@Test
	public void shouldWrapInvalidUrlWithJsonFormat()
	{
		sendEvent(HttpMethod.GET, "/xyzt.json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(0, observer.getSuccessCount());
		assertEquals(1, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("{\"code\":404,\"status\":\"error\",\"message\":\"Unresolvable URL: http://null/xyzt.json\",\"data\":\"NotFoundException\"}", httpResponse.toString());
	}

	@Test
	public void shouldWrapInvalidUrlWithJsonFormatUsingQueryString()
	{
		sendEvent(HttpMethod.GET, "/xyzt?format=json", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(0, observer.getSuccessCount());
		assertEquals(1, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertEquals("{\"code\":404,\"status\":\"error\",\"message\":\"Unresolvable URL: http://null/xyzt?format=json\",\"data\":\"NotFoundException\"}", httpResponse.toString());
	}

	@Test
	public void shouldWrapInvalidUrlWithXmlFormat()
	{
		sendEvent(HttpMethod.GET, "/xyzt.xml", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(0, observer.getSuccessCount());
		assertEquals(1, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertTrue(httpResponse.toString().startsWith("<response>"));
		assertTrue(httpResponse.toString().contains("<code>404</code>"));
		assertTrue(httpResponse.toString().contains("<status>error</status>"));
		assertTrue(httpResponse.toString().contains("<message>Unresolvable URL: http://null/xyzt.xml</message>"));
		assertTrue(httpResponse.toString().endsWith("</response>"));
	}

	@Test
	public void shouldWrapInvalidUrlWithXmlFormatUsingQueryString()
	{
		sendEvent(HttpMethod.GET, "/xyzt?format=xml", "");
		assertEquals(1, observer.getReceivedCount());
		assertEquals(1, observer.getCompleteCount());
		assertEquals(0, observer.getSuccessCount());
		assertEquals(1, observer.getExceptionCount());
//		System.out.println(httpResponse.toString());
		assertTrue(httpResponse.toString().startsWith("<response>"));
		assertTrue(httpResponse.toString().contains("<code>404</code>"));
		assertTrue(httpResponse.toString().contains("<status>error</status>"));
		assertTrue(httpResponse.toString().contains("<message>Unresolvable URL: http://null/xyzt?format=xml</message>"));
		assertTrue(httpResponse.toString().endsWith("</response>"));
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

        	uri("/not_found.{format}", controller, defaults)
        		.action("notFoundAction", HttpMethod.GET);

        	uri("/null_pointer.{format}", controller, defaults)
        		.action("nullPointerAction", HttpMethod.GET);
        }
	}
}
