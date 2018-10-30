package top.plusy.test.ch03;

import top.plusy.ch03.StapleOperator;

public class StapleOperatorTest {
    public static void main(String[] args){
        StapleOperator stapleOperator = new StapleOperator();
        stapleOperator.createOperator();

        System.out.println();
        stapleOperator.justOperator();

        System.out.println();
        stapleOperator.fromOperatorV1();

        System.out.println();
        stapleOperator.fromOperatorV2();

        System.out.println();
        stapleOperator.fromOperatorV3();

        System.out.println();
        stapleOperator.fromOperatorV4();
    }
}
