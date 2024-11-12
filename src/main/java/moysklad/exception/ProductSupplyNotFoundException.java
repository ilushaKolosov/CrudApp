package moysklad.exception;

public class ProductSupplyNotFoundException extends RuntimeException {
    public ProductSupplyNotFoundException(String message) {
        super(message);
    }
}
