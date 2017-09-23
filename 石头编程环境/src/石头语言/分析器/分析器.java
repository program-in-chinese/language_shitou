package 石头语言.分析器;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import 石头语言.库.内置库;
import 石头语言.语法.方法;

public class 分析器 {
  private static Set<String> 正则表达式特殊字符 = new HashSet<>(Arrays.asList("+", "*"));
  
  public static final 方法 分析(String 输入) {
    Set<String> 方法名列表 = 内置库.方法表.keySet();
    
    for (String 方法名 : 方法名列表) {
      if(输入.contains(方法名)) {
        String[] 方法名分割 = 输入.split(正则表达式特殊字符.contains(方法名) ? "\\" + 方法名 : 方法名);
        List<Object> 参数表 = new ArrayList<>();
        for (String 字符串 : 方法名分割) {
          参数表.add(字符串);
        }
        return new 方法(方法名, 参数表);
      }
    }
    return null;
  }
}
