package com.minyazi.j2ee.test.algorithm.counter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SecondCounter implements Counter {
    private int threadPoolSize;
    
    public SecondCounter(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }
    
    @Override
    public long count(int[] numbers) {
        long result = 0;
        ExecutorService threadPool = null;
        List<Future<Long>> results = new ArrayList<>();
        int part = numbers.length / threadPoolSize;
        try {
            threadPool = Executors.newFixedThreadPool(threadPoolSize);
            for (int i = 0; i < threadPoolSize; i++) {
                results.add(threadPool.submit(new CounterCallable(numbers, i * part, (i + 1) * part)));
            }
            for (Future<Long> j : results) {
                result += j.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            if (threadPool != null) {
                threadPool.shutdown();
            }
        }
        return result;
    }
    
    private class CounterCallable implements Callable<Long> {
        private int[] numbers;
        private int beginNumber;
        private int endNumber;
        
        public CounterCallable(int[] numbers, int beginNumber, int endNumber) {
            this.numbers = numbers;
            this.beginNumber = beginNumber;
            this.endNumber = endNumber;
        }
        
        @Override
        public Long call() throws Exception {
            long result = 0;
            for (int i = beginNumber; i < endNumber; i++) {
                if (numbers[i] == 1) {
                    result += 1;
                }
            }
            return result;
        }
    }
}
