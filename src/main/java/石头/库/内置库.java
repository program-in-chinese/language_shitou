package 石头.库;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class 内置库 {
  public static final Map<String, Function<List<Object>, Object>> 方法表 = new HashMap<>();
  static {
    方法表.put("+", (值列表) -> {
      return 值列表.stream().reduce((x, y) -> (int) x + (int) y).get();
    });
    方法表.put("=", (值列表) -> {
      return 值列表.stream().reduce((x, y) -> x.equals(y)).get();
    });
  }

}
