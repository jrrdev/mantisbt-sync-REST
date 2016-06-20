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

import com.github.jrrdev.mantisbtsync.rest.beans.IssueLineBean;

/**
 * DAO service for issues related operations.
 *
 * @author jrrdev
 *
 */
public interface IssuesDao {

	/**
	 * Retrieves the issues by project id and with a status under the passed limit.
	 * The project id is optionnal, if null all issues are retrieved.
	 * The status limit is also optionnal.
	 *
	 * @param projectId
	 * 			the project id
	 * @param maxStatus
	 * 			Name of the limit status
	 * @return a list of issue
	 */
	List<IssueLineBean> getOpenIssuesByProjectId(Integer projectId, String maxStatus);

}
