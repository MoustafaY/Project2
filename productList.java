import java.util.*;
import java.io.*;
import java.util.Iterator;

public class productList implements Serializable {
  private static final long serialVersionUID = 1L;
  private LinkedList<product> products = new LinkedList<product>();
  private static productList productList;
  public productList() {
  }
  public static productList instance() {
    if (productList == null) {
      return (productList = new productList());
    } else {
      return productList;
    }
  }
  
  public Iterator<product> getProducts() {//To get iterator of product list
		return products.iterator();
	}

  public product insertProduct(String name, int quantity, double purchasePrice) {
	product temp = new product(name, quantity, purchasePrice);
    products.add(temp);
    return temp;
  }
  
  public void printSuppList(String prodId) {
	  product temp = getProduct(prodId);
	  temp.printProducts();
  }
  
  public void assignSupp(String prodId, supplier supp) {
		product temp = getProduct(prodId);
		temp.addSupplier(supp);
	}

  public product getProduct(String iD){//To get a specific products through iterator

	  Iterator<product> pointer = getProducts();
	  product temp;
	  while(pointer.hasNext()) {
		  temp =pointer.next();
		  if(temp.getId().equals(iD)) {
			  return temp;
		  }
	  }
	  
     return null;
  }
  
  public void addClient(String prodId, client client) {
	  getProduct(prodId).addClient(client.getName(), client.getId());
  }
  
  
  
  public void acceptShipment(String id, int quantity) {
	  
	  product temp;
	  temp = getProduct(id);
	  int newQuantity = temp.getQuantity() + quantity;
	  temp.setQuantity(newQuantity);
	  
  }
  
  
  
  public void editProductQuantity(String id, int quantity) {
	  product temp;
	  temp = getProduct(id);
	  temp.setQuantity(quantity);
	  }
	
  
  public void editProductPrice(String id, double price) {
	  product temp;
	  temp = getProduct(id);
	  temp.setPurchasePrice(price);
	}
  
  public void printProducts() {
	  Iterator<product> pointer = products.iterator();
	  product temp;
	  while(pointer.hasNext()) {
		  temp = pointer.next();
		  System.out.println("Name: " + temp.getName() + ", ID: " + temp.getId() + ", Purchase price: " + temp.getPurchasePrice() + ", Quantity: " + temp.getQuantity());
	  }
  }
  
  public void printWaitListClients(String prodId) {
	  product temp = getProduct(prodId);
	  client tempClient;
	  
	  System.out.println("Waitlisted clients for product: ");
	  
	  Iterator<client> pointer = temp.getClients();
	  while(pointer.hasNext()) {
		  tempClient = pointer.next();
		  System.out.println(tempClient.toString());
	  }
  }
  
  
  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(productList);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }
  private void readObject(java.io.ObjectInputStream input) {
    try {
      if (productList != null) {
        return;
      } else {
        input.defaultReadObject();
        if (productList == null) {
          productList = (productList) input.readObject();
        } else {
          input.readObject();
        }
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }
  }
  public String toString() {
    return products.toString();
  }
  
  
}
