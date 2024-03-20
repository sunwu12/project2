import com.jiedui.Expression;
import com.jiedui.TxtHandle;
import com.jiedui.utils;
import org.junit.Test;

import java.util.List;

public class test {
    @Test
    public void testMain() {
        List<Expression> es = utils.getAllExpression(20, 15);
        for (Expression e : es) {
            System.out.println(e);
        }
        //TxtHandle.txtRecord(es,"../../src/resources/Exercises.txt",
        //        "../../src/resources/Answers.txt");
//        TxtHandle.txtRecord(es,"../../src/resources/Exercises.txt",
//                "../../src/resources/Answers.txt");
        //FileUtil.appendUtf8Lines(es,"");
        List<String> list =utils.getInfixExpression(es.get(1).expression);
        System.out.println(list);
//        List<String> List =utils.getPostfixExpression(list);
//        System.out.println(List);
    }
}

//    @Test
//    public void TestTxt(){
//        TxtHandle.txtRead();
//    }
//}