package V_Serveur;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.SocketHandler;

public class SimpleLogger {
	 
/**
* @param args
 * @throws IOException 
*/

  
	   final static Logger logger = Logger.getLogger(SimpleLogger.class.getName());
	    private static FileHandler fh = null;
	 
	    public static void init(){
			try {
				fh=new FileHandler("logVoip.log", false);
			} catch (SecurityException  | IOException  e) {
				e.printStackTrace();
			}
	    	Logger l = Logger.getLogger("");
	    	fh.setFormatter(new SimpleFormatter());
	    	l.addHandler(fh);
			l.setLevel(Level.CONFIG);
	    }
	 
		
}
