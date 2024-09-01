package in.quallit.tdd_practice.exceptions;

/**
 * @author Jigs
 */
public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String message) {
        super(message);
    }
}
