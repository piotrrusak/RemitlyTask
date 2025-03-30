
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

```bash
docker-compose up --build
```

The application will be available at: [http://localhost:8080](http://localhost:8080)

Database is accessible via MySQL on port `3307` (user: `root`, pass: `root`, db: `swiftdb`)

---

## 🔌 API Endpoints

All endpoints are under `/v1`:

### 1. Get bank details by SWIFT code
```
GET /v1/swift-codes/{swiftCode}
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

### 2. Get all banks in a country
```
GET /v1/swift-codes/country/{countryISO2}
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

### 3. Create a new bank
```
POST /v1/swift-codes
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

### 4. Delete a bank by SWIFT code
```
DELETE /v1/swift-codes/{swiftCode}
```

**Response**
```json
{ "message": "Bank deleted" }
```

---

## 🧪 Running Tests

Use Maven to run tests:

```bash
./mvnw test
```

- Integration tests cover all endpoints
- Unit tests validate business logic

---

## 📁 Project Structure

- `src/main/java/.../rest` – Controllers
- `src/main/java/.../service` – Business logic
- `src/main/java/.../entity` – DTOs and Entities
- `src/test/java` – Unit & Integration tests
- `docker-compose.yml` – Container setup
- `init_swiftdb.sql` – DB initialization