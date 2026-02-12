import axios from 'axios';

// Giả định API Gateway chạy ở port 8080
const API_URL = 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Interceptor để thêm Token vào mỗi request nếu có
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export const authApi = {
  login: (credentials: any) => api.post('/auth/login', credentials),
  register: (data: any) => api.post('/auth/register', data),
};

export const movieApi = {
  getAllMovies: () => api.get('/movies'),
  getMovieById: (id: string) => api.get(`/movies/${id}`),
};

export const showtimeApi = {
  getShowtimesByMovie: (movieId: string) => api.get(`/showtimes?movieId=${movieId}`),
};

export const bookingApi = {
  createBooking: (bookingData: any) => api.post('/bookings', bookingData),
  getMyBookings: () => api.get('/bookings/my-bookings'),
};

export default api;
