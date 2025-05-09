# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Spring Boot application for managing job listings for Clark County School District (CCSD). The application provides:
- A RESTful API for CRUD operations
- A web interface using Thymeleaf templates
- Export functionality to Excel and PDF formats
- Different job categories: Administrative Personnel, Licensed Personnel, and Support Professional

## Build Commands

```bash
# Build the project
mvn clean install

# Run the application (development mode)
mvn spring-boot:run

# Run tests
mvn test

# Run a specific test class
mvn test -Dtest=AdministrativePersonnelApiControllerTest

# Run a specific test method
mvn test -Dtest=AdministrativePersonnelApiControllerTest#testGetAllAdministrativePersonnel
```

## Database Configuration

The application uses MySQL. Create a file named `env.properties` in the project root with the following variables:

```properties
DB_DATABASE=your_database_name
DB_USER=your_username
DB_PASSWORD=your_password
```

## Code Architecture

### Package Structure
- `com.bryansiegel.ccsdjobsjava`
  - `.config`: Contains application configuration (e.g., SecurityConfig)
  - `.controllers`: MVC controllers for web interface
    - `.api`: REST API controllers
  - `.models`: JPA entity classes
  - `.repositories`: Spring Data JPA repositories
  - `.services`: Service layer classes (e.g., export services)

### Key Components

1. **Models Layer**: JPA entities representing different job types
   - AdministrativePersonnel, LicensedPersonnel, SupportProfessional
   - Annotated with JPA annotations for database mapping

2. **Repository Layer**: Interfaces extending JpaRepository
   - Provide CRUD operations for each entity
   - Spring Data JPA handles implementation

3. **Controller Layer**: 
   - Web controllers returning Thymeleaf templates
   - REST API controllers returning JSON
   - Organized by job category

4. **Service Layer**:
   - ExportExcelService: Exports job listings to Excel format
   - ExportPDFService: Exports job listings to PDF format

5. **Views**:
   - Thymeleaf templates organized by job category
   - Components using HTMX for dynamic content loading

## Security

The application uses Spring Security for authentication and authorization. The `SecurityConfig` class configures security rules.

## Testing

Tests use:
- JUnit 5 for test organization
- Spring Boot Test for Spring context testing
- MockMvc for controller testing
- Mockito for mocking dependencies