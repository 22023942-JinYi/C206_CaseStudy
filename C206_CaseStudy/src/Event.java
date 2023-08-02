import java.time.LocalDate;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

public class Event {

	private String id;
	private String name;
	private String venue;
	private LocalDate eventDate;
	private int participants;
	private String description;

	public Event(String id, String name, String venue, LocalDate eventDate, int participants, String description) {
		this.id = id;
		this.name = name;
		this.venue = venue;
		this.eventDate = eventDate;
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

	public String getVenue() {
		return name;
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

	public static void addEvent(ArrayList<Event> eventList) {
		String id = Helper.readString("Enter event id  > ");
		String name = Helper.readString("Enter event name > ");
		String venue = Helper.readString("Enter event venue > ");
		String eventDate = Helper.readString("Enter event date (yyyy-MM-dd) > ");
		int participants = Helper.readInt("Enter event number of participant > ");
		String description = Helper.readString("Enter event description > ");
		

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate eDate = LocalDate.parse(eventDate, dtf);

		Event newEvent = new Event(id, name, venue, eDate, participants, description);
		eventList.add(newEvent);

		System.out.println("New Event successfully added.");

	}

	public static void viewAllEvent(ArrayList<Event> eventList) {

	    String output = String.format("%-10s %-10s %-10 %-10s %-10 %-10s %-10s\n", "ID", "NAME", "VENUE", "EVENT DATE",
	            "PARTICIPANTS", "DESCRIPTION");

	    for (Event event : eventList) {
	        output += String.format("%-10s %-10s %-10 %-10s %-10 %-10d %-10s\n", event.getId(), event.getName(),
	                event.getVenue(), event.getEventDate(), event.getParticipants(), event.getDescription());
	    }

	    System.out.println(output);
	}
	public static void deleteEvent(ArrayList<Event> eventList) {
	    String id = Helper.readString("Enter id to delete > ");
	    boolean found = false; 

	    for (Event event: eventList) {
	        if (event.getId().equals(id)) {
	            found = true;
	            char confirm = Helper.readChar("Are you sure you want to delete the event (y/n) > ");
	            if (Character.toLowerCase(confirm) == 'y') {
	            	eventList.remove(eventList);
	                System.out.println("Event has been deleted successfully.");
	            } else {
	                System.out.println("hi.");
	            }
	            break; 
	        }
	    }

	    if (!found) {
	        System.out.println("Event was not found.");
	    }
	}


}