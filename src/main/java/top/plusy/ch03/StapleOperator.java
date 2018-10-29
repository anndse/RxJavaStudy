package top.plusy.ch03;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import java.util.EnumMap;

public class StapleOperator {
    public void createOperator(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                try {
                    if(!emitter.isDisposed()){
                        for(int i = 0; i < 10; i++){
                            emitter.onNext(i);
                        }
                        emitter.onComplete();
                    }
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("Next: " + integer);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println(throwable.getMessage());
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("Sequence complete");
            }
        });
    }

    public void justOperator(){
        Observable.just(1, 2, 3, 4,5, 6, 7, 8, 9, 10)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("Next: " + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println(throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("Sequence complete");
                    }
                });
    }
}
