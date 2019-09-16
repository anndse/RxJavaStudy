package top.plusy.ch02;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

import java.util.concurrent.TimeUnit;

public class ColdObservable {

    private Consumer<Long> subscribe1
            = aLong -> System.out.println("subscribe1: " + aLong);

    private Consumer<Long> subscribe2
            = aLong -> System.out.println("\tsubscribe2: " + aLong);

    private Consumer<Long> subscribe3
            = aLong -> System.out.println("\tsubscribe3: " + aLong);

    private Observable<Long> observable
            = Observable.create((ObservableOnSubscribe<Long>) emitter -> Observable.interval(10,
            TimeUnit.MILLISECONDS, Schedulers.computation())
            .take(Integer.MAX_VALUE)
            .subscribe(emitter::onNext)).observeOn(Schedulers.newThread());

    public void ColdObservableUse(){

        observable.subscribe(subscribe1);
        observable.subscribe(subscribe2);

        try{
            Thread.sleep(100L);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void coldObservableToHotObservable(){
        observable.publish().connect();
        observable.subscribe(subscribe1);
        observable.subscribe(subscribe2);

        try{
            Thread.sleep(20L);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        observable.subscribe(subscribe3);

        try{
            Thread.sleep(100L);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void subjectUse(){
        PublishSubject<Long> subject = PublishSubject.create();
        observable.subscribe(subject);

        subject.subscribe(subscribe1);
        subject.subscribe(subscribe2);

        try{
            Thread.sleep(20L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        subject.subscribe(subscribe3);

        try{
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
