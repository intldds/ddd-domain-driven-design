import React, {Component} from "react";
import axios from 'axios';
import Table from "../tables/Table";

const api = axios.create({
    baseURL: 'http://localhost:8080/'
})

class Members extends Component {
    state = {
        members: []
    }

    constructor() {
        super();
        this.getAllMembers()
    }


}