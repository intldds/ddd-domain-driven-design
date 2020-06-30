import React, {useContext, useEffect} from "react";
import AppContext from "../../../context/AppContext";
import {Api, fetchMembersError, fetchMembersStarted, fetchMembersSuccess} from "../../../context/Actions";
import MembersTable from "../../../tables/MembersTable/MembersTable";
import MembersFrom from "./MembersForm";
import GroupId from "../../../links/GroupId";

function EntityMembers() {

    const {state, dispatch} = useContext(AppContext);

    const {isLogged, groupDenomination, members} = state;

    const {isLoading, error, membersData} = members;


    useEffect(() => {
        if (isLogged == true) {
            getAllMembers();

        }
    }, [])

    const getAllMembers = async () => {

        const url = '/groups/' + groupDenomination + '/allMembers';

        dispatch(fetchMembersStarted());

        try {
            const res = await Api.get(url);
            const {data} = res;
            dispatch(fetchMembersSuccess(data.allMembers));
        } catch (err) {
            dispatch(fetchMembersError(err.message));
        }
    };


    const headers = {
        header1: 'Members',
        header2: 'Clearance',
    };

    let data;


    if (!isLogged) {
        return (<h1>Not logged</h1>)
    }

    if (isLoading) {
        return (<h1>Loading...</h1>);
    }

    if (error.length > 0) {
        return (<h1>{error}...</h1>);
    } else {
        data = <MembersTable data={membersData} headers={headers}/>
    }

    return (
        <div>
            <GroupId/>
            <br/>
            {data}
            <MembersFrom/>
        </div>
    )

}

export default EntityMembers;