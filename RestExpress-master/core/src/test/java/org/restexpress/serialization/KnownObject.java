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
package org.restexpress.serialization;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author toddf
 * @since Aug 4, 2011
 */
public class KnownObject
{
	public static final String CONSTANT = "i hope you don't see this";
	@SuppressWarnings("unused")
    private static final String INTERNAL = "or this";
	private static final Calendar KNOWN_CALENDAR = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
	
	static
	{
		KNOWN_CALENDAR.set(1964, Calendar.DECEMBER, 17, 23, 30, 0);
		KNOWN_CALENDAR.set(Calendar.MILLISECOND, 0);
	}

	public int integer = 1;
	public String string = "string value";
    public Date date = KNOWN_CALENDAR.getTime();
	private String p = "something private";
	public String[] sa;

	public String getP()
	{
		return p;
	}
	
	public String getQ()
	{
		return "Q(" + p + ")";
	}
}
