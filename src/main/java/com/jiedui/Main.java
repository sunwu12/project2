package com.jiedui;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Expression> es=utils.getAllExpression(15,15);
        System.out.println(es);
        String str1="(10-(3+5))×2";
        String str2="2×(10-(5+3))";

            boolean result=utils.checkDuplicate(str1,str2);
            if(result) System.out.println("重复");
            else System.out.println("不重复");


        System.out.println(utils.cal(str1));
        System.out.println(utils.cal(str2));

    }
}