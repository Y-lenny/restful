/*
 * Copyright 2009, Strategic Gains, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.restexpress.serialization;

/**
 * @author toddf
 * @since Nov 20, 2009
 */
public class SerializationException
extends RuntimeException
{
    private static final long serialVersionUID = -5208426797622464550L;

	public SerializationException()
	{
	}

	/**
	 * @param message
	 */
	public SerializationException(String message)
	{
		super(message);
	}

	/**
	 * @param cause
	 */
	public SerializationException(Throwable cause)
	{
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SerializationException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
