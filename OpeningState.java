/*******************************************************************************
OpeningState.java (Menu for System Essentially)
Responsible individual: 
Provides Interface to chose the users role
	- typeing exit can be used to quit any state
	- higher authorities may log into lower permissions
		+ after exiting the user will return to the origional role
Options:
	1. Client
	2. Clerk
	3. Manager
	q. quit
********************************************************************************/
import Source_Code.*;
import java.util.*;
import java.io.*;
import java.lang.*;

public class OpeningState {
	final static String MAINMENU =  ""+
	        "SELECT STATE          \n\t"+
	          "1. Client Menu State\n\t"+
	          "2. Clerk Menu State\n\t"+
	          "3. Manager Menu State\n\t" +
			  "q. Quit the program\n\n";
	final static String FILENAME = "WareData";

  public static void main(String args[]){
	  Warehouse warehouse = openWarehouse(FILENAME);
      Scanner s = new Scanner(System.in);
	  boolean notDone = true;
	  while(notDone){
		  System.out.println(MAINMENU);
		  String choice = s.nextLine();
		  switch(choice){
			case "1":
			  ClientMenuState.processInput(warehouse);
			  break;
			case "2":
			  ClerkMenuState.processInput(warehouse);
			  break;
			case "3":
			  ManagerMenuState.performMenu(warehouse);
			  break;
			case "exit":
			case "q":
				notDone = false;
				break;
			default:
			  System.out.println("ERROR: invalid option, exiting program");
		  }//end switch
	  }//end while
	  saveChanges(FILENAME, warehouse);
	}//end main

	/******************************************************************************
	saveChanges
	Saves any changes made to the warehouse.
	******************************************************************************/
	public static void saveChanges(String file, Warehouse warehouse){
		if(warehouse.saveData(file))
				System.out.println("Saved successfully");
		else
			System.out.println("Save failed. Error occured");
	}//end saveChanges

	/*************************************************************
	openWarehouse
	Given a filename, attempts to open the warehouse file
	If not found, then it creates a new warehouse
	Returns a warehouse object.
	**************************************************************/
	private static Warehouse openWarehouse(String file){
		Warehouse w;
		try{
			w = Warehouse.retrieveData(file);
			if(w == null){
				System.out.println("Empty file. Creating new warehouse.");
				w = Warehouse.instance();
			} else
				System.out.println("Warehouse successfully read from file.");
		} catch(IOException ioe){
			w = Warehouse.instance();
			System.out.println("Warehouse file not found. Creating new warehouse");
		}

		return w;
	}//end openWarehouse
}
