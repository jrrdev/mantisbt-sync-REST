/**
 * 
 */
package com.github.jrrdev.mantisbtsync.rest.domain.projections;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.jrrdev.mantisbtsync.rest.domain.HandlerStats;

/**
 * Projection for handlerStats
 * @author slopezgarcia
 *
 */
@Projection(name = "handlerStatsSummary", types = { HandlerStats.class })
@JsonPropertyOrder({ "handlerName", "projectName", "statusName", "computeDate",
		"sumIssues" })
public interface HandlerStatsSummary {
	
	public String getHandlerName();
	
	public String getProjectName();
	
	public String getStatusName();
	
	public Date getComputeDate();
	
	public long getSumIssues();

}
