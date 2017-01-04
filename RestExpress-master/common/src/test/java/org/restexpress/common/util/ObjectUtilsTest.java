package org.restexpress.common.util;

import static org.junit.Assert.*;

import org.junit.Test;
import org.restexpress.common.query.FilterComponent;

public class ObjectUtilsTest
{
	@Test
	public void testIsComparable()
	{
		assertFalse(ObjectUtils.isComparable(new FilterComponent(null, null, null)));
		assertTrue(ObjectUtils.isComparable(new InternalClass()));
	}
	@Test
	public void testAreComparable()
	{
		assertFalse(ObjectUtils.areComparable(new InternalClass(), new FilterComponent(null, null, null)));
		assertFalse(ObjectUtils.areComparable(new FilterComponent(null, null, null), new InternalClass()));
		assertTrue(ObjectUtils.areComparable(new InternalClass(), new InternalClass()));
		assertTrue(ObjectUtils.areComparable(new InternalClass(), new InternalAlso()));
		assertTrue(ObjectUtils.areComparable(new InternalAlso(), new InternalClass()));
	}

	@Test
	public void shouldHandleNullComparable()
	{
		ObjectUtils.areComparable(new InternalClass(), null);
		ObjectUtils.areComparable(null, new InternalClass());
		ObjectUtils.isComparable(null);
	}

	@Test
	public void shouldInvokePrivateMethod()
	{
		InternalClass o = new InternalClass();
		ObjectUtils.invokeMethod("_handle", o, 33, "something");
		assertEquals(33, o.handled());
		assertEquals(9, o.strlen());
	}

	@Test
	public void shouldReturnValue()
	{
		InternalClass o = new InternalClass();
		int i = ObjectUtils.invokeMethod("handle", o, 33, "something");
		assertEquals(42, i);
		assertEquals(33, o.handled());
		assertEquals(9, o.strlen());
	}

	public class InternalClass
	implements Comparable<InternalClass>
	{
		private int handled = 0;
		private int strlen = 0;

		@SuppressWarnings("unused")
        private void _handle(Integer value, String string)
		{
			handled += value;
			strlen += string.length();
		}

		public int handle(Integer value, String string)
		{
			handled += value;
			strlen += string.length();
			return value + strlen;
		}

		public int handled()
		{
			return handled;
		}

		public int strlen()
		{
			return strlen;
		}

		@Override
        public int compareTo(InternalClass o)
        {
	        return 0;
        }
	}

	public class InternalAlso
	extends InternalClass
	{
	}
}
