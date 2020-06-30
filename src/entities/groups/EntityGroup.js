import React, {useContext, useEffect} from "react";
import axios from 'axios';
import AppContext from "../../context/AppContext";
import {fetch_group_started, fetch_group_success, fetch_group_error} from "../../context/Actions";
import GroupsTable from "../../tables/GroupsTable/GroupsTable";
import GroupForm from "./GroupForm";
import MyPage from "../../links/Mypage";
import User from "../../tables/User/User";

function EntityGroup() {

    const {state, dispatch} = useContext(AppContext);

    const {isLogged, groups, userID, username} = state;

    const {isLoading, error, groupsData} = groups;

    useEffect(() => {
        if (isLogged == true) {
            getGroups();
        }
    }, [])


    // Get User Groups

    const getGroups = async () => {

        const user = userID.toLowerCase();

        const url = `http://localhost:8080/persons/${user}/groups`;

        dispatch(fetch_group_started());

        try {
            const res = await axios.get(url);
            const {data} = await res;
            dispatch(fetch_group_success(data.groups));
        } catch (err) {
            dispatch(fetch_group_error(err.message));
        }
    }


    // Conditional rendering

    const headers = {
        header1: 'Denomination',
        header2: 'Description',
        header3: 'Date of Creation'
    };
    let data;

    if (!isLogged) {
        return (<h1>Not logged</h1>);

    }

    if (isLoading == true) {
        return (<h1>Loading...</h1>);
    }

    if (error.length > 0) {
        return (<h1>{error}...</h1>);
    } else {
        data = <GroupsTable data={groupsData} headers={headers}/>
    }

    return (

        <div>
            <MyPage />
            <br/>
            {data}
            <GroupForm/>
        </div>
    )

}

export default EntityGroup;