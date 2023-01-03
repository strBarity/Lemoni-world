package data;

public class DataAlreadyExistsException extends Exception {
    public DataAlreadyExistsException() {
        super();
    }

    public DataAlreadyExistsException(String s) {
        super(s);
    }
}
