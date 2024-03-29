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

package com.google.gdata.data.media.mediarss;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sk.seges.acris.json.client.annotation.Field;
import sk.seges.acris.json.client.annotation.JsonObject;

@JsonObject(group = MediaRssNamespace.PREFIX, value = "restriction")
public class MediaRestriction extends AbstractElementWithContent {
	
	@Field
	private MediaRestriction.Type type;
	
	@Field
	private MediaRestriction.Relationship relationship;

	public MediaRestriction.Relationship getRelationship() {
		return relationship;
	}

	public void setRelationship(MediaRestriction.Relationship relationship) {
		this.relationship = relationship;
	}

	public MediaRestriction.Type getType() {
		return type;
	}

	public void setType(MediaRestriction.Type type) {
		this.type = type;
	}

	/**
	 * Sets the content as a space-separated list of country codes.
	 * 
	 * @param codes
	 *            a list of country codes
	 */
	public void setContentAsCountryCodeList(List<String> codes) {
		if (codes.isEmpty()) {
			setContent(null);
			return;
		}

		StringBuilder builder = new StringBuilder();
		boolean isFirst = true;
		for (String code : codes) {
			if (isFirst) {
				isFirst = false;
			} else {
				builder.append(' ');
			}
			builder.append(code);
		}
		setContent(builder.toString());
	}

	/**
	 * Gets the content as a space-separated list of country codes.
	 * 
	 * Check the type before calling this method.
	 * 
	 * @return a list of strings
	 */
	public List<String> getContentAsCountryCodeList() {
		if (getContent() == null) {
			return Collections.emptyList();
		}
		List<String> countries = new ArrayList<String>();

		String[] toks = getContent().split(" ");

		for (String tok : toks) {
			countries.add(tok.trim());
		}
		return countries;
	}

	/** Values for the type attribute. */
	public static enum Type {
		COUNTRY, URI;
	}

	/** Values for the relationship attribute. */
	public static enum Relationship {
		ALLOW, DENY
	}
}
