import React, { Component } from "react";
import TableBody from "./TableBody";
import TableHeader from "./TableHeader";
import Table from "react-bootstrap/Table";

class GroupsTable extends Component {

    constructor(props) {
        super(props);
    }
    render() {
        const headers = this.props.headers;

        console.log({headers})
        const data = this.props.data;

        return (
            <Table className="Tables">
                <TableHeader headers={headers}/>
                <TableBody  data={data}/>
            </Table>
        );
    }
}

export default GroupsTable;