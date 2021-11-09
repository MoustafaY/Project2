import java.util.*;
import java.io.*;

public class client implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String id;
	private double balance;
	private LinkedList<product> cart = new LinkedList<product>();//shopping cart is a linked list of products 
	private LinkedList<transaction> transactionList = new LinkedList<transaction>();//transaction list is a linked list of transactions
	private LinkedList<product> waitList = new LinkedList<product>();//waitlist is a linked list of products

	private static final String CLIENT_STRING = "C";
	
	public client(String name) {
		this.name = name;
		id = CLIENT_STRING + (clientIdServer.instance()).getId();
		this.balance = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String newId) {
		id = newId;
	}
	
	public double getBalance() {
        return balance;
    }
	
	public void setBalance(double payment){
		balance = payment;
	}
	
	public Iterator<product> getCart() {
		return cart.iterator();
	}
	
	public void addCart(String name, String prodId, int quantity, double price) {
		
		product prod = new product(name, quantity, price);
		prod.setId(prodId);
		cart.add(prod);

	}
	
	public void remCart(String prodId) {
		
		Iterator<product> pointer = cart.iterator();
		product temp;
		
		while(pointer.hasNext()) {
			temp = pointer.next();
			if(temp.getId().equals(prodId)) {
				pointer.remove();
				break;
			}
		}
	}
	
	public void changeCartQuantity(String prodId, int quantity) {
		
		Iterator<product> pointer = cart.iterator();
		product temp;
		
		while(pointer.hasNext()) {
			temp = pointer.next();
			if(temp.getId().equals(prodId)) {
				temp.setQuantity(quantity);
				break;
			}
		}
	}
	
	
	public Iterator<transaction> getTransactionList(){
		return transactionList.iterator();
	}
	
	public void addTransaction(transaction newTransaction) {
		transactionList.add(newTransaction);
	}
	
	public Iterator<product> getWaitList(){
		return waitList.iterator();
	}
	
	public void addWaitListItem(product prod) {
		waitList.add(prod);
	}
	
	public product getWaitItem(String prodId) {
		
		Iterator<product> pointer = getWaitList();
		product temp = null;
		
		while(pointer.hasNext()) {
			temp = pointer.next();
			if(temp.getId().equals(prodId)) {
				return temp;
			}
		}
		
		return null;
	}
	
	public void setWaitQuantity(String prodId, int quantity) {
		
		Iterator<product> pointer = getWaitList();
		product temp;
		
		while(pointer.hasNext()) {
			temp = pointer.next();
			if(temp.getId().equals(prodId)) {
				temp.setQuantity(quantity);
				break;
			}
		}
	}
	
	public void remWaitItem(String prodId) {
		
		Iterator<product> pointer = getWaitList();
		product temp;
		
		while(pointer.hasNext()) {
			temp = pointer.next();
			if(temp.getId().equals(prodId)) {
				pointer.remove();
				break;
			}
		}
	}
	
	public void printWaitList() {
		
		Iterator<product> pointer = waitList.iterator();
		product temp;
		
		
		while(pointer.hasNext()) {
			temp = pointer.next();
			System.out.println("Product: " + temp.getName() + ", ID: " + temp.getId() + ", Quantity: " + temp.getQuantity());
		}
	}
   
	public void setName(String newName) {
		this.name = newName;
	}

	    public void setNewBalance(double newBalance) {
	        this.balance = newBalance;
	    }


	public String toString() {
		String string = "Client name " + name + " Id " + id + " Client balance: " + balance;
		return string;
	}
	
}