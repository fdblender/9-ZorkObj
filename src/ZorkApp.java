
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class ZorkApp {
	private static Map<Integer, ZorkRooms> listOfRooms;
	static int thief;
	static int lamp;
	static boolean special = false;
	static boolean lampFound = false;
	static ZorkRooms currentRoom;
	static Scanner in;
	static char temp;
	static Report report;
	static List<String> userActivity ;
	static int indexList =0;
	static Random rnd;
	public static void main(String[] args) {
		initializeGame();
		report = new Report();
		userActivity = report.getUserActivity();
		currentRoom = listOfRooms.get(1);
		report.setTotalWinnings(currentRoom.getMoney());
		report.setRoomCount(1);
		userActivity.add(indexList,"Room Visited " + 1 + " and Winnings: " + report.getTotalWinnings());
		report.setUserActivity(userActivity);
		indexList++;
		System.out.println("You are standing in the foyer of an old house.");
		System.out.println("You see a dead scorpion.");
		System.out.println("{You can exit to the north 'N' or south 'S' or west 'W' or east'E' or type 'Q' quit or type 'H' to get history}");
		in = new Scanner(System.in);
		temp = in.next().toCharArray()[0];
		Random rnd = new Random();
		thief = rnd.nextInt(7) + 1;
		lamp = rnd.nextInt(7) + 1;
		while (true) {
			if (temp == 'N' || temp == 'S' || temp == 'W' || temp == 'E' || temp == 'R') {
				
				
				process();
			} else if (temp == 'Q'){
				break;
			} else if (temp == 'H') {
				history();
				System.out.println("{You can exit to the north 'N' or south 'S' "
						+ "or west 'W' or east'E' or type 'Q' quit or type 'H' to get history}");
				temp = in.next().toCharArray()[0];
				process();
			}
			
		}
		

	}
	public static void process() {
		Integer nextRoom;
		if (temp == 'R') {
			nextRoom =8;
		} else {
			nextRoom = currentRoom.getGoesTo().get(temp);
		}
		
		if (nextRoom == null) {
			System.out.println("Press some other direction");
			temp = in.next().toCharArray()[0];
			
		} else {
			if ((currentRoom.getNum() == 6 && special) || (currentRoom.getNum() == 3 && special && lampFound)) {
				nextRoom = 8;
				special = false;
			}
			currentRoom = listOfRooms.get(nextRoom);
			
			if (nextRoom == thief) {
				report.setTotalWinnings(0);
			} else {
				report.setTotalWinnings(report.getTotalWinnings() + currentRoom.getMoney());
			}
			userActivity = report.getUserActivity();
			userActivity.add(indexList,"Room Visited " + nextRoom + " and Winnings: " + report.getTotalWinnings());
			report.setUserActivity(userActivity);
			indexList++;
			System.out.println("You are standing in the "+ currentRoom.getName());
			System.out.println("You see " + currentRoom.getDesc());
			if (nextRoom == lamp && !lampFound) {
				System.out.println("{You can exit to the north 'N' or south 'S' "
						+ "or west 'W' or east'E' or type 'Q' quit or type 'H' to get history or type 'L' to get lamp}");
			} else if (lampFound && nextRoom == 3) {
				System.out.println("{You can exit to the north 'N' or south 'S' "
						+ "or west 'W' or east'E' or type 'Q' quit or type 'H' "
						+ "to get history or type 'L' to get lamp or type 'R' to read scroll}");
			} else {
				System.out.println("{You can exit to the north 'N' or south 'S' "
						+ "or west 'W' or east'E' or type 'Q' quit or type 'H' to get history}");
			}
			
			if (nextRoom == 6) {
				int randomInt = rnd.nextInt(4);
				if (randomInt == 3) {
					special = true; 
					temp = 'E';
				}
			} else {
				temp = in.next().toCharArray()[0];
			}
			
		}
		if (temp == 'L') {
			updateDescription();
			userActivity = report.getUserActivity();
			userActivity.add("Lamp Found");
			report.setUserActivity(userActivity);
			indexList++;
			lampFound = true;
			if (nextRoom == 3) {
				System.out.println("{You can exit to the north 'N' or south 'S' "
						+ "or west 'W' or east'E' or type 'Q' quit or type 'H' to get history"
						+ " or read scroll 'R'}");
			} else {
				System.out.println("{You can exit to the north 'N' or south 'S' "
						+ "or west 'W' or east'E' or type 'Q' quit or type 'H' to get history}");
			}
			temp = in.next().toCharArray()[0];
			
		} 
		if (temp == 'R') {
			special = true;
			
		} 
	}

	private static void history() {
		for(String out: report.getUserActivity()) {
			System.out.println(out);
		}
		System.out.println("Winnings: " + report.getTotalWinnings());
		System.out.println("Rooms visited: " + report.getRoomCount());
	}

	private static void updateDescription() {
		listOfRooms.get(1).setDesc(listOfRooms.get(1).getDesc() + " and a spider web of gold and silver");
		listOfRooms.get(2).setDesc(listOfRooms.get(2).getDesc() + " and sheet music for Blank Space");
		listOfRooms.get(3).setDesc(listOfRooms.get(3).getDesc() + " and a scroll on the wall");
		listOfRooms.get(4).setDesc(listOfRooms.get(4).getDesc() + " and refrigerator full of sweets and ice cream");
		listOfRooms.get(5).setDesc("a dusty box with a gift card");
		listOfRooms.get(6).setDesc(listOfRooms.get(6).getDesc() + " and bats");
		listOfRooms.get(7).setDesc(listOfRooms.get(7).getDesc() + " and SRK portrait with tickets to Conjuring2");
		
		
	}

	private static void initializeGame() {
		listOfRooms = new HashMap<Integer, ZorkRooms>();
		Random rnd = new Random();
		Map<Character, Integer> goesTo = new HashMap<Character, Integer>();
		goesTo.put('S', 3);
		ZorkRooms zr1 = new ZorkRooms(5,"dining room","dusty emptybox",rnd.nextInt(1000),goesTo);
		listOfRooms.put(5,zr1);
		
		goesTo = new HashMap<Character, Integer>();		
		goesTo.put('E', 7);		
		ZorkRooms zr2 = new ZorkRooms(6,"vault","3 walking skeletons",rnd.nextInt(1000),goesTo);
		listOfRooms.put(6,zr2);
		
		goesTo = new HashMap<Character, Integer>();
		goesTo.put('W', 6);
		goesTo.put('S', 4);		
		ZorkRooms zr3 = new ZorkRooms(7,"parlor", "treasurechest",rnd.nextInt(1000),goesTo);
		listOfRooms.put(7, zr3);
		
		goesTo = new HashMap<Character, Integer>();
		goesTo.put('E', 2);
		goesTo.put('N', 5);		
		ZorkRooms zr4 = new ZorkRooms(3,"library", "spiders,",rnd.nextInt(1000),goesTo);
		listOfRooms.put(3, zr4);
		
		goesTo = new HashMap<Character, Integer>();
		goesTo.put('S', 1);
		goesTo.put('W', 3);	
		goesTo.put('E', 4);
		ZorkRooms zr5 = new ZorkRooms(2,"front room", "piano,",rnd.nextInt(1000),goesTo);
		listOfRooms.put(2, zr5);
		
		goesTo = new HashMap<Character, Integer>();
		goesTo.put('W', 2);
		goesTo.put('N', 7);			
		ZorkRooms zr6 = new ZorkRooms(4,"kitchen", "bats",rnd.nextInt(1000),goesTo);		
		listOfRooms.put(4, zr6);
		
		goesTo = new HashMap<Character, Integer>();
		goesTo.put('N', 2);			
		ZorkRooms zr7 = new ZorkRooms(1,"foyer", "dead scorpion",rnd.nextInt(1000),goesTo);
		listOfRooms.put(1, zr7);
		
		goesTo = new HashMap<Character, Integer>();
		goesTo.put('W', 6);			
		ZorkRooms zr8 = new ZorkRooms(8,"secret room", "piles of gold",rnd.nextInt(1000),goesTo);
		listOfRooms.put(8, zr8);
		
	}

}
