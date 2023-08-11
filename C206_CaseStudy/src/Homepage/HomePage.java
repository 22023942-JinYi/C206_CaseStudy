package Homepage;

import Helper.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HomePage {

	private static final int ADD_GROUP = 1;
	private static final int VIEW_GROUP = 2;
	private static final int DELETE_GROUP = 3;
	private static final int ADD_EVENT = 1;
	private static final int VIEW_EVENT = 2;
	private static final int DELETE_EVENT = 3;
	private static final int ADD_DIS = 1;
	private static final int VIEW_DIS = 2;
	private static final int DELETE_DIS = 3;
	private static final int ADD_BIKE = 1;
	private static final int VIEW_BIKE = 2;
	private static final int DELETE_BIKE = 3;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User u1 = new User(1, "jin", "password", true);
		User u2 = new User(2, "jake", "password1", false);
		User u3 = new User(3, "fin", "password2", false);
		User u4 = new User(4, "batman", "password3", false);

		Friend f1 = new Friend("jake", 1, true);
		Friend f2 = new Friend("jin", 2, true);

		// Sample Data

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
				checklogin(uuser,password,users,friends);
			}  else if (option == 2) {
				String pattern = ".{8,}";
				String user = Helper.readString("Enter your username: ");
				String password = Helper.readStringRegEx("Enter your password: ", pattern);
				signup(users, user, password);
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

	public static void eventmenu() {
		Helper.line(40, "=");
		System.out.println("Event");
		Helper.line(40, "=");
		System.out.println("1. Add event");
		System.out.println("2. View event");
		System.out.println("3. Delete event");
		System.out.println("4. Exit");
		Helper.line(40, "=");
	}

	public static void bikemenu() {
		Helper.line(40, "=");
		System.out.println("Bike");
		Helper.line(40, "=");
		System.out.println("1. Add Bike");
		System.out.println("2. View Bike");
		System.out.println("3. Delete Bike");
		System.out.println("4. Exit");
		Helper.line(40, "=");
	}

	public static void dismenu() {
		Helper.line(40, "=");
		System.out.println("Discussion");
		Helper.line(40, "=");
		System.out.println("1. Add Discussion");
		System.out.println("2. View Discussion");
		System.out.println("3. Delete Discussion");
		System.out.println("4. Exit");
		Helper.line(40, "=");
	}

	public static void groupmenu() {
		Helper.line(40, "=");
		System.out.println("Group");
		Helper.line(40, "=");
		System.out.println("1. Add Group");
		System.out.println("2. View Group");
		System.out.println("3. Delete Group");
		System.out.println("4. Exit");
		Helper.line(40, "=");
	}

	public static void regmenu() {
		Helper.line(40, "=");
		System.out.println("Registration");
		Helper.line(40, "=");
		System.out.println("1. Add Registration");
		System.out.println("2. View Registration");
		System.out.println("3. Delete Registration");
		System.out.println("4. Exit");
		Helper.line(40, "=");
	}
	public static void checklogin(String uuser, String password, ArrayList<User> users, ArrayList<Friend> friends) {
		boolean found = false;
		for (User u : users) {
			if (u.getUsername().equals(uuser) && u.getPassword().equals(password) && u.getAdmin() == false) {
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
	}
	
	public static void adduser(ArrayList<User> users, User u) {
		users.add(u);
	}
	
	public static void signup(ArrayList<User> users, String user, String password) {
		Random random = new Random();
		int x = random.nextInt(99) + 1;
		String pattern = ".{8,}";
		
		if (password.matches(pattern)) {
			boolean usernameExists = false;
			for (User u : users) {
				if (u.getUsername().equals(user)) {
					usernameExists = true;
					break;
				}
			}

			if ((!usernameExists) && password.matches(pattern)) {
				User u = (new User(x, user, password, false));
				adduser(users, u);
				System.out.println("Account is created");
			}
			else {
				System.out.println("Choose another username.");
			}
		}
		else {
			System.out.println("Password must have minimum 8 characters");
		}
		
		
	}
	public static void deleteuser(ArrayList<User> users, int uindex) {
		// Remove the user from users list
		users.remove(uindex);
		System.out.println("User deleted");
	}
	public static String viewUsers(ArrayList<User> users) {
		String output = "";
	    for (User u : users) {
	    	output += u.getUsername() + "\n";
	    }
	    return output;
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
				System.out.println(viewUsers(users));
			} else if (option == 2) {
				Helper.line(40, "-");
				System.out.println("Friend List");
				Helper.line(40, "-");
				for (User u : users) {
					for (Friend f : friends) {
						if (myid == f.getId() && u.getUsername().equals(f.getUsername()) && u.getId() != myid
								&& f.getFriend() == true) {
							System.out.println(u.getUsername());
							break;
						}
					}
				}
			} else if (option == 3) {
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

			} else if (option == 4) {
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
						deleteuser(users, uindex);

						// Find and remove friends associated with the deleted user
						for (int i = friends.size() - 1; i >= 0; i--) {
							Friend f = friends.get(i);
							if ((f.getUsername().equals(uuser) && f.getFriend())
									|| (f.getId() == myid && f.getFriend())) {
								friends.remove(i);
								option = 10;
								break;
							}
						}
					} else {
						System.out.println("User not found");
					}
				} else if (choose == 'n') {
					System.out.println("It is not deleted");
				} else {
					System.out.println("Invalid input");
				}
			} else if (option == 5) {// bike
				ArrayList<Bike> bikeList = new ArrayList<Bike>();
				bikemenu();
				int opt = Helper.readInt("Choose your option: ");
				while (opt != 4) {
					if (opt == ADD_BIKE) {
						
						Bike.addBike(bikeList,Bike.inputBike());
					} else if (opt == VIEW_BIKE) {
						Bike.viewAllBike(bikeList);
					} else if (opt == DELETE_BIKE) {
						Bike.deleteBike(bikeList,Bike.inputdelete(),Bike.confirmDelete());
					} else {
						System.out.println("Invalid input");
					}
					// Prompt for the next option within the loop
					bikemenu();
					opt = Helper.readInt("Choose your option: ");
				}
				if (opt == 4) {
					System.out.println("See you next time!!");
				}

			} else if (option == 6) {// discussion
				ArrayList<Discussion> discussionList = new ArrayList<Discussion>();
				dismenu();
				int opt = Helper.readInt("Choose your option: ");
				while (opt != 4) {
					if (opt == ADD_DIS) {
						Discussion.addDiscussion(discussionList, Discussion.inputDiscussion());
					} else if (opt == VIEW_DIS) {
						Discussion.viewAllDiscussion(discussionList);
					} else if (opt == DELETE_DIS) {
						Discussion.deleteDiscussion(discussionList, Discussion.inputDeleteDiscussion(), Discussion.inputConfirmation());
					} else {
						System.out.println("Invalid input");
					}
					// Prompt for the next option within the loop
					dismenu();
					opt = Helper.readInt("Choose your option: ");
				}
				if (opt == 4) {
					System.out.println("See you next time!!");
				}
				if (opt == 4) {
					System.out.println("See you next time!!");
				}

			} else if (option == 7) {// group
				ArrayList<Group> groupList = new ArrayList<Group>();
				groupmenu();
				int opt = Helper.readInt("Choose your option: ");
				while (opt != 4) {
					if (opt == ADD_GROUP) {
						Group.addGroup(groupList);
					} else if (opt == VIEW_GROUP) {
						Group.viewAllGroup(groupList);
					} else if (opt == DELETE_GROUP) {
						Group.deleteGroup(groupList);
					} else {
						System.out.println("Invalid input");
					}
					// Prompt for the next option within the loop
					groupmenu();
					opt = Helper.readInt("Choose your option: ");
				}
				if (opt == 4) {
					System.out.println("See you next time!!");
				}

			} else if (option == 8) {// event
				Event e1 = new Event(1, "HI", "Taman U", LocalDate.of(2022, 8, 8), 2, "You");
				Event e2 = new Event(2, "BYE", "Taman P", LocalDate.of(2022, 10, 23), 5, "Are");
				Event e3 = new Event(3, "HELLO", "Taman E", LocalDate.of(2022, 12, 22), 7, "Wrong");
				// Initialize Array List
				ArrayList<Event> eventList = new ArrayList<Event>();
				eventList.addAll(Arrays.asList(e1, e2, e3));
				eventmenu();
				int opt = Helper.readInt("Choose your option: ");
				while (opt != 4) {
					if (opt == ADD_EVENT) {
						Event.addEvent(eventList,Event.inputevent());
					} else if (opt == VIEW_EVENT) {
						Event.viewAllEvent(eventList);
					} else if (opt == DELETE_EVENT) {
						Event.deleteEvent(eventList,Event.iptEvtDel(),Event.confEvtDel());
					} else {
						System.out.println("Invalid input");
					}
					// Prompt for the next option within the loop
					eventmenu();
					opt = Helper.readInt("Choose your option: ");
				}
				if (opt == 4) {
					System.out.println("See you next time!!");
				}

			} else if (option == 9) {// registration
		        ArrayList<Registration> registrationsList = new ArrayList<Registration>();
		        ArrayList<Event> eventList = new ArrayList<Event>();
		        
		        regmenu();
		        int opt = Helper.readInt("Choose your option: ");
		        while (opt != 4) {
		          if (opt == 1) {

		        	    int id = Helper.readInt("Enter event id  > ");
		        	  String name = Helper.readString("Enter event name > ");
		        	  Registration newEvent = new Registration(id, name);
		        	  
		            Registration.addEventRegistrations(registrationsList, newEvent);
		          } else if (opt == 2) {
		            Registration.displayAllRegistration(registrationsList);
		          } else if (opt == 3) {
		        	int registeredEventID = Helper.readInt("Enter the registeredEventID to delete: ");
		        	char confirm = 'n';
		            Registration.deleteEventRegistration(registrationsList,registeredEventID , confirm);
		          } else {
		            System.out.println("Invalid input");
		          }
		          // Prompt for the next option within the loop
		          regmenu();
		          opt = Helper.readInt("Choose your option: ");
		        }if (opt == 4) {
		            System.out.println("See you next time!!");
		          }
		        

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
				System.out.println(viewUsers(users));
			} else if (option == 2) {
				String user = Helper.readString("Search username: ");
				char choose = Helper.readChar("Delete user? (y/n)");
				admindeleteuser(users, friends, user, choose);
				
			}
		} while (option != 3);
	}
	public static void admindeleteuser(ArrayList<User> users,ArrayList<Friend> friends,  String user, char choose) {
		if (choose == 'y') {
			// Temporary lists to store users and friends to be removed
			ArrayList<User> usersToRemove = new ArrayList<>();
			ArrayList<Friend> friendsToRemove = new ArrayList<>();
	
			for (User u : users) {
				if (user.equals(u.getUsername())) {
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

}