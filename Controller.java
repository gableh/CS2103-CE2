import java.io.*;
public class Controller {
	private static final String DEFAULT_FILENAME = "textbuddy.txt";
	private static final String MESSAGE_NOQUERY = "Error, please enter a search query";
	private static final String MESSAGE_INVALIDCOMMAND = "Invalid Command.";
	private static final String MESSAGE_ERROR = "An unknown error has occurred";
	private static final String MESSAGE_GOODBYE = "Goodbye.";
	private static final String MESSAGE_NOFILE = "Since no input file has been specified,textbuddy.txt has been created for you";
	private static boolean isRunning;
	private int lineNumber;
	private static String fileName;
	public static String[] commandArray;
	private static final String MESSAGE_WELCOME ="Welcome to TextBuddy. %1$s is ready for use.";
	
	
	public Controller(String[] args){
		isRunning = true;
		setFileName(args);
		displayWelcome();
		
	}
	public void executeCommand(String command){
		Storage storage = new Storage(fileName);
		parseCommand(command);
		switch (getCommand()) {
			case "add":
				addTo(storage);
				break;
			case "delete":
				deleteLine(storage);
				break;
			case "clear":
				storage.clear();
				break;
			case "exit":
				exit();
				break;
			case "display":
				storage.display();
				break;
			case "sort":
				storage.sort();
				break;
			case "search":
				if(hasNoQuery()){
					displayNoQueryError();
				}else{
					search(storage);
				}
				break;
			default:
				displayInvalidCommand();
		}
	}
	private void addTo(Storage storage) {
	    try {
	        storage.add(commandArray);
        } catch (IOException e) {
        	displayError();
        }
    }
	private void search(Storage storage) {
	    storage.search(commandArray[1]);
    }
	private void displayNoQueryError() {
	    System.out.println(MESSAGE_NOQUERY);
    }
	private boolean hasNoQuery() {
	    return commandArray.length==1;
    }
	private void displayInvalidCommand() {
		System.out.println(MESSAGE_INVALIDCOMMAND);
	}
	private void deleteLine(Storage storage) {
		getLineNumber(commandArray[1]);
		storage.delete(lineNumber);
	}
	private void displayError() {
		System.out.println(MESSAGE_ERROR);
	}
	private void getLineNumber(String string){
		lineNumber = Integer.parseInt(string);
	}
	private void exit() {
		isRunning = false;
		displayMessage(MESSAGE_GOODBYE);
	}
	private String getCommand() {
		return commandArray[0].toLowerCase();
	}
	private void parseCommand(String command){
		commandArray = command.split(" ");
	}
	public boolean getIsRunning() {
		return isRunning;
	}
	private void displayWelcome() {
		System.out.println(String.format(MESSAGE_WELCOME, fileName));
	}
	
	private void setFileName(String[] args) {
		if (args.length == 0) {
			displayMessage(MESSAGE_NOFILE);
			setDefaultFileName();
		} else {
			setGivenFileName(args);
		}
	}
	private void setGivenFileName(String[] args) {
	    fileName = args[0];
    }
	private void setDefaultFileName() {
	    fileName = DEFAULT_FILENAME;
    }

	private void displayMessage(String message) {
		System.out.println(message);
	}
	public String getFileName(){
		return fileName;
	}
}
