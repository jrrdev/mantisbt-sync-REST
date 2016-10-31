/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 J�rard Devarulrajah
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
package com.github.jrrdev.mantisbtsync.rest.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.github.jrrdev.mantisbtsync.rest.domain.Bug;
import com.github.jrrdev.mantisbtsync.rest.repositories.commons.ReadOnlyPagingAndSortingRepository;

/**
 * Repository for domain object stored into mantis_bug_table.
 *
 * @author jrrdev
 *
 */
@RepositoryRestResource
public interface BugRepository extends ReadOnlyPagingAndSortingRepository<Bug, Long> {

	List<Bug> findByProjectIdAndHandlerIdAndStatusIdNotIn(@Param("project") Long project,
			@Param("handler") Long handler, @Param("status") List<Long> status, Sort sort);

	List<Bug> findByProjectIdAndTargetVersion(@Param("project") Long project,
			@Param("version") String version, Sort sort);

	List<Bug> findByProjectIdAndTargetVersionAndStatusIdIn(@Param("project") Long project,
			@Param("version") String version, @Param("status") List<Long> status, Sort sort);

}
