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
package com.github.jrrdev.mantisbtsync.rest.repositories;

import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.github.jrrdev.mantisbtsync.rest.domain.Bug;
import com.github.jrrdev.mantisbtsync.rest.junit.AbstractRepositoryTest;
import com.ninja_squad.dbsetup.generator.ValueGenerators;
import com.ninja_squad.dbsetup.operation.Operation;

/**
 * JUnit test for BugRepository.
 *
 * @author jrrdev
 *
 */
public class BugRepositoryTest extends AbstractRepositoryTest {

	@Autowired
	private BugRepository repo;

	@Test
	public void testFindByProjectIdAndHandlerIdAndStatusIdNotIn() {

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

		final List<Long> statusFilter = new ArrayList<Long>();
		statusFilter.add(2L);

		final List<Bug> results = repo.findByProjectIdAndHandlerIdAndStatusIdNotIn(1L, 1L, statusFilter, null);
		assertEquals(1, results.size());
		assertEquals(1, results.get(0).getId());
	}

	@Test
	public void testFindByProjectIdAndTargetVersion() {

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

		final List<Bug> results = repo.findByProjectIdAndTargetVersion(1L, "1.0", null);
		assertEquals(1, results.size());
		assertEquals(1, results.get(0).getId());
	}

	@Test
	public void testFindByProjectIdAndTargetVersionAndStatusIdIn() {

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

		final List<Long> statusFilter = new ArrayList<Long>();
		statusFilter.add(1L);

		final List<Bug> results = repo.findByProjectIdAndTargetVersionAndStatusIdIn(1L, "1.0", statusFilter, null);
		assertEquals(1, results.size());
		assertEquals(1, results.get(0).getId());
	}

	@Test
	public void testFindByProjectIdAndStatusIdNotIn() {

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
				.values(3, 1, 1, 2, "should be in result 2", ValueGenerators.dateSequence().nextValue())
				.values(4, 1, 2, 1, "wrong status", ValueGenerators.dateSequence().nextValue())
				.build());

		lauchOperation(op);

		final List<Long> statusFilter = new ArrayList<Long>();
		statusFilter.add(2L);

		final Order order = new Order(Sort.Direction.ASC, "id");
		final Sort sort = new Sort(order);

		final List<Bug> results = repo.findByProjectIdAndStatusIdNotIn(1L, statusFilter, sort);
		assertEquals(2, results.size());
		assertEquals(1, results.get(0).getId());
		assertEquals(3, results.get(1).getId());
	}

	/**
	 * @return the repo
	 */
	public BugRepository getRepo() {
		return repo;
	}

	/**
	 * @param repo the repo to set
	 */
	public void setRepo(final BugRepository repo) {
		this.repo = repo;
	}
}
