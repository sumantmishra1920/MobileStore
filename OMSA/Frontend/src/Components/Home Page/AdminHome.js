import React, { Component } from 'react';
import Navbarcust from '../Navbar/navbaradmin';
//Change HERE*******

class Home extends Component {
    render() {
        return (
            <div>
                <Navbarcust></Navbarcust>
                <h3>Home</h3>

                <div id="hp_container_outer">
                    <div id="hp_container_inner">

                        <h1 >Welcome Admin!</h1>
                        <img src="./images/admin.jpg" style={{ height: "35vh", width: "18vw" }} />
                        <h5><i>Choose the desired link from Navigation bar to get started!</i></h5>

                    </div>
                </div>

            </div>
        )
    }
}
export default Home;