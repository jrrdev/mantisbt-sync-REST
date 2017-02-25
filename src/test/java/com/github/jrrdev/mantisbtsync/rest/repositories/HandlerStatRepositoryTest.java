/**
 * 
 */
package com.github.jrrdev.mantisbtsync.rest.repositories;

import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.jrrdev.mantisbtsync.rest.domain.HandlerStats;
import com.github.jrrdev.mantisbtsync.rest.junit.AbstractRepositoryTest;
import com.ninja_squad.dbsetup.operation.Operation;

/**
 * @author slopezgarcia
 *
 */
public class HandlerStatRepositoryTest extends AbstractRepositoryTest {
	@Autowired
	private HandlerStatsRepository repo;

	@Test
	public void testFindStatsByProjectIdAndStatusIdNotInAndHandlerIdInWithSopraHandler() {
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

		List<Long> statusList = new ArrayList<>();
		statusList.add(2L);
		statusList.add(4L);

		Long projectId = 1L;

		Long sopraHandler = 1L;

		// Test du filtre projet et statut avec sopraHandler = 1
		// Resultat attendu : 4 lignes
		final List<HandlerStats> stats = repo
				.findStatsByProjectIdAndStatusIdNotInWithSopraHandler(sopraHandler, projectId,
						statusList);
		assertNotNull(stats);
		assertEquals(stats.size(), 4);

		String handlerNameSopra = "Sopra Steria";
		String handlerNameMJ = "Ministère de la Justice";
		String projectName = "target project";
		String targetStatusName = "target status";
		String thirdStatusName = "third status";

		// Ligne 1 : handlerName = Ministère de la Justice, projectName = target
		// project, statusName = target status, computeDate = date du jour,
		// sumIssues = 4

		HandlerStats stat = stats.get(0);
		assertEquals(stat.getHandlerName().trim(), handlerNameMJ);
		assertEquals(stat.getProjectName(), projectName);
		assertEquals(stat.getComputeDate().getTime() - date.getTime(), 0);
		assertEquals(stat.getStatusName(), targetStatusName);
		assertEquals(stat.getSumIssues(), 4);

		// Ligne 2 : handlerName = Ministère de la Justice, projectName = target
		// project, statusName = third status, computeDate = date du jour,
		// sumIssues = 3
		stat = stats.get(1);
		assertEquals(stat.getHandlerName().trim(), handlerNameMJ);
		assertEquals(stat.getProjectName(), projectName);
		assertEquals(stat.getComputeDate().getTime() - date.getTime(), 0);
		assertEquals(stat.getStatusName(), thirdStatusName);
		assertEquals(stat.getSumIssues(), 3);
		
		// Ligne 3 : handlerName = Sopra Steria, projectName = target project,
		// statusName = target status, computeDate = date du jour, sumIssues = 4
		stat = stats.get(2);
		assertEquals(stat.getHandlerName().trim(), handlerNameSopra);
		assertEquals(stat.getProjectName(), projectName);
		assertEquals(stat.getComputeDate().getTime() - date.getTime(), 0);
		assertEquals(stat.getStatusName(), targetStatusName);
		assertEquals(stat.getSumIssues(), 4);
		
		// Ligne 4 : handlerName = Sopra Steria, projectName = target project,
		// statusName = third status, computeDate = date du jour, sumIssues = 2
		stat = stats.get(3);
		assertEquals(stat.getHandlerName().trim(), handlerNameSopra);
		assertEquals(stat.getProjectName(), projectName);
		assertEquals(stat.getComputeDate().getTime() - date.getTime(), 0);
		assertEquals(stat.getStatusName(), thirdStatusName);
		assertEquals(stat.getSumIssues(), 2);

	}
}
