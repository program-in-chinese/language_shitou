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
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class 文件批量处理 {

  //private static String 文件匹配表达式 = "\\*.html";
  // private static String shell命令模板 = "";
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
      new BufferedReader(new InputStreamReader(inputStream)).lines()
              .forEach(consumer);
    }
  }

  public static void main(String[] 参数) throws InterruptedException {
    String 文件匹配表达式 = "*.html"; //参数[0];
    String shell命令模板 = "ls -l 文件";//参数[1];

    try {
      List<File> 匹配文件 = 获取匹配文件(文件匹配表达式);
      for (File 文件 : 匹配文件) {
        运行命令(shell命令模板, 文件.getAbsolutePath());
      }
      单线程执行器.shutdown();
    } catch (IOException ex) {
      Logger.getLogger(文件批量处理.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private static List<File> 获取匹配文件(String 文件匹配表达式) throws IOException {
    ArrayList<File> 匹配文件 = new ArrayList<>();
    Path startDir = Paths.get("/Users/xuanwu/work/bak/");
    FileSystem fs = FileSystems.getDefault();
    final PathMatcher matcher = fs.getPathMatcher("glob:*.html");

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
    Files.walkFileTree(startDir, matcherVisitor);
    return 匹配文件;
  }

  private static void 运行命令(String shell命令模板, String 文件路径) throws IOException, InterruptedException {
    String[] 命令行 = shell命令模板.split(" ");
    List<String> 命令行段 = new ArrayList<>();
    for(String 分段 : 命令行) {
      命令行段.add(分段.equals(占位符) ? 文件路径 : 分段);
    }
    运行命令(命令行段);
  }

  private static void 运行命令(List<String> 命令行段) throws IOException, InterruptedException {
    boolean isWindows = System.getProperty("os.name")
            .toLowerCase().startsWith("windows");
    ProcessBuilder builder = new ProcessBuilder();
    if (isWindows) {
      builder.command("cmd.exe", "/c", "dir");
    } else {
      builder.command(命令行段);
    }
    builder.directory(new File(System.getProperty("user.home")));
    Process process = builder.start();
    StreamGobbler streamGobbler
            = new StreamGobbler(process.getInputStream(), System.out::println);
    单线程执行器.submit(streamGobbler);
    int exitCode = process.waitFor();
    assert exitCode == 0;
  }
}
