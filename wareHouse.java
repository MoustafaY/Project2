import java.util.*;
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
	
	public String addProduct(String name, int quantity, int price) {
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
}