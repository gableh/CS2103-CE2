import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
public class Storage {
	private static final String MESSAGE_ADD ="added to %1$s: \"%2$s\"";
	private static final String MESSAGE_DELETE ="deleted from %1$s : \"%2$s\"";
	private static final String MESSAGE_CLEAR ="all content cleared from %1$s";
	private static final String MESSAGE_ERROR = "An unknown error has occurred";
	private static final String MESSAGE_NOTFOUND = "The following term: \"%1$s\" could not be found";
	private static final String MESSAGE_SORTED = "%1$s has been sorted";
	private static final String MESSAGE_FOUND = "Search Results";
	private static final String MESSAGE_EMPTY = "%1$s is empty";
	private File file;
	private boolean isSuccessful = true;
	private String toBeAdded;
	private String toBeDeleted;
	private ArrayList<String> contents = new ArrayList<String>();
	private static BufferedReader reader;
	private static BufferedWriter writer;
	private static ArrayList<String> searchArray;
	public Storage(String fileName){
		try {
			file = accessFile(fileName);
			reader = createReader(file);
			setContents();
		} catch (Exception e) {
			 isSuccessful = false;
		}
	}
	public ArrayList<String> getContents(){
		return contents;
	}
	private void setContents() throws IOException{
		String line;
		try{
			while ((line = reader.readLine())!= null) {
				contents.add(line);
			}
		} catch (Exception e) {
			displayError();
		} finally {

			closeReader(reader);
		}
	}
	private void displayError() {
		System.out.println(MESSAGE_ERROR);
	}
	public void add(String[] Commands) throws IOException{
		createLine(Commands);
		contents.add(toBeAdded);
		writeToFile();
		displayAddSuccess();
	}
	private void displayAddSuccess() {
		System.out.println(String.format(MESSAGE_ADD,file.getName(),toBeAdded.trim()));
	}
	private void writeToFile() throws IOException{
		writer = createWriter(file);
		for (int i = 0; i< contents.size();i++) {
			writeLineToFile(i);
			if (notLastLine(i)) {
				writer.newLine();
			}
		}
		closeStreams(writer,reader);
	}
	private void writeLineToFile(int i) throws IOException {
		writer.write(contents.get(i).trim());
	}

	private boolean notLastLine(int i){
		return i != contents.size()-1;
	}
	private void createLine(String[] Commands){
		toBeAdded = "";
		for (int i=1;i<Commands.length;i++) {
			toBeAdded=toBeAdded + Commands[i]+" ";
		}
	
	}
	public void delete(int lineNumber) throws IOException{
		toBeDeleted = contents.get(lineNumber -1);
		contents.remove(lineNumber -1);
		writeToFile();
		displayDeleteSuccess();

	}
	private void displayDeleteSuccess() {
		System.out.println(String.format(MESSAGE_DELETE,file.getName(),toBeDeleted));
	}
	public void clear() throws IOException{
		contents = new ArrayList<String>();
		writeToFile();
		displayClearSuccess();
	}
	private void displayClearSuccess() {
		System.out.println(String.format(MESSAGE_CLEAR,file.getName()));
	}
	public void display(){
		if(fileIsEmpty()){
			displayEmptyFile();
		}
		for (int i = 0; i < contents.size(); i++ ) {
			displayLine((i+1)+". "+ contents.get(i));
		}
	}
	private boolean fileIsEmpty() {
	    return contents.size()==0;
    }
	private void displayEmptyFile() {
	    System.out.println(String.format(MESSAGE_EMPTY,file.getName()));
    }
	public void display(ArrayList<String> list){
		for (int i = 0; i < list.size(); i++ ){
			displayLine(list.get(i));
		}
	}
	public void displayLine(String string){
		System.out.println(string);
	}

	public void sort(){
		Collections.sort(contents,String.CASE_INSENSITIVE_ORDER);
		try {
			writeToFile();
			displaySortSuccess();
		} catch (IOException e) {
			displayError();
		}
	}
	private void displaySortSuccess() {
		System.out.println(String.format(MESSAGE_SORTED,file.getName()));
	}
	public void search(String string){
		searchArray = new ArrayList<String>();
		for (int i = 0;i< contents.size();i++) {
			if (lineContainsString(string, i)) {
				addToSearchArray(i);
			}
		}
		if (searchArray.size() ==0) {
			displaySearchFail(string);
		} else {

			displaySearchSuccess();
			display(searchArray);
		}
	}
	private void addToSearchArray(int i) {
		searchArray.add((i+1)+". "+ contents.get(i));
	}
	private void displaySearchSuccess() {
		System.out.println(String.format(MESSAGE_FOUND));
	}
	private void displaySearchFail(String string) {
		System.out.println(String.format(MESSAGE_NOTFOUND,string));
	}
	private boolean lineContainsString(String string, int i) {
		return contents.get(i).contains(string);
	}
	public ArrayList<String> getSearchArray(){
		return searchArray;
	}

	public boolean getIsSuccessful(){
		return isSuccessful;
	}
	/**
	 * Attempts to access the file and return a file object
	 * @param args
	 * @return File
	 * @throws IOException
	 */
	private static File accessFile(String args) throws IOException {
		File file = new File(args);
		checkFileExists(file);
		return file;
	}
	/**
	 * Checks if the file exists,will create a new file if it does not.
	 * @param file
	 * @throws IOException
	 */
	private static void checkFileExists(File file) throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		}
	}
	/**
	 * Create a BufferedWriter from given file.
	 * @param file
	 * @return BufferedWriter
	 * @throws IOException
	 */
	private BufferedWriter createWriter(File file) throws IOException {
		return new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
	}
	/**
	 * Creates a bufferedReader from the input file.
	 * @param file
	 * @return BufferedReader
	 * @throws FileNotFoundException
	 */
	private BufferedReader createReader(File file)
			throws FileNotFoundException {
		return new BufferedReader(new FileReader(file));
	}
	/**
	 * Closes the Writer and Reader.
	 * @param writer
	 * @param reader
	 * @throws IOException
	 */
	private void closeStreams(BufferedWriter writer, BufferedReader reader)
			throws IOException {
		closeWriter(writer);
		closeReader(reader);
	
	}
	/**
	 * Helper method to close Reader
	 * @param reader
	 * @throws IOException
	 */
	private void closeReader(BufferedReader reader) throws IOException {
		if (reader!= null) {
			reader.close();
			reader = null;
		}
	}
	/**
	 * Helper method to close Writer.
	 * @param writer
	 * @throws IOException
	 */
	private void closeWriter(BufferedWriter writer) throws IOException {
		if (writer !=null) {
			writer.flush();
			writer.close();
			writer=null;
		}
	}
}
