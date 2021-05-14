import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;



class AddingStack {
    ArrayList<Integer> ls = new ArrayList<Integer>();
    long size_total = 0;
    long size_current = 0;
    long total = 0;
    ArrayList<Long> increment = new ArrayList<Long>();


    public void push(int v) {        
        if (size_current >= size_total) {
            ls.add(v);
            size_total++;
            size_current++;
            total += v;
            increment.add((long)0);
        }
        else {
            ls.set(((int)size_current) , v);
            size_current ++;
            total += v;
            increment.set(((int)size_current-1), (long)0);
        }
    }

    public void pop() {
        size_current --;
        total -= ls.get((int)size_current);
        if (increment.get((int)size_current) != 0) {
            total -= increment.get((int)size_current);
            if (size_current != 0) {
                if (increment.get(((int)size_current)-1) != 0) {
                    increment.set(((int)size_current) -1, increment.get((int)size_current) + increment.get(((int)size_current)- 1));
                }
                else {
                    increment.set(((int)size_current) -1, increment.get((int)size_current));
                }
            }
        }

    }
    
    public void inc(int i, int v) {
        total += ((long)v* (long)i);
        if (increment.get(i - 1) != 0) {
            increment.set(i-1, v + increment.get(i - 1));
        }
        else {
           increment.set(i-1, (long) v); 
        }
    }

    public boolean empty() {
        if (size_current == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public long peek() {
        if (increment.get((int)size_current-1) != 0) {
            return ls.get(((int) size_current)-1) + increment.get((int)size_current-1);
        }
        return ls.get(((int) size_current)-1);
    }

    public long sum() {
        return total;
    }
}