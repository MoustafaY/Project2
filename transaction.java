import java.io.*;
import java.text.*;
import java.util.*;

public class transaction implements Serializable {
  private static final long serialVersionUID = 1L;
  private double amount;
  private client cli;
  private product prod;

  public transaction(client clI, product prod, double amt) {
	this.cli = clI;
	this.prod = prod;
    this.amount = amt;
  }
  
}
