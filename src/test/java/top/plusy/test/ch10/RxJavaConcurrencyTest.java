package top.plusy.test.ch10;

import top.plusy.ch10.RxJavaConcurrency;

public class RxJavaConcurrencyTest {
    public static void main(String []args) {
        RxJavaConcurrency.simpleExample();
        RxJavaConcurrency.flatMapExample();
        RxJavaConcurrency.flatMapWithExecutorService();
        RxJavaConcurrency.RoundRobinSimpleExample();
        RxJavaConcurrency.RoundRobinWithExecutorService();
        RxJavaConcurrency.parallelFlowableExample();
    }
}
