import java.io.IOException;

public class Main {
    public static String input, output;

    public static void main(String[] args) throws IOException {
        input = args[0];
        output = args[1];
        Io.fileCleaner();
        CommandHandler.executeCommands();
    }
}