package top.plusy.ch16;

import java.util.concurrent.*;

public class FutureExample {
    public static void simpleExample() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(()-> {
            System.out.println("Running task");
            Thread.sleep(5000L);
            return "return task";
        });
        System.out.println("so something else");
        try {
            System.out.println(future.get(10000L, TimeUnit.SECONDS));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
