package top.plusy.ch10;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class RxJavaConcurrency {
    public static void simpleExample() {
        List<Integer> result = new ArrayList<>();
        for(Integer i = 0; i < 100; i++) {
            result.add(i);
        }
        result.parallelStream().map(Object::toString).forEach(System.out::println);
    }

    public static void flatMapExample() {
        Observable.range(1, 100)
                .flatMap((Function<Integer, ObservableSource<String>>) integer -> Observable.just(integer)
                        .subscribeOn(Schedulers.computation())
                        .map(Object::toString))
                .subscribe(System.out::println);
    }

    public static void flatMapWithExecutorService() {
        int threadNum = Runtime.getRuntime().availableProcessors()+1;
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        final Scheduler scheduler = Schedulers.from(executorService);

        Observable.range(1, 100)
                .flatMap((Function<Integer, ObservableSource<String>>) integer -> Observable.just(integer)
                        .subscribeOn(scheduler)
                        .map(Object::toString))
                        .doFinally(executorService::shutdown)
                .subscribe(System.out::println);
    }

    public static void RoundRobinSimpleExample() {
        final AtomicInteger batch = new AtomicInteger(0);
        Observable.range(1, 100)
                .groupBy(integer -> batch.getAndIncrement() % 5)
                .flatMap((Function<GroupedObservable<Integer, Integer>, ObservableSource<String>>)
                        integerIntegerGroupedObservable -> integerIntegerGroupedObservable.observeOn(Schedulers.io())
                                .map(Object::toString))
                .subscribe(System.out::println);
    }

    public static void RoundRobinWithExecutorService() {
        final AtomicInteger batch = new AtomicInteger(0);
        int threadNum = 5;
        final ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        final Scheduler scheduler = Schedulers.from(executorService);

        Observable.range(1, 100)
                .groupBy(integer->batch.getAndIncrement() % threadNum)
                .flatMap((Function<GroupedObservable<Integer, Integer>, ObservableSource<String>>)
                    integerIntegerGroupedObservable -> integerIntegerGroupedObservable.observeOn(scheduler)
                            .map(Object::toString))
                .doFinally(executorService::shutdown)
                .subscribe(System.out::println);
    }

    public static void parallelFlowableExample() {
        ParallelFlowable<Integer> parallelFlowable = Flowable.range(1, 100).parallel();

        parallelFlowable.runOn(Schedulers.io())
                .map(Object::toString)
                .sequential()
                .subscribe(System.out::println);
    }
}
