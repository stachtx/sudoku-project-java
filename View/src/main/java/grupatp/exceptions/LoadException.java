package grupatp.exceptions;

/**
 *
 * @author Tomasz Stachura
 * @author Paweł Ograbek
 */
public class LoadException extends Exception {

    private static final long serialVersionUID = 1L;
    
    private String message;
    
    public LoadException(String message, Throwable cause) {
        super(cause);
        this.message = message;
    }

    public LoadException(String message) {
       this.message = message;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
}
