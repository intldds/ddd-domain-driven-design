import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import AppContext from '../context/AppContext';

/*
 { component: XPTO, ...rest } 
   component: XPTO : Find the component property defined on props (Note: lowercase component) and assign it to a new location in state we call XPTO
   ...rest: Then, take all remaining properties defined on the props object and collect them inside an argument called rest.
*/

function PrivateRoute({ component: XPTO, ...rest }) {
  const {state} = React.useContext(AppContext);
  const {isLogged} = state;
  return(
    <Route  {...rest} render = { (props) =>(
      isLogged 
      ? (<XPTO {...props} />) 
      : (<Redirect to="/login" />) )}
    />
  );
}

export default PrivateRoute;