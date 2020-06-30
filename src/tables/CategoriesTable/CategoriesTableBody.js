import React, {Component} from 'react';

class CategoriesTableBody extends Component {

    render() {
        const {data} = this.props; // data = this.props.data;

        const rows = data.map(
            (row, index) =>
                <tr key={index}>
                    <td>{row}</td>
                </tr>
        )

        return (
            <tbody>{rows}</tbody>
        );
    }
}

export default CategoriesTableBody;


