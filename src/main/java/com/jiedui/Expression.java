package com.jiedui;

public class Expression {
    String expression=null;//表达式字符串
    String value=null;//表达式的值
    char keySign=' ';//表达式的主运算符
    Expression leftE=null;//表达式的左边表达式
    Expression rightE=null;//表达式的右边表达式
    int num=0;//表达式中含有几个运算符

    public Expression(){}
    public Expression(int maxValue){//获取只含一个随机的数的表达式
        this.value=utils.getRandomValue(maxValue);
        this.expression=this.value;
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
        newE.leftE=leftE;
        newE.rightE=rightE;
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
