import React from 'react';
import { Redirect } from 'react-router-dom';
import AppContext from '../context/AppContext';
import {logout} from '../context/Actions'

function NoRoute() {
  
  const {dispatch} = React.useContext(AppContext);
  React.useEffect(() =>{
    dispatch(logout());
});
  return(
    <Redirect to="/login" />
  );
}

export default NoRoute;