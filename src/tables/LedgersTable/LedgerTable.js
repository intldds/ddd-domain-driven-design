import React, { Component } from 'react';
import LedgerTableBody from "./LedgerTableBody";
import LedgerTableHeader from "./LedgerTableHeader";
import Table from "react-bootstrap/Table";

class LedgerTable extends Component {

    constructor(props) {
        super(props);
    }

    render() {

        const headers = this.props.headers;
        const data = this.props.data;

        return (

                <Table className="Tables">
                    <LedgerTableHeader headers={headers}/>
                    <LedgerTableBody data = {data}/>
                </Table>
        );
    }
}
export default LedgerTable