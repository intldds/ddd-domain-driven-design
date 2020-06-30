import React, { Component } from 'react';
import CategoriesTableHeader from './CategoriesTableHeader';
import CategoriesTableBody from './CategoriesTableBody';
import Table from "react-bootstrap/Table";
class CategoriesTable extends Component {

    render() {
        const headers = this.props.headers;

        console.log({headers})
        const data = this.props.data;

        return (
            <Table className="Tables" >
                <CategoriesTableHeader headers={headers}/>
                <CategoriesTableBody data={data}/>
            </Table>
        );
    }
}
export default CategoriesTable;