package com.jiedui;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpHandle {

    /*
     将给定的字符串表达式进行计算
     */
    public static String calExpressionString(String expression) {
        List<String> postfixExpression = getPostfixExpression(expression);
        String[] sinList = new String[2];
        do {
            postfixExpression = handleList(postfixExpression, sinList);
            if (postfixExpression == null) break;
        } while (postfixExpression.size() != 1);
        if (postfixExpression != null) {
            return postfixExpression.get(0);
        }
        return null;
    }

    //判断两个后缀表达式是否重复
    public static Boolean checkDuplicate(String expression1, String expression2) {
        List<String> e1 = getPostfixExpression(expression1);
        List<String> e2 = getPostfixExpression(expression2);
        if (e1.size() != e2.size()) return false;
        if (!Objects.equals(calExpressionString(expression1), calExpressionString(expression2))) return false;
        String[] sinList1 = new String[2];
        String[] sinList2 = new String[2];
        do {
            e1 = handleList(e1, sinList1);
            e2 = handleList(e2, sinList2);
            if (!Arrays.equals(sinList1, sinList2)) return false;
            if (e1 == null || e2 == null) {
                break;
            }
        } while (e1.size() != 1);
        return true;
    }

    /*
   获取符号的优先级
    */


    /*
    将字符串转换为中缀表达式存入到集合中，为转换为后缀表达式做准备
     */
    private static List<String> getInfixExpression(String str) {
        List<String> infixExpression = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        str = str.replace(" ", "");
        Pattern pattern = Pattern.compile("[+×÷()-]");
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            Matcher matcher = pattern.matcher(Character.toString(ch));
            if (!matcher.find()) {
                sb.append(ch);
            } else {
                if (!sb.isEmpty()) infixExpression.add(sb.toString());
                infixExpression.add(Character.toString(ch));
                sb = new StringBuilder();
            }
        }
        if (!sb.isEmpty()) infixExpression.add(sb.toString());
        return infixExpression;
    }

    /*
    中缀转为后缀表达式，意义是去除括号
     */
    private static List<String> getPostfixExpression(String expression) {
        List<String> infixExpression = getInfixExpression(expression);
        List<String> s1 = new ArrayList<>();//用于存放数字
        Stack<String> s2 = new Stack<>();//用于存放运算符和符号
        for (String str : infixExpression) {
            if (str.charAt(0)>='0' &&str.charAt(0)<='9') {
                s1.add(str);
            } else if (str.equals("(")) {
                s2.push(str);
            } else if (str.equals(")")) {
                while (!s2.peek().equals("(")) {
                    s1.add(s2.pop());
                    if (s2.isEmpty())
                        break;
                }
                if (!s2.isEmpty()) {
                    s2.pop();
                }
            } else {
                while (!s2.isEmpty() && getSignPriority(s2.peek()) >= getSignPriority(str)) {
                    s1.add(s2.pop());
                }
                s2.push(str);
            }
        }
        while (!s2.isEmpty()) {
            s1.add(s2.pop());
        }
        return s1;
    }

    private static int getSignPriority(String s) {
        if (s.equals("+") || s.equals("-")) return 1;
        else if(s.equals("×")||s.equals("÷")) return 2;
        else return 0;
    }

    //处理后缀表达式集合
    private static List<String> handleList(List<String> list, String[] sinList) {
        int i = 0;
        Pattern pattern = Pattern.compile("[+×÷-]");
        //每进行一次，去除一个运算符，合并两个数值
        while (i < list.size()) {
            String str = list.get(i);
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                char sign = str.charAt(0);
                List<String> newList = new ArrayList<>(list);
                String newVal = Fraction.fractionCalculate(newList.get(i - 2), newList.get(i - 1), sign);
                if (newVal == null) return null;
                sinList[0] = newList.get(i - 2);
                sinList[1] = newList.get(i - 1);
                Arrays.sort(sinList);
                newList.set(i - 2, newVal);
                newList.remove(i - 1);
                newList.remove(i - 1);
                return newList;
            }
            i++;
        }
        return null;
    }
}

