package Homepage;

public class Friend extends User{
	private boolean friend;


	public Friend(String username, int id, boolean friend) {
		super(username,id);
		this.friend = friend;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the friend
	 */
	public boolean getFriend() {
		return friend;
	}

	/**
	 * @param friend the friend to set
	 */
	public void setFriend(boolean friend) {
		this.friend = friend;
	}
	
	
}
