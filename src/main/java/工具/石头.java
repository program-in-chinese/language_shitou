package 工具;

import java.util.Scanner;

public class 石头 {

  private static String 命令_退出 = "拜";
  private static String 命令_找文件 = "找文件";
  private static String 命令_运行 = "运行";

  private static String 反馈_提示 = "请说:";
  private static String 反馈_欢迎 = "欢迎使用石头";
  private static String 反馈_退出 = "回见";
  private static String 反馈_无法识别 = "不可识别: ";
  
  private static 文件批量处理 文件处理 = new 文件批量处理();
  
  public static void main(String[] args) {
    String 命令 = "";
    Scanner 读入 = new Scanner(System.in);
    do {
      应对(命令);
      命令 = 读入.nextLine();
    } while (!命令.equals(命令_退出));
    System.out.println(反馈_退出);
    读入.close();
  }

  private static void 应对(String 命令) {
    if (命令.isEmpty()) {
      System.out.println(反馈_欢迎);
    } else if (命令.indexOf(命令_找文件) == 0) {
      文件处理.置文件匹配表达式(命令.substring(命令_找文件.length()));
    } else if (命令.indexOf(命令_运行) == 0) {
      文件处理.置Shell命令模板(命令.substring(命令_运行.length()));
      try {
        文件处理.运行();
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } else {
      System.out.println(反馈_无法识别 + 命令);
    }
    System.out.print(反馈_提示);
  }

}
