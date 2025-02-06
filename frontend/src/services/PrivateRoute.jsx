import React, { useContext } from 'react';
import { Navigate } from 'react-router-dom';
import AuthContext from './AuthContext';

const PrivateRoute = ({ children }) => {
    const { isLoggedIn } = useContext(AuthContext);

    console.log('Stanje prijave u PrivateRoute:', isLoggedIn);

    return isLoggedIn ? children : <Navigate to="/login" replace />;
};



export default PrivateRoute;
