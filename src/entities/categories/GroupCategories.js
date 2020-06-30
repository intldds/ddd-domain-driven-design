import React, {useContext} from 'react';
import AppContext from '../../context/AppContext';

function GroupCategories() {
    const {state} = useContext(AppContext);
    const {groups, categoriesData, accountsData, transactions} = state;
    const {transactionsData} = transactions;
    const {isLoading, error, groupsData} = groups;
    // if (isLoading === true) {
    //     return (<h1>Loading ....</h1>);
    // }
    // else {
    //     if (error !== null) {
    //         return (<h1>Error ....</h1>);
    //     } else
            if (categoriesData.length > 0) {
            return (
                <div>
                    <div>
                        <ul>
                            {categoriesData.map((category) => (
                                <li>{category}</li>
                            ))}
                        </ul>
                    </div>
                </div>
            );
        } else {
            return (<h1>No data ....</h1>);
        }

}

export default GroupCategories;