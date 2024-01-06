import java.util.Scanner;

public class FileSystem {
    public static void main(String[] args) {
        Root root = Root.getRoot();

        Scanner scanner = new Scanner(System.in);
        Command command = new Command(root);
        while (true) {
            String input = scanner.nextLine();
            if(input.equals("exit")) break;
            else {
                command.setCommand(input);
                command.executeCommand();
            }
        }
    }
}
