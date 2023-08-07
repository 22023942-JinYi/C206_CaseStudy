import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Homepage.Discussion;

public class C206_CaseStudyTest {

    private ArrayList<Discussion> discussionList;

    @Before
    public void setUp() {
        discussionList = new ArrayList<>();
        // Add some sample discussions to the list for testing
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate discussionDate1 = LocalDate.parse("2023-08-06", dtf);
        LocalDate discussionDate2 = LocalDate.parse("2023-08-07", dtf);
        discussionList.add(new Discussion(1, "Discussion 1", discussionDate1, "Description for Discussion 1"));
        discussionList.add(new Discussion(2, "Discussion 2", discussionDate2, "Description for Discussion 2"));
        
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
        assertEquals(LocalDate.parse("2023-08-08", DateTimeFormatter.ofPattern("yyyy-MM-dd")), newDiscussion.getDiscussionDate());
        assertEquals("Description for Discussion 3", newDiscussion.getDescription());
    }

    @Test
    public void testViewAllDiscussion() {
        // Test that the discussions are formatted neatly
        String actualOutput = Discussion.viewAllDiscussion(discussionList);
        String expectedOutput = "";

	    for (Discussion discussion : discussionList) {
	    	expectedOutput += " ------------------------------------------------------------------------------------------------------------------------------------------------------";
	    	expectedOutput += String.format("\n%-20s%-30s%-20s\n", "Id:", "Title of Discussion:", "Date of Discussion:");
	    	expectedOutput += " ------------------------------------------------------------------------------------------------------------------------------------------------------";
	    	expectedOutput += String.format("\n%-20s%-30s%-20s", discussion.getId(), discussion.getTitle(), discussion.getDiscussionDate());
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
    	 for (Discussion discussion: discussionList) {
		        if (discussion.getId() == input1) {
		        	discussionList.remove(0);
		        }
	        	assertEquals(input1, discussionList.get(0).getId());


		        
    	 }
		    	
    	
        // After deletion, the size of the list should be 1 (since we only removed one discussion)
        int expectedSizeAfterDeletion = 1;
        assertEquals(expectedSizeAfterDeletion, discussionList.size());
    }
    

 
}