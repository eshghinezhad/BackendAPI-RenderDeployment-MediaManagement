# Movie Listing Backend API

A Spring Boot application for managing movie listings with MongoDB integration.

## 🌐 Live API

**Production URL**: https://backendapi-renderdeployment.onrender.com/

## Features

- RESTful API for movie management
- MongoDB database integration
- Spring Security authentication
- User management system

## Technology Stack

- Java 17
- Spring Boot 3.5.3
- Spring Security
- Spring Data MongoDB
- Maven
- MongoDB Atlas

## Local Development

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- MongoDB Atlas account

### Running Locally

1. Clone the repository:
```bash
git clone https://github.com/eshghinezhad/BackendAPI-RenderDeployment-MediaManagement.git
cd BackendAPI-RenderDeployment-MediaManagement
```

2. Build the project:
```bash
mvn clean package
```

3. Run the application:
```bash
java -jar target/MovieListingBackend-0.0.1-SNAPSHOT.jar
```

The application will be available at `http://localhost:8080`

## Deployment to Render.com

### Prerequisites

1. A Render.com account
2. Your code pushed to GitHub

### Deployment Steps

1. **Connect to GitHub:**
   - Go to [Render.com](https://render.com)
   - Sign up/Login with your GitHub account
   - Click "New +" and select "Web Service"

2. **Configure the Service:**
   - **Name:** `movie-listing-backend`
   - **Environment:** `Java`
   - **Build Command:** `mvn clean package -DskipTests`
   - **Start Command:** `java -jar target/MovieListingBackend-0.0.1-SNAPSHOT.jar`

3. **Environment Variables:**
   Add these environment variables in Render dashboard:
   - `SPRING_PROFILES_ACTIVE`: `production`
   - `MONGODB_URI`: Your MongoDB Atlas connection string
   - `ADMIN_USERNAME`: Admin username for basic auth
   - `ADMIN_PASSWORD`: Admin password for basic auth

4. **Deploy:**
   - Click "Create Web Service"
   - Render will automatically build and deploy your application

### Environment Variables

| Variable | Description | Required |
|----------|-------------|----------|
| `SPRING_PROFILES_ACTIVE` | Spring profile to use | Yes |
| `MONGODB_URI` | MongoDB connection string | Yes |
| `ADMIN_USERNAME` | Admin username | No (default: admin) |
| `ADMIN_PASSWORD` | Admin password | No (default: admin123) |
| `PORT` | Server port | No (default: 8080) |

## API Endpoints

### Authentication
- `POST /api/auth/login` - User login
- `POST /api/auth/register` - User registration

### Movies
- `GET /api/movies` - Get all movies
- `POST /api/movies` - Create new movie
- `GET /api/movies/{id}` - Get movie by ID
- `PUT /api/movies/{id}` - Update movie
- `DELETE /api/movies/{id}` - Delete movie

### Users
- `GET /api/users` - Get all users
- `POST /api/users` - Create new user
- `GET /api/users/{id}` - Get user by ID
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

## 🔗 Frontend Integration

### React Frontend Connection

To connect your React frontend to this backend API:

#### 1. **API Base URL**
```javascript
const API_BASE_URL = 'https://backendapi-renderdeployment.onrender.com';
```

#### 2. **Example API Calls**
```javascript
// Get all movies
const getMovies = async () => {
  const response = await fetch(`${API_BASE_URL}/api/movies`, {
    headers: {
      'Authorization': 'Basic ' + btoa('admin:admin123'),
      'Content-Type': 'application/json'
    }
  });
  return response.json();
};

// Create a new movie
const createMovie = async (movieData) => {
  const response = await fetch(`${API_BASE_URL}/api/movies`, {
    method: 'POST',
    headers: {
      'Authorization': 'Basic ' + btoa('admin:admin123'),
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(movieData)
  });
  return response.json();
};

// User login
const login = async (credentials) => {
  const response = await fetch(`${API_BASE_URL}/api/auth/login`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(credentials)
  });
  return response.json();
};
```

#### 3. **CORS Configuration**
The backend is configured to accept requests from any origin. If you need to restrict CORS, update the security configuration.

#### 4. **Authentication**
- **Basic Auth**: Use `admin:admin123` for protected endpoints
- **JWT**: For user authentication, use the `/api/auth/login` endpoint

#### 5. **Environment Variables**
In your React app, you can set the API URL as an environment variable:
```javascript
// .env
REACT_APP_API_URL=https://backendapi-renderdeployment.onrender.com
```

Then use it in your code:
```javascript
const API_BASE_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080';
```

## Security

The application uses Spring Security with basic authentication. All API endpoints require authentication except for registration.

## Database

The application uses MongoDB Atlas as the database. Make sure to:
1. Create a MongoDB Atlas cluster
2. Get your connection string
3. Set the `MONGODB_URI` environment variable in Render

## Troubleshooting

### Common Issues

1. **Build fails on Render:**
   - Check that all dependencies are in `pom.xml`
   - Ensure Java 17 is specified in `pom.xml`

2. **Application won't start:**
   - Verify environment variables are set correctly
   - Check MongoDB connection string
   - Review application logs in Render dashboard

3. **Database connection issues:**
   - Ensure MongoDB Atlas IP whitelist includes Render's IPs
   - Verify connection string format
   - Check database user credentials

## Support

For issues or questions, please create an issue in the GitHub repository. 
