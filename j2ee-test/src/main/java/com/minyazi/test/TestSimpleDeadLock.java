package com.minyazi.test;

public class TestSimpleDeadLock extends Thread {
    public static Object lock1 = new Object();
    public static Object lock2 = new Object();
    
    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();
    }
    
    private static class Thread1 extends Thread {
        public void run() {
            System.out.println("Thread 1: Waiting for lock 1...");
            synchronized (lock1) {
                System.out.println("Thread 1: Holding lock 1...");
                
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                System.out.println("Thread 1: Waiting for lock 2...");
                synchronized (lock2) {
                    System.out.println("Thread 1: Holding lock 1 & 2...");
                }
            }
        }
    }
    
    private static class Thread2 extends Thread {
        public void run() {
            System.out.println("Thread 2: Waiting for lock 2...");
            synchronized (lock2) {
                System.out.println("Thread 2: Holding lock 2...");
                
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                System.out.println("Thread 2: Waiting for lock 1...");
                synchronized (lock1) {
                    System.out.println("Thread 2: Holding lock 2 & 1...");
                }
            }
        }
    }
}
