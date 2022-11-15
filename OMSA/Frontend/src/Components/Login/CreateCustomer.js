import React,{useState} from 'react';
import "./login.css"
import axios from 'axios';
import 'bootstrap/dist//css/bootstrap.min.css'
import {useNavigate } from "react-router-dom";
import "../CSSStyles/Styles.css";

function CreateCustomer(){
    const navigate = useNavigate();
    
    //object to pass to the database
  const [customer, setCustomer] = useState({
    customerName: "",
    emailId: "",
    mobileNumber: "",
    address: "",
  });

  //input handler
  const onInputChange = (e) => {
    setCustomer({ ...customer, [e.target.name]: e.target.value });
  };
  const { customerName, emailId, mobileNumber, address} =
    customer;

  //submit handler
  const FormHandle = (e) => {
    e.preventDefault();
    addDataToServer(customer);
  };

  //adding to the database
  const addDataToServer = (data) => {
    axios.post("http://localhost:8289/customer/register", data).then(
      (response) => {
        console.log(response);
        alert("You have been Registered Successfully");
        navigate("/login");
      },
      (error) => {
        alert("Registeration failed");
      }
    );
  };
  return(
    <div className='mystyle' style={{position:"fixed",top:"0"}}>
 <div className="Auth-form-container" >
   <div className="Auth-form">
     <div className="Auth-form-content">
       <h3 style={{fontFamily:"cursive"}}>Add Your Details</h3>
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
                  <div className="form-group mt-3">
                  <label for="exampleInputMobileNo">Mobile Number</label>
                  <input
                    type="number"
                    className="form-control mt-3"
                    name="mobileNumber"
                    placeholder="Enter Mobile Number"
                    required
                    value={mobileNumber}
                    onChange={(e) => onInputChange(e)}
                  />
                  </div>
                  <div className="form-group mt-3">
                  <label for="exampleInputAddress">Address</label>
                  <input
                    type="text"
                    className="form-control mt-3"
                    name="address"
                    placeholder="Enter Address"
                    required
                    value={address}
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
                    Register
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

export default CreateCustomer;