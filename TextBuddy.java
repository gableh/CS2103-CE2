import java.util.Scanner;
public class TextBuddy {
	private static final String MESSAGE_ERROR = "An unknown error has occured";
	public static String[] commandArray;
	public static String command;
	public static Controller controller;
	public static Scanner sc;
	public static void main(String[] args) {
		try{
			createController(args);
			createScanner();
			while (controller.getIsRunning()) {
				getCommand();
				parseCommand();
			}
		}catch (Exception e) {
			displayError();
		}
	}
	private static void displayError() {
		System.out.println(MESSAGE_ERROR);
	}
	private static void getCommand() {
		 command = sc.nextLine();
	}
	private static void createScanner() {
		sc = new Scanner(System.in);
	}
	private static void createController(String[] args) {
		controller = new Controller(args);
	}
	private static void parseCommand()
	{
		commandArray = command.split(" ");
	}

}
