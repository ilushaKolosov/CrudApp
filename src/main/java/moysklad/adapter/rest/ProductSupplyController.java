package moysklad.adapter.rest;

import lombok.RequiredArgsConstructor;
import moysklad.app.api.product.GetProductByIdInbound;
import moysklad.app.api.supplies.*;
import moysklad.domain.ProductSupply;
import moysklad.exception.ProductNotFoundException;
import moysklad.exception.ProductSupplyNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product-supplies")
public class ProductSupplyController {
    private final CreateProductSupplyInbound createProductSupplyInbound;
    private final DeleteProductSupplyInbound deleteProductSupplyInbound;
    private final GetAllProductSuppliesInbound getAllProductSuppliesInbound;
    private final GetProductSupplyByIdInbound getProductSupplyByIdInbound;
    private final UpdateProductSupplyInbound updateProductSupplyInbound;
    private final GetProductByIdInbound getProductByIdInbound;

    @PostMapping
    public ProductSupply createProductSupply(@RequestBody ProductSupply productSupply) {
        getProductByIdInbound.execute(productSupply.getProduct().getId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productSupply.getId()));
        return createProductSupplyInbound.execute(productSupply);
    }

    @GetMapping
    public List<ProductSupply> getAllProductSupplies() {
        return getAllProductSuppliesInbound.execute();
    }

    @GetMapping("/{id}")
    public ProductSupply getProductSupplyById(@PathVariable Long id) {
        return getProductSupplyByIdInbound.execute(id)
                .orElseThrow(() -> new ProductSupplyNotFoundException("ProductSupply not found with id: " + id));
    }

    @PutMapping
    public ProductSupply updateProductSupply(@RequestBody ProductSupply productSupply) {
        getProductSupplyByIdInbound.execute(productSupply.getId())
                .orElseThrow(() -> new ProductSupplyNotFoundException("ProductSupply not found with id: " + productSupply.getId()));
        return updateProductSupplyInbound.execute(productSupply);
    }

    @DeleteMapping("/{id}")
    public void deleteProductSupply(@PathVariable Long id) {
        getProductSupplyByIdInbound.execute(id)
                .orElseThrow(() -> new ProductSupplyNotFoundException("ProductSupply not found with id: " + id));
        deleteProductSupplyInbound.execute(id);
    }
}
