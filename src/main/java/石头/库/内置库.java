package 石头.库;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class 内置库 {
  public static final Map<String, Function<List<Object>, Object>> 方法表 = new HashMap<>();
  static {
    方法表.put("+", (数量列表) -> {
      int 和 = 0;
      for (Object 数 : 数量列表) {
        和 += (int) 数;
      }
      return 和;
    });
  }

}
