# Cinema Booking System

A microservices-based cinema booking system built with Spring Boot and React.

## Architecture

This project follows a microservices architecture with the following components:

### Backend Services

- **API Gateway** (Port 8080): Routes requests to appropriate microservices
- **Auth Service** (Port 8081): Handles authentication and authorization
- **Catalog Service** (Port 8082): Manages movie catalog
- **Showtime Service** (Port 8083): Manages movie showtimes
- **Booking Service** (Port 8084): Handles ticket bookings
- **Payment Service** (Port 8085): Processes payments

### Common Modules

- **common-web**: Shared web components (ApiResponse, exceptions, validation)
- **common-security**: JWT utilities and security configurations
- **common-utils**: Shared utility classes

### Infrastructure

- **PostgreSQL**: Primary database
- **Redis**: Caching and session management
- **Docker**: Containerization

### Frontend

- React with TypeScript
- Vite for build tooling
- TailwindCSS for styling
- React Router for navigation

## Project Structure

```
cinema-booking/
├── docker-compose.yml
├── backend/
│   ├── pom.xml (parent)
│   ├── common/
│   │   ├── common-web/
│   │   ├── common-security/
│   │   └── common-utils/
│   ├── api-gateway/
│   ├── auth-service/
│   ├── catalog-service/
│   ├── showtime-service/
│   ├── booking-service/
│   └── payment-service/
└── frontend/
    ├── package.json
    └── src/
```

## Prerequisites

- Java 17+
- Maven 3.9+
- Node.js 20+
- Docker & Docker Compose
- PostgreSQL 15 (if running locally)
- Redis 7 (if running locally)

## Getting Started

### Running with Docker Compose (Recommended)

1. Clone the repository
2. Navigate to the project root
3. Start all services:

```bash
docker-compose up -d
```

This will start:
- PostgreSQL on port 5432
- Redis on port 6379
- All microservices on their respective ports
- Frontend on port 3000

### Running Locally

#### Backend Services

1. Start PostgreSQL and Redis:
```bash
docker-compose up -d postgres redis
```

2. Build all modules:
```bash
cd backend
mvn clean install
```

3. Run each service:
```bash
# API Gateway
cd api-gateway
mvn spring-boot:run

# Auth Service
cd auth-service
mvn spring-boot:run

# ... repeat for other services
```

#### Frontend

```bash
cd frontend
npm install
npm run dev
```

The frontend will be available at http://localhost:3000

## API Endpoints

### Auth Service
- POST `/api/auth/register` - Register new user
- POST `/api/auth/login` - Login
- GET `/api/auth/user` - Get current user

### Catalog Service
- GET `/api/movies` - List all movies
- GET `/api/movies/{id}` - Get movie details
- POST `/api/movies` - Create movie (admin)
- PUT `/api/movies/{id}` - Update movie (admin)
- DELETE `/api/movies/{id}` - Delete movie (admin)

### Showtime Service
- GET `/api/showtimes` - List all showtimes
- GET `/api/showtimes/{id}` - Get showtime details
- POST `/api/showtimes` - Create showtime (admin)

### Booking Service
- POST `/api/bookings` - Create booking
- GET `/api/bookings/{id}` - Get booking details
- GET `/api/bookings/user` - Get user's bookings

### Payment Service
- POST `/api/payments` - Process payment
- GET `/api/payments/{id}` - Get payment details

## Configuration

Each service has its own `application.properties` file in `src/main/resources/`.

Key configurations:
- Database connection strings
- JWT secret and expiration
- Redis connection
- Service ports

## Development

### Building Common Modules

When making changes to common modules:

```bash
cd backend
mvn clean install -pl common/common-web,common/common-security,common/common-utils
```

### Running Tests

```bash
# Backend
cd backend
mvn test

# Frontend
cd frontend
npm test
```

## Technologies Used

### Backend
- Spring Boot 3.2.0
- Spring Cloud Gateway
- Spring Data JPA
- Spring Security
- PostgreSQL
- Redis
- JWT (io.jsonwebtoken)
- Lombok
- MapStruct

### Frontend
- React 18
- TypeScript
- Vite
- TailwindCSS
- React Router
- Axios
- React Query
- Redux Toolkit

## License

MIT License

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request
