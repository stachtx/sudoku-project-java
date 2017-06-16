
package grupatp.exceptions;

/**
 *
 * @author Tomasz Stachura
 * @author Paweł Ograbek
 */
public class JdbcException extends Exception{
     private static final long serialVersionUID = 1L;
    
    private String message;
    
    public JdbcException(String message, Throwable cause) {
        super(cause);
        this.message = message;
    }

    public JdbcException(String message) {
       this.message = message;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
}
