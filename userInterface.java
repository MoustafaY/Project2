import java.util.*;
import java.text.*;
import java.io.*;
public class userInterface{
	private static userInterface UI;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static final int EXIT = 0;
	private static final int ADD_SUPPLIER = 1;
	private static final int PRINT_SUPPLIER = 2;
	private static final int ADD_PRODUCT = 3;
	private static final int PRINT_PRODUCT = 4;
	private static final int ASSIGN = 5;
	private static final int CHANGE_PROD_IN_SUPP = 6;
	private static final int REM_PROD_FROM_LIST = 7;
	private static final int PRINT_PRODLIST_FROM_SUPP = 8;
	private static final int PRINT_SUPPLIST_FROM_PROD = 9;


	private static wareHouse wh = new wareHouse();
		
	private userInterface() {
		wh = wareHouse.instance();
	}
	public static userInterface instance() {
		if(UI == null) {
			return UI = new userInterface();
		}else {
			return UI;
		}
	}
	
	private boolean yesOrNo(String prompt) {
	    String more = getToken(prompt + " (Y|y)[es] or anything else for no");
	    if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
	      return false;
	    }
	    return true;
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
	
	
	//Functions here
	
	//Supplier Class
	public void addSupplier() {
		String name = getToken("Enter supplier name: ");
		String out = wh.addSupplier(name);
		System.out.println(out);
	}
	
	
	public void printSupplier() {
		wh.printSuppliers();
	}
	
	public void assign() {//assign product to supplier
		String suppId = getToken("Enter supplier Id: ");
		String prodId = getToken("Enter product Id: ");
		
		String out = wh.assign(suppId, prodId);
		System.out.println(out);
	}
	
	public void changeProdInSupp() {
		String suppId = getToken("Enter supplier Id: ");
		String prodId = getToken("Enter product Id: ");
		int quantity = Integer.parseInt(getToken("Enter new quantity: "));
		
		String out = wh.changeProdInSupp(suppId, prodId, quantity);
		System.out.println(out);
	}
	
	public void remProdFromSupp() {
		String suppId = getToken("Enter supplier Id: ");
		String prodId = getToken("Enter product Id: ");
		
		String out = wh.remProdFromSupp(suppId, prodId);
		System.out.println(out);
		
	}
	
	public void printProdFromSupp() {
		String suppId = getToken("Enter supplier Id: ");
		
		wh.printProdFromSupp(suppId);
	}
	
	//Product class
	public void addProduct() {
		String name = getToken("Enter product name: ");
		int quantity = Integer.parseInt(getToken("Enter quantity: "));
		double purchasePrice = Integer.parseInt(getToken("Enter purchase price: "));
		
		String out = wh.addProduct(name, quantity, quantity);
		System.out.println(out);
	}
	
	
	public void printProduct() {
		wh.printProducts();
	}
	
	public void printSuppFromProd() {
		String prodId = getToken("Enter product Id: ");
		
		wh.printSuppFromProd(prodId);
	}
	
	
	/*---------------*/

	

	
	public void process() {
		int command;
		while((command = getCommand()) != EXIT) {
			switch(command) {
			case ADD_SUPPLIER: addSupplier();
							   break;
			case PRINT_SUPPLIER: printSupplier();
								 break;
			case ADD_PRODUCT: addProduct();
							  break;
			case PRINT_PRODUCT: printProduct();
								break;
			case ASSIGN: assign();
									break;
			case CHANGE_PROD_IN_SUPP: changeProdInSupp();
										break;
			case REM_PROD_FROM_LIST: remProdFromSupp();
										break;
			case PRINT_PRODLIST_FROM_SUPP: printProdFromSupp();
											break;
			case PRINT_SUPPLIST_FROM_PROD: printSuppFromProd();
											break;
			}
		}
	}
	
	public static void main(String[] s) {
		userInterface.instance().process();
	}
}
