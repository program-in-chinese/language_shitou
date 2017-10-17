package 工具;

import java.io.File;
import java.text.MessageFormat;
import java.util.Scanner;

public class 石头 {

  private static String 命令_退出 = "拜";
  private static String 命令_找文件 = "找文件";
  private static String 命令_运行 = "运行";
  private static String 命令_哪些 = "哪些";

  private static String 反馈_提示 = "请说:";
  private static String 反馈_欢迎 = "欢迎使用石头";
  private static String 反馈_退出 = "回见";
  private static String 反馈_无法识别 = "不可识别: ";
  private static String 反馈_选中文件 = "选中{0,number,integer}个文件";

  private static 文件批量处理 文件处理 = new 文件批量处理();
  private static Scanner 读入 = new Scanner(System.in);

  public static void main(String[] args) {
    String 命令 = "";
    do {
      应对(命令);
      命令 = 读入.nextLine();
    } while (!命令.equals(命令_退出));

    清理();
  }

  private static void 清理() {
    System.out.println(反馈_退出);
    文件处理.清理();
    读入.close();
  }

  private static void 应对(String 命令) {
    if (命令.isEmpty()) {
      反馈(反馈_欢迎);
    } else if (命令.indexOf(命令_找文件) == 0) {
      文件处理.置文件匹配表达式(命令.substring(命令_找文件.length()));
      反馈(MessageFormat.format(反馈_选中文件, 文件处理.取选中文件().size()));
    } else if (命令.indexOf(命令_哪些) == 0) {
      反馈(MessageFormat.format(反馈_选中文件, 文件处理.取选中文件().size()));
      for(File 文件 : 文件处理.取选中文件()) {
        反馈(文件.getAbsolutePath());
      }
    } else if (命令.indexOf(命令_运行) == 0) {
      文件处理.置Shell命令模板(命令.substring(命令_运行.length()));
      try {
        文件处理.运行();
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } else {
      反馈(反馈_无法识别 + 命令);
    }
    反馈(反馈_提示, false);
  }

  private static void 反馈(String 信息) {
    反馈(信息, true);
  }
  
  private static void 反馈(String 信息, boolean 换行) {
    if (换行) {
      System.out.println(信息);
    } else {
      System.out.print(信息);
    }
  }
}
