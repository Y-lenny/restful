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

import java.util.Properties;

import org.restexpress.common.exception.ConfigurationException;

/**
 * @author tfredrich
 * @since Jul 22, 2015
 */
public class TestConfig
extends Environment
{
	private String fromWhere;

	public TestConfig()
	{
		super();
	}

	@Override
    protected void fillValues(Properties p) throws ConfigurationException
    {
		fromWhere = p.getProperty("where");
    }

	public String fromWhere()
	{
		return fromWhere;
	}
}
