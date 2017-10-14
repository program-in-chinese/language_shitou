package 工具;

import java.util.Scanner;

public class 石头 {

  private static 文件批量处理 文件处理 = new 文件批量处理();
  
  public static void main(String[] args) {
    String 命令 = "";
    Scanner 读入 = new Scanner(System.in);
    do {
      应对(命令);
      命令 = 读入.nextLine();
    } while (!命令.equals("退"));
    System.out.println("回见");
    读入.close();
  }

  private static void 应对(String 命令) {
    if (命令.isEmpty()) {
      System.out.println("欢迎使用石头");
    } else if (命令.indexOf("找文件") == 0) {
      文件处理.置文件匹配表达式(命令.substring(3));
    } else if (命令.indexOf("运行") == 0) {
      文件处理.置Shell命令模板(命令.substring(2));
      try {
        文件处理.运行();
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } else {
      System.out.println("不可识别: " + 命令);
    }
    System.out.print("请说:");
  }

}
