import com.jiedui.Expression;
import com.jiedui.utils;
import org.junit.Test;

public class test {
    @Test
    public void testMain(){
        Expression[] es=utils.getAllExpression(15,15);
        for(Expression e:es){
            System.out.println(e);
        }
    }
}