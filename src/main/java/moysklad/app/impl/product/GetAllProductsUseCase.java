package moysklad.app.impl.product;

import lombok.RequiredArgsConstructor;
import moysklad.adapter.dto.GetProductsDto;
import moysklad.app.api.product.GetAllProductsInbound;
import moysklad.app.api.product.ProductRepository;
import moysklad.domain.Product;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetAllProductsUseCase implements GetAllProductsInbound {
    private final ProductRepository productRepository;

    @Override
    public List<Product> execute(GetProductsDto request) {
        request.validate();

        List<Product> filteredProducts = productRepository.findAll().stream()
                .filter(product -> request.getName() == null || product.getName().contains(request.getName()))
                .filter(product -> request.getPriceMin() == null || product.getPrice() >= request.getPriceMin())
                .filter(product -> request.getPriceMax() == null || product.getPrice() <= request.getPriceMax())
                .filter(product -> request.getInStock() == null || product.getInStock().equals(request.getInStock()))
                .collect(Collectors.toList());

        Comparator<Product> comparator = Comparator.comparing(Product::getName);
        if ("price".equalsIgnoreCase(request.getSortBy())) {
            comparator = Comparator.comparing(Product::getPrice);
        } else if ("name".equalsIgnoreCase(request.getSortBy())) {
            comparator = Comparator.comparing(Product::getName);
        }

        filteredProducts = filteredProducts.stream()
                .sorted(comparator)
                .limit(request.getLimit() != null ? request.getLimit() : filteredProducts.size())
                .collect(Collectors.toList());

        return filteredProducts;
    }
}
