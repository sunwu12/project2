package com.jiedui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
                int count=0,maxValue=0;
        String subjectPath=null,answerPath=null;
        for(int i=0;i<args.length;i++){
            switch (args[i]) {
                case "-n":
                    count = Integer.parseInt(args[i + 1]);
                    break;
                case "-r":
                    maxValue = Integer.parseInt(args[i + 1]);
                    break;
                case "-e":
                    subjectPath = args[i + 1];

                    break;
                case "-a":
                    answerPath = args[i + 1];
                    break;
            }
        }
        List<Expression> es = utils.getAllExpression(count, maxValue);
        for (Expression e : es) {
            System.out.println(e);
        }
        if(count!=0&&maxValue!=0) {
            TxtHandle.txtRecord(es);
        }else if(subjectPath!=null&&answerPath!=null){
            TxtHandle.txtJudge(subjectPath,answerPath);
        }
    }
}