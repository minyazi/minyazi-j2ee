package com.minyazi.j2ee.test.algorithm.counter;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ThirdCounter implements Counter {
    private int threadPoolSize;
    private int threshold; // 阈值
    
    public ThirdCounter(int threadPoolSize, int threshold) {
        this.threadPoolSize = threadPoolSize;
        this.threshold = threshold;
    }
    
    @Override
    public long count(int[] numbers) {
        long result = 0;
        ForkJoinPool threadPool = null;
        try {
            threadPool = new ForkJoinPool(threadPoolSize);
            result = threadPool.invoke(new CounterTask(numbers, 0, numbers.length));
        } finally {
            if (threadPool != null) {
                threadPool.shutdown();
            }
        }
        return result;
    }
    
    private class CounterTask extends RecursiveTask<Long> {
        private static final long serialVersionUID = 1L;
        private int[] numbers;
        private int beginNumber;
        private int endNumber;
        
        public CounterTask(int[] numbers, int beginNumber, int endNumber) {
            this.numbers = numbers;
            this.beginNumber = beginNumber;
            this.endNumber = endNumber;
        }
        
        @Override
        protected Long compute() {
            if (endNumber - beginNumber <= threshold) {
                long result = 0;
                for (int i = beginNumber; i < endNumber; i++) {
                    if (numbers[i] == 1) {
                        result += 1;
                    }
                }
                return result;
            } else {
                int middleNumber = (beginNumber + endNumber) / 2;
                CounterTask left = new CounterTask(numbers, beginNumber, middleNumber);
                left.fork();
                CounterTask right = new CounterTask(numbers, middleNumber, endNumber);
                right.fork();
                return left.join() + right.join();
            }
        }
    }
}
