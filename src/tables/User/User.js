import React, {Component, useContext, useState} from "react";
import axios from 'axios';
import AppContext from "../../context/AppContext";


const api = axios.create({
    baseURL: 'http://localhost:8080'
})

function User() {
    const {state, dispatch} = useContext(AppContext);
    const {user} = state;

    return (
        <h1 id="userConfig" >{user}</h1>
    )
}
export default User
