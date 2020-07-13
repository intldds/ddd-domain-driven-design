import React, {useContext, useEffect, useState} from "react";
import AppContext from "../../context/AppContext";
import {
    Api,
    updateLedgers,
    fetch_ledgers_error,
    fetchCategoriesError,
    fetchCategoriesSuccess,
    fetchAccountsSuccess, searchTransactionForm, fetch_ledgers_success
} from "../../context/Actions";
import Button from "react-bootstrap/Button";

const LedgerForm = (props) => {

        useEffect(() => {
            if (isLogged == true && myPage && !myGroups) {
                getCategories();
                getAccounts();
            } else if (isLogged == true && !myPage && myGroups) {
                getGroupCategories();
                getGroupAccounts();
            }
        }, [])

        const {state, dispatch} = useContext(AppContext);

        const {isLogged, categories, accounts, userID, transactionsData, groupDenomination, myPage, myGroups} = state;
        const {categoriesData} = categories;
        const {accountsData} = accounts;

        const [category, setCategory] = useState('');
        const [type, setType] = useState('');
        const [description, setDescription] = useState('');
        const [amount, setAmount] = useState('');
        const [date, setDate] = useState('');
        const [debitAccount, setDebitAccount] = useState('');
        const [creditAccount, setCreditAccount] = useState('');


        const handleClick = (event) => {
            const user = userID.toLowerCase();
            console.log(user);
            event.preventDefault();
            console.log("handleChange: " + event.target);
            console.log(category);
            console.log(type);
            console.log(description);
            console.log(amount);
            console.log(date);
            console.log(debitAccount);
            console.log(creditAccount);


            const request = {
                denominationCategory: category,
                type: type,
                description: description,
                amount: amount,
                denominationAccountDeb: debitAccount,
                denominationAccountCred: creditAccount,
                date: date
            };


            if (myPage && !myGroups) {

                console.log(JSON.stringify(request));
                //dispach loading a true
                Api.post(props.url,
                    request
                ).then(async () => {

                        const url = '/persons/' + user + '/ledgers/records';

                        const res = await Api.get(url);
                        const {data} = await res;

                        dispatch(fetch_ledgers_success(data));
                        if (res.data._links.searchTransaction != null) {
                            dispatch(searchTransactionForm(true));
                        } else {
                            dispatch(searchTransactionForm(false));
                        }

                    }
                ).catch(err => alert(err.response.data.message + ';' + JSON.stringify(request)));
                /*dispatch(updateLedgers([...transactionsData, {
                    category,
                    type,
                    description,
                    amount,
                    date,
                    debitAccount,
                    creditAccount
                }]));*/
                dispatch(searchTransactionForm(true));


                setCategory('');
                setType('');
                setDescription('');
                setAmount('');
                setDate('');
                setDebitAccount('');
                setCreditAccount('');

            }

            if (!myPage && myGroups) {

                console.log(JSON.stringify(request));
                //dispach loading a true
                let data = Api.post('/persons/' + user + '/groups/' + groupDenomination + '/ledgers/records',
                    request
                ).then(async () => {

                        const url = '/persons/' + user + '/groups/' + groupDenomination + '/ledgers/records';

                        const res = await Api.get(url);
                        const {data} = await res;

                        dispatch(fetch_ledgers_success(data));
                        if (res.data._links.searchTransaction != null) {
                            dispatch(searchTransactionForm(true));
                        } else {
                            dispatch(searchTransactionForm(false));
                        }

                    }
                ).catch(err => alert(err.response.data.message + ';' + JSON.stringify(request)));
                /*dispatch(updateLedgers([...transactionsData, {
                    category,
                    type,
                    description,
                    amount,
                    date,
                    debitAccount,
                    creditAccount
                }]));*/
                dispatch(searchTransactionForm(true));

                setCategory('');
                setType('');
                setDescription('');
                setAmount('');
                setDate('');
                setDebitAccount('');
                setCreditAccount('');

            }

            document.getElementById("myCategories").selectedIndex = "0";
            document.getElementById("myType").selectedIndex = "0";
            document.getElementById("myDebitAccount").selectedIndex = "0";
            document.getElementById("myCreditAccount").selectedIndex = "0";
        }

        const getCategories = async () => {

            const user = userID.toLowerCase();

            const url = '/persons/' + user + '/categories';

            try {
                const res = await Api.get(url);
                const {data} = await res;
                dispatch(fetchCategoriesSuccess(data.categories));
            } catch (err) {
                dispatch(fetchCategoriesError(err.message));
            }

        }

        const getAccounts = async () => {

            const user = userID.toLowerCase();

            const url = '/persons/' + user + '/accounts';


            try {
                const res = await Api.get(url);
                const {data} = await res;
                dispatch(fetchAccountsSuccess(data.accounts));
            } catch (err) {
                dispatch(fetch_ledgers_error(err.message));
            }
        }

        const getGroupCategories = async () => {

            const user = userID.toLowerCase();

            const url = '/persons/' + user + '/groups/' + groupDenomination + '/categories';

            try {
                const res = await Api.get(url);
                const {data} = await res;
                dispatch(fetchCategoriesSuccess(data.categories));
            } catch (err) {
                dispatch(fetchCategoriesError(err.message));
            }
        }

        const getGroupAccounts = async () => {

            const user = userID.toLowerCase();

            const url = '/persons/' + user + '/groups/' + groupDenomination + '/accounts';

            try {
                const res = await Api.get(url);
                const {data} = await res;
                dispatch(fetchAccountsSuccess(data.accounts));
            } catch (err) {
                dispatch(fetch_ledgers_error(err.message));
            }
        }

        return (
            <div>
                <form className="Forms" id="form" onSubmit={handleClick}>
                    <select id={"myCategories"} required
                            onChange={(event) => setCategory(event.target.value)}>
                        <option> Please enter Category</option>
                        {categoriesData && categoriesData.map((category) => {
                            return <option value={category}>{category}</option>
                        })}
                    </select>
                    <select id={"myType"} required onChange={(event) => setType(event.target.value)}>
                        <option> Please enter type</option>
                        <option value="credit">credit</option>
                        <option value="debit">debit</option>
                    </select>
                    <input type="text" value={description}
                           placeholder={'Please enter description'}
                           required onChange={(event) => setDescription(event.target.value)}/>
                    <input type="number" min="0" value={amount}
                           placeholder={'Please enter amount'}
                           required onChange={(event) => setAmount(event.target.value)}/>
                    <input type="date" value={date}
                           placeholder={'Please enter date'}
                           required onChange={(event) => setDate(event.target.value)}/>
                    <select id={"myDebitAccount"} required onChange={(event) => setDebitAccount(event.target.value)}>
                        <option> Please enter Debit Account</option>
                        {accountsData && accountsData.map((event) => {
                            return <option denomination={event.denomination}
                                           value={event.denomination}>{event.denomination}</option>
                        })}
                    </select>
                    <select id={"myCreditAccount"} required onChange={(event) => setCreditAccount(event.target.value)}>
                        <option> Please enter Credit Account</option>
                        {accountsData && accountsData.map((event) => {
                            return <option denomination={event.denomination}
                                           value={event.denomination}>{event.denomination}</option>
                        })}
                    </select>
                    <Button variant="outline-primary" type="submit">Add Transaction</Button>
                </form>
            </div>
        );
    }
;

export default LedgerForm;