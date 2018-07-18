package com.minyazi.j2ee.test;

import java.io.IOException;

public class TestCpu1 {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            private int count;
            
            @Override
            public void run() {
                int busyTime = 10;
                int idleTime = busyTime;
                long startTime = 0;
                while (true) {
                    count++;
                    startTime = System.currentTimeMillis();
                    System.out.println(Thread.currentThread().getName() + "，" + count + "，" + System.currentTimeMillis() + "，" + startTime + "，" + (System.currentTimeMillis() - startTime));
                    
                    // busy loop
                    while ((System.currentTimeMillis() - startTime) <= busyTime) {
//                        System.out.println(Thread.currentThread().getName()); // 加上该条语句后CPU负载会降下来
                        ;
                    }
                    
                    // idle loop
                    try {
                        Thread.sleep(idleTime);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }
            }
        };
        
        for (int i = 0; i < 15; i++) {
            Thread thread = new Thread(runnable, "Thread-" + i);
            thread.start();
        }
        
        // 启动Windows任务管理器
        try {
            Runtime.getRuntime().exec("taskmgr");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
