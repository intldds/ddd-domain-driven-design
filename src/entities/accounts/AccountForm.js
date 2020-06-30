import React, {useContext, useState} from "react";
import axios from 'axios';
import AppContext from "../../context/AppContext";
import {Api, updateAccounts} from "../../context/Actions";
import Button from "react-bootstrap/Button";

const AccountForm = (props) => {

    const {state, dispatch} = useContext(AppContext);

    const {userID, accounts, myPage, myGroups} = state;
    const [denomination, setDenomination] = useState('');
    const [description, setDescription] = useState('');

    const handleClick = async (event) => {
        const user = userID.toLowerCase();


        event.preventDefault();

        if (myPage && !myGroups) {

            try {
                await Api.post('/persons/' + user + '/accounts/',{
                    denomination: denomination,
                    description: description
                });

                // Only dispatches if it has a valid response
                dispatch(updateAccounts([...accounts.accountsData, {denomination, description}]));

            } catch (err) {
                alert(err.response.data.message);
            }
        }

        if (!myPage && myGroups) {

            try {
                await axios.post(props.url,{accountDescription: description, accountDenomination: denomination});

                // Only dispatches if it has a valid response
                dispatch(updateAccounts([...accounts.accountsData, {denomination, description}]));

            } catch (err) {
                alert(err.response.data.message);
            }
        }

        setDenomination('');
        setDescription('');
    };

    return (
        <div>
        <form className="Forms"  onSubmit={handleClick}>

            <input type="text" value={denomination} autoFocus
                   placeholder={'Please enter denomination'}
                   required onChange={(event) => setDenomination(event.target.value)} />
            <input type="text" value={description}
                   placeholder={'Please enter description'}
                   required onChange={(event) => setDescription(event.target.value)} />
            <Button variant="outline-primary" type="submit">Add Account</Button>
        </form>
        </div>
    );
};

export  default  AccountForm;