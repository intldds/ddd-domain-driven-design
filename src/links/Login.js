import React, {useState} from 'react';
import {useHistory} from 'react-router-dom';
import AppContext from '../context/AppContext';
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

import {Api, login} from '../context/Actions';

const Login = () => {
    const {dispatch} = React.useContext(AppContext);

    let history = useHistory();

    const [userID, setUserID] = useState('');

    const handleClick = async (e) => {
        e.preventDefault();

        const url = '/persons/' + userID;

        const array = userID.toLowerCase().split("@");
        const username = array[0];

        try {
            const res = await Api.get(url);
            if (res.status === 200) {
                dispatch(login(userID, username));
                history.push("/mypage");
            }
        } catch (err) {
            alert(err.response.data.message);
        }
    }

    return (
        <div className="outerDiv">

            <div className="innerDiv" >

                <Form name="form">
                    <h3 className="loginTitle">Web App with React</h3>
                    <h4 className="secTitle">Personal Finance Management</h4>
                    <Form.Group controlId={userID}>
                        <Form.Label>Username </Form.Label>
                        <Form.Control type="text" placeholder="Enter Username" autoFocus value={userID} required onChange={(e) => setUserID(e.target.value)}/>
                    </Form.Group>

                    <Form.Group controlId="password">
                        <Form.Label>Password </Form.Label>
                        <Form.Control type="password" placeholder="Enter Password"/>
                    </Form.Group>
                    <br></br>
                    <Button variant="outline-primary" size="sm" block type="submit" onClick={handleClick}>Login</Button>
                </Form>

            </div>

        </div>
    );
};

export default Login;
