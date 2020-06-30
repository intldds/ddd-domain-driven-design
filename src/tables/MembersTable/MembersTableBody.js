import React, {Component} from 'react';

class MembersTableBody extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        const {data} = this.props;

        const rows = data.map(
            (row, index) =>
                <tr key={index}>
                    <td>{row.memberID}</td>
                    <td>{row.clearance}</td>

                </tr>
        );

        return (
            <tbody>{rows}</tbody>
        );
    }
}

export default MembersTableBody