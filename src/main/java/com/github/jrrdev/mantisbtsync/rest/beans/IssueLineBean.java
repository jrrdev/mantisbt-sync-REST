/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Jérard Devarulrajah
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.jrrdev.mantisbtsync.rest.beans;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jrrdev
 *
 */
public class IssueLineBean {

	@JsonProperty(value = "Issue")
	private Integer id;

	@JsonProperty(value = "Label")
	private String summary;

	@JsonIgnore
	private Integer statusId;

	@JsonProperty(value = "Status")
	private String statusLabel;

	@JsonIgnore
	private Integer severityId;

	@JsonProperty(value = "Severity")
	private String severityLabel;

	@JsonProperty(value = "Date Submitted")
	private Date dateSubmitted;

	@JsonIgnore
	private Integer orgId;

	@JsonIgnore
	private Integer handlerId;

	@JsonIgnore
	private String handlerLabel;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(final String summary) {
		this.summary = summary;
	}

	/**
	 * @return the statusId
	 */
	public Integer getStatusId() {
		return statusId;
	}

	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(final Integer statusId) {
		this.statusId = statusId;
	}

	/**
	 * @return the statusLabel
	 */
	public String getStatusLabel() {
		return statusLabel;
	}

	/**
	 * @param statusLabel the statusLabel to set
	 */
	public void setStatusLabel(final String statusLabel) {
		this.statusLabel = statusLabel;
	}

	/**
	 * @return the severityId
	 */
	public Integer getSeverityId() {
		return severityId;
	}

	/**
	 * @param severityId the severityId to set
	 */
	public void setSeverityId(final Integer severityId) {
		this.severityId = severityId;
	}

	/**
	 * @return the severityLabel
	 */
	public String getSeverityLabel() {
		return severityLabel;
	}

	/**
	 * @param severityLabel the severityLabel to set
	 */
	public void setSeverityLabel(final String severityLabel) {
		this.severityLabel = severityLabel;
	}

	/**
	 * @return the dateSubmitted
	 */
	public Date getDateSubmitted() {
		return dateSubmitted;
	}

	/**
	 * @param dateSubmitted the dateSubmitted to set
	 */
	public void setDateSubmitted(final Date dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	/**
	 * @return the orgId
	 */
	public Integer getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(final Integer orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return the handlerId
	 */
	public Integer getHandlerId() {
		return handlerId;
	}

	/**
	 * @param handlerId the handlerId to set
	 */
	public void setHandlerId(final Integer handlerId) {
		this.handlerId = handlerId;
	}

	/**
	 * @return the handlerLabel
	 */
	public String getHandlerLabel() {
		return handlerLabel;
	}

	/**
	 * @param handlerLabel the handlerLabel to set
	 */
	public void setHandlerLabel(final String handlerLabel) {
		this.handlerLabel = handlerLabel;
	}
}
