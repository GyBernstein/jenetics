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
 *    Franz Wilhelmstötter (franz.wilhelmstoetter@gmail.com)
 */
package io.jenetics.internal.util;

import static java.lang.Integer.rotateLeft;

import java.util.Objects;

/**
 * @author <a href="mailto:franz.wilhelmstoetter@gmail.com">Franz Wilhelmstötter</a>
 * @version !__version__!
 * @since !__version__!
 */
public final class Murmur3 {

	private Murmur3() {
	}

	public static int hash(final Object value, final int seed) {
		return mix(seed, value != null ? value.hashCode() : 0xdeadbeef);
	}

	public static int hash(final int value, final int seed) {
		return mix(seed, value);
	}

	public static int hash(final long value, final int seed) {
		return mix(seed, Long.hashCode(value));
	}

	public static int hash(final float value, final int seed) {
		return mix(seed, Float.hashCode(value));
	}

	public static int hash(final double value, final int seed) {
		return mix(seed, Double.hashCode(value));
	}

	static int mix(final int hash, final int data) {
		int h = mixLast(hash, data);
		h = rotateLeft(h, 13);
		return h * 5 + 0xe6546b64;
	}

	static int mixLast(final int hash, final int data) {
		int k = data;
		k *= 0xcc9e2d51;
		k = rotateLeft(k, 15);
		k *= 0xcc9e2d51;
		return hash^k;
	}

	public static int finalize(final int hash, final int length) {
		return avalanche(hash^length);
	}

	static int avalanche(final int hash) {
		int h = hash;
		h ^= h >>> 16;
		h *= 0x85ebca6b;
		h ^= h >>> 13;
		h *= 0xc2b2ae35;
		h ^= h >>> 16;
		return h;
	}

}
