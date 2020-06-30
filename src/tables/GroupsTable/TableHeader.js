import React, {Component} from 'react';

class TableHeader extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        const {headers} = this.props;
        return (
            <thead>
            <tr>
                <th>{headers.header1}</th>
                <th>{headers.header2}</th>
                <th>{headers.header3}</th>
            </tr>
            </thead>
        );
    }
}

export default TableHeader;