/**
 * 
 */
package com.github.jrrdev.mantisbtsync.rest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.github.jrrdev.mantisbtsync.rest.domain.HandlerStats;
import com.github.jrrdev.mantisbtsync.rest.repositories.commons.ReadOnlyPagingAndSortingRepository;

/**
 * Repository for domain object stored into handlers_stats.
 * 
 * @author slopezgarcia
 *
 */
@RepositoryRestResource
public interface HandlerStatsRepository extends
		ReadOnlyPagingAndSortingRepository<HandlerStats, Long> {

	@Query(value="SELECT new com.github.jrrdev.mantisbtsync.rest.domain.HandlerStats(CASE WHEN hs.handler.id = :handlerSopra THEN 'Sopra Steria' ELSE 'Ministère de la Justice' END, hs.project.name, hs.status.name, hs.computeDate, SUM(hs.nbIssues)) "
			+ "FROM HandlerStats hs WHERE hs.project.id IN :project " 
			+ "AND hs.status.id NOT IN :status "
			+ "AND hs.computeDate = (SELECT MAX(hs2.computeDate) FROM HandlerStats hs2) "
			+ "GROUP  BY CASE WHEN hs.handler.id = :handlerSopra THEN 'Sopra Steria' ELSE 'Ministère de la Justice' END, hs.project.name, hs.status.name, hs.computeDate "
			+ "ORDER BY CASE WHEN hs.handler.id = :handlerSopra THEN 'Sopra Steria' ELSE 'Ministère de la Justice' END, hs.status.name")
	List<HandlerStats> findStatsByProjectIdAndStatusIdNotInWithSopraHandler(@Param("handlerSopra") Long handlerSopra, @Param("project") Long project, @Param("status") List<Long> status);
}
