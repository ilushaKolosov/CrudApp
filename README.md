ProductController API Endpoints
This API provides CRUD operations for managing products, along with an additional endpoint to retrieve the database state.

Base URL
/products

Endpoints
1. Get All Products
Endpoint: GET /products
Description: Retrieves a list of all products.
Response: Returns a list of Product objects.
2. Get Product by ID
Endpoint: GET /products/{id}
Description: Retrieves a product by its ID.
Path Variables:
id (Long): The ID of the product.
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
6. Get Database State
Endpoint: GET /products/info/database-state
Description: Retrieves information about the current state of the product database.
Response: Returns a String message indicating the database state.
