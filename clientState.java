import java.util.*;
import java.io.*;

public class clientState extends state{
	
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	private static clientState instance;
	private static final int EXIT = 0;
	private static final int SHOW_CLIENT = 1;
	private static final int SHOW_PRODUCT_LIST = 2;
	private static final int SHOW_CLIENT_TRANSACTIONS = 3;
	private static final int ADD_TO_CART = 4;
	private static final int REM_FROM_CART = 5;
	private static final int MOD_FROM_CART= 6;
	private static final int SHOW_WAITLIST = 7;
	private static final int MAKE_ORDER = 8;
	private static final int MAKE_PAYMENT = 9;
	
	private clientState() {
		super();
	}
	
	public static clientState instance() {
		if(instance == null) {
			instance = new clientState();
		}
		return instance;
	}
	
	public int getInt(String prompt) {
	    do {
	      try {
	        String item = getToken(prompt);
	        Integer num = Integer.parseInt(item);
	        return num.intValue();
	      } catch (NumberFormatException nfe) {
	        System.out.println("Please input a number ");
	      }
	    } while (true);
	  }
	
	public String getToken(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
				if(tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			}catch(IOException ioe) {
				System.exit(0);
			}
		}while(true);
	}
	
	public int getCommand() {
		do {
			try {
				int value = Integer.parseInt(getToken("Enter command: "));
				if(value >= EXIT) {
					return value;
				}
			}catch(NumberFormatException nfe) {
				System.out.println("Enter a number");
			}
		}while(true);
	}
	
	public void showClient() {
		String out = wh.getClient((contxt).getUser());
		
		System.out.println(out);
	}
	
	public void showProdList() {
		wh.printProducts();
	}
	
	public void showTransList() {
		wh.getTransactions((contxt).getUser());	
	}
	
	public void addToCart() {
		String prodId = getToken("Enter product ID: ");
		int quantity = Integer.parseInt("Enter product quantity: ");
		
		String out = wh.addToCart((contxt).getUser(), prodId, quantity);
		System.out.println(out);
	}
	
	public void remFromCart() {
		String prodId = getToken("Enter product ID: ");
		
		String out = wh.remFromCart((contxt).getUser(), prodId);
		System.out.println(out);
	}
	
	public void modFromCart() {
		String prodId = getToken("Enter product ID: ");
		int quantity = Integer.parseInt(getToken("Enter new quantity of cart item: "));
		
		String out  = wh.changeCartQuantity((contxt).getUser(), prodId, quantity);
		System.out.println(out);
	}
	
	public void showWaitList() {
		wh.getWaitlist((contxt).getUser());
	}
	
	public void logout() {
		if ((contxt).getLogin() == context.manager) {
		      System.out.println("Returning to Manager");
		      (contxt).changeState(context.MANAGER_STATE);
		            
		    } else if (contxt.getLogin() == context.sales) {
		      System.out.println("Returning to Clerk");
		      (contxt).changeState(context.SALES_STATE);
		            
		    } else if (contxt.getLogin() == context.client) {
		      (contxt).changeState(context.CLIENT_STATE); 
		            
		    } else {
		      (contxt).changeState(context.LOGIN_STATE);
		    }
	}
	
	public void makeOrder(){
		String out = wh.makeOrder((contxt).getUser());
		System.out.println(out);
	}
	
	public void makePayment(){
		double payment = Integer.pareseInt(getToken("Enter payment: "));
		String out = wh.makePayment((cntxt).getUser(), payment);
		
		System.out.println(out);
	}
	
	public void process() {
		int command;
		while((command = getCommand()) != EXIT) {
			switch(command) {
			case SHOW_CLIENT: showClient();
								break;
			case SHOW_PRODUCT_LIST: showProdList();
									break;
			case SHOW_CLIENT_TRANSACTIONS: showTransList();
											break;
			case ADD_TO_CART: addToCart();
								break;
			case REM_FROM_CART: remFromCart();
								break;
			case MOD_FROM_CART: modFromCart();
								break;
			case SHOW_WAITLIST: showWaitList();
								break;
			case MAKE_ORDER: makeOrder();
								break;
			case MAKE_PAYMENT: makePayment();
								break;
			}
		}
		
		logout();
	}
	
	public void run() {
		process();
	}
}
