package com.jiedui;

import java.util.Random;

public class utils {

    //生成一个表达式
    public static Expression getExpression(int maxValue) {
        char[] chars = {'+', '-', '×', '÷'};
        Random rand = new Random();
        String expression, value;
        while (true) {
            int a = rand.nextInt(maxValue);
            int b = rand.nextInt(maxValue);
            char sign = chars[rand.nextInt(4)];
            if (sign == '+') {
                value = String.valueOf(a + b);
                expression = a + " + " + b;
            } else if (sign == '-') {
                if (a < b) continue;
                value = String.valueOf(a - b);
                expression = a + " - " + b;
            } else if (sign == '×') {
                value = String.valueOf(a * b);
                expression = a + " × " + b;
            } else {
                if (b == 0) continue;
                value = GPF(a, b);
                expression = a + " ÷ " + b;
            }
            //results.add(splicing(a,b,sign,result));
            break;
        }
        return new Expression(value, expression);
    }


    /*
    计算公约数用于约分
     */
    public static int calculateGCD(int x, int y) {
        if (x % y == 0) {
            return y;
        }
        return calculateGCD(y, x % y);
    }


    public static String GPF(int x, int y) {

        if (x % y == 0) {
            return String.valueOf(x / y);
        } else {
            int gcd = calculateGCD(x, y);
            x /= gcd;
            y /= gcd;
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
    /*
    将存入字符串中的分数转为能进行运算的数字
     */
    public static int[] transformNum(String str1) {
        Integer num1=null;
        Integer num2=null;
        int[]num=new int[2];
        if (str1.contains("'")) {
            String[] dataA1 = str1.split("['/]");
             num1 = Integer.valueOf(dataA1[0]);
             num2 = Integer.valueOf(dataA1[1]);
            Integer num3 = Integer.valueOf(dataA1[2]);//真分数分母
            int numz = num1 * num3 + num2;//真分数分子
        } //真分数情况下
        else if(str1.contains("/")){
            String[] data1 = str1.split("/");
            num1 = Integer.valueOf(data1[0]);//分子
            num2 = Integer.valueOf(data1[1]);//分母
        }//分数情况下
        else{
            String[]dataan= str1.split("/");
            num1=Integer.valueOf(dataan[0]);//分子
            num2=1;//分母
        }//整数情况下
        num[0]=num1;
        num[1]=num2;
        return num;
    }

    /*
    与分数相关的计算
     */
    public static String divisionFractionCalculate(String str1, String str2, char A) {
        if (str1 != null && str2 != null) {
            Integer num1 = null;
            Integer num2 = null;
            Integer num3 = null;
            Integer num4 = null;
//            if (str1.contains("'")) {
//                String[] dataA1 = str1.split("['/]");
//                Integer numa1 = Integer.valueOf(dataA1[0]);
//                Integer numa2 = Integer.valueOf(dataA1[1]);
//                Integer numa3 = Integer.valueOf(dataA1[2]);//分母
//                int numa = numa1 * numa3 + numa2;//分子
//            } else if(str1.contains("/")){
//                String[] data1 = str1.split("/");
//                num1 = Integer.valueOf(data1[0]);//分子
//                num2 = Integer.valueOf(data1[1]);//分母
//            }
//            else{
//                String[]dataan= str1.split("/");
//                num1=Integer.valueOf(dataan[0]);//分子
//                num2=1;//分母
//            }
//            if (str2.contains("'")) {
//                String[] dataB1 = str2.split("['/]");
//                Integer numb1 = Integer.valueOf(dataB1[0]);
//                Integer numb2 = Integer.valueOf(dataB1[1]);
//                Integer numb3 = Integer.valueOf(dataB1[2]);//分母
//                int num = numb1 * numb3 + numb2;//分子
//            } else if(str2.contains("/")){
//                String[] data2 = str2.split("/");
//                num3 = Integer.valueOf(data2[0]);//分子
//                num4 = Integer.valueOf(data2[1]);//分母
//            }
//            else{
//                String[]databn=str2.split("/");
//                num3=Integer.valueOf(databn[0]);
//                num4=1;
//            }

            int[]str1_num;
            int[]str2_num;
            str1_num=transformNum(str1);
            str2_num=transformNum(str2);
            if (A == '+') {
                if (str1_num[1] == str2_num[1]) {
                    int Numerator = str1_num[0] + str2_num[0];
                    int Denominator = str1_num[1];
                    String k = GPF(Numerator, Denominator);
                    System.out.println(k);
                    return k;
                } else {
                    int Denominator = str1_num[1] * str2_num[1];
                    int Numerator = str1_num[0] * str2_num[1] + str2_num[0] * str1_num[1];
                    String k = GPF(Numerator, Denominator);
                    System.out.println(k);
                    return k;
                }
            }
            if (A == '-') {
                if (str1_num[1] == str2_num[1]) {
                    int Numerator = str1_num[0] - str1_num[0];
                    int Denominator = str1_num[1];
                    String k = GPF(Numerator, Denominator);
                    System.out.println(k);
                    return k;
                } else {
                    int Denominator = str1_num[1] * str2_num[1];
                    int Numerator = str1_num[0] * str2_num[1] - str2_num[0] * str1_num[1];
                    String k = GPF(Numerator, Denominator);
                    System.out.println(k);
                    return k;
                }
            }
            if(A=='×'){
                int Numerator=str1_num[0]*str2_num[0];
                int Denominator=str1_num[1]*str2_num[1];
                String k=GPF(Numerator,Denominator);
                System.out.println(k);
                return k;
            }
            if(A=='÷'){
                int Numerator=str1_num[0]*str2_num[1];
                int Denominator=str1_num[1]*str2_num[0];
                String k=GPF(Numerator,Denominator);
                System.out.println(k);
                return k;
            }







//            if (A == '+') {
//                if (num2 == num4) {
//                    int Numerator = num1 + num3;
//                    int Denominator = num2;
//                    String k = GPF(Numerator, Denominator);
//                    System.out.println(k);
//                } else {
//                    int Denominator = num2 * num4;
//                    int Numerator = num1 * num4 + num3 * num2;
//                    String k = GPF(Numerator, Denominator);
//                    System.out.println(k);
//                }
//            }
//            if (A == '-') {
//                if (num2 == num4) {
//                    int Numerator = num1 - num3;
//                    int Denominator = num2;
//                    String k = GPF(Numerator, Denominator);
//                    System.out.println(k);
//                } else {
//                    int Denominator = num2 * num4;
//                    int Numerator = num1 * num4 - num3 * num2;
//                    String k = GPF(Numerator, Denominator);
//                    System.out.println(k);
//                }
//            }
//            if(A=='×'){
//                int Numerator=num1*num3;
//                int Denominator=num2*num4;
//                String k=GPF(Numerator,Denominator);
//                System.out.println(k);
//            }
//            if(A=='÷'){
//                int Numerator=num1*num4;
//                int Denominator=num2*num3;
//                String k=GPF(Numerator,Denominator);
//                System.out.println(k);
//            }
        }

        return null;
    }
}
