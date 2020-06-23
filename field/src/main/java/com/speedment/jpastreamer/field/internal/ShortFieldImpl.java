/*
 *
 * Copyright (c) 2006-2020, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.jpastreamer.field.internal;

import com.speedment.common.annotation.GeneratedCode;
import com.speedment.jpastreamer.field.ShortField;
import com.speedment.jpastreamer.field.comparator.NullOrder;
import com.speedment.jpastreamer.field.internal.method.GetShortImpl;
import com.speedment.jpastreamer.field.internal.predicate.shorts.*;
import com.speedment.jpastreamer.field.internal.util.CollectionUtil;
import com.speedment.jpastreamer.field.predicate.FieldPredicate;
import com.speedment.jpastreamer.field.comparator.ShortFieldComparator;
import com.speedment.jpastreamer.field.internal.comparator.ShortFieldComparatorImpl;
import com.speedment.jpastreamer.field.method.GetShort;
import com.speedment.jpastreamer.field.method.ShortGetter;
import com.speedment.jpastreamer.field.predicate.Inclusion;
import com.speedment.jpastreamer.field.predicate.SpeedmentPredicate;

import javax.persistence.AttributeConverter;
import java.util.Collection;

import static java.util.Objects.requireNonNull;

/**
 * Default implementation of the {@link ShortField}-interface.
 * 
 * Generated by com.speedment.sources.pattern.FieldImplPattern
 * 
 * @param <ENTITY> entity type
 * @param <D>      database type
 * 
 * @author Emil Forslund
 * @since  3.0.0
 */
@GeneratedCode(value = "Speedment")
public final class ShortFieldImpl<ENTITY, D> implements ShortField<ENTITY, D> {
    
    private final Class<ENTITY> table;
    private final String columnName;
    private final GetShort<ENTITY, D> getter;
    private final Class<? extends AttributeConverter<Short, ? super D>> attributeConverterClass;
    private final boolean unique;

    public ShortFieldImpl(
            Class<ENTITY> table,
            String columnName,
            ShortGetter<ENTITY> getter,
            Class<? extends AttributeConverter<Short, ? super D>>attributeConverterClass,
            boolean unique) {
        this.table = requireNonNull(table);
        this.columnName = requireNonNull(columnName);
        this.getter     = new GetShortImpl<>(this, getter);
        this.attributeConverterClass = attributeConverterClass;
        this.unique     = unique;
    }

    @Override
    public Class<ENTITY> table() {
        return table;
    }

    @Override
    public GetShort<ENTITY, D> getter() {
        return getter;
    }
    
    @Override
    public Class<? extends AttributeConverter<Short, ? super D>>attributeConverterClass() {
        return attributeConverterClass;
    }
    
    @Override
    public boolean isUnique() {
        return unique;
    }
    
    @Override
    public ShortFieldComparator<ENTITY, D> comparator() {
        return new ShortFieldComparatorImpl<>(this);
    }
    
    @Override
    public ShortFieldComparator<ENTITY, D> reversed() {
        return comparator().reversed();
    }
    
    @Override
    public ShortFieldComparator<ENTITY, D> comparatorNullFieldsFirst() {
        return comparator();
    }
    
    @Override
    public NullOrder getNullOrder() {
        return NullOrder.LAST;
    }
    
    @Override
    public boolean isReversed() {
        return false;
    }
    
    @Override
    public FieldPredicate<ENTITY> equal(Short value) {
        return new ShortEqualPredicate<>(this, value);
    }
    
    @Override
    public FieldPredicate<ENTITY> greaterThan(Short value) {
        return new ShortGreaterThanPredicate<>(this, value);
    }
    
    @Override
    public FieldPredicate<ENTITY> greaterOrEqual(Short value) {
        return new ShortGreaterOrEqualPredicate<>(this, value);
    }
    
    @Override
    public FieldPredicate<ENTITY> between(
            Short start,
            Short end,
            Inclusion inclusion) {
        return new ShortBetweenPredicate<>(this, start, end, inclusion);
    }
    
    @Override
    public FieldPredicate<ENTITY> in(Collection<Short> values) {
        return new ShortInPredicate<>(this, CollectionUtil.collectionToSet(values));
    }
    
    @Override
    public SpeedmentPredicate<ENTITY> notEqual(Short value) {
        return new ShortNotEqualPredicate<>(this, value);
    }
    
    @Override
    public SpeedmentPredicate<ENTITY> lessOrEqual(Short value) {
        return new ShortLessOrEqualPredicate<>(this, value);
    }
    
    @Override
    public SpeedmentPredicate<ENTITY> lessThan(Short value) {
        return new ShortLessThanPredicate<>(this, value);
    }
    
    @Override
    public SpeedmentPredicate<ENTITY> notBetween(
            Short start,
            Short end,
            Inclusion inclusion) {
        return new ShortNotBetweenPredicate<>(this, start, end, inclusion);
    }
    
    @Override
    public SpeedmentPredicate<ENTITY> notIn(Collection<Short> values) {
        return new ShortNotInPredicate<>(this, CollectionUtil.collectionToSet(values));
    }

    @Override
    public String columnName() {
        return columnName;
    }
}