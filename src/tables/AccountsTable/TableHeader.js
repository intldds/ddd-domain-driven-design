import React, {Component} from 'react';

class TableHeader extends Component {

    render() {
        const {headers} = this.props;
        return (
            <thead>
            <tr>

                <th>{headers.header1}</th>
                <th>{headers.header2}</th>
            </tr>
            </thead>
        );
    }
}

export default TableHeader;