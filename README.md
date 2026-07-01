# 🥛 Dairy Billing System - Multi-Tenant REST API

A production-ready **Spring Boot REST API** for dairy operations and milk collection management. The system supports customer registration, secure role-based authentication, automated billing, and real-time SMS notifications. It is built with a **database-per-tenant** architecture, allowing multiple dairy branches to operate independently using a single backend.

---

## ✨ Features

- Multi-tenant architecture (Database per Tenant)
- Secure authentication using JWT
- Role-based authorization with Spring Security
- Customer management
- Milk collection management
- Automatic billing based on milk quantity and fat percentage
- Real-time SMS receipt generation using Twilio
- RESTful API design
- MySQL database integration

---

## 🛠 Tech Stack

### Backend
- Java 17+
- Spring Boot 3
- Spring Security 6
- Spring Data JPA (Hibernate)

### Database
- MySQL 8

### Authentication
- JWT (JSON Web Tokens)

### SMS Service
- Twilio API

---

## 🏗 Multi-Tenant Architecture

This project follows a **Database-per-Tenant** architecture to provide complete data isolation between different dairy branches.

Every request must include the `X-Tenant-ID` header.

Example:

```http
X-Tenant-ID: dairy_db
```

```http
X-Tenant-ID: national
```

The application dynamically routes each request to the corresponding MySQL database.

---

## 📂 Project Structure

```
src
├── controller
├── service
├── repository
├── entity
├── security
├── tenant
├── config
├── dto
└── exception
```

---

# 🚀 Local Setup

## 1. Create Databases

Run the following SQL commands in MySQL.

```sql
CREATE DATABASE dairy_db;
CREATE DATABASE national_dairy_db;
CREATE DATABASE sunrise_dairy_db;
```

Hibernate will automatically create the required tables when the application starts.

---

## 2. Configure application.properties

Update your local MySQL credentials.

```properties
# Master Database
app.datasource.master.jdbcUrl=jdbc:mysql://localhost:3306/dairy_db?useSSL=false&allowPublicKeyRetrieval=true
app.datasource.master.username=root
app.datasource.master.password=YOUR_PASSWORD

# National Database
app.datasource.national.jdbcUrl=jdbc:mysql://localhost:3306/national_dairy_db?useSSL=false&allowPublicKeyRetrieval=true
app.datasource.national.username=root
app.datasource.national.password=YOUR_PASSWORD
```

Configure JWT.

```properties
jwt.secret=YOUR_256_BIT_SECRET_KEY
```

Configure Twilio.

```properties
twilio.account.sid=YOUR_TWILIO_SID
twilio.auth.token=YOUR_TWILIO_AUTH_TOKEN
twilio.phone.number=YOUR_TWILIO_PHONE_NUMBER
```

---

## 3. Run the Application

Clone the repository.

```bash
git clone https://github.com/your-username/dairy-billing-system.git
```

Navigate to the project.

```bash
cd dairy-billing-system
```

Run the application.

```bash
./mvnw spring-boot:run
```

Or run the main Spring Boot application class from IntelliJ IDEA or Eclipse.

The server starts on:

```
http://localhost:8080
```

---

# 🔐 Authentication

## Register User

```
POST /api/v1/auth/register
```

Creates a new user.

> Requires **BRANCH_ADMIN** role for administrative access.

---

## Login

```
POST /api/v1/auth/login
```

Returns a JWT Bearer Token.

Include the token in future requests.

```http
Authorization: Bearer <your-jwt-token>
```

---

# 🥛 Milk Collection API

## Record Milk Collection

```
POST /api/v1/collection/customer/{customerId}
```

### Required Headers

```http
Authorization: Bearer <JWT_TOKEN>
X-Tenant-ID: dairy_db
```

### Request Payload

```json
{
  "quantity": 12.5,
  "fatPercentage": 4.2,
  "shift": "Morning"
}
```

### What Happens?

- Saves milk collection details
- Calculates payout based on fat percentage
- Stores the transaction
- Sends an SMS receipt to the customer's registered mobile number using Twilio

---

# 🔒 Security

- Spring Security 6
- JWT Authentication
- Role-Based Authorization
- Protected REST APIs
- Tenant-based database isolation

---

# 📡 API Highlights

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/api/v1/auth/register` | Register a user |
| POST | `/api/v1/auth/login` | Login and receive JWT |
| POST | `/api/v1/collection/customer/{customerId}` | Record milk collection |

---

# 📌 Future Improvements

- Swagger/OpenAPI documentation
- Docker support
- Kubernetes deployment
- Monthly billing reports
- Email notifications
- Admin dashboard
- Payment integration

---

## 👨‍💻 Author

**Shubham Dashrath Jadhav**

Backend Developer | Java | Spring Boot | MySQL
