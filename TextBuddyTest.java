import static org.junit.Assert.*;

import org.junit.Test;

public class TextBuddyTest {
	String[] args = new String[1];
	@Test
	public void testController() {
		Controller controller = new Controller(args);
		assertEquals("test constructor","textbuddy.txt",controller.getFileName());
		assertEquals("test is running",true,controller.getIsRunning());
		controller.executeCommand("clear");
		controller.executeCommand("add g");
		controller.executeCommand("add h");
		controller.executeCommand("add a");
		controller.executeCommand("add b");
		controller.executeCommand("add d");
		controller.executeCommand("add c");
		
	}
	@Test
	public void testStorage(){
		Storage storage = new Storage("textbuddy.txt");
		assertEquals("test storage constructor",true,storage.getIsSuccessful());


	}
	

}
