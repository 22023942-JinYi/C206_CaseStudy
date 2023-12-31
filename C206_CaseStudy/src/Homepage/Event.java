package Homepage;

import java.time.LocalDate;
import java.util.ArrayList;

import Helper.Helper;

import java.time.format.DateTimeFormatter;

public class Event {

	private int id;
	private String name;
	private String venue;
	private LocalDate eventDate;
	private int participants;
	private String description;

	public Event(int id, String name, String venue, LocalDate eventDate, int participants, String description) {
		this.id = id;
		this.name = name;
		this.venue = venue;
		this.eventDate = eventDate;
		this.participants = participants;
		this.description = description;

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

	public static Event inputevent() {
		int id = Helper.readInt("Enter event id  > ");
		String name = Helper.readString("Enter event name > ");
		String venue = Helper.readString("Enter event venue > ");
		String eventDate = Helper.readString("Enter event date (yyyy-MM-dd) > ");
		int participants = Helper.readInt("Enter event number of participants > ");

		String description = Helper.readString("Enter event description > ");

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate eDate = LocalDate.parse(eventDate, dtf);

		Event newEvent = new Event(id, name, venue, eDate, participants, description);
		return newEvent;

	}

	public static boolean addEvent(ArrayList<Event> eventList, Event newEvent) {

		// Check for duplicate ID
		for (Event event : eventList) {
			if (event.getId() == newEvent.getId()) {
				System.out.println("An event with the same ID already exists. Cannot add a duplicate event.");
				return false;
			}
		}
		if (newEvent.getId() == 0 || newEvent.getName().isEmpty() || newEvent.getVenue().isEmpty()
				|| newEvent.getParticipants() == 1 || newEvent.getDescription().isEmpty()) {
			return false;
		} else {
			eventList.add(newEvent);
			System.out.println("New Event successfully added.");
			return true;

		}

	}

	public static String viewAllEvent(ArrayList<Event> eventList) {
		String output = String.format("%-10s %-20s %-20s %-12s %-12s %-30s%n", "ID", "NAME", "VENUE", "EVENT DATE",
				"PARTICIPANTS", "DESCRIPTION");

		for (Event event : eventList) {
			output += String.format("%-10d %-20s %-20s %-12s %-12d %-30s%n", event.getId(), event.getName(),
					event.getVenue(), event.getEventDate(), event.getParticipants(), event.getDescription());
		}

		System.out.println(output);
		return output;
	}

	public static int iptEvtDel() {
		int id = Helper.readInt("Enter id to delete > ");
		return id;
	}

	public static char confEvtDel() {
		char confirm = Helper.readChar("Are you sure you want to delete the event (y/n) > ");
		return confirm;
	}

	public static boolean deleteEvent(ArrayList<Event> eventList, int id, char confirm) {
		viewAllEvent(eventList);
		boolean found= false;

		for (Event event : eventList) {
			if (event.getId() == id) {
				found = true;
				if (Character.toLowerCase(confirm) == 'y') {
					eventList.remove(event);
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
		return found;
	}

}
