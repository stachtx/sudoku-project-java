package grupatp.exceptions;

/**
 *
 * @author Tomasz Stachura
 * @author Paweł Ograbek
 */
public class CharForbiddenException extends Exception {

    private static final long serialVersionUID = 1L;
    
    private String message;
    
    public CharForbiddenException(String message, Throwable cause) {
        super(cause);
        this.message = message;
    }

    public CharForbiddenException(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }

}
