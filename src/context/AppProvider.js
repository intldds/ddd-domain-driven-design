import React, {useReducer} from 'react';
import PropTypes from "prop-types";
import {Provider} from './AppContext';
import reducer from './Reducer';

const initialState = {

    // User
    username: "",
    userID: "",
    groupDenomination: "",

    // Login
    isLogged: false,

    // My Page
    myPage: false,

    // My groups
    myGroups: false,

    groups: {
        isLoading: false,
        error: "",
        groupsData: [],
    },

    // Members

    members: {
        isLoading: false,
        error: "",
        membersData: [],
    },

    // Ledger- Transactions

    transactions: {
        isLoading: false,
        error: "",
    },

    // Transactions
    transactionsData: [],


    // Categories
    categories: {
        isLoading: false,
        error: "",
        categoriesData: [],
    },

    // Accounts
    accounts: {
        isLoading: false,
        error: "",
        accountsData: [],
    },

    // Search Transaction
    searchTransaction: false,

};

const headers = {
    denomination: "Denomination",
    name: "Name",
    username: "User Name",
    email: "Email",
};

const headersGroup = {
    denomination: "Denomination",
    description: "Description",
    dateOfCreation: "Date of Creation",
    _links: "_links"
}

const AppProvider = (props) => {
    // Dispatch is the function that will allow to change the state
    const [state, dispatch] = useReducer(reducer, initialState);
    return (
        //We are making available the State and dispatch for all the children components
        <Provider value={{
            state,
            dispatch
        }}>
            {props.children}
        </Provider>
    );
};

AppProvider.propTypes = {
    children: PropTypes.node,
};


export default AppProvider;
