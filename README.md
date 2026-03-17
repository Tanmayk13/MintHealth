# MintHealth 🏥

MintHealth is a **Spring Boot backend REST API** for managing healthcare workflows such as patients, doctors, appointments, and medical records.

The system provides **secure authentication using JWT** and **role-based access control** for different users (ADMIN, DOCTOR, PATIENT).

This project demonstrates backend development using **Java, Spring Boot, Spring Security, MySQL, Docker, and Swagger**, with deployment on Render and database hosted on Aiven.

---

# 🚀 Tech Stack

- Java 21
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA / Hibernate
- MySQL (Aiven Cloud Database)
- Maven
- Docker
- Swagger / OpenAPI

---

# ✨ Features

- Secure user authentication using **JWT**
- Role-based authorization
  - ADMIN
  - DOCTOR
  - PATIENT
- Secure password encryption using **BCrypt**
- RESTful API design
- API documentation using **Swagger**
- Cloud database integration
- Stateless security configuration (JWT, no server sessions)
- Admin-only creation of **DOCTOR** and **ADMIN** accounts
- “My appointments” endpoints (no IDs required in URL)
- Secure access controls for medical records and appointments (ownership checks)

---

# 📚 API Documentation

Swagger UI allows you to explore and test all APIs.

Swagger URL:

```id="0e9s3l"
https://<your-render-url>/swagger-ui/index.html
```

Example:

```id="ztvl0k"
https://minthealth.onrender.com/swagger-ui/index.html
```

Swagger provides:

- Endpoint documentation
- Request / response schemas
- Interactive API testing

---

# 🧭 Quick Guide (Endpoints)

Base URL (local):

```bash
http://localhost:8080
```

### Authentication

- **POST** `/auth/register` (Public) → creates a **PATIENT** + patient profile
- **POST** `/auth/login` (Public) → returns JWT

### Admin (ADMIN only)

- **POST** `/admin/users` → create **DOCTOR** or **ADMIN**

### Doctors

- **GET** `/doctors` (ADMIN, PATIENT)
- **GET** `/doctors/{id}` (Authenticated)

### Appointments

- **GET** `/appointments` (ADMIN, DOCTOR)
- **POST** `/appointments` (PATIENT) → book appointment
- **PUT** `/appointments/{appointmentId}/cancel` (PATIENT, owner only)
- **GET** `/appointments/me` (PATIENT)
- **GET** `/appointments/me/doctor` (DOCTOR)

### Medical Records

- **POST** `/records` (DOCTOR)
- **GET** `/records/{appointmentId}` (DOCTOR/ADMIN or PATIENT owner)

---

# 🧪 API Examples (copy‑paste)

## 1) Register (Public → PATIENT)

```bash
curl -X POST "http://localhost:8080/auth/register" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Patient",
    "email": "john@gmail.com",
    "password": "john123"
  }'
```

Example response:

```json
{
  "token": "<jwt-token>"
}
```

## 2) Login (Public)

```bash
curl -X POST "http://localhost:8080/auth/login" \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@gmail.com",
    "password": "john123"
  }'
```

Example response:

```json
{
  "token": "<jwt-token>"
}
```

## 3) Admin creates DOCTOR (ADMIN only)

```bash
curl -X POST "http://localhost:8080/admin/users" \
  -H "Authorization: Bearer <admin-jwt>" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Dr. Jane Doe",
    "email": "jane.doe@minthealth.com",
    "password": "ChangeMe123",
    "role": "DOCTOR",
    "specialization": "Cardiology",
    "experience": 5
  }'
```

Example response:

```json
{
  "userId": 10,
  "doctorId": 3,
  "email": "jane.doe@minthealth.com",
  "role": "DOCTOR"
}
```

## 4) Admin creates ADMIN (ADMIN only)

```bash
curl -X POST "http://localhost:8080/admin/users" \
  -H "Authorization: Bearer <admin-jwt>" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "System Admin",
    "email": "admin2@minthealth.com",
    "password": "ChangeMe123",
    "role": "ADMIN"
  }'
```

Example response:

```json
{
  "userId": 11,
  "doctorId": null,
  "email": "admin2@minthealth.com",
  "role": "ADMIN"
}
```

## 5) List doctors (ADMIN, PATIENT)

```bash
curl "http://localhost:8080/doctors?page=0&size=10" \
  -H "Authorization: Bearer <jwt>"
```

## 6) Get doctor by id (Authenticated)

```bash
curl "http://localhost:8080/doctors/1" \
  -H "Authorization: Bearer <jwt>"
```

## 7) Book appointment (PATIENT)

```bash
curl -X POST "http://localhost:8080/appointments" \
  -H "Authorization: Bearer <patient-jwt>" \
  -H "Content-Type: application/json" \
  -d '{
    "doctorId": 1,
    "appointmentTime": "2026-03-20T10:00:00"
  }'
```

## 8) Cancel appointment (PATIENT, owner only)

```bash
curl -X PUT "http://localhost:8080/appointments/1/cancel" \
  -H "Authorization: Bearer <patient-jwt>"
```

## 9) My appointments (PATIENT)

```bash
curl "http://localhost:8080/appointments/me?page=0&size=10" \
  -H "Authorization: Bearer <patient-jwt>"
```

## 10) My appointments (DOCTOR)

```bash
curl "http://localhost:8080/appointments/me/doctor?page=0&size=10" \
  -H "Authorization: Bearer <doctor-jwt>"
```

## 11) Get all appointments (ADMIN, DOCTOR)

```bash
curl "http://localhost:8080/appointments?page=0&size=10" \
  -H "Authorization: Bearer <jwt>"
```

## 12) Create medical record (DOCTOR)

```bash
curl -X POST "http://localhost:8080/records" \
  -H "Authorization: Bearer <doctor-jwt>" \
  -H "Content-Type: application/json" \
  -d '{
    "appointmentId": 1,
    "diagnosis": "Common Cold",
    "prescription": "Rest + Fluids"
  }'
```

## 13) Get medical record by appointmentId (DOCTOR/ADMIN or PATIENT owner)

```bash
curl "http://localhost:8080/records/1" \
  -H "Authorization: Bearer <jwt>"
```

## Unauthorized / invalid token responses (JSON)

Missing or invalid auth returns a consistent JSON payload:

```json
{
  "timestamp": "2026-03-17T12:00:00",
  "status": 401,
  "error": "Unauthorized",
  "path": "/appointments/me"
}
```

---

# 🗄 Database

The application uses **MySQL hosted on Aiven Cloud**.

Main tables include:

- users
- patients
- doctors
- appointments
- medical_records

Database schema is automatically managed using Hibernate with:

```id="7f3b6k"
ddl-auto: update
```

---

# ⚙️ Configuration

The application uses **environment variables** for secure configuration of database and JWT settings.

Required environment variables:

```id="ytazpx"
DB_HOST
DB_PORT
DB_NAME
DB_USERNAME
DB_PASSWORD
JWT_SECRET
```

Example configuration in `application.yml`:

```id="vrv0gk"
spring.datasource.url=jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_NAME}?ssl-mode=REQUIRED
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

jwt.secret=${JWT_SECRET}
```

These values are configured in the deployment environment (Render).

---

# 💻 Running the Project Locally

1️⃣ Clone the repository

```id="pds7l4"
git clone https://github.com/Tanmayk13/MintHealth.git
```

2️⃣ Navigate into the project

```id="r4y9g2"
cd MintHealth
```

3️⃣ Update database configuration in `application.yml` for your local MySQL instance.

Example:

```id="zoyy7b"
spring.datasource.url=jdbc:mysql://localhost:3306/minthealth
spring.datasource.username=root
spring.datasource.password=root
```

4️⃣ Build the project

```id="peb0d7"
mvn clean install
```

5️⃣ Run the application

```id="y2exwq"
mvn spring-boot:run
```

Application will start at:

```id="p4e66m"
http://localhost:8080
```

Swagger UI:

```id="xbcy55"
http://localhost:8080/swagger-ui/index.html
```

Run using the dev profile (optional, for local defaults):

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

---

# 🐳 Docker

Build Docker image:

```id="xmp7ix"
docker build -t minthealth .
```

Run container:

```id="9lsc7p"
docker run -p 8080:8080 minthealth
```

---

# 🧪 Example Users


| ID  | Email                                                 | Role    |
| --- | ----------------------------------------------------- | ------- |
| 1   | [john@gmail.com](mailto:john@gmail.com)               | PATIENT |
| 2   | [tanmay@gmail.com](mailto:tanmay@gmail.com)           | PATIENT |
| 3   | [sharma@minthealth.com](mailto:sharma@minthealth.com) | DOCTOR  |
| 4   | [mehta@minthealth.com](mailto:mehta@minthealth.com)   | DOCTOR  |
| 5   | [admin@minthealth.com](mailto:admin@minthealth.com)   | ADMIN   |


---

# 🔐 Notes on Roles & Registration

- Public registration (`POST /auth/register`) creates **PATIENT** accounts only (role cannot be chosen by the client).
- **ADMIN-only** endpoint (`POST /admin/users`) is used to create **DOCTOR** and **ADMIN** accounts.
- Patients can fetch their own appointments via `GET /appointments/me`.
- Doctors can fetch their own appointments via `GET /appointments/me/doctor`.
- Unauthorized requests return a consistent JSON error response (`ErrorResponse`) with HTTP 401.

---

# 👨‍💻 Author

**Tanmay Khilari**

Backend Developer
Java | Spring Boot | REST APIs

LinkedIn
[https://linkedin.com/in/itsmetanmayk](https://linkedin.com/in/itsmetanmayk)

---

⭐ If you found this project useful, consider giving the repository a star.