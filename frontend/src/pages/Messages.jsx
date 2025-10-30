import { useState, useContext, useEffect } from 'react';
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
            console.log("Ucitavanje korisnika.");
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

    const handleSelectFriend = async (friendId) => {
        setSelectedFriend(friendId);
        const token = localStorage.getItem("token");

        try {
            const response = await axios.get("http://localhost:8080/api/messages", {
                headers: { Authorization: `Bearer ${token}` },
                params: { user1Id: currentUser.id, user2Id: friendId }
            });

            setMessages(response.data || []); 
        } catch (error) {
            console.error("Greška prilikom preuzimanja poruka:", error);
            setMessages([]);
        }
    };

    const handleSendMessage = async () => {
        if (!newMessage.trim()) return;

        const token = localStorage.getItem("token");

        const newMsg = {
            senderId: currentUser.id,
            receiverId: selectedFriend,
            content: newMessage,
            timestamp: new Date().toISOString()
        };

        try {
            const response = await axios.post("http://localhost:8080/api/messages", newMsg, {
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`
                }
            });

            setMessages(prev => [...prev, {
                ...newMsg, sender: { id: currentUser.id },
                receiver: { id: selectedFriend }
            }]);
            
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
                                    className={`message ${msg.sender?.id === currentUser.id ? 'sent' : 'recived'}`}
                                >
                                    <span className="content">{msg.content}</span>
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