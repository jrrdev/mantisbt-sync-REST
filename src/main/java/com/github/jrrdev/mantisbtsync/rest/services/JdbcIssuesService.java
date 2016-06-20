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
package com.github.jrrdev.mantisbtsync.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.github.jrrdev.mantisbtsync.rest.beans.IssueLineBean;

/**
 * Implementation of IssuesDao.
 *
 * @author jrrdev
 *
 */
@Repository
public class JdbcIssuesService implements IssuesDao {

	private static final String SQL_OPEN_ISSUES = "SELECT bug.id, bug.summary, bug.date_submitted, bug.handler_id\n"
			+ "	bug.status_id, stat.name as statusLabel,\n"
			+ "	bug.severity_id, sev.name as severityLabel,\n"
			+ "	usr.org_id, IFNULL(org.org_name, usr.name) as handlerLabel\n"
			+ " FROM mantis_bug_table bug\n"
			+ " JOIN mantis_enum_status stat ON stat.id = bug.status_id\n"
			+ " JOIN mantis_enum_severities sev ON sev.id = bug.severity_id\n"
			+ " JOIN mantis_user_table usr ON usr.id = bug.handler_id\n"
			+ " LEFT JOIN organization org ON org.org_id = usr.org_id\n"
			+ " JOIN mantis_project_table proj ON proj.id = bug.project_id\n"
			+ " WHERE (? IS NULL\n"
			+ " 	OR bug.project_id = ?\n"
			+ " 	)\n"
			+ " AND (? IS NULL\n"
			+ "      OR bug.status_id < (SELECT id FROM mantis_enum_status WHERE UPPER(name) = UPPER(?))\n"
			+ "     )\n"
			+ " ORDER BY handlerLabel, bug.status_id, bug.severity_id desc, bug.date_submitted";

	/**
	 * JDBC template.
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * @param jdbcTemplate the jdbcTemplate to set
	 */
	public void setJdbcTemplate(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * {@inheritDoc}
	 * @see com.github.jrrdev.mantisbtsync.rest.services.IssuesDao#getIssuesByProjectId(java.lang.Integer, String)
	 */
	@Override
	public List<IssueLineBean> getOpenIssuesByProjectId(final Integer projectId, final String maxStatus) {

		return jdbcTemplate.query(SQL_OPEN_ISSUES,
				new BeanPropertyRowMapper<IssueLineBean>(IssueLineBean.class),
				projectId, projectId, maxStatus, maxStatus);
	}
}
