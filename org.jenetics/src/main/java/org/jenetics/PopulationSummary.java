/*
 * Java Genetic Algorithm Library (@__identifier__@).
 * Copyright (c) @__year__@ Franz Wilhelmstötter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Author:
 *    Franz Wilhelmstötter (franz.wilhelmstoetter@gmx.at)
 */
package org.jenetics;

import java.util.Objects;

import org.jenetics.stat.IntSummary;

/**
 * Contains statistical values about a given population.
 *
 * @author <a href="mailto:franz.wilhelmstoetter@gmx.at">Franz Wilhelmstötter</a>
 * @since 3.0
 * @version 3.0 &mdash; <em>$Date: 2014-07-10 $</em>
 */
public final class PopulationSummary<
	G extends Gene<?, G>,
	C extends Comparable<? super C>
>
{
	private final int _count;
	private final Phenotype<G, C> _best;
	private final Phenotype<G, C> _worst;
	private final IntSummary _ageSummary;

	/**
	 * Create a new population summary object.
	 *
	 * @param count the number of phenotypes this summary was build from
	 * @param best the best phenotype
	 * @param worst the worst phenotype
	 * @param ageSummary the summary of the phenotype ages
	 * @param <G> the gene type
	 * @param <C> the fitness value type
	 * @throws java.lang.NullPointerException if one of the parameters is
	 *         {@code null}.
	 */
	private PopulationSummary(
		final int count,
		final Phenotype<G, C> best,
		final Phenotype<G, C> worst,
		final IntSummary ageSummary
	) {
		_count = count;
		_best = Objects.requireNonNull(best);
		_worst = Objects.requireNonNull(worst);
		_ageSummary = Objects.requireNonNull(ageSummary);
	}

	/**
	 * The phenotype count this summary was build from.
	 *
	 * @return the number of phenotypes this summary was build from
	 */
	public int getCount() {
		return _count;
	}

	/**
	 * The best phenotype.
	 *
	 * @return the best phenotype
	 */
	public Phenotype<G, C> getBest() {
		return _best;
	}

	/**
	 * The worst phenotype.
	 *
	 * @return the worst phenotype
	 */
	public Phenotype<G, C> getWorst() {
		return _worst;
	}

	/**
	 * The summary of the phenotype ages.
	 *
	 * @return the summary of the phenotype ages
	 */
	public IntSummary getAgeSummary() {
		return _ageSummary;
	}

	/**
	 * Create a new population summary object.
	 *
	 * @param count the number of phenotypes this summary was build from
	 * @param best the best phenotype
	 * @param worst the worst phenotype
	 * @param ageSummary the summary of the phenotype ages
	 * @param <G> the gene type
	 * @param <C> the fitness value type
	 * @return a new population summary object
	 * @throws java.lang.NullPointerException if one of the parameters is
	 *         {@code null}.
	 */
	public static <G extends Gene<?, G>, C extends Comparable<? super C>>
	PopulationSummary<G, C> of(
		final int count,
		final Phenotype<G, C> best,
		final Phenotype<G, C> worst,
		final IntSummary ageSummary
	) {
		return new PopulationSummary<>(
			count,
			best,
			worst,
			ageSummary
		);
	}

	/**
	 * Return a new population summary object.
	 *
	 * @param statistics the creating (mutable) statistics class
	 * @param <G> the gene type
	 * @param <C> the fitness value type
	 * @return a new population summary object
	 * @throws java.lang.NullPointerException if one of the parameters is
	 *         {@code null}.
	 */
	public static <G extends Gene<?, G>, C extends Comparable<? super C>>
	PopulationSummary<G, C> of(final PopulationSummaryStatistics<G, C> statistics) {
		return of(
			(int)statistics.getAgeSummary().getCount(),
			statistics.getBest(),
			statistics.getWorst(),
			IntSummary.of(statistics.getAgeSummary())
		);
	}

}
