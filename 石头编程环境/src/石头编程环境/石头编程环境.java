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
  public void start(Stage primaryStage) {
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.TOP_CENTER);
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(25, 25, 25, 25));
    grid.setBackground(Background.EMPTY);

    Scene scene = new Scene(grid, 800, 600);

    TextField 用户输入 = new TextField();
    用户输入.setMinWidth(700);
    
    // TODO: 输入检查/提示?
    用户输入.setTooltip(new Tooltip("请说"));
    grid.add(用户输入, 0, 1, 2, 1);

    final List<问答记录> 问答历史 = new ArrayList<>();

    用户输入.setOnKeyPressed(new EventHandler<KeyEvent>() {
      public void handle(KeyEvent ke) {
        if (ke.getCode().equals(KeyCode.ENTER)) {
          final Text 问 = new Text();
          grid.add(问, 0, 问答历史.size() * 2 + 记录位置);
          问.setFill(Color.CORNFLOWERBLUE);
          问.setFont(Font.font(字体, 字体大小));
          String 问内容 = 用户输入.getText();
          问.setText(问内容);

          final Text 答 = new Text();
          grid.add(答, 1, 问答历史.size() * 2 + 1 + 记录位置);
          答.setFill(Color.CHOCOLATE);
          答.setFont(Font.font(字体, 字体大小));
          String 答内容 = 演示问答.get(问内容);
          答内容 = 答内容 == null ? "听不懂" : 答内容;
          答.setText(答内容);
          问答历史.add(new 问答记录(问内容, 答内容));

          用户输入.setText("");
        }
      }
    });

    primaryStage.setTitle("石头演示");
    primaryStage.setScene(scene);
    primaryStage.show();

  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

}
