import React, {Component, useContext} from 'react';
import AppContext from "../../context/AppContext";
import axios from "axios";
import Button from "react-bootstrap/Button";
import {
    Api, fetch_ledgers_error,
    fetch_ledgers_initiated,
    fetch_ledgers_success,
    searchTransactionForm,
    updateLedgers
} from "../../context/Actions";

function LedgerTableBody() {
    const {state, dispatch} = useContext(AppContext);
    const {transactionsData, userID, myPage, myGroups, groupDenomination} = state;
    console.log({transactionsData});

    const deleteTransaction = async (urlDeleteTransaction) => {

        try {
            await axios.delete(urlDeleteTransaction);

            //Daqui para baixo devia ser uma action que tivesse ela a responsabilidade de ir buscar a info
            // e que fizesse o fetch das transações e o respetivo update do estado

            //Não tendo essa arquitetura implementada estamos a repetir código

            //A mudar no futuro

            const user = userID.toLowerCase();

            if (myPage && !myGroups) {

                const url = '/persons/' + user + '/ledgers/records';

                try {
                    const res = await Api.get(url);
                    const {data} = await res;
                    dispatch(fetch_ledgers_success(data));
                    if (res.data._links.searchTransaction != null) {
                        dispatch(searchTransactionForm(true));
                    } else {
                        dispatch(searchTransactionForm(false));
                    }
                } catch (err) {
                    dispatch(fetch_ledgers_error(err.message));
                }
            }

            if (!myPage && myGroups) {

                const url = '/persons/' + user + '/groups/' + groupDenomination + '/ledgers/records';

                try {
                    const res = await Api.get(url);
                    const {data} = await res;
                    dispatch(fetch_ledgers_success(data));
                    if (res.data._links.searchTransaction != null) {
                        dispatch(searchTransactionForm(true));
                    } else {
                        dispatch(searchTransactionForm(false));
                    }
                } catch (err) {
                    dispatch(fetch_ledgers_error(err.message));
                }

            }

        } catch (err) {
            alert(err.message);
        }
    }

    // const {transactionsData} = transactions;

    const rows = transactionsData.map((row, index) => {

        return (
            <tr key={index} id="LedgerBody">
                <td colSpan="1" style={{width: "200px"}}>{row.category}</td>
                <td style={{width: "200px"}}>{row.type}</td>
                <td style={{width: "200px"}}>{row.description}</td>
                <td style={{width: "200px"}}>{row.amount}</td>
                <td style={{width: "200px"}}>{row.date}</td>
                <td style={{width: "200px"}}>{row.debitAccount}</td>
                <td style={{width: "200px"}}>{row.creditAccount}</td>
                <td style={{width: "200px"}}>
                    <Button variant="outline-warning" type="submit">Edit</Button>&nbsp;
                    <Button variant="outline-danger" type="submit"
                            onClick={() => deleteTransaction(row._links.deleteTransaction.href.toString())}>Delete</Button>
                </td>
            </tr>
        )
    });
    return (

        <tbody>{rows}</tbody>
    );
}

export default LedgerTableBody;