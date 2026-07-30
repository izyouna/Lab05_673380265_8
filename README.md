# Coffee Shop REST API (Lab05 - Spring Boot)

A RESTful API application for managing a coffee shop menu (Create, Read, Update, Delete) built with Spring Boot 3.x following Layered Architecture principles (Model - Service - Controller).

## [Report Here!](https://docs.google.com/document/d/1snFQZB3olqkvNXHGxubF5f_-Jn5m9ocTpRLYe-qlBFw/edit?usp=sharing)
##

---

## Tech Stack & Prerequisites
- **Language:** Java 17+
- **Framework:** Spring Boot 3.3.0 (Spring Web)
- **Build Tool:** Maven
- **Data Storage:** In-Memory List (`List<Coffee>`)

---

## System Architecture (Layered Design)

| Layer | Class | Responsibility |
|---|---|---|
| **Model** | `Coffee` | Represents the data structure (`id`, `name`, `price`) |
| **Service** | `CoffeeService` | Implements business logic and manages the in-memory `List<Coffee>` |
| **Controller** | `CoffeeController` | Handles HTTP Requests and delegates execution to `CoffeeService` (stateless) |

---

## How to Compile & Build

Navigate into the project folder (`cs6733802658`):
```bash
cd cs6733802658
```

### 1. Compile Source Code
To compile the Java source files (`.java` to `.class`):

**macOS / Linux:**
```bash
./mvnw compile
```

**Windows:**
```cmd
mvnw.cmd compile
```

### 2. Clean and Compile (Fresh Build)
To remove previous build artifacts in `target/` and perform a clean re-compilation:

**macOS / Linux:**
```bash
./mvnw clean compile
```

**Windows:**
```cmd
mvnw.cmd clean compile
```

### 3. Package Application (Create JAR file)
To compile, run tests, and package the application into an executable JAR file:

**macOS / Linux:**
```bash
./mvnw clean package
```

**Windows:**
```cmd
mvnw.cmd clean package
```

---

## How to Run the Application

### 1. Run via Spring Boot Plugin
Open a terminal in the project directory (`cs6733802658`) and execute:

**macOS / Linux:**
```bash
./mvnw spring-boot:run
```

**Windows:**
```cmd
mvnw.cmd spring-boot:run
```

**Using System Maven:**
```bash
mvn spring-boot:run
```

Once started successfully, the server will be available at `http://localhost:8080`.

### 2. Run Unit Tests
```bash
./mvnw test
```

---

## API Endpoints Specification

| # | HTTP Method | Endpoint Path | Description | Expected Status Code |
|---|---|---|---|---|
| 1 | `GET` | `/coffees` | Retrieve all coffee menu items | `200 OK` |
| 2 | `GET` | `/coffees/{id}` | Retrieve a single coffee item by ID | `200 OK` / `404 Not Found` |
| 3 | `POST` | `/coffees` | Create a new coffee menu item | `201 Created` |
| 4 | `PUT` | `/coffees/{id}` | Update an existing coffee item by ID | `200 OK` / `404 Not Found` |
| 5 | `DELETE` | `/coffees/{id}` | Delete a coffee item by ID | `200 OK` / `404 Not Found` |

---

## API Usage Examples (`curl`)

### 1. Get All Coffees (`GET /coffees`)
```bash
curl -X GET http://localhost:8080/coffees
```
**Example Response:**
```json
[
  { "id": 1, "name": "Espresso", "price": 45.0 },
  { "id": 2, "name": "Latte", "price": 55.0 }
]
```

---

### 2. Get Coffee by ID (`GET /coffees/{id}`)
```bash
curl -X GET http://localhost:8080/coffees/1
```
**Example Response (`200 OK`):**
```json
{
  "id": 1,
  "name": "Espresso",
  "price": 45.0
}
```

**Non-existent ID (`404 Not Found`):**
```bash
curl -X GET http://localhost:8080/coffees/999
```

---

### 3. Add New Coffee (`POST /coffees`)
```bash
curl -X POST http://localhost:8080/coffees \
     -H "Content-Type: application/json" \
     -d '{"name": "Cappuccino", "price": 60.0}'
```
**Example Response (`201 Created`):**
```json
{
  "id": 3,
  "name": "Cappuccino",
  "price": 60.0
}
```

---

### 4. Update Coffee by ID (`PUT /coffees/{id}`)
```bash
curl -X PUT http://localhost:8080/coffees/2 \
     -H "Content-Type: application/json" \
     -d '{"name": "Latte", "price": 50.0}'
```
**Example Response (`200 OK`):**
```json
{
  "id": 2,
  "name": "Latte",
  "price": 50.0
}
```

---

### 5. Delete Coffee by ID (`DELETE /coffees/{id}`)
```bash
curl -X DELETE http://localhost:8080/coffees/3
```
**Example Response (`200 OK`):**
```text
Coffee with id 3 deleted successfully.
```
