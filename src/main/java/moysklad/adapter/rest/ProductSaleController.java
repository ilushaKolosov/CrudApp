package moysklad.adapter.rest;

import lombok.RequiredArgsConstructor;
import moysklad.app.api.product.GetProductByIdInbound;
import moysklad.app.api.sales.*;
import moysklad.domain.ProductSale;
import moysklad.exception.ProductNotFoundException;
import moysklad.exception.ProductSaleNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product-sales")
public class ProductSaleController {
    private final CreateProductSaleInbound createProductSaleInbound;
    private final DeleteProductSaleInbound deleteProductSaleInbound;
    private final GetAllProductSalesInbound getAllProductSalesInbound;
    private final GetProductSaleByIdInbound getProductSaleByIdInbound;
    private final UpdateProductSaleInbound updateProductSaleInbound;
    private final GetProductByIdInbound getProductByIdInbound;

    @PostMapping
    public ProductSale createProductSale(@RequestBody ProductSale productSale) {
        getProductByIdInbound.execute(productSale.getProduct().getId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productSale.getProduct().getId()));
        return createProductSaleInbound.execute(productSale);
    }

    @GetMapping
    public List<ProductSale> getAllProductSales() {
        return getAllProductSalesInbound.execute();
    }

    @GetMapping("/{id}")
    public ProductSale getProductSaleById(@PathVariable Long id) {
        return getProductSaleByIdInbound.execute(id)
                .orElseThrow(() -> new ProductSaleNotFoundException("ProductSale not found with id: " + id));
    }

    @PutMapping
    public ProductSale updateProductSale(@RequestBody ProductSale productSale) {
        getProductSaleByIdInbound.execute(productSale.getId())
                .orElseThrow(() -> new ProductSaleNotFoundException("ProductSale not found with id: " + productSale.getId()));
        return updateProductSaleInbound.execute(productSale);
    }

    @DeleteMapping("/{id}")
    public void deleteProductSale(@PathVariable Long id) {
        getProductSaleByIdInbound.execute(id)
                .orElseThrow(() -> new ProductSaleNotFoundException("ProductSale not found with id: " + id));
        deleteProductSaleInbound.execute(id);
    }
}
