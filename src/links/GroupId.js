import React, {useContext} from 'react';
import AppContext from "../context/AppContext";
import {Link, useHistory} from "react-router-dom";
import '../index.css'
import {myGroupsHandleOnClicks, myHomePageHandleOnClicks, myPageHandleOnClicks} from "../context/Actions";
import Logout from "../links/Logout";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";

function GroupId() {

    const history = useHistory();
    const currentRoute = history.location.pathname;

    const {state, dispatch} = useContext(AppContext)
    const {groupDenomination, username} = state;

    const myPageHandleOnClick = () => {
        dispatch(myPageHandleOnClicks());
    };
    const myGroupsHandleOnClick = () => {
        dispatch(myGroupsHandleOnClicks());
    };

    const myHomePageHandleOnClick = () => {
        dispatch(myHomePageHandleOnClicks());
    };

    const links = {
        accounts: '/myGroups/' + groupDenomination + '/accounts',
        categories: '/myGroups/' + groupDenomination + '/categories',
        ledger: '/myGroups/' + groupDenomination + '/ledger',
        members: '/myGroups/' + groupDenomination + '/members'
    };

    return (
        <div>
            <Navbar classname="navBar" bg="light" variant="light">
                <Navbar.Brand>
                    <Link to="/mypage" onClick={() => myHomePageHandleOnClick()}>G3</Link>
                </Navbar.Brand>
                <Nav>
                    <Nav.Link>
                        <Link to="/mypage" onClick={() => myPageHandleOnClick()}>My Page</Link>
                    </Nav.Link>

                    <Nav.Link className={currentRoute.startsWith("/myGroups") ? "activeNavLink" : undefined}>
                        <Link to="/myGroups" onClick={() => myGroupsHandleOnClick()}>
                            My Groups
                        </Link>
                    </Nav.Link>
                    <Nav.Link>
                        <p id="GroupIdentification">
                            | &ensp; {groupDenomination}
                        </p>
                    </Nav.Link>
                </Nav>
                <Navbar.Collapse className="justify-content-end">
                    <Navbar.Text id="userConfig">
                        {username}
                    </Navbar.Text>
                    <Logout/>
                </Navbar.Collapse>
            </Navbar>

            <br/>

            <div>
                <nav>
                    <ul className="homeNavBar">
                        <li style={{display: "inline"}} className={currentRoute.startsWith('/myGroups/' + groupDenomination + '/members') ? "activeNavLink" : undefined}><Link to={links.members}>Members</Link></li>
                        <li style={{display: "inline"}} className={currentRoute.startsWith('/myGroups/' + groupDenomination + '/ledger') ? "activeNavLink" : undefined}><Link to={links.ledger}>Ledger</Link></li>
                        <li style={{display: "inline"}} className={currentRoute.startsWith('/myGroups/' + groupDenomination + '/accounts') ? "activeNavLink" : undefined}><Link to={links.accounts}>Accounts</Link></li>
                        <li style={{display: "inline"}} className={currentRoute.startsWith('/myGroups/' + groupDenomination + '/categories') ? "activeNavLink" : undefined}><Link to={links.categories}>Categories</Link></li>
                    </ul>
                </nav>
                <hr></hr>
            </div>
        </div>
    )
}


export default GroupId;