import React, {useContext} from 'react';
import AppContext from "../context/AppContext";
import MyPage from "./Mypage";

function Home() {

    const {state} = useContext(AppContext);

    const {isLogged} = state;

    if (isLogged === true) {
        return (
            <div>
                <ul>
                    <MyPage/>
                </ul>
            </div>


        );
    }

}

export default Home;