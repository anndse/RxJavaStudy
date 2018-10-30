package top.plusy.ch04;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class simpleScheduler {
    public static void aSimpleScheduler() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                System.out.println("A:"+Thread.currentThread().getName());
                emitter.onNext("Hello");
                emitter.onNext("World");
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s+"->B:"+Thread.currentThread().getName());
                    }
                });
    }

    public static void aScheduler() {
        Observable.just("Hello", "World")
                .subscribeOn(Schedulers.single())
                .observeOn(Schedulers.newThread())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception{
                        System.out.println(s + "-> 1:"+Thread.currentThread().getName());
                        return s.toUpperCase();
                    }
                })
                .observeOn(Schedulers.trampoline())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s + "-> 2:"+Thread.currentThread().getName());
                    }
                });
    }
}
