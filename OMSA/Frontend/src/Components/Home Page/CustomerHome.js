import React, { Component } from 'react';
import Navbarcust from '../Navbar/navbarcustomer'; //Change HERE*******
import "../CSSStyles/CustomerHome.css"; //Change HERE*******
import Footer from "../Footer/Footer";
import { Link } from 'react-router-dom'
class Home extends Component {
    render() {
        return (
            <div className='customerhome'>
                <Navbarcust></Navbarcust>

                <div id="hp_container_outer">

                    <div id="hp_container_inner">
                        <h5><i><br></br>Hurry Up! Limited Time Special Offers</i></h5>
                    </div>
                    <div style={{ paddingTop: "542px" }}>
                        <Link to='../mobile'>
                            <button className="start">Shop Now!</button>
                        </Link>
                    </div>
                    <br />
                </div>
                <Footer />
            </div>
        )
    }
}
export default Home;