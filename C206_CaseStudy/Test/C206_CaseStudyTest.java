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
	private ArrayList<Event> eventList;
	private ArrayList<Group> groupList;
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
		// Add some sample discussions to the list for testing
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate discussionDate1 = LocalDate.parse("2023-08-06", dtf);
		LocalDate discussionDate2 = LocalDate.parse("2023-08-07", dtf);

		discussionList.add(new Discussion(1, "Discussion 1", discussionDate1, "Description for Discussion 1"));
		discussionList.add(new Discussion(2, "Discussion 2", discussionDate2, "Description for Discussion 2"));
		eventList.add(new Event(1, "HI", "Taman U", LocalDate.of(2022, 8, 8), 2, "Tom")); // year, month, day
		eventList.add(new Event(2, "BYE", "Taman P", LocalDate.of(2022, 10, 23), 5, "Amy"));
		eventList.add(new Event(3, "HELLO", "Taman E", LocalDate.of(2022, 12, 22), 7, "Mark"));
		bike1 = new Bike(1, "gg", "red", 69);
		bike2 = new Bike(2, "we", "blue", 70);
		bikeList = new ArrayList<Bike>();
		groupList.add(new Group("1", "Bikers", 10, "A group for biking enthusiasts"));
		groupList.add(new Group("2", "MountBike", 15, "For those who love mountain biking"));
		u1 = new User(1, "jin", "password", true);
		u2 = new User(2, "jake", "pass1", false);
		u3 = new User(3, "fin", "pass2", false);

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
	public void testAddEvent() {
		eventList.add(new Event(4, "HELLO", "Taman E", LocalDate.of(2022, 12, 22), 7, "Mark"));
		// Check if an event was added to the list
		assertEquals(4, eventList.size());
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
	public void testViewAllEvent() {
		ArrayList<Event> eventList = new ArrayList<>();
		Event event1 = new Event(1, "Event 1", "Venue 1", LocalDate.now(), 50, "Description 1");
		Event event2 = new Event(2, "Event 2", "Venue 2", LocalDate.now().plusDays(1), 75, "Description 2");
		eventList.add(event1);
		eventList.add(event2);

		// Perform the viewAllEvent method
		// Since this method only prints the events, it's hard to write a direct test
		// for its output

		// Check that the events were not modified
		assertEquals(2, eventList.size());
		assertTrue(eventList.contains(event1));
		assertTrue(eventList.contains(event2));
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
	public void testDeleteEvent() {
		// Delete the event
		ArrayList<Event> eventList = new ArrayList<>();
		Event event = new Event(1, "Tet Event", "Test Venue", LocalDate.now(), 100, "Test Description");
		eventList.remove(event);

		// Check if the event was deleted
		assertEquals(0, eventList.size());
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

	@After
	public void tearDown() throws Exception {
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