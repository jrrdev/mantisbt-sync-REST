package com.github.jrrdev.mantisbtsync.rest.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class CustomFieldValuePk implements Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 3706587367789536118L;

	@ManyToOne
	@JoinColumn(name="bug_id")
	private Bug bug;

	@ManyToOne
	@JoinColumn(name="field_id")
	private CustomFieldDefinition definition;

	/**
	 * @return the bug
	 */
	public Bug getBug() {
		return bug;
	}

	/**
	 * @param bug the bug to set
	 */
	public void setBug(final Bug bug) {
		this.bug = bug;
	}

	/**
	 * @return the definition
	 */
	public CustomFieldDefinition getDefinition() {
		return definition;
	}

	/**
	 * @param definition the definition to set
	 */
	public void setDefinition(final CustomFieldDefinition definition) {
		this.definition = definition;
	}
}