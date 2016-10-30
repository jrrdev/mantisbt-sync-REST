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
package com.github.jrrdev.mantisbtsync.rest.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Domain object stored into mantis_bug_table.
 *
 * @author jrrdev
 *
 */
@Entity
@Table(name = "mantis_bug_table")
public class Bug {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	@JoinColumn(name="project_id")
	private Project project;

	@ManyToOne
	@JoinColumn(name="reporter_id")
	private User reporter;

	@ManyToOne
	@JoinColumn(name="handler_id")
	private User handler;

	@ManyToOne
	@JoinColumn(name="priority_id")
	private Priority priority;

	@ManyToOne
	@JoinColumn(name="severity_id")
	private Severity severity;

	@ManyToOne
	@JoinColumn(name="status_id")
	private Status status;

	@ManyToOne
	@JoinColumn(name="resolution_id")
	private Resolution resolution;

	private String description;

	private String stepsToReproduce;

	private String additionalInformation;

	private String platform;

	private String version;

	private String fixedInVersion;

	private String targetVersion;

	private String summary;

	private String category;

	private Date dateSubmitted;

	private Date lastUpdated;

	@OneToMany(mappedBy="bug", fetch = FetchType.LAZY)
	private List<BugNote> notes;

	@OneToMany(mappedBy="primaryKey.bug", fetch = FetchType.LAZY)
	private List<CustomFieldValue> customFields;

	@OneToMany(mappedBy="bug", fetch = FetchType.LAZY)
	private List<BugHistory> histories;


	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final long id) {
		this.id = id;
	}

	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(final Project project) {
		this.project = project;
	}

	/**
	 * @return the reporter
	 */
	public User getReporter() {
		return reporter;
	}

	/**
	 * @param reporter the reporter to set
	 */
	public void setReporter(final User reporter) {
		this.reporter = reporter;
	}

	/**
	 * @return the handler
	 */
	public User getHandler() {
		return handler;
	}

	/**
	 * @param handler the handler to set
	 */
	public void setHandler(final User handler) {
		this.handler = handler;
	}

	/**
	 * @return the priority
	 */
	public Priority getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(final Priority priority) {
		this.priority = priority;
	}

	/**
	 * @return the severity
	 */
	public Severity getSeverity() {
		return severity;
	}

	/**
	 * @param severity the severity to set
	 */
	public void setSeverity(final Severity severity) {
		this.severity = severity;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(final Status status) {
		this.status = status;
	}

	/**
	 * @return the resolution
	 */
	public Resolution getResolution() {
		return resolution;
	}

	/**
	 * @param resolution the resolution to set
	 */
	public void setResolution(final Resolution resolution) {
		this.resolution = resolution;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the stepsToReproduce
	 */
	public String getStepsToReproduce() {
		return stepsToReproduce;
	}

	/**
	 * @param stepsToReproduce the stepsToReproduce to set
	 */
	public void setStepsToReproduce(final String stepsToReproduce) {
		this.stepsToReproduce = stepsToReproduce;
	}

	/**
	 * @return the additionalInformation
	 */
	public String getAdditionalInformation() {
		return additionalInformation;
	}

	/**
	 * @param additionalInformation the additionalInformation to set
	 */
	public void setAdditionalInformation(final String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	/**
	 * @return the platform
	 */
	public String getPlatform() {
		return platform;
	}

	/**
	 * @param platform the platform to set
	 */
	public void setPlatform(final String platform) {
		this.platform = platform;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(final String version) {
		this.version = version;
	}

	/**
	 * @return the fixedInVersion
	 */
	public String getFixedInVersion() {
		return fixedInVersion;
	}

	/**
	 * @param fixedInVersion the fixedInVersion to set
	 */
	public void setFixedInVersion(final String fixedInVersion) {
		this.fixedInVersion = fixedInVersion;
	}

	/**
	 * @return the targetVersion
	 */
	public String getTargetVersion() {
		return targetVersion;
	}

	/**
	 * @param targetVersion the targetVersion to set
	 */
	public void setTargetVersion(final String targetVersion) {
		this.targetVersion = targetVersion;
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
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(final String category) {
		this.category = category;
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
	 * @return the lastUpdated
	 */
	public Date getLastUpdated() {
		return lastUpdated;
	}

	/**
	 * @param lastUpdated the lastUpdated to set
	 */
	public void setLastUpdated(final Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	/**
	 * @return the notes
	 */
	public List<BugNote> getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(final List<BugNote> notes) {
		this.notes = notes;
	}

	/**
	 * @return the customFields
	 */
	public List<CustomFieldValue> getCustomFields() {
		return customFields;
	}

	/**
	 * @param customFields the customFields to set
	 */
	public void setCustomFields(final List<CustomFieldValue> customFields) {
		this.customFields = customFields;
	}

	/**
	 * @return the histories
	 */
	public List<BugHistory> getHistories() {
		return histories;
	}

	/**
	 * @param histories the histories to set
	 */
	public void setHistories(final List<BugHistory> histories) {
		this.histories = histories;
	}

}
