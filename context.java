import java.util.*;
import java.io.*;
import java.text.*;

public class context{
	
	private int currentState;
	private static context contxt;
	private int currentUser;
	private String userId;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	public static final int manager = 0;
	public static final int sales = 1;
	public static final int client = 2;
	public static final int MANAGER_STATE = 0;
	public static final int SALES_STATE = 1;
	public static final int CLIENT_STATE = 2;
	public static final int LOGIN_STATE = 3;
	
	private state[] states;
	private int[][] nextState;
	
	public static context instance() {
		if(contxt == null) {
			contxt = new context();
		}
		return contxt;
	}
	
	public String getToken(String prompt) {
	    do {
	      try {
	        System.out.println(prompt);
	        String line = reader.readLine();
	        StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
	        if (tokenizer.hasMoreTokens()) {
	          return tokenizer.nextToken();
	        }
	      } catch (IOException ioe) {
	        System.exit(0);
	      }
	    } while (true);
	  }
	
	public void setLogin(int num) {
		currentUser = num;
	}
	
	public int getLogin() {
		return currentUser;
	}
	
	public void setUser(String id) {
		userId = id;
	}
	
	public String getUser() {
		return userId;
	}
	
	private context() {
		
		//Setting up states
		states = new state[4];
		states[0] = managerState.instance();//assigning states to java file states
		states[1] = salesState.instance();
		states[2] = clientState.instance();
		states[3] = loginState.instance();
		
		//Setting up transition table
		nextState = new int[6][6];
		//Transition from manager state
		nextState[MANAGER_STATE][MANAGER_STATE] = LOGIN_STATE;
		nextState[MANAGER_STATE][SALES_STATE] = SALES_STATE;
		nextState[MANAGER_STATE][CLIENT_STATE] = -1;
		nextState[MANAGER_STATE][LOGIN_STATE] = -1;
		//Transition from sales state
		nextState[SALES_STATE][MANAGER_STATE] = MANAGER_STATE;
		nextState[SALES_STATE][SALES_STATE] = LOGIN_STATE;
		nextState[SALES_STATE][CLIENT_STATE] = CLIENT_STATE;
		nextState[SALES_STATE][LOGIN_STATE] = -1;
		//Transition from client state
		nextState[CLIENT_STATE][MANAGER_STATE] = -1;
		nextState[CLIENT_STATE][SALES_STATE] = SALES_STATE;
		nextState[CLIENT_STATE][CLIENT_STATE] = LOGIN_STATE;
		nextState[CLIENT_STATE][LOGIN_STATE] = LOGIN_STATE;
		//Transition from login state
		nextState[LOGIN_STATE][MANAGER_STATE] = MANAGER_STATE;
		nextState[LOGIN_STATE][SALES_STATE] = SALES_STATE;
		nextState[LOGIN_STATE][CLIENT_STATE] = CLIENT_STATE;
		nextState[LOGIN_STATE][LOGIN_STATE] = -2;
		
		currentState = LOGIN_STATE;
		
	}
	
	public void changeState(int transition) {
		currentState = nextState[currentState][transition];
		if(currentState == -1) {
			System.out.println("Invalid input");
			terminate();
		}
		if(currentState == -2) {
			terminate();
		}
		states[currentState].run();
	}
	
	public void terminate() {
		System.out.println("End of program");
		System.exit(0);
	}
	
	
	public void process() {
		states[currentState].run();
	}
	
	public static void run() {
		contxt.instance().process();
	}
}
