package Homepage;


import java.time.LocalDateTime;
import java.util.ArrayList;
import Helper.Helper;

public class Registration {

  private String event;
  private LocalDateTime registrationDateTime;
  private int eventRegisteredID;

  public Registration(String event) {

    this.event = event;

  }

  public String getEvent() {
    return event;
  }

  public LocalDateTime getRegistrationDateTime() {
    return registrationDateTime;
  }

  public String getName() {
    return null;
  }

  public void display() {
    System.out.println("Events Registered: " + event);
  }
  
  
  public int getEventRegisteredID() {
    return eventRegisteredID;
    
  }
  private static void displayAllRegistration (ArrayList <Registration> registrationsList) {
    // TODO Auto-generated method stub
    if (registrationsList.isEmpty()) {
      System.out.println("No registrations in the list.");
    } else {
      System.out.println("All Registrations:");
      for (Registration r : registrationsList) {
        r.display();
        
        
        
      }
    }

  }

  public static void addEventRegistrations(ArrayList<Event> events, ArrayList<Registration> registrationsList) {
    Helper.line(70, "-");
    String name = Helper.readString("Enter Event Name > ");

    // Check if the event with the same name already exists

    boolean register = false;
    for (Event event : events) {
      if (event.getName().equalsIgnoreCase(name)) {
        registrationsList.add(new Registration(event.getName()));
        register = true;
        break;
      }
    }

    if (register == true) {
      System.out.println("Event Registered!");
    } else {
      System.out.println("Invalid event registered!");
    }
  }
   public static void deleteEventRegistration(ArrayList<Registration> registrationsList) {
          // Implement method to delete a bike
          int registeredEventID = Helper.readInt("Enter the registeredEventID to delete: ");
          boolean found = false;

          for (Registration r : registrationsList) {
              if (r.getEventRegisteredID() == registeredEventID) {
                  registrationsList.remove(r);
                  System.out.println("Event Registration deleted successfully!");
                  found = true;
                  break;
              }
          }

          if (!found) {
              System.out.println("Registered event not found with the given ID!");
          }
      }
}
