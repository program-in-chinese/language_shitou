package 石头语言.库;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class 内置库 {

  public static final Map<String, Function<List<Object>, Object>> 方法表 = new HashMap<>();

  static {
    方法表.put("+", (值列表) -> {
      return 值列表.stream().reduce((x, y) -> 叠加(x, y)).get();
    });
    方法表.put("=", (值列表) -> {
      return 值列表.stream().reduce((x, y) -> x.equals(y)).get();
    });
  }

  private static Object 叠加(Object x, Object y) {
    int 数1 = x instanceof String ? Integer.parseInt((String)x) : (int)x;
    int 数2 = y instanceof String ? Integer.parseInt((String)y) : (int)y;
    return x instanceof String ? (数1 + 数2) + "" : 数1 + 数2;
  }

}
