import java.io.IOException;
import java.util.Scanner;

public class Main
{
    private static String addCommand = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static String commandExamples = "\t" + addCommand + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static String commandError = "Wrong command! Available command examples: \n" +
            commandExamples;
    private static String helpText = "Command examples:\n" + commandExamples;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();
        for(;;) {
            try {
                String command = scanner.nextLine();
                command.trim();
                System.out.println(command);
                String[] tokens = command.trim().split("\\s+", 2);
                if (tokens[0].equals("add")) {
                    executor.addCustomer(tokens[1]);
                } else if (tokens[0].equals("list") && tokens.length == 1) {
                    executor.listCustomers();
                } else if (tokens[0].equals("remove") && tokens.length == 2) {
                    executor.removeCustomer(tokens[1]);
                } else if (tokens[0].equals("count") && tokens.length == 1) {
                    System.out.println("There are " + executor.getCount() + " customers");
                } else if (tokens[0].equals("help") && tokens.length == 1) {
                    System.out.println(helpText);
                } else {
                    throw new IOException(commandError);
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
