import React, {useContext} from 'react';
import '../index.css'
import AppContext from "../context/AppContext";
import {Link} from "react-router-dom";
import {myPageHandleOnClicks, myGroupsHandleOnClicks, myHomePageHandleOnClicks} from "../context/Actions";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import Logout from "./Logout";

function MyPage() {

    const {state, dispatch} = useContext(AppContext);

    const {username, myPage, myGroups} = state;

    const myPageHandleOnClick = () => {
        dispatch(myPageHandleOnClicks());
    };
    const myGroupsHandleOnClick = () => {
        dispatch(myGroupsHandleOnClicks());
    };

    const myHomePageHandleOnClick = () => {
        dispatch(myHomePageHandleOnClicks());
    };


    if (!myPage && !myGroups || !myPage && myGroups) {
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
                            <Link to="/myGroups" onClick={() => myGroupsHandleOnClick()}>My Groups</Link>
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
            </div>


        );
    } else if (myPage && !myGroups) {
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
                            <Link to="/myGroups" onClick={() => myGroupsHandleOnClick()}>My Groups</Link>
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
                <nav>
                    <ul className="homeNavBar">
                        <li style={{display: "inline"}}><Link to="/ledger">Ledger</Link></li>
                        <li style={{display: "inline"}}><Link to="/accounts">Accounts</Link></li>
                        <li style={{display: "inline"}}><Link to="/categories">Categories</Link></li>
                    </ul>
                    <hr/>
                </nav>
            </div>

        )
    }
}

export default MyPage;