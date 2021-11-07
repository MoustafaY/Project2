import java.util.*;
import java.io.*;

public class managerState extends state{
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	private static managerState instance;
	private static final int EXIT = 0;
	private static final int ADD_PRODUCT = 1;
	private static final int ADD_SUPPLIER = 2;
	private static final int SHOW_SUPPLIERS = 3;
	private static final int SHOW_SUPPLIERS_FOR_PRODUCT = 4;
	private static final int SHOW_PRODUCTS_FOR_SUPPLIER = 5;
	private static final int UPDATE_PRODUCTS = 6;
	private static final int BECOME_CLERK = 7;
	
	private managerState() {
		super();
	}
	
	public static managerState instance() {
		if(instance == null) {
			instance = new managerState();
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
	
	public void addProduct() {
		
		String name = getToken("Enter product name: ");
		int quantity = Integer.parseInt(getToken("Enter quantity: "));
		double price = Integer.parseInt(getToken("Enter price: "));
		
		String out = wh.addProduct(name, quantity, price);
		
		System.out.println(out);
	}
	
	public void addSupplier() {
		String name = getToken("Enter supplier name: ");
		
		String out = wh.addSupplier(name);
		System.out.println(out);
	}
	
	public void showSuppliers() {
		wh.printSuppliers();	
	}
	
	public void showSuppFromProd() {
		String prodId = getToken("Enter product ID: ");
		
		wh.printSuppFromProd(prodId);
	}
	
	public void showProdFromSupp() {
		String suppId = getToken("Enter supplier ID: ");
		
		wh.printProdFromSupp(suppId);
	}
	
	public void updateProduct() {
		String suppId = getToken("Enter supplier ID: ");
		
		wh.printProdFromSupp(suppId);
		
		String prodId = getToken("Enter product ID: ");
		String out;
		double price;
		
		int cmd = Integer.parseInt(getToken("Enter update command: (1 to add product) (2 to remove product) (3 to change quantity of product)"));
		if(cmd == 1) {
			out = wh.assign(suppId, prodId);
			System.out.println(out);
		}
		else if(cmd == 2) {
			out = wh.remProdFromSupp(suppId, prodId);
			System.out.println(out);
		}
		else if(cmd == 3) {
			price = Integer.parseInt(getToken("Enter new price of product: "));
			out = wh.setProdQuantity(suppId, prodId, price);
			System.out.println(out);
		}
	}
	
	public void becomeClerk() {
		(contxt).changeState(contxt.SALES_STATE);
	}
	
	public void logout() {
		(contxt).changeState(context.MANAGER_STATE);
	}
	
	public void process() {
		int command;
		while((command = getCommand()) != EXIT) {
			switch(command) {
			case ADD_PRODUCT: addProduct();
								break;
			case ADD_SUPPLIER: addSupplier();
									break;
			case SHOW_SUPPLIERS: showSuppliers();
											break;
			case SHOW_SUPPLIERS_FOR_PRODUCT: showSuppFromProd();
								break;
			case SHOW_PRODUCTS_FOR_SUPPLIER: showProdFromSupp();
								break;
			case UPDATE_PRODUCTS: updateProduct();
								break;
			case BECOME_CLERK: becomeClerk();
								break;
			}
		}
		
		logout();
	}
	
	public void run() {
		process();
	}
}