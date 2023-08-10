package Homepage;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import Helper.Helper;

public class Registration {

  private String event;
  private LocalDateTime registrationDateTime;
  private int eventRegisteredID;
  private int id;
  private String name;
  
  public Registration(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public Registration(String event) {

    this.event = event;

  }

  public String getEvent() {
    return event;
  }

  public LocalDateTime getRegistrationDateTime() {
    return registrationDateTime;
  }
  public int getId() {
    return id;
  }
  public String getName() {
    return name;
  }

  public void display() {
    System.out.println("Events Registered: " + event);
  }
  
  
  public int getEventRegisteredID() {
    return eventRegisteredID;
    
  }
  public static String displayAllRegistration (ArrayList <Registration> registrationsList) {
    // TODO Auto-generated method stub
    System.out.println( String.format("%-10s %-20s", "ID", "NAME"));
    String output ="";

      for (Registration NewEvent : registrationsList) {
           output += String.format("\n%-10d %-20s",NewEvent.getId(), NewEvent.getName());
      }

      System.out.println(output);
	return output;
  }


  public static void addEventRegistrations(ArrayList<Registration> registrationsList, Registration newEvent) {
    Helper.line(70, "-");

  registrationsList.add(newEvent);

  System.out.println("New Event successfully added.");
  }
  

  
  
   public static void deleteEventRegistration(ArrayList<Registration> registrationsList, int registeredEventID, char confirm) {
          // Implement method to delete a bike
	   	
          boolean found = false;

          for (Registration r : registrationsList) {
            if (r.getId()==registeredEventID) {
                found = true;
	            if (confirm == 'n') {
	                confirm = Helper.readChar("Are you sure you want to delete the event (y/n) > ");
	                if (Character.toLowerCase(confirm) == 'y') {
	                  registrationsList.remove(r);
	                    System.out.println("Event has been deleted successfully.");
	                } else {
	                    System.out.println("hi.");
	                }
	                break; 
	            }
	            else if(confirm == 'y') {
	            	registrationsList.remove(r);
	            }
            }
            
        }

        if (!found) {
            System.out.println("Event was not found.");
        }
    }
  

  }