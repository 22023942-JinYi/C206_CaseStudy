package Homepage;

import Helper.Helper;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class HomePage{
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User u1 = new User(1, "jin", "password", true);
		User u2 = new User(2, "jake", "pass1", false);
		User u3 = new User(3, "fin", "pass2", false);
		User u4 = new User(4, "batman", "pass3", false);

		Friend f1 = new Friend("jake", 1, true);
		Friend f2 = new Friend("jin", 2, true);

		ArrayList<User> users = new ArrayList<User>();
		ArrayList<Friend> friends = new ArrayList<Friend>();

		friends.addAll(Arrays.asList(f1, f2));
		users.addAll(Arrays.asList(u1, u2, u3, u4));

		menu();
		int option = Helper.readInt("Choose your option: ");
		while (option != 3) {
			if (option == 1) {
				String uuser = Helper.readString("Enter your username: ");
				String password = Helper.readString("Enter your password: ");
				boolean found = false;
				for (User u : users) {
					if (u.getUsername().equals(uuser) && u.getPassword().equals(password) 
							&& u.getAdmin() == false) {
						int myid = u.getId();
						login(users, myid, friends, uuser);
						found = true;
						break;
					} else if (u.getUsername().equals(uuser) && u.getPassword().equals(password)
							&& u.getAdmin() == true) {
						int myid = u.getId();
						adminlogin(users, myid, friends, uuser);
						found = true;
						break;
					} else if (u.getUsername().equals(uuser) && !u.getPassword().equals(password)) {
						System.out.println("Your password is wrong. Please try again.");
						found = true;
						break;
					}
				}
				if (!found) {
					System.out.println("User not found.");
				}
			} else if (option == 2) {
				signup(users);
			}
			menu();
			option = Helper.readInt("Choose your option: ");
		}

	}

	public static void menu() {
		Helper.line(40, "=");
		System.out.println("Bikers Community Portal");
		Helper.line(40, "=");
		System.out.println("1. Log in");
		System.out.println("2. Sign up");
		System.out.println("3. Exit");
		Helper.line(40, "=");
	}

	public static void homemenu() {
		Helper.line(40, "=");
		System.out.println("User");
		Helper.line(40, "=");
		System.out.println("1. View all users");
		System.out.println("2. Friend list");
		System.out.println("3. Add Friend");
		System.out.println("4. Delete account");
		System.out.println("5. Bike");
		System.out.println("6. Discussion");
		System.out.println("7. Group");
		System.out.println("8. Event");
		System.out.println("9. Registration");
		System.out.println("10. Exit account");
		Helper.line(40, "=");
	}
	
	public static void adminmenu() {
		Helper.line(40, "=");
		System.out.println("Admin");
		Helper.line(40, "=");
		System.out.println("1. View all users");
		System.out.println("2. Delete user account");
		System.out.println("3. Exit administrator account");
		Helper.line(40, "=");
	}

	public static void signup(ArrayList<User> users) {
		Random random = new Random();
		int x = random.nextInt(99) + 1;

		String user = Helper.readString("Enter your username: ");
		boolean usernameExists = false;
		for (User u : users) {
			if (u.getUsername().equals(user)) {
				usernameExists = true;
				break;
			}
		}

		if (!usernameExists) {
			String password = Helper.readString("Enter your password: ");
			users.add(new User(x, user, password, false));
		} else {
			System.out.println("Choose another username.");
		}
	}

	public static void login(ArrayList<User> users, int myid, ArrayList<Friend> friends, String uuser) {
	    int option;
	    do {
	        homemenu();
	        option = Helper.readInt("Enter option: ");
	        if (option == 1) {
	            Helper.line(40, "-");
	            System.out.println("Users");
	            Helper.line(40, "-");
	            for (User u : users) {
	            	System.out.println(u.getUsername());
	            }
	        }
	        else if (option == 2) {
	            Helper.line(40, "-");
	            System.out.println("Friend List");
	            Helper.line(40, "-");
	            for (User u : users) {
	                for (Friend f : friends) {
	                    if (myid == f.getId() && u.getUsername().equals(f.getUsername()) && u.getId()!= myid && f.getFriend() == true) {
	                    		System.out.println(u.getUsername());
	                    		break;
		                    }
	                }
	            }
	        }
	        else if(option == 3) {
	        	Helper.line(40, "-");
	            System.out.println("Add Friend");
	            Helper.line(40, "-");
	            String user = Helper.readString("Search username: ");
	            char choose = Helper.readChar("Add user as friend? (y/n)");
	            boolean friendAdded = false;
	            if (choose == 'y') {
	                for (User u : users) {
	                    if (user.equalsIgnoreCase(u.getUsername())) {
	                        for (Friend f : friends) {
	                            if (f.getId() != myid) {
	                                friends.add(new Friend(u.getUsername(), myid, true));
	                                friends.add(new Friend(uuser, u.getId(), true));
	                                friendAdded = true; // Update the flag to indicate a friend has been added
	                                break;
	                            }
	                        }
	                    }
	                }
	            } else if (choose == 'n') {
	                System.out.println("Did not add as a friend");
	            } else {
	                System.out.println("Invalid input");
	            }

	            if (friendAdded) {
	                System.out.println("Friend added");
	            } else {
	                System.out.println("There is no such user");
	            }
	    
	        }
	        else if (option == 4) {
	            char choose = Helper.readChar("Delete Account? (y/n)");
	            int uindex = -1; // Initialize index to -1
	            int findex = -1; // Initialize index to -1

	            if (choose == 'y') {
	                // Find the index of the user to remove
	                for (int i = 0; i < users.size(); i++) {
	                    if (users.get(i).getUsername().equals(uuser)) {
	                        uindex = i;
	                        break;
	                    }
	                }

	                if (uindex > 0) {
	                    // Remove the user from users list
	                    users.remove(uindex);
	                    System.out.println("User deleted");

	                    // Find and remove friends associated with the deleted user
	                    for (int i = friends.size() - 1; i >= 0; i--) {
	                        Friend f = friends.get(i);
	                        if ((f.getUsername().equals(uuser) && f.getFriend()) || (f.getId() == myid && f.getFriend())) {
	                            friends.remove(i);
	                            option = 5;
	                            break;
	                        }
	                    }
	                }
	                else {
	                    System.out.println("User not found");
	                }
	            } else if (choose == 'n') {
	                System.out.println("It is not deleted");
	            } else {
	                System.out.println("Invalid input");
	            }
	        }
	        else if (option == 5) {//bike
	        	
	        }
	        else if (option == 6) {//discussion
	        	
	        }
	        else if (option == 7) {//group
	        	
	        }
	        else if (option == 8) {//event
	        	
	        }
	        else if (option == 9) {//registration
	        	
	        }
	    } while (option != 10);
	}

	public static void adminlogin(ArrayList<User> users, int myid, ArrayList<Friend> friends, String uuser) {
		int option;
	    do {
	        adminmenu();
	        option = Helper.readInt("Enter option: ");
	        if (option == 1) {
	        	Helper.line(40, "-");
	            System.out.println("Users");
	            Helper.line(40, "-");
	            for (User u : users) {
	            	System.out.println(u.getUsername());
	            }
	        }
	        else if (option == 2) {
	            String user = Helper.readString("Search username: ");
	            char choose = Helper.readChar("Delete user? (y/n)");

	            if (choose == 'y') {
	                // Temporary lists to store users and friends to be removed
	                ArrayList<User> usersToRemove = new ArrayList<>();
	                ArrayList<Friend> friendsToRemove = new ArrayList<>();

	                for (User u : users) {
	                    if (user.equalsIgnoreCase(u.getUsername())) {
	                        usersToRemove.add(u);

	                        for (Friend f : friends) {
	                            if (f.getId() == u.getId() || f.getUsername().equals(u.getUsername())) {
	                                friendsToRemove.add(f);
	                            }
	                        }
	                    }
	                }

	                if (!usersToRemove.isEmpty()) {
	                    // Remove users
	                    users.removeAll(usersToRemove);
	                    System.out.println("User deleted");

	                    // Remove friends
	                    friends.removeAll(friendsToRemove);
	                    System.out.println("User's friend list is deleted");
	                } else {
	                    System.out.println("There is no such user");
	                }
	            } else if (choose == 'n') {
	                System.out.println("User Account is not deleted");
	            } else {
	                System.out.println("Invalid input");
	            }
	        }
	    } while (option != 3);
	}

	}

