package com.jiedui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class utils {

    //生成一个表达式
    public static Expression getExpression(int maxValue){
        char[] chars = {'+','-','×','÷'};
        Random rand = new Random();
        String expression,value;
        while(true){
            int a=rand.nextInt(maxValue);
            int b=rand.nextInt(maxValue);
            char sign = chars[rand.nextInt(4)];
            if(sign=='+'){
                value= String.valueOf(a+b);
                expression = a+" + "+b;
            } else if(sign=='-'){
                if(a<b)continue;
                value= String.valueOf(a-b);
                expression = a+" - "+b;
            } else if(sign=='×'){
                value= String.valueOf(a*b);
                expression = a+" × "+b;
            }else{
                if(b==0)continue;
                value= GPF(a,b);
                expression = a+" ÷ "+b;
            }
            //results.add(splicing(a,b,sign,result));
            break;
        }
        return new Expression(value,expression);
    }


    /*
    计算公约数用于约分
     */
    public static int calculateGCD(int x, int y){
        if(x%y==0){
            return y;
        }
        return calculateGCD(y,x%y);
    }


    public static String GPF(int x, int y) {

        if(x%y==0)
        {
            return String.valueOf(x/y);
        }
        else {
            int gcd=calculateGCD(x,y);
            x/=gcd;
            y/=gcd;
            if (x < y) {
                return x + "/" + y;

            } else if (x > y) {
                int a = x / y;
                int b = (x % y);
                StringBuilder outcom2 = new StringBuilder();
                String A = String.valueOf(a);
                String B = String.valueOf(b);
                outcom2.append(A);
                outcom2.append("'");
                outcom2.append(B);
                outcom2.append("/");
                outcom2.append(y);
                return outcom2.toString();
            }
        }
        return null;
    }
}
