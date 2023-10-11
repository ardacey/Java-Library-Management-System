import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
public class Io {
    public static String[] readFile(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));
        return lines.toArray(new String[0]);
    }

    public static void writeToFile(String content, boolean newLine) {
        PrintStream ps = null;
        try {
            ps = new PrintStream(new FileOutputStream(Main.output, true));
            ps.print(content + (newLine ? "\n" : ""));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.flush();
                ps.close();
            }
        }
    }

    public static void fileCleaner() {
        try {
            PrintWriter writer = new PrintWriter("output.txt");
            writer.print("");
            writer.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}