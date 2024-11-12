package moysklad.exception;

public class ProductSaleNotFoundException extends RuntimeException {
    public ProductSaleNotFoundException(String message) {
        super(message);
    }
}
