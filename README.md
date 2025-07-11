# IEAA - Certificate Examination Center Web Application
# Overview
- The application is designed to streamline the operations of a certification testing center, enabling:
Candidates, Customer to register for exams (e.g., TOEIC, IELTS, IT certifications).
Staff to manage exam schedules, process registrations, issue invoices, and enter results.
Administrators to manage user accounts and certification catalogs.

## :ledger: Index
- [About](#about)
- [Key-Features](#key-features)
- [Usage](#zap-usage)
  - [ProjectStructure](#project_structure)
  - [Installation](#electric_plug-installation)

## Key Features
- **Register for individual Customer**
- **Invoice Management**
- **Renew Form Management**
- **Shedule Created**
- **Search and update status of result exam**

## Usage
### Project Structure
```
certificate-examination-center/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/center/certification/
│   │   │       ├── config/            # Configurations (e.g., Security, Cloudinary)
│   │   │       ├── controller/        # Spring MVC controllers
│   │   │       ├── entity/             # Entity classes (e.g., Registration, Schedule) for mapping with table in db
│   │   │       ├── repository/        # Spring Data JPA repositories
│   │   │       ├── service/           # Business logic
|   |   |       ├── dto/              # Data Transfer Object for API responses and requests
|   |   |       ├── dao/              # Data Access Object for custome database queries
|   |   |       ├── exception/       # Custom exception class for error handling.
|   |   |       ├── factory/         # get Role instance for client define.
|   |   |       ├── mapper/           # Mapper classes for converting beetween entities and DTOs
|   |   |       ├── security/         # Security configuration, jwtUtils, authentication and authorization logic.
|   |   |       ├── seeders/         # Initial role data for client.
│   │   │       └── CertificatExaminationCenterApplication.java   # Main Spring Boot application
│   │   ├── resources/
│   │   │   ├── db/migration/         # Flyway migration scripts
│   │   │   ├── static/
│   │   │   │   ├── css/              # CSS files
│   │   │   │   ├── js/               # jQuery scripts
│   │   │   │   └── images/           # Static assets
│   │   │   ├── templates/            # Thymeleaf HTML templates
│   │   │   └── application.properties # Configuration (e.g., database, Cloudinary settings)
├── pom.xml                           # Maven dependencies
└── README.md
```
### Installation
There are several ways to install and run this application on your local machine.
1. **Prerequisites**:
   - JDK 17 (STL) or later
   - Java SE 17 or later
   - Maven 3 (for dependency management)

2. **Setup**:
```bash
# Clone repository
git clone https://github.com/thPhonG-oss/certificate-examination-center
```
- Create your application-local.properties to config database and others. You can see an application-local.properties.example in scr/resources/application-local.properties.example to config your info.
```shell
Example:
# thymeleaf configuration
spring.thymeleaf.enabled=true
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.mode=HTML
spring.thymeleaf.encoding=UTF-8
spring.thymeleaf.cache=false

# Database configuration
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=your_DB_name;encrypt=true;trustServerCertificate=true
spring.datasource.username=<your_username>
spring.datasource.password=<your_password>
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.SQLServerDialect

#flyway configuration
spring.flyway.enabled=true
spring.flyway.baseline-on-migrate=true
spring.flyway.locations=classpath:db/migration

# Security configuration
app.jwtSecret=<your_jwt_Secret>
app.jwtExpirationMs=8640000

# Cloudinary configuration
cloudinary.cloud.name=<your_cloud_name>
cloudinary.api.key=<your_cloud_key>
cloudinary.api.secret=<your_cloud_secretKey>

```
3. **Run Application**:
   - You can run CertificateExamMinationApplication.java file in Intellij or run below shell interminal:
   ```shell
   cd certificate-examination-center
   mvn spring-boot:run
   ```
   - if you meet some error about connect to MS SQL Server, you need to create User Authentication with username and password (ex: username: as, password: yourpasswrod), and then you can see a solution to fix error TCP port in this link: https://springframework.guru/configuring-spring-boot-for-microsoft-sql-server/
4. **Demo some key features**:
  - This app is main only for user that is employee of center or company. Admin will create account and active it for each employee.
  - Before testing some key feature of this application, you need run an api sign-up:
  - You can use Postman or any API Development and Testing Tool to run this API:
  ```bass
  http://localhost:8081/api/auth/sign-up
  ```
  - with body like this or custom yourself
    ```bash
    {
    "username": "user",
    "email": "user@gmail.com",
    "password": "123456a@",
    "phone_number": "0325961720",
    "full_name": "Nguyễn Nhân Viên",
    "gender": "M",
    "dob" : "1999-10-22",
    "address": "TP.HCM"
    }
    ```
