import java.util.*;
import java.io.*;

public class loginState extends state{
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	private static loginState instance;
	private static final int EXIT = 0;
	private static final int BECOME_CLIENT = 1;
	private static final int BECOME_CLERK = 2;
	private static final int BECOME_MANAGER = 3;

	
	private loginState() {
		super();
	}
	
	public static loginState instance() {
		if(instance == null) {
			instance = new loginState();
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
				int value = Integer.parseInt(getToken("Enter command: (1 to be client) (2 to be clerk) (3 to be manager) "));
				if(value >= EXIT) {
					return value;
				}
			}catch(NumberFormatException nfe) {
				System.out.println("Enter a number");
			}
		}while(true);
	}
	
	public void becomeManager() {
		System.out.println("Switching to manager");
		(contxt).setLogin(context.manager);
		(contxt).changeState(0);
	}
	
	public void becomeClerk() {
		System.out.println("Switching to clerk");
		(contxt).setLogin(context.sales);
		(contxt).changeState(1);
	}
	
	public void becomeClient() {
		
		String clientId = getToken("Enter client ID: ");
		System.out.println("Switching to client");
		(contxt).setLogin(context.client);
		(contxt).setUser(clientId);
		(contxt).changeState(contxt.CLIENT_STATE);
	}
	
	
	public void process() {
		int command;
		while((command = getCommand()) != EXIT) {
			switch(command) {
			case BECOME_CLIENT: becomeClient();
								break;
			case BECOME_CLERK: becomeClerk();
									break;
			case BECOME_MANAGER: becomeManager();
											break;
			}
		}
		
		(contxt).changeState(3);
	}
	
	public void run() {
		process();
	}
}