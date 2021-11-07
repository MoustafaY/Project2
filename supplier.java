import java.util.*;
import java.io.*;

public class supplier implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String id;
	private static final String SUPPLIER_STRING = "S";
	private LinkedList<product> prodList = new LinkedList<product>();
	
	public supplier(String name) {
		this.name = name;
		id = SUPPLIER_STRING + (supplierIdServer.instance()).getId();
	}
	
	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}
	
	
	public void setName(String newName) {
		name = newName;
	}
	
	public void addProduct(product prod) {
		prodList.add(prod);
	}
	
	public void printSuppliers() {
		Iterator<product> pointer = prodList.iterator();
		  product temp;
		  System.out.println("Supplier: " + name + ", ID: " + id + ", Products: ");
		  while(pointer.hasNext()) {
			  temp = pointer.next();
			  System.out.println("Name: " + temp.getName() + ", ID: " + temp.getId() + ", Purchase Price: " + temp.getPurchasePrice());
		  }
	}
	
	public void printProdIds() {
		Iterator<product> pointer = prodList.iterator();
		product temp;
		
		while(pointer.hasNext()) {
			temp = pointer.next();
			System.out.println("ID: " + temp.getId() + ", Purchase price " + temp.getPurchasePrice());
		}
	}
	
	public void remProd(String prodId) {
		Iterator<product> pointer = prodList.iterator();
		  product temp;
		  while(pointer.hasNext()) {
			  temp =pointer.next();
			  if(temp.getId().equals(prodId)) {
				  pointer.remove();
			  }
		  }
	}
	
	public void editProd(String prodId, double newPrice) {
		Iterator<product> pointer = prodList.iterator();
		  product temp;
		  while(pointer.hasNext()) {
			  temp =pointer.next();
			  if(temp.getId().equals(prodId)) {
				  temp.setPurchasePrice(newPrice);
			  }
		  }
	}
	
	
	
}