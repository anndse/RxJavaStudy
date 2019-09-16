package top.plusy.ch04;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public class simpleScheduler {
    public static void aSimpleScheduler() {
        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            System.out.println("A:"+Thread.currentThread().getName());
            emitter.onNext("Hello");
            emitter.onNext("World");
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(s -> System.out.println(s + " ->B:" + Thread.currentThread().getName()));
    }

    public static void aScheduler() {
        Observable.just("Hello", "World")
                .subscribeOn(Schedulers.single())
                .observeOn(Schedulers.newThread())
                .map(s -> {
                    System.out.println(s + "-> 1:"+Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .observeOn(Schedulers.trampoline())
                .subscribe(s -> System.out.println(s + "-> 2:"+Thread.currentThread().getName()));
    }
}
