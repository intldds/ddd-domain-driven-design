import React, {Component} from "react";
import MembersTableHeader from "./MembersTableHeader";
import MembersTableBody from "./MembersTableBody";
import Table from "react-bootstrap/Table";


class MembersTable extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        const headers = this.props.headers;
        const data = this.props.data;

        return (
            <Table className="Tables">
                <MembersTableHeader headers={headers}/>
                <MembersTableBody data={data}/>
            </Table>
        );
    }

}

export default MembersTable