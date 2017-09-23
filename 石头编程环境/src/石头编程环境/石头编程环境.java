package 石头编程环境;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import 石头语言.分析器.分析器;
import 石头语言.语法.方法;

public class 石头编程环境 extends Application {

  private static final int 记录位置 = 2;
  private static final String 字体 = "Songti SC";
  private static final int 字体大小 = 15;
  private static final HashMap<String, String> 演示问答 = new HashMap<>();

  static {
    演示问答.put("3+4", "7");
    演示问答.put("找 c:\\下载目录 所有txt文件", "已找到3个:\nc:\\test1.txt\nc:\\test2.txt\nc:\\test3.txt");
    演示问答.put("每个文件", "开始循环");
    演示问答.put("读文件", "开始循环");
  }

  @Override
  public void start(Stage 顶层容器) {
    GridPane 网格面板 = new GridPane();
    网格面板.setAlignment(Pos.TOP_CENTER);
    网格面板.setHgap(10);
    网格面板.setVgap(10);
    网格面板.setPadding(new Insets(25, 25, 25, 25));
    网格面板.setBackground(Background.EMPTY);

    Scene 情景 = new Scene(网格面板, 800, 600);

    TextField 用户输入 = new TextField();
    用户输入.setMinWidth(700);
    
    // TODO: 输入检查/提示?
    用户输入.setTooltip(new Tooltip("请说"));
    网格面板.add(用户输入, 0, 1, 2, 1);

    final List<问答记录> 问答历史 = new ArrayList<>();

    用户输入.setOnKeyPressed(new EventHandler<KeyEvent>() {
      public void handle(KeyEvent 键盘事件) {
        if (键盘事件.getCode().equals(KeyCode.ENTER)) {
          final Text 问 = new Text();
          网格面板.add(问, 0, 问答历史.size() * 2 + 记录位置);
          问.setFill(Color.CORNFLOWERBLUE);
          问.setFont(Font.font(字体, 字体大小));
          String 问内容 = 用户输入.getText();
          问.setText(问内容);

          final Text 答 = new Text();
          网格面板.add(答, 1, 问答历史.size() * 2 + 1 + 记录位置);
          答.setFill(Color.CHOCOLATE);
          答.setFont(Font.font(字体, 字体大小));
          
          方法 识别方法 = 分析器.分析(问内容);
          String 答内容 = 识别方法 != null ? 识别方法.运行().toString() : 演示问答.get(问内容);
          答内容 = 答内容 == null ? "听不懂" : 答内容;
          答.setText(答内容);
          问答历史.add(new 问答记录(问内容, 答内容));

          用户输入.setText("");
        }
      }
    });

    顶层容器.setTitle("石头演示");
    顶层容器.setScene(情景);
    顶层容器.show();

  }

  public static void main(String[] 参数) {
    launch(参数);
  }

}
