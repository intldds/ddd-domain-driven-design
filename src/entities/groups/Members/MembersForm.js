import React, {useContext, useState} from "react";
import AppContext from "../../../context/AppContext";
import {Api, updateMembers} from "../../../context/Actions";
import Button from "react-bootstrap/Button";

const MembersFrom = () => {

    const {state, dispatch} = useContext(AppContext);

    const {groupDenomination, members} = state;
    const {membersData} = members;

    const [memberID, setMemberID] = useState('');

    // Clearance

    const [clearance, setClearance] = useState("member");


    const handleClick = (event) => {

        event.preventDefault();

        Api.post('/groups/' + groupDenomination + '/members', {
            email: memberID
        }).then(response => {
            dispatch(updateMembers([...membersData, {memberID, clearance}]));
        })
            .catch(err => alert(err.response.data.message));
        setMemberID('');
    }

    return (
        <div>
            <form className="Forms" onSubmit={handleClick}>
                <input type="text" value={memberID} autoFocus
                       placeholder={'Please enter e-mail'}
                       required onChange={(event) => setMemberID(event.target.value)}/>
                <Button variant="outline-primary" type="submit">Add Member</Button>
            </form>
        </div>
    );
}

export default MembersFrom