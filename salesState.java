import java.util.*;
import java.io.*;

public class salesState extends state{
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	private static salesState instance;
	private static final int EXIT = 0;
	private static final int ADD_CLIENT = 1;
	private static final int SHOW_PRODUCTS = 2;
	private static final int SHOW_CLIENTS = 3;
	private static final int SHOW_CLIENTS_OUT = 4;
	private static final int BECOME_CLIENT = 5;
	private static final int SHOW_WAITLIST = 6;
	private static final int RECEIVE_SHIPMENT = 7;
	
	private salesState() {
		super();
	}
	
	public static salesState instance() {
		if(instance == null) {
			instance = new salesState();
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
	
	public void addClient() {
		
		String name = getToken("Enter client name: ");

		
		String out = wh.addClient(name);
		
		System.out.println(out);
	}
	
	public void showProducts() {
		wh.printProducts();
	}
	
	public void showClient() {
		wh.printClients();	
	}
	
	public void printClientsOut() {
		wh.getOutClient();
	}
	
	public void becomeClient() {
		String clientId = getToken("Enter client ID: ");
		
		(contxt).setUser(clientId);
		(contxt).changeState(context.CLIENT_STATE);
	}
	
	public void printWaitList() {
		String prodId = getToken("Enter product ID: ");
		
		wh.printWaitListClients(prodId);
	}
	
	public void receiveShipment() {
		String prodId = getToken("Enter product ID: ");
		int quantity = Integer.parseInt(getToken("Enter quantity of product: "));
		
		String out = wh.acceptShipment(prodId, quantity);
		System.out.println(out);
	}
	
	public void logout() {
		if((contxt).getLogin() == context.manager) {
			System.out.println("Returning to manager");
			(contxt).changeState(contxt.MANAGER_STATE);
		}
		else {
			(contxt).changeState(context.SALES_STATE);
		}
	}
	
	public void process() {
		int command;
		while((command = getCommand()) != EXIT) {
			switch(command) {
			case ADD_CLIENT: addClient();
								break;
			case SHOW_PRODUCTS: showProducts();
									break;
			case SHOW_CLIENTS: showClient();
											break;
			case SHOW_CLIENTS_OUT: printClientsOut();
								break;
			case BECOME_CLIENT: becomeClient();
								break;
			case SHOW_WAITLIST: printWaitList();
								break;
			case RECEIVE_SHIPMENT: receiveShipment();
								break;
			}
		}
		
		logout();
	}
	
	public void run() {
		process();
	}
}