package 石头.语法;

import static com.github.program_in_chinese.junit4_in_chinese.断言类.相等;

import java.util.Arrays;

import org.junit.Test;

public class 方法测试类 {

  @Test
  public void test() {
    方法 加 = new 方法();
    加.名称 = "+";
    加.参数 = Arrays.asList(1, 2, 3);

    Object 返回值 = 加.运行();
    相等(6, 返回值);
  }

}
