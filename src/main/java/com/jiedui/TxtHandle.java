package com.jiedui;

import cn.hutool.core.io.FileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TxtHandle {
    public static void txtRecord(List<Expression> es, String subjectPath,String answerPath){
        List<String> subjectList=es.stream().map(e->e.expression+" =").collect(Collectors.toList());
        List<String> answerList=es.stream().map(e->e.value).collect(Collectors.toList());
        FileUtil.appendUtf8Lines(subjectList,subjectPath);
        FileUtil.appendUtf8Lines(answerList,answerPath);
    }

    public static List<String> txtRead(){
        List<String> subjectList=FileUtil.readUtf8Lines("../../src/resources/Exercises.txt");
        List<String> anwerList=FileUtil.readUtf8Lines("../../src/resources/Answers.txt");
        System.out.println(subjectList);
        System.out.println(subjectList.get(1));
        String str=subjectList.get(1).replace(" =","");
        System.out.println(str);
        System.out.println(str.replace(" ",""));
        return subjectList;
    }


}
