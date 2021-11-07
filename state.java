import java.util.*;
import java.io.*;
import java.text.*;

public abstract class state{
	protected static context contxt = context.instance();
	protected static wareHouse wh = new wareHouse();

		protected state() {

		}
	
	public abstract void run();
}