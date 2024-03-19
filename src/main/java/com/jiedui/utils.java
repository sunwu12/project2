package com.jiedui;

import java.util.Random;

public class utils {

    public static String getRandomSign(int count,int maxValue){
        char[] chars = {'+','-','×','÷'};
        Random rand = new Random();
        int a=rand.nextInt(maxValue);
        int b=rand.nextInt(maxValue);
        String result=null;
        char sign = chars[rand.nextInt(4)];
        switch (sign){
            case '+':{
                int r=a+b;
                result=String.valueOf(r);
                break;
            }
            case '-':{
                int r=a-b;
                result=String.valueOf(r);
                break;
            }
            case '×':{
                int r=a*b;
                result=String.valueOf(r);
                break;
            }
            case '÷':{
                result=GPF(a,b);
                break;
            }
        }
        String s=a+" "+sign+" "+b+" = "+result;
        System.out.println(s);
        return s;
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
