package Homepage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Helper.Helper;

public class Group {

	private String id;
	private String name;
	private int participants;
	private String description;

	public Group(String id, String name, int participants, String description) {
		this.id = id;
		this.name = name;
		this.participants = participants;
		this.description = description;

	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParticipants() {
		return participants;
	}

	public void setParticipants(int participants) {
		this.participants = participants;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static Group inputGroup() {
		String groupID = Helper.readString("Enter the group ID >"); 
	    String groupName = Helper.readString("Enter the group name > ");
	    int groupPart= Helper.readInt("Enter the number of participants > ");
	    String groupDescription = Helper.readString("Enter the group description > "); 
	  

	    Group group = new Group(groupID, groupName, groupPart, groupDescription);
	    return group;
	}
	
	public static boolean addGroup(ArrayList<Group> groupList, Group newGroup) {
	    for (Group group : groupList) {
	        if (group.getId() == newGroup.getId()) {
	            return false;
	        }
	    }

	    if (newGroup.getId().isEmpty() || newGroup.getName().isEmpty() || newGroup.getParticipants() == 0) {
	        return false;
	    } else {
	        groupList.add(newGroup);
	        System.out.println("Group has been successfully added");
	        return true;
	    }
	}

	public static String showallgrp(ArrayList<Group> groupList) {
		Helper.line(45, "=");
        System.out.println(String.format("%-5s %-10s %-15s %s", "ID", "NAME", "PARTICIPANTS", "DESCRIPTION"));
String output = "";
        for (Group group : groupList) {
            output += String.format("%-5s %-10s %-15d %s\n", group.getId(), group.getName(),
                    group.getParticipants(), group.getDescription());
        }
        return output;

	}

	public static void viewAllGroup(ArrayList<Group> groupList) {

	    while (true) {
	        Helper.line(40, "=");
	        System.out.println("Menu");
	        Helper.line(40, "=");
	        System.out.println("1. View All Groups");
	        System.out.println("2. View a Specific Group");
	        System.out.println("3. Exit");

	        int choice = Helper.readInt("Enter your choice:");

	        if (choice == 1) {
	            System.out.println(showallgrp(groupList));
	            Helper.line(45, "=");

	        } else if (choice == 2) {
	            String groupName = Helper.readString("\nPlease enter the name of the group to view:\n");

	            boolean foundGroup = false;
	            for (Group group : groupList) {
	                if (group.getName().equalsIgnoreCase(groupName)) {
	                    foundGroup = true;
	                    Helper.line(40, "=");
	                    System.out.println("Group Details");
	                    Helper.line(40, "=");
	                    System.out.println("ID: " + group.getId());
	                    System.out.println("Name: " + group.getName());
	                    System.out.println("Participants: " + group.getParticipants());
	                    System.out.println("Description: " + group.getDescription());
	                }
	            }

	            if (!foundGroup) {
	                System.out.println("Group not found. Please try Again!!");
	            }

	        } else if (choice == 3) {
	            System.out.println("Goodbye!");
	            break;
	        } else {
	            System.out.println("Invalid choice. Please try again!");
	        }
	    }
	}


	public static String inputGroupID() {
	    String id = Helper.readString("Enter ID of the group to delete > ");
		return id;
	}
	public static char inputConfirmation() {
		char YesorNo = Helper.readChar("Are you sure you want to delete the group (y/n) > ");
		return YesorNo;
	}
	
		public static boolean deleteGroup(ArrayList<Group> groupList, String ID, char confirmation) {
		    boolean found = false; 
		    for (Group group: groupList) {
		        if (group.getId().equals(ID)) {
		            found = true;
		            if (Character.toLowerCase(confirmation) == 'y') {
		            	groupList.remove(group);
		                System.out.println("Group has been deleted successfully.");
		            } else {
		                System.out.println("Group has not been deleted.");
		            }
		            break; 
		        }
		    }
		    
		    if (!found) {
		        System.out.println("Group was not found.");
		    }
			return found;
		}



}
