package Homepage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Helper.Helper;

public class Discussion {
	private String id;
	private String title;
	private LocalDate discussionDate;
	private String description;

	
	public Discussion(String id, String title, LocalDate discussionDate, String description) {
		this.id = id;
		this.title = title;
		this.discussionDate = discussionDate;
		this.description = description;
		
	}


	public String getId() {
		return id;
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
	
	
	public static void addDiscussion(ArrayList<Discussion> discussionList) {
		String id = Helper.readString("Enter discussion id  > ");
		String title = Helper.readString("Enter discussion title > ");
		String discussionDate = Helper.readString("Enter discussion date (yyyy-MM-dd) > ");
		String description = Helper.readString("Enter discussion description > ");
		while(description.length() > 120) {
			System.out.println("Discussion have to be less than 120 characters.");
			description = Helper.readString("Enter discussion description > ");
		}

		
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate dDate = LocalDate.parse(discussionDate, dtf);
			Discussion newDiscussion = new Discussion(id, title, dDate, description);
			discussionList.add(newDiscussion);
		System.out.println("New Discussion successfully added.");
		}
		
		
		public static void viewAllDiscussion(ArrayList<Discussion> discussionList) {
		


		    for (Discussion discussion : discussionList) {
		    	Helper.line(150, "-");
				System.out.println(String.format("%-20s%-30s%-20s", "Id:", "Title of Discussion:", "Date of Discussion:"));
				Helper.line(150, "-");
		        System.out.println(String.format("%-20s%-30s%-20s", discussion.getId(), discussion.getTitle(), discussion.getDiscussionDate()));
				System.out.println(" ");

		    System.out.println(String.format("%-20s%-80s", "Description:", discussion.getDescription()));
			System.out.println(" ");

		    }
		}
		public static void deleteDiscussion(ArrayList<Discussion> discussionList) {
		    String id = Helper.readString("Enter id to delete > ");
		    boolean found = false; 

		    for (Discussion discussion: discussionList) {
		        if (discussion.getId().equals(id)) {
		            found = true;
		            char confirm = Helper.readChar("Are you sure you want to delete the discussion (y/n) > ");
		            if (Character.toLowerCase(confirm) == 'y') {
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
		}
}
