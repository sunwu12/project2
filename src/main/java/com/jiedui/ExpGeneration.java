package com.jiedui;

import java.util.*;


public class ExpGeneration {
    private static final char[] signArr = {'+', '-', '×', '÷'};

    //随机生成表达式数组
    public static List<Expression> getAllExpression(int count, int maxValue) {
        List<Expression> expressions = new ArrayList<>();
        Random rand = new Random();
        //生成表达式的个数
        while (count > 0) {
            Expression e1 = getExpression(maxValue, rand.nextInt(4));
            while (true) {
                Expression e2;
                //拼接失败则存入集合
                if ((e2 = Expression.splicing(e1, getExpression(maxValue, rand.nextInt(4)), signArr[rand.nextInt(4)]))
                        != null) {
                    e1 = e2;
                } else if (e1.num > 0) break;//无运算符表达式，重新刷新
            }
            //如果出现重复则刷新新表达式
            if (expressions.contains(e1)) continue;
            expressions.add(e1);
            count--;
        }
        return expressions;
    }

    //生成一个随机表达式
    private static Expression getExpression(int maxValue, int num) {
        //运算符的个数
        if (num == 0) return new Expression(maxValue);
        else {
            while (true) {
                Random rand = new Random();
                char sign = signArr[rand.nextInt(4)];
                Expression e1 = new Expression(maxValue);
                Expression e2 = getExpression(maxValue, num - 1);
                Expression e3;
                //增加随机性
                if (rand.nextBoolean()) e3 = Expression.splicing(e1, e2, sign);
                else e3 = Expression.splicing(e2, e1, sign);
                if (e3 == null) continue;
                return e3;
            }
        }
    }
}



