/**
 *
 */
package com.github.jrrdev.mantisbtsync.rest;

import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.github.jrrdev.mantisbtsync.rest.junit.AbstractRepositoryTest;
import com.ninja_squad.dbsetup.generator.ValueGenerators;
import com.ninja_squad.dbsetup.operation.Operation;

/**
 * @author jrrdev
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BugRestTest extends AbstractRepositoryTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testFindByProjectIdAndHandlerIdAndStatusIdNotIn() throws Exception {
		final Operation op = sequenceOf(
				insertInto("mantis_enum_status")
				.columns("id", "name")
				.values(1, "target status")
				.values(2, "other status")
				.build(),

				insertInto("mantis_project_table")
				.columns("id", "name")
				.values(1, "target project")
				.values(2, "other project")
				.build(),

				insertInto("mantis_user_table")
				.columns("id", "name")
				.values(1, "target user")
				.values(2, "other user")
				.build(),

				insertInto("mantis_bug_table")
				.columns("id", "project_id", "status_id", "handler_id", "summary", "last_sync")
				.values(1, 1, 1, 1, "should be in result", ValueGenerators.dateSequence().nextValue())
				.values(2, 2, 1, 1, "wrong project", ValueGenerators.dateSequence().nextValue())
				.values(3, 1, 1, 2, "wrong handler", ValueGenerators.dateSequence().nextValue())
				.values(4, 1, 2, 1, "wrong status", ValueGenerators.dateSequence().nextValue())
				.build());

		lauchOperation(op);

		mockMvc.perform(
				get("/bugs/search/findByProjectIdAndHandlerIdAndStatusIdNotIn"
						+ "?project={id}&handler={handler}&status={status}",
						1L, 1L, 2L))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$._embedded.bugs[0].summary").value("should be in result"));
	}

	@Test
	public void testFindByProjectIdAndTargetVersion() throws Exception {

		final Operation op = sequenceOf(

				insertInto("mantis_project_table")
				.columns("id", "name")
				.values(1, "target project")
				.values(2, "other project")
				.build(),

				insertInto("mantis_bug_table")
				.columns("id", "project_id", "target_version", "summary", "last_sync")
				.values(1, 1, "1.0", "should be in result", ValueGenerators.dateSequence().nextValue())
				.values(2, 2, "1.0", "wrong project", ValueGenerators.dateSequence().nextValue())
				.values(3, 1, null, "null version", ValueGenerators.dateSequence().nextValue())
				.values(4, 1, "2.0", "wrong version", ValueGenerators.dateSequence().nextValue())
				.build());

		lauchOperation(op);

		mockMvc.perform(
				get("/bugs/search/findByProjectIdAndTargetVersion"
						+ "?project={id}&version={version}",
						1L, "1.0"))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$._embedded.bugs[0].summary").value("should be in result"));
	}

	@Test
	public void testFindByProjectIdAndTargetVersionAndStatusIdIn() throws Exception {

		final Operation op = sequenceOf(
				insertInto("mantis_enum_status")
				.columns("id", "name")
				.values(1, "target status")
				.values(2, "other status")
				.build(),

				insertInto("mantis_project_table")
				.columns("id", "name")
				.values(1, "target project")
				.values(2, "other project")
				.build(),

				insertInto("mantis_bug_table")
				.columns("id", "project_id", "target_version", "status_id", "summary", "last_sync")
				.values(1, 1, "1.0", 1, "should be in result", ValueGenerators.dateSequence().nextValue())
				.values(2, 2, "1.0", 1, "wrong project", ValueGenerators.dateSequence().nextValue())
				.values(3, 1, null, 1, "null version", ValueGenerators.dateSequence().nextValue())
				.values(4, 1, "2.0", 1, "wrong version", ValueGenerators.dateSequence().nextValue())
				.values(5, 1, "1.0", 2, "wrong status", ValueGenerators.dateSequence().nextValue())
				.build());

		lauchOperation(op);

		mockMvc.perform(
				get("/bugs/search/findByProjectIdAndTargetVersionAndStatusIdIn"
						+ "?project={id}&version={version}&status={status}",
						1L, "1.0", 1L))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$._embedded.bugs[0].summary").value("should be in result"));
	}

	@Test
	public void testProjectionBugSummary() throws Exception {

		final Operation op = sequenceOf(
				insertInto("mantis_enum_status")
				.columns("id", "name")
				.values(1, "target status")
				.build(),

				insertInto("mantis_project_table")
				.columns("id", "name")
				.values(1, "target project")
				.build(),

				insertInto("mantis_user_table")
				.columns("id", "name")
				.values(1, "target user")
				.build(),

				insertInto("mantis_bug_table")
				.columns("id", "project_id", "status_id", "handler_id", "summary", "last_sync")
				.values(1, 1, 1, 1, "should be in result", ValueGenerators.dateSequence().nextValue())
				.build());

		lauchOperation(op);

		mockMvc.perform(
				get("/bugs/1?projection={projection}", "bugSummary"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.summary").value("should be in result"))
				.andExpect(jsonPath("$.handlerName").value("target user"))
				.andExpect(jsonPath("$.statusName").value("target status"));
	}

}
