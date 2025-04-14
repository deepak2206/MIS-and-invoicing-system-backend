🔐 MIS and Invoicing System - Backend

This is the backend of the **MIS and Invoicing System** built using **Spring Boot**, **Spring Security**, and **JWT (JSON Web Tokens)** for secure user authentication.

---

🧰 Tech Stack

- Java 17+
- Spring Boot
- Spring Security
- JWT Authentication
- Maven
- MySQL
- JPA (Hibernate)
- Lombok

---

📦 Features

- JWT-based login and secure authentication
- Role-based access (User/Admin)
- CORS configuration for frontend integration
- Custom `UserDetailsService` with `UserPrincipal`
- Secure endpoints using Spring Security
- RESTful APIs

---
🔐 Authentication Flow (JWT)

1. User sends credentials (email & password) to `/login`.
2. Backend authenticates the user using `UserDetailsService`.
3. If valid, a JWT token is generated and returned.
4. For secured routes, the client must pass the token in the `Authorization` header.

