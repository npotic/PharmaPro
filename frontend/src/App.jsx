import { React, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import PrivateRoute from './services/PrivateRoute';
import Home from './pages/Home';
import Login from './pages/Login';
import Register from './pages/Register';
import Dashboard from './pages/Dashboard';
import SearchUsers from './pages/SearchUsers';
import Messages from './pages/Messages';
//import Admin from './pages/Admin';

function App() {
    useEffect(() => {
        const handleStorageChange = (e) => {
            if (e.key === 'token') {
                console.log('Promena tokena u localStorage:', e.newValue);
            }
        };

        window.addEventListener('storage', handleStorageChange);

        return () => {
            window.removeEventListener('storage', handleStorageChange);
        };
    }, []);

    return (
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route
                path="/dashboard"
                element={
                    <PrivateRoute>
                        <Dashboard />
                    </PrivateRoute>
                }
            />
            <Route
                path="/search-users" element={<PrivateRoute>
                    <SearchUsers />
                </PrivateRoute>}
            />
            <Route
                path="/messages" element={<PrivateRoute>
                    <Messages />
                </PrivateRoute>}
            />
        </Routes>

    );
}

export default App;
