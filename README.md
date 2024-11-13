ProductController API Endpoints
This API provides CRUD operations for managing products, with support for advanced filtering and sorting on the product list endpoint.

Base URL
/products

Endpoints
1. Get All Products
Endpoint: GET /products
Description: Retrieves a list of products, with optional filtering, sorting, and limiting capabilities.
Query Parameters:
name (String): Filters products by name (optional).
priceMin (Double): Filters products with a minimum price (optional).
priceMax (Double): Filters products with a maximum price (optional).
inStock (Boolean): Filters products that are in stock (optional).
sortBy (String): Specifies the attribute by which the products should be sorted (e.g., "name", "price"). Sorting order defaults to ascending unless specified otherwise (optional).
limit (Integer): Limits the number of products returned (optional).
Response: Returns a list of Product objects that match the specified filters.
2. Get Product by ID
Endpoint: GET /products/{id}
Description: Retrieves a product by its ID.
Path Variables:
id (Long): The ID of the product to retrieve.
Response: Returns the Product object if found.
Error: If the product is not found, returns a 404 Not Found status with a ProductNotFoundException.
3. Create Product
Endpoint: POST /products
Description: Creates a new product.
Request Body:
Product object: The details of the product to create.
Response: Returns the created Product object.
4. Update Product
Endpoint: PUT /products
Description: Updates an existing product.
Request Body:
Product object: The updated details of the product. The product ID must be specified.
Response: Returns the updated Product object.
Error: If the product is not found, returns a 404 Not Found status with a ProductNotFoundException.
5. Delete Product
Endpoint: DELETE /products/{id}
Description: Deletes a product by its ID.
Path Variables:
id (Long): The ID of the product to delete.
Response: Returns a 204 No Content status if the deletion is successful.
Error: If the product is not found, returns a 404 Not Found status with a ProductNotFoundException.
