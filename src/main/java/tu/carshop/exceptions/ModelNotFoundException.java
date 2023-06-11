package tu.carshop.exceptions;

public class ModelNotFoundException extends RuntimeException {
    public ModelNotFoundException(String message) {
        super(message);
    }
}
