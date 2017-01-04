/*
    Copyright 2015, Strategic Gains, Inc.

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
package org.restexpress.util;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

/**
 * @author fredta2
 * @since Jul 22, 2015
 */
public class EnvironmentTest
{
	@Test
	public void shouldLoadFromClasspath()
	throws FileNotFoundException, IOException
	{
		TestConfig e = Environment.fromDefault(TestConfig.class);
		assertNotNull(e);
		assertEquals("classpath", e.fromWhere());
	}

	@Test
	public void shouldAttemptLoadingFromFileSystem()
	throws IOException
	{
		try
		{
			Environment.from("prod", TestConfig.class);
		}
		catch(FileNotFoundException e)
		{
			if(e.getMessage().contains("config/prod/environment.properties")) return;
			else
			{
				fail("Did not throw appropriate error: " + e.getMessage());
			}
		}

	}
}
