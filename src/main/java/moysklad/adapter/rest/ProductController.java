package moysklad.adapter.rest;

import lombok.RequiredArgsConstructor;
import moysklad.app.api.product.*;
import moysklad.exception.ProductNotFoundException;
import moysklad.domain.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final CreateProductInbound createProductInbound;
    private final DeleteProductInbound deleteProductInbound;
    private final GetAllProductsInbound getAllProductsInbound;
    private final GetProductByIdInbound getProductByIdInbound;
    private final UpdateProductInbound updateProductInbound;

    @GetMapping
    public List<Product> getAllProducts() {
        return getAllProductsInbound.execute();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return getProductByIdInbound.execute(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return createProductInbound.execute(product);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        getProductByIdInbound.execute(product.getId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + product.getId()));
        return updateProductInbound.execute(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        Product product = getProductByIdInbound.execute(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        deleteProductInbound.execute(product.getId());
    }
}