import axios from "axios";

export const Api = axios.create({
    baseURL: 'http://localhost:8080'
})

export const LOGIN = 'LOGIN';
export const LOGOUT = 'LOGOUT';
export const FETCH_GROUPS_STARTED = 'FETCH_GROUPS_STARTED';
export const FETCH_GROUPS_SUCCESS = 'FETCH_GROUPS_SUCCESS';
export const FETCH_GROUPS_ERROR = 'FETCH_GROUPS_ERROR';
export const FETCH_LEDGERS_INITIATED = 'FETCH_lEDGERS_INITIATED';
export const FETCH_LEDGERS_SUCCESS = 'FETCH_lEDGERS_SUCCESS';
export const FETCH_LEDGERS_ERROR = 'FETCH_LEDGERS_ERROR';
export const UPDATE_LEDGERS = 'UPDATE_LEDGERS';
export const FETCH_CATEGORIES_ERROR = 'FETCH_CATEGORIES_ERROR';
export const FETCH_CATEGORIES_STARTED = 'FETCH_CATEGORIES_STARTED';
export const FETCH_CATEGORIES_SUCCESS = 'FETCH_CATEGORIES_SUCCESS';
export const UPDATE_CATEGORIES = 'UPDATE_CATEGORIES';
export const MY_PAGE_HANDLE_CLICKS = 'MY_PAGE_HANDLE_CLICKS';
export const MY_GROUPS_HANDLE_CLICKS = 'MY_GROUPS_HANDLE_CLICKS';
export const MY_HOMEPAGE_HANDLE_CLICKS = 'MY_HOMEPAGE_HANDLE_CLICKS';
export const HIDE_INFO = 'HIDE_INFO';
export const FETCH_ACCOUNTS_STARTED = 'FETCH_ACCOUNTS_STARTED';
export const FETCH_ACCOUNTS_SUCCESS = 'FETCH_ACCOUNTS_SUCCESS';
export const FETCH_ACCOUNTS_ERROR = 'FETCH_ACCOUNTS_ERROR';
export const UPDATE_ACCOUNTS = 'UPDATE_ACCOUNTS';
export const UPDATE_GROUPS = 'UPDATE_GROUPS';
export const SET_GROUP_DENOMINATION = 'SET_GROUP_DENOMINATION';
export const FETCH_MEMBERS_STARTED = 'FETCH_MEMBERS_STARTED';
export const FETCH_MEMBERS_SUCCESS = 'FETCH_MEMBERS_SUCCESS';
export const FETCH_MEMBERS_ERROR = 'FETCH_MEMBERS_ERROR';
export const UPDATE_MEMBERS = 'UPDATE_MEMBERS';
export const SEARCH_TRANSACTION = 'SEARCH_TRANSACTION';

// This functions will return objects with some information/value within it


// lOGIN / LOGOUT


export function login(userID, username) {

    return {
        type: LOGIN,
        payload: {
            userID: userID,
            username: username
        }

    }
}

export function logout() {

    return {
        type: LOGOUT,
    }
}

// GROUP

export function fetch_group_started() {

    return {
        type: FETCH_GROUPS_STARTED,
    }
}


export function fetch_group_success(data) {

    return {
        type: FETCH_GROUPS_SUCCESS,
        payload: {
            data: data,
        }
    }
}

export function fetch_group_error(error) {

    return {
        type: FETCH_GROUPS_ERROR,
        payload: {
            data: error,
        }
    }

}

export function updateGroups(data) {
    return {
        type: UPDATE_GROUPS,
        payload: {
            data: data,
        }
    }
}

export function setGroupDenomination(groupDenomination) {
    return {
        type: SET_GROUP_DENOMINATION,
        payload: {
            groupDenomination: groupDenomination,
        }
    }
}


// MEMBERS


export function fetchMembersStarted() {
    return {
        type: FETCH_MEMBERS_STARTED,
    }
}

export function fetchMembersSuccess(data) {
    return {
        type: FETCH_MEMBERS_SUCCESS,
        payload: {
            data: data,
        }
    }
}

export function fetchMembersError(error) {
    return {
        type: FETCH_MEMBERS_ERROR,
    }
}

export function updateMembers(data) {

    return {
        type: UPDATE_MEMBERS,
        payload: {
            data: data,
        }
    }
}


// LEDGER


export function fetch_ledgers_initiated() {
    return {
        type: FETCH_LEDGERS_INITIATED,
    }
}


export function fetch_ledgers_success(data) {
    return {
        type: FETCH_LEDGERS_SUCCESS,
        payload: {
            data: data.transactions,
        }
    }
}

export function fetch_ledgers_error(error) {
    return {
        type: FETCH_LEDGERS_ERROR,
        payload: {
            data: error,
        }
    }

}

export function updateLedgers(data) {

    return {
        type: UPDATE_LEDGERS,
        payload: {
            data: data,

        }
    }
}

// ACCOUNTS

export function fetchAccountsStarted() {

    return {
        type: FETCH_ACCOUNTS_STARTED,
    }
}


// Accounts


export function fetchAccountsSuccess(data) {

    return {
        type: FETCH_ACCOUNTS_SUCCESS,
        payload: {
            data: data,

        }
    }
}

export function fetchAccountsError(error) {

    return {
        type: FETCH_ACCOUNTS_ERROR,
        payload: {
            data: error,
        }
    }
}

export function updateAccounts(data) {

    return {
        type: UPDATE_ACCOUNTS,
        payload: {
            data: data,

        }
    }
}

// CATEGORIES

export function fetchCategoriesStarted() {
    return {
        type: FETCH_CATEGORIES_STARTED,
    }
}

export function fetchCategoriesSuccess(data) {
    return {
        type: FETCH_CATEGORIES_SUCCESS,
        payload: {
            data: data,
        }
    }
}

export function fetchCategoriesError(error) {
    return {
        type: FETCH_CATEGORIES_ERROR,
        payload: {
            data: error,
        }
    }
}

export function updateCategories(data) {

    return {
        type: UPDATE_CATEGORIES,
        payload: {
            data: data,

        }
    }
}

// HANDLE CLICKS

export function myPageHandleOnClicks() {
    return {
        type: MY_PAGE_HANDLE_CLICKS,

    }
}

export function myGroupsHandleOnClicks() {
    return {
        type: MY_GROUPS_HANDLE_CLICKS,

    }
}

export function myHomePageHandleOnClicks() {
    return {
        type: MY_HOMEPAGE_HANDLE_CLICKS,

    }
}


// SEARCH FORM - TRANSACTIONS

export function searchTransactionForm(boolean) {

    return {
        type: SEARCH_TRANSACTION,
        payload: {
            searchTransaction: boolean
        }

    }
}
