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

  <img src="https://github.com/Iaura-w/libraryManagement/assets/26602440/f6c996a6-cbeb-4b9e-b280-4a1c69d4b198" width=800>

### Customers

- Find all customers: GET /customers
- Find customer by id: GET /customers/{id}
- Add customer: POST /customers
- Update customer: PUT /customers/{id}
- Delete customers (only if not on loans table): DELETE /customers/{id}

  <img src="https://github.com/Iaura-w/libraryManagement/assets/26602440/000cfba9-7cca-4c0b-87ab-7698fdf5db0f" width=800>


### Library

- Borrow book by customer: POST /library/borrow/{bookId}/customer/{customerId}
- Return book by customer: POST /library/return/{bookId}/customer/{customerId}

  <img src="https://github.com/Iaura-w/libraryManagement/assets/26602440/0002e0aa-b118-45d3-8864-612db826cf31" width=800>

## Example server response for endpoint GET /customers
<img src="https://github.com/Iaura-w/libraryManagement/assets/26602440/4c42b1c9-325e-4063-a579-e71bf05a099b">

