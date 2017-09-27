package 工具;

public class 文件批量处理 {

  private String 文件匹配表达式 = "*";

  public static void main(String[] 参数) {
    if (参数.length != 2) {
      System.out.println("参数必须两个: " +
              "1) 文件匹配表达式, 如c:\\test*.txt; " +
              "2) 对文件处理的shell命令, 带双引号, 以'wenjian'指代文件, 如\"cp wenjian /tmp\"");
    }
  }
}
