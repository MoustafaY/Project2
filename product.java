import java.util.*;
import java.io.*;
public class product implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private String id;
  private int quantity;
  private double purchasePrice;
  private LinkedList<supplier> suppList = new LinkedList<supplier>();
  private LinkedList<client> cliList = new LinkedList<client>();
  private static final String PRODUCT_STRING = "P";
	
  public  product (String name, int quantity, double purchasePrice) {
    this.name = name;
    this.quantity = quantity;
    this.purchasePrice = purchasePrice;
    id = PRODUCT_STRING + (productIdServer.instance()).getId();
  }

  public String getName() {
        return name;
  }
  
  public int getQuantity() {
        return quantity;
  }
  
  public String getId() {
        return id;
  }
  
  public void setId(String newId) {
	  id = newId;
  }
  
  
  public double getPurchasePrice() {
  return purchasePrice;
}
	  
  public void setName(String newName) {
        name = newName;
  }
  
  public void setQuantity(int newQuantity) {
        quantity = newQuantity;
  }
  
  public void setPurchasePrice(double newPurchasePrice) {
	    this.purchasePrice = newPurchasePrice;
	  }
  
  public void addSupplier(supplier supp) {
	  suppList.add(supp);
  }
  
  public void addClient(String clientName, String clientId) {
	  client cli = new client(clientName);
	  cli.setId(clientId);
	  cliList.add(cli);
  }
  
  public Iterator<client> getClients() {
	  return cliList.iterator();
  }
  
  public void remClient(String clientId) {
	  
	  Iterator<client> pointer = cliList.iterator();
		client temp;
		
		while(pointer.hasNext()) {
			temp = pointer.next();
			if(temp.getId().equals(clientId)) {
				pointer.remove();
				break;
			}
		}
  }
	 
  
  public void printProducts() {
	  Iterator<supplier> pointer = suppList.iterator();
	  supplier temp;
	  System.out.println("Product: " + name + ", ID: " + id + ", Quantity: " + quantity + ", Purchase Price: " + purchasePrice + ", Suppliers: ");
	  while(pointer.hasNext()) {
		  temp = pointer.next();
		  System.out.println("Name: " + temp.getName() + ", ID: " + temp.getId() + ", Purchase Price: " + purchasePrice);
	  }
  }
  
}