
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

	public static boolean addGroup(ArrayList<Group> groupList) {
		boolean isFound = false;

		String ied = Helper.readString("Enter group id  > ");
		for (int i = 0; i < groupList.size(); i++) {
			if (ied.equalsIgnoreCase(groupList.get(i).getId())) {
				System.out.println("Group has already been created!");
				isFound = true;

			} else {
				String name = Helper.readString("Enter group name > ");
				int participants = Helper.readInt("Enter group number of participant > ");
				String description = Helper.readString("Enter group description > ");

				Group newGroup = new Group(ied, name, participants, description);
				groupList.add(newGroup);

				System.out.println("New Group has been successfully added.");
			}

		}
		return isFound;

	}

	public static void viewAllGroup(ArrayList<Group> groupList) {

		while (true) {
			Helper.line(50,"-");
			System.out.println("Menu");
			Helper.line(50,"-");
			System.out.println("1. View All Groups");
			System.out.println("2. View a Specific Group");
			System.out.println("3. Exit");

			int choice = Helper.readInt("Enter your choice:");

			if (choice == 1) {
				String output = String.format("%-5s %-10s %-15s %s\n", "ID", "NAME", "PARTICIPANTS", "DESCRIPTION");

				for (Group group : groupList) {
					output += String.format("%-5s %-10s %-15d %s\n", group.getId(), group.getName(),
							group.getParticipants(), group.getDescription());
				}

				System.out.println(output);

			}

			else if (choice == 2) {
				String groupName = Helper.readString("\nPlease enter the name of the group to view:\n");

				boolean foundGroup = false;
				for (Group group : groupList) {
					if (group.getName().equalsIgnoreCase(groupName)) {
						foundGroup = true;
						Helper.line(60, "=");
						System.out.println("Group Details");
						Helper.line(60, "=");
						System.out.println("ID: " + group.getId());
						System.out.println("Name: " + group.getName());
						System.out.println("Participants: " + group.getParticipants());
						System.out.println("Description: " + group.getDescription());

					}
				}

			} else if (choice == 3) {
				System.out.println("Goodbye!");
				break;
			} else {
				System.out.println("Invalid choice. Please try again!");
			}

		}

	}

	public static void deleteGroup(ArrayList<Group> groupList) {
		String idDel = Helper.readString("Enter id of group to delete > ");
		boolean found = false;

		for (Group group : groupList) {
			if (group.getId().equals(idDel)) {
				found = true;
				char confirm = Helper.readChar("Are you sure you want to delete the group (y/n) > ");
				if (Character.toLowerCase(confirm) == 'y') {
					groupList.remove(group);
					System.out.println("Group has been deleted successfully.");
				} else {
					System.out.println("Group has not been deleted");
				}
				break;
			}
		}

		if (!found) {
			System.out.println("Group was not found.");
		}
	}



}
