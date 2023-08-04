package UserAccess;

import Helper.Helper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class HomePage{
	

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			User u1 = new User(1, "jin", "BananaJY123", true);
			User u2 = new User(2, "jake", "hmmjake123", false);

			Friend f1 = new Friend("jake", 1, true);
			Friend f2 = new Friend("jin", 2, true);

			ArrayList<User> users = new ArrayList<User>();
			ArrayList<Friend> friends = new ArrayList<Friend>();

			friends.addAll(Arrays.asList(f1, f2));
			users.addAll(Arrays.asList(u1, u2));

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
			System.out.println("1. View all users");
			System.out.println("2. Add Friend");
			System.out.println("3. Delete account");
			System.out.println("4. Exit account");
			Helper.line(40, "=");
		}
		
		public static void adminmenu() {
			Helper.line(40, "=");
			System.out.println("Admin");
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
		            	boolean isFriend = false;
		                for (Friend f : friends) {
		                    if (myid == f.getId()) {
		                    if (u.getId()!= myid) {
		                   	if(f.getFriend() == true) {
		                        System.out.println((u.getUsername()) + " (Friend) ");
		                        isFriend = true;
		                        break;
		                    	}
		                    }
		                    }
		                }
		                if (isFriend == false) {
		                    System.out.println(u.getUsername());
		                }
		            }
		        }
		        else if(option == 2) {
		        	Helper.line(40, "-");
		            System.out.println("Delete User Account");
		            Helper.line(40, "-");
		        	for (User u : users) {
		        		String user = Helper.readString("Search username: ");
		        		if (user.equalsIgnoreCase(u.getUsername())) {
		        			char choose = Helper.readChar("Delete user? (y/n)");
		        			if (choose == 'y') {
		        				for (Friend f : friends) {
		        					if(f.getId() != myid) {
				        				friends.add(new Friend(u.getUsername(),myid,true));
				        				friends.add(new Friend(uuser,u.getId(),true));
				        				System.out.println("Friend added");
				        				break;
		        					}
		        					else {
		        						System.out.println("Aready added as friend");
		        					}
		        				}
		        			}
		        			else if(choose == 'n') {
		        				System.out.println("Did not add as friend");
		        			}
		        			else {
		    	        		System.out.println("Invalid input");
		    	        	}
		        		}
		        		else {
		        			System.out.println("There is no such user");
		        		}
		        	}
		        }
		        else if(option == 3) {
		        	char choose = Helper.readChar("Delete Account? (y/n)");
		        	if(choose == 'y') {
		        		for (User u : users) {
		        			users.remove(u);
		        			System.out.println("Account deleted");
		        			break;
		        		}
		        	}
		        	else if(choose == 'n'){
		        		System.out.println("It is not deleted");
		        	}
		        	else {
		        		System.out.println("Invalid input");
		        	}
		        }
		    } while (option != 4);
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
		            	boolean isFriend = false;
		                for (Friend f : friends) {
		                    if (myid == f.getId()) {
		                    if (u.getId()!= myid) {
		                   	if(f.getFriend() == true) {
		                        System.out.println((u.getUsername()) + " (Friend) ");
		                        isFriend = true;
		                        break;
		                    	}
		                    }
		                    }
		                }
		                if (isFriend == false) {
		                    System.out.println(u.getUsername());
		                }
		            }
		        }
		        else if(option == 2) {
		        	for (User u : users) {
		        		String user = Helper.readString("Search username: ");
		        		if (user.equalsIgnoreCase(u.getUsername())) {
		        			char choose = Helper.readChar("Delete user? (y/n)");
		        			if (choose == 'y') {
		        				for (Friend f : friends) {
		        					if(f.getId() != myid) {
				        				break;
		        					}
		        					else {
		        						System.out.println("Aready added as friend");
		        					}
		        				}
		        			}
		        			else if(choose == 'n') {
		        				System.out.println("Did not add as friend");
		        			}
		        			else {
		    	        		System.out.println("Invalid input");
		    	        	}
		        		}
		        		else {
		        			System.out.println("There is no such user");
		        		}
		        	}
		        }
		        else if(option == 3) {
		        	char choose = Helper.readChar("Delete Account? (y/n)");
		        	if(choose == 'y') {
		        		for (User u : users) {
		        			users.remove(u);
		        			System.out.println("Account deleted");
		        			break;
		        		}
		        	}
		        	else if(choose == 'n'){
		        		System.out.println("It is not deleted");
		        	}
		        	else {
		        		System.out.println("Invalid input");
		        	}
		        }
		    } while (option != 4);
		}


	}

