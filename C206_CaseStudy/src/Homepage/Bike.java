package Homepage;
import java.util.ArrayList;
import java.util.regex.Pattern;

import Helper.Helper;
public class Bike {
	private int id;
	private String model;
	private String colour;
	private double weight;

	public Bike(int id, String model, String colour, double weight) {
		this.id = id;
		this.model = model;
		this.colour = colour;
		this.weight = weight;

	}

	public int getId() {
		return id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public double getWeight() {
		return weight;
	}
	

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public static void addBike(ArrayList<Bike> bikeList) {
		String check= "[a-zA-z_0-9]+";
		int id=bikeList.size()+1;
		String model = Helper.readStringRegEx("Enter bike model > ", check);
		String colour = Helper.readStringRegEx("Enter bike's colour > ", check);
		String weight = Helper.readStringRegEx("Enter bike's weight > ", check);
		double weights=Double.parseDouble(weight);
		Bike newBike = new Bike(id,model, colour,weights);
		bikeList.add(newBike);

		System.out.println("Bike has been successfully added");

	}

	public static void viewAllBike(ArrayList<Bike> bikeList) {

		String output = String.format("%-10s %-10s %-10 %-10s\n", "ID", "MODEL", "COLOUR", "WEIGHT");

		for (Bike bike : bikeList) {
			output += String.format("%-10d %-10s %-10 %-10.2f\n", bike.getId(), bike.getModel(),
					bike.getColour(), bike.getWeight());
		}

		System.out.println(output);
	}

	public static void deleteBike(ArrayList<Bike> bikeList) {
		viewAllBike(bikeList);
		int id = Helper.readInt("Enter id to delete > ");
		boolean found = false;

		for (Bike bike : bikeList) {
			if (bike.getId() == id) {
				found = true;
				char confirm = Helper.readChar("Are you sure you want to delete the event (y/n) > ");
				if (Character.toLowerCase(confirm) == 'y') {
					bikeList.remove(bike.getId());
					System.out.println("Event has been deleted successfully.");
				} else {
					System.out.println("hi.");
				}
				break;
			}
		}

		if (!found) {
			System.out.println("Bike was not found.");
		}
	}
}
