import java.util.*;
import java.io.*;
import java.io.Serializable;

public class productIdServer implements Serializable {

   
    private static final long serialVersionUID = 1L;
  private  int idCounter;
  private static productIdServer server;
  private productIdServer() {
    idCounter = 1;
  }
  public static productIdServer instance() {
    if (server == null) {
      return (server = new productIdServer());
    } else {
      return server;
    }
  }
  public int getId() {
    return idCounter++;
  }
  public String toString() {
    return ("IdServer" + idCounter);
  }
  public static void retrieve(ObjectInputStream input) {
    try {
      server = (productIdServer) input.readObject();
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(Exception cnfe) {
      cnfe.printStackTrace();
    }
  }
  private void writeObject(java.io.ObjectOutputStream output) throws IOException {
    try {
      output.defaultWriteObject();
      output.writeObject(server);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }
  private void readObject(java.io.ObjectInputStream input) throws IOException, ClassNotFoundException {
    try {
      input.defaultReadObject();
      if (server == null) {
        server = (productIdServer) input.readObject();
      } else {
        input.readObject();
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }
}
