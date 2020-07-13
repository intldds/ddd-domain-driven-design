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
            // <div id="LedgerTableDiv">
            <Table className="Tables">
                {/*<div id="LedgerHeader">*/}
                <LedgerTableHeader headers={headers}/>
                {/*</div>*/}
                <div id="LedgerTableBody">
                    <LedgerTableBody data = {data}/>
                </div>
            </Table>

        );
    }
}
export default LedgerTable