import React, {useContext, useEffect, useState} from "react";
import axios from 'axios';
import AppContext from "../../context/AppContext";
import {
    fetch_ledgers_initiated,
    fetch_ledgers_success,
    fetch_ledgers_error,
    Api,
    fetchAccountsStarted, fetchAccountsSuccess, fetchAccountsError
} from "../../context/Actions";
import LedgerTable from "../../tables/LedgersTable/LedgerTable";
import LedgerForm from "./LedgerForm";
import MyPage from "../../links/Mypage";
import TransactionAccountSelect from "./TransactionsAccountSelect";
import TransactionGroupAccountSelect from "./TransactionsGroupAccountSelect";
import GroupId from "../../links/GroupId";

function EntityLedgers() {

    const {state, dispatch} = useContext(AppContext);

    const {isLogged, transactions, userID, groupDenomination, myPage, myGroups} = state;
    console.log({userID})
    const {isLoading, error} = transactions;

    useEffect(() => {
        if (isLogged == true) {
            getLedgers();
        }
    }, [])

    //Get Transactions in the Ledger

    const getLedgers = async () => {

        const user = userID.toLowerCase();

        if (myPage && !myGroups) {

            const url = '/persons/' + user + '/ledgers/records';

            dispatch(fetch_ledgers_initiated());

            try {
                const res = await Api.get(url);
                const {data} = await res;
                dispatch(fetch_ledgers_success(data));
            } catch (err) {
                dispatch(fetch_ledgers_error(err.message));
            }
        }

        if (!myPage && myGroups) {

            const url = '/persons/' + user + '/groups/' + groupDenomination + '/ledgers/records';

            dispatch(fetch_ledgers_initiated());

            try {
                const res = await Api.get(url);
                const {data} = await res;
                dispatch(fetch_ledgers_success(data));
            } catch (err) {
                dispatch(fetch_ledgers_error(err.message));
            }

        }

    }

    // Conditional rendering

    const headers = {
        header1: 'Category',
        header2: 'Type',
        header3: 'Description',
        header4: 'Amount \u20AC',
        header5: 'Date',
        header6: 'DebitAccount',
        header7: 'CreditAccount',

    };
    let navBar;
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
        data = <LedgerTable headers={headers}/>
    }


    if (myPage && !myGroups) {
        navBar = <MyPage/>;
    }

    if (!myPage && myGroups) {
        navBar = <GroupId/>
    }

    if (myPage && !myGroups) {
        return (
            <div>
                {navBar}
                <br/>
                <TransactionAccountSelect/>
                <br/>
                {data}
                <LedgerForm/>
            </div>
        )
    }

    if (!myPage && myGroups || !myPage && !myGroups) {
        return (
            <div>
                {navBar}
                <br/>
                <TransactionGroupAccountSelect/>
                <br/>
                {data}
                <LedgerForm/>
            </div>
        )

    }



}

export default EntityLedgers;