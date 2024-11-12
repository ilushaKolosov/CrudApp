package moysklad.app.impl.product;

import moysklad.adapter.dto.GetProductsDto;
import moysklad.app.api.product.ProductRepository;
import moysklad.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllProductsUseCaseTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private GetAllProductsUseCase getAllProductsUseCase;

    @BeforeEach
    void setUp() {
        List<Product> products = Arrays.asList(
                new Product(1L, "Product A", "Description A", 100.0, true, 10),
                new Product(2L, "Product B", "Description B", 200.0, false, 11),
                new Product(3L, "Product C", "Description C", 150.0, true, 9),
                new Product(4L, "Product D", "Description D", 250.0, false, 0),
                new Product(5L, "Product E", "Description E", 300.0, true, 367),
                new Product(6L, "Product F", "Description F", 50.0, false, 72),
                new Product(7L, "Product G", "Description G", 120.0, true, 38),
                new Product(8L, "Product H", "Description H", 400.0, false, 72),
                new Product(9L, "Product I", "Description I", 90.0, true, 72),
                new Product(10L, "Product J", "Description J", 80.0, true, 724)
        );

        lenient().when(productRepository.findAll()).thenReturn(products);
    }

    /**
     * Test to ensure that an exception is thrown when the DTO is invalid.
     * The test checks if the validation logic for the DTO fields works correctly, particularly
     * when the product name exceeds the allowed length.
     */
    @Test
    void execute_shouldThrowException_whenDtoIsInvalid() {
        // Arrange
        GetProductsDto invalidRequest = new GetProductsDto("a".repeat(256), null, null, null, null, null);

        // Act & Assert
        assertThatThrownBy(() -> getAllProductsUseCase.execute(invalidRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product name cannot exceed 255 characters.");
    }

    /**
     * Test to verify the filtering of products by name.
     * This test ensures that the filtering logic by product name works as expected.
     */
    @Test
    void execute_shouldFilterByName() {
        // Arrange
        GetProductsDto request = new GetProductsDto("Product A", null, null, null, null, null);

        // Act
        List<Product> result = getAllProductsUseCase.execute(request);

        // Assert
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Product A");
    }

    /**
     * Test to verify the filtering of products by price range.
     * This test checks that products are correctly filtered based on a given price range.
     */
    @Test
    void execute_shouldFilterByPriceRange() {
        // Arrange
        GetProductsDto request = new GetProductsDto(null, 100.0, 150.0, null, null, null);

        // Act
        List<Product> result = getAllProductsUseCase.execute(request);

        // Assert
        assertThat(result).hasSize(3);
        assertThat(result).extracting(Product::getName).containsExactlyInAnyOrder("Product A", "Product G", "Product C");
    }

    /**
     * Test to ensure filtering by stock availability (inStock).
     * This test checks that products marked as "inStock" are correctly filtered.
     */
    @Test
    void execute_shouldFilterByInStock() {
        // Arrange
        GetProductsDto request = new GetProductsDto(null, null, null, true, null, null);

        // Act
        List<Product> result = getAllProductsUseCase.execute(request);

        // Assert
        assertThat(result).extracting(Product::getInStock).containsOnly(true);
    }

    /**
     * Test to verify sorting of products by name in ascending order.
     * This test checks that products are correctly sorted by their name field.
     */
    @Test
    void execute_shouldSortByName() {
        // Arrange
        GetProductsDto request = new GetProductsDto(null, null, null, null, "name", null);

        // Act
        List<Product> result = getAllProductsUseCase.execute(request);

        // Assert
        assertThat(result).isSortedAccordingTo((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
    }

    /**
     * Test to verify sorting of products by price in ascending order.
     * This test checks that products are correctly sorted by their price field.
     */
    @Test
    void execute_shouldSortByPrice() {
        // Arrange
        GetProductsDto request = new GetProductsDto(null, null, null, null, "price", null);

        // Act
        List<Product> result = getAllProductsUseCase.execute(request);

        // Assert
        assertThat(result).isSortedAccordingTo(Comparator.comparing(Product::getPrice));
    }

    /**
     * Test to verify that the number of products returned is limited based on the limit parameter.
     * This test ensures that when a limit is set, the number of products returned does not exceed that limit.
     */
    @Test
    void execute_shouldLimitResults() {
        // Arrange
        GetProductsDto request = new GetProductsDto(null, null, null, null, null, 5);

        // Act
        List<Product> result = getAllProductsUseCase.execute(request);

        // Assert
        assertThat(result).hasSize(5);
    }

    /**
     * Test to verify that multiple filters and sorting can be applied together.
     * This test ensures that filtering by price range and in-stock status works together with sorting by price.
     */
    @Test
    void execute_shouldApplyMultipleFiltersAndSorting() {
        // Arrange
        GetProductsDto request = new GetProductsDto(null, 80.0, 300.0, true, "price", 3);

        // Act
        List<Product> result = getAllProductsUseCase.execute(request);

        // Assert
        assertThat(result).hasSize(3);
        assertThat(result).extracting(Product::getInStock).containsOnly(true);
        assertThat(result).isSortedAccordingTo(Comparator.comparing(Product::getPrice));
        assertThat(result).extracting(Product::getPrice).containsExactly(80.0, 90.0, 100.0);
    }
}
