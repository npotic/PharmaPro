import React, { useState, useContext } from 'react';
import axios from '../services/axiosInstance';
import AuthContext from '../services/AuthContext';
import '../assets/css/Home.css'; 

const SearchUsers = () => {
    const [keyword, setKeyword] = useState('');
    const [users, setUsers] = useState([]);
    const { logout } = useContext(AuthContext);
    const { currentUser } = useContext(AuthContext);

    const handleSearch = async () => {
        try {
            const response = await axios.get(`/users/search?keyword=${keyword}`);
            setUsers(response.data);
        } catch (error) {
            console.error('Greška prilikom pretrage korisnika:', error);
        }
    };

    const sendFriendRequest = async (receiverId) => {
        if (!currentUser) {
            console.log("Korisnik nije još uvek učitan...");
            return;
        }

        if (!currentUser.id) {
            console.error("Korisnik nije prijavljen ili ID nije dostupan.");
            return;
        }
        const token = localStorage.getItem("token");

        try {
            const response = await axios.post("http://localhost:8080/api/friends/request", receiverId, {
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`
                }
            });
            console.log("Zahtev za prijateljstvo:", response.data);
            alert('Zahtev za prijateljstvo poslat!');
        } catch (error) {
            console.error('Greška prilikom slanja zahteva:', error);
        }
    };

    return (
        <div className='search-users-page'>
            <nav className='search-users-nav'>
                <div className="nav-left">PharmaPro</div>
                <div className="nav-right">
                    <a href="/">Pocetna</a>
                    <a href="/messages">Poruke</a>
                    <a href="/dashboard">Dashboard</a>
                    <a onClick={logout} href="/">Odjavi se</a>
                </div>
            </nav>
            <h1 className='search-users-title'>Pretraga korisnika</h1>
            <div className='search-users-input'>
                <input
                    type="text"
                    placeholder="Pretraga korisnika"
                    value={keyword}
                    onChange={(e) => setKeyword(e.target.value)}
                />
                <button onClick={handleSearch}>Pretraži</button>
            </div>
            <ul className='search-users-list'>
                {Array.isArray(users) && users.map((user) => (
                    <li key={user.id} className='search-users-item'>
                        <span>{user.username}  </span> 
                        <span>{user.firstName}  </span>
                        <span> {user.lastName}  </span>
                        <span> {user.dateOfBirth}</span>
                        <button onClick={() => sendFriendRequest(user.id)} className='add-friend'>Dodaj prijatelja</button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default SearchUsers;