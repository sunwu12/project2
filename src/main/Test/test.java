import cn.hutool.core.io.FileUtil;
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

