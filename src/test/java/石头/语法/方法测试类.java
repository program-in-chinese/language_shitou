package 石头.语法;

import static com.github.program_in_chinese.junit4_in_chinese.断言类.相等;

import java.util.Arrays;

import org.junit.Test;

public class 方法测试类 {

  @Test
  public void 加() {
    相等(1, new 方法("+", Arrays.asList(1)).运行());
    相等(6, new 方法("+", Arrays.asList(1, 2, 3)).运行());
  }

  @Test
  public void 判断相等() {
    方法 相等 = new 方法("=", Arrays.asList(1, 2));
    相等(false, 相等.运行());

    相等.参数 = Arrays.asList(2, 2);
    相等(true, 相等.运行());

    相等.参数 = Arrays.asList(2, 2, 1);
    相等(false, 相等.运行());
  }

  @Test
  public void 条件() {
    方法 条件 = new 方法("如果",
        Arrays.asList(new 方法("=", Arrays.asList(2, 2)),
            new 方法("+", Arrays.asList(1)),
            new 方法("+", Arrays.asList(1, 2))));
    相等(1, 条件.运行());
  }
}
