import java.io.*;
public class Storage {
	private boolean isSuccessful = true;
	public Storage(String fileName){
		try {
			File file = accessFile(fileName);
			BufferedWriter writer = createWriter(file);
			BufferedReader reader = createReader(file);
		} catch (Exception e) {
			 isSuccessful = false;
		}
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
		return new BufferedWriter(new FileWriter(file.getAbsoluteFile(),true));
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
