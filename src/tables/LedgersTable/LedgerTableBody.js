import React, {Component, useContext} from 'react';
import AppContext from "../../context/AppContext";
import axios from "axios";

const api = axios.create({
    baseURL: 'http://localhost:8080'
})

function LedgerTableBody() {
    const {state} = useContext(AppContext);
    const {userid, transactionsData} = state;
    console.log({transactionsData});

    // const {transactionsData} = transactions;

    const rows = transactionsData.map((row, index) => {

        return (

            <tr key={index}>
                <td colSpan="1" style={{width: "200px"}}>{row.category}</td>
                <td style={{width: "200px"}}>{row.type}</td>
                <td style={{width: "200px"}}>{row.description}</td>
                <td style={{width: "200px"}}>{row.amount}</td>
                <td style={{width: "200px"}}>{row.date}</td>
                <td style={{width: "200px"}}>{row.debitAccount}</td>
                <td style={{width: "200px"}}>{row.creditAccount}</td>
            </tr>

        )
    });
    return (

        <tbody>{rows}</tbody>
    );
}

export default LedgerTableBody;