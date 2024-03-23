package com.jiedui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        int count = 0, maxValue = 0;
        String subjectPath = null, answerPath = null;
        for (int i = 0; i < args.length; i++) {
            try{
                switch (args[i]) {
                    case "-n" -> count = Integer.parseInt(args[i + 1]);
                    case "-r" -> maxValue = Integer.parseInt(args[i + 1]);
                    case "-e" -> subjectPath = args[i + 1];
                    case "-a" -> answerPath = args[i + 1];
                }
            }catch (Exception e){
                System.out.println("输入错误，请重新输入！");
            }

        }
        List<Expression> es = utils.getAllExpression(count, maxValue);
        for (Expression e : es) {
            System.out.println(e);
        }
        if(count<=0) System.out.println("输入的题目个数不能小于1");
        else if(maxValue<3) System.out.println("输入的最大值不能小于3");
        //将表达式集合写入题目文件和答案文件中
        TxtHandle.txtRecord(es);
        //根据输入的文件判断表达式正确数量并写入grade文件中
        try {
            TxtHandle.txtJudge(subjectPath, answerPath);
        } catch (IOException e) {
            System.out.println("文件打开失败！请重新选择文件");
        }

    }
}