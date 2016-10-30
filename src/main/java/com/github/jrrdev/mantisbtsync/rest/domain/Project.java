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
 * Domain object stored into mantis_project_table.
 *
 * @author jrrdev
 *
 */
@Entity
@Table(name = "mantis_project_table")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name="mantis_project_hierarchy_table",
			joinColumns=@JoinColumn(name="parent_id", referencedColumnName="id"),
			inverseJoinColumns=@JoinColumn(name="child_id", referencedColumnName="id"))
	private List<Project> childProjects;

	@ManyToMany(mappedBy="childProjects", fetch = FetchType.LAZY)
	private List<Project> parentProjects;

	@OneToMany(mappedBy="project", fetch = FetchType.LAZY)
	private List<Category> categories;

	@ManyToMany(mappedBy="projects", fetch = FetchType.LAZY)
	private List<CustomFieldDefinition> customFields;

	@ManyToMany(mappedBy="projects", fetch = FetchType.LAZY)
	private List<User> users;

	@OneToMany(mappedBy="project", fetch = FetchType.LAZY)
	private List<Version> versions;

	@OneToMany(mappedBy="project", fetch = FetchType.LAZY)
	private List<Bug> bugs;

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
	 * @return the childProjects
	 */
	public List<Project> getChildProjects() {
		return childProjects;
	}

	/**
	 * @param childProjects the childProjects to set
	 */
	public void setChildProjects(final List<Project> childProjects) {
		this.childProjects = childProjects;
	}

	/**
	 * @return the categories
	 */
	public List<Category> getCategories() {
		return categories;
	}

	/**
	 * @param categories the categories to set
	 */
	public void setCategories(final List<Category> categories) {
		this.categories = categories;
	}

	/**
	 * @return the customFields
	 */
	public List<CustomFieldDefinition> getCustomFields() {
		return customFields;
	}

	/**
	 * @param customFields the customFields to set
	 */
	public void setCustomFields(final List<CustomFieldDefinition> customFields) {
		this.customFields = customFields;
	}

	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(final List<User> users) {
		this.users = users;
	}

	/**
	 * @return the versions
	 */
	public List<Version> getVersions() {
		return versions;
	}

	/**
	 * @param versions the versions to set
	 */
	public void setVersions(final List<Version> versions) {
		this.versions = versions;
	}

	/**
	 * @return the bugs
	 */
	public List<Bug> getBugs() {
		return bugs;
	}

	/**
	 * @param bugs the bugs to set
	 */
	public void setBugs(final List<Bug> bugs) {
		this.bugs = bugs;
	}

	/**
	 * @return the parentProjects
	 */
	public List<Project> getParentProjects() {
		return parentProjects;
	}

	/**
	 * @param parentProjects the parentProjects to set
	 */
	public void setParentProjects(final List<Project> parentProjects) {
		this.parentProjects = parentProjects;
	}

}
