/**
 *
 */
package com.github.jrrdev.mantisbtsync.rest.domain.projections;

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
public class BugSummaryTest extends AbstractRepositoryTest {

	@Autowired
	private MockMvc mockMvc;

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
				.andExpect(jsonPath("$.statusName").value("target status"))
				.andExpect(jsonPath("$.targetVersion").doesNotExist());
	}
}
