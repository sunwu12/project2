package com.jiedui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        System.out.println("success");
        Expression e1=Expression.splicing(new Expression("5"),new Expression("3"),'+');
        Expression e2=Expression.splicing(new Expression("3"),new Expression("5"),'+');
        System.out.println(e1.equals(e2));
        List<Expression> list=new ArrayList<>();
        list.add(e1);
        System.out.println(list.contains(e1));
    }
}