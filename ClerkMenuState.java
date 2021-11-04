/*****************************************************************
ClerkMenuState.java
(a)    Add a client. Gets details of new client; calls method on Façade.
(b)    Show list of products with quantities and sale prices.  The state invokes a method on Facade to get an iterator, and then extracts the needed information.
(c)     Show list of clients. The state invokes a method on Facade to get an iterator, and then extracts the needed information.
(d)    Show list of clients with outstanding balance. The state invokes a method on Facade to get an iterator, and then extracts the needed information.
(e)    Become a client. The actor will be asked to input a ClientID; if valid, this ID will be stored in Context, and the system transitions to the  ClientMenuState.
(f)      Display the waitlist for a product. The state asks the actor for productid; calls method on Façade to get an iterator.
(g)    Receive a shipment. The state asks the actor for productid and quantity; calls method on Façade to get an iterator. Displays each waitlisted order and performs operation requested by actor (skip or fill).
(h)    Logout. System transitions to the previous  state, which has to be remembered in the context. (If previous state was the OpeningState, it goes there; otherwise it goes to ManagerMenuState.)
*****************************************************************/
import Source_Code.*; //Source Code would be the original code base form project 1
import java.util.*;
import java.io.*;
import java.lang.*;

public class ClerkMenuState{
	private static Warehouse warehouse;
	private static ClerkMenuState ClerkMenuState;
 	final static String FILENAME = "WareData";
  final static String MAINMENU =  ""+
        "CLERK MENU OPTIONS                                                  \n\t"+
          "a. Add A Client                                        (ADDCLIENT)\n\t"+
          "b. Show list of products                      (DISPLAYALLPRODUCTS)\n\t"+
          "c. Show list of clients                        (DISPLAYALLCLIENTS)\n\t"+
          "d. Show list of clients with outstanding balance (DISPLAYINVOICES)\n\t"+
          "e. Become a client                                                \n\t"+
          "f. Display the waitlist for a product               (SHOWWAITLIST)\n\t"+
          "g. Receive a shipment                                (ADDSHIPMENT)\n\t"+
          "h. Record a payment from a client.                                \n\t"+
          "i. Logout                                                         \n\n";



/*************************** Generic prompt methods ******************************/
	/*** For prompts that are used many times in many applications ******************/

	/*********************************************************************
	getClientId
	Prompts user for client id, retrieves it and returns it
	**********************************************************************/
	public static int getClientId(){
		System.out.print("Please enter a client id: ");
		Scanner s = new Scanner(System.in);
		return s.nextInt();
	}//end getClientId()

	/*********************************************************************
	getProductId
	Prompts user for product id, retrieves it and returns it
	**********************************************************************/
	public static int getProductId(){
		System.out.print("Please enter a product id: ");
		Scanner s = new Scanner(System.in);
		return s.nextInt();
	}//end getProductId()


/************************** File reading methods **********************************/

	/******************************************************************************
	openWarehouse
	Opens the given Warehouse, or creates if it doesn't exist
	Returns the Warehouse object
	*******************************************************************************/
/*	public static void openWarehouse(){
			Warehouse w = Warehouse.retrieveData(FILENAME);
			if(w == null){
				System.out.println("Warehouse not found in file. Creating new Warehouse.");
				warehouse = Warehouse.instance();
			} else{
				System.out.println("Warehouse successfully read from file.");
				warehouse = w;
			}//end else
	}//end openWarehouse
*/
	/******************************************************************************
	saveChanges
	Saves any changes made to the warehouse.
	******************************************************************************/
/*	public static void saveChanges(){
		if(warehouse.saveData(FILENAME) )
				System.out.println("Saved successfully");
		else
			System.out.println("Save failed. Error occured");
	}//end saveChanges
*/
	/******************************************************************************
	instance()
	Called to create an instance of the ClerkMenuState
	*****************************************************************************/
//	public static void logOut(){saveChanges();}

	/******************************************************************************
	instance()
	Called to create an instance of the ClerkMenuState
	*****************************************************************************/
	public static ClerkMenuState instance() {
		if(ClerkMenuState == null)
			return ClerkMenuState = new ClerkMenuState();
		else
			return ClerkMenuState;
	}//end instance()

  /******************************************************************************
  addClient
  Code to prompt user for necessary information to add a new client to the Warehouse
  *******************************************************************************/
  private static void addClient(){
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a name for the client: ");
    String name = input.nextLine();
    System.out.print("Enter a phone number for the client: ");
    String phone = input.nextLine();
    System.out.print("Enter an address for the client: ");
    String address = input.nextLine();
    warehouse.addClient(name, phone, address);
    System.out.println("Client added successfully");
  }//end addClient

	/*******************************************************************************
	displayAllProducts
	Displays all Product objects in the system.
	*******************************************************************************/
	private static void displayAllProducts(){
		Iterator it = warehouse.getProducts();
		while(it.hasNext() )
			System.out.println(it.next().toString());
	}//end displayAllProducts

	/*******************************************************************************
	displayAllClients
	Displays all client objects in the system.
	*******************************************************************************/
	private static void displayAllClients(){
		Iterator it = warehouse.getClients();
		while(it.hasNext() )
			System.out.println(it.next().toString());
	}//end displayAllClients

  /**************************************************************************
  displayInvoices()
  Prompts the user for a client id.
  Asks the user if they'd like to display detailed data (This includes the items
                              that were charged for)
  Displays the date and relevant data for each invoice in the client's history
  ***************************************************************************/
  private static void displayInvoices(){
    int clientId = getClientId();
    Iterator invoiceIt;
    boolean choice;
    if(!warehouse.verifyClient(clientId)){
      System.out.println("Error, invalid client id. Aborting operation");
      return;
    }//end if
    System.out.print("Would you like to display detailed transactions? (Y|N) ");
    choice = new Scanner(System.in).next().equals("Y"); //true if Y
    invoiceIt = warehouse.getInvoiceIt(clientId);
    if(choice)
      while(invoiceIt.hasNext() )
        System.out.println(((Invoice)(invoiceIt.next())).detailedString() );
    else
      while(invoiceIt.hasNext() )
        System.out.println(((Invoice)(invoiceIt.next())).toString());

		System.out.println("WARNING: displayInvoices in ClerkMenuState testing incomplete");
  }//end displayInvoices()

  /******************************************************************************
  showWaitList
  Gets the product id, then displays its wait list
  ******************************************************************************/
  private static void showWaitList(){
     int productID = getProductId();
     Iterator it;
     if(!warehouse.verifyProduct(productID)){
        System.out.println("Error, invalid product id. Aborting operation");
        return;
     }//end if
     it = warehouse.getProductWaitList(productID);
     System.out.println("Product: \n" + warehouse.findProduct(productID).toString());
     if(!it.hasNext())
        System.out.println("Product has no wait list currently");
     else{
        System.out.println("Wait list:\n"+
                           "__________________");
        while(it.hasNext())
           System.out.println(((WaitListItem)it.next()).toString());
     }//end else
  }//end showWaitList

	/**************************************************************************
	addShipment()
	Will prompt operator for a supplier ID for the shipment being taken in.
	Verifies that id
	Will repeatedly prompt operator for a product id
		Verifies that id
		Prompts for a quantity
		Increases that product's quantity
	Repeats until user enters a sentinel key to exit
	**************************************************************************/
	private static void addShipment(){
		int supplierId, productId, quantity, quantCount;
		Scanner scanner;
		boolean moreProducts = true;
		Iterator waitList;
		WaitListItem currItem;
		char choice;
		//Get supplier id:
		System.out.print("Please enter a supplier id: ");
		scanner = new Scanner(System.in);
		supplierId = scanner.nextInt();
		//Verify supplier id:
		if(!warehouse.verifySupplier(supplierId)){
			System.out.println("Error, invalid supplier id. Aborting operation");
			return;
		}//end if
		while(moreProducts){
			//Get product id:
			System.out.print("Enter the received product's id (0 to quit): ");
			scanner = new Scanner(System.in); //flush input buffer
			productId = scanner.nextInt();
         if(productId == 0) //Sentinel value to return with
            return;
			//Verify product id:
			if(!warehouse.verifyProduct(productId)){
				System.out.println("Error, invalid product id. Aborting operation");
				return;
			}//end if
			//If valid, get product quantity
			System.out.print("Enter a quantity for the product: ");
			scanner = new Scanner(System.in); //flush buffer
			quantity = scanner.nextInt();
			//Increment product's quantity
			waitList = warehouse.addShippedItem(productId, quantity);
			//Receive waitList, prompt for any quantities that can be fulfilled.
			quantCount = 0; //Reset quant count for new item
			while(waitList.hasNext() ){
				currItem = (WaitListItem)waitList.next();
				if((currItem.getQuantity() + quantCount)<= warehouse.getStock(productId)){
					System.out.println("Order " + currItem.getOrder().getId() +
						" can be fulfilled with new stock.\n" +
                  " Current stock: " + warehouse.getStock(productId) +
                  " Order quantity needed: " + currItem.getQuantity() +
						"\nFulfill? (Y|N) ");
					scanner = new Scanner(System.in);
					choice = scanner.next().charAt(0);
					if(choice == 'Y'){
                  System.out.println("Fulfilling order");
						warehouse.fulfillWaitListItem(productId, currItem);
						quantCount += currItem.getQuantity();
					}
				}//end if
			}//end while
			warehouse.doneAddingfulfillItems();
		}//end while(moreProducts)
	}//end addShipment


  /******************************************************************************
  callClient
  calls the ClientMenuState
  ******************************************************************************/
	private static void callClient(){
		ClientMenuState.processInput(warehouse);
		System.out.println(MAINMENU);
	}

  public static void processInput(Warehouse w){
		warehouse = w;
		Scanner input = new Scanner(System.in);
		String inputStr = "";
		System.out.println(MAINMENU);
		while(!inputStr.equals("exit") && !inputStr.equals("i") && !inputStr.equals("logout")){
			inputStr = input.next();

			switch(inputStr.toUpperCase()){
				case "EXIT":
					System.out.println("Exiting Clerk Operations\n");
          break;
        case "A":
				case "ADDCLIENT":
				case "ADDACLIENT":
					addClient();
          break;
        case "B":
        case "DISPLAYALLPRODUCTS":
        case "SHOWLISTOFPRODUCTS":
          displayAllProducts();
          break;
        case "C":
        case "DISPLAYALLCLIENTS":
        case "SHOWLISTOFCLIENTS":
          displayAllClients();
          break;
        case "D":
        case "DISPLAYINVOICES":
        case "SHOWLISTOFCLIENTSWITHOUTSTANDINGBALANCE":
          displayInvoices();
          break;
        case "E":
					callClient();
          break;
        case "F":
        case "SHOWWAITLIST":
        case "DISPLAYTHEWAITLISTFORAPRODUCT":
          showWaitList();
          break;
        case "G":
				case "ADDSHIPMENT":
				case "RECEIVEASHIPMENT":
					addShipment();
          break;
        case "H":
					System.out.println("WARNING: Record a payment from a client unavailable");
          break;
        case "I":
          break;
        default:
          System.out.print("ERROR: Invalid option\n" + MAINMENU);
          break;
      }//end switch
    }//end while
  }//end processInput
}//end ClerkMenuState class
