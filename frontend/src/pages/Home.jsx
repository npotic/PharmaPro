import React, { useState, useEffect, useContext } from 'react';
import axios from '../services/axiosInstance';
import AuthContext from '../services/AuthContext';
import '../assets/css/Home.css';
import { useNavigate } from 'react-router-dom';


function Home() {
    const [lekovi, setLekovi] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const [filterType, setFilterType] = useState('');
    const [filterNamena, setFilterNamena] = useState('');
    const [filterProizvodjac, setFilterProizvodjac] = useState('');
    const [selectedLek, setSelectedLek] = useState(null); // Za selektovani lek
    const [isPopupOpen, setIsPopupOpen] = useState(false); // Za kontrolu popup prozora
    const { isLoggedIn } = useContext(AuthContext);
    const navigate = useNavigate();
    
    

    const handleLogout = () => {
        localStorage.removeItem('token'); // Uklanjanje tokena
        logout(); // Pozivanje logout funkcije iz AuthContext-a
        navigate('/login'); // Navigacija
    };

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

    const filteredLekovi = lekovi.filter((lek) => {
        const matchesSearch = lek.naziv.toLowerCase().startsWith(searchTerm.toLowerCase());
        const matchesType = filterType ? lek.farmaceutski_oblik === filterType : true;
        const matchesNamena = filterNamena ? lek.namena === filterNamena : true;
        const matchesProizvodjac = filterProizvodjac ? lek.proizvodjac === filterProizvodjac : true;
        return matchesSearch && matchesType && matchesNamena && matchesProizvodjac;
    });

     const handleAddToTherapy = async (lekId) => {
        try {
            // Proveri da li je token prisutan u localStorage
                const token = localStorage.getItem('token');

                // Ako token nije prisutan, prikaži grešku
                if (!token) {
                    alert('Token nije pronađen. Prijavite se ponovo.');
                    navigate('/login');
                    return;
                }

            console.log(`Dodavanje leka sa ID ${lekId} u terapiju...`);
            console.log("Lek ID: ", lekId);

            // Pošaljite POST zahtev sa tokenom u Authorization headeru
            await axios.post("http://localhost:8080/api/users/terapija/" + lekId, {}, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                }
            })
            .then(response => {
                console.log(response.data);
                alert('Lek je dodat u terapiju!');
            })
            .catch(error => {
                console.error(error);
            });

        } catch (error) {
            console.error('Greška prilikom dodavanja leka u terapiju:', error);
            if (error.response?.status === 401) {
                alert('Vaša sesija je istekla. Prijavite se ponovo.');
                localStorage.removeItem('token');
                navigate('/login');
            } else {
                alert('Došlo je do greške prilikom dodavanja leka. Pokušajte ponovo.');
            }
        }
    };

    const handleDeleteMedication = async (lekId) => {
        try {
            // Proveri da li je token prisutan u localStorage
            const token = localStorage.getItem('token');

            // Ako token nije prisutan, prikaži grešku
            if (!token) {
                alert('Token nije pronađen. Prijavite se ponovo.');
                navigate('/login');
                return;
            }

            console.log(`Brisanje leka sa ID ${lekId}...`);
            closePopup();
            // Pošaljite DELETE zahtev sa tokenom u Authorization headeru
            await axios.delete(`http://localhost:8080/api/lekovi/${lekId}`, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                }
            })
            .then(response => {
                console.log(response.data);
            })
            .catch(error => {
                console.error(error);
            });
            window.location.reload(false);
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
        <div>
            <nav>
                <div className="nav-left">PharmaPro</div>
                <div className="nav-right">
                    {!isLoggedIn && (
                        <div><a href="/login">Prijava</a>
                            <a href="/register">Registracija</a>
                        </div>
                    )}
                    {isLoggedIn && (
                        <div>
                            <a href="/dashboard">Dashboard</a>
                            <a onClick={handleLogout} href="/">Odjavi se</a>

                        </div>
                    )}
                </div>
            </nav>

            <h1>Pretraga lekova</h1>

            <div className="search-filter-container">
                <input
                    type="text"
                    placeholder="Pretraga po nazivu"
                    value={searchTerm}
                    onChange={(e) => setSearchTerm(e.target.value)}
                />

                <select value={filterType} onChange={(e) => setFilterType(e.target.value)}>
                    <option value="">Svi tipovi</option>
                    <option value="Injekcija">Rastvor za injekciju</option>
                    <option value="Kapsula">Kapsula</option>
                    <option value="Krem">Krem</option>
                    <option value="Lozenga">Lozenga</option>
                    <option value="Mast">Mast</option>
                    <option value="Rastvor">Oralni rastvor</option>
                    <option value="Tableta">Tableta</option>
                </select>

                <select value={filterNamena} onChange={(e) => setFilterNamena(e.target.value)}>
                    <option value="">Sve namene</option>
                    <option value="Glavobolja">Glavobolja</option>
                    <option value="Kašalj">Kašalj</option>
                    <option value="Prehlada">Prehlada</option>
                    <option value="Stomak">Stomačne tegobe</option>
                    <option value="Vitamini">Vitamini i minerali</option>
                    <option value="Ostalo">Ostalo</option>
                </select>

                <select value={filterProizvodjac} onChange={(e) => setFilterProizvodjac(e.target.value)}>
                    <option value="">Proizvođač</option>
                    <option value="AbelaPharm">AbelaPharm</option>
                    <option value="Bayer">Bayer</option>
                    <option value="Belupo">Belupo</option>
                    <option value="Galenika">Galenika</option>
                    <option value="Hemofarm">Hemofarm</option>
                    <option value="Mylan Madjarska">Mylan Mađarska</option>
                    <option value="PharmaSwiss">PharmaSwiss</option>
                    <option value="Strong Nature">Strong Nature</option>
                    <option value="Sopharma">Sopharma</option>
                    
                </select>

                <button onClick={() => { 
                    setSearchTerm(''); 
                    setFilterType(''); 
                    setFilterNamena(''); 
                    setFilterProizvodjac('');
                }}>Resetuj filtere</button>
            </div>

            <ul className="medication-list">
                {filteredLekovi.length > 0 ? (
                    filteredLekovi.map((lek) => (
                        <li key={lek.id} onClick={() => openPopup(lek)} className="medication-item">
                            <span>{lek.naziv}</span>
                        </li>
                    ))
                ) : (
                    <li className="no-medications">Nema lekova koji odgovaraju vašim kriterijumima.</li>
                )}
            </ul>

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
                            <img src={selectedLek.fotografija || '/default-avatar.png'} alt="Fotografija" className="photo" />
                            {isLoggedIn && (
                                <div>
                                    <button><a onClick={() => handleAddToTherapy(selectedLek.id)} > Dodaj lek u terapiju </a></button>
                                    <button><a onClick={() => handleDeleteMedication(selectedLek.id)} > Obrisi lek </a></button>

                                </div>
                            )}  
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}

export default Home;
