import React, {useContext, useState} from "react";
import AppContext from "../../context/AppContext";
import {Api, updateGroups} from "../../context/Actions";
import Button from "react-bootstrap/Button";

const GroupForm = () => {

    const {state, dispatch} = useContext(AppContext);

    const {userID, groups} = state;

    const [denomination, setDenomination] = useState('');
    const [description, setDescription] = useState('');

    // Date of creation == today

    let date = new Date();
    var dd = date.getDate();
    var mm = date.getMonth() + 1;
    var yyyy = date.getFullYear();
    if (dd < 10) {
        dd = '0' + dd;
    }
    if (mm < 10) {
        mm = '0' + mm;
    }
    date = yyyy + '-' + mm + '-' + dd;
    const [dateOfCreation, setdateOfCreation] = useState(date);

    const handleClick = (event) => {

        const username = userID.toLowerCase();

        console.log(username);
        event.preventDefault();
        console.log("handleChange: " + event.target);
        console.log(denomination);

        Api.post('/groups/', {
            email: username,
            denomination: denomination,
            description: description
        }).then(response => {
            dispatch(updateGroups([...groups.groupsData, {denomination, description, dateOfCreation}]));
        })
            .catch(err => alert(err.response.data.message));
        setDenomination('');
        setDescription('');
    }

    return (
        <div>
            <form className="Forms" onSubmit={handleClick}>
                <input type="text" value={denomination} autoFocus
                       placeholder={'Please enter denomination'}
                       required onChange={(event) => setDenomination(event.target.value)}/>
                <input type="text" value={description}
                       placeholder={'Please enter description'}
                       required onChange={(event) => setDescription(event.target.value)}/>
                <Button variant="outline-primary" type="submit">Create Group</Button>
            </form>
        </div>
    );

}

export default GroupForm;