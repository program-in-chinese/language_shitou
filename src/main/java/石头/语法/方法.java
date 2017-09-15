package 石头.语法;

import java.util.ArrayList;
import java.util.List;

import 石头.库.内置库;

public class 方法 {
  public Object 名称;
  public List<Object> 参数 = new ArrayList<>();

  public 方法(Object 名称, List<Object> 参数) {
    this.名称 = 名称;
    this.参数 = 参数;
  }

  public Object 运行() {
    return 内置库.方法表.get(名称).apply(参数);
  }
}
