import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public interface CustomerMap {
	//map that will store the OrderID from each customer.
	Map<String, String> map = new HashMap<>();
	String ID = getID();
	
	private static String getID() {
		Random rand = new Random();
		return Integer.toString(rand.nextInt(50)) + 10 + "bc" + Integer.toString(rand .nextInt(100000));
	}

}
