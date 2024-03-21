package com.jiedui;

import java.util.*;

public class utils {
    static char[] signArr = {'+','-','×','÷'};

    //随机生成表达式数组
    public static List<Expression> getAllExpression(int count, int maxValue){
        List<Expression> expressions = new ArrayList<>();
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
            expressions.add(e1);
        }

        return expressions;
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
                Expression e3;
                //增加随机性
                if(rand.nextBoolean())e3=Expression.splicing(e1,e2,sign);
                else e3=Expression.splicing(e2,e1,sign);
                if(e3==null)continue;
                return e3;
            }
        }
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
    后缀表达式
     */
    public static void ConvertSuffixExpressions(){

    }


    /*
    检查Expression类中的项是否重复
    符号优先级
     */
    public  static int getsignvalue(String s){
        int plus =1;
        int minus=1;
        int times=2;
        int divide=2;
        if(s.equals("+")){
            return plus;
        }
        if(s.equals("-")){
            return minus;
        }
        if(s.equals("×")){
            return times;
        }
        if(s.equals("÷")){
            return divide;
        }
        return 0;
    }
    public static List<String> getInfixExpression(String str){
        List<String> infixExpression=new ArrayList<>();
        int i=0;
        String A;
        char B;
        String b= str.replace(" ","");
        do{
            if(b.charAt(i)=='+'||b.charAt(i)=='-'||b.charAt(i)=='×'||b.charAt(i)=='÷'||b.charAt(i)=='('||b.charAt(i)==')')
            {
                B=b.charAt(i);
                infixExpression.add(""+b.charAt(i));
                i++;
            }
            else{
                A="";
                while((b.charAt(i)>=48&&b.charAt(i)<=57)||b.charAt(i)=='\''||b.charAt(i)=='/')
                {
                    A+=b.charAt(i);
                    i++;
                    if(i>=b.length())
                        break;

                }
                infixExpression.add(A);
            }
        }while(i<b.length());
        return infixExpression;
    }
    /*
    中缀转为后缀表达式
     */
    public static List<String> getPostfixExpression(List<String> infixExpression){
        List<String> s1= new ArrayList<>();//用于存放数字
        Stack<String> s2=new Stack<>();//用于存放运算符和符号
        for(String str:infixExpression)
        {
            if(!(str.equals("+")||str.equals("-")||str.equals("×")||str.equals("÷")||str.equals("(")||str.equals(")"))){
                s1.add(str);
            }
            else if(str.equals("("))
            {
                s2.push(str);
            }
            else if(str.equals(")"))
            {
                while(!s2.isEmpty()&&s2.peek().equals("("))
                {
                    s1.add(s2.pop());
                }
                s2.pop();
            } else
            {
                while(!s2.isEmpty()&&getsignvalue(s2.peek())>=getsignvalue(str))
                {
                    s1.add(s2.pop());
                }
                s2.push(str);
            }
        }
        while(!s2.isEmpty())
        {
            s1.add(s2.pop());
        }
        return s1;
    }
}

