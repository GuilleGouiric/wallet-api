📘 wallet-api
Aplicación backend para gestión de wallets virtuales y transferencias, construida con Spring Boot, arquitectura hexagonal y soporte para PostgreSQL y H2 (tests).

Tecnologías utilizadas:
Java 21

Spring Boot 3.2

Maven

PostgreSQL (Docker)

H2 (para tests)



⚙️ Cómo correr la app
1. Clonar el repo : git clone https://github.com/tu-usuario/wallet-api.git

  

2. Levantar base PostgreSQL con Docker : docker compose up -d
   
   Si querés resetear los datos: docker compose down -v && docker compose up -d


3. Ejecutar la app localmente : mvn spring-boot:run
 
   
   🧪 Cómo correr los tests

   mvn clean test
   Usa H2 en memoria y scripts de inicialización automáticos para cargar datos consistentes en cada test.


📚 Swagger / API Docs
Disponible en: http://localhost:8080/swagger-ui.html
