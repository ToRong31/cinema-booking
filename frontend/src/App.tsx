import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import { Toaster } from 'react-hot-toast'

function App() {
  return (
    <Router>
      <div className="min-h-screen bg-gray-100">
        <Toaster position="top-right" />
        
        <nav className="bg-white shadow-lg">
          <div className="max-w-7xl mx-auto px-4">
            <div className="flex justify-between h-16">
              <div className="flex items-center">
                <h1 className="text-xl font-bold text-gray-800">
                  Cinema Booking System
                </h1>
              </div>
            </div>
          </div>
        </nav>

        <main className="max-w-7xl mx-auto py-6 px-4">
          <Routes>
            <Route path="/" element={
              <div className="text-center">
                <h2 className="text-3xl font-bold text-gray-900 mb-4">
                  Welcome to Cinema Booking System
                </h2>
                <p className="text-gray-600">
                  Your microservices application is ready!
                </p>
              </div>
            } />
          </Routes>
        </main>
      </div>
    </Router>
  )
}

export default App
