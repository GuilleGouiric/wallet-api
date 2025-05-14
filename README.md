📘 wallet-api
Backend application for managing virtual wallets and transfers, built with Spring Boot, hexagonal architecture, and support for PostgreSQL and H2 (for tests).

Technologies Used

Java 21
Spring Boot 3.2
Maven
PostgreSQL (Docker)
H2 (para tests)

⚙️ How to Run the Application
  1. Clone the repository : git clone https://github.com/recargapay-dev/GuillermoGouiric.git

  2. Start the PostgreSQL database using Docker : docker compose up -d
   
     To reset the database:: docker compose down -v && docker compose up -d

  3. Run the application locally : cd wallet-api -> mvn spring-boot:run
 

   🧪 How to Run Tests

   mvn clean test
   
The test suite uses an in-memory H2 database with automatic initialization scripts to ensure consistent data for each run.

Preloaded test data includes wallets with IDs 3 and 4, containing historical transfers from May 2024 to May 2025, used for testing historical balance use cases.


📚 Swagger / API Docs
Available at: http://localhost:8080/swagger-ui.html
