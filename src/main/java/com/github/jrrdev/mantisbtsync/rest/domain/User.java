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

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Domain object stored into mantis_user_table.
 *
 * @author jrrdev
 *
 */
@Entity
@Table(name = "mantis_user_table")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name="mantis_project_user_list_table",
			joinColumns=@JoinColumn(name="user_id", referencedColumnName="id"),
			inverseJoinColumns=@JoinColumn(name="project_id", referencedColumnName="id"))
	private List<Project> projects;

	@OneToMany(mappedBy="reporter", fetch = FetchType.LAZY)
	private List<Bug> reportedBugs;

	@OneToMany(mappedBy="handler", fetch = FetchType.LAZY)
	private List<Bug> handledBugs;

	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private List<BugHistory> bugModifications;

	@OneToMany(mappedBy="reporter", fetch = FetchType.LAZY)
	private List<BugNote> bugNotes;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the projects
	 */
	public List<Project> getProjects() {
		return projects;
	}

	/**
	 * @param projects the projects to set
	 */
	public void setProjects(final List<Project> projects) {
		this.projects = projects;
	}

	/**
	 * @return the reportedBugs
	 */
	public List<Bug> getReportedBugs() {
		return reportedBugs;
	}

	/**
	 * @param reportedBugs the reportedBugs to set
	 */
	public void setReportedBugs(final List<Bug> reportedBugs) {
		this.reportedBugs = reportedBugs;
	}

	/**
	 * @return the handledBugs
	 */
	public List<Bug> getHandledBugs() {
		return handledBugs;
	}

	/**
	 * @param handledBugs the handledBugs to set
	 */
	public void setHandledBugs(final List<Bug> handledBugs) {
		this.handledBugs = handledBugs;
	}

	/**
	 * @return the bugModifications
	 */
	public List<BugHistory> getBugModifications() {
		return bugModifications;
	}

	/**
	 * @param bugModifications the bugModifications to set
	 */
	public void setBugModifications(final List<BugHistory> bugModifications) {
		this.bugModifications = bugModifications;
	}

	/**
	 * @return the bugNotes
	 */
	public List<BugNote> getBugNotes() {
		return bugNotes;
	}

	/**
	 * @param bugNotes the bugNotes to set
	 */
	public void setBugNotes(final List<BugNote> bugNotes) {
		this.bugNotes = bugNotes;
	}

}
