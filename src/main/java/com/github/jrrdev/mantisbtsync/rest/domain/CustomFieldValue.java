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

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Domain object stored into mantis_custom_field_string_table.
 *
 * @author jrrdev
 *
 */
@Entity
@Table(name = "mantis_custom_field_string_table")
public class CustomFieldValue {

	@EmbeddedId
	private CustomFieldValuePk primaryKey;

	private String fieldValue;

	/**
	 * @return the fieldValue
	 */
	public String getFieldValue() {
		return fieldValue;
	}

	/**
	 * @param fieldValue the fieldValue to set
	 */
	public void setFieldValue(final String fieldValue) {
		this.fieldValue = fieldValue;
	}

	/**
	 * @return the primaryKey
	 */
	public CustomFieldValuePk getPrimaryKey() {
		return primaryKey;
	}

	/**
	 * @param primaryKey the primaryKey to set
	 */
	public void setPrimaryKey(final CustomFieldValuePk primaryKey) {
		this.primaryKey = primaryKey;
	}

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
}
