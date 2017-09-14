package 石头;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class 方法 {
  private static final Map<String, Function<List<Object>, Object>> 方法表 = new HashMap<>();
  static {
    方法表.put("+", (数量列表) -> {
      int 和 = 0;
      for (Object 数 : 数量列表) {
        和 += (int)数;
      }
      return 和;
      });
  }
  
  public String 名称;
  public List<Object> 参数 = new ArrayList<>();
  
  public Object 运行() {
    return 方法表.get(名称).apply(参数);
  }
}
