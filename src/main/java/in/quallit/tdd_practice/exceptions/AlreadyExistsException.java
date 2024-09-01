package in.quallit.tdd_practice.exceptions;

/**
 * @author Jigs
 */
public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String message) {
        super(message);
    }
}
