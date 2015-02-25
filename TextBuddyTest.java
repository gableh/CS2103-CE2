import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TextBuddyTest {
	String[] args = new String[0];
	private Controller controller;
	@Before
	public void init(){
		controller = new Controller(args);
		controller.executeCommand("clear");
		controller.executeCommand("add g");
		controller.executeCommand("add h");
		controller.executeCommand("add a");
		controller.executeCommand("add b");
		controller.executeCommand("add d");
		controller.executeCommand("add c");
	}
	@Test
	public void testController() {
		assertEquals("test constructor","textbuddy.txt",controller.getFileName());
		assertEquals("test is running",true,controller.getIsRunning());
		
	}
	@Test
	public void testStorage(){
		Storage storage = new Storage("textbuddy.txt");
		assertEquals("test storage constructor",true,storage.getIsSuccessful());
	}
	@Test
	public void testSort(){
		controller.executeCommand("sort");
		Storage storage = new Storage("textbuddy.txt");
		assertEquals("test sort", "a",storage.getContents().get(0));

	}
	@Test
	public void testSearch(){
		controller.executeCommand("search b");
		Storage storage = new Storage("textbuddy.txt");
		assertEquals("test search", "4. b",storage.getSearchArray().get(0));

	}

}
