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
- Proper HTTP status codes and error handling
- Documentation with JavaDoc comments

## API Endpoints

### Books

- `GET /api/books` - Get all books
- `GET /api/books/{id}` - Get a specific book
- `POST /api/books` - Create a new book
- `PUT /api/books/{id}` - Update a book
- `DELETE /api/books/{id}` - Delete a book

### Authors

- `GET /api/authors` - Get all authors
- `GET /api/authors/{id}` - Get a specific author
- `POST /api/authors` - Create a new author
- `PUT /api/authors/{id}` - Update an author
- `DELETE /api/authors/{id}` - Delete an author
- `GET /api/authors/{id}/books` - Get all books by an author

### Customers

- `GET /api/customers` - Get all customers
- `GET /api/customers/{id}` - Get a specific customer
- `POST /api/customers` - Create a new customer
- `PUT /api/customers/{id}` - Update a customer
- `DELETE /api/customers/{id}` - Delete a customer

### Shopping Carts

- `GET /api/customers/{customerId}/cart` - Get a customer's cart
- `POST /api/customers/{customerId}/cart/items` - Add an item to the cart
- `PUT /api/customers/{customerId}/cart/items/{bookId}` - Update an item in the cart
- `DELETE /api/customers/{customerId}/cart/items/{bookId}` - Remove an item from the cart

### Orders

- `POST /api/customers/{customerId}/orders` - Create a new order from the cart
- `GET /api/customers/{customerId}/orders` - Get all orders for a customer
- `GET /api/customers/{customerId}/orders/{orderId}` - Get a specific order

## Setup

1. Ensure you have Java 17+ and Maven installed.
2. Clone the repository.
3. Build the project with Maven:
   ```
   mvn clean package
   ```
4. Deploy the WAR file to a Jakarta EE compatible server (e.g., Tomcat 10+, Glassfish, WildFly).

## Testing

You can test the API using Postman, cURL, or any HTTP client. Here are some sample requests:

### Create a Book (POST /api/books)
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

### Create a Customer (POST /api/customers)
```json
{
  "name": "Alice Johnson",
  "email": "alice.johnson@example.com",
  "password": "password123"
}
```

### Add an Item to Cart (POST /api/customers/1/cart/items)
```json
{
  "bookId": 1,
  "quantity": 2
}
```

## Error Handling

The API provides consistent error responses with appropriate HTTP status codes:
- 400 Bad Request - For invalid input
- 404 Not Found - When requested resources don't exist
- 500 Internal Server Error - For server-side errors
