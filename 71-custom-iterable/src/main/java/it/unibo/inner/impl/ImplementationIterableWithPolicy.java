package it.unibo.inner.impl;

import java.util.Iterator;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class ImplementationIterableWithPolicy<T> implements IterableWithPolicy<T>{
    
    private final T array[];
    private Predicate<T> filter;

    public ImplementationIterableWithPolicy(T array[]) {
        this(array, item -> true);
    }

    public ImplementationIterableWithPolicy(T array[], Predicate<T> filter) {
        this.array = array;
        this.filter = filter;
    }
    
    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }

    private class PolicyIterator implements Iterator<T> {

        private int cursor = -1;

        @Override
        public boolean hasNext() {
            while ((cursor + 1) < array.length && !filter.test(array[cursor + 1])) {
                cursor++;
            }
            return (cursor + 1) < array.length;
        }

        @Override
        public T next() {
            while (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            cursor++;
            return array[cursor];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new PolicyIterator();
    }
}