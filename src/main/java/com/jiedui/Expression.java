package com.jiedui;

import java.util.Random;

public class Expression extends Throwable {
    public String expression=null;//表达式字符串
    String value=null;//表达式的值
    char keySign=' ';//表达式的主运算符
    int num=0;//表达式中含有几个运算符

    public Expression(){}
    public Expression(int maxValue){//获取只含一个随机的数的表达式
        this.value=getRandomValue(maxValue);
        this.expression=this.value;
    }

    public Expression(String value){//获取只含一个确定的数的表达式
        this.value=value;
        this.expression=value;
    }

    public static String getRandomValue(int maxValue){
        Random random=new Random();
        if(random.nextBoolean()){//生成随机真分数
            String str=utils.getFraction(random.nextInt(maxValue),random.nextInt(maxValue));
            while(str==null)str=utils.getFraction(random.nextInt(maxValue),random.nextInt(maxValue));
            return str;
        }else return String.valueOf(random.nextInt(maxValue));//生成随机整数
    }

    //拼接两个表达式成一个
    public static Expression splicing(Expression leftE,Expression rightE,char sign){
        if(leftE==null||rightE==null)return null;
        int newNum=leftE.num+rightE.num+1;
        if(newNum>3)return null;
        Expression newE=new Expression();
        if((newE.value=utils.divisionFractionCalculate(leftE.value,rightE.value,sign))==null)return null;
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

    //判断能否相减

    @Override
    public String toString() {
        return "Expression{" +
                "结果=\" " + value + " \"" +
                ", 表达式=\" " + expression + " \"" +
                ", 运算符个数=" + num +
                '}';
    }
}
