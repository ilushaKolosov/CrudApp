API Documentation
This project includes three main controllers to manage products, sales, and supplies in an inventory system. Each controller provides CRUD operations and specific business logic.

Base URL
/api/v1

1. ProductController
Base URL: /products
Endpoints
1.1 Get All Products
Endpoint: GET /products
Description: Retrieves a list of products with optional filters and sorting.
Query Parameters:
name (String): Filters by product name.
priceMin (Double): Minimum price.
priceMax (Double): Maximum price.
inStock (Boolean): Filter by stock status.
sortBy (String): Sort products by a specified attribute.
limit (Integer): Limits the number of results.
Response: List of Product objects.
1.2 Get Product by ID
Endpoint: GET /products/{id}
Description: Fetches a product by its ID.
Path Variables:
id (Long): Product ID.
Response: Product object.
Error: Returns 404 if the product is not found.
1.3 Create Product
Endpoint: POST /products
Description: Creates a new product.
Request Body: Product object.
Response: Created Product object.
1.4 Update Product
Endpoint: PUT /products
Description: Updates an existing product.
Request Body: Updated Product object (requires ID).
Response: Updated Product object.
Error: Returns 404 if the product is not found.
1.5 Delete Product
Endpoint: DELETE /products/{id}
Description: Deletes a product by ID.
Path Variables:
id (Long): Product ID.
Response: 204 No Content on success.
Error: Returns 404 if the product is not found.
2. ProductSaleController
Base URL: /product-sales
Endpoints
2.1 Create Product Sale
Endpoint: POST /product-sales
Description: Creates a new product sale entry.
Request Body: ProductSale object.
Validation: Checks if the associated product exists.
Response: Created ProductSale object.
2.2 Get All Product Sales
Endpoint: GET /product-sales
Description: Retrieves a list of all product sales.
Response: List of ProductSale objects.
2.3 Get Product Sale by ID
Endpoint: GET /product-sales/{id}
Description: Fetches a product sale by its ID.
Path Variables:
id (Long): ProductSale ID.
Response: ProductSale object.
Error: Returns 404 if the product sale is not found.
2.4 Update Product Sale
Endpoint: PUT /product-sales
Description: Updates an existing product sale entry.
Request Body: Updated ProductSale object (requires ID).
Response: Updated ProductSale object.
Error: Returns 404 if the product sale is not found.
2.5 Delete Product Sale
Endpoint: DELETE /product-sales/{id}
Description: Deletes a product sale by ID.
Path Variables:
id (Long): ProductSale ID.
Response: 204 No Content on success.
Error: Returns 404 if the product sale is not found.
3. ProductSupplyController
Base URL: /product-supplies
Endpoints
3.1 Create Product Supply
Endpoint: POST /product-supplies
Description: Creates a new product supply entry.
Request Body: ProductSupply object.
Validation: Checks if the associated product exists.
Response: Created ProductSupply object.
3.2 Get All Product Supplies
Endpoint: GET /product-supplies
Description: Retrieves a list of all product supplies.
Response: List of ProductSupply objects.
3.3 Get Product Supply by ID
Endpoint: GET /product-supplies/{id}
Description: Fetches a product supply by its ID.
Path Variables:
id (Long): ProductSupply ID.
Response: ProductSupply object.
Error: Returns 404 if the product supply is not found.
3.4 Update Product Supply
Endpoint: PUT /product-supplies
Description: Updates an existing product supply entry.
Request Body: Updated ProductSupply object (requires ID).
Response: Updated ProductSupply object.
Error: Returns 404 if the product supply is not found.
3.5 Delete Product Supply
Endpoint: DELETE /product-supplies/{id}
Description: Deletes a product supply by ID.
Path Variables:
id (Long): ProductSupply ID.
Response: 204 No Content on success.
Error: Returns 404 if the product supply is not found.
