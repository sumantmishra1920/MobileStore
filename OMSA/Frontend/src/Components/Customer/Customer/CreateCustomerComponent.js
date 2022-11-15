import React,{useState} from 'react';
import axios from 'axios';
import Navbarcust from "../Navbar/navbaradmin";
import {useNavigate } from "react-router-dom";
import "../CSSStyles/Styles.css";

//Create customer
function CreateCustomerComponent(){
  
  //Navigation for the other components
    const navigate = useNavigate();

    //variables of the customer
  const [customer, setCustomer] = useState({
    customerName: "",
    emailId: "",
    mobileNumber: "",
    address: "",
  });

  //update the customer variables
  const onInputChange = (e) => {
    setCustomer({ ...customer, [e.target.name]: e.target.value });
  };

  //store the data in customer
  const { customerName, emailId, mobileNumber, address} =
    customer;

    //for validation
  const FormHandle = (e) => {
    e.preventDefault();
    addDataToServer(customer);
  };

  //Adding customer
  const addDataToServer = (data) => {
    axios.post("http://localhost:8289/customer/register", data).then(
      (response) => {
        console.log(response);
        alert("Customer Registered Successfully");
        navigate("/customeradmin");
      },
      (error) => {
        alert("Registeration failed");
      }
    );
  };
  return(
    <div className='mystyle'>
  <Navbarcust/>
 <div className="container" >
   <div className="w-75 mx-auto shadow p-5 mt-2 bg-light">
     <div className="jumbotron">
       <h3 style={{fontFamily:"cursive"}}>Add Customer Details</h3>
       <div>
         <form onSubmit={(e) => FormHandle(e)}>

           <div className="form-group">
             <label for="exampleInputCustomerName">Customer Name</label>
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
                  <label for="exampleInputEmailId">Email Id</label>
                  <input
                    type="email"
                    className="form-control"
                    name="emailId"
                    placeholder="Enter Email ID"
                    required
                    value={emailId}
                    onChange={(e) => onInputChange(e)}
                  />
                  </div>
                  <div className="form-group">
                  <label for="exampleInputMobileNo">Mobile Number</label>
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
                  <label for="exampleInputAddress">Address</label>
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
                    Add Customer
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

export default CreateCustomerComponent;