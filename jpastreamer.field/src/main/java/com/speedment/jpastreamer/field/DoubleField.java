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
package com.speedment.jpastreamer.field;

import com.speedment.jpastreamer.field.comparator.DoubleFieldComparator;
import com.speedment.jpastreamer.field.internal.DoubleFieldImpl;
import com.speedment.jpastreamer.field.method.DoubleGetter;
import com.speedment.jpastreamer.field.trait.HasComparableOperators;
import com.speedment.jpastreamer.field.trait.HasDoubleValue;
import com.speedment.runtime.compute.ToDouble;

/**
 * A field that represents a primitive {@code double} value.
 * <p>
 * Generated by com.speedment.sources.pattern.FieldPattern.
 * 
 * @param <ENTITY> entity type
 *
 * @author Emil Forslund
 * @since  3.0.0
 */
public interface DoubleField<ENTITY> extends Field<ENTITY>,
        HasDoubleValue<ENTITY>,
        HasComparableOperators<ENTITY, Double>,
        ToDouble<ENTITY>,
        DoubleFieldComparator<ENTITY> {

    /**
     * Creates a new {@link DoubleField} using the default implementation.
     * 
     * @param <ENTITY>   entity type
     * @param table      the table that the field belongs to
     * @param columnName the name of the database column the field represents
     * @param getter     method reference to getter in entity
     * @param unique     if column only contains unique values
     * @return           the created field
     */
    static <ENTITY> DoubleField<ENTITY> create(
            Class<ENTITY> table,
            String columnName,
            DoubleGetter<ENTITY> getter,
            boolean unique) {
        return new DoubleFieldImpl<>(
                table, columnName, getter, unique
        );
    }
    
    @Override
    default double applyAsDouble(ENTITY entity) {
        return getAsDouble(entity);
    }
    
    @Override
    DoubleFieldComparator<ENTITY> comparator();
    
    @Override
    default DoubleFieldComparator<ENTITY> reversed() {
        return comparator().reversed();
    }
    
    @Override
    default DoubleField<ENTITY> getField() {
        return this;
    }
}