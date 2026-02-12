import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { movieApi } from '../services/api';

interface Movie {
  id: string;
  title: string;
  description: string;
  genre: string;
  duration: number;
  posterUrl?: string; // Optional image
}

const Home: React.FC = () => {
  const [movies, setMovies] = useState<Movie[]>([]);

  useEffect(() => {
    // Mock data for demo
    const mockMovies = [
      { id: '1', title: 'Inception', description: 'Dream within a dream', genre: 'Sci-Fi', duration: 148 },
      { id: '2', title: 'The Dark Knight', description: 'Batman vs Joker', genre: 'Action', duration: 152 },
      { id: '3', title: 'Interstellar', description: 'Space exploration', genre: 'Sci-Fi', duration: 169 },
    ];
    setMovies(mockMovies);

    // movieApi.getAllMovies().then(res => setMovies(res.data)).catch(console.error);
  }, []);

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-3xl font-bold mb-6">Now Showing</h1>
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        {movies.map((movie) => (
          <div key={movie.id} className="bg-white rounded shadow hover:shadow-lg transition">
            <div className="h-48 bg-gray-300 flex items-center justify-center text-gray-500">
              Poster Placeholder
            </div>
            <div className="p-4">
              <h3 className="text-xl font-bold mb-2">{movie.title}</h3>
              <p className="text-gray-600 text-sm mb-2">{movie.genre} | {movie.duration} min</p>
              <p className="text-gray-700 truncate mb-4">{movie.description}</p>
              <Link 
                to={`/booking/${movie.id}`} 
                className="block text-center bg-red-600 text-white py-2 rounded hover:bg-red-700"
              >
                Book Tickets
              </Link>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Home;
