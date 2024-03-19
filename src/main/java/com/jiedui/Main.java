package com.jiedui;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Expression[] es=utils.getAllExpression(10,15);
        for(Expression e:es){
            System.out.println(e);
        }
    }
}