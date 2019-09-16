package top.plusy.ch03;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class StapleOperator {
    public void createOperator(){
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            try {
                if(!emitter.isDisposed()) {
                    for(int i = 0; i < 10; i++){
                        emitter.onNext(i);
                    }
                    emitter.onComplete();
                }
            } catch (Exception e) {
                emitter.onError(e);
            }
        })
        .subscribe(aInt -> System.out.println("Next: " + aInt),
                throwable -> System.out.println(throwable.getMessage()),
                () -> System.out.println("Sequence complete"));
    }

    public void justOperator(){
        Observable.just(1, 2, 3, 4,5, 6, 7, 8, 9, 10)
                .subscribe(aInt -> System.out.println("Next: " + aInt),
                        throwable -> System.out.println(throwable.getMessage()),
                        () -> System.out.println("Sequence complete"));
    }

    //from array
    public void fromOperatorV1(){
        Observable.fromArray("Hello", "World").subscribe(System.out::println);
    }

    //from iterator
    public void fromOperatorV2() {
        List<Integer> items = new ArrayList<>();
        for(int i = 0; i < 10; i+=1) {
            items.add(i);
        }

        Observable.fromIterable(items).subscribe(aInt -> System.out.println("Nect: " + aInt),
                throwable -> System.out.println(throwable.getMessage()),
                () -> System.out.println("Sequence complete"));
    }

    //from future
    static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("do sth paste time...");
            Thread.sleep(5000L);
            return "OK";
        }
    }

    public void fromOperatorV3() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());

        Observable.fromFuture(future)
                .subscribe(System.out::println);
    }

    public void fromOperatorV4(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());

        Observable.fromFuture(future, 4, TimeUnit.SECONDS)
                .subscribe(System.out::println, throwable -> System.out.println(throwable.getMessage()));
    }
}
