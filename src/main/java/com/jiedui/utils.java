package com.jiedui;

import java.util.Random;

public class utils {
    public static String getRandomSign(int count,int maxValue){
        char[] chars = {'+','-','×','÷'};
        Random rand = new Random();
        int a=rand.nextInt(maxValue);
        int b=rand.nextInt(maxValue);
        int result=0;
        char sign = chars[rand.nextInt(4)];
        switch (sign){
            case '+':result=a+b;break;
            case '-':result=a-b;break;
            case '×':result=a*b;break;
            case '÷':result=a/b;break;
        }
        String s=a+" "+sign+" "+b+" = "+result;
        System.out.println(s);
        return s;
    }


    public static String getProperFration(String x, String y) {
        int num1 = Integer.parseInt(x);
        int num2 = Integer.parseInt(y);
        if (num1 < num2) {
            StringBuilder outcom = new StringBuilder();
            outcom.append(x);
            outcom.append("/");
            outcom.append(y);
            return outcom.toString();

        } else if (num1 > num2) {
            int a = num1 / num2;
            int b = (num1 % num2);
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
