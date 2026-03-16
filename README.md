# MintHealth 🏥

MintHealth is a **Spring Boot backend REST API** for managing healthcare workflows such as patients, doctors, appointments, and medical records.

The system provides **secure authentication using JWT** and **role-based access control** for different users (ADMIN, DOCTOR, PATIENT).

This project demonstrates backend development using **Java, Spring Boot, Spring Security, MySQL, Docker, and Swagger**, with deployment on Render and database hosted on Aiven.

---

# 🚀 Tech Stack

* Java 21
* Spring Boot
* Spring Security
* JWT Authentication
* Spring Data JPA / Hibernate
* MySQL (Aiven Cloud Database)
* Maven
* Docker
* Swagger / OpenAPI

---

# ✨ Features

* Secure user authentication using **JWT**
* Role-based authorization

  * ADMIN
  * DOCTOR
  * PATIENT
* Patient profile management
* Doctor profile management
* Appointment scheduling system
* Medical records management
* Secure password encryption using **BCrypt**
* RESTful API design
* API documentation using **Swagger**
* Cloud database integration

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

* Endpoint documentation
* Request / response schemas
* Interactive API testing

---

# 🗄 Database

The application uses **MySQL hosted on Aiven Cloud**.

Main tables include:

* users
* patients
* doctors
* appointments
* medical_records

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

| ID | Email                                                 | Role    |
| -- | ----------------------------------------------------- | ------- |
| 1  | [john@gmail.com](mailto:john@gmail.com)               | PATIENT |
| 2  | [tanmay@gmail.com](mailto:tanmay@gmail.com)           | PATIENT |
| 3  | [sharma@minthealth.com](mailto:sharma@minthealth.com) | DOCTOR  |
| 4  | [mehta@minthealth.com](mailto:mehta@minthealth.com)   | DOCTOR  |
| 5  | [admin@minthealth.com](mailto:admin@minthealth.com)   | ADMIN   |

---

# 👨‍💻 Author

**Tanmay Khilari**

Backend Developer
Java | Spring Boot | REST APIs

LinkedIn
https://linkedin.com/in/itsmetanmayk

---

⭐ If you found this project useful, consider giving the repository a star.
