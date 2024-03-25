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
        //System.out.println(Arrays.toString(arr));
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
        this.transformValue();
    }

    /*
    分数约分
     */
    private void transformValue() {
        //分母为0，则value为null
        if(this.denominator==0){
            this.value=null;
        }else if(this.numerator==0){
            this.value="0";
        } else {
            //化简
            this.fractionSimplify();
            //分母为1，返回整数
            if(this.denominator==1)this.value=this.numerator+"";
            else if (this.numerator < this.denominator) {
                this.value = this.numerator + "/" + this.denominator;
            } else if (this.numerator > this.denominator) {
                int a = this.numerator / this.denominator;
                int b = (this.numerator % this.denominator);
                this.value=a+"'"+b+"/"+this.denominator;
            }
        }
    }

    /*
        分数化简
   */
    private void fractionSimplify() {
        int a=this.numerator,b=this.denominator;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        this.numerator/=a;
        this.denominator/=a;
    }


    //计算两个分数
    public static String fractionCalculate(String str1,String str2,char sign) {
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
