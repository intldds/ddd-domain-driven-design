import React from 'react';

import {BrowserRouter as Router, Route, Link, Switch} from 'react-router-dom';
import AppContext from './context/AppContext';
import PrivateRoute from './links/PrivateRoute';
import NoRoute from './links/NoRoute';
import Home from './links/Home';
import MyGroups from './links/MyGroups';
import Ledger from './links/Ledger';
import Accounts from './links/Accounts';
import Categories from './links/Categories';
import Login from './links/Login';

import GroupId from './links/GroupId';
import AdminsGroup from "./links/AdminsGroup";
import MembersGroup from "./links/MembersGroup";
import LedgerGroup from './entities/ledgers/LedgerGroup';
import MyPage from "./links/Mypage";

const App = () => {
    const {state} = React.useContext(AppContext);
    const {isLogged} = state;
    if (isLogged === true)
        return (
            <div>
                {/*It enables the rendering of each component assigned to a URL*/}
                <Router>
                    <Switch>
                        {/*Only one of this components wil be rendered, according to the path*/}
                        {/*  In order to render all the components we need to use the propriety exact*/}
                        <PrivateRoute exact path="/" component={Home}/>

                        <PrivateRoute exact path="/mypage" component={MyPage}/>

                        <PrivateRoute exact path="/myGroups" component={MyGroups}/>
                        <PrivateRoute exact path="/myGroups/:groupDenomination" component={GroupId}/>
                        <PrivateRoute exact path="/myGroups/:groupDenomination/MembersGroup" component={MembersGroup}/>

                        {/*localhost:8080//persons/paulo@gmail.com/ledgers/records?accountName=Company&startDate=2020-01-01&endDate=2020-05-18*/}


                        <PrivateRoute exact path="/mypage/ledger" component={Ledger}/>
                        <PrivateRoute exact path="/myGroups/:groupDenomination/ledger" component={LedgerGroup}/>

                        <PrivateRoute exact path="/mypage/accounts" component={Accounts}/>
                        <PrivateRoute exact path="/myGroups/:groupDenomination/accounts" component={Accounts}/>

                        <PrivateRoute exact path="/mypage/categories" component={Categories}/>
                        <PrivateRoute exact path="/myGroups/:groupDenomination/categories" component={Categories}/>

                        <PrivateRoute exact path="/admins/group" component={AdminsGroup}/>
                        <PrivateRoute exact path="/myGroups/:groupDenomination/members" component={MembersGroup}/>



                        {/*If the URL typed isn't correct it will redirect to the component Login*/}
                        <NoRoute component={Login}/>
                    </Switch>
                </Router>
            </div>
        );
    else {
        return (
            <Router>
                <Route component={Login}/>
            </Router>
        );
    }

}

export default App;

