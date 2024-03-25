package com.jiedui;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        int count = -1, maxValue = -1;
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
                return;
            }

        }
        //将表达式集合写入题目文件和答案文件中
        if(count!=-1&&maxValue!=-1) {
            if(count<=0) {
                System.out.println("输入的题目个数不能小于1");
                return;
            }
            else if(maxValue<3) {
                System.out.println("输入的最大值不能小于3");
                return;
            }
            else {
                List<Expression> es = ExpGeneration.getAllExpression(count, maxValue);
                TxtHandle.txtRecord(es);
            }
        }else if(count!=-1||maxValue!=-1) {
            System.out.println("请输入正确的格式！");
            return;
        }
        //根据输入的文件判断表达式正确数量并写入grade文件中
        try {
            if(subjectPath!=null&&answerPath!=null){
                TxtHandle.txtJudge(subjectPath, answerPath);
            }else if(subjectPath!=null||answerPath!=null) {
                System.out.println("请输入正确的格式！");
            }
        } catch (Exception e) {
            System.out.println("文件打开失败！请重新选择文件");
        }

    }
}