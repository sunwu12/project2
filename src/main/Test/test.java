import com.jiedui.Expression;
import com.jiedui.utils;
import org.junit.Test;

public class test {
    @Test
    public void testMain(){
        Expression expression=utils.getExpression(10,3);
        System.out.println(expression);
    }
}