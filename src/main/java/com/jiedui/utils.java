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
    /*
    将字符串转换为中缀表达式存入到集合中，为转换为后缀表达式做准备
     */
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
    中缀转为后缀表达式，意义是去除括号
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
                while(!s2.peek().equals("("))
                {
                    s1.add(s2.pop());
                    if(s2.isEmpty())
                        break;
                }
                if(!s2.isEmpty()) {
                    s2.pop();
                }
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

    /*
     将给定的字符串表达式进行计算
     */
    public static String cal(List<String> ostfixExpression){
        List<String>num=new ArrayList<>();//用于存放数字
        String save=null,A,B;
        int i,j;
        for(String ch:ostfixExpression)
        {
            if(!(ch.equals("+")||ch.equals("-")||ch.equals("×")||ch.equals("÷")||ch.equals("(")||ch.equals(")"))){
                num.add(ch);
            }
            else
            {
                switch (ch) {
                    case "+":
                        i = (num.size() - 1);
                        A = num.get(i);
                        B = num.get(i - 1);
                        for (i = num.size() - 1, j = 0; j < 2; j++) {
                            num.remove(i);
                            i--;
                        }
                        save = divisionFractionCalculate(A, B, '+');
                        num.add(save);
                        break;
                    case "-":
                        i = (num.size() - 1);
                        A = num.get(i - 1);
                        B = num.get(i);
                        for (i = num.size() - 1, j = 0; j < 2; j++) {
                            num.remove(i);
                            i--;
                        }
                        save = divisionFractionCalculate(A, B, '-');
                        num.add(save);
                        break;
                    case "×":
                        i = (num.size() - 1);
                        A = num.get(i);
                        B = num.get(i - 1);
                        for (i = num.size() - 1, j = 0; j < 2; j++) {
                            num.remove(i);
                            i--;
                        }
                        save = divisionFractionCalculate(A, B, '×');
                        num.add(save);
                        break;
                    case "÷":
                        i = (num.size() - 1);
                        A = num.get(i - 1);
                        B = num.get(i);
                        for (i = num.size() - 1, j = 0; j < 2; j++) {
                            num.remove(i);
                            i--;
                        }
                        save = divisionFractionCalculate(A, B, '÷');
                        num.add(save);
                        break;
                }
            }
        }
        return save;
    }
    /*
    将后缀表达式转换为字符串,为检查重复做准备
     */
    public static String ConverPostfixExpressionToStrings(List<String> ostfixExpression){
       StringBuilder str=new StringBuilder();//存入该字符串
        List<String>num=new ArrayList<>();//用于存放数字
        int i,j,record=0;
        String A,B;
        for(String ch:ostfixExpression)
        {
            if(!(ch.equals("+")||ch.equals("-")||ch.equals("×")||ch.equals("÷")||ch.equals("(")||ch.equals(")"))){
                num.add(ch);
                record++;
            }
            else{
                if(record==ostfixExpression.size()-1)
                {
                    str.append(ch);
                    break;
                }
                while(num.size()<2)
                {
                  String space="";
                  num.add(space);
                }
                i=num.size()-1;
                A = num.get(i-1);
                B = num.get(i);
                str.append(ch);
                str.append(A);
                str.append(B);
                for (i = num.size() - 1, j = 0; j < 2; j++) {
                    num.remove(i);
                    i--;
                }
                record++;
            }
        }
        return str.toString();
    }

    public static void CheckDuplicates(String str1,String str2){
        int str1num=str1.length();
        int str2num=str2.length();
        int i=0,str1k=0,m=0,str2k=0;
        char[]s1=new char[str1num+1];
        char[]s2=new char[str2num+1];
        if(str1num<str2num){
            i=str1num;
        }
        else{
            i=str2num;
        }
       String []str1a=str1.split("[1234567890]");
        String []str2a=str2.split("[1234567890]");
        for(int j=0;j<str1a.length;j++)
        {
            if(!str1a[j].equals(str2a[j]))
            {
                System.out.println("不一样");
                return;
            }
        }

        for(int j=0;j<i;j++)
        {
            if(str1.charAt(j)=='+'||str1.charAt(j)=='-'||str1.charAt(j)=='×'||str1.charAt(j)=='÷'){

                m=j;
                if(j==i-1){
                    break;
                }
                for(int p=0;p<2;p++) {
                    s1[str1k] = str1.charAt(m + 1);
                    str1k++;
                    m++;
                }
            }
            if(str2.charAt(j)=='+'||str2.charAt(j)=='-'||str2.charAt(j)=='×'||str2.charAt(j)=='÷'){
                m=j;
                if(j==i-1){
                    break;
                }
                for(int p=0;p<2;p++) {
                    s2[str2k] = str2.charAt(m + 1);
                    str2k++;
                    m++;
                }
            }
        }
        for(int j=0;j<i;j=j+2){
            int sum1=0,sum2=0;;
           sum1+=(s1[j]-'0')+(s1[j+1]-'0');
           sum2+=(s2[j]-'0')+(s2[j+1]-'0');
           if(sum1!=sum2){
               System.out.println("不一样");
               return;
           }
        }
        System.out.println("一样");
    }
}

