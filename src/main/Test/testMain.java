import com.jiedui.Expression;
import com.jiedui.TxtHandle;
import com.jiedui.utils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class testMain {
    //测试单一生产表达式
    @Test
    public void testGetExpression(){
        //maxvalue是表达式中的最大值，生成的数值均不会大于该数值，num是指表达式中的运算符个数
        List<Expression> es=utils.getAllExpression(1,15);
        for(Expression e : es){
            System.out.println(e);
        }
    }


}

