package top.plusy.test.ch04;

import top.plusy.ch04.simpleScheduler;

public class simpleSchedulerTest {
    public static void main(String []args) {
        simpleScheduler.aSimpleScheduler();
        simpleScheduler.aScheduler();
        try {
            Thread.currentThread().join(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
