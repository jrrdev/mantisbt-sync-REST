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
package com.github.jrrdev.mantisbtsync.rest.domain.projections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.jrrdev.mantisbtsync.rest.domain.Bug;

/**
 * @author jrrdev
 *
 */
@Projection(name = "bugDetails", types = { Bug.class })
@JsonPropertyOrder({"id", "category", "summary", "handlerName", "handlerUsername", "statusName", "priorityName", "severityName"})
public interface BugDetails {

	public long getId();

	public String getSummary();

	public String getDescription();

	public String getStepsToReproduce();

    public String getAdditionalInformation();

	public String getCategory();

	@Value("#{target.handler.name}")
	public String getHandlerName();
	
	@Value("#{target.handler.name.replaceFirst('ex\\.(.)[^.]*\\.(.*)', '$1$2')}")
    public String getHandlerUsername();

	@Value("#{target.status.name}")
	public String getStatusName();

    @Value("#{target.priority.name}")
    public String getPriorityName();

    @Value("#{target.severity.name}")
    public String getSeverityName();
}
