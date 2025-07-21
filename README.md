
# 🔐 Code Vault - Spring Boot Backend (Oracle DB)

This is the **backend API** for **Code Vault**, a secure full-stack encryption and decryption application. It handles generating secret codes, encrypting messages, storing them in an Oracle database, and decrypting them with proper credentials.

---

## 📌 Features

- 🔐 Encrypt messages using passwords
- 🔓 Decrypt messages using secret code and password
- 🧩 Random 12-character secret code generator
- 💾 Store messages securely in Oracle DB
- 📡 REST APIs integrated with React frontend

---

## 🛠️ Technologies Used

- **Java 17+**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **Oracle Database**
- **Lombok**
- **Jakarta Persistence API**

---

## 📁 Project Structure

```
backend/
├── controller/
│   └── MessageController.java       # REST API endpoints
├── entity/
│   └── Message.java                 # JPA entity class
├── repo/
│   └── MessageRepo.java            # Repository interface
├── service/
│   └── SecretMessage.java          # Random code generator
└── application.properties          # Oracle DB and server config
```

---

## 🔐 API Endpoints

### 1. Encrypt Message

```
POST /encryptMsg?msg=YourMessage&pwd=YourPassword
```

- **Returns:** Randomly generated secret code
- **Action:** Stores the message, password, and secret code in Oracle DB

### 2. Decrypt Message

```
POST /decryptMsg?secret=SecretCode&pwd=YourPassword
```

- **Returns:** Original message (if valid) or "No Message available"
- **Action:** Validates secret code and password against database

---

## 🔒 Code Generator Logic

```java
public String generateMessage() {
    String str = "!@#$%^&*_-+=<>?";
    Random r = new Random();
    String secret = "";
    for(int i = 1; i <= 12; i++) {
        int num = r.nextInt(str.length());
        secret += str.charAt(num);
    }
    return secret;
}
```

---

## ⚙️ How to Run

### Prerequisites

- Java 17+
- Maven
- Oracle Database (running locally or remotely)
- Oracle JDBC driver (manually added if not already available)

### Steps

```bash
cd backend
./mvnw spring-boot:run
```

Or run from your IDE (Eclipse/IntelliJ) as a Spring Boot app.

---

## 🛠️ application.properties (for Oracle DB)

```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=your_oracle_username
spring.datasource.password=your_oracle_password
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8080

# CORS - if frontend is on Vite (port 5173)
spring.web.cors.allowed-origins=http://localhost:5173
```

> ⚠️ Make sure your Oracle DB is running and the user/schema has permissions to create/update the `Message` table.

---

## 📄 Dependencies

Ensure the Oracle JDBC driver is installed. If Maven doesn’t automatically include it, you may need to:

- Manually download it from Oracle site
- Install into local Maven repo:
```bash
mvn install:install-file \
   -Dfile=ojdbc8.jar \
   -DgroupId=com.oracle \
   -DartifactId=ojdbc8 \
   -Dversion=19.3 \
   -Dpackaging=jar
```

Then in `pom.xml`:
```xml
<dependency>
  <groupId>com.oracle</groupId>
  <artifactId>ojdbc8</artifactId>
  <version>19.3</version>
</dependency>
```

---

## 📄 License

This backend project is open-source and available under the **MIT License**.

---

## 👤 Author

**Yeswanth Kumar Rallapilla**  
GitHub: [yeshu450](https://github.com/yeshu450)

---

**Code Vault** — Secure your secrets, share them safely. 🔐
