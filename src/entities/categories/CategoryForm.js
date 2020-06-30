import React, {useContext, useState} from "react";
import axios from 'axios';
import AppContext from "../../context/AppContext";
import {Api, updateCategories} from "../../context/Actions";
import Button from "react-bootstrap/Button";

const CategoryForm = (props) => {

    const {state, dispatch} = useContext(AppContext)

    const {userID, categories, myPage, myGroups} = state;
    const [denomination, setDenomination] = useState('');

    const handleClick = async (event) => {
        const user = userID.toLowerCase();

        event.preventDefault();

        if (myPage && !myGroups) {

            try {
                await Api.post('/persons/' + user + '/categories/', {
                    denomination: denomination
                });

                dispatch(updateCategories([...categories.categoriesData, denomination]));

            } catch (err) {
                alert(err.response.data.message);
            }
        }

        if (!myPage && myGroups) {

            try {
                await axios.post(props.url, {categoryDenomination: denomination});

                dispatch(updateCategories([...categories.categoriesData, denomination]));

            } catch (err) {
                alert(err.response.data.message);
            }
        }
        setDenomination('');
    }

    return (
        <div>
        <form className="Forms" onSubmit={handleClick}>

            <input type="text" value={denomination} autoFocus
                   placeholder={'Please enter denomination'}
                   required onChange={(event) => setDenomination(event.target.value)}/>

            <Button variant="outline-primary" type="submit">Add Category</Button>
        </form>
        </div>
    );
};

export default CategoryForm;