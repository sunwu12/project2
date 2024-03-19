package com.jiedui;

public class Expression {
    String expression=null;//表达式字符串
    String value=null;//表达式的值
    char keySign=' ',leftSign=' ',rightSign=' ';
    int num;//表达式中含有几个运算符


    public Expression(){}

    public Expression(String value){
        this.value= value;
        this.expression= value;
        this.num=0;
    }



    public Expression(String value, String expression,char keySign) {
        this.value = value;
        this.expression = expression;
        this.keySign=keySign;
        this.num=1;
    }


    public static Expression splicing(Expression leftE,Expression rightE,char sign){
        if(leftE==null||rightE==null)return null;
        int newNum=leftE.num+rightE.num+1;
        if(newNum>3)return null;
        Expression newE=new Expression();
        if(sign=='×'||sign=='÷'){
            if(leftE.keySign=='+'||leftE.keySign=='-')addBrackets(leftE);
            if(rightE.keySign=='+'||rightE.keySign=='-')addBrackets(rightE);
        }
        if(sign=='÷'&&(rightE.keySign=='÷'||rightE.keySign=='×'))addBrackets(rightE);
        if(sign=='-'&&(rightE.keySign=='+'||rightE.keySign=='-'))addBrackets(rightE);
        newE.leftSign=leftE.keySign;
        newE.rightSign=rightE.keySign;
        newE.keySign=sign;
        newE.num=newNum;
        newE.expression=leftE.expression+' '+sign+' '+rightE.expression;
        newE.value= utils.divisionFractionCalculate(leftE.value,rightE.value,sign);
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
                "value=\" " + value + " \"" +
                ", expression=\" " + expression + " \"" +
                '}';
    }
}
