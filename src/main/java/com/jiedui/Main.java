package com.jiedui;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<Expression> es=utils.getAllExpression(15,15);
        System.out.println(es);
        Expression expression=new Expression("15");
        System.out.println(expression);
        TxtHandle.txtRecord(es,null,null);
    }
}