# FirApplication
the Masai FIR application aims to simplify the process of filing FIRs and tracking their progress while providing a user-friendly interface and robust security feature



# Tech Stack
- Java
- Spring Framework
- Spring Boot
- Spring Data JPA
- MySQL
- Swagger UI
- Lambok
- Maven


# Features
- Users, police, and police stations can register themselves
- Users can log into the system
- Users can file an FIR against one or more other users
- Users can withdraw their FIR within 24 hours of filing if it has not been closed
- Each police station has a list of FIRs filed there, along with an officer in charge and several constables
- Each police officer has a list of FIRs filed by them and a list of FIRs (cases) closed by them
- Each FIR has the details of:
- The user who filed the FIR
- The list of users against whom the FIR is filed
- The police officer who filed the FIR
- The police station where the FIR was filed
- Whether the case is open or closed


# Modules

- Login Module
- User Module
- Police Module
- Police Station Module
- Fir Module







# Installation & Run
 - Before running the API server, you should update the database config inside the application.properties file.
- Update the port number, username and password as per your local database configuration.

```
    server.port=8080

    spring.datasource.url=jdbc:mysql://localhost:3306/FirDB;
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=root
```

# API Root Endpoint
```
https://localhost:8080/
```
```
http://localhost:8080/swagger-ui/
```
