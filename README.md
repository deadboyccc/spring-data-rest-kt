# Spring Data REST Kotlin Application

A simple Spring Boot application demonstrating Spring Data REST with Kotlin, featuring a Beer management system.

## Features

- **Spring Data REST**: Automatic REST API generation from JPA repositories
- **Kotlin**: Modern JVM language with concise syntax
- **H2 Database**: In-memory database for development and testing
- **JPA/Hibernate**: Object-relational mapping
- **Data Validation**: Bean validation with Jakarta validation
- **Bootstrap Data**: Automatic loading of sample beer data

## Project Structure

```
src/main/kotlin/dev/dead/springdatarestkt/
├── SpringDataRestKtApplication.kt    # Main application class
├── entities/
│   ├── Beer.kt                       # Beer entity with JPA annotations
│   └── BeerStyle.kt                  # Beer style enum
├── repositories/
│   └── BeerRepository.kt            # JPA repository interface
└── bootstrap/
    └── BeerLoader.kt                 # Data initialization component
```

## API Endpoints

Once the application is running, Spring Data REST automatically exposes the following endpoints:

- `GET /api/beers` - List all beers (paginated)
- `GET /api/beers/{id}` - Get a specific beer
- `POST /api/beers` - Create a new beer
- `PUT /api/beers/{id}` - Update a beer
- `DELETE /api/beers/{id}` - Delete a beer
- `GET /api/beers/search` - Discover available search methods

### Search Endpoints

- `GET /api/beers/search/findAllByBeerName?beerName={name}` - Find beers by name
- `GET /api/beers/search/findAllByBeerStyle?beerStyle={style}` - Find beers by style
- `GET /api/beers/search/findByUpc?upc={upc}` - Find beer by UPC

## Running the Application

### Prerequisites

- Java 17 or higher
- Maven 3.6+

### Build and Run

```bash
# Build the project
./mvnw clean compile

# Run tests
./mvnw test

# Run the application
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

### H2 Console

Access the H2 database console at `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: `password`

## Sample Data

The application automatically loads 30 sample beers with various styles including:
- ALE, IPA, STOUT, LAGER, WHEAT, PORTER, PILSNER, GOSE, SAISON, PALE_ALE

## Configuration

Key configuration properties in `application.properties`:

```properties
# Database
spring.datasource.url=jdbc:h2:mem:testdb
spring.h2.console.enabled=true

# JPA
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# REST API
spring.data.rest.base-path=/api
```

## Technologies Used

- Spring Boot 3.5.6
- Spring Data REST
- Spring Data JPA
- Kotlin 1.9.25
- H2 Database
- Hibernate
- Jakarta Validation
- Jackson (Kotlin module)
