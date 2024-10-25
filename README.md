# To-Do List Application

## Overview

This is a To-Do List application built with Spring Boot for the backend and Angular for the frontend (the frontend is hosted in a separate repository). The application allows users to manage tasks, categories, and user information efficiently.

## Technologies Used

- **Backend**: Spring Boot
- **Frontend**: Angular (in a separate repository)
- **Database**: MySQL
- **Testing**: JUnit, Mockito for unit tests, and Postman for API testing
- **CI/CD**: GitHub Actions for continuous integration and deployment

## Features

- User management: Create, read, update, and delete users.
- Task management: Add, edit, delete, and view tasks.
- Category management: Organize tasks by categories.
- API endpoints for all functionalities, allowing for easy integration with the frontend.

## Classes

- **User**: Represents the user entity with attributes like ID, name, email, and password.
- **Task**: Represents a task entity with attributes like ID, title, description, deadline, status, and associated user and category.
- **Categorie**: Represents a category entity to group tasks.

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- Docker (for running MySQL and the application container)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/todolist.git
   cd todolist
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Set up the MySQL database using Docker:
   ```bash
   docker run -d --name mysql --network app_network -e MYSQL_DATABASE=todolist -e MYSQL_ROOT_PASSWORD=root_password -p 3306:3306 mysql
   ```

4. Run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```

### Running Tests

Unit tests can be run using:
```bash
mvn test
```

### API Testing with Postman

Postman can be used to test the API endpoints. Import the provided Postman collection to get started.

## CI/CD Pipeline

This project utilizes GitHub Actions for CI/CD. The pipeline includes:

- **Building the Docker image** for the application.
- **Pushing the image** to Docker Hub.
- **Creating a Docker network** for application containers.
- **Running MySQL and the application** containers in a CI/CD environment.

### Workflow File

The GitHub Actions workflow is defined in `.github/workflows/maven-publish.yml`. Make sure to update the secrets in your repository settings for Docker Hub credentials.

## API Documentation

Refer to the Postman collection for detailed API documentation, including endpoints, request/response formats, and examples.

## Contribution

Feel free to fork the repository and submit pull requests for any improvements or bug fixes.

## Acknowledgments

- Spring Boot Documentation
- Angular Documentation
- Mockito Documentation
- Postman Documentation
- GitHub Actions Documentation
```

You can copy and paste this updated content into your `README.md` file!
