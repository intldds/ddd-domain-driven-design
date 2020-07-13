import {
    LOGIN,
    LOGOUT,
    FETCH_GROUPS_STARTED,
    FETCH_GROUPS_SUCCESS,
    FETCH_GROUPS_ERROR,
    FETCH_LEDGERS_INITIATED,
    FETCH_LEDGERS_SUCCESS,
    FETCH_LEDGERS_ERROR,
    UPDATE_LEDGERS,
    FETCH_ACCOUNTS_STARTED,
    FETCH_ACCOUNTS_SUCCESS,
    FETCH_ACCOUNTS_ERROR,
    UPDATE_ACCOUNTS,
    MY_PAGE_HANDLE_CLICKS,
    MY_GROUPS_HANDLE_CLICKS,
    MY_HOMEPAGE_HANDLE_CLICKS,
    HIDE_INFO,
    UPDATE_GROUPS,
    FETCH_CATEGORIES_STARTED,
    FETCH_CATEGORIES_SUCCESS,
    FETCH_CATEGORIES_ERROR,
    UPDATE_CATEGORIES,
    SET_GROUP_DENOMINATION,
    FETCH_MEMBERS_STARTED,
    FETCH_MEMBERS_SUCCESS,
    FETCH_MEMBERS_ERROR,
    UPDATE_MEMBERS,
    SEARCH_TRANSACTION

} from './Actions'

// With the information received from the dispatch, the reducer will analyze the
// type of the information received

function reducer(state, action) {

    const dataMembers = {...state.members};

    const dataGroups = {...state.groups};

    const dataAccounts = {...state.accountsData};

    const dataCategories = {...state.categoriesData};

    switch (action.type) {



// LOGIN


        // If the type of the action is Login, then the reducer will
        // update the state with the attribute isLogged is true

        case LOGIN:

            //This function will copy the actual state and change the variable isLogged with true
            return {
                ...state,
                isLogged: true,
                username: action.payload.username,
                userID: action.payload.userID,
            };

        // If the type of the action is Logout, then the reducer will update the state with the attribute isLogged is false
        case LOGOUT:
            return {
                ...state,
                isLogged: false,
            };



// GROUPS


        // Fetch_Groups_Started: Is started the empty array of dataGroups

        case FETCH_GROUPS_STARTED:
            dataGroups.isLoading = true;
            dataGroups.error = "";
            dataGroups.groupsData = [];
            return {
                ...state,
                groups: dataGroups,

            };

        // Fetch_Groups_Success: dataGroups array is filled with data

        case FETCH_GROUPS_SUCCESS:
            dataGroups.isLoading = false;
            dataGroups.error = "";
            dataGroups.groupsData = action.payload.data.sort(function (a, b) {
                if (a.denomination.toLowerCase() < b.denomination.toLowerCase()) {
                    return -1;
                }
                if (a.denomination.toLowerCase() > b.denomination.toLowerCase()) {
                    return 1;
                }
                return 0;
            });

            return {
                ...state,
                groups: dataGroups,

            };

        // Fetch_Groups_Error: It is not possible to present the groups

        case FETCH_GROUPS_ERROR:
            dataGroups.isLoading = false;
            dataGroups.error = action.payload.data;
            dataGroups.groupsData = [];
            return {
                ...state,
                groups: dataGroups,

            };

        // UPDATE_GROUPS: To update new groups added

        case UPDATE_GROUPS:

            console.log("UpdateGroups:" + JSON.stringify(action.payload.data));
            console.log({state});
            dataGroups.isLoading = false;
            dataGroups.error = "";
            dataGroups.groupsData = action.payload.data.sort(function (a, b) {
                if (a.denomination.toLowerCase() < b.denomination.toLowerCase()) {
                    return -1;
                }
                if (a.denomination.toLowerCase() > b.denomination.toLowerCase()) {
                    return 1;
                }
                return 0;
            });

            return {
                //Copy the actual state and update it according to the available groups
                ...state,
                groups: dataGroups,
            };

        case SET_GROUP_DENOMINATION:

            return {
                ...state,
                groupDenomination: action.payload.groupDenomination,
            };


// MEMBERS

        case FETCH_MEMBERS_STARTED:
            dataMembers.isLoading = true;
            dataMembers.error = "";
            dataMembers.membersData = [];
            return {
                ...state,
                members: dataMembers,
            };

        case FETCH_MEMBERS_SUCCESS:
            dataMembers.isLoading = false;
            dataMembers.error = "";
            dataMembers.membersData = action.payload.data.sort(function (a, b) {
                if (a.memberID.toLowerCase() < b.memberID.toLowerCase()) {
                    return -1;
                }
                if (a.memberID.toLowerCase() > b.memberID.toLowerCase()) {
                    return 1;
                }
                return 0;
            });
            return {
                ...state,
                members: dataMembers,
            };

        case FETCH_MEMBERS_ERROR:
            dataMembers.isLoading = false;
            dataMembers.error = action.payload.data;
            dataMembers.membersData = [];
            return {
                ...state,
                members: dataMembers,

            };

        case UPDATE_MEMBERS:
            dataMembers.isLoading = false;
            dataMembers.error = "";
            dataMembers.membersData = action.payload.data.sort(function (a, b) {
                if (a.memberID.toLowerCase() < b.memberID.toLowerCase()) {
                    return -1;
                }
                if (a.memberID.toLowerCase() > b.memberID.toLowerCase()) {
                    return 1;
                }
                return 0;
            });

            return {
                //Copy the actual state and update it according to the available members
                ...state,
                members: dataMembers,
            };


// LEDGER


        // Fetch_Ledgers_Initiated: Is started the empty array of transactions

        case FETCH_LEDGERS_INITIATED:
            const dataTransactionsInitiated = {...state.transactions};
            dataTransactionsInitiated.isLoading = true;
            dataTransactionsInitiated.error = "";
            dataTransactionsInitiated.transactionsData = [];
            return {
                ...state,
                transactions: dataTransactionsInitiated,

            };

        // Fetch_Ledgers_Success: dataLedgers array is filled with data

        case FETCH_LEDGERS_SUCCESS:
            const dataTransactionsSuccess = {...state.transactions};
            dataTransactionsSuccess.isLoading = false;
            dataTransactionsSuccess.error = "";
            // dataTransactionsSuccess.transactionsData = action.payload.data;
            return {
                ...state,
                transactions: dataTransactionsSuccess,
                transactionsData: action.payload.data,
            };

        // Fetch_Ledgers_Error: It is not possible to present the transactions

        case FETCH_LEDGERS_ERROR:
            const dataTransactionsError = {...state.transactions};
            dataTransactionsError.isLoading = false;
            dataTransactionsError.error = action.payload.data;
            dataTransactionsError.transactionsData = [];
            return {
                ...state,
                transactions: dataTransactionsError,

            };

        case UPDATE_LEDGERS:

            console.log("UpdateLedgers:" + JSON.stringify(action.payload.data));
            console.log({state});
            const dataTransactions = {...state.transactions};
            // dataTransactions.transactionsData = action.payload.data;
            dataTransactions.isLoading = false;
            dataTransactions.error = '';
            return {
                //Copy the actual state and update it according to the available accounts
                ...state,
                transactionsData: action.payload.data,
                transactions: dataTransactions
            };



//CATEGORIES

        // Fetch_Categories_Started: Is started the empty array of dataCategories

        case FETCH_CATEGORIES_STARTED:
            dataCategories.isLoading = true;
            dataCategories.error = "";
            dataCategories.categoriesData = [];
            return {
                ...state,
                accounts: dataCategories,
            };

        // Fetch_Categories_Success: dataCategories array is filled with data

        case FETCH_CATEGORIES_SUCCESS:
            dataCategories.isLoading = false;
            dataCategories.error = "";
            dataCategories.categoriesData = action.payload.data.sort(function (a, b) {
                if (a.toLowerCase() < b.toLowerCase()) {
                    return -1;
                }
                if (a.toLowerCase() > b.toLowerCase()) {
                    return 1;
                }
                return 0;
            });

            console.log(action.payload.data);
            return {
                ...state,
                categories: dataCategories,
            };

        // Fetch_Categories_Error: It is not possible to present the categories

        case FETCH_CATEGORIES_ERROR:
            dataCategories.isLoading = false;
            dataCategories.error = action.payload.data;
            dataCategories.categoriesData = [];
            return {
                ...state,
                categories: dataCategories,
            };

        // Update_Categories

        case UPDATE_CATEGORIES:

            //console.log("UpdateCategories:" + JSON.stringify(action.payload.data));
            //console.log({state});

            dataCategories.isLoading = false;
            dataCategories.error = "";
            dataCategories.categoriesData = action.payload.data.sort(function (a, b) {
                if (a.toLowerCase() < b.toLowerCase()) {
                    return -1;
                }
                if (a.toLowerCase() > b.toLowerCase()) {
                    return 1;
                }
                return 0;
            });

            return {
                //Copy the actual state and update it according to the available categories
                ...state,
                categories: dataCategories,
            };


//ACCOUNTS

        // Fetch_Account_Started: Is started the empty array of dataAccounts

        case FETCH_ACCOUNTS_STARTED:
            dataAccounts.isLoading = true;
            dataAccounts.error = "";
            dataAccounts.accountsData = [];
            return {
                ...state,
                accounts: dataAccounts,

            };

        // Fetch_Account_Success: dataAccounts array is filled with data

        case FETCH_ACCOUNTS_SUCCESS:
            dataAccounts.isLoading = false;
            dataAccounts.error = "";
            dataAccounts.accountsData = action.payload.data.sort(function (a, b) {
                if (a.denomination.toLowerCase() < b.denomination.toLowerCase()) {
                    return -1;
                }
                if (a.denomination.toLowerCase() > b.denomination.toLowerCase()) {
                    return 1;
                }
                return 0;
            });

            console.log(action.payload.data);
            return {
                ...state,
                accounts: dataAccounts,

            };

        // Fetch_Accounts_Error: It is not possible to present the accounts

        case FETCH_ACCOUNTS_ERROR:
            dataAccounts.isLoading = false;
            dataAccounts.error = action.payload.data;
            dataAccounts.accountsData = [];
            return {
                ...state,
                accounts: dataAccounts,

            };

        case UPDATE_ACCOUNTS:

            //console.log("UpdateAccounts:" + JSON.stringify(action.payload.data));
            //console.log({state});

            dataAccounts.isLoading = false;
            dataAccounts.error = "";
            dataAccounts.accountsData = action.payload.data.sort(function (a, b) {
                if (a.denomination.toLowerCase() < b.denomination.toLowerCase()) {
                    return -1;
                }
                if (a.denomination.toLowerCase() > b.denomination.toLowerCase()) {
                    return 1;
                }
                return 0;
            });

            return {
                //Copy the actual state and update it according to the available accounts
                ...state,
                accounts: dataAccounts,
            };

        // HANDLE CLICKS

// MY PAGE / MY GROUPS


        case MY_PAGE_HANDLE_CLICKS:
            return {
                ...state,
                myPage: true,
                myGroups: false,
            }
        case MY_GROUPS_HANDLE_CLICKS:
            return {
                ...state,
                myPage: false,
                myGroups: true,
            }
        case MY_HOMEPAGE_HANDLE_CLICKS:
            return {
                ...state,
                myPage: false,
                myGroups: false,
            }

        //HIDE INFO

        case HIDE_INFO:
            return {
                ...state,
                userid: 0,
                categoriesData: [],
                accountsData: [],
            }
        //SEARCH_TRANSACTION
        case SEARCH_TRANSACTION:

            //This function will copy the actual state and change the variable isLogged with true
            return {
                ...state,
                searchTransaction: action.payload.searchTransaction,
            };
        default:
            return state;
    }
}

export default reducer;