package grupatp.exceptions;

/**
 *
 * @author Tomasz Stachura
 * @author Paweł Ograbek
 */
public class TooLongCharException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    private String message;
    
    public TooLongCharException(String message, Throwable cause) {
        super(cause);
        
        this.message = message;
    }

    public TooLongCharException(String message) {
       
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
}
