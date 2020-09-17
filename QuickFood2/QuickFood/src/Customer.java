
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;


public class Customer extends Restaurant implements CustomerMap{
	protected String Name;
	protected String streetNumber;
	protected String Street;
	protected String Area;
	protected String Location;
	protected String phoneNumber;
	protected String restaurant;
	protected String Instructions;
	protected ArrayList<Integer> count;
	protected int Total;
	ArrayList<String> txtLines = new ArrayList<String>();
	ArrayList<String> Locations = new ArrayList<String>();
	
	//Constructor.
	public Customer(String _name, String _strNumber, String _street, String _area,
			String _location, String _number, String _restaurant,
			ArrayList<Integer> _count, String _instructions, int _total) throws IOException {
		this.Name = _name;
		this.Location = _location;
		this.phoneNumber = _number;
		this.restaurant = _restaurant;
		this.streetNumber = _strNumber;
		this.Street = _street;
		this.Area = _area;
		this.count = _count;
		this.Instructions = _instructions;
		this.Total = _total;
		
	}
	
	public void getCustomers() throws IOException {
		//File Path.
		File path = new File("C:\\Users\\Brandon\\Dropbox\\Brandon -65681\\Immersive Full Stack Web and Software Engineer Bootcamp\\2. Advanced Programming Concepts\\Task 11\\QuickFood2\\customers.txt");
		
		//Adds the current customer.
		txtLines.add(Name + " | " + map.get(Name) + " | " + Location);
		//Checks if path exists.
		if(path.exists()) {
			Scanner scanner = new Scanner(path);
			//checks if text file has lines of text.
			if(scanner.hasNextLine()) {
				
				while(scanner.hasNext()) {
					txtLines.add(scanner.nextLine());//adds lines to array list.
				}
				scanner.close();
				//Sorts array into Alphabetical order.
				txtLines.sort(String::compareToIgnoreCase);
				
				FileWriter customer = new FileWriter(path);
				BufferedWriter out = new BufferedWriter(customer);
				
				for(int i = 0; i < txtLines.size();i++) {
					Locations.add(txtLines.get(i).substring((txtLines.get(i).lastIndexOf(" | ")) + 1, txtLines.get(i).length()));
					customer.write(txtLines.get(i) + "\r\n");
				}
				
				customer.close();
				out.close();
			}
			else {				
				//If no lines are found.
				FileWriter customer = new FileWriter(path);
				BufferedWriter out = new BufferedWriter(customer);
				Locations.add(txtLines.get(0).substring((txtLines.get(0).lastIndexOf(" | ")) + 1, txtLines.get(0).length()));
				customer.write(Name + " | " + map.get(Name) + " | " + Location);
				out.close();
			}
		}
		else {
			//If no file is found.
			Formatter newFile = new Formatter(path);
			Locations.add(txtLines.get(0).substring((txtLines.get(0).lastIndexOf(" | ")) + 1, txtLines.get(0).length()));
			newFile.format(txtLines.get(0));
			newFile.close();
		}
		
	}
	//Groups the users and their location in a new text file.
	public void groupLoc() throws IOException {
		ArrayList<String> users = new ArrayList<String>();
		if(!txtLines.isEmpty()) {

			File path = new File("C:\\Users\\Brandon\\Dropbox\\Brandon -65681\\Immersive Full Stack Web and Software Engineer Bootcamp\\2. Advanced Programming Concepts\\Task 11\\QuickFood2\\GroupedCustomers.txt");
			Formatter in = new Formatter(path);
			for(int i = 0; i < txtLines.size(); i++) {
				users.add(Locations.get(i)  + " | " + txtLines.get(i).substring(0, txtLines.get(i).indexOf(" | ") + 1) + "\r\n");
			}
			users.sort(String::compareToIgnoreCase);
			
			for(int i = 0; i < txtLines.size(); i++) {
				in.format(users.get(i));
			}
			
			in.close();
		}
	}
}
