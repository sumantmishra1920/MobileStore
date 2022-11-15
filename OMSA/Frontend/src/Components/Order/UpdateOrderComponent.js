import React, { useState, useEffect } from "react";
import axios from "axios";
import 'bootstrap/dist//css/bootstrap.min.css';
import { useParams, useNavigate } from "react-router-dom";
import Navbarcust from "../Navbar/navbarcustomer";
import OrderService from "./OrderService";
import "../CSSStyles/Styles.css";

//updating the order details
function UpdateOrderComponent(){
    const { id } = useParams();

    //Naviagtion to other components
    const navigate = useNavigate();
  
    //Variables of the order
    const [order, setOrder] = useState({
      dispatchDate: "",
      status: "",
    });

    //store the data in order
    const { dispatchDate, status} =
    order;

    //updating the order variables
    const onInputChange = (e) => {
        setOrder({...order, [e.target.name]: e.target.value});
    };

    //for Validation
    const FormHandle = (e) => {
        e.preventDefault();
        updateDataToServer(order);
    };

    //updating the order
    const updateDataToServer = (data) => {
        OrderService.updateDispatchDate(id,data.dispatchDate).then(
          (response) => {
            alert("Dispatch Date Updated Successfully");
          },
          (error) => {
            console.log(error.response.data);
            alert("Operation failed");
          }
        );
        OrderService.updateStatus(id,data.status).then(
          (response) => {
            alert("Status Updated Successfully");
            navigate('/orderadmin');
          },
          (error) => {
            console.log(error.response.data);
            alert("Operation failed");
          }
        );
    };
return(
        <div className="mystyle" style={{height:"100vh"}}>
      <Navbarcust/>
      <div className="container">
        <div className="w-75 mx-auto shadow p-5 mt-2 bg-light">
          <div className="jumbotron">
            <h3 className="display-4 text-center" style={{fontFamily:"cursive"}}>Update Order</h3>
            <div>
              <form onSubmit={(e) => FormHandle(e)}>

                <div className="form-group">
                  <label>Dispatch Date</label>
                  <input
                    type="date"
                    className="form-control"
                    name="dispatchDate"
                    placeholder="Enter Dispatch Date"
                    required
                    style={{boxShadow:"2px 2px 5px lightblue"}}
                    value={dispatchDate}
                    onChange={(e) => onInputChange(e)}
                  />
                </div>

                

                <div className="form-group">
                  <label>Status</label>
                  <input
                    type="text"
                    className="form-control"
                    name="status"
                    placeholder="Enter status of order"
                    required
                    style={{boxShadow:"2px 2px 5px lightblue"}}
                    value={status}
                    onChange={(e) => onInputChange(e)}
                  />
                </div>
                <br/>
            
                <div className="container text-center">
                  <button
                    id="addbtn"
                    type="submit"
                    className="btn btn-primary"
                  >
                    Update order
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    );


}
export default UpdateOrderComponent;