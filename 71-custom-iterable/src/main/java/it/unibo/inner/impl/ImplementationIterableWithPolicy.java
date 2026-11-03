package it.unibo.inner.impl;

import java.util.Iterator;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class ImplementationIterableWithPolicy<T> implements IterableWithPolicy<T>{
    
    private final T array[];
    
    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        // Empty (for now)
    }

    private class PolicyIterator implements Iterator<T> {

        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < array.length;
        }

        @Override
        public T next() {
            if (hasNext()) {
                T element = array[cursor];
                cursor++;
                return element;
            }
            return null;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new PolicyIterator();
    }

    public ImplementationIterableWithPolicy(T array[]) {
        this.array = array;
    }
}