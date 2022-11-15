import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import Navbarcust from "../Navbar/navbarcustomer";
import "../CSSStyles/Styles.css";
import Footer from "../Footer/Footer";

//Update the customer
function UpdateCustomer(){
  const { customerId } = useParams();
  const URL = `http://localhost:8289/customer/update/${customerId}`;

  //Navigation to other components
  const navigate = useNavigate();

  useEffect(() => {
    getCustomerById();
  });

  //Variables of the customer
  const [customer, setCustomer] = useState({
    customerName: "",
    emailId: "",
    mobileNumber: "",
    address: "",
  });

  //store the data in customer
  const { customerName, emailId, mobileNumber, address} =
    customer;

    //updating the customer
  const onInputChange = (e) => {
        setCustomer({ ...customer, [e.target.name]: e.target.value });
    };

    //For validation
    const FormHandle = (e) => {
        e.preventDefault();
        updateDataToServer(customer);
      };
    
      //updating the customer
      const updateDataToServer = (data) => {
        axios.put(URL, data).then(
          (response) => {
            alert("Your Details Updated Successfully");
            navigate("/customer");
          },
          (error) => {
            alert("Operation failed");
          }
        );
      };

      const getCustomerById = async (e) => {
        const customerInfo = await axios.get(URL);
        setCustomer(customerInfo.data);
      };

      return(
        <div className="mystyle">
      <Navbarcust/>
      <div className="container">
        <div className="w-75 mx-auto shadow p-5 mt-2 bg-light">
          <div className="jumbotron">
            <h3 style={{fontFamily:"cursive"}}>Update Your Details</h3>
            <div>
              <form onSubmit={(e) => FormHandle(e)}>
                <div className="form-group">
                  <label>Customer Name</label>
                  <input
                    type="text"
                    className="form-control"
                    name="customerName"
                    placeholder="Enter Customer Name"
                    required
                    value={customerName}
                    onChange={(e) => onInputChange(e)}
                  />
                </div>
                
                <div className="form-group">
                  <label>Email Id </label>
                  <input
                    type="email"
                    className="form-control"
                    name="emailId"
                    placeholder="Enter Email Id"
                    required
                    value={emailId}
                    onChange={(e) => onInputChange(e)}
                  />
                </div>
                <div className="form-group">
                  <label>Mobile Number </label>
                  <input
                    type="number"
                    className="form-control"
                    name="mobileNumber"
                    placeholder="Enter Mobile Number"
                    required
                    value={mobileNumber}
                    onChange={(e) => onInputChange(e)}
                  />
                </div>
                <div className="form-group">
                  <label>Address</label>
                  <input
                    type="text"
                    className="form-control"
                    name="address"
                    placeholder="Enter Address"
                    required
                    value={address}
                    onChange={(e) => onInputChange(e)}
                  />
                </div>
                <div className="container text-center">
                  <button
                    id="addbtn"
                    type="submit"
                    className="btn btn-primary"
                  >
                    Update
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
      <Footer/>
    </div>
    );
}

export default UpdateCustomer;