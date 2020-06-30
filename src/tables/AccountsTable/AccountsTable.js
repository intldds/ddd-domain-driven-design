import React, {Component} from 'react';
import TableHeader from './TableHeader';
import TableBody from './TableBody';
import Table from "react-bootstrap/Table";

class AccountsTable extends Component {

    render() {
        const headers = this.props.headers;

        console.log ({headers})
        const data = this.props.data;

        return (
            <Table className="Tables">
                <TableHeader headers={headers} />
                <TableBody data={data} />
            </Table>
        );
    }
}

export default AccountsTable;