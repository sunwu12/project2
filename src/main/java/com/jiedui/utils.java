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

    public static String GPF(int x, int y) {

        if (x < y) {
            StringBuilder outcom = new StringBuilder();
            outcom.append(x);
            outcom.append("/");
            outcom.append(y);
            return outcom.toString();

        } else if (x > y) {
            int a = x / y;
            int b = (x % y);
            StringBuilder outcom1 = new StringBuilder();
            String A = String.valueOf(a);
            String B = String.valueOf(b);
            outcom1.append(A);
            outcom1.append("'");
            outcom1.append(B);
            outcom1.append("/");
            outcom1.append(y);
            return outcom1.toString();


        }
        return null;
    }
}
