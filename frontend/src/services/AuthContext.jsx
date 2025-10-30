import { createContext, useState, useEffect } from "react";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [token, setToken] = useState(localStorage.getItem("token"));
    const [currentUser, setCurrentUser] = useState(null); 

    useEffect(() => {
        if (token) {
            try {
                const decodedToken = JSON.parse(atob(token.split(".")[1]));
                
                if (decodedToken.exp * 1000 > Date.now()) { 
                    setCurrentUser({ id: decodedToken.userId, roles: decodedToken.roles });
                    console.log("Trenutni korisnik (iz AuthContexta):", { id: decodedToken.userId, roles: decodedToken.roles });
                } else {
                    console.log("Token je istekao. Odjavljivanje...");
                    localStorage.removeItem("token");
                    setToken(null);
                    setCurrentUser(null);
                }
            } catch (error) {
                console.error("Greška prilikom dekodiranja tokena:", error);
                localStorage.removeItem("token");
                setToken(null);
                setCurrentUser(null);
            }
        } else {
            setCurrentUser(null);
        }
    }, [token]);

    const login = (newToken) => {
        console.log("Prijava sa tokenom:", newToken);
        localStorage.setItem("token", newToken);
        setToken(newToken);
        try {
            const decodedToken = JSON.parse(atob(newToken.split(".")[1]));
            setCurrentUser({ id: decodedToken.userId, roles: decodedToken.roles });
        } catch (error) {
            console.error("Greška prilikom dekodiranja novog tokena:", error);
            setCurrentUser(null);
        }
    };

    const logout = () => {
        console.log("Odjava korisnika...");
        localStorage.removeItem("token");
        setToken(null);
        setCurrentUser(null);
    };

    return (
        <AuthContext.Provider value={{ isLoggedIn: !!token, token, login, logout, currentUser }}>
            {children}
        </AuthContext.Provider>
    );
};

export default AuthContext;