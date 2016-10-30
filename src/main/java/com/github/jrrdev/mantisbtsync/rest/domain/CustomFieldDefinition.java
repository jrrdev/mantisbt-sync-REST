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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Domain object stored into mantis_custom_field_table.
 *
 * @author jrrdev
 *
 */
@Entity
@Table(name = "mantis_custom_field_table")
public class CustomFieldDefinition {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	@ManyToOne
	@JoinColumn(name="type_id")
	private CustomFieldType type;

	private String possibleValues;

	private String defaultValue;

	private String validRegexp;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name="mantis_custom_field_project_table",
			joinColumns=@JoinColumn(name="field_id", referencedColumnName="id"),
			inverseJoinColumns=@JoinColumn(name="project_id", referencedColumnName="id"))
	private List<Project> projects;

	@OneToMany(mappedBy="primaryKey.definition", fetch = FetchType.LAZY)
	private List<CustomFieldValue> fieldValues;

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
	 * @return the type
	 */
	public CustomFieldType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(final CustomFieldType type) {
		this.type = type;
	}

	/**
	 * @return the possibleValues
	 */
	public String getPossibleValues() {
		return possibleValues;
	}

	/**
	 * @param possibleValues the possibleValues to set
	 */
	public void setPossibleValues(final String possibleValues) {
		this.possibleValues = possibleValues;
	}

	/**
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(final String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * @return the validRegexp
	 */
	public String getValidRegexp() {
		return validRegexp;
	}

	/**
	 * @param validRegexp the validRegexp to set
	 */
	public void setValidRegexp(final String validRegexp) {
		this.validRegexp = validRegexp;
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
	 * @return the fieldValues
	 */
	public List<CustomFieldValue> getFieldValues() {
		return fieldValues;
	}

	/**
	 * @param fieldValues the fieldValues to set
	 */
	public void setFieldValues(final List<CustomFieldValue> fieldValues) {
		this.fieldValues = fieldValues;
	}
}
