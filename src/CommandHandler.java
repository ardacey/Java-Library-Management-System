import java.io.IOException;
import java.time.LocalDate;

public class CommandHandler {
    public static void executeCommands() throws IOException {
        String[] commands = Io.readFile(Main.input);
        for (String command : commands){
            String[] action = command.split("\t");
            if (action[0].equals("addBook")){
                Library.addBook(action[1]);
            }
            if (action[0].equals("addMember")){
                Library.addMember(action[1]);
            }
            if (action[0].equals("borrowBook")){
                Library.borrowBook(Integer.parseInt(action[1]), Integer.parseInt(action[2]), LocalDate.parse(action[3]));
            }
            if (action[0].equals("readInLibrary")){
                Library.readInLibrary(Integer.parseInt(action[1]), Integer.parseInt(action[2]), LocalDate.parse(action[3]));
            }
            if (action[0].equals("returnBook")){
                Library.returnBook(Integer.parseInt(action[1]), Integer.parseInt(action[2]), LocalDate.parse(action[3]));
            }
            if (action[0].equals("extendBook")){
                Library.extendBook(Integer.parseInt(action[1]), Integer.parseInt(action[2]), LocalDate.parse(action[3]));
            }
            if (action[0].equals("getTheHistory")){
                History.getTheHistory();
            }
        }
    }
}
