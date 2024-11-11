package moysklad.adapter.rest;

import moysklad.adapter.persistence.product.ProductRepositoryAdapter;
import moysklad.domain.Product;
import moysklad.fw.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.ConcurrentHashMap;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * This test class validates the functionality of the ProductController class.
 * It tests the CRUD operations on Product resources using MockMvc to simulate HTTP requests.
 */
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepositoryAdapter productRepositoryAdapter;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Initializes the test environment before each test method by clearing the in-memory product storage.
     * This ensures that each test has an isolated storage state and does not affect other tests.
     */
    @BeforeEach
    void setUp() {
        ProductRepositoryAdapter.STORAGE = new ConcurrentHashMap<>();
    }

    /**
     * Tests the getAllProducts endpoint.
     * Validates that an empty list is returned when no products are present in the storage.
     *
     * @throws Exception if any error occurs during request processing.
     */
    @Test
    void getAllProducts_shouldReturnEmptyList_whenNoProductsExist() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    /**
     * Tests the createProduct endpoint.
     * Validates that a product can be created and that the created product matches the submitted data.
     *
     * @throws Exception if any error occurs during request processing.
     */
    @Test
    void createProduct_shouldReturnCreatedProduct() throws Exception {
        Product product = new Product(null, "Test Product", "Description", 100.0, true);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Test Product"))
                .andExpect(jsonPath("$.description").value("Description"))
                .andExpect(jsonPath("$.price").value(100.0))
                .andExpect(jsonPath("$.inStock").value(true));
    }

    /**
     * Tests the getProductById endpoint.
     * Validates that an existing product can be retrieved by its ID and that the returned product data is accurate.
     *
     * @throws Exception if any error occurs during request processing.
     */
    @Test
    void getProductById_shouldReturnProduct_whenProductExists() throws Exception {
        Product product = new Product(null, "Test Product", "Description", 100.0, true);
        Product savedProduct = productRepositoryAdapter.save(product);

        mockMvc.perform(get("/products/" + savedProduct.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(savedProduct.getId()))
                .andExpect(jsonPath("$.name").value("Test Product"));
    }

    /**
     * Tests the updateProduct endpoint.
     * Validates that an existing product can be updated and that the returned product data matches the updated data.
     *
     * @throws Exception if any error occurs during request processing.
     */
    @Test
    void updateProduct_shouldReturnUpdatedProduct_whenProductExists() throws Exception {
        Product product = new Product(null, "Test Product", "Description", 100.0, true);
        Product savedProduct = productRepositoryAdapter.save(product);

        Product updatedProduct = new Product(savedProduct.getId(), "Updated Product", "Updated Description", 150.0, false);

        mockMvc.perform(put("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedProduct)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Product"))
                .andExpect(jsonPath("$.description").value("Updated Description"))
                .andExpect(jsonPath("$.price").value(150.0))
                .andExpect(jsonPath("$.inStock").value(false));
    }

    /**
     * Tests the deleteProduct endpoint.
     * Validates that an existing product can be deleted and that a subsequent attempt to retrieve it returns a 404 status.
     *
     * @throws Exception if any error occurs during request processing.
     */
    @Test
    void deleteProduct_shouldDeleteProduct_whenProductExists() throws Exception {
        Product product = new Product(null, "Test Product", "Description", 100.0, true);
        Product savedProduct = productRepositoryAdapter.save(product);

        mockMvc.perform(delete("/products/" + savedProduct.getId()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/products/" + savedProduct.getId()))
                .andExpect(status().isNotFound());
    }

    /**
     * Tests the getDatabaseState endpoint.
     * Validates that the endpoint returns the current state of the product storage, listing all products.
     *
     * @throws Exception if any error occurs during request processing.
     */
    @Test
    void getDatabaseState_shouldReturnDatabaseState() throws Exception {
        Product product1 = new Product(null, "Product1", "Description1", 50.0, true);
        Product product2 = new Product(null, "Product2", "Description2", 150.0, false);
        productRepositoryAdapter.save(product1);
        productRepositoryAdapter.save(product2);

        mockMvc.perform(get("/products/info/database-state"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Product1")))
                .andExpect(content().string(containsString("Product2")));
    }
}
