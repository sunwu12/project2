package com.jiedui;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Expression> es=utils.getAllExpression(15,15);
        System.out.println(es);
        String str1="(10-(8+5))×2";
        String str2="2×(10-(5+8))";
        try{
            boolean result=utils.checkDuplicate(str1,str2);
            if(result) System.out.println("重复");
            else System.out.println("不重复");
        }catch (Exception e){
            System.out.println("表达式错误");
        }

        System.out.println(utils.cal(str1));
        System.out.println(utils.cal(str2));

    }
}