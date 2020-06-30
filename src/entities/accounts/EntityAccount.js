import React, {Component, useContext, useEffect, useState} from "react";
import AppContext from "../../context/AppContext";
import {Api, fetchAccountsError, fetchAccountsStarted, fetchAccountsSuccess} from "../../context/Actions";
import AccountsTable from "../../tables/AccountsTable/AccountsTable";
import AccountForm from "./AccountForm";
import MyPage from "../../links/Mypage";
import GroupId from "../../links/GroupId";

function EntityAccount() {
    const {state, dispatch} = useContext(AppContext);

    const {isLogged, userID, accounts, groupDenomination, myPage, myGroups} = state;
    const {isLoading, error, accountsData} = accounts;
    const [isAdmin, setIsAdmin] = useState(false);
    const [urlForm, setUrlForm] = useState('');

    useEffect(() => {
        if (isLogged == true) {
            getAccounts();
        }
    }, [])

    const getAccounts = async () => {

        const user = userID.toLowerCase();

        if (myPage && !myGroups) {

            const url = '/persons/' + user + '/accounts';

            dispatch(fetchAccountsStarted());

            try {
                const res = await Api.get(url);
                const {data} = res;
                dispatch(fetchAccountsSuccess(data.accounts));
                setIsAdmin(true);
            } catch (err) {
                dispatch(fetchAccountsError(err.message));
            }
        }

        if (!myPage && myGroups) {

            const url = '/persons/' + user + '/groups/' + groupDenomination + '/accounts';

            dispatch(fetchAccountsStarted());

            try {
                const res = await Api.get(url);
                const {data} = res;
                dispatch(fetchAccountsSuccess(data.accounts));
                if (res.data._links != null) {
                    setIsAdmin(true);
                    setUrlForm(res.data._links.addAccount.href.toString());
                } else {
                    setIsAdmin(false);
                }
            } catch (err) {
                dispatch(fetchAccountsError(err.message));
            }

        }

    }

    const headers = {
        header1: 'Account',
        header2: 'Description'
    };

    let data;
    let admin;
    let navBar;

    if (!isLogged) {
        return (<h1>Not logged</h1>)
    }

    if (isLoading) {
        return (<h1>Loading...</h1>);
    }

    if (error.length > 0) {
        return (<h1>{error}...</h1>);
    } else {
        data = <AccountsTable data={accountsData} headers={headers}/>
    }

    if (isAdmin) {
        admin = <AccountForm url={urlForm}/>
    }

    if (myPage && !myGroups) {
        navBar = <MyPage/>;
    }

    if (!myPage && myGroups) {
        navBar = <GroupId/>
    }

    return (
        <div>
            {navBar}
            <br/>
            {data}
            {admin}
        </div>
    )

}

export default EntityAccount;

