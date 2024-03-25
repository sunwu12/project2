package com.jiedui;

import cn.hutool.core.io.FileUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TxtHandle {
    public static void txtRecord(List<Expression> es){
            AtomicInteger i = new AtomicInteger();
            AtomicInteger j = new AtomicInteger();
//            String subjectPath="src/resources/Exercises_test01.txt";
//            String answerPath="src/resources/Answers_test01.txt";
            String subjectPath= "Exercises.txt";
            String answerPath= "Answers.txt";
            //读取文件中最大序号
            List<String> a=FileUtil.readUtf8Lines(subjectPath);
            if(a.isEmpty()) {
                i.set(1);
                j.set(1);
            } else {
                String str=a.get(a.size()-1);
                i.set(Integer.parseInt(str.split("\\.")[0])+1);
                j.set(Integer.parseInt(str.split("\\.")[0])+1);
            }

            //写入带有序号的表达式
            List<String> subjectList=es.stream().map(e->i.getAndIncrement()+". "+e.expression+" = ").collect(Collectors.toList());
            List<String> answerList=es.stream().map(e->j.getAndIncrement()+". "+e.value).collect(Collectors.toList());

            FileUtil.appendUtf8Lines(subjectList,subjectPath);
            FileUtil.appendUtf8Lines(answerList,answerPath);
        }

        public static void txtJudge(String subjectPath,String answerPath)throws Exception {
                List<String> subjectList=FileUtil.readUtf8Lines(subjectPath);
                List<String> anwerList=FileUtil.readUtf8Lines(answerPath);
                //将读取的表达式中的等于号去掉
                List<String> expList=subjectList.stream().map(s->s.replace(" =","")
                        .split("^\\d+\\.")[1]).toList();
                List<String> valList=anwerList.stream().map(s->s.replace(" =","")
                        .split("^\\d+\\.")[1].trim()).toList();
                int[] gradeList=new int[subjectList.size()];
                for(int i=0;i<subjectList.size();i++){
                    //计算正确则为1，反之为0
                    gradeList[i]= Objects.equals(ExpHandle.calExpressionString(expList.get(i)), valList.get(i)) ?1:0;
                }
                StringBuilder sb1=new StringBuilder();
                StringBuilder sb2=new StringBuilder();
                //根据1，0的个数判断题目正确个数
                long corNum=Arrays.stream(gradeList).filter(i->i==1).count();
                long wroNum=Arrays.stream(gradeList).filter(i->i==0).count();
                sb1.append("Correct: ").append(corNum).append("(");
                sb2.append("Wrong: ").append(wroNum).append("(");
                for(int i=0;i<subjectList.size();i++){
                    if(gradeList[i]==1) {
                        sb1.append(i + 1).append(",");
                    }
                    else sb2.append(i+1).append(",");
                }
                if(sb1.charAt(sb1.length()-1)==',')sb1.deleteCharAt(sb1.length()-1);
                if(sb2.charAt(sb2.length()-1)==',')sb2.deleteCharAt(sb2.length()-1);
                sb1.append(")");
                sb2.append(")");
                String gradePath="src/resources/Answers_test01.txt";
                FileUtil.writeUtf8Lines(new ArrayList<>(List.of(new String[]{sb1.toString(),sb2.toString()})), gradePath);
            }
        }
