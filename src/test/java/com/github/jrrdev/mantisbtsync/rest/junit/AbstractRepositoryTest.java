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
package com.github.jrrdev.mantisbtsync.rest.junit;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.Destination;
import com.ninja_squad.dbsetup.operation.Operation;

/**
 * Abstract Junit class to test writing to database.
 * Hold the DBSetup configuration.
 *
 * @author jrrdev
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractRepositoryTest {

	@Autowired
	private Destination dbSetupDest;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	protected final void lauchOperation(final Operation op) {
		new DbSetup(dbSetupDest, op).launch();
	}

	protected final Destination getDbSetupDest() {
		return dbSetupDest;
	}

	public final void setDbSetupDest(final Destination dbSetupDest) {
		this.dbSetupDest = dbSetupDest;
	}

	protected final JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public final void setJdbcTemplate(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Before
	public void preparaDatabase() {
		lauchOperation(deleteAllFrom(
				"handlers_stats",
				"mantis_bug_history_table",
				"mantis_custom_field_string_table",
				"mantis_bugnote_table",
				"mantis_bug_table",
				"mantis_custom_field_project_table",
				"mantis_custom_field_table",
				"mantis_category_table",
				"mantis_project_user_list_table",
				"organization",
				"mantis_user_table",
				"mantis_project_version_table",
				"mantis_project_hierarchy_table",
				"mantis_project_table",
				"mantis_enum_custom_field_types",
				"mantis_enum_etas",
				"mantis_enum_priorities",
				"mantis_enum_projections",
				"mantis_enum_project_status",
				"mantis_enum_project_view_states",
				"mantis_enum_reproducibilities",
				"mantis_enum_resolutions",
				"mantis_enum_severities",
				"mantis_enum_status"));
	}
}
