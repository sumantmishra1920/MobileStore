import React from "react";
import 'bootstrap/dist//css/bootstrap.min.css';
import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import Navbarcust from "../Navbar/navbarcustomer";
import MobileService from "./MobileService";
import "../CSSStyles/Styles.css";
import { BsFillCartFill } from "react-icons/bs";
import Footer from "../Footer/Footer";

function ViewMobileComponent() {
    const { id } = useParams();
    const navigate = useNavigate();
    //object to store data of the mobile
    const [mobile, setMobile] = useState({
        mobileName: "",
        mobileCost: "",
        mfd: "",
        modelNumber: "",
        companyName: "",
        category: {}
    });

    //getting the data from database
    const getMobileById = async (e) => {
        const mobileInfo = await MobileService.getMobileById(id);
        setMobile(mobileInfo.data);
    };
    useEffect(() => {
        getMobileById();
    });

    //function to add to cart
    const add = (id) => {
        MobileService.addToCart(1, id).then((response) => {
            console.log(response);
            alert("Mobile Added to Your Cart Successfully");
            navigate("/mobile");
        },
            (error) => {
                console.log(error.response.data);
                alert(error.response.data.exceptionMessage);
            }
        );
    };
    //storing path of the image to use
    const imgsrc = "/images/" + mobile.mobileName + ".jpg";
    return (
        <div className="mystyle">
            <Navbarcust></Navbarcust>
            <div ></div>
            <div>
                <div></div>
                <div className="card">
                    <img className="center" src={imgsrc} alt="Not found" style={{ height: 250, width: 250 }}></img>
                    <div className="card-body" style={{ color: "purple" }}>
                        <h5 className="card-title">
                            Mobile Name : {mobile.mobileName}
                        </h5>
                        <h5 className="card-text">
                            Mobile Cost &nbsp; :&nbsp;₹{mobile.mobileCost}
                        </h5>
                        <h5 className="card-text">
                            Manufactured Date &nbsp; :&nbsp;{mobile.mfd}
                        </h5>
                        <h5 className="card-text">
                            Model Number &nbsp; :&nbsp;{mobile.modelNumber}
                        </h5>
                        <h5 className="card-text">
                            Company Name &nbsp; :&nbsp;{mobile.companyName}
                        </h5>
                        <h5 className="card-text">
                            Category &nbsp; :&nbsp;{mobile.category.categoryName}
                        </h5>
                        <div className="d-grid gap-4">
                            <button className="btn btn-info" type="button" onClick={() => { navigate('/mobile') }}>
                                Go Back
                            </button>
                            <button className="btn btn-warning" type="button" onClick={() => { add(mobile.mobileId) }}>
                                Add to Cart&thinsp;<span ><BsFillCartFill style={{ paddingBottom: "3px", verticalAlign: "middle" }} /></span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <Footer />
        </div>
    )

}
export default ViewMobileComponent;