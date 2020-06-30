import React, {Component} from 'react';

class TableBody extends Component {

    render() {
        const {data} = this.props;

        const rows = data.map(
            (row, index) =>
                <tr key={index}>
                    <td>{row.denomination}</td>
                    <td>{row.description}</td>
                </tr>
        );

        return (
            <tbody>{rows}</tbody>
        );
    }
}

export default TableBody;