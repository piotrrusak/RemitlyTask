# Swift Bank API

A Spring Boot application for managing and querying SWIFT code-based bank data. The app supports creating, retrieving, listing, and deleting banks via RESTful endpoints.

## 📦 Tech Stack

- Java 17
- Spring Boot
- MySQL 8
- Maven
- Docker / Docker Compose
- JUnit 5 + MockMvc (tests)

---

## 🚀 Getting Started

### Prerequisites

- Docker & Docker Compose

### Run with Docker

Build the JAR (only once or after changes):

```bash
    mvn clean package
```

Then run the containers:

```bash
    docker-compose down -v
    docker-compose up --build
```

If you get a permission error, try running with `sudo`:

```bash
    sudo docker-compose down -v
    sudo docker-compose up --build
```

The application will be available at: [http://localhost:8080](http://localhost:8080)

Database is accessible via MySQL on port `3307` (user: `root`, pass: `root`, db: `swiftdb`)

---

## 🔌 API Endpoints

### 1. Get all of the banks

```
GET /api/banks
```

### 2. Get bank details by SWIFT code

```
GET /api/banks/{swiftCode}
```

**Response**
```json
{
  "swiftCode": "BOFAUS3N",
  "bankName": "Bank of America",
  "address": "123 Main St",
  "countryISO2": "US",
  "countryName": "United States",
  "isHeadquarter": true,
  "branches": [ ... ]
}
```

---

### 3. Get all banks in a country
```
GET /api/banks/country/{countryISO2}
```

**Response**
```json
{
  "countryISO2": "PL",
  "countryName": "Poland",
  "banksFromCountry": [ ... ]
}
```

---

### 4. Create a new bank
```
POST /api/banks
```

**Body**
```json
{
  "swiftCode": "BOFAUS3N",
  "bankName": "Bank of America",
  "address": "123 Main St",
  "countryISO2": "US",
  "countryName": "United States",
  "isHeadquarter": true
}
```

**Response**
```json
{ "message": "Bank created" }
```

---

### 5. Delete a bank by SWIFT code
```
DELETE /api/banks/{swiftCode}
```

**Response**
```json
{ "message": "Bank deleted" }
```

## Tests

To run tests locally with Maven:

```bash
mvn test
```

---

## 📁 Project Structure

- `src/main/java/.../controller` – Controllers
- `src/main/java/.../service` – Business logic
- `src/main/java/.../model` – Database model
- `src/main/java/.../dto` – Data Transfer Objects
- `src/test/java` – Unit & Integration tests
- `docker-compose.yml` – Container setup