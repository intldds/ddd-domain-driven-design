import React, {Component} from 'react';

class CategoriesTableHeader extends Component {

    render() {
        const {headers} = this.props;
        return (
            <thead>
            <tr>
                <th>{headers.header1}</th>
            </tr>
            </thead>
        );
    }
}

export default CategoriesTableHeader;