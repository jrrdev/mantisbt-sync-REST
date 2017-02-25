/**
 * 
 */
package com.github.jrrdev.mantisbtsync.rest;

import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.github.jrrdev.mantisbtsync.rest.junit.AbstractRepositoryTest;
import com.ninja_squad.dbsetup.operation.Operation;

/**
 * JUnit test for handlerStats rest calls
 * @author slopezgarcia
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HandlerStatsRestTest extends AbstractRepositoryTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testFindStatsByProjectIdAndStatusIdNotInWithSopraHandler() throws Exception {

		final Date date = new Date();
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, -1);
		final Date dateMoinsUn = cal.getTime();
		final Operation op = sequenceOf(
				insertInto("mantis_enum_status").columns("id", "name")
						.values(1, "target status").values(2, "other status")
						.values(3, "third status").values(4, "fourth status")
						.build(),

				insertInto("mantis_project_table").columns("id", "name")
						.values(1, "target project").values(2, "other project")
						.values(3, "third project").build(),

				insertInto("mantis_user_table").columns("id", "name")
						.values(1, "sopra user").values(2, "other user")
						.values(3, "third user").build(),

				insertInto("handlers_stats")
						.columns("id", "compute_date", "project_id",
								"handler_id", "status_id", "nb_issues")
						.values(1, date, 1, 1, 1, 4)
						.values(2, date, 2, 1, 1, 2)
						.values(3, date, 3, 1, 1, 1)
						.values(4, date, 1, 1, 2, 3)
						.values(5, date, 1, 2, 1, 4)
						.values(6, date, 2, 3, 1, 2)
						.values(7, date, 3, 2, 1, 1)
						.values(8, date, 1, 3, 2, 3)
						.values(9, date, 1, 1, 3, 2)
						.values(10, date, 2, 1, 4, 2)
						.values(11, date, 1, 2, 4, 4)
						.values(12, date, 2, 3, 3, 2)
						.values(13, date, 1, 3, 3, 2)
						.values(14, date, 1, 2, 3, 1)
						.values(15, dateMoinsUn, 1, 2, 3, 1).build());

		lauchOperation(op);

		mockMvc.perform(
				get("/handlerStatses/search/findStatsByProjectIdAndStatusIdNotInWithSopraHandler?project=1&handlerSopra=1&status=2,4&projection=handlerStatsSummary", "bugSummary"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$._embedded.handlerStatses[0].projectName").value("target project"))
				.andExpect(jsonPath("$._embedded.handlerStatses[0].statusName").value("target status"))
				.andExpect(jsonPath("$._embedded.handlerStatses[0].handlerName").value("Ministère de la Justice"))
				.andExpect(jsonPath("$._embedded.handlerStatses[0].sumIssues").value(4));
	}
}
