package 石头;

import java.util.Arrays;

public class 解释器 {

  public static void main(String[] 参数) {
    方法 加 = new 方法();
    加.名称 = "+";
    加.参数 = Arrays.asList(1, 2, 3, 4);
    
    Object 返回值 = 加.运行();
    
    System.out.println(返回值);
  }

}
