package javase.logging;
import java.util.logging.Level;
import java.util.logging.Logger;

public class loggingExample {
    public static final Logger logger = Logger.getLogger(loggingExample.class.getName() );

    public static void main(String[] args){
        logger.setLevel(Level.ALL);
        logger.finest("This is a finest message.");
        logger.finer("This is a finer message.");
        logger.fine("This is a fine message.");
        logger.info("This is an info message.");
        logger.warning("This is a warning message.");
        logger.severe("This is a severe message.");

    }
}
