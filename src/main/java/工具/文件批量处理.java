package 工具;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class 文件批量处理 {

  private String 文件匹配表达式 = "";
  private List<File> 选中文件 = new ArrayList<>();
  private String shell命令模板 = "";

  private static String 占位符 = "文件";
  private static ExecutorService 单线程执行器 = Executors.newSingleThreadExecutor();

  private static class StreamGobbler implements Runnable {

    private InputStream inputStream;
    private Consumer<String> consumer;

    public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
      this.inputStream = inputStream;
      this.consumer = consumer;
    }

    @Override
    public void run() {
      new BufferedReader(new InputStreamReader(inputStream)).lines().forEach(consumer);
    }
  }

  public void 运行() throws InterruptedException {
    try {
      String 命令模板 = 取Shell命令模板();
      if (!命令模板.contains(占位符)) {
        运行命令(命令模板);
      } else {
        for (File 文件 : 选中文件) {
          运行命令(命令模板, 文件.getAbsolutePath());
        }
      }
    } catch (IOException ex) {
      Logger.getLogger(文件批量处理.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private static List<File> 获取匹配文件(String 文件匹配表达式) {
    // TODO: 支持无*, 多*, 按属性(大小,创建/修改时间)过滤等等. 考虑调用ls/dir的返回值
    // TODO: 支持追加文件, 重置选中文件
    int 匹配符位置 = 文件匹配表达式.indexOf("*");
    String 目录路径 = 文件匹配表达式.substring(0, 匹配符位置 - 1);
    ArrayList<File> 匹配文件 = new ArrayList<>();
    Path startDir = Paths.get(目录路径);
    FileSystem fs = FileSystems.getDefault();
    final PathMatcher matcher = fs.getPathMatcher("glob:" + 文件匹配表达式.substring(匹配符位置));

    FileVisitor<Path> matcherVisitor = new SimpleFileVisitor<Path>() {
      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attribs) {
        Path name = file.getFileName();
        if (matcher.matches(name)) {
          匹配文件.add(file.toFile());
        }
        return FileVisitResult.CONTINUE;
      }

      @Override
      public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        // 无视所有无法访问的文件
        return FileVisitResult.CONTINUE;
      }
    };
    try {
      Files.walkFileTree(startDir, matcherVisitor);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return 匹配文件;
  }

  private static void 运行命令(String shell命令模板) throws IOException, InterruptedException {
    运行命令(Arrays.asList(shell命令模板.split(" ")));
  }

  private static void 运行命令(String shell命令模板, String 文件路径) throws IOException, InterruptedException {
    String[] 命令行 = shell命令模板.split(" ");
    List<String> 命令行段 = new ArrayList<>();
    for (String 分段 : 命令行) {
      命令行段.add(分段.equals(占位符) ? 文件路径 : 分段);
    }
    运行命令(命令行段);
  }

  private static void 运行命令(List<String> 命令行段) throws IOException, InterruptedException {
    boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
    ProcessBuilder builder = new ProcessBuilder();
    if (isWindows) {
      builder.command("cmd.exe", "/c", "dir");
    } else {
      builder.command(命令行段);
    }
    builder.directory(new File(System.getProperty("user.home")));
    Process process = builder.start();
    StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
    单线程执行器.submit(streamGobbler);
    int exitCode = process.waitFor();
    assert exitCode == 0;
  }

  public String 取文件匹配表达式() {
    return 文件匹配表达式;
  }

  public void 置文件匹配表达式(String 文件匹配表达式) {
    this.文件匹配表达式 = 文件匹配表达式;
    选中文件 = 获取匹配文件(文件匹配表达式);
  }

  public String 取Shell命令模板() {
    return shell命令模板;
  }

  public void 置Shell命令模板(String shell命令模板) {
    this.shell命令模板 = shell命令模板;
  }

  public List<File> 取选中文件() {
    return 选中文件;
  }

  public void 清理() {
    单线程执行器.shutdown();
  }
}
