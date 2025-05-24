# Note App

A full-stack note-taking application built with Spring Boot and React.

## Features

- User authentication and authorization
- Create, read, update, and delete notes
- Organize notes with categories
- Responsive design
- RESTful API

## Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- JWT Authentication

### Frontend
- React
- Bootstrap
- JavaScript
- HTML/CSS

### DevOps
- Docker
- Docker Compose

## Prerequisites

- Java 17 or higher
- Node.js 16 or higher
- Docker and Docker Compose
- PostgreSQL

## Setup Instructions

1. Clone the repository
2. Start the database:
   ```bash
   docker-compose up -d postgres
   ```
3. Start the backend:
   ```bash
   ./gradlew bootRun
   ```
4. Start the frontend:
   ```bash
   cd frontend
   npm install
   npm start
   ```

## API Documentation

The API documentation is available at `/swagger-ui.html` when running the application.

## License

MIT 