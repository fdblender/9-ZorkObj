package test;

import static org.junit.Assert.*;

import java.util.LinkedList;



import org.junit.BeforeClass;
import org.junit.Test;
import Zork.*;

public class ZorkTest {
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ZorkApp.initializeGame();			
		ZorkApp.currentRoom = ZorkApp.listOfRooms.get(1);
		ZorkApp.updateRoom();
	}

	
	

	@Test
	public void Check() {
		ZorkApp.report.setUserActivity(new LinkedList<String>());
		ZorkApp.indexList =0;
		ZorkApp.updateReport("Testing Report");
		assertTrue(ZorkApp.report.getUserActivity().size() == 1);
		assertTrue(ZorkApp.report.getUserActivity().get(0).equals("Testing Report"));
		ZorkApp.indexList =0;
		ZorkApp.report.setUserActivity(new LinkedList<String>());
		
	}

}
