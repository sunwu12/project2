package com.jiedui;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<Expression> es=utils.getAllExpression(15,15);
        System.out.println(es);
        List<String> Alist =utils.getInfixExpression("");
        List<String> Blist =utils.getInfixExpression("(1+93)+1");

        List<String> AList =utils.getPostfixExpression(Alist);
        List<String> BList =utils.getPostfixExpression(Blist);


        String Ass=utils.cal(AList);
        String Bss=utils.cal(BList);

        System.out.println(Bss);

        String s1=utils.ConverPostfixExpressionToStrings(AList);
        String s2=utils.ConverPostfixExpressionToStrings(BList);

        utils.CheckDuplicates(s1,s2);



//        Expression expression=new Expression("15");
//        System.out.println(expression);
//        TxtHandle.txtRecord(es,null,null);

    }
}