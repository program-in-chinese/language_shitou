package 工具;

import java.util.Scanner;

public class 石头 {

  public static void main(String[] args) {
    String 命令 = "";
    Scanner 读入 = new Scanner(System.in);
    do {
      应对(命令);
      命令 = 读入.next();
    } while (!命令.equals("退"));
    读入.close();
  }

  private static void 应对(String 命令) {
    if (命令.isEmpty()) {
      System.out.println("欢迎使用石头");
    } else {
      System.out.println("已输入: " + 命令);
    }
    System.out.print("请说:");
  }

}
