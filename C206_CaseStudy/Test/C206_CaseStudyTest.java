import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Homepage.*;

public class C206_CaseStudyTest {

	private ArrayList<Discussion> discussionList;
	private ArrayList<Group> groupList;
	private ArrayList<Registration> registrationsList;
	private Event event1;
	private Event event2;
	private ArrayList<Event> eventList;
	private Bike bike1;
	private Bike bike2;
	private Bike bike3;
	private ArrayList<Bike> bikeList;
	private User u1;
	private User u2;
	private User u3;
	private Friend f1;
	private Friend f2;
	private Registration r1;
	private Registration r2;
	private Discussion d3;
	private Discussion d4;
	private Event e3;
	private Event e4;

	private ArrayList<User> users;
	private ArrayList<Friend> friends;

	public C206_CaseStudyTest() {
		super();
	}

	@Before
	public void setUp() {
		discussionList = new ArrayList<>();
		eventList = new ArrayList<>();
		groupList = new ArrayList<>();
		registrationsList = new ArrayList<>();
		// Add some sample discussions to the list for testing
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate discussionDate1 = LocalDate.parse("2023-08-06", dtf);
		LocalDate discussionDate2 = LocalDate.parse("2023-08-07", dtf);

		discussionList.add(new Discussion(1, "Discussion 1", discussionDate1, "Description for Discussion 1"));
		discussionList.add(new Discussion(2, "Discussion 2", discussionDate2, "Description for Discussion 2"));
		event1 = new Event(1, "HI", "Taman U", LocalDate.of(2022, 8, 8), 2, "Tom");
		event2 = new Event(2, "BYE", "Taman P", LocalDate.of(2022, 10, 23), 5, "Amy");
		bike1 = new Bike(1, "gg", "red", 69);
		bike2 = new Bike(2, "we", "blue", 70);
		bike3 = new Bike(3, "", "blue", 71);
		bikeList = new ArrayList<Bike>();
		
		u1 = new User(1, "jin", "password", true);
		u2 = new User(2, "jake", "password1", false);
		u3 = new User(3, "fin", "password2", false);

		r1 = new Registration(1, "HI");
		r2 = new Registration(2, "BYE");

		Friend f1 = new Friend("jake", 1, true);
		Friend f2 = new Friend("jin", 2, true);
		users = new ArrayList<User>();
		friends = new ArrayList<Friend>();
	}

	@Test
	public void testAddDiscussion() {
		// New Discussion Added with user input - normal
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate discussionDate3 = LocalDate.parse("2023-08-08", dtf);
		d3 = new Discussion(3, "Discussion 3", discussionDate3, "Description for Discussion 3");
		Discussion.addDiscussion(discussionList, d3);
		// Verify that a new discussion is added to the list
		assertEquals(3, discussionList.size());

		// Verify that the details of the new discussion matches with the user input -
		// Normal
		Discussion newDiscussion = discussionList.get(2);
		assertEquals(3, newDiscussion.getId());
		assertEquals("Discussion 3", newDiscussion.getTitle());
		assertEquals(LocalDate.parse("2023-08-08", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
				newDiscussion.getDiscussionDate());
		assertEquals("Description for Discussion 3", newDiscussion.getDescription());

		// Verify that the description is less than 200 characters. - Boundary
		LocalDate discussionDate4 = LocalDate.parse("2023-08-09", dtf);
		String characters = "*".repeat(199);

		// Call the addDiscussion method with the required parameters
		d4 = new Discussion(4, "Discussion 4", discussionDate4, characters);
		Discussion.addDiscussion(discussionList, d4);
		assertEquals(4, discussionList.size());
	}

	@Test
	public void testViewAllDiscussion() {
		// Test that the discussions are formatted neatly - Normal
		String actualOutput = Discussion.viewAllDiscussion(discussionList);
		String expectedOutput = "";
		String header = "-".repeat(150);
		for (Discussion discussion : discussionList) {
			expectedOutput += header;
			expectedOutput += String.format("\n%-20s%-30s%-20s\n", "Id:", "Title of Discussion:",
					"Date of Discussion:");
			expectedOutput += header;
			expectedOutput += String.format("\n%-20s%-30s%-20s", discussion.getId(), discussion.getTitle(),
					discussion.getDiscussionDate());
			expectedOutput += "\n";
			expectedOutput += String.format("%-20s%-80s", "Description:", discussion.getDescription());
			expectedOutput += "\n";
			expectedOutput += "\n";
		}
		assertEquals(expectedOutput, actualOutput);
		// Test that nothing would be displayed if the list is empty. - Boundary
		discussionList.clear();
		String empty = Discussion.viewAllDiscussion(discussionList);
		String emptyOutput = "";
		assertEquals(empty, emptyOutput);

	}

	@Test
	public void testDeleteDiscussion() {
		// Test that discussion deleted is of the ID that the user input - Normal
		int input1 = 1;
		assertEquals(input1, discussionList.get(0).getId());

		// After deletion, the size of the list should be 1 (since we only removed one -
		// Normal
		// discussion)
		Discussion.deleteDiscussion(discussionList, input1, 'y');
		int expectedSizeAfterDeletion = 1;
		assertEquals(expectedSizeAfterDeletion, discussionList.size());

		// After another deletion, the size of the list should be 0 - Boundary
		Discussion.deleteDiscussion(discussionList, 2, 'y');
		assertEquals(0, discussionList.size());

		// An empty discussion list cannot be deleted. - Error
		discussionList.clear();
		Discussion.deleteDiscussion(discussionList, input1, 'y');
		int actualSize = discussionList.size();
		int expSizeAftDeletion = -1;
		assertNotEquals(expSizeAftDeletion, actualSize);

	}

	@Test
	public void testAddEvent() {
		// Item list is not null, so that can add a new item - boundary
		assertNotNull("Check if there is valid Event arraylist to add to", eventList);
		// Add an event to the list
		eventList.add(event1);
		eventList.add(event2);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate eventDate3 = LocalDate.parse("2022-12-22", dtf);
		e3 = new Event(3, "HELLO", "Taman E", eventDate3, 7, "Mark");
		Event.addEvent(eventList, e3);

		// Check if the size of the eventList increased by 1
		assertTrue(eventList.size() == 3);

		// Check if the added event has the expected properties
		Event addedEvent = eventList.get(2);
		assertEquals("Test the ID of the added event.", 3, addedEvent.getId());
		assertEquals("Test the name of the added event.", "HELLO", addedEvent.getName());
		assertEquals("Test the location of the added event.", "Taman E", addedEvent.getVenue());
		assertEquals("Test the date of the added event.", LocalDate.of(2022, 12, 22), addedEvent.getEventDate());
		assertEquals("Test the duration of the added event.", 7, addedEvent.getParticipants());
		assertEquals("Test the organizer of the added event.", "Mark", addedEvent.getDescription());
		LocalDate eventDate2 = LocalDate.parse("2023-01-15", dtf);
		e4 = new Event(4, "Meeting", "Conference Room", eventDate2, 12, "Jane");
		Event.addEvent(eventList, e4);

		// Check if the size of the eventList increased by the number of added events
		assertEquals(4, eventList.size()); // The initial size was 2, so 2 + 2 = 4
	}

	@Test
	public void testViewAllEvent() {
		// Test if Item list events is not null but empty -boundary
		assertNotNull("Test if there is valid Event arraylist to retrieve item", eventList);

		// Perform the viewAllEvent method and capture the actual output
		String actualOutput1 = Event.viewAllEvent(eventList);

		// Build the expected output, which contains only the header line
		String expectedOutput1 = String.format("%-10s %-20s %-20s %-12s %-12s %-30s%n", "ID", "NAME", "VENUE",
				"EVENT DATE", "PARTICIPANTS", "DESCRIPTION");

		// Check if the actual output matches the expected output
		// Check if the view was empty
		assertEquals(expectedOutput1, actualOutput1);// -normal
	}

	@Test
	public void testDeleteEvent() {
		Event.addEvent(eventList, event1);
		Event.addEvent(eventList, event2);

		// Delete the event
		int removal = eventList.get(0).getId();
		Event.deleteEvent(eventList, removal, 'y');

		// Check if the event was deleted.-Normal
		assertEquals(1, eventList.size());

		// Attempt to delete all the bike in the list. -Boundry
		int remove = eventList.get(0).getId();
		Event.deleteEvent(eventList, remove, 'y');
		assertEquals(0, eventList.size());

		// Check list cannot be deleted. - Error
		assertFalse(Event.deleteEvent(eventList, 1, 'y'));

	}

	@Test
	public void testAddBike() {
		// bike list is not null and it is empty
		assertNotNull("Test if there is valid Bike arraylist to add to", bikeList);
		assertEquals("Test that the Bike arraylist is empty.", 0, bikeList.size());
		// Given an empty list, after adding 1 item, the size of the list is 1
		Bike.addBike(bikeList, bike1);
		assertEquals("Test that the Bike arraylist size is 1.", 1, bikeList.size());

		// Add an item
		Bike.addBike(bikeList, bike2);
		assertEquals("Test that the Bike arraylist size is now 2.", 2, bikeList.size());
		// The bike just added is as same as the last item in the list
		assertSame("Test that Bike is added to the end of the list.", bike2, bikeList.get(1));
		// Try to add bike that has an empty string
		assertFalse(Bike.addBike(bikeList, bike3));
		// Add the same bike
		assertFalse(Bike.addBike(bikeList, bike1));
	}

	@Test
	public void testViewAllBike() {
		// view empty list
		String actualOutput = Bike.viewAllBike(bikeList);
		String expected = String.format("%-10s %-10s %-10s %-10s\n", "ID", "MODEL", "COLOUR", "WEIGHT");
		for (Bike b : bikeList) {
			expected += String.format("%-10d %-10s %-10s %-10.2f\n", b.getId(), b.getModel(), b.getColour(),
					b.getWeight());
		}
		assertEquals(expected, actualOutput);
		// View list with item
		Bike.addBike(bikeList, bike1);
		Bike.addBike(bikeList, bike2);
		String actualOutput1 = Bike.viewAllBike(bikeList);
		String expected1 = String.format("%-10s %-10s %-10s %-10s\n", "ID", "MODEL", "COLOUR", "WEIGHT");
		for (Bike b : bikeList) {
			expected1 += String.format("%-10d %-10s %-10s %-10.2f\n", b.getId(), b.getModel(), b.getColour(),
					b.getWeight());
		}
		assertEquals(expected1, actualOutput1);
	}

	@Test
	public void testDeleteBike() {
		Bike.addBike(bikeList, bike1);
		Bike.addBike(bikeList, bike2);
		assertEquals(2, bikeList.size());

		// After deletion, the size of the list should be 1 (since we only removed one
		// discussion)
		int removal = bikeList.get(0).getId();
		Bike.deleteBike(bikeList, removal, 'y');
		assertEquals(1, bikeList.size());

		// check if the list is empty after all the bike added is deleted
		int remove = bikeList.get(0).getId();
		Bike.deleteBike(bikeList, remove, 'y');
		assertEquals(0, bikeList.size());
		// An empty bike list cannot be deleted.
		assertFalse(Bike.deleteBike(bikeList, 1, 'y'));
	}

	@Test
	public void testAddGroup() {
		String newGroupId = "3";
		String newGroupName = "RoadBike";
		int newGroupParticipants = 12;
		String newGroupDescription = "Conquering roads on two wheels.";
		Group newGroup = new Group(newGroupId, newGroupName, newGroupParticipants, newGroupDescription);

		// Test that there is no duplicated ID and add the Group into the list
		Group.addGroup(groupList, newGroup);
		// Verify that the group is created
		assertEquals(1, groupList.size());
	}

	@Test 
	 public void testViewGroups() { 
	  // Test that initially, the groupList has a size of 2 
	  assertNotNull("Check that if there is any groups to add",groupList); 
	  String newGroupId = "3"; 
	  String newGroupName = "Biking";
	  int newGroupParticipants = 150;
	  String newGroupDescription = "Biking nerds"; 
	  Group newGroup = new Group(newGroupId, newGroupName, newGroupParticipants, newGroupDescription);
	  Group.addGroup(groupList,newGroup); 
	  assertEquals("Test that group arrayList size is 1",1,groupList.size()); 
	   
	  
	   
	  String testOutput=String.format("%-5s %-10s %-15d %s\n", "3", "Biking",150,"Biking nerds"); 
	   
	  assertEquals("Test that testViewGroups list is the expected output", testOutput,Group.showallgrp(groupList)); 
	     
	 } 
	@Test
	public void testSearchGroup() {
		groupList.add(new Group("1", "Bikers", 10, "A group for biking enthusiasts"));
		groupList.add(new Group("2", "MountBike", 15, "For those who love mountain biking"));

		Group foundGroup = null;
		String searchGroupName = "Bikers";
		for (Group group : groupList) {
			if (group.getName().equalsIgnoreCase(searchGroupName)) {
				foundGroup = group;
				break;
			}
		}
		assertNotNull(foundGroup);
		assertEquals("1", foundGroup.getId());

		// Test searching for a non-existing group
		Group notFoundGroup = null;
		String nonExistingGroupName = "Road Warriors";
		for (Group group : groupList) {
			if (group.getName().equalsIgnoreCase(nonExistingGroupName)) {
				notFoundGroup = group;
				break;
			}
		}
		assertNull(notFoundGroup);
	}

	@Test
	public void testDeleteGroup() {
		// Test that group deleted is of the ID that the user input - Normal
		
		groupList.add(new Group("1", "Bikers", 10, "A group for biking enthusiasts"));
		groupList.add(new Group("2", "MountBike", 15, "For those who love mountain biking"));
		String input1 = "1";
		assertEquals(input1, groupList.get(0).getId());

		// After deletion, the size of the list should be 1 (since we only removed one -
		// Normal
		// group)
		Group.deleteGroup(groupList, input1, 'y');
		int expectedSizeAfterDeletion = 1;
		assertEquals(expectedSizeAfterDeletion, groupList.size());

		// After another deletion, the size of the list should be 0 - Boundary
		Group.deleteGroup(groupList, "2", 'y');
		assertEquals(0, groupList.size());

		// An empty group list cannot be deleted. - Error
		groupList.clear();
		Group.deleteGroup(groupList, input1, 'y');
		int actualSize = groupList.size();
		int expSizeAftDeletion = -1;
		assertNotEquals(expSizeAftDeletion, actualSize);

	}

	// Jin Yi
	@Test
	public void AddUser() {
		// Item list is not null, so that can add a new item - boundary
		assertNotNull("Check if there is valid users arraylist to add to", users);
		// Given an empty list, after adding 1 item, the size of the list is 1 - normal
		// The item just added is as same as the first item of the list
		HomePage.adduser(users, u1);
		assertEquals("Check that users arraylist size is 1", 1, users.size());
		assertSame("Check that user is added", u1, users.get(0));

		// Add another item. test The size of the list is 2? -normal
		// The item just added is as same as the second item of the list
		HomePage.adduser(users, u2);
		assertEquals("Check that users arraylist size is 2", 2, users.size());
		assertSame("Check that user is added", u2, users.get(1));
	}

	// Jin Yi
	@Test
	public void testsignup() {
		assertNotNull("Check if there is valid users arraylist to add to", users);

		String user = "Hmm";
		String password = "12345678";
		for (User u : users) {
			// check if password is acceptable when its 8 char
			HomePage.signup(users, user, password);
			assertEquals("Check that users arraylist size is 1", 1, users.size());
			assertSame("Check that user is added", u, user);
			break;
		}

		String user2 = "lol";
		password = "123";
		// check if password is unacceptable when its below 8 char
		HomePage.signup(users, user2, password);
		assertSame("Check that users arraylist size is empty", 0, users.size());
		;

		String user3 = "lol1";
		password = "12345678910";
		for (User u : users) {
			// check if password is acceptable when its more than 8 char
			HomePage.signup(users, user, password);
			assertEquals("Check that users arraylist size is 2", 2, users.size());
			assertSame("Check that user is added", u, user3);
			break;
		}
	}

	// Jin Yi
	@Test
	public void viewalluser() {
		// Test if User list is not null but empty - boundary
		assertNotNull("Check if there is a valid users arraylist to add to", users);

		// Given an empty list, after adding 2 items, test if the size of the list is 2
		// - normal
		HomePage.adduser(users, u1);
		HomePage.adduser(users, u2);
		assertEquals("Test that users arraylist size is 2", 2, users.size());

		assertNotNull("Check if there are users in the users arraylist to add to", users);

		String userslist = HomePage.viewUsers(users);
		String testOutput = "jin\njake\n"; // Correctly formatted output of users
		assertEquals("Test that ViewAllUsers list", testOutput, userslist);
	}

	// Jin Yi
	@Test
	public void checkdeleteOwnUser() {
		assertNotNull("Check if there is a valid users arraylist to add to", users);
		HomePage.adduser(users, u1);
		HomePage.adduser(users, u2);

		assertEquals("Test that users arraylist size is 2", 2, users.size());
		// delete own user
		for (User u : users) {
			if (u.getId() == 1) {
				HomePage.deleteuser(users, 1);
			}
		}
		int expectedSizeAfterDeletion = 1;
		assertEquals(expectedSizeAfterDeletion, users.size());

	}

	// Jin Yi
	@Test
	public void checkdeleteuserADMIN() {
		assertNotNull("Check if there is a valid users arraylist to add to", users);
		HomePage.adduser(users, u2);
		HomePage.adduser(users, u3);

		assertEquals("Test that users arraylist size is 2", 2, users.size());

		// delete other users
		String user = "fin";
		char choose = 'y';
		HomePage.admindeleteuser(users, friends, user, choose);
		int expectedSizeAfterDeletion = 1;
		assertEquals(expectedSizeAfterDeletion, users.size());

		HomePage.adduser(users, u2);
		assertEquals("Test that users arraylist size is 2", 2, users.size());

		// delete user that is not inside the arraylist
		user = "wee";
		choose = 'y';
		HomePage.admindeleteuser(users, friends, user, choose);
		expectedSizeAfterDeletion = 2;
		assertEquals(expectedSizeAfterDeletion, users.size());

		// user not deleted when admin choose n
		user = "hmm";
		choose = 'n';
		HomePage.admindeleteuser(users, friends, user, choose);
		expectedSizeAfterDeletion = 2;
		assertEquals(expectedSizeAfterDeletion, users.size());

	}

	@Test
	public void testAddRegistration() {
		// Item list is not null, so that can add a new item - boundary
		assertNotNull("Check if there is valid Registration arraylist to add to", registrationsList);

		// Given an empty list, after adding 1 item, the size of the list is 1 - normal
		// The item just added is as same as the first item of the list
		Registration.addEventRegistrations(registrationsList, r1);
		assertEquals("Check that Registration arraylist size is 1", 1, registrationsList.size());
		assertSame("Check that Registration is added", r1, registrationsList.get(0));

		// Add another Registration. test The size of the list is 2? -normal
		// The item just added is as same as the second item of the list
		Registration.addEventRegistrations(registrationsList, r2);
		assertEquals("Check that Registration arraylist size is 2", 2, registrationsList.size());
		assertSame("Check that Registration is added", r2, registrationsList.get(1));
	}

	@Test
	public void testViewAllRegistration() {
		/*
		 * r1 = new Registration(1, "HI"); r2 = new Registration(2, "BYE");
		 */
		// Test if Registration list is not null but empty - boundary
		assertNotNull("Check if there is a valid users arraylist to add to", registrationsList);
		Registration.addEventRegistrations(registrationsList, r1);
		Registration.addEventRegistrations(registrationsList, r2);
		assertEquals("Check that Registration arraylist size is 2", 2, registrationsList.size());

		// Given an empty list, after adding 2 items, test if the size of the list is 2
		// - normal

		String testOutput = String.format("\n%-10d %-20s", 1, "HI");
		testOutput += String.format("\n%-10d %-20s", 2, "BYE");// Correctly formatted output of Registration
		assertEquals("Test that ViewAllUsers list", testOutput, Registration.displayAllRegistration(registrationsList));
	}

	@Test
	public void testDeleteRegistration() {
		assertNotNull("Check if there is a valid registration arraylist to add to", registrationsList);
		Registration.addEventRegistrations(registrationsList, r1);
		Registration.addEventRegistrations(registrationsList, r2);
		assertEquals("Check that Registration arraylist size is 2", 2, registrationsList.size());

		Registration.deleteEventRegistration(registrationsList, 1, 'y');

		int expectedSizeAfterDeletion = 1;
		assertEquals("Check that Registration arraylist size is 1", expectedSizeAfterDeletion,
				registrationsList.size());
	}

	@After
	public void tearDown() throws Exception {
		event1 = null;
		event2 = null;
		bike1 = null;
		bike2 = null;
		bike3 = null;
		bikeList = null;
		u1 = null;
		u2 = null;
		u3 = null;
		f1 = null;
		f2 = null;
		users = null;
		friends = null;
		r1 = null;
		r2 = null;
		groupList = null;
	}
}