import com.jiedui.Expression;
import com.jiedui.ExpGeneration;
import org.junit.Test;

import java.util.List;

public class UnitTest {
    //测试单一生产表达式
    @Test
    public void testMain(){
        //获取随机表达式集合
        List<Expression> es= ExpGeneration.getAllExpression(10,20);
        for(Expression e : es){
            System.out.println(e);
        }
        //将表达式集合写入题目文件和答案文件中
        //TxtHandle.txtRecord(es);
        //测试文件正确表达式个数
        //TxtHandle.txtJudge();
    }


}

