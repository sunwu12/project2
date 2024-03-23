import com.jiedui.Expression;
import com.jiedui.TxtHandle;
import com.jiedui.utils;
import org.junit.Test;

import java.util.List;
import java.util.Random;

public class test {
    //测试单一生产表达式
    @Test
    public void testGetExpression(){
        Expression expression= utils.getExpression(30,3);//maxvalue是表达式中的最大值，生成的数值均不会大于该数值，num是指表达式中的运算符个数
        System.out.println(expression);
    }

    //测试生产表达式数组
    @Test
    public void testGetAllExpression(){
        List<Expression> es = utils.getAllExpression(5, 15);
        for (Expression e : es) {
            System.out.println(e);
        }

    }

    //测试分数的转换
    @Test
    public void testGetFraction(){
        Random rand = new Random();
        int A= rand.nextInt(10),B= rand.nextInt(10);
        String fraction=utils.getFraction(A,B);
        System.out.println("分子:"+A);
        System.out.println("分母:"+B);
        System.out.println("分数:"+fraction);
    }


    //测试表达式的计算
    @Test
    public void testDivisionFractionCalculate(){
        List<Expression> es = utils.getAllExpression(2, 15);
        for (Expression e : es) {
            System.out.println(e);
        }
        utils.divisionFractionCalculate(es.get(1).expression,es.get(2).expression,'+');
    }













    //测试判断结果对错
    @Test
    public void testJudge() {
        List<Expression> es = utils.getAllExpression(5, 15);
        for (Expression e : es) {
            System.out.println(e);
        }
        TxtHandle.txtRecord(es,"../../src/resources/Exercises.txt",
                "../../src/resources/Answers.txt");
        String subPath="../../src/resources/Exercises.txt";
        String ansPath="../../src/resources/Answers.txt";
        String graPath="../../src/resources/Grade.txt";
        TxtHandle.txtJudge(subPath,ansPath,graPath);
    }

}

