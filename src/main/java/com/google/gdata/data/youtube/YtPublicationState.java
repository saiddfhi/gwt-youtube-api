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

package com.google.gdata.data.youtube;

import sk.seges.acris.json.client.annotation.Field;
import sk.seges.acris.json.client.annotation.JsonObject;
import sk.seges.acris.json.client.extension.ExtensionPoint;

@JsonObject(group = YouTubeNamespace.PREFIX, value = "state")
public class YtPublicationState extends ExtensionPoint {
	/**
	 * Current state.
	 * 
	 * This tag never appears on published (live) entries, so there is no state {@code PUBLISHED}.
	 */
	public static enum State {

		/**
		 * The entry has not been completely defined yet, but it's been stored already. Either the entry data or the
		 * description is missing.
		 */
		INCOMPLETE,

		/** The entry has just been uploaded and is not yet publicly available. */
		PROCESSING,

		/** The entry has been rejected. */
		REJECTED,

		/** The entry was not successfully processed. */
		FAILED,

		/** Entry was removed by the owner. */
		DELETED,

		/** Entry is restricted for the current user, see reasonCode for details. */
		RESTRICTED, ;
	}

	@Field
	private State state;
	@Field
	private String reasonCode;
	@Field
	private String helpUrl;
	@Field
	private String description;

	public YtPublicationState() {
	}

	public YtPublicationState(State state, String reasonCode, String helpUrl, String description) {
		this.state = state;
		this.reasonCode = reasonCode;
		this.helpUrl = helpUrl;
		this.description = description;
	}

	public YtPublicationState(State state) {
		this.state = state;
	}

	/**
	 * Gets the publication state enum value.
	 * 
	 * @return publication state
	 */
	public State getState() {
		return state;
	}

	/**
	 * Sets the publication state enum value.
	 * 
	 * @param state
	 *            publication state
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * Gets a code describing the reason why the entry was rejected or why processing the entry failed.
	 * 
	 * @return a code or {@code null}
	 */
	public String getReasonCode() {
		return reasonCode;
	}

	/**
	 * Sets the code describing the reason why the entry was rejected or why processing the entry failed.
	 * 
	 * @param code
	 *            a code or {@code null}
	 */
	public void setReasonCode(String code) {
		this.reasonCode = code;
	}

	/**
	 * Returns, in a human-readable form, the reason why the entry was rejected or why processing the entry failed.
	 * 
	 * This error message is only meant to help debugging and should not be parsed automatically. The exact message will
	 * change with time. Please use {@link #getReasonCode()} for that.
	 * 
	 * @return description human-readable error description or {@code null}
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets a human-readable error message.
	 * 
	 * @param description
	 *            or {@code null}
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns a HTTP url which describes the failure in more details and/or provides a way of solving the problem.
	 * 
	 * @return url a url or {@code null}
	 */
	public String getHelpUrl() {
		return helpUrl;
	}

	/**
	 * Sets a url to which users can learn more about the current issue.
	 * 
	 * @param url
	 *            an HTTP url or {@code null}
	 */
	public void setHelpUrl(String url) {
		this.helpUrl = url;
	}
}