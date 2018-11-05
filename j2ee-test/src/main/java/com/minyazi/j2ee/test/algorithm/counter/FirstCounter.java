package com.minyazi.j2ee.test.algorithm.counter;

public class FirstCounter implements Counter {
    public FirstCounter() {}
    
    @Override
    public long count(int[] numbers) {
        long result = 0;
        for (int number : numbers) {
            if (number == 1) {
                result += 1;
            }
        }
        return result;
    }
}
