package Homepage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Helper.Helper;

public class Discussion {
	private int id;
	private String title;
	private LocalDate discussionDate;
	private String description;

	
	public Discussion(int id, String title, LocalDate discussionDate, String description) {
		this.id = id;
		this.title = title;
		this.discussionDate = discussionDate;
		this.description = description;
		
	}


	public int getId() {
		return id;
	}
	

	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {	
		this.title = title;
	}


	public LocalDate getDiscussionDate() {
		return discussionDate;
	}


	public void setDiscussionDate(LocalDate discussionDate) {
		this.discussionDate = discussionDate;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public static Discussion inputDiscussion() {
	    int id = Helper.readInt("Enter discussion id > ");
	    String title = Helper.readString("Enter discussion title > ");
	    String discussionDate = Helper.readString("Enter discussion date (yyyy-MM-dd) > ");
	    String description = Helper.readString("Enter discussion description > ");
	    while (description.length() > 200) {
	        System.out.println("Discussion has to be less than 200 characters.");
	        description = Helper.readString("Enter discussion description > ");
	    }

	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate dDate = LocalDate.parse(discussionDate, dtf);
	    Discussion discussion = new Discussion(id, title, dDate, description);
	    return discussion;
	}
	
	public static boolean addDiscussion(ArrayList<Discussion> discussionList, Discussion newDiscussion) {
	    for (Discussion discussion : discussionList) {
	        if (discussion.getId() == newDiscussion.getId()) {
	            return false;
	        }
	    }

	    if (newDiscussion.getId() == 0 || newDiscussion.getTitle().isEmpty() || newDiscussion.getDescription().isEmpty()) {
	        return false;
	    } else {
	        discussionList.add(newDiscussion);
	        System.out.println("Discussion has been successfully added");
	        return true;
	    }
	}


		
		
	public static String viewAllDiscussion(ArrayList<Discussion> discussionList) {
		

		   String output = "";
		   String header = "-".repeat(150);
		    for (Discussion discussion : discussionList) {
		    	output += header;
		        output += String.format("\n%-20s%-30s%-20s\n", "Id:", "Title of Discussion:", "Date of Discussion:");
		    	output += header;
		        output += String.format("\n%-20s%-30s%-20s", discussion.getId(), discussion.getTitle(), discussion.getDiscussionDate());
		        output += "\n";
		        output += String.format("%-20s%-80s", "Description:", discussion.getDescription());
		        output += "\n";
		        output += "\n";
		    }
		    System.out.println(output);
		    return output;
		}
	
	public static int inputDeleteDiscussion() {
	    int id = Helper.readInt("Enter ID of the discussion to delete > ");
		return id;
	}
	public static char inputConfirmation() {
		char YesorNo = Helper.readChar("Are you sure you want to delete the discussion (y/n) > ");
		return YesorNo;
	}
	
		public static boolean deleteDiscussion(ArrayList<Discussion> discussionList, int ID, char confirmation) {
		    boolean found = false; 
		    for (Discussion discussion: discussionList) {
		        if (discussion.getId()== (ID)) {
		            found = true;
		            if (Character.toLowerCase(confirmation) == 'y') {
		            	discussionList.remove(discussion);
		                System.out.println("Discussion has been deleted successfully.");
		            } else {
		                System.out.println("Discussion has not been deleted.");
		            }
		            break; 
		        }
		    }
		    
		    if (!found) {
		        System.out.println("Discussions was not found.");
		    }
			return found;
		}
}
