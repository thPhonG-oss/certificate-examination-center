IEAA - Certificate Examination Center Web Application

1. Overview
The application is designed to streamline the operations of a certification testing center, enabling:
Candidates, Customer to register for exams (e.g., TOEIC, IELTS, IT certifications).
Staff to manage exam schedules, process registrations, issue invoices, and enter results.
Administrators to manage user accounts and certification catalogs.

The system uses Spring Boot for server-side rendering to generate dynamic HTML pages, styled with CSS and enhanced with jQuery for client-side interactivit.

2. Technology Stack:
   - Maven for manage dependency and build tool.
   - Backend:
      + Spring boot: framework for RESTful and serverside rendering.
      + Spring security: provides authentication and authorization using jwt.
      + Spring data jpa: simplifies database operations with repository interfaces.
      + Flyway: for manage database schema migration and insert default data.
  - Frontend: Thymleaf, CSS, Bootstrap 5, Jquery for client side interactivity.
  - Database: MS SQL Server for storing data.
  - File Storing: Cloudinary for storing candidate's avatar.
3. Project structure:
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
|   |   |       |__ dto/              # Data Transfer Object for API responses and requests
|   |   |       |__ dao/              # Data Access Object for custome database queries
|   |   |       |__ exception/       # Custom exception class for error handling.
|   |   |       |__ factory/         # get Role instance for client define.
|   |   |       |__ mapper/           # Mapper classes for converting beetween entities and DTOs
|   |   |       |__ security/         # Security configuration, jwtUtils, authentication and authorization logic.
|   |   |       |__ seeders/         # Initial role data for client.
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
