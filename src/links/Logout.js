import React from 'react';
import { useHistory } from 'react-router-dom';  
import AppContext from '../context/AppContext';
import {logout} from '../context/Actions';
import Button from "react-bootstrap/Button";

function Logout () {
    const {dispatch} = React.useContext(AppContext);
    let history = useHistory();
    const handleClick = () => {
        window.location.href = '/';
        dispatch(logout());
    }

  return (
      <Button variant="outline-primary" type="submit" onClick={handleClick}>Logout</Button>
  );

}

export default Logout;