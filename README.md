# CCSD Jobs Java Application

This is a Spring Boot application for managing administrative personnel data. The application provides a RESTful API for CRUD operations and a web interface for managing administrative personnel details.

## Technologies Used

- Java
- Spring Boot
- Maven
- Thymeleaf
- Vue
- Mockito
- JUnit
- MySQL

## Prerequisites

- Java 17 or higher
- Maven 3.6.0 or higher
- MySQL 8.0 or higher

## Setup

1. **Clone the repository:**

    ```sh
    git clone https://github.com/bryansiegel/ccsd-jobs-java.git
    cd ccsd-jobs-java
    ```

2. **Configure the database:**

   Create a MySQL database and update the `application.properties` file with your database credentials.

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update
    ```

3. **Build the project:**

    ```sh
    mvn clean install
    ```

4. **Run the application:**

    ```sh
    mvn spring-boot:run
    ```

## API Endpoints

- **GET /api/administrative-personnel**: Get all administrative personnel
- **GET /api/administrative-personnel/{id}**: Get administrative personnel by ID
- **POST /api/administrative-personnel**: Create new administrative personnel
- **PUT /api/administrative-personnel/{id}**: Update administrative personnel by ID
- **DELETE /api/administrative-personnel/{id}**: Delete administrative personnel by ID

## Web Interface

The web interface is accessible at `http://localhost:8080/admin/administrative-personnel`. It provides forms for creating, updating, and viewing administrative personnel details.

## Running Tests

To run the tests, use the following command:

```sh
mvn test
```

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.