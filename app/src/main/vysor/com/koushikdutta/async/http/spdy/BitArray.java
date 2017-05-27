// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.spdy;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Locale;

interface BitArray
{
    void clear();
    
    boolean get(final int p0);
    
    void set(final int p0);
    
    void shiftLeft(final int p0);
    
    void toggle(final int p0);
    
    public static final class FixedCapacity implements BitArray
    {
        long data;
        
        public FixedCapacity() {
            this.data = 0L;
        }
        
        private static int checkInput(final int n) {
            if (n < 0 || n > 63) {
                throw new IllegalArgumentException(String.format(Locale.ENGLISH, "input must be between 0 and 63: %s", n));
            }
            return n;
        }
        
        @Override
        public void clear() {
            this.data = 0L;
        }
        
        @Override
        public boolean get(final int n) {
            return (0x1L & this.data >> checkInput(n)) == 0x1L;
        }
        
        @Override
        public void set(final int n) {
            this.data |= 1L << checkInput(n);
        }
        
        @Override
        public void shiftLeft(final int n) {
            this.data <<= checkInput(n);
        }
        
        @Override
        public String toString() {
            return Long.toBinaryString(this.data);
        }
        
        public BitArray toVariableCapacity() {
            return new VariableCapacity(this);
        }
        
        @Override
        public void toggle(final int n) {
            this.data ^= 1L << checkInput(n);
        }
    }
    
    public static final class VariableCapacity implements BitArray
    {
        long[] data;
        private int start;
        
        public VariableCapacity() {
            this.data = new long[1];
        }
        
        private VariableCapacity(final FixedCapacity fixedCapacity) {
            this.data = new long[] { fixedCapacity.data, 0L };
        }
        
        private static int checkInput(final int n) {
            if (n < 0) {
                throw new IllegalArgumentException(String.format(Locale.ENGLISH, "input must be a positive number: %s", n));
            }
            return n;
        }
        
        private void growToSize(final int n) {
            final long[] data = new long[n];
            if (this.data != null) {
                System.arraycopy(this.data, 0, data, 0, this.data.length);
            }
            this.data = data;
        }
        
        private int offsetOf(final int n) {
            final int n2 = (n + this.start) / 64;
            if (n2 > -1 + this.data.length) {
                this.growToSize(n2 + 1);
            }
            return n2;
        }
        
        private int shiftOf(final int n) {
            return (n + this.start) % 64;
        }
        
        @Override
        public void clear() {
            Arrays.fill(this.data, 0L);
        }
        
        @Override
        public boolean get(final int n) {
            checkInput(n);
            return (this.data[this.offsetOf(n)] & 1L << this.shiftOf(n)) != 0x0L;
        }
        
        @Override
        public void set(final int n) {
            checkInput(n);
            final int offset = this.offsetOf(n);
            final long[] data = this.data;
            data[offset] |= 1L << this.shiftOf(n);
        }
        
        @Override
        public void shiftLeft(final int n) {
            this.start -= checkInput(n);
            if (this.start < 0) {
                final int n2 = 1 + this.start / -64;
                final long[] data = new long[n2 + this.data.length];
                System.arraycopy(this.data, 0, data, n2, this.data.length);
                this.data = data;
                this.start = 64 + this.start % 64;
            }
        }
        
        List<Integer> toIntegerList() {
            final ArrayList<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < 64 * this.data.length - this.start; ++i) {
                if (this.get(i)) {
                    list.add(i);
                }
            }
            return list;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            final List<Integer> integerList = this.toIntegerList();
            for (int i = 0; i < integerList.size(); ++i) {
                if (i > 0) {
                    sb.append(',');
                }
                sb.append(integerList.get(i));
            }
            return sb.append('}').toString();
        }
        
        @Override
        public void toggle(final int n) {
            checkInput(n);
            final int offset = this.offsetOf(n);
            final long[] data = this.data;
            data[offset] ^= 1L << this.shiftOf(n);
        }
    }
}
