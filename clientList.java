import java.util.*;
import java.io.*;
public class clientList implements Serializable{
	private static final long serialVersionUID = 1L;
	private LinkedList<client> clients = new LinkedList<client>();
	private static clientList cliList;
	clientList() {
		
	}
	
	public static clientList instance() {
		if(cliList == null) {
			return(cliList = new clientList());
		}else {
			return cliList;
		}
	}
	
	public client insertClient(String name) {
		client temp = new client(name);
		clients.add(temp);
		return temp;
	}
	
	
	public Iterator<client> getClients() {
		return clients.iterator();
	}
	
	public client getClient(String iD){
		
		Iterator<client> pointer = clients.iterator();
		  client temp;
		  while(pointer.hasNext()) {
			  temp =pointer.next();
			  if(temp.getId().equals(iD)) {
				  return temp;
			  }
		  }
		  
	     return null;
	  }
	
	public void printClients() {
		
		Iterator<client> pointer = clients.iterator();
		  client temp;
		  while(pointer.hasNext()) {
			  temp =pointer.next();
			  System.out.println("Name: " + temp.getName() + ", ID: " + temp.getId() + ", Balance: " + temp.getBalance());
		  }
	}
	
	
	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(cliList);
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	private void readObject(java.io.ObjectInputStream input) {
		try {
			if(cliList != null) {
				return;
			}else {
				input.defaultReadObject();
				if(cliList == null) {
					cliList = (clientList) input.readObject();
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