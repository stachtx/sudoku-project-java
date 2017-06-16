package grupatp.exceptions;

/**
 *
 * @author Tomasz Stachura
 * @author Paweł Ograbek
 */
public class SaveException extends Exception{
    
    private static final long serialVersionUID = 1L;
    
    private String message;
    
    public SaveException(String message, Throwable cause) {
        super(cause);
        this.message = message;
    }

    public SaveException(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
}
