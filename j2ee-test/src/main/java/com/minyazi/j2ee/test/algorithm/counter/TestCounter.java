package com.minyazi.j2ee.test.algorithm.counter;

public class TestCounter {
    public static void main(String[] args) {
        int size = (int) 1.5e8;
        int[] numbers = new int[size];
        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) {
                numbers[i] = 1;
            }
        }
        
        Counter firstCounter = new FirstCounter();
        long beginTime = System.currentTimeMillis();
        System.out.println(firstCounter.count(numbers));
        long endTime = System.currentTimeMillis();
        System.out.println("算法一耗时：" + (endTime - beginTime) + "毫秒");
        
        Counter secondCounter = new SecondCounter(2);
        beginTime = System.currentTimeMillis();
        System.out.println(secondCounter.count(numbers));
        endTime = System.currentTimeMillis();
        System.out.println("算法二耗时：" + (endTime - beginTime) + "毫秒");
        
        Counter thirdCounter = new ThirdCounter();
        beginTime = System.currentTimeMillis();
        System.out.println(thirdCounter.count(numbers));
        endTime = System.currentTimeMillis();
        System.out.println("算法三耗时：" + (endTime - beginTime) + "毫秒");        
    }
}
