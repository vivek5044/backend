#  User Management Backend (Spring Boot + H2)

A Spring Boot app to load user data from an external API into an in-memory H2 database and provide search/retrieval APIs.

---

## How to Run

1. Clone the repo
2. Ensure Java 17+ and Maven are installed
3. Start the application:
```bash
mvn clean install
mvn spring-boot:run

App runs at: http://localhost:8080


Step 1: Load Users into Database  
Must be called once after app starts to populate the in-memory DB.
curl -X POST http://localhost:8080/users/load

Step 2: Search & Retrieve Users
a. Search by firstName / lastName / ssn (free-text)
curl "http://localhost:8080/users/search?q=john"

b. Get User by ID
curl http://localhost:8080/users/1

c. Get User by Email
curl http://localhost:8080/users/email/emily.johnson%40x.dummyjson.com


H2 Console
Make sure to complete Step 1 to populate the database with da
To view data in browser:

Visit: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:test

Username: sa

Password: (leave blank)
SELECT * FROM users;
