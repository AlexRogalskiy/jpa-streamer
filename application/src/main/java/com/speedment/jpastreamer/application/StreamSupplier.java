/*
 * JPAstreamer - Express JPA queries with Java Streams
 * Copyright (c) 2020-2022, Speedment, Inc. All Rights Reserved.
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * See: https://github.com/speedment/jpa-streamer/blob/master/LICENSE
 */
package com.speedment.jpastreamer.application;

import com.speedment.jpastreamer.streamconfiguration.StreamConfiguration;

import java.util.stream.Stream;

/**
 * A Stream Supplier is responsible for creating Streams from a data source
 * An entity source can be RDBMSes, files or other data sources.
 *
 * A Stream Supplier must be thread safe and be able to handle several reading and
 * writing threads at the same time.
 *
 * @author Per Minborg, Julia Gustafsson 
 * @since 3.0.1
 */
public interface StreamSupplier<T> extends AutoCloseable {

    /**
     * Creates and returns a new {@link Stream} over all entities in the
     * underlying data source (e.g database) according to the {@code streamConfiguration} associated with this Streamer.
     * <p>
     * The order in which elements are returned when the stream is eventually
     * consumed <em>is unspecified</em>. The order may even change from one
     * invocation to another. Thus, it is an error to assume any particular
     * element order even though is might appear, for some stream sources, that
     * there is a de-facto order.
     * <p>
     * If a deterministic order is required, then make sure to invoke the
     * {@link Stream#sorted(java.util.Comparator)} method on the {@link Stream}
     * returned.
     * <p>
     * Mutable elements are not reused within the stream. More formally, there
     * are no pair of mutable stream elements <code>e1</code> and
     * <code>e2</code> such that <code>e1 == e2</code>.
     * <p>
     * The Stream will never contain <code>null</code> elements.
     * <p>
     * This is <em>an inexpensive O(1) operation</em> that will complete in
     * constant time regardless of the number of entities in the underlying
     * database.
     * <p>
     * The returned stream is aware of its own pipeline and will optionally
     * <em>optimize its own pipeline</em> whenever it encounters a <em>Terminal
     * Operation</em> so that it will only iterate over a minimum set of
     * matching entities.
     * <p>
     * When a Terminal Operation is eventually called on the {@link Stream},
     * that execution time of the Terminal Operation will depend on the
     * optimized pipeline and the entities in the underlying database.
     * <p>
     * The Stream will be automatically
     * {@link Stream#onClose(java.lang.Runnable) closed} after the Terminal
     * Operation is completed or if an Exception is thrown during the Terminal
     * Operation.
     * <p>
     * 
     * Some of the <em>Terminal Operations</em> are:
     * <ul>
     * <li>{@link Stream#forEach(java.util.function.Consumer) forEach(Consumer)}
     * <li>{@link Stream#forEachOrdered(java.util.function.Consumer) forEachOrdered(Consumer)}
     * <li>{@link Stream#toArray() toArray()}
     * <li>{@link Stream#toArray(java.util.function.IntFunction) toArray(IntFunction)}
     * <li>{@link Stream#reduce(java.util.function.BinaryOperator) reduce(BinaryOperation}
     * <li>{@link Stream#reduce(java.lang.Object, java.util.function.BinaryOperator) reduce(Object, BinaryOperator)}
     * <li>{@link Stream#reduce(java.lang.Object, java.util.function.BiFunction, java.util.function.BinaryOperator) reduce(Object, BiFunction, BinaryOperator)}
     * <li>{@link Stream#collect(java.util.stream.Collector) collect(Collector)}
     * <li>{@link Stream#collect(java.util.function.Supplier, java.util.function.BiConsumer, java.util.function.BiConsumer) collect(Supplier, BiConsumer, BiConsumer)}
     * <li>{@link Stream#min(java.util.Comparator) min(Comparator)}
     * <li>{@link Stream#max(java.util.Comparator) min(Comparator)}
     * <li>{@link Stream#count() count()}
     * <li>{@link Stream#anyMatch(java.util.function.Predicate) anyMatch(Predicate)}
     * <li>{@link Stream#noneMatch(java.util.function.Predicate) noneMatch(Predicate)}
     * <li>{@link Stream#findFirst() findFirst()}
     * <li>{@link Stream#findAny() findAny()}
     * <li>{@link Stream#iterator() iterator()}
     * </ul>
     * <p>
     * Any Terminating Operation may throw an Exception if the
     * underlying database throws an Exception (e.g. an SqlException)
     * <p>
     * Because the Stream may short-circuit operations in the Stream pipeline,
     * methods having side-effects (like
     * {@link Stream#peek(java.util.function.Consumer) peek(Consumer)} will
     * potentially be affected by the optimization.
     * <p>
     * Here are some examples of how the stream optimization might work:
     * <ul>
     * <li>
     * <pre>{@code stream(Film.class)
     *   .filter(Film$.name.equal("Casablanca"))
     *   .collect(toList());}</pre>
     * <pre>{@code -> select * from film where name='Casablanca'}</pre>
     * </li>
     * <li>
     * <pre>{@code stream.count();}</pre>
     * <pre>{@code -> select count(*) from film}</pre>
     * </li>
     * <li>
     * <pre>{@code stream(Film.class)
     *   .filter(Film$.name.startsWith("A"))
     *   .count();}</pre>
     * <pre>{@code -> select count(*) from hares where
     *   name LIKE 'A%'}</pre>
     * <p>
     * </li>
     * <li>
     * <pre>{@code stream.stream(Film.class)
     *   .filter(Film$.rating.equal("G")
     *   .filter(Film$.length.greaterThan(100)
     *   .count();}</pre>
     * <pre>{@code -> select count(*) from hares where
     *          rating ='G'
     *        and
     *          length > 100}</pre>
     * </li>
     * </ul>
     *
     * @return a new stream over all entities in this table in unspecified order
     *
     * @throws RuntimeException if an error occurs during a Terminal Operation
     * (e.g. an SqlException is thrown by the underlying database)
     *
     * @see java.util.stream
     * @see Stream
     */
    Stream<T> stream();

    /**
     * Closes this Stream Supplier and releases any resources potentially held, such as the underlying Entity Manager. 
     */
    void close();

    /**
     * Returns the {@link StreamConfiguration} that describes the stream source of the Streams generated by this Supplier.
     *
     * @return the configuration of the Streams generated by this Supplier 
     */
    StreamConfiguration<T> configuration(); 
    
}
