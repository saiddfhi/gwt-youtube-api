/* Copyright (c) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
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

package com.google.gdata.data.geo.impl;

import com.google.gdata.data.geo.Point;

/**
 * Point that represents the upper corner of a gml:Envelope.
 * 
 * 
 */
public class GmlUpperCorner extends PointConstruct {

	static final String NAME = "upperCorner";

	/**
	 * Constructs an empty gml:upperCorner element.
	 */
	public GmlUpperCorner() {
		super(NAME);
	}

	/**
	 * Constructs a gml:upperCorner element with the given lat and lon.
	 */
	public GmlUpperCorner(Double lat, Double lon) {
		super(NAME, lat, lon);
	}

	/**
	 * Constructs a gml:upperCorner element by copying the data from the given
	 * point. If the given point is null an empty point will be created.
	 */
	public GmlUpperCorner(Point copyFrom) {
		super(NAME, copyFrom);
	}
}