package com.pubmatic.test;
public class Counter {
    private int num;
    private static Counter counter = null;

    private Counter(int num) {
        this.num = num;
    }

    public static Counter getInstance(int num){
        if(counter == null){
            synchronized (Counter.class) {
                if(counter == null){
                    counter = new Counter(num);
                }
            }
        }
        return counter;
    }
}
/**
 Here Synchronization is necessary as when multiple threads will call getInstance method at that time
 it may be possible to to generate two different objects. But anyway Singleton pattern can be break using
 Reflection API also Using De serialization. If we want to avoid that we have to override readResolve method.
*/