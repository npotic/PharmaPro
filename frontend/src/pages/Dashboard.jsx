import React, { useState, useEffect, useContext } from 'react';
import axios from '../services/axiosInstance';
import AuthContext from '../services/AuthContext';
import { Link, useNavigate } from 'react-router-dom';
import '../assets/css/Dashboard.css'; 

const Dashboard = () => {
    const [user, setUser] = useState({});
    const [lekovi, setLekovi] = useState([]);
    const [addLek, setAddLek] = useState({
        naziv: '', 
        farmaceutski_oblik: '',
        proizvodjac: '',
        terapijske_indikacije: '',
        doziranje_i_nacin_primene: '',
        fotografija: '',
        namena: ''
    })
    const [updateData, setUpdateData] = useState({
        firstName: '',
        lastName: '',
        description: '',
        profilePicture: '',
        password: ''
    });
    const [selectedLek, setSelectedLek] = useState(null);
    const [isPopupOpen, setIsPopupOpen] = useState(false);
    const [terapija, setMedications] = useState([]);
    const { isLoggedIn } = useContext(AuthContext);
    const { logout } = useContext(AuthContext);

    const navigate = useNavigate();

    useEffect(() => {
        const fetchUserData = async () => {
            try {
                const response = await axios.get('/users/me');
                setUser(response.data);

                const terapija = await axios.get('/users/terapija');
                setMedications(terapija.data);
            } catch (error) {
                console.error('Greška prilikom učitavanja podataka:', error);
            }
        };
        fetchUserData();
    }, []);

    useEffect(() => {
        const fetchLekovi = async () => {
            try {
                const response = await axios.get('/lekovi');
                setLekovi(response.data);
            } catch (error) {
                console.error('Greška prilikom preuzimanja lekova:', error);
            }
        };

        fetchLekovi();
    }, []);

    const handleUpdate = async (e) => {
        e.preventDefault();

        try {
            let profilePicturePath = user.profilePicture; // Keep the existing profile picture path

            // If a new profile picture is provided, upload it first
            if (updateData.profilePicture instanceof File) {
                const formData = new FormData();
                formData.append('file', updateData.profilePicture);

                const uploadResponse = await axios.post('/users/upload/profile-picture', formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data',
                    },
                });
                profilePicturePath = uploadResponse.data; // Get the new profile picture path
            }

            // Update user data with the new profile picture path
            const updateResponse = await axios.put(`/users/${user.id}/update`, {
                firstName: updateData.firstName,
                lastName: updateData.lastName,
                description: updateData.description,
                profilePicture: profilePicturePath,
                password: updateData.password,
            });

            alert('Podaci uspešno ažurirani!');
            setUser(updateResponse.data); // Update the user state with the new data
        } catch (error) {
            console.error('Greška prilikom ažuriranja podataka:', error);
        }
    };

    const handleAddMedication = async (e) => {
        e.preventDefault();
        const formData = new FormData();
        formData.append('naziv', addLek.naziv);
        formData.append('farmaceutski_oblik', addLek.farmaceutski_oblik);
        formData.append('proizvodjac', addLek.proizvodjac);
        formData.append('terapijske_indikacije', addLek.terapijske_indikacije);
        formData.append('doziranje_i_nacin_primene', addLek.doziranje_i_nacin_primene);
        if (addLek.fotografija instanceof File) {
            formData.append('file', addLek.fotografija);
        }
        formData.append('namena', addLek.namena);

        try {

            const response = await axios.post("http://localhost:8080/api/lekovi", formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                },
            });
            alert('Lek je uspešno dodat!');
            setLekovi((prevLekovi) => [...prevLekovi, response.data]);
        } catch (error) {
            console.error('Greška prilikom dodavanja leka:', error);
            alert('Došlo je do greške prilikom dodavanja leka.');
        }
    };

    const handleDeleteMedication = async (lekId) => {
        try {
            const token = localStorage.getItem('token');
            if (!token) {
                alert('Token nije pronađen. Prijavite se ponovo.');
                navigate('/login');
                return;
            }

            await axios.delete(`http://localhost:8080/api/users/terapija/${lekId}`, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });

            alert('Lek je obrisan iz terapije!');
            setMedications((prev) => prev.filter((lek) => lek.id !== lekId));
            closePopup();
        } catch (error) {
            console.error('Greška prilikom brisanja leka:', error);
            if (error.response?.status === 401) {
                alert('Vaša sesija je istekla. Prijavite se ponovo.');
                localStorage.removeItem('token');
                navigate('/login');
            } else {
                alert('Došlo je do greške prilikom brisanja leka. Pokušajte ponovo.');
            }
        }
    };

    const openPopup = (lek) => {
        setSelectedLek(lek);
        setIsPopupOpen(true);
    };

    const closePopup = () => {
        setIsPopupOpen(false);
        setSelectedLek(null);
    };

    return (
        <div className="dashboard">
            <nav>
                <div className="nav-left">PharmaPro</div>
                <div className="nav-right">
                    <a href="/">Pocetna</a>
                    <a href="/messages">Poruke</a>
                    <a href="/search-users">Pretraga farmaceuta</a>
                    <a onClick={logout} href="/">Odjavi se</a>
                </div>
            </nav>

            <h1>Dobrodošli, {user.firstName || 'Korisniče'}!</h1>

            <section className="user-info">
                <h2>Podaci korisnika:</h2>
                {user.username ? (
                    <>
                        <img
                            src={user.profilePicture ? `http://localhost:8080${user.profilePicture}` : '/default-avatar.png'}
                            alt="Profilna slika"
                            className="profile-picture"
                        />
                        <p><strong>Korisničko ime:</strong> {user.username}</p>
                        <p><strong>Ime:</strong> {user.firstName || 'Nije postavljeno'}</p>
                        <p><strong>Prezime:</strong> {user.lastName || 'Nije postavljeno'}</p>
                        <p><strong>Datum rodjenja:</strong> {new Date(user.dateOfBirth).toLocaleDateString() || 'Nije postavljeno'}</p>
                        <p><strong>Struka:</strong> {user.zanimanje || 'Nije postavljeno'}</p>
                        <p><strong>Opis:</strong> {user.description || 'Nije postavljeno'}</p>
                        
                    </>
                ) : (<p>Podaci o korisniku nisu dostupni.</p>)}
            </section>
            
            
            <section className="update-form">
                <h2>Ažuriranje podataka:</h2>
                <form onSubmit={handleUpdate}>
                    <input
                        type="text"
                        placeholder="Ime"
                        value={updateData.firstName}
                        onChange={(e) => setUpdateData({ ...updateData, firstName: e.target.value })}
                    />
                    <input
                        type="text"
                        placeholder="Prezime"
                        value={updateData.lastName}
                        onChange={(e) => setUpdateData({ ...updateData, lastName: e.target.value })}
                    />
                    <input
                        type="text"
                        placeholder="Opis"
                        value={updateData.description}
                        onChange={(e) => setUpdateData({ ...updateData, description: e.target.value })}
                    />
                    <input
                        type="file"
                        placeholder="Izaberi fotografiju"
                        accept="image/*"
                        onChange={(e) => setUpdateData({ ...updateData, profilePicture: e.target.files[0] })}
                    /><br />
                    <input
                        type="password"
                        placeholder="Nova lozinka"
                        value={updateData.password}
                        onChange={(e) => setUpdateData({ ...updateData, password: e.target.value })}
                    />
                    <button type="submit">Ažuriraj</button>
                </form>
            </section>

            <section className="medications">
                <h2>Terapija</h2>
                {terapija && terapija.length > 0 ? (
                    <ul className="medication-list-dashboard">
            {terapija.map((lek) => (
                <li key={lek.id} onClick={() => openPopup(lek)} className="medication-item-dashboard">
                    {lek.naziv}
                </li>
            ))}
                    </ul>
                    
    ) : (
        <p>Nemate obeležene lekove u terapiji.</p>
                )}
                {isPopupOpen && selectedLek && (
                <div className="popup-overlay">
                    <div className="popup">
                        <div className="inner-popup">
                            <button className="close-btn" onClick={closePopup}>X</button>
                            <h2 className="details">Detalji leka</h2>
                            <p><strong>Naziv:</strong> </p><h3>{selectedLek.naziv}</h3>
                            <p><strong>Farmaceutski oblik:<br /></strong> {selectedLek.farmaceutski_oblik}</p><br />
                            <p><strong>Proizvođač:<br /></strong> {selectedLek.proizvodjac}</p><br />
                            <p><strong>Terapijske indikacije:<br /></strong> {selectedLek.terapijske_indikacije}</p><br />
                            <p><strong>Doziranje i nacin primene: <br /></strong> {selectedLek.doziranje_i_nacin_primene}</p><br />
                                <img src={selectedLek.fotografija ? `http://localhost:8080${selectedLek.fotografija}` : '/default-avatar.png'} alt="Fotografija" className="slikaLeka" />
                            {isLoggedIn && (
                                <div>
                                    <button><a onClick={() => handleDeleteMedication(selectedLek.id)} > Ukloni lek iz terapije </a></button>
                                </div>
                            )}  
                        </div>
                    </div>
                </div>
            )}
            </section>

            <section className="update-form">
                <h2>Dodavanje novog leka:</h2>
                <form onSubmit={handleAddMedication}>
                    <input
                        type="text"
                        placeholder="Naziv"
                        value={addLek.naziv}
                        onChange={(e) => setAddLek({ ...addLek, naziv: e.target.value })}
                    />
                    <input
                        type="text"
                        placeholder="Farmaceutski oblik"
                        value={addLek.farmaceutski_oblik}
                        onChange={(e) => setAddLek({ ...addLek, farmaceutski_oblik: e.target.value })}
                    />
                    <input
                        type="text"
                        placeholder="Proizvodjac"
                        value={addLek.proizvodjac}
                        onChange={(e) => setAddLek({ ...addLek, proizvodjac: e.target.value })}
                    />
                    <input
                        type="text"
                        placeholder="Terapijske indikacije"
                        maxLength={255}
                        value={addLek.terapijske_indikacije}
                        onChange={(e) => setAddLek({ ...addLek, terapijske_indikacije: e.target.value })}
                    />
                    <input
                        type="text"
                        placeholder="Doziranje i nacin primene"
                        maxLength={255}
                        value={addLek.doziranje_i_nacin_primene}
                        onChange={(e) => setAddLek({ ...addLek, doziranje_i_nacin_primene: e.target.value })}
                    />
                    <input
                        type={"file"}
                        placeholder="Fotografija"
                        accept="image/*"
                        onChange={(e) => setAddLek({ ...addLek, fotografija: e.target.files[0] })}
                    />
                    <input
                        type="text"
                        placeholder="Namena"
                        value={addLek.namena}
                        onChange={(e) => setAddLek({ ...addLek, namena: e.target.value })}
                    />
                    <button type="submit">Dodaj novi lek</button>
                </form>
            </section>
        </div>
    );
};

export default Dashboard;
