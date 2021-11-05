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

	private static final String CLIENT_STRING = "M";
	
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
	public double getBalance() {
        return balance;
    }
	
	public Iterator<product> getCart() {
		return cart.iterator();
	}
	
	public void addCart(product prod) {
		cart.add(prod);
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
   
	public void setName(String newName) {
		this.name = newName;
	}

	    public void setBalance(double newBalance) {
	        this.balance = newBalance;
	    }


	public String toString() {
		String string = "Client name " + name + " Id " + id + " Client balance: " + balance;
		return string;
	}
	
}
