# 🛡️ Sentinel‑Secure

A Spring Boot security project that implements robust authentication and token management using JSON Web Tokens (JWT).  
It demonstrates how to build a **dual‑token system** (access token + refresh token) for secure, stateless authentication.

---

## ✨ **Features**

✅ Stateless authentication with JWT  
✅ Generates both:
- **Access Token**: short‑lived, used for accessing protected resources
- **Refresh Token**: longer‑lived, used to get new access tokens without re‑authentication

✅ Spring Security integration  
✅ Clear service layer for token generation and validation  
✅ Logging with SLF4J

---

## ⚙️ **Tech Stack**

- Java 21+
- Spring Boot
- Spring Security
- jjwt (Java JWT library)
- Maven

---

## 🚀 **Getting Started**

1. **Clone the repo**
```bash
git clone https://github.com/yourusername/Sentinel-Secure.git
cd Sentinel-Secure
Make your Own application.properties

## ⚙️ **Configuration**

This project uses an `application.properties` file to manage database configuration, JWT and security settings.

Example:
```properties
# JWT secret key (must be a secure random string, stored in base64)
app.jwt.secret=(use secret key using `openssl rand -base64 32`)

# Access token expiration time in milliseconds (e.g., 3600000 = 1 hour)
app.jwt.expiration=3600000

# Refresh token expiration time in milliseconds (e.g., 604800000 = 7 days)
app.jwt.refresh-expiration=604800000

Get Started...
