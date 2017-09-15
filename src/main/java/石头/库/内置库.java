package 石头.库;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import 石头.语法.方法;

public class 内置库 {
  public static final Map<String, Function<List<Object>, Object>> 方法表 = new HashMap<>();
  static {
    方法表.put("+", (值列表) -> {
      return 值列表.stream().reduce((x, y) -> (int) x + (int) y).get();
    });
    方法表.put("=", (值列表) -> {
      return 值列表.stream().reduce((x, y) -> x.equals(y)).get();
    });
    方法表.put("如果", (值列表) -> {

      // TODO: 检查值列表长度, 确保第一项为条件(方法), 第二项为真分支, 第三项为假分支
      return ((方法)(值列表.get(0))).运行().equals(true) ?
          ((方法)(值列表.get(1))).运行() : ((方法)(值列表.get(2))).运行();
    });
  }

}
