package web_run.hellospring.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class test {
    int mun=0;
    String name="java";

    @Test
    public void test()
    {
        String text = null;

        if (text == null) {
            throw new NullPointerException("text 변수가 null입니다.");
        }
    }
    @Test
    public void test1()
    {
        mun++;

        System.out.println(mun);
    }

}
