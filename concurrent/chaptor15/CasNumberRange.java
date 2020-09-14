package com.project.jvm.concurrent.chaptor15;

import java.util.concurrent.atomic.AtomicReference;

public class CasNumberRange {

    final static class IntPair {
        final int lower;
        final int upper;

        public IntPair(int lower, int upper) {
            this.lower = lower;
            this.upper = upper;
        }
    }

    private AtomicReference<IntPair> value =
            new AtomicReference<>(new IntPair(0,0));

    public int getLower() {
        return value.get().lower;
    }

    public int getUpper() {
        return value.get().upper;
    }

    public void setLower(int lower) {
        while (true) {
            IntPair oldV = value.get();
            if (oldV.upper<lower) {
                throw new IllegalArgumentException("Can't set lower to"+lower+">"+oldV.upper);
            }
            IntPair newV = new IntPair(lower,oldV.upper);
            if (value.compareAndSet(oldV,newV)) {
                return;
            }
        }
    }

    public void setUpper(int upper) {
        while (true) {
            IntPair oldV = value.get();
            if (oldV.lower>upper) {
                throw new IllegalArgumentException("Can't set lower to"+upper+"<"+oldV.lower);
            }
            IntPair newV = new IntPair(upper,oldV.lower);
            if (value.compareAndSet(oldV,newV)) {
                return;
            }
        }
    }
}
