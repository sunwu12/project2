package com.jiedui;

import java.util.Random;

public class Expression {
    public String expression=null;//表达式字符串
    public String value=null;//表达式的值
    char keySign=' ';//表达式的主运算符
    int num=0;//表达式中含有几个运算符
    int maxNum=3;//最大运算符个数

    public Expression(){}
    public Expression(int maxValue){//获取只含一个随机的数的表达式
        this.value=getRandomValue(maxValue);
        this.expression=this.value;
    }

    //生成随机值
    public static String getRandomValue(int maxValue){
        Random random=new Random();
        if(random.nextBoolean()){//生成随机真分数
            Fraction fraction=new Fraction(random.nextInt(maxValue),random.nextInt(maxValue));
            while(fraction.value==null)fraction=new Fraction(random.nextInt(maxValue),random.nextInt(maxValue));
            return fraction.value;
        }else return String.valueOf(random.nextInt(maxValue));//生成随机整数
    }

    //拼接两个表达式成一个
    public static Expression splicing(Expression leftE,Expression rightE,char sign){
        if(leftE==null||rightE==null)return null;
        int newNum=leftE.num+rightE.num+1;
        Expression newE=new Expression();
        //运算符个数超过3个
        if(newNum> newE.maxNum)return null;
        if((newE.value=Fraction.fractionCalculate(leftE.value,rightE.value,sign))==null)return null;
        //添加括号
        if(sign=='×'||sign=='÷'){
            if(leftE.keySign=='+'||leftE.keySign=='-')addBrackets(leftE);
            if(rightE.keySign=='+'||rightE.keySign=='-')addBrackets(rightE);
        }
        if(sign=='÷'&&(rightE.keySign=='÷'||rightE.keySign=='×'))addBrackets(rightE);
        if(sign=='-'&&(rightE.keySign=='+'||rightE.keySign=='-'))addBrackets(rightE);
        newE.keySign=sign;
        newE.num=newNum;
        newE.expression=leftE.expression+' '+sign+' '+rightE.expression;
        return newE;
    }

    //表达式加括号
    public static void addBrackets(Expression E){
        E.expression='('+E.expression+')';
    }

    @Override
    public String toString() {
        return "Expression{" +
                "结果=\" " + value + " \"" +
                ", 表达式=\" " + expression + " \"" +
                ", 运算符个数=" + num +
                '}';
    }

    //重写判断表达式相等
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass()!= o.getClass()) return false;
        Expression exp = (Expression) o;
        return ExpHandle.checkDuplicate(this.expression,exp.expression);
    }
}
