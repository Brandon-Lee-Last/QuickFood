import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Restaurant implements CustomerMap{
	private int choice;
	private String Restaurant;
	//stores the menu in a array.
	private String[] foodMenu = new String[] {"1 : Chips & buddy | R59", "2 : Combo Meal + Coke | R79", "3 : Plain Juice | R18", "1 : Beef Burger Meal | R74", "2 : Steers Chips | R32", "3 : 1l Coke | R21", "1 : Medium Pepperoni Pizza | R98", "2 : Small Foldover Pizza | R62", "3 : Chocolate Pizza | R52"};
	protected String Foods[] = new String[3];
	protected ArrayList<String> Food = new ArrayList<String>();
	protected ArrayList<Integer> amount = new ArrayList<Integer>();
	private String SpecialIntructions;
	private boolean repeat = true;
	protected int Total = 0;
	
	//Method that will accept the order.
	public String getFood() {
		System.out.print("Please Pick a Restaurant \r\n1 : Mochachos \r\n2 : Steers \r\n3 : Pizza Perfect \r\n");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter asnwer Here \r\n-------> ");
		choice = input.nextInt();
		switch(choice) {
		case 1:
			Restaurant = "Mochachos";
			for(int i =0; i < 3;i++) {
				Foods[i] = foodMenu[i];
			}
			break;
		case 2:
			Restaurant = "Steers";
			for(int i =3; i < 6;i++) {
				Foods[i-3] = foodMenu[i];
			}
			break;
		case 3:
			Restaurant = "Pizza Perfect";
			for(int i =6; i < 9;i++) {
				Foods[i-6] = foodMenu[i];
			}
			break;
		}
		
		while(repeat == true) {
			System.out.print("Menu\r\n");
			for(int i =0; i < Foods.length;i++) {
				System.out.print(Foods[i] + "\r\n");
			}
			
			System.out.print("\r\nWhat would you like from " + Restaurant + "\r\n-------> ");
			choice = input.nextInt();
			
			switch(choice) {
			case 1:
				Food.add(Foods[0].substring(3, Foods[0].length()));
				break;
			case 2:
				Food.add(Foods[1].substring(3, Foods[1].length()));
				break;
			case 3:
				Food.add(Foods[2].substring(3, Foods[2].length()));
				break;
			}
			
			System.out.print("How many orders? \r\n-------> ");
			amount.add(input.nextInt());
			
			@SuppressWarnings("resource")
			Scanner textInput = new Scanner(System.in);
			System.out.print("Please enter any special instructions, if you have none please enter none.\r\n");
			SpecialIntructions = textInput.nextLine();
			
			System.out.print("Will that be all?, yes/no \r\n-------> ");
			String exit = textInput.nextLine();
			
			if(exit.toLowerCase().contains("no") || exit.toLowerCase().contains("yes")) {
				if(exit.toLowerCase().contains("yes")) {
					repeat = false;
				}
				else if(exit.toLowerCase().contains("no")){
					repeat = true;
				}
			}
			else{
				System.out.print("We did not understand you !");
				repeat = false;
			}
		}
		return Restaurant;
	}
	
	//gets the amount of orders.
	public ArrayList<Integer> getAmount() {
		return amount;
	}
	
	//gets the Food list.
	public ArrayList<String> seeFood() {
		return Food;
	}
	
	//gets the instructions given by user.
	public String GetInstructions() {
		return SpecialIntructions;
	}
	
	//gets the total amount.
	public int getTotal() {
		int[] nums = new int[Food.size()];;
		for(int i =0;i<Food.size();i++) {
			String letters = Food.get(i).substring(Food.get(i).length()-2, Food.get(i).length());
			nums[i] = Integer.parseInt(letters) * amount.get(i);
		}
		
		int sum = IntStream.of(nums).sum();
		Total = sum;
		return Total;
	}
}
