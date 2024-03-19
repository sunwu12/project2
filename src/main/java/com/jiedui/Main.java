package com.jiedui;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Expression expression1= new Expression(3);
        Expression expression2= utils.getExpression(15);
        System.out.println(Expression.splicing(expression1, expression2,'+'));

    }
}