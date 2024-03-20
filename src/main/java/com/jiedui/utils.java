package com.jiedui;

import java.util.Random;

public class utils {
    static char[] signArr = {'+','-','×','÷'};

    //随机生成表达式数组
    public static Expression[] getAllExpression(int count,int maxValue){
        Expression[] es = new Expression[count];
        Random rand = new Random();
        while(count-->0){
            Expression e1=getExpression(maxValue,rand.nextInt(4));
            while(true){
                Expression e2;
                if((e2=Expression.splicing(e1,getExpression(maxValue,rand.nextInt(4)),signArr[rand.nextInt(4)]))
                !=null){
                    e1=e2;
                }else if(e1.num>0)break;
            }
            es[es.length-count-1]=e1;
        }

        return es;
    }

    //生成一个表达式
    public static Expression getExpression(int maxValue,int num){
        if(num==0)return new Expression(maxValue);
        else {
            while(true){
                Random rand = new Random();
                char sign=signArr[rand.nextInt(4)];
                Expression e1=new Expression(maxValue);
                Expression e2=getExpression(maxValue,num-1);
                Expression e3=Expression.splicing(e1,e2,sign);
                if(e3==null)continue;
                return e3;
            }
        }
    }

    public static String getRandomValue(int maxValue){
        Random random=new Random();
        if(random.nextBoolean()){//生成随机真分数
            return getFraction(random.nextInt(maxValue),random.nextInt(maxValue));
        }else return String.valueOf(random.nextInt(maxValue));//生成随机整数
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


    //转换出分数
    public static String getFraction(int x, int y) {
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
        int num1;
        int num2;
        int[]num=new int[2];
        if (str1.contains("'")) {
            String[] dataA1 = str1.split("['/]");
             int Anum1 = Integer.parseInt(dataA1[0]);
             int Anum2 = Integer.parseInt(dataA1[1]);
             num2 = Integer.parseInt(dataA1[2]);//真分数分母
             num1 = Anum1 * num2 + Anum2;//真分数分子
        } //真分数情况下
        else if(str1.contains("/")){
            String[] data1 = str1.split("/");
            num1 = Integer.parseInt(data1[0]);//分子
            num2 = Integer.parseInt(data1[1]);//分母
        }//分数情况下
        else{
            String[]dataan= str1.split("/");
            num1= Integer.parseInt(dataan[0]);//分子
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
                    return getFraction(Numerator, Denominator);
                } else {
                    int Denominator = str1_num[1] * str2_num[1];
                    int Numerator = str1_num[0] * str2_num[1] + str2_num[0] * str1_num[1];
                    return getFraction(Numerator, Denominator);
                }
            }
            if (A == '-') {
                if (str1_num[1] == str2_num[1]) {
                    int Numerator = str1_num[0] - str2_num[0];
                    if(Numerator<0){
                        return null;
                    }
                    int Denominator = str1_num[1];
                    return getFraction(Numerator, Denominator);
                } else {
                    int Denominator = str1_num[1] * str2_num[1];
                    int Numerator = str1_num[0] * str2_num[1] - str2_num[0] * str1_num[1];
                    if(Numerator<0){
                        return null;
                    }
                    return getFraction(Numerator, Denominator);
                }
            }
            if(A=='×'){
                int Numerator=str1_num[0]*str2_num[0];
                int Denominator=str1_num[1]*str2_num[1];
                return getFraction(Numerator,Denominator);
            }
            if(A=='÷'){
                int Numerator=str1_num[0]*str2_num[1];
                int Denominator=str1_num[1]*str2_num[0];
                if(Denominator==0){
                    return null;
                }
                return getFraction(Numerator,Denominator);
            }
        }
        return null;
    }
    /*
    检查表达式是否重复
     */
//    public  static String checkExpression(Expression e1,Expression e2)
//    {
//
//    }
}

