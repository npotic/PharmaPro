import React, { useState, useContext, useEffect } from 'react';
import axios from '../services/axiosInstance';
import AuthContext from '../services/AuthContext';
import '../assets/css/Messages.css'; 

const Messages = () => {
    const [friends, setFriends] = useState([]);
    const [selectedFriend, setSelectedFriend] = useState(null);
    const [messages, setMessages] = useState([]);
    const [newMessage, setNewMessage] = useState('');
    const { logout } = useContext(AuthContext);
    const { currentUser } = useContext(AuthContext);

    useEffect(() => {
        if (!currentUser) {
            console.log("Korisnik nije još uvek učitan...");
            return; // Sačekaj da se currentUser postavi
        }

        if (!currentUser.id) {
            console.error("Korisnik nije prijavljen ili ID nije dostupan.");
            return;
        }

        const fetchFriends = async () => {
            try {
                const response = await axios.get(`/friends?userId=${currentUser.id}`);
                console.log('Prijatelji:', response.data);
                setFriends(response.data);
            } catch (error) {
                console.error('Greška prilikom preuzimanja prijatelja:', error);
            }
        };

        fetchFriends();
    }, [currentUser]);

    // Funkcija za selektovanje prijatelja i preuzimanje poruka
    const handleSelectFriend = async (friendId) => {
        setSelectedFriend(friendId);
        const token = localStorage.getItem("token");

        try {
            const response = await axios.get("http://localhost:8080/api/messages", {
                headers: { Authorization: `Bearer ${token}` },
                params: { user1Id: currentUser.id, user2Id: friendId }
            });

            console.log("Dobijene poruke:", response.data); // Provera odgovora
            setMessages(response.data || []); // Postavi prazan niz ako je undefined
        } catch (error) {
            console.error("Greška prilikom preuzimanja poruka:", error);
            setMessages([]); // Ako dođe do greške, postavi prazan niz umesto undefined
        }
    };


    // Slanje nove poruke
    const handleSendMessage = async () => {
        if (!newMessage.trim()) return;

        const token = localStorage.getItem("token");

        const newMsg = {
            senderId: currentUser.id,
            receiverId: selectedFriend,
            content: newMessage,
        };

        try {
            const response = await axios.post("http://localhost:8080/api/messages", newMsg, {
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`
                }
            });

            console.log("Poruka poslata:", response.data);
            setMessages(prev => [...prev, newMsg]); // Dodaj poruku u state
            setNewMessage('');
        } catch (error) {
            console.error("Greška prilikom slanja poruke:", error);
        }
    };

    return (
        <div className="messages-container">
            <nav>
                <div className="nav-left">PharmaPro</div>
                <div className="nav-right">
                    <a href="/">Pocetna</a>
                    <a href="/messages">Poruke</a>
                    <a href="/dashboard">Dashboard</a>
                    <a onClick={logout} href="/">Odjavi se</a>
                </div>
            </nav>
            <div className="friends-list">
                <h2>Prijatelji</h2>
                <ul className="friends-list-ul">
                    {friends.map((friend) => (
                        <li
                            key={friend.id}
                            className={selectedFriend === friend.id ? 'active' : ''}
                            onClick={() => handleSelectFriend(friend.id)}
                        >
                            {friend.firstName} {friend.lastName}
                        </li>
                    ))}
                </ul>
            </div>

            <div className="chat-box">
                {selectedFriend ? (
                    <>
                        <div className="messages-header">
                            <h3>{friends.find((f) => f.id === selectedFriend)?.firstName}</h3>
                        </div>
                        <div className="messages-list">
                            {messages.map((msg, index) => (
                                <div
                                    key={index}
                                    className={`message ${msg.senderId === currentUser.id ? 'sent' : 'received'}`}
                                >
                                    <p className="content">{msg.content}</p>
                                    <p className="timestamp">{new Date(msg.timestamp).toLocaleTimeString()}</p>
                                </div>
                            ))}
                        </div>
                        <div className="message-input">
                            <input
                                type="text"
                                placeholder="Unesite poruku..."
                                value={newMessage}
                                onChange={(e) => setNewMessage(e.target.value)}
                            />
                            <button onClick={handleSendMessage}>Pošalji</button>
                        </div>
                    </>
                ) : (
                    <div className="no-friend-selected">
                        <p>Izaberite prijatelja za prikaz poruka.</p>
                    </div>
                )}
            </div>
        </div>
    );
};

export default Messages;

