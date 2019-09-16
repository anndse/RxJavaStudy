package top.plusy.ch02;

import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.ReplaySubject;

public class SubjectStudy {
    public void AsyncSubjectUse() {
        AsyncSubject<String> subject = AsyncSubject.create();
        subject.onNext("AsyncSubject1");
        subject.onNext("AsyncSubject2");
        //subject.onComplete();

        subject.subscribe(s -> System.out.println("AsyncSubject:" + s),
                throwable -> System.out.println("AsyncSubject onError"),
                () -> System.out.println("AsyncSubjeact:complete"));

        subject.onNext("AsyncSubject3");
        subject.onNext("AsyncSubject4");
        subject.onComplete();
    }

    public void BehaviorSubjectUse(){

        BehaviorSubject<String> subject = BehaviorSubject.createDefault("behaviorSubject1");
        subject.onNext("behaviorSubject2");

        subject.subscribe(s -> System.out.println("behaviorSubject: " + s),
                throwable -> System.out.println(throwable.getMessage()),
                () -> System.out.println("behaviorSubject:complete"));

        subject.onNext("behaviorSubject2");
        subject.onNext("behaviorSubject3");
        subject.onNext("behaviorSubject4");
    }

    public void ReplaySubjectUse(){
        ReplaySubject<String> subject = ReplaySubject.create();

        subject.onNext("ReplaySubject1");
        subject.onNext("ReplaySubject2");

        subject.subscribe( s -> System.out.println("ReplaySubject: " + s),
                throwable -> System.out.println(throwable.getMessage()),
                () -> System.out.println("ReplaySubject: complete"));

        subject.onNext("ReplaySubject3");
        subject.onNext("ReplaySubject4");
    }
}
