import java.util.ArrayList;
import java.util.List;

public class Report {
	private List<String> userActivity = new ArrayList<String>();
	private double totalWinnings=0;
	private int roomCount=0;
	public List<String> getUserActivity() {
		return userActivity;
	}
	public void setUserActivity(List<String> userActivity) {
		this.userActivity = userActivity;
	}
	public double getTotalWinnings() {
		return totalWinnings;
	}
	public void setTotalWinnings(double totalWinnings) {
		this.totalWinnings = totalWinnings;
	}
	public int getRoomCount() {
		return roomCount;
	}
	public void setRoomCount(int roomCount) {
		this.roomCount = roomCount;
	}
	
	
	
	

}
