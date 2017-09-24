package 石头语言.分析器;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import 石头语言.语法.方法;

public class 分析器测试类 {

  @Test
  public void 分析() {
    assertEquals(new 方法("+", Arrays.asList("1", "2")), 分析器.分析("1+2"));
    assertEquals(new 方法("如果", 
            Arrays.asList(new 方法("=", Arrays.asList("3", "3")),
                    new 方法("+", Arrays.asList("1", "2")),
                    new 方法("+", Arrays.asList("2", "3")))),
            分析器.分析("如果3=3,1+2,否则2+3"));
  }
}
