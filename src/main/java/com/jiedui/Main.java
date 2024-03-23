package com.jiedui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        int count=0,maxValue=0;
        System.out.println("22222222");
 String subjectPath=null,answerPath=null;
 for(int i=0;i<args.length;i++){
 if(args[i].equals("-n")){
 count=Integer.parseInt(args[i+1]);
 }else if(args[i].equals("-r"))
 {
 maxValue=Integer.parseInt(args[i+1]);
 }else if(args[i].equals("-e")){
 subjectPath=args[i+1];

 }else if(args[i].equals("-a")){
 answerPath=args[i+1];
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