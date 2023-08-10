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
	public static Bike inputBike() {
		int id= Helper.readInt("Enter bike id > ");
		String model = Helper.readString("Enter bike model > ");
		String colour = Helper.readString("Enter bike's colour > ");
		double weight = Helper.readDouble("Enter bike's weight > ");
		Bike x = new Bike(id,model, colour,weight);
		return x;
	}
	public static boolean addBike(ArrayList<Bike> bikeList,Bike x) {
		Bike bike;
		for(int i = 0; i < bikeList.size(); i++) {
			bike=bikeList.get(i);
			if(bike.getId()==x.getId()) {
				return false;
			}
		}
		if(x.getId()==0||x.getModel().isEmpty()||x.getColour().isEmpty()||x.getWeight()==0) {
			return false;
		}
		else {
		bikeList.add(x);
		System.out.println("Bike has been successfully added");
		return true;
		}
		}
		

	public static String viewAllBike(ArrayList<Bike> bikeList) {

		String output = String.format("%-10s %-10s %-10s %-10s\n", "ID", "MODEL", "COLOUR", "WEIGHT");

		for (Bike bike : bikeList) {
			output += String.format("%-10d %-10s %-10s %-10.2f\n", bike.getId(), bike.getModel(),
					bike.getColour(), bike.getWeight());
		}

		System.out.println(output);
		return output;
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
					bikeList.remove(bike);
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
