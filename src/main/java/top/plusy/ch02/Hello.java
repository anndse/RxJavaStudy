package top.plusy.ch02;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Hello {
    public void sayHello(){
        Observable.just("Hello World").subscribe(System.out::println);
    }

    public void sayHelloV2(){
        Observable.just("Hello World").subscribe(System.out::println,
                throwable -> System.out.println(throwable.getMessage()),
                () -> System.out.println("onComplete()"));
    }

    public void sayHelloV3(){
        Observable.just("Hello World").subscribe(System.out::println,
                throwable -> System.out.println(throwable.getMessage()),
                () -> System.out.println("OnComplete()"),
                disposable -> System.out.println("Subscribe"));
    }

    public void sayHelloV4(){
        Observable.just("Hello World").subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe()");
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete()");
            }
        });
    }
    public void sayHelloWithDo(){
        Observable.just("Hello")
                .doOnNext(s -> System.out.println("doOnNext: " + s))
                .doAfterNext(s -> System.out.println("doAfterNext:"+s))
                .doAfterTerminate(() -> System.out.println("doAfterTerminate"))
                .doOnComplete(() -> System.out.println("doOnComplete:"))
                .doFinally(() -> System.out.println("doFinally:"))
                .doOnDispose(() -> System.out.println("doOnDispose"))
                .doOnError(throwable -> System.out.println(throwable.getMessage()))
                .doOnEach(stringNotification -> System.out.println("doOnEach:" + (stringNotification.isOnNext() ?
                        "onNext" : stringNotification.isOnComplete() ?
                        "onComplete" : "onError")))
                .doOnSubscribe(disposable -> System.out.println("doOnSubscribe"))
                .doOnLifecycle(disposable -> System.out.println("doOnLifecycle: " + disposable.isDisposed()), () -> System.out.println("doOnLifecycle: run"))
                .doOnTerminate(() -> System.out.println("doOnTerminate"))
                .subscribe(s -> System.out.println("RCV: "+s));
    }
}
