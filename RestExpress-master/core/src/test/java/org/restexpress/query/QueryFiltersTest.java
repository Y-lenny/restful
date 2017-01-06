/*
    Copyright 2012, Strategic Gains, Inc.

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
package org.restexpress.query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.restexpress.Request;
import org.restexpress.common.query.FilterCallback;
import org.restexpress.common.query.FilterComponent;
import org.restexpress.common.query.FilterOperator;
import org.restexpress.common.query.QueryFilter;
import org.restexpress.exception.BadRequestException;

/**
 * @author toddf
 * @since Jul 27, 2012
 */
public class QueryFiltersTest
{
	@Test
	public void shouldParseEqualityOperatorFromQueryString()
	{
		FullHttpRequest httpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "http://www.example.com/somethings?filter=name:=:todd|description:!=:amazing");
		Request request = new Request(httpRequest, null);
		QueryFilter f = QueryFilters.parseFrom(request);
		assertTrue(f.hasFilters());
		FCallback callback = new FCallback();
		f.iterate(callback);
		assertEquals(2, callback.getFilterCount());
		assertEquals("todd", callback.get("name").getValue());
		assertEquals(FilterOperator.EQUALS, callback.get("name").getOperator());
		assertEquals("amazing", callback.get("description").getValue());
		assertEquals(FilterOperator.NOT_EQUALS, callback.get("description").getOperator());
	}

	@Test
	public void shouldParseLessThanOperatorsFromQueryString()
	{
		FullHttpRequest httpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "http://www.example.com/somethings?filter=name:<:todd|description:<=:amazing");
		Request request = new Request(httpRequest, null);
		QueryFilter f = QueryFilters.parseFrom(request);
		assertTrue(f.hasFilters());
		FCallback callback = new FCallback();
		f.iterate(callback);
		assertEquals(2, callback.getFilterCount());
		assertEquals("todd", callback.get("name").getValue());
		assertEquals(FilterOperator.LESS_THAN, callback.get("name").getOperator());
		assertEquals("amazing", callback.get("description").getValue());
		assertEquals(FilterOperator.LESS_THAN_OR_EQUAL_TO, callback.get("description").getOperator());
	}

	@Test
	public void shouldParseGreaterThanOperatorsFromQueryString()
	{
		FullHttpRequest httpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "http://www.example.com/somethings?filter=name:>=:todd|description:>:amazing");
		Request request = new Request(httpRequest, null);
		QueryFilter f = QueryFilters.parseFrom(request);
		assertTrue(f.hasFilters());
		FCallback callback = new FCallback();
		f.iterate(callback);
		assertEquals(2, callback.getFilterCount());
		assertEquals("todd", callback.get("name").getValue());
		assertEquals(FilterOperator.GREATER_THAN_OR_EQUAL_TO, callback.get("name").getOperator());
		assertEquals("amazing", callback.get("description").getValue());
		assertEquals(FilterOperator.GREATER_THAN, callback.get("description").getOperator());
	}

	@Test
	public void shouldParseQueryString()
	{
		FullHttpRequest httpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "http://www.example.com/somethings?filter=name::todd|description::amazing");
		Request request = new Request(httpRequest, null);
		QueryFilter f = QueryFilters.parseFrom(request);
		assertTrue(f.hasFilters());
		FCallback callback = new FCallback();
		f.iterate(callback);
		assertEquals(2, callback.getFilterCount());
		assertEquals("todd", callback.get("name").getValue());
		assertEquals(FilterOperator.CONTAINS, callback.get("name").getOperator());
		assertEquals("amazing", callback.get("description").getValue());
		assertEquals(FilterOperator.CONTAINS, callback.get("description").getOperator());
	}

	@Test
	public void shouldParseFilterHeader()
	{
		FullHttpRequest httpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "http://www.example.com/somethings");
		httpRequest.headers().add("filter", "name::todd|description::amazing");
		Request request = new Request(httpRequest, null);
		QueryFilter f = QueryFilters.parseFrom(request);
		assertTrue(f.hasFilters());
		FCallback callback = new FCallback();
		f.iterate(callback);
		assertEquals(2, callback.getFilterCount());
		assertEquals("todd", callback.get("name").getValue());
		assertEquals(FilterOperator.CONTAINS, callback.get("name").getOperator());
		assertEquals("amazing", callback.get("description").getValue());
		assertEquals(FilterOperator.CONTAINS, callback.get("name").getOperator());
	}

	@Test
	public void shouldAllowSupportedFilterProperties()
	{
		FullHttpRequest httpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "http://www.example.com/somethings");
		httpRequest.headers().add("filter", "name:=:todd|description:*:amazing");
		Request request = new Request(httpRequest, null);
		QueryFilter f = QueryFilters.parseFrom(request, Arrays.asList(new String[] {"name", "description"}));
		assertTrue(f.hasFilters());
		FCallback callback = new FCallback();
		f.iterate(callback);
		assertEquals(2, callback.getFilterCount());
		assertEquals("todd", callback.get("name").getValue());
		assertEquals(FilterOperator.EQUALS, callback.get("name").getOperator());
		assertEquals("amazing", callback.get("description").getValue());
		assertEquals(FilterOperator.STARTS_WITH, callback.get("description").getOperator());
	}

	@Test
	public void shouldParseInOperatorsFromQueryString()
	{
		FullHttpRequest httpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "http://www.example.com/somethings?filter=name:IN:todd,fred,rick");
		Request request = new Request(httpRequest, null);
		QueryFilter f = QueryFilters.parseFrom(request);
		assertTrue(f.hasFilters());
		FCallback callback = new FCallback();
		f.iterate(callback);
		assertEquals(1, callback.getFilterCount());
		assertEquals(FilterOperator.IN, callback.get("name").getOperator());
		String[] ins = (String[]) callback.get("name").getValue();
		assertNotNull(ins);
		assertEquals(3, ins.length);
		assertEquals("todd", ins[0]);
		assertEquals("fred", ins[1]);
		assertEquals("rick", ins[2]);
	}

	@Test(expected=BadRequestException.class)
	public void shouldThrowOnInvalidFilter()
	{
		FullHttpRequest httpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "http://www.example.com/somethings");
		httpRequest.headers().add("filter", "name::todd|description::amazing");
		Request request = new Request(httpRequest, null);
		QueryFilters.parseFrom(request, Arrays.asList(new String[] {"abc", "def", "ghi"}));
	}

	@Test
	public void shouldAllowSingleFilter()
	{
		FullHttpRequest httpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "http://www.example.com/somethings");
		httpRequest.headers().add("filter", "abc::todd");
		Request request = new Request(httpRequest, null);
		QueryFilter f = QueryFilters.parseFrom(request, Arrays.asList(new String[] {"abc", "def", "ghi"}));
		assertTrue(f.hasFilters());
	}

	@Test(expected=BadRequestException.class)
	public void shouldThrowOnSingleInvalidFilter()
	{
		FullHttpRequest httpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "http://www.example.com/somethings");
		httpRequest.headers().add("filter", "name::todd");
		Request request = new Request(httpRequest, null);
		QueryFilters.parseFrom(request, Arrays.asList(new String[] {"abc", "def", "ghi"}));
	}
	
	private class FCallback
	implements FilterCallback
	{
		private Map<String, FilterComponent> filters = new HashMap<String, FilterComponent>();

        @Override
        public void filterOn(FilterComponent c)
        {
        	filters.put(c.getField(), c);
        }

        public int getFilterCount()
        {
        	return filters.size();
        }
        
        public FilterComponent get(String name)
        {
        	return filters.get(name);
        }
	}
}
