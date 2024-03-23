package com.jiedui;

import java.util.Arrays;
import java.util.List;

//分数类
public class Fraction {
    public int numerator;//分子
    public int denominator;//分母
    public String value;//分数的值

    public Fraction(String str) {
        //分割
        String[] arr=str.split("['/]");
        //字符串转成数字
        List<Integer> list= Arrays.stream(arr).map(Integer::parseInt).toList();
        int size=list.size();
        //根据真分数情况计算分子分母
        this.denominator=size>1? list.get(size-1) :1;
        this.numerator=size>2?list.get(0)*list.get(2)+list.get(1):list.get(0);
        if(this.denominator==0)this.value=null;
        else this.value=str;
    }

    public Fraction(int numerator,int denominator) {
        this.numerator=numerator;
        this.denominator=denominator;
        transformValue(this);
    }

    public Fraction(){}
    /*
    转换出分子和分母
     */
    public static void transformValue(Fraction f) {
        //分母为0，则value为null
        if(f.denominator==0){
            f.value=null;
        }else if(f.numerator==0){
            f.value="0";
        } else {
            int gcd = calculateGCD(f.numerator, f.denominator);
            f.numerator /= gcd;
            f.denominator /= gcd;
            //分母为1，返回整数
            if(f.denominator==1)f.value=f.numerator+"";
            else if (f.numerator < f.denominator) {
                f.value = f.numerator + "/" + f.denominator;
            } else if (f.numerator > f.denominator) {
                int a = f.numerator / f.denominator;
                int b = (f.numerator % f.denominator);
                f.value=a+"'"+b+"/"+f.denominator;
            }
        }
    }

    /*
  计算最大公约数
   */
    public static int calculateGCD(int x, int y) {
        if (x % y == 0) return y;
        //y作分子，x%y作分母进入循环
        return calculateGCD(y, x % y);
    }


    //转换出分数
    public static String divisionFractionCalculate(String str1,String str2,char sign) {
        Fraction f1=new Fraction(str1);
        Fraction f2=new Fraction(str2);
        int Numerator=0,Denominator=0;
        if (f1.value != null && f2.value != null) {
            if (sign == '+'||sign=='-') {
                if(sign=='-')f2.numerator=-f2.numerator;
                if (f1.denominator == f2.denominator) {
                    Numerator = f1.numerator+f2.numerator;
                    Denominator = f1.denominator;
                } else {
                    Numerator = f1.numerator * f2.denominator + f2.numerator * f1.denominator;
                    Denominator = f1.denominator * f2.denominator;
                }
                if(sign=='-')f2.numerator=-f2.numerator;
                if(Numerator<0) return null;//结果不能为负
            } else if(sign=='×'){
                Numerator=f1.numerator*f2.numerator;
                Denominator=f1.denominator*f2.denominator;
            }
            else if(sign=='÷'){
                Numerator=f1.numerator*f2.denominator;
                Denominator=f1.denominator*f2.numerator;
                if(Denominator==0){
                    return null;
                }
            }
            Fraction newF=new Fraction(Numerator,Denominator);
            return newF.value;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Fraction{" +
                "结果=\" " + value + " \"" +
                ", 分子=\" " + numerator + " \"" +
                ", 分母=" + denominator +
                '}';
    }
}
