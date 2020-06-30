import React, {Component, useContext, useEffect, useState} from "react";
import AppContext from "../../context/AppContext";
import {Api, fetchCategoriesError, fetchCategoriesStarted, fetchCategoriesSuccess} from '../../context/Actions';
import CategoryForm from "./CategoryForm";
import MyPage from "../../links/Mypage";
import CategoriesTable from "../../tables/CategoriesTable/CategoriesTable";
import GroupId from "../../links/GroupId";

function EntityCategory() {
    const {state, dispatch} = useContext(AppContext);

    const {isLogged, userID, categories, groupDenomination, myPage, myGroups} = state;
    const {isLoading, error, categoriesData} = categories;
    const [isAdmin, setIsAdmin] = useState(false);
    const [urlForm, setUrlForm] = useState('');

    useEffect(() => {
        if (isLogged == true) {
            getCategories();
        }
    }, [])

    const getCategories = async () => {

        const user = userID.toLowerCase();

        if (myPage && !myGroups) {

            const url = '/persons/' + user + '/categories';

            dispatch(fetchCategoriesStarted());

            try {
                const res = await Api.get(url);
                const {data} = res;
                dispatch(fetchCategoriesSuccess(data.categories));
                setIsAdmin(true);
            } catch (err) {
                dispatch(fetchCategoriesError(err.message));
            }
        }

        if (!myPage && myGroups) {

            const url = '/persons/' + user + '/groups/' + groupDenomination + '/categories';

            dispatch(fetchCategoriesStarted());

            try {
                const res = await Api.get(url);
                const {data} = res;
                dispatch(fetchCategoriesSuccess(data.categories));
                if (res.data._links != null) {
                    setIsAdmin(true);
                    setUrlForm(res.data._links.addCategory.href.toString());
                } else {
                    setIsAdmin(false);
                }
            } catch (err) {
                dispatch(fetchCategoriesError(err.message));
            }

        }

    }

    const headers = {
        header1: 'Category'
    };

    let data;
    let admin;
    let navBar;

    if (!isLogged) {
        return (<h1>Not logged</h1>);
    }

    if (isLoading) {
        return (<h1>Loading...</h1>);
    }

    if (error.length > 0) {
        return (<h1>{error}...</h1>);
    } else {
        data = <CategoriesTable data={categoriesData} headers={headers}/>
    }

    if (isAdmin) {

        admin = <CategoryForm url={urlForm}/>

    }

    if (myPage && !myGroups) {
        navBar = <MyPage/>;
    }

    if (!myPage && myGroups) {
        navBar = <GroupId/>
    }

    return (

        <div>
            {navBar}
            <br/>
            {data}
            {admin}
        </div>
    )
}

export default EntityCategory;

