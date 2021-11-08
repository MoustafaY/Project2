import java.util.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import java.io.*;
public class wareHouse implements Serializable{
	private supplierList suppList;
	private clientList cliList;
	private productList prodList;
	private static wareHouse warehouse;
	
	wareHouse() {
		suppList = supplierList.instance();//class list here
		cliList = clientList.instance();
		prodList = productList.instance();
	}
	
	public static wareHouse instance() {
		if(warehouse == null) {
			supplierIdServer.instance();//class id server here
			clientIdServer.instance();
			productIdServer.instance();
			return (warehouse = new wareHouse());
		}else {
			return warehouse;
		}
	}
	
	
	/*Supplier functions*/
	
	public String addSupplier(String name) {
		supplier temp = suppList.insertSupplier(name);
		String output = "Supplier added: " + temp.getName() + ", ID: " + temp.getId();
		return output;
	}
	
	public void printSuppliers() {
		suppList.printSuppliers();
	}
	
	public String assign(String suppId, String prodId) {
		product tempProd = prodList.getProduct(prodId);
		supplier tempSupp = suppList.getSupplier(suppId);
		suppList.assignProd(suppId, tempProd);
		prodList.assignSupp(prodId, tempSupp);
		
		String output = "Product: " + prodId + " and supplier: " + suppId + " are assigned to each other";
		return output;
	}
	
	public String changeProdInSupp(String suppId, String prodId, int quantity) {
		suppList.editProd(suppId, prodId, quantity);
		
		String output = "New quantity of product: " + prodId + " is: " + quantity;
		return output;
	}
	
	public String remProdFromSupp(String suppId, String prodId) {
		
		suppList.remProdFromSupp(suppId, prodId);
		
		String output = "Product: " + prodId + " is removed from supplier: " + suppId;
		return output;
	}
	
	
	public void printProdFromSupp(String suppId) {
		suppList.printProdList(suppId);
	}
	
	
	/*Product functions*/
	
	public String addProduct(String name, int quantity, double price) {
		product temp = prodList.insertProduct(name, quantity, price);
		String output = "Product added: " + temp.getName() + ", ID: " + temp.getId();
		
		return output;
	}
	
	public void printProducts() {
		prodList.printProducts();
	}
	
	
	public void printSuppFromProd(String prodId) {
		prodList.printSuppList(prodId);
	}
	
	public void printWaitListClients(String prodId) {
		prodList.printWaitListClients(prodId);
	}
	
	public String setProdQuantity(String suppId, String prodId, double price) {
		supplier supp = suppList.getSupplier(suppId);
		product prod = prodList.getProduct(prodId);
		
		prod.setPurchasePrice(price);
		supp.editProd(suppId, price);
		
		String output = "New price of product: " + prodId + " = " + price;
		return output;
	}
	
	public String acceptShipment(String prodId, int quantity) {
		
		client tempClient;
		product tempProd;
		tempProd = prodList.getProduct(prodId);
		product waitItem;
		int newQuantity;
		int tempQuantity = quantity;
		
		Iterator<client> pointerCli = cliList.getClients();
		Iterator<product> pointerProd = prodList.getProducts();
		
		while(pointerCli.hasNext()) {
			tempClient = pointerCli.next();
			waitItem = tempClient.getWaitItem(prodId);
			if(waitItem.getId().equals(prodId)) {
				if(waitItem.getQuantity() > tempQuantity) {
					newQuantity = waitItem.getQuantity() - tempProd.getQuantity();
					tempClient.setWaitQuantity(prodId, newQuantity);
					break;
				}
				else if(waitItem.getQuantity() < tempQuantity) {
					newQuantity = tempProd.getQuantity() - waitItem.getQuantity();
					tempClient.remWaitItem(prodId);
					tempQuantity = newQuantity;
				}
				else {
					tempClient.remWaitItem(prodId);
					break;
				}
			}
		}
		
		String output = "Shipment for product: " + prodId + " has been accepted";
		return output;
	}
	
	
	/*Client functions*/
	
	public String addClient(String name) {
		client temp = cliList.insertClient(name);
		
		String output = "Client added: " + temp.getName();
		return output;
	}
	
	public String getClient(String id) {
		client temp = cliList.getClient(id);
		
		String output = "Client: " + temp.getName() + ", ID: " + temp.getId() + ", balance: " + temp.getBalance();
		return output;
	}
	
	public String printClient(String id) {
		client temp = cliList.getClient(id);
		
		String output = "Client: " + temp.getName() + ", ID: " + temp.getId() + ", Balance: " + temp.getBalance();
		return output;
	}
	
	public void printClients() {
		cliList.printClients();
	}
	
	public void getOutClient() {
		cliList.printOutClients();
	}
	
		/*functions to modify shopping cart of client*/
	
	public String addToCart(String clientId, String prodId, int quantity) {
		product tempProd = prodList.getProduct(prodId);
		client tempClient = cliList.getClient(clientId);
		cliList.addToCart(clientId, tempProd);
		tempClient.changeCartQuantity(prodId, quantity);
		
		String output = "Product: " + prodId + " was added to shopping cart of client: " + clientId;
		return output;
	}
	
	public String remFromCart(String clientId, String prodId) {
		cliList.remFromCart(clientId, prodId);
		
		String output = "Product: " + prodId + " was removed from shopping cart of client: " + clientId;
		return output;
	}
	
	public String changeCartQuantity(String clientId, String prodId, int quantity) {
		cliList.changeCartQuantity(clientId, prodId, quantity);
		
		String output = "Product: " + prodId + " from shopping cart of client: " + clientId + " changed quantity to: " + quantity;
		return output;
	}
	
	public String makeOrder(String clientId) {
		
		client tempClient = cliList.getClient(clientId);
		product cartProd;
		product invProd;
		double totalAmt = 0;
		double clientAmt = tempClient.getBalance();
		int tempQuantity;
		
		 
		Iterator<product> cartPointer = tempClient.getCart();
		Iterator<product> invPointer = prodList.getProducts();
		while(cartPointer.hasNext()) {
			cartProd = cartPointer.next();
			invProd = invPointer.next();
			if(cartProd.getId().equals(invProd.getId())) {
				if(cartProd.getQuantity() > invProd.getQuantity()) {
					tempQuantity = cartProd.getQuantity() - invProd.getQuantity();
					totalAmt += (invProd.getQuantity() * invProd.getPurchasePrice());
					invProd.setQuantity(0);
					cartProd.setQuantity(tempQuantity);
					tempClient.addWaitListItem(cartProd);
					tempClient.remCart(cartProd.getId());
					invProd.addClient(tempClient);
				}
				else if(cartProd.getQuantity() < invProd.getQuantity()) {
					tempQuantity = invProd.getQuantity() - cartProd.getQuantity();
					totalAmt += (cartProd.getQuantity() * cartProd.getPurchasePrice());
					invProd.setQuantity(tempQuantity);
					tempClient.remCart(cartProd.getId());
				}
				else {
					totalAmt += (cartProd.getQuantity() * cartProd.getPurchasePrice());
					tempClient.remCart(cartProd.getId());
					invProd.setQuantity(0);
				}
			}
			
		}
		
		tempClient.setBalance(totalAmt + clientAmt);
		transaction clientTransaction = new transaction(tempClient, totalAmt);
		tempClient.addTransaction(clientTransaction);
		
		String output = "Order for client: " + tempClient.getId() + " confirmed for total of: " + totalAmt + " dollars";
		return output;
	}
	
	public String makePayment(String clientId, double payment){
		client temp = cliList.getClient(clientId);
		if(temp.getBalance() < payment){
			String output = "Invalid input";
			return output;
		}
		
		double newBalance = temp.getBalance() - payment;
		temp.setBalance(newBalance);
		
		String output = "Payment complete new balance: " + temp.getBalance();
		return output;
	}
	
	public void getTransactions(String clientId) {
		client temp = cliList.getClient(clientId);
		transaction clientTransaction;
		
		Iterator<transaction> transactionPointer = temp.getTransactionList();
		System.out.println("Transaction list of client: ");
		while(transactionPointer.hasNext()) {
			clientTransaction = transactionPointer.next();
			System.out.println("Client paid: " + clientTransaction.getAmt() + " dollars");
		}
	}
	
	public void getWaitlist(String clientId) {
		client temp = cliList.getClient(clientId);
		product item;
		
		Iterator<product> itemPointer = temp.getWaitList();
		System.out.println("Waitlist of client:");
		while(itemPointer.hasNext()) {
			item = itemPointer.next();
			System.out.println("Product: " + item.getName() + ", ID: " + item.getId() + ", quantity: " + item.getQuantity());
		}
	}
}
