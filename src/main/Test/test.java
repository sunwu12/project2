import com.jiedui.Expression;
import com.jiedui.TxtHandle;
import com.jiedui.utils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class test {
    @Test
    public void testMain() {
        List<Expression> es = utils.getAllExpression(20, 15);
        for (Expression e : es) {
           // System.out.println(e);
        }
        //TxtHandle.txtRecord(es,"../../src/resources/Exercises.txt",
        //        "../../src/resources/Answers.txt");
//        TxtHandle.txtRecord(es,"../../src/resources/Exercises.txt",
//                "../../src/resources/Answers.txt");
        //FileUtil.appendUtf8Lines(es,"");
        List<String> list =utils.getInfixExpression(es.get(1).expression);
        System.out.println(list);
        List<String> List =utils.getPostfixExpression(list);
        System.out.println(List);
    }

//    @Test
//    public void TestTxt(){
//        String expression="(5 - (3 + 2/5)) ÷ 7";
//        expression=expression.replace(" ","");
//        String[] strings=expression.split("[+×÷-]");
//        System.out.println(Arrays.toString(strings));
//        List<Expression> expressions=convertExpressArr(strings);
//        String signString=getSignString(expression);
//        System.out.println(signString);
//        System.out.println(expressions);
//        //convertExpression(expressions,signString);
//    }

//    private static Expression removeParentheses(String expression){
//
//    }

//    private static String getSignString(String expression){
//        StringBuilder sb=new StringBuilder();
//        for(int i=0;i<expression.length();i++){
//            char c=expression.charAt(i);
//            Pattern p = Pattern.compile(".*[()+\\-×÷].*");
//            Matcher m = p.matcher(Character.toString(c));
//            if(m.matches())sb.append(c);
//        }
//        return sb.toString();
//    }
//
//    private static List<Expression> convertExpressArr(String []strings){
//        List<Expression> expressions=new ArrayList<>();
//        for (String string : strings) {
//            string=string.replace("(","").replace(")","");
//            if(!string.isEmpty())expressions.add(new Expression(string));
//        }
//        return expressions;
//    }
//
//    private static Expression convertExpression(List<Expression> expressions, String signString){
//        int index=signString.indexOf("(");
//        if(index!=-1){
//            int first=signString.indexOf(")");
//            if(first!=-1){
//                int second=signString.indexOf(")",first);
//            }
//        }
//        String[] regexArr={"[×÷]","[+-]"};
//        for(String regex : regexArr){
//            Pattern pattern = Pattern.compile(regex);
//            Matcher matcher = pattern.matcher(signString);
//            while (matcher.find()) {
//                int j = matcher.start();
//                char sign=signString.charAt(j);
//                if(signString.length()==1)signString=signString.replace(Character.toString(sign),"");
//                else signString=signString.replaceFirst(Character.toString(sign),"");
//                Expression newE=Expression.splicing(expressions.get(j),expressions.get(j+1),sign);
//                expressions.remove(j);
//                expressions.set(j,newE);
//                matcher = pattern.matcher(signString);
//            }
//        }
//        System.out.println(expressions);
//        return expressions.get(0);
//    }
//
//    private static void clearSign(List<Expression> expressions,String signString,String regex){
//
//    }
}

