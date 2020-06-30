import React, {useContext} from 'react';
import AppContext from "../context/AppContext";
import {Link} from "react-router-dom";
import '../index.css'
import {myGroupsHandleOnClicks, myHomePageHandleOnClicks, myPageHandleOnClicks} from "../context/Actions";
import Logout from "../links/Logout";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";

function GroupId() {

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
        categories: '/myGroups/' + groupDenomination + '/categories'
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

                    <Nav.Link>
                        <Link to="/myGroups" onClick={() => myGroupsHandleOnClick()}>My Groups &ensp;</Link> <p id="GroupIdentification"> | &ensp; {groupDenomination}</p>
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
                        <li style={{display: "inline"}}><Link to="/members/group">Members</Link></li>
                        <li style={{display: "inline"}}><Link to="/ledger/group">Ledger</Link></li>
                        <li style={{display: "inline"}}><Link to={links.accounts}>Accounts</Link></li>
                        <li style={{display: "inline"}}><Link to={links.categories}>Categories</Link></li>
                    </ul>
                </nav>
                <hr></hr>
            </div>
        </div>
    )
}


export default GroupId;