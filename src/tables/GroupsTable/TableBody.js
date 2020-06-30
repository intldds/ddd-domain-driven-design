import React, {useContext} from 'react';
import AppContext from "../../context/AppContext";
import {useHistory} from 'react-router-dom';
import {setGroupDenomination} from "../../context/Actions";

function GroupsTableBody() {
    const {state, dispatch} = useContext(AppContext);
    const {groups} = state;

    const {groupsData} = groups;

    let history = useHistory();

    const handleOnClick = (denomination) => {

        dispatch(setGroupDenomination(denomination));

        history.push("/myGroups/" + denomination)
    }

    const rows = groupsData.map((row, index) => {

        return (
            <tr key={index}>
                <td className="GroupDenomination" style={{cursor: "pointer", hover: "blue"}} onClick={() => handleOnClick(row.denomination)}>{row.denomination}</td>
                <td>{row.description}</td>
                <td>{row.dateOfCreation}</td>
            </tr>

        )
    });
    return (

        <tbody>{rows}</tbody>
    );
}

export default GroupsTableBody;