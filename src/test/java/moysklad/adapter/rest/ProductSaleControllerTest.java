package moysklad.adapter.rest;

import moysklad.app.api.product.GetProductByIdInbound;
import moysklad.app.api.sales.*;
import moysklad.domain.Product;
import moysklad.domain.ProductSale;
import moysklad.fw.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class ProductSaleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateProductSaleInbound createProductSaleInbound;

    @MockBean
    private DeleteProductSaleInbound deleteProductSaleInbound;

    @MockBean
    private GetAllProductSalesInbound getAllProductSalesInbound;

    @MockBean
    private GetProductSaleByIdInbound getProductSaleByIdInbound;

    @MockBean
    private UpdateProductSaleInbound updateProductSaleInbound;

    @MockBean
    private GetProductByIdInbound getProductByIdInbound;

    private ProductSale productSale;

    @BeforeEach
    void setUp() {
        Product product = new Product(1L, "Product A", "Description A", 100.0, true, 10);
        productSale = new ProductSale(1L,"name", product, 5, 500.0);
    }

    @Test
    void createProductSale_shouldReturnCreatedProductSale_whenProductExists() throws Exception {
        when(getProductByIdInbound.execute(productSale.getProduct().getId())).thenReturn(Optional.of(productSale.getProduct()));
        when(createProductSaleInbound.execute(any(ProductSale.class))).thenReturn(productSale);

        mockMvc.perform(post("/product-sales")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"product\": {\"id\": 1}, \"quantity\": 5, \"totalPrice\": 500.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productSale.getId()))
                .andExpect(jsonPath("$.product.id").value(productSale.getProduct().getId()))
                .andExpect(jsonPath("$.quantity").value(productSale.getQuantity()));
    }

    @Test
    void createProductSale_shouldThrowProductNotFoundException_whenProductDoesNotExist() throws Exception {
        when(getProductByIdInbound.execute(productSale.getProduct().getId())).thenReturn(Optional.empty());

        mockMvc.perform(post("/product-sales")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"product\": {\"id\": 1}, \"quantity\": 5, \"totalPrice\": 500.0}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllProductSales_shouldReturnListOfProductSales() throws Exception {
        when(getAllProductSalesInbound.execute()).thenReturn(List.of(productSale));

        mockMvc.perform(get("/product-sales"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(productSale.getId()));
    }

    @Test
    void getProductSaleById_shouldReturnProductSale_whenProductSaleExists() throws Exception {
        when(getProductSaleByIdInbound.execute(productSale.getId())).thenReturn(Optional.of(productSale));

        mockMvc.perform(get("/product-sales/{id}", productSale.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productSale.getId()))
                .andExpect(jsonPath("$.product.id").value(productSale.getProduct().getId()));
    }

    @Test
    void getProductSaleById_shouldThrowProductSaleNotFoundException_whenProductSaleDoesNotExist() throws Exception {
        when(getProductSaleByIdInbound.execute(productSale.getId())).thenReturn(Optional.empty());

        mockMvc.perform(get("/product-sales/{id}", productSale.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateProductSale_shouldReturnUpdatedProductSale_whenProductSaleExists() throws Exception {
        when(getProductSaleByIdInbound.execute(productSale.getId())).thenReturn(Optional.of(productSale));
        when(updateProductSaleInbound.execute(any(ProductSale.class))).thenReturn(productSale);

        mockMvc.perform(put("/product-sales")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"product\": {\"id\": 1}, \"quantity\": 5, \"totalPrice\": 500.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productSale.getId()))
                .andExpect(jsonPath("$.quantity").value(productSale.getQuantity()));
    }

    @Test
    void updateProductSale_shouldThrowProductSaleNotFoundException_whenProductSaleDoesNotExist() throws Exception {
        when(getProductSaleByIdInbound.execute(productSale.getId())).thenReturn(Optional.empty());

        mockMvc.perform(put("/product-sales")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"product\": {\"id\": 1}, \"quantity\": 5, \"totalPrice\": 500.0}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteProductSale_shouldReturnOk_whenProductSaleExists() throws Exception {
        when(getProductSaleByIdInbound.execute(productSale.getId())).thenReturn(Optional.of(productSale));
        doNothing().when(deleteProductSaleInbound).execute(productSale.getId());

        mockMvc.perform(delete("/product-sales/{id}", productSale.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void deleteProductSale_shouldThrowProductSaleNotFoundException_whenProductSaleDoesNotExist() throws Exception {
        when(getProductSaleByIdInbound.execute(productSale.getId())).thenReturn(Optional.empty());

        mockMvc.perform(delete("/product-sales/{id}", productSale.getId()))
                .andExpect(status().isNotFound());
    }
}
