package dao;

/**
* <h1>Account!</h1>
* Class describes users account in the bank.
* @author  Bodnar Volodymyr
* @version 0.1
*/

public class PersistException extends Exception {

	private static final long serialVersionUID = 1880972383691082459L;

	public PersistException() {
    }

    public PersistException(String message) {
        super(message);
    }

    public PersistException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistException(Throwable cause) {
        super(cause);
    }

    public PersistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
