import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Scanner;

//Extends Customer and Implements CustomerMap.
public class Order extends Customer implements CustomerMap{

	private String OrderID;
	private int[] deliveries;
	private String Driver;
	private String foodList = "";
	protected ArrayList<String> Drivers = new ArrayList<String>();
	private ArrayList<String> availableDrivers = new ArrayList<String>();
	private ArrayList<String> food = new ArrayList<String>();
	public boolean available = false;
	File path = new File("C:\\Users\\Brandon\\Dropbox\\Brandon -65681\\Immersive Full Stack Web and Software Engineer Bootcamp\\2. Advanced Programming Concepts\\Task 11\\QuickFood2\\drivers.txt");
	
	//Constructor.
	public Order(String _orderID, String _name, String _strNumber, String _street,
			String _area,  String _location, int _phone, String _restaurant,
			ArrayList<Integer> _count, ArrayList<String> _food, String _instructions, int _total) throws IOException {
		
		super(_name, _strNumber, _street, _area, _location, _phone, _restaurant, _count, _instructions, _total);
		this.OrderID = _orderID;
		this.food = _food;
	}
	
	//Method that checks if the Driver is available.
	protected boolean isAvailable() throws FileNotFoundException {
		@SuppressWarnings("resource")
		Scanner drivers = new Scanner(path);
		
		//loops through the drivers line by line.
		while(drivers.hasNextLine()) {
			Drivers.add(drivers.nextLine());
		}
		
		//loops though Drivers and gets the drivers users area.
		for(int i =0;i<Drivers.size();i++) {
			String driverLocation = Drivers.get(i).substring(Drivers.get(i).indexOf(',') + 3, Drivers.get(i).length() - 3);
			if(Location.toLowerCase().contains(driverLocation.toLowerCase())) {
				availableDrivers.add(Drivers.get(i));
			}
		}
		
		//checks if there are drivers available.
		if(availableDrivers.size() != 0){
			available = true;
		}
		
		return available;
	}
	
	//gets the driver and the amount of deliveries.
	private String getDriver() throws FileNotFoundException {
		if(isAvailable() == true) {
			
			for(int i = 0; i< availableDrivers.size();i++) {
				if(Character.isDigit(availableDrivers.get(i).charAt(availableDrivers.get(i).length()-1))) {
					deliveries = new int[availableDrivers.size()];
					
					for(int j = 0; j<availableDrivers.size();j++) {
						//stores the amount of deliveries.
						String char1 = Character.toString(availableDrivers.get(j).charAt(availableDrivers.get(j).length()-2));
							
						String char2 = Character.toString(availableDrivers.get(j).charAt(availableDrivers.get(j).length()-1));
							
						String num = char1 + char2;
							
						if(num.contains(" ")) {
								deliveries[j] = Integer.parseInt(char2);
						}
						else {
								deliveries[j] = Integer.parseInt(num);
						}
					}
					//sorts them from smallest to biggest.
					Arrays.sort(deliveries);
					
						
					//gets the driver.
					for(int j =0;j<availableDrivers.size();j++) {
						if(availableDrivers.get(j).substring(availableDrivers.get(j).length()-2, availableDrivers.get(j).length()).contains(" ")) {
							if(Character.getNumericValue(availableDrivers.get(j).charAt(availableDrivers.get(j).length() -1)) == deliveries[0]) {
								Driver = availableDrivers.get(j).substring(0, availableDrivers.get(j).indexOf(','));
							}
						}
						else {
							if(availableDrivers.get(j).substring(availableDrivers.get(j).length()-2, availableDrivers.get(j).length()) == Integer.toString(deliveries[0])) {
									Driver = availableDrivers.get(j).substring(0, availableDrivers.get(j).indexOf(','));
							}
						}
					}
					
				}
				
		else {
				Driver = availableDrivers.get(i).substring(0, availableDrivers.get(i).indexOf(','));
			}
		}
	}
	else {
			Driver = "There are no drivers in that Area, please try again.";
	}
	return Driver;
}
	
	//gets the food ordered and multiplies if needed.
	private String foodList() {
		foodList = count.get(0) + " X " + food.get(0) + "\r\n";
		
			for(int i=1 ;i<food.size() ;i++) {
				foodList += count.get(i) + " X " + food.get(i) + "\r\n";
			}
			
			
		return foodList;
	}
	
	//Method that will update the drivers package count.
	public void updateDriver() {
		ArrayList<String> line = new ArrayList<String>();
		try {
			Scanner in = new Scanner(path);
			while(in.hasNextLine()) {
				String word = in.nextLine();
				//Checks for the current driver and then updates the package.
				if(word.contains(Driver)) {
					line.add(Driver + ", " + Location + ", " + Math.addExact(deliveries[0], 1));
				}
				else{
					line.add(word);
				}
			}
			in.close();
			
			//Rewrites the text file and updates it.
			Formatter overrideFile = new Formatter("C:\\Users\\Brandon\\Dropbox\\Brandon -65681\\Immersive Full Stack Web and Software Engineer Bootcamp\\2. Advanced Programming Concepts\\Task 11\\QuickFood2\\drivers.txt");
			
			for(int i = 0; i < line.size(); i++) {
				if(line.get(i) != null) {
					overrideFile.format(line.get(i) + "\r\n");
				}
			}
			overrideFile.close();
		}
	    catch( IOException e ) {
	        e.printStackTrace( System.out );
	    }
	}
	
	//Gets the receipt of the order, then it will print it to a text file.
	public void getReciept() throws FileNotFoundException {
		if(isAvailable() == true) {
			Formatter reciept = new Formatter("C:\\Users\\Brandon\\Dropbox\\Brandon -65681\\Immersive Full Stack Web and Software Engineer Bootcamp\\2. Advanced Programming Concepts\\Task 7\\Task 2\\reciept.txt");
			reciept.format("Order ID : " + OrderID + "\r\n" + 
			"Customer : " + Name + "\r\n" +
			"Phone Number : " + phoneNumber + "\r\n" + 
			" \r\nYou have ordered the following from " + restaurant + " " + Location  + "\r\n" +
			"\r\n" + foodList() + "\r\n" + "Special Instructions : " + Instructions + "\r\n" +
			"\r\nTotal : " + "R" + Total + "\r\n" +
			getDriver() + " is nearest to the restaurant and so he/she will be delivering your\r\n" + 
					"order to you at: " + streetNumber + " " + Street + " " + Area +
					"\r\n" + "\r\nIf you need to contact the restaurant, their number is 098 765 4321.");
			reciept.close();
		}
	}
}
