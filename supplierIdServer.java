import java.io.*;

public class supplierIdServer implements Serializable{
	private static final long serialVersionUID = 1L;
	private int idCounter;
	private static supplierIdServer server;
	private supplierIdServer() {
		idCounter = 1;
	}
	
	public static supplierIdServer instance() {
		if(server == null) {
			return(server = new supplierIdServer());
		}
		else {
			return server;
		}
	}
	
	public int getId() {
		return idCounter++;
	}
	
	public String toString() {
		return("IdServer" + idCounter);
	}
	
	public static void retrieve(ObjectInputStream input) {
		try {
			server = (supplierIdServer) input.readObject();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}catch(Exception cnfe) {
			cnfe.printStackTrace();
		}
	}
	
	private void writeObject(java.io.ObjectOutputStream output) throws IOException{
		try {
			output.defaultWriteObject();
			output.writeObject(server);
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	private void readObject(java.io.ObjectInputStream input) throws IOException, ClassNotFoundException{
		try {
			input.defaultReadObject();
			if(server == null) {
				server  = (supplierIdServer) input.readObject();
			}else {
				input.readObject();
			}
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}