import java.util.*;
import java.io.*;
public class supplierList implements Serializable{
	private static final long serialVersionUID = 1L;
	private LinkedList<supplier> suppliers = new LinkedList<supplier>();
	private static supplierList suppList;
	supplierList() {
		
	}
	
	public static supplierList instance() {
		if(suppList == null) {
			return(suppList = new supplierList());
		}else {
			return suppList;
		}
	}
	
	public Iterator<supplier> getSuppliers() {//To get iterator of supplier list
		return suppliers.iterator();
	}
	
	public supplier insertSupplier(String name) {
		supplier temp = new supplier(name);
		suppliers.add(temp);
		return temp;
	}
	
	public void printProdList(String suppId) {
		  supplier temp = getSupplier(suppId);
		  temp.printSuppliers();
	  }
	
	public void assignProd(String suppId, product prod) {
		supplier temp = getSupplier(suppId);
		temp.addProduct(prod);
	}
	
	public supplier getSupplier(String iD) {//To get a specific supplier through iterator
		  Iterator<supplier> pointer = getSuppliers();
		  supplier temp;
		  while(pointer.hasNext()) {
			  temp =pointer.next();
			  if(temp.getId().equals(iD)) {
				  return temp;
			  }
		  }
		  
	     return null;
	}
	
	public void printSuppliers() {
		  Iterator<supplier> pointer = suppliers.iterator();
		  supplier temp;
		  while(pointer.hasNext()) {
			  temp = pointer.next();
			  System.out.println("Name: " + temp.getName() + ", ID: " + temp.getId());
		  }
	  }
	
	public void printSuppProdList(String id) {
		supplier temp = getSupplier(id);
		temp.printProdIds();
	}
	
	public void remProdFromSupp(String suppId, String prodId) {
		supplier temp = getSupplier(suppId);
		temp.remProd(prodId);
	}
	
	public void editProd(String suppId, String prodId, int quantity) {
		supplier temp = getSupplier(suppId);
		temp.editProd(prodId, quantity);
	}
	
	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(suppList);
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	private void readObject(java.io.ObjectInputStream input) {
		try {
			if(suppList != null) {
				return;
			}else {
				input.defaultReadObject();
				if(suppList == null) {
					suppList = (supplierList) input.readObject();
				}else {
					input.readObject();
				}
			}
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
	
	
	
	
}