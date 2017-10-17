package 工具;

import static com.github.program_in_chinese.junit4_in_chinese.断言.相等;

import org.junit.Test;

public class 文件处理测试类 {

  private static 文件批量处理 文件处理 = new 文件批量处理();

  @Test
  public void test() {
    文件处理.置文件匹配表达式("测试资源/*.txt");
    相等(1, 文件处理.取选中文件().size());
    文件处理.置文件匹配表达式("测试资源/*.html");
    相等(2, 文件处理.取选中文件().size());
  }

}
