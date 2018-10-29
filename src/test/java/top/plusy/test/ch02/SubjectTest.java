package top.plusy.test.ch02;

import top.plusy.ch02.SubjectStudy;

public class SubjectTest {
    public static void main(String[] args){
        SubjectStudy subjectStudy = new SubjectStudy();
        subjectStudy.AsyncSubjectUse();
        System.out.println();
        subjectStudy.BehaviorSubjectUse();
        System.out.println();
        subjectStudy.ReplaySubjectUse();
    }
}
