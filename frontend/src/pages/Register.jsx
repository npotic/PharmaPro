import React, { useState } from 'react';
import axios from '../services/axiosInstance';
import { useNavigate } from 'react-router-dom';
import '../assets/css/Login.css'; 

const Register = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [dateOfBirth, setDateOfBirth] = useState('');
    const [zanimanje, setZanimanje] = useState('');
    const navigate = useNavigate();


    const handleRegister = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('/users/register', {
                username,
                password,
                dateOfBirth,
                zanimanje
            });
            navigate('/');
        } catch (error) {
            console.error('Greška prilikom registracije:', error);
        }
    };

    return (
        <form onSubmit={handleRegister} className="search-filter-container-login">
            <h1>Registracija</h1>
            <input
                type="text"
                placeholder="Korisničko ime"
                required
                value={username}
                onChange={(e) => setUsername(e.target.value)}
            />
            <input
                type="password"
                placeholder="Lozinka"
                required
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />
            <input
                type="date"
                placeholder="Datum rodjenja"
                required
                value={dateOfBirth}
                onChange={(e) => setDateOfBirth(e.target.value)}

            />
            <input
                type="text"
                placeholder="Sifra delatnosti / strucna sprema"
                required
                value={zanimanje}
                onChange={(e) => setZanimanje(e.target.value)}
            />
            <button type="submit">Registruj se</button>
        </form>
    );
};

export default Register;
