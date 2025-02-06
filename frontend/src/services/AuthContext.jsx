import React, { createContext, useState, useEffect } from "react";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [token, setToken] = useState(localStorage.getItem("token"));
    const [currentUser, setCurrentUser] = useState(null);

    useEffect(() => {
        if (token) {
            try {
                const decodedToken = JSON.parse(atob(token.split(".")[1]));
                setCurrentUser({ id: decodedToken.userId, roles: decodedToken.roles }); // Koristimo userId
            } catch (error) {
                console.error("Greška prilikom dekodiranja tokena:", error);
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
