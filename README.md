# 🔐 API Identity Service

## Overview

This Maven-based Identity Service API provides a robust solution for user authentication, role management, and content access control, specifically designed for podcast platforms.

## 🚀 Features

- **User Authentication**: Secure login and registration
- **Role**: Fine-grained access control
- **Podcast**: Manage podcast and episode content access

## 📋 Prerequisites

- Java Development Kit (JDK) 11+
- Apache Maven 3.6.3+

## 🛠 Project Setup

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/api-identity-service.git
cd api-identity-service
```

### 2. Build the Project

```bash
# Full build with tests
mvn clean install

# Build without running tests
mvn clean install -DskipTests
```

### 3. Run the Application

```bash
mvn spring-boot:run
```


## 🔧 Configuration

### Database Setup
- Supported databases: PostgreSQL

### Environment Variables
- `SPRING_DATASOURCE_URL`: Database connection URL
- `SPRING_DATASOURCE_USERNAME`: Database username
- `SPRING_DATASOURCE_PASSWORD`: Database password
- `SPRING_JWT_SIGNERKEY`: JWT token generation secret


