package com.jiedui;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<Expression> es=utils.getAllExpression(15,15);
        //System.out.println(es);
        String str1="(10-(2+3))×2";
        String str2="2×(10-(3+2))";

        List<String> Alist =utils.getInfixExpression(str1);
        List<String> AList =utils.getPostfixExpression(Alist);
        List<String> Blist =utils.getInfixExpression(str2);
        List<String> BList =utils.getPostfixExpression(Blist);
        System.out.println(AList);
        System.out.println(BList);
        System.out.println(utils.checkDuplicate(AList,BList));

        System.out.println(utils.cal(AList));
        System.out.println(utils.cal(BList));

    }
}