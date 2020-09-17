
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//extends Order class and implements CustomerMap interface for Hash-Map.
public class userSide extends Order implements CustomerMap{
	
//Constructor with super class variables.
	public userSide(String _orderID, String _name, String _strNumber, String _street, String _area, String _location,
			String _phone, String _restaurant, ArrayList<Integer> _count, ArrayList<String> _food, String _instructions,
			int _total) throws IOException {
		super(_orderID, _name, _strNumber, _street, _area, _location, _phone, _restaurant, _count, _food, _instructions,
				_total);
		// TODO Auto-generated constructor stub
	}

	//main method
	public static void main(String args[]) {
		String name = "";
		String streetNumber = "";
		String street = "";
		String area = "";
		String location = "";
		String number = "";
		String restaurant = "";
		//Labels that will ask the user questions.
		String[] labels = new String[] {"What is your name : ", "What is your street number : ",
				"What is your street name : ", "What area do you live in E.g (Glenvista) : ",
				"What location are you situated in E.g (Durban) : ", "What is your phone number : "};
		
		Scanner input = new Scanner(System.in);
		//Loops through labels and checks which variable to store where.
		for(int i = 0; i < labels.length; i++) {
			System.out.print(labels[i]);
			switch(i) {
			case 0:
				name = input.nextLine();
				break;
			case 1:
				streetNumber = input.nextLine();
				break;
			case 2:
				street = input.nextLine();
				break;
			case 3:
				area = input.nextLine();
				break;
			case 4:
				location = input.nextLine();
				break;
			case 5:
				number = input.nextLine();
				break;
			}
		}
		
		if(number.length != 10){
			System.out.print("Your number is incorrect, please make sure you have no space in-between numbers and the length should be 10 digits");
		}
		else{
				try {
				//creates a new restaurant object.
				Restaurant rest = new Restaurant();
				restaurant = rest.getFood();//calls the method to fetch the menu for the restaurant user picked.
				//Creates a new customer object and stores all the variables in the required parameters.
				Customer user = new Customer(name, streetNumber, street, area, location, number, restaurant, rest.amount, rest.GetInstructions(), rest.getTotal());
				//Stores the name and user object into Hash-Map, that will generate a customer ID.
				map.put(name , "Customer@" + ID);
				//Creates a new order object with same parameters as Customer.
				Order order = new Order(map.get(name).toString(), name, streetNumber, street, area, location, number, restaurant, rest.amount, rest.seeFood(), rest.GetInstructions(), rest.getTotal());
					
				//checks whether the location user is in is valid.
				if(order.isAvailable() == true) {
					System.out.print("The restaurant is on it!, \r\nReceipt printed.");
					order.getReciept();//prints out the receipt.
				}
				else {
					//lets the user know that the location is not supported.
					System.out.print("We dont deliver to " + location + " , please try again later.");
				}
					
				user.getCustomers();
				user.groupLoc();
				order.getReciept();
				order.updateDriver();
				input.close();
			}
			catch(IOException  e) {
				e.printStackTrace( System.out );
			}
		}
	}
}
