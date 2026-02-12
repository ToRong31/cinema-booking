import React, { useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { bookingApi } from '../services/api';

const ROWS = 5;
const COLS = 8;

const Booking: React.FC = () => {
  const { movieId } = useParams();
  const navigate = useNavigate();
  const [selectedSeats, setSelectedSeats] = useState<string[]>([]);
  
  // Tạo danh sách ghế (A1, A2... E8)
  const seats = Array.from({ length: ROWS }, (_, r) => 
    Array.from({ length: COLS }, (_, c) => 
      `${String.fromCharCode(65 + r)}${c + 1}`
    )
  );

  const toggleSeat = (seat: string) => {
    if (selectedSeats.includes(seat)) {
      setSelectedSeats(selectedSeats.filter(s => s !== seat));
    } else {
      setSelectedSeats([...selectedSeats, seat]);
    }
  };

  const handleBooking = async () => {
    if (selectedSeats.length === 0) return alert('Please select at least one seat');
    
    try {
      const bookingData = {
        movieId,
        showtimeId: 'mock-showtime-id', // Trong thực tế user phải chọn suất chiếu trước
        seats: selectedSeats,
        amount: selectedSeats.length * 100 // Giả định 100k/vé
      };
      
      console.log('Booking:', bookingData);
      // await bookingApi.createBooking(bookingData);
      
      alert('Booking Successful!');
      navigate('/');
    } catch (error) {
      alert('Booking Failed');
    }
  };

  return (
    <div className="container mx-auto p-4 max-w-2xl">
      <h1 className="text-2xl font-bold mb-6 text-center">Select Seats</h1>
      
      <div className="mb-8 w-full h-12 bg-gray-400 text-white flex items-center justify-center rounded">
        SCREEN
      </div>

      <div className="grid gap-4 mb-8">
        {seats.map((row, rowIndex) => (
          <div key={rowIndex} className="flex justify-center gap-2">
            {row.map(seat => (
              <button
                key={seat}
                onClick={() => toggleSeat(seat)}
                className={`w-10 h-10 rounded text-xs font-bold transition
                  ${selectedSeats.includes(seat) 
                    ? 'bg-green-500 text-white' 
                    : 'bg-gray-200 hover:bg-gray-300'}`}
              >
                {seat}
              </button>
            ))}
          </div>
        ))}
      </div>

      <div className="border-t pt-4">
        <div className="flex justify-between items-center mb-4">
          <span className="font-bold text-lg">Total: {selectedSeats.length} tickets</span>
          <span className="font-bold text-xl text-red-600">${selectedSeats.length * 10}</span>
        </div>
        <button
          onClick={handleBooking}
          className="w-full bg-blue-600 text-white py-3 rounded font-bold hover:bg-blue-700 disabled:bg-gray-400"
          disabled={selectedSeats.length === 0}
        >
          Confirm Booking
        </button>
      </div>
    </div>
  );
};

export default Booking;
