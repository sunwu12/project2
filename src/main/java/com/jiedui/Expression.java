package com.jiedui;

public class Expression {
    String expression;//表达式字符串
    String value;//表达式的值
    int num;//表达式中含有几个运算符


    public Expression(){}

    public Expression(int value){
        this.value= String.valueOf(value);
        this.expression= String.valueOf(value);
        this.num=0;
    }

    public Expression(String value, String expression) {
        this.value = value;
        this.expression = expression;
        this.num=1;
    }

    public static Expression splicing(Expression leftE,Expression rightE,char sign){
        Expression newE=new Expression();
        newE.num=leftE.num+rightE.num;
        newE.expression=leftE.expression+' '+sign+' '+rightE.expression;
        newE.value= String.valueOf(Integer.parseInt(leftE.value)+Integer.parseInt(rightE.value));
        return newE;
    }

    @Override
    public String toString() {
        return "Expression{" +
                "value=\" " + value + " \"" +
                ", expression=\" " + expression + " \"" +
                '}';
    }
}
