Product API Documentation
This API provides endpoints for managing products in the system. It supports the basic CRUD (Create, Read, Update, Delete) operations.

Base URL
bash
Copy code
/products
Endpoints
1. Get All Products
Method: GET
URL: /products
Description: Retrieves a list of all products.
Response:
200 OK: A list of all products.
Example:
json
Copy code
[
    {
        "id": 1,
        "name": "Product 1",
        "price": 100.0,
        "inStock": true
    },
    {
        "id": 2,
        "name": "Product 2",
        "price": 200.0,
        "inStock": false
    }
]
2. Get Product by ID
Method: GET
URL: /products/{id}
Description: Retrieves a product by its unique ID.
Path Parameter:
id: The unique identifier of the product.
Response:
200 OK: The product with the specified id.
404 Not Found: If the product with the specified id does not exist.
Example:
json
Copy code
{
    "id": 1,
    "name": "Product 1",
    "price": 100.0,
    "inStock": true
}
3. Create Product
Method: POST
URL: /products
Description: Creates a new product.
Request Body:
The request body should include a Product object with the following fields:

name (string) - The name of the product.
price (double) - The price of the product.
inStock (boolean) - Availability of the product.
Response:
201 Created: The newly created product.
400 Bad Request: If the product data is invalid.
Example Request:
json
Copy code
{
    "name": "New Product",
    "price": 150.0,
    "inStock": true
}
Example Response:
json
Copy code
{
    "id": 3,
    "name": "New Product",
    "price": 150.0,
    "inStock": true
}
4. Update Product
Method: PUT
URL: /products
Description: Updates an existing product. The product to be updated must exist, and the request body must include the updated product information.
Request Body:
The request body should include a Product object with the following fields:

id (long) - The ID of the product to be updated.
name (string) - The new name of the product.
price (double) - The new price of the product.
inStock (boolean) - The updated availability status.
Response:
200 OK: The updated product.
404 Not Found: If the product with the specified id does not exist.
Example Request:
json
Copy code
{
    "id": 1,
    "name": "Updated Product 1",
    "price": 120.0,
    "inStock": true
}
Example Response:
json
Copy code
{
    "id": 1,
    "name": "Updated Product 1",
    "price": 120.0,
    "inStock": true
}
5. Delete Product
Method: DELETE
URL: /products/{id}
Description: Deletes a product by its ID.
Path Parameter:
id: The unique identifier of the product to delete.
Response:
200 OK: If the product is successfully deleted.
404 Not Found: If the product with the specified id does not exist.
Example:
json
Copy code
{
    "message": "Product deleted successfully."
}
Error Handling
404 Not Found: When the specified resource (product) does not exist.
400 Bad Request: When the request body is malformed or missing required data.
