import React, {useContext, useEffect, useState} from "react";

import AppContext from "../../context/AppContext";
import {
    Api,
    fetch_ledgers_error, fetch_ledgers_success, fetchAccountsSuccess,
} from "../../context/Actions";
import Button from "react-bootstrap/Button";

const TransactionAccountSelect = (props) => {

    useEffect(() => {
        if (isLogged == true) {
            getAccounts();
        }
    }, [])

    const {state, dispatch} = useContext(AppContext);

    const {isLogged} = state


    const {userID, accounts} = state;
    const {accountsData} = accounts;
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');
    const [accountDenomination, setAccountDenomination] = useState('');

    const handleClick = (event) => {
        const user = userID.toLowerCase();
        console.log(user);
        event.preventDefault();
        console.log("handleChange: " + event.target);
        console.log(startDate);
        console.log(endDate);
        console.log(accountDenomination);

        setAccountDenomination('');
        setStartDate('');
        setEndDate('');

        document.getElementById("myCategory").selectedIndex = "0";

    }

    const getAccounts = async () => {

        const user = userID.toLowerCase();

        const url = `/persons/`+ user +`/accounts`;

        try {
            const res = await Api.get(url);
            const {data} = await res;
            dispatch(fetchAccountsSuccess(data.accounts));
        } catch (err) {
            dispatch(fetch_ledgers_error(err.message));
        }
    }

    const getTransactionsBetweenTwoDatesFromAnAccount = async (event) => {


        //console.log(user);
        event.preventDefault();
        console.log("handleChange: " + event.target);
        console.log(startDate);
        console.log(endDate);
        console.log(accountDenomination);


        const user = userID.toLowerCase();

        const href = props.url.split("?");
        const parameters = href[1].split("&")

        //accountName -> parameters[0]
        //startDate -> parameters[1]
        //endDate -> parameters[2]

        const url = href[0] + `?` + parameters[0] + `${accountDenomination}&` + parameters[1] + `${startDate}&` + parameters[2] + `${endDate}`;

        console.log(url)

        try {
            const res = await Api.get(url);
            const {data} = await res;
            console.log(res)
            console.log(data)
            dispatch(fetch_ledgers_success(data));
        } catch (err) {
            dispatch(fetch_ledgers_error(err.message));
        }
        setAccountDenomination('');
        setStartDate('');
        setEndDate('');

        //document.getElementById("myAccount").selectedIndex = "0";
    }

    return (

        <div className="FormDiv">
            <form className="Forms" id="form" onSubmit={getTransactionsBetweenTwoDatesFromAnAccount}>
                <label id="label" htmlFor="fname"><b> Insert Account: </b></label>
                <select id={"myAccount"} onChange={(event) => setAccountDenomination(event.target.value)}>
                    <option value={""}>Please enter account</option>
                    {accountsData && accountsData.map((event) => {
                        return <option denomination={event.denomination}
                                       value={event.denomination}>{event.denomination}</option>
                    })}
                </select>
                <label id="label" htmlFor="fname"><b> Insert StartDate: </b></label>
                <input  type="date" value={startDate}
                        placeholder={'Please enter start date'}
                        onChange={(event) => setStartDate(event.target.value)}/>
                <label id="label" htmlFor="fname"><b> Insert EndDate: </b></label>
                <input type="date" value={endDate}
                       placeholder={'Please enter end date'}
                       onChange={(event) => setEndDate(event.target.value)}/>
                <Button id = "SearchButton" variant="outline-primary" type="submit">Search</Button>
            </form>
            <Button id="ResetButton" variant="outline-primary" type="submit" onClick={getTransactionsBetweenTwoDatesFromAnAccount}>Reset</Button>

        </div>

    );
};

export default TransactionAccountSelect;