import React, {Component} from 'react';

class LedgerTableHeader extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        const {headers} = this.props;
        return (
            <div id="LedgerHeader">
                <thead>
                <tr>

                    <th>{headers.header1}</th>
                    <th>{headers.header2}</th>
                    <th>{headers.header3}</th>
                    <th>{headers.header4}</th>
                    <th>{headers.header5}</th>
                    <th>{headers.header6}</th>
                    <th>{headers.header7}</th>
                    <th>{headers.header8}</th>

                </tr>
                </thead>
            </div>
        );
    }
}

export default LedgerTableHeader;