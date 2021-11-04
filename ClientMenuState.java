/*****************************************************************
ClientMenuState.java
Responsible individual: Reuben Koop
1.	In the ClientMenuState, the Context has stored the ClientID for the current client; all operations are for that ClientID. The state will have operations for the following:
(a)	Show client details. The state invokes a method on Facade to get the Client object and then gets the client details. Note that the ClientID is available in the Context.
(b)	Show list of products with sale prices.  The state invokes a method on Facade to get an iterator, and then extracts the needed information.
(c)	Show client transactions. The state invokes a method on Facade to get the Client object and then gets the transaction details for the client. Note that the ClientID is available in the Context.
(d)	Modify client’s shopping cart. System displays contents. Actor can add product, remove product or change quantity.
(e)	Display client waitlist.
(f)	Logout. System transitions to the previous  state, which has to be remembered in the context. (If previous state was the OpeningState, it goes there; otherwise it goes to ClientMenuState.)
*****************************************************************/
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import Source_Code.*;

public class ClientMenuState{
	private static Warehouse warehouse;
	private static ClerkMenuState clerkMenuState;
  final static String MAINMENU =  ""+
        "CLIENT MENU OPTIONS                                                  \n\t"+
          "a. Show Client detail                                        (DISPLAYCLIENTDETAILS)\n\t"+
          "b. Show list of products                                     (DISPLAYPRODUCTSLIST)\n\t"+
          "c. Show client transactions                                  (DISPLAYCLIENTTRANSACTIONS)\n\t"+
          "d. Edit client's shopping cart and change the quantities of product on it (DISPLAYSHOPPINGCART)\n\t"+
          "e. Add client shopping cart                                           (ADDSHOPPINGCART)\n\t"+
          "f. Display the client waitlist                                (DISPLAYCLIENTWAITLIST)\n\t"+
          "g. Logout                                                         \n\n";
   
	/*********************************************************************
	getProductId
	Prompts user for product id, retrieves it and returns it
	**********************************************************************/
	public static int getProductId(){
		System.out.print("Please enter a product id: ");
		Scanner s = new Scanner(System.in);
		return s.nextInt();
	}//end getProductId()

	/*******************************************************************************
	displayClientDetails
	Displays all Product objects in the system.
	*******************************************************************************/
	private static void displayClientsDetails(){
		try {
			int clientId = clerkMenuState.getClientId();
			Iterator it = warehouse.getClients();
			while(it.hasNext() )
				System.out.println(it.next().toString());
		}
		catch (Exception e){ System.out.println("ERROR: displayClientDetails() in ClientMenuState " + e);}
	}//end displayAllProducts

	/*******************************************************************************
	displayProductList
	Displays all client objects in the system.
	*******************************************************************************/
	private static void displayProductList(){
		try {
			Iterator it = warehouse.getProducts();
			while(it.hasNext() )
				System.out.println(it.next().toString());
		}
		catch (Exception e){ System.out.println("ERROR: getProducts() in ClientMenuState " + e);}
	}//end displayAllClients

  /**************************************************************************
  displayClientTransactions()
  Prompts the user for a client id.
  Asks the user if they'd like to display detailed data (This includes the items
                              that were charged for)
  Displays the date and relevant data for each invoice in the client's history
  ***************************************************************************/
  private void displayClientTransaction(){
    int clientId = clerkMenuState.getClientId();
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
        System.out.println(((invoiceIt.next())).toString());
    else
      while(invoiceIt.hasNext() )
        System.out.println(((invoiceIt.next())).toString());
  }//end displayInvoices()

  /******************************************************************************
  showWaitList
  Gets the product id, then displays its wait list
  ******************************************************************************/
  private static void showWaitList(){
			try {
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
		 }//end try
		 catch (Exception e){ System.out.println("ERROR: showWaitList() in ClientMenuState " + e);}
  }//end showWaitList


  /*******************************************************************************
	Editing the Shopping cart
	Add and remove in the shopping cart.
	*******************************************************************************/
	private static void ShopingCart(){
		Iterator it = warehouse.getProducts();
		while(it.hasNext() )
			System.out.println(it.next().toString());
	}//end displayAllClients

  //editing and adding the products in the shopping cart
	private static void displayShoppingCart() {

	  Scanner sc = new Scanner(System.in);
	    ArrayList<ItemList> cart = new ArrayList<ItemList>();

	    ItemList item;
	    int itemID;
	    String itemName;
	    double itemPrice;
	    String itemDescription;
	    int itemQuantity;
	    double itemTax;
	    int ch;
	    String choice;

	    ProductList shoppingCart = new ProductList();

	    while (true) {
	        System.out.println("Menu:");
	        System.out.println("0) Exit " + "\n"
	                + "1) Add item in shopping cart" + "\n"
	                + "2) Remove item from shpping cart");
	        ch = sc.nextInt();

	        switch (ch) {
	        case 0:
	            System.out.println("\n" + "Good bye!");
	            System.exit(0);

	        case 1:
	            System.out.println("Enter item ID: ");
	            itemID = sc.nextInt();

	            System.out.println("Enter item name: ");
	            itemName = sc.next();

	            System.out.println("Enter item price: ");
	            itemPrice = sc.nextDouble();

	            System.out.println("Enter short description of item: ");
	            itemDescription = sc.next();

	            System.out.println("Enter quantity: ");
	            itemQuantity = sc.nextInt();

	            System.out.println("Enter tax rate:");
	            itemTax = sc.nextDouble();

	            shoppingCart.add(itemID, itemName, itemPrice, itemDescription, itemQuantity, itemTax);

	            break;

	        case 2:
	            System.out.println("Enter name of the item that you would like to remove: ");
	            choice = sc.next();
	            shoppingCart.remove(choice);

	            break;
	        }

	    }
	}

  public static void processInput(Warehouse warehouse){
		Scanner input = new Scanner(System.in);
		String inputStr = "";
		System.out.println(MAINMENU);
		while(!inputStr.equals("exit") && !inputStr.equals("g")){
			inputStr = input.next();

			switch(inputStr.toUpperCase()){
				case "EXIT":
					System.out.println("Exiting warehouse operations\n");
          break;
        case "A":
				case "DISPLAYCLIENTDETAILS":
				case "SHOWCLIENTDETAIL  ":
					displayClientsDetails();
          break;
        case "B":
        case "DISPLAYPRODUCTSLIST":
        case "SHOWLISTOFPRODUCTS":
          displayProductList();
          break;
        case "C":
        case "DISPLAYCLIENTTRANSACTIONS":
        case "SHOWCLIENTTRANSACTIONS":
					System.out.println("WARNING: Show client transactions unavailable");
          break;
        case "D":
        case "DISPLAYSHOPPINGCART":
        case "EDITCLIENTSSHOPPINGCARTANDCHANGETHEQUANTITIESOFPTODUCTSONIT":
          displayShoppingCart();
          break;
        case "E":
					System.out.println("WARNING: Add client shopping cart unavailable");
					break;
        case "F":
        case "Add":
        case "DISPLAYTHECLIENTWAITLIST":
          showWaitList();
          break;
        case "G":
					System.out.println("Logging out of client\n");
          break;
        default:
          System.out.print("ERROR: Invalid option\n" + MAINMENU);
          break;
      }//end switch
    }//end while
  }//end processInput
}//end ClientMenuState class
