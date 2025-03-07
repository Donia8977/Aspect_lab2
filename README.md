# Spring Boot CRUD API with Logging Aspect

This project is a Spring Boot REST API that provides CRUD (Create, Read, Update, Delete) operations for managing products. The application connects to a local MySQL/PostgreSQL database and logs request bodies using Aspect-Oriented Programming (AOP).

## Features
- Create, Update, Delete, and Find by ID endpoints.
- Uses `@RequestBody` and `@PathVariable` for handling requests.
- Connects to a PostgreSQL database .
- Implements an Aspect to log request bodies before the controller methods execute .

## Technologies Used
- Java 23
- Spring Boot 3.x
- Spring Data JPA
- Hibernate
- PostgreSQL
- Lombok 
- Aspect-Oriented Programming (AOP) with Spring
- Postman for API testing

## Endpoints

| Method | Endpoint          | Description               | Request Type |
|--------|------------------|---------------------------|--------------|
| POST   | `/products`      | Create a new product      | `@RequestBody` |
| GET    | `/products/{id}` | Get a product by ID       | `@PathVariable` |
| PUT    | `/products/{id}` | Update a product by ID    | `@PathVariable` & `@RequestBody` |
| DELETE | `/products/{id}` | Delete a product by ID    | `@PathVariable` |

## Setup Instructions

### 1. Clone the repository
```sh
git clone https://github.com/yourusername/your-repo.git
cd your-repo
