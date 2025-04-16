# JAX-RS Bookstore API

A RESTful Bookstore API using JAX-RS (Jakarta RESTful Web Services) with Jersey implementation.

## Overview

This project implements a comprehensive RESTful API for a bookstore application. It provides endpoints for managing books, authors, customers, shopping carts, and orders.

## Features

- Complete CRUD operations for all entities:
  - Books
  - Authors
  - Customers
  - Shopping Carts
  - Orders
- In-memory data storage with thread-safe collections
- RESTful architecture following best practices
- JSON input/output
- Comprehensive exception handling with custom exception mappers
- Proper HTTP status codes
- Validation for input data

## API Endpoints

### Books

- `GET /books` - Get all books
- `GET /books/{id}` - Get a specific book
- `POST /books` - Create a new book
- `PUT /books/{id}` - Update a book
- `DELETE /books/{id}` - Delete a book

### Authors

- `GET /authors` - Get all authors
- `GET /authors/{id}` - Get a specific author
- `POST /authors` - Create a new author
- `PUT /authors/{id}` - Update an author
- `DELETE /authors/{id}` - Delete an author
- `GET /authors/{id}/books` - Get all books by an author

### Customers

- `GET /customers` - Get all customers
- `GET /customers/{id}` - Get a specific customer
- `POST /customers` - Create a new customer
- `PUT /customers/{id}` - Update a customer
- `DELETE /customers/{id}` - Delete a customer

### Shopping Carts

- `GET /customers/{customerId}/cart` - Get a customer's cart
- `POST /customers/{customerId}/cart/items` - Add an item to the cart
- `PUT /customers/{customerId}/cart/items/{bookId}` - Update an item in the cart
- `DELETE /customers/{customerId}/cart/items/{bookId}` - Remove an item from the cart

### Orders

- `POST /customers/{customerId}/orders` - Create a new order from the cart
- `GET /customers/{customerId}/orders` - Get all orders for a customer
- `GET /customers/{customerId}/orders/{orderId}` - Get a specific order

## Exception Handling

The API provides comprehensive exception handling with custom exception mappers:

- `AuthorNotFoundException` - When an author with the specified ID doesn't exist
- `BookNotFoundException` - When a book with the specified ID doesn't exist
- `CartNotFoundException` - When a cart doesn't exist for the specified customer
- `CustomerNotFoundException` - When a customer with the specified ID doesn't exist
- `OrderNotFoundException` - When an order with the specified ID doesn't exist
- `InvalidInputException` - When input validation fails (e.g., negative price, empty name)
- `OutOfStockException` - When trying to order more books than available in stock
- `JsonParseExceptionMapper` - For handling malformed JSON requests
- `GlobalExceptionMapper` - Catch-all for any unexpected exceptions

All exceptions are mapped to appropriate HTTP status codes and return consistent JSON error responses with an error type and message.

## Setup

1. Ensure you have Java 21+ and Maven installed.
2. Clone the repository.
3. Build the project with Maven:
   ```
   mvn clean package
   ```
4. Deploy the WAR file to a Jakarta EE compatible server (e.g., Tomcat 11+).
5. Alternatively, use the provided run.sh script:
   ```
   ./run.sh
   ```

## Testing

You can test the API using Postman, cURL, or any HTTP client. Here are some sample requests:

### Create a Book (POST /books)
```json
{
  "title": "The Hobbit",
  "authorId": 1,
  "isbn": "978-0-261-10295-9",
  "publicationYear": 1937,
  "price": 12.99,
  "stock": 50
}
```

### Create a Customer (POST /customers)
```json
{
  "name": "Alice Johnson",
  "email": "alice.johnson@example.com",
  "password": "password123"
}
```

### Add an Item to Cart (POST /customers/1/cart/items)
```json
{
  "bookId": 1,
  "quantity": 2
}
```

### Create an Order (POST /customers/1/orders)
```
No request body needed - creates an order from the customer's current cart
```

## Error Response Format

All error responses follow a consistent JSON format:

```json
{
  "error": "Error Type",
  "message": "Detailed error message"
}
```

Examples:
- 404 Not Found: `{"error": "Book Not Found", "message": "Book with ID 999 does not exist."}`
- 400 Bad Request: `{"error": "Invalid Input", "message": "Book price cannot be negative"}`
- 500 Internal Server Error: `{"error": "Internal Server Error", "message": "An unexpected error occurred"}`
