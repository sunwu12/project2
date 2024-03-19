package com.jiedui;

import java.util.Objects;
import java.util.Random;

public class utils {
    static char[] signArr = {'+','-','×','÷',' '};

    public static Expression[] getAllExpression(int count,int maxValue){
        Expression[] es = new Expression[count];
        Random rand = new Random();
        while(count > 0){
            char sign=signArr[rand.nextInt(4)];
            Expression e1 = getExpression(maxValue);
            Expression e2 = getExpression(maxValue);
            if(divisionFractionCalculate(e1.value,e2.value,sign)==null)continue;
            es[es.length-count]=Expression.splicing(e1,e2,sign);
            count--;
        }
        return es;
    }

    //生成一个表达式
    public static Expression getExpression(int maxValue){
        Random rand = new Random();
        String value,expression;
        char sign;
        while (true){
            sign=signArr[rand.nextInt(5)];
            String ran1=GPF(rand.nextInt(maxValue), rand.nextInt(maxValue) );
            String ran2=GPF(rand.nextInt(maxValue), rand.nextInt(maxValue) );
            if(sign==' '){
                return new Expression(ran1);
            }else {
                value=divisionFractionCalculate(ran1,ran2,sign);
                if(value==null)continue;
                expression=ran1+" "+sign+" "+ran2;
            }
            break;
        }
        return new Expression(value,expression,sign);
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


    //相除得真分数
    public static String GPF(int x, int y) {
        if(y==0)
        {
            return null;
        }
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
    转换出分子和分母
     */
    public static int[] transformNum(String str1) {
        Integer num1=null;
        Integer num2=null;
        int[]num=new int[2];
        if (str1.contains("'")) {
            String[] dataA1 = str1.split("['/]");
             Integer Anum1 = Integer.valueOf(dataA1[0]);
             Integer Anum2 = Integer.valueOf(dataA1[1]);
             num2 = Integer.valueOf(dataA1[2]);//真分数分母
             num1 = Anum1 * num2 + Anum2;//真分数分子
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
    计算
     */
    public static String divisionFractionCalculate(String str1, String str2, char A) {
        if (str1 != null && str2 != null) {
            int[]str1_num;
            int[]str2_num;
            str1_num=transformNum(str1);
            str2_num=transformNum(str2);
            if (A == '+') {
                if (str1_num[1] == str2_num[1]) {
                    int Numerator = str1_num[0] + str2_num[0];
                    int Denominator = str1_num[1];
                    String k = GPF(Numerator, Denominator);
                    return k;
                } else {
                    int Denominator = str1_num[1] * str2_num[1];
                    int Numerator = str1_num[0] * str2_num[1] + str2_num[0] * str1_num[1];
                    String k = GPF(Numerator, Denominator);
                    return k;
                }
            }
            if (A == '-') {
                if (str1_num[1] == str2_num[1]) {
                    int Numerator = str1_num[0] - str2_num[0];
                    if(Numerator<0){
                        return null;
                    }
                    int Denominator = str1_num[1];
                    String k = GPF(Numerator, Denominator);
                    return k;
                } else {
                    int Denominator = str1_num[1] * str2_num[1];
                    int Numerator = str1_num[0] * str2_num[1] - str2_num[0] * str1_num[1];
                    if(Numerator<0){
                        return null;
                    }
                    String k = GPF(Numerator, Denominator);
                    return k;
                }
            }
            if(A=='×'){
                int Numerator=str1_num[0]*str2_num[0];
                int Denominator=str1_num[1]*str2_num[1];
                String k=GPF(Numerator,Denominator);
                return k;
            }
            if(A=='÷'){
                int Numerator=str1_num[0]*str2_num[1];
                int Denominator=str1_num[1]*str2_num[0];
                if(Denominator==0){
                    return null;
                }
                String k=GPF(Numerator,Denominator);
                return k;
            }
        }
        return null;
    }
//    public  static String adb(Expression e1,Expression e2)
//    {}
}

