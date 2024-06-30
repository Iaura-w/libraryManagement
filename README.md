# Library Management Application

The Library Management Application is an application for managing a library,
allowing for the storage of information about books and customers and enabling the borrowing and returning of books.
Additionally, each customer has loan history with dates of borrowing and returning books.
The application is built with Java using Spring Boot and MongoDB as the database.
The application provides validation to prevent the deletion of books and customers if they are present in the `loans`
table.

## Technologies

- Java
- Spring
- Spring Boot
- MongoDB
- Gradle
- Docker Compose

## Run Application

- Run the Docker Compose with database MongoDB: `docker-compose up`
- Run the application
- The application should be available at: http://localhost:8080 and connected to the MongoDB instance running in the
  Docker container
- After running the application, Swagger UI is available to test API endpoints
  at: http://localhost:8080/swagger-ui/index.html

## Endpoints

### Books

- Find all books: GET /books
- Find book by id: GET /books/{id}
- Add book: POST /books
- Update book: PUT /books/{id}
- Delete book (only if not on loans table): DELETE /books/{id}

### Customers

- Find all customers: GET /customers
- Find customer by id: GET /customers/{id}
- Add customer: POST /customers
- Update customer: PUT /customers/{id}
- Delete customers (only if not on loans table): DELETE /customers/{id}

### Library

- Borrow book by customer: POST /library/borrow/{bookId}/customer/{customerId}
- Return book by customer: POST /library/return/{bookId}/customer/{customerId}
