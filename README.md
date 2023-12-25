# Personal Blog App - Spring Boot Backend

Welcome to the backend repository of my Personal Blog App, a full-stack blog application developed using Spring Boot, Vue.js, and PostgreSQL. This backend API provides the core functionality for handling blog posts and user interactions through a secure and efficient REST API.

## Features

- **CRUD Operations:** Perform Create, Read, Update, and Delete operations on blog posts.
- **JWT Authentication:** Secure user interactions with JSON Web Token (JWT) authentication using Spring Security.
- **Azure Deployment:** Utilizes Azure services for deployment, ensuring a reliable and seamless user experience.
- **CI/CD Pipeline:** GitHub Actions automates the Continuous Integration and Continuous Deployment (CI/CD) pipeline for efficient code updates and deployments.

## Tech Stack

- **Spring Boot:** Java-based framework for building robust and scalable backend applications.
- **Vue.js:** Frontend framework for creating dynamic and interactive user interfaces.
- **PostgreSQL:** Relational database for storing blog posts and user information.
- **Azure Services:**
  - **Azure Static Web Apps:** Hosts the frontend of the application.
  - **Azure App Service:** Delivers the backend API.
  - **Azure PostgreSQL:** Manages the PostgreSQL database.
  - **Azure Blob Storage:** Stores media files associated with blog posts.

## Setup Instructions

### Prerequisites

- Java Development Kit (JDK) installed
- Maven build tool installed
- Docker and Docker Compose installed

### Steps

1. **Clone the Repository:**

   `
   git clone https://github.com/your-username/personal-blog-backend.git
   cd personal-blog-backend
   `

2. **Build the Project with Maven:**
    `bash
      mvn clean install
    `

 3. **Run Docker Compose for PostgreSQL:**
    `
    docker-compose up -d
    `
 4. **Configure Database Connection:**
    Update the application.properties file with your PostgreSQL connection details:
    `
    spring:
  datasource:
    username: <username>
    password: <password>
    url: jdbc:postgresql://localhost:54321/<db-name>
    `
5. **Run the Spring Boot Application:**
   `
   mvn spring-boot:run
   `
The API will be accessible at http://localhost:8080.


Checkout the frontend repository [here](https://github.com/hazzaRR/PersonalBlogVue/).
