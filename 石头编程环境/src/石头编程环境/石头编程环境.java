package 石头编程环境;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author xuanwu
 */
public class 石头编程环境 extends Application {

  private static final int 记录位置 = 2;
  private static final HashMap<String, String> 演示问答 = new HashMap<>();
  static {
    演示问答.put("3+4", "7");
    演示问答.put("找 c:\\下载 所有txt文件", "已找到5个:\nc:\\test1.txt\nc:\\test2.txt\nc:\\test3.txt\nc:\\test4.txt\nc:\\test5.txt");
    演示问答.put("每个文件", "开始循环");
    演示问答.put("读文件", "开始循环");
  }
  
  @Override
  public void start(Stage primaryStage) {
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(25, 25, 25, 25));

    Scene scene = new Scene(grid, 300, 275);

    TextField 用户输入 = new TextField();
    grid.add(用户输入, 0, 1);

    Button btn = new Button("");
    HBox hbBtn = new HBox(10);
    hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
    hbBtn.getChildren().add(btn);
    grid.add(hbBtn, 1, 1);

    final List<问答记录> 问答历史 = new ArrayList<>();

    btn.setOnAction((ActionEvent e) -> {
      final Text 问 = new Text();
      grid.add(问, 0, 问答历史.size() * 2 + 记录位置);
      问.setFill(Color.CHOCOLATE);
      String 问内容 = 用户输入.getText();
      问.setText(问内容);
      
      final Text 答 = new Text();
      grid.add(答, 1, 问答历史.size() * 2 + 1 + 记录位置);
      答.setFill(Color.CORNFLOWERBLUE);
      String 答内容 = 演示问答.get(问内容);
      答.setText(答内容);
      问答历史.add(new 问答记录(问内容, 答内容));
    });

    primaryStage.setTitle("编程环境演示");
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
