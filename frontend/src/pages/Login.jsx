import React, { useState, useContext } from 'react'; 
import axios from '../services/axiosInstance';
import { useNavigate } from 'react-router-dom';
import AuthContext from '../services/AuthContext';
import '../assets/css/Login.css';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const { login } = useContext(AuthContext);
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            console.log('Pokušaj prijave...');
            const response = await axios.post('/users/login', {
                username,
                password,
            });
            const token = response.data;

            if (token) {
                console.log('Token primljen:', token);
                login(token); // Postavlja token u AuthContext i localStorage
                navigate('/dashboard'); // Preusmerava na dashboard
            } else {
                console.error('Prijava nije uspela: token nije vraćen.');
            }
        } catch (error) {
            console.error('Greška prilikom prijave:', error);
        }
    };

    return (
        <form onSubmit={handleLogin} className="search-filter-container-login">
            <h1>Prijava</h1>
            <input
                type="text"
                placeholder="Korisničko ime"
                required
                value={username}
                onChange={(e) => setUsername(e.target.value)}
            /><br />
            <input
                type="password"
                placeholder="Lozinka"
                required
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            /><br />
            <button type="submit">Prijavi se</button>
        </form>
    );
};

export default Login;
