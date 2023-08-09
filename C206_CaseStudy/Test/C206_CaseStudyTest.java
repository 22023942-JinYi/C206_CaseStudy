import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.After;
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
		bikeList = new ArrayList<Bike>();
		groupList.add(new Group("1", "Bikers", 10, "A group for biking enthusiasts"));
		groupList.add(new Group("2", "MountBike", 15, "For those who love mountain biking"));
		u1 = new User(1, "jin", "password", true);
		u2 = new User(2, "jake", "pass1", false);
		u3 = new User(3, "fin", "pass2", false);

		registrationsList.add(new Registration(1, "HI"));
		registrationsList.add(new Registration(2, "BYE"));

		Friend f1 = new Friend("jake", 1, true);
		Friend f2 = new Friend("jin", 2, true);
		users = new ArrayList<User>();
		friends = new ArrayList<Friend>();
	}

	@Test
	public void testAddDiscussion() {
		// New Discussion Added with user input
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate discussionDate3 = LocalDate.parse("2023-08-08", dtf);
		discussionList.add(new Discussion(3, "Discussion 3", discussionDate3, "Description for Discussion 3"));

		// Verify that a new discussion is added to the list
		assertEquals(3, discussionList.size());

		// Verify that the details of the new discussion match with the user input
		Discussion newDiscussion = discussionList.get(2);
		assertEquals(3, newDiscussion.getId());
		assertEquals("Discussion 3", newDiscussion.getTitle());
		assertEquals(LocalDate.parse("2023-08-08", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
				newDiscussion.getDiscussionDate());
		assertEquals("Description for Discussion 3", newDiscussion.getDescription());
	}

	@Test
	public void testViewAllDiscussion() {
		// Test that the discussions are formatted neatly
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
	}

	@Test
	public void testDeleteDiscussion() {
		// Test that discussion deleted is of ID that user input
		int input1 = 1;
		assertEquals(input1, discussionList.get(0).getId());

		// After deletion, the size of the list should be 1 (since we only removed one
		// discussion)
		int removal = discussionList.get(0).getId();
		discussionList.remove(removal);
		int expectedSizeAfterDeletion = 1;
		assertEquals(expectedSizeAfterDeletion, discussionList.size());
	}

	@Test
	public void testAddEvent() {
		// Add an event to the list
		eventList.add(event1);
		eventList.add(event2);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate eventDate3 = LocalDate.parse("2022-12-22", dtf);
		eventList.add(new Event(3, "HELLO", "Taman E", eventDate3, 7, "Mark"));

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
		eventList.add(new Event(4, "Meeting", "Conference Room", eventDate2, 12, "Jane"));

		// Check if the size of the eventList increased by the number of added events
		assertEquals(4, eventList.size()); // The initial size was 2, so 2 + 2 = 4
	}

	@Test
	public void testViewAllEvent() {
		// Perform the viewAllEvent method and capture the actual output
		String actualOutput1 = Event.viewAllEvent(eventList);

		// Build the expected output, which contains only the header line
		String expectedOutput1 = String.format("%-10s %-20s %-20s %-12s %-12s %-30s%n", "ID", "NAME", "VENUE",
				"EVENT DATE", "PARTICIPANTS", "DESCRIPTION");

		// Check if the actual output matches the expected output
		// Check if the view was empty
		assertEquals(expectedOutput1, actualOutput1);

		// Perform the viewAllEvent method and capture the actual output
		String actualOutput = Event.viewAllEvent(eventList);
		// Build the expected output using a StringBuilder
		String expectedOutput = String.format("%-10s %-20s %-20s %-12s %-12s %-30s%n", "ID", "NAME", "VENUE",
				"EVENT DATE", "PARTICIPANTS", "DESCRIPTION");
		for (Event event : eventList) {
			expectedOutput = String.format("%-10d %-20s %-20s %-12s %-12d %-30s%n", event.getId(), event.getName(),
					event.getVenue(), event.getEventDate(), event.getParticipants(), event.getDescription());
		}
	}

	@Test
	public void testDeleteEvent() {
		Event eventToDelete = new Event(1, "Test Event", "Test Venue", 
				LocalDate.now(), 100, "Test Description");

		// Create an event list
		ArrayList<Event> eventList = new ArrayList<>();
		eventList.add(eventToDelete);

		// Delete the event
		boolean removed = eventList.remove(eventToDelete);

		// Check if the event was deleted
		assertTrue("Existing event should be deleted", removed);
		assertFalse("Event should not exist in the list", eventToDelete );
		assertEquals("Event list should be empty after deletion", 0, eventList.size());

	}

	private void assertFalse(String string, Event eventToDelete) {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void testAddBike() {
		// bike list is not null and it is empty
		assertNotNull("Test if there is valid Bike arraylist to add to", bikeList);
		assertEquals("Test that the Bike arraylist is empty.", 0, bikeList.size());
		// Given an empty list, after adding 1 item, the size of the list is 1
		bikeList.add(bike1);
		assertEquals("Test that the Bike arraylist size is 1.", 1, bikeList.size());

		// Add an item
		bikeList.add(bike2);
		assertEquals("Test that the Bike arraylist size is now 2.", 2, bikeList.size());
		// The bike just added is as same as the last item in the list
		assertSame("Test that Bike is added to the end of the list.", bike2, bikeList.get(1));
	}

	@Test
	public void testViewAllBike() {
		String actualOutput = Bike.viewAllBike(bikeList);
		String expected = String.format("%-10s %-10s %-10s %-10s\n", "ID", "MODEL", "COLOUR", "WEIGHT");
		;
		for (Bike b : bikeList) {
			expected += String.format("%-10d %-10s %-10s %-10.2f\n", b.getId(), b.getModel(), b.getColour(),
					b.getWeight());
		}
		assertEquals(expected, actualOutput);
	}

	@Test
	public void testDeleteBike() {
		bikeList.add(bike1);
		bikeList.add(bike2);
		int input1 = 1;
		assertEquals(input1, bikeList.get(0).getId());

		// After deletion, the size of the list should be 1 (since we only removed one
		// discussion)
		int removal = bikeList.get(0).getId();
		bikeList.remove(removal);
		int expectedSizeAfterDeletion = 1;
		assertEquals(expectedSizeAfterDeletion, bikeList.size());
	}

	@Test
	public void testAddGroup() {
		groupList.add(new Group("3", "Biking", 30, "A group for Biking"));
		assertEquals(3, groupList.size());
	}

	@Test
	public void testAddGroup_DupID() {
		groupList.add(new Group("1", "Bikers", 10, "A group for biking enthusiasts"));
		String duplicateID = groupList.get(2).getId();
		assertEquals("1", duplicateID);
	}

	@Test
	public void testViewAllGroup() {
		groupList.add(new Group("1", "Bikers", 10, "A group for biking enthusiasts"));
		groupList.add(new Group("2", "MountBike", 15, "For those who love mountain biking"));

		assertEquals(4, groupList.size());
	}

	@Test
	public void testDeleteGroup() {
		groupList.remove(new Group("1", "Bikers", 10, "A group for biking enthusiasts"));
		assertEquals(2, groupList.size());
	}

	@Test
	public void testShowDeleteGroup() {
		String groupNameToDelete = "MountBike";
		boolean deleted = false;

		for (int i = 0; i < groupList.size(); i++) {
			if (groupList.get(i).getName().equalsIgnoreCase(groupNameToDelete)) {
				groupList.remove(i);
				deleted = true;
				break;
			}
		}
		assertTrue(deleted);

		// Verify that the group is deleted
		for (Group group : groupList) {
			assertNotEquals(groupNameToDelete, group.getName());
		}
	}

	@Test
	public void testSearchGroup() {

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

	@Test
	public void testsignup() {
		assertNotNull("Check if there is valid users arraylist to add to", users);

		String user = "Hmm";
		String password = "123";
		HomePage.signup(users, user, password);
		for (User u : users) {
			if (u.getUsername().equals(user)) {
				assertEquals("Check that users arraylist size is 1", 1, users.size());
				assertSame("Check that user is added", u, users.get(0));
				break;
			}
		}
	}

	@Test
	public void viewall() {
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

	@Test
	public void checkdeleteUser() {
		assertNotNull("Check if there is a valid users arraylist to add to", users);
		HomePage.adduser(users, u1);
		HomePage.adduser(users, u2);

		assertEquals("Test that users arraylist size is 2", 2, users.size());
		for (User u : users) {
			if (u.getId() == 1) {
				HomePage.deleteuser(users, 1);
			}
		}
		int expectedSizeAfterDeletion = 1;
		assertEquals(expectedSizeAfterDeletion, users.size());
	}

	@Test
	public void testAddRegistration() {
		// Add an reg to the list
		registrationsList.add(new Registration(3, "HELLO"));

		// Check if the size of the registrationsList increased by 1
		assertEquals(3, registrationsList.size());

		// Check if the added REG has the expected properties
		Registration addedEvent = registrationsList.get(2);
		assertEquals("Test the ID of the added event.", 3, addedEvent.getId());
		assertEquals("Test the name of the added event.", "HELLO", addedEvent.getName());
	}

	@Test
	public void testViewAllRegistration() {

		// Perform the ViewAllRegistrationv method and capture the actual output
		String actualOutput = Registration.displayAllRegistration(registrationsList);
		// Build the expected output using a StringBuilder
		String expectedOutput = String.format("%-10s %-20s", "ID", "NAME");
		for (Registration Registration : registrationsList) {
			expectedOutput = String.format("%-10d %-20s", Registration.getId(), Registration.getName());
		}
	}

	@Test
	public void testDeleteRegistration() {
		// Delete the REG
		ArrayList<Registration> registrationsList = new ArrayList<>();
		Registration Registration = new Registration(1, "Test Event");
		registrationsList.remove(Registration);

		// Check if the REG was deleted
		assertEquals(0, registrationsList.size());
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
	}
}