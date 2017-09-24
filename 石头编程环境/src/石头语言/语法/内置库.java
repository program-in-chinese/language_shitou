package 石头语言.语法;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class 内置库 {

  public static final Map<String, Function<List<Object>, Object>> 方法表 = new HashMap<>();
  public static final Map<String, String> 语法定义 = new HashMap<>();
  
  static {
    方法表.put("+", (值列表) -> {
      return 值列表.stream().reduce((x, y) -> 叠加(x, y)).get();
    });
    方法表.put("=", (值列表) -> {
      return 值列表.stream().reduce((x, y) -> x.equals(y)).get();
    });
    方法表.put("如果", (值列表) -> {

      // TODO: 检查值列表长度, 确保第一项为条件(方法), 第二项为真分支, 第三项为假分支
      return ((方法)(值列表.get(0))).运行().equals(true) ?
          ((方法)(值列表.get(1))).运行() : ((方法)(值列表.get(2))).运行();
    });
    语法定义.put("如果", "如果([\\d\\=\\+]+),([\\d\\=\\+]+),否则([\\d\\=\\+]+)");
  }

  private static Object 叠加(Object x, Object y) {
    int 数1 = x instanceof String ? Integer.parseInt((String)x) : (int)x;
    int 数2 = y instanceof String ? Integer.parseInt((String)y) : (int)y;
    return x instanceof String ? (数1 + 数2) + "" : 数1 + 数2;
  }

}
