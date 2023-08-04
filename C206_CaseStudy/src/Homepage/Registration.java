package Homepage;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;


public class Registration {
    private int id;
    private String name;
    private String venue;
    private LocalDate eventDate;
    private int participants;
    private String description;
    private List <Registration> signedUpForEvents;
    

	public Registration(int id, String name, String venue, LocalDate eventDate, int participants, String description) {
        this.id = id;
       this.name = name;
        this.venue = venue;
        this.eventDate = eventDate;
        this.participants = participants;
        this.description = description;
        this.signedUpForEvents = new ArrayList<>();
    }
    public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public LocalDate getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
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





    // Method to sign up for an event
    public void signUpForEvent(User user) {
        if (!user.LoggedIn()) {
            System.out.println("Please log in to sign up for the event.");
            return;
        }
        user.addEvent(this);
    }
    
    //Method to view all registration of participants
    public List<Registration> getSignedUpForEvents(User user) {
        return user.getSignedUpForEvents();
    }

    // Method to remove a signed-up event
    public void removeSignedUpEvent(User user) {
        // Assuming you have a User class that stores user information
        user.removeEvent(this);
    }
} 
