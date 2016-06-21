


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class ZorkObj {

	static Random rnd = new Random();
	public static void main(String[] args) {
		FileWriter fwr = null;
		BufferedWriter bwr = null;
		Scanner in = null;
		try {
			File file = new File("zorkHistory.txt");
			file.createNewFile();
			fwr = new FileWriter(file, true);
			bwr = new BufferedWriter(fwr);
			bwr.write("ZorkHistory\n");
			String[][] zork = new String[3][3];

			zork[0][0] = "5,dining room,dusty emptybox,50";
			zork[0][1] = "6,vault,3 walking skeletons,100";
			zork[0][2] = "7,parlor,treasurechest,600";
			zork[1][0] = "3,library,spiders,800";
			zork[1][1] = "2,front room,piano,0";
			zork[1][2] = "4,kitchen,bats,1000";
			zork[2][0] = "0";
			zork[2][1] = "1,foyer,dead scorpion,10";
			zork[2][2] = "0";

			in = new Scanner(System.in);
			System.out.println("You are standing in the foyer of an old house.");
			System.out.println("You see a dead scorpion.");
			System.out.println("{You can (1)exit to the north or press Q to quit or type History to get history}");
			String temp = in.next();
			int i = 1;
			int j = 1;
			boolean special = false;
			int total = 0;
			int rooms = 1;
			Random rnd = new Random();
			int chor = rnd.nextInt(7) + 1;
			int lamp = rnd.nextInt(7) + 1;
			boolean lampFlag = false;
			while (!temp.equals("Q")) {
				String vals[] = zork[i][j].split(",");
				String currentRoom = vals[0];
				total += Integer.parseInt(vals[3]);

				if (currentRoom.equals("" + chor)) {
					total = 0;
					System.out.println("You met chor and Lost all your winnings!");
					chor = rnd.nextInt(8) + 1;
					bwr.write("Room Visited " + vals[0] + " and Winnings: 0 \n");
				} else {
					bwr.write("Room Visited " + vals[0] + " and Winnings: " + Integer.parseInt(vals[3]) + "\n");
				}

				zork[i][j] = vals[0] + "," + vals[1] + "," + vals[2] + "," + "0";
				if (!special) {
					System.out.println("You are standing in the " + vals[1] + ".");
					System.out.println("You see a " + vals[2] + ".");
				} else {
					System.out.println("You are standing in the secret room.");
					System.out.println("You see piles of gold.");
					special = false;
				}
				System.out.println("Winnings so far: " + total);
				Map<String, String> options = new HashMap<String, String>();
				System.out.print("{You can ");
				int k = 0;
				if (currentRoom.equals("6")) {
					int randomInt = rnd.nextInt(4);
					if (randomInt == 3) {
						special = true;
					}
					j = j + 1;
					System.out.print("(1)exit to the east");

				} else {
					if ((i - 1) >= 0) {
						vals = zork[i - 1][j].split(",");
						if ((!vals[0].equals("6") || currentRoom.equals("7")) && !vals[0].equals("0")) {
							System.out.print("(" + k + ")exit to the north");
							options.put("" + k, (i - 1) + "," + j);
							k++;
						}

					}

					if ((i + 1) <= 2) {
						vals = zork[i + 1][j].split(",");
						if ((!vals[0].equals("6") || currentRoom.equals("7")) && !vals[0].equals("0")) {
							System.out.print("(" + k + ")exit to the south");
							options.put("" + k, (i + 1) + "," + j);
							k++;
						}

					}
					if ((j - 1) >= 0) {
						vals = zork[i][j - 1].split(",");
						if ((!vals[0].equals("6") || currentRoom.equals("7")) && !vals[0].equals("0")) {

							System.out.print("(" + k + ")exit to the west");
							options.put("" + k, (i) + "," + (j - 1));
							k++;
						}

					}
					if ((j + 1) <= 2) {
						vals = zork[i][j + 1].split(",");
						if ((!vals[0].equals("6") || currentRoom.equals("7")) && !vals[0].equals("0")) {
							System.out.print("(" + k + ")exit to the east");
							options.put("" + k, (i) + "," + (j + 1));
							k++;
						}

					}
				}
				if (currentRoom.equals(""+lamp)) {
					System.out.print(" or press GetLamp");
					
				} 
				if (currentRoom.equals(""+5) && lampFlag) {
					System.out.print(" or press GetCard ");
				} else if (currentRoom.equals(""+3) && lampFlag) {
					System.out.print(" or press ReadScroll ");
				}

				System.out.println(" or press Q to quit or type History to get history}");
				temp = in.next();

				if (!temp.equals("Q") && !temp.equals("History") && !temp.equals("GetLamp") 
						&& !temp.equals("ReadScroll") && !temp.equals("GetCard")) {
					System.out.println(temp);
					rooms++;
					if (!currentRoom.equals("6")) {
						String[] indexes = options.get(temp).split(",");
						i = Integer.parseInt(indexes[0]);
						j = Integer.parseInt(indexes[1]);
					}

				} else if (temp.equals("Q")) {
					break;
				} else if (temp.equals("GetLamp")) {
					lampFlag = true;
					bwr.write("You got a Lamp\n");
					String update[] = zork[0][0].split(",");
					zork[0][0] = update[0] + "," + update[1] + "," + " a dusty box with a gift card" + "," + update[3];
					update = zork[0][1].split(",");
					zork[0][1] = update[0] + "," + update[1] + "," + update[2] + " and bats" + "," + update[3];
					update = zork[0][2].split(",");
					zork[0][2] = update[0] + "," + update[1] + "," + update[2] + " and SRK portrait with tickets to Conjuring2" + "," + update[3];
					update = zork[1][0].split(",");
					zork[1][0] = update[0] + "," + update[1] + "," + update[2] + " and a scroll on the wall" + "," + update[3];
					update = zork[1][1].split(",");
					zork[1][1] = update[0] + "," + update[1] + "," + update[2] + " and sheet music for Blank Space" + "," + update[3];
					update = zork[1][2].split(",");
					zork[1][2] = update[0] + "," + update[1] + "," + update[2] + " and refrigerator full of sweets and ice cream" + "," + update[3];
					update = zork[2][1].split(",");
					zork[2][1] = update[0] + "," + update[1] + "," + update[2] + " and a spider web of gold and silver" + "," + update[3];
				} else if (temp.equals("GetCard")) {
					bwr.write("Yot got gift card\n");
				} else if (temp.equals("ReadScroll")) {
					j = 2;
					i = 0;
					special = true;
				} else {
					bwr.flush();
					bwr.close();
					history(file);
					fwr = new FileWriter(file, true);
					bwr = new BufferedWriter(fwr);
				}

			}
			System.out.println("Sorry to see you go!");
			System.out.println("Winnings: " + total + " and Total Rooms: " + rooms);
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("I/O Exception");
		} catch (NullPointerException e) {
			System.out.println("Null Exception");
		} catch (ArithmeticException e) {
			System.out.println("Arithematic Exception");
		} catch (Exception e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		} finally {
			in.close();
			try {
				bwr.flush();
				bwr.close();
			} catch (IOException e) {
				System.out.println("I/O Exception");
			} catch (Exception e) {
				System.out.println("Something went wrong");
			}
			
		}
	}

	private static void history(File file) {
		BufferedReader br = null;
		try {
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			

		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				System.out.println("I/O Exception");
			} catch (Exception e) {
				System.out.println("Something went wrong");
			}
		}

	}

}



