/**
 * 
 */
package com.github.jrrdev.mantisbtsync.rest.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Domain object stored into handlers_stats
 * @author slopezgarcia
 *
 */
@Entity
@Table(name="handlers_stats")
public class HandlerStats {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	@JoinColumn(name="project_id")
	private Project project;

	@ManyToOne
	@JoinColumn(name="handler_id")
	private User handler;

	@ManyToOne
	@JoinColumn(name="status_id")
	private Status status;
	
	@Column(name="compute_date")
	private Date computeDate;
	
	@Column(name="nb_issues")
	private long nbIssues;
	
	private long sumIssues;
	
	private String handlerName;
	
	private String projectName;
	
	private String statusName;
	
	

	/**
	 * @param computeDate
	 * @param sumIssues
	 * @param handlerName
	 * @param projectName
	 * @param statusName
	 */
	public HandlerStats(String handlerName, String projectName, String statusName, Date computeDate, long sumIssues) {
		super();
		this.computeDate = computeDate;
		this.sumIssues = sumIssues;
		this.handlerName = handlerName;
		this.projectName = projectName;
		this.statusName = statusName;
	}

	/**
	 * @return the handlerName
	 */
	public String getHandlerName() {
		return handlerName;
	}

	/**
	 * @param handlerName the handlerName to set
	 */
	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * @return the handler
	 */
	public User getHandler() {
		return handler;
	}

	/**
	 * @param handler the handler to set
	 */
	public void setHandler(User handler) {
		this.handler = handler;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the computeDate
	 */
	public Date getComputeDate() {
		return computeDate;
	}

	/**
	 * @param computeDate the computeDate to set
	 */
	public void setComputeDate(Date computeDate) {
		this.computeDate = computeDate;
	}

	/**
	 * @return the nbIssues
	 */
	public long getNbIssues() {
		return nbIssues;
	}

	/**
	 * @param nbIssues the nbIssues to set
	 */
	public void setNbIssues(long nbIssues) {
		this.nbIssues = nbIssues;
	}

	/**
	 * @return the sumIssues
	 */
	public long getSumIssues() {
		return sumIssues;
	}

	/**
	 * @param sumIssues the sumIssues to set
	 */
	public void setSumIssues(long sumIssues) {
		this.sumIssues = sumIssues;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the statusName
	 */
	public String getStatusName() {
		return statusName;
	}

	/**
	 * @param statusName the statusName to set
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
}
