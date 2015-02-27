import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
	public void testAdd() {
		try {
	        Method storageAdd = Storage.class.getDeclaredMethod("add",String[].class);
			Storage storage = new Storage("textbuddy.txt");
			String testString = "add abc";
			String[] splitTestString = testString.split(" ");
			Object[] argObjects = {splitTestString}; 
	        storageAdd.setAccessible(true);
	        try {
	            storageAdd.invoke(storage, argObjects);
            } catch (IllegalAccessException e) {
	            e.printStackTrace();
            } catch (IllegalArgumentException e) {
	            e.printStackTrace();
            } catch (InvocationTargetException e) {
	            e.printStackTrace();
            }
			Storage storage1 = new Storage("textbuddy.txt");
			assertEquals("test add","abc",storage1.getContents().get(storage1.getContents().size()-1));
        } catch (NoSuchMethodException e) {
	        e.printStackTrace();
        } catch (SecurityException e) {
	        e.printStackTrace();
        }
	}
	@Test
	public void testDelete() {
		try {
	        Method storageAdd = Storage.class.getDeclaredMethod("delete",int.class);
			Storage storage = new Storage("textbuddy.txt");
			int testInt = 1;
			Object[] argObjects = {testInt}; 
	        storageAdd.setAccessible(true);
	        try {
	            storageAdd.invoke(storage, argObjects);
            } catch (IllegalAccessException e) {
	            e.printStackTrace();
            } catch (IllegalArgumentException e) {
	            e.printStackTrace();
            } catch (InvocationTargetException e) {
	            e.printStackTrace();
            }
			Storage storage1 = new Storage("textbuddy.txt");
			assertEquals("test delete","h",storage1.getContents().get(0));
        } catch (NoSuchMethodException e) {
	        e.printStackTrace();
        } catch (SecurityException e) {
	        e.printStackTrace();
        }
	}
	@Test
	public void testController() {
		assertEquals("test constructor","textbuddy.txt",controller.getFileName());
		assertEquals("test is running",true,controller.getIsRunning());
		
	}
	@Test
	public void testStorage() {
		Storage storage = new Storage("textbuddy.txt");
		assertEquals("test storage constructor",true,storage.getIsSuccessful());
	}
	@Test
	public void testSort() {
		controller.executeCommand("sort");
		Storage storage = new Storage("textbuddy.txt");
		assertEquals("test sort", "a",storage.getContents().get(0));

	}
	@Test
	public void testSearch() {
		controller.executeCommand("search b");
		Storage storage = new Storage("textbuddy.txt");
		assertEquals("test search", "4. b",storage.getSearchArray().get(0));

	}

}
