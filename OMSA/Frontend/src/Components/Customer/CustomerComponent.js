import React, { Component } from "react";
import CustomerService from "./CustomerService";
import { Link } from "react-router-dom";
import 'bootstrap/dist//css/bootstrap.min.css'
import Navbarcust from "../Navbar/navbarcustomer";
import "../CSSStyles/Styles.css";
import Footer from "../Footer/Footer";

//List of customer
class CustomerComponent extends Component{
     // Step 1:
  constructor(props) {
    super(props);
    this.state = {
      customer: {},
    };
  }

  //showing the all customer details
  //Step 2:
  componentDidMount() {
    CustomerService.showByCustomerId(1).then((Response) => {
      this.setState({ customer: Response.data });
    });
  }

render(){
        return(
            <div className="mystyle" style={{height:"100vh"}}>
                <Navbarcust/>
                <div className="container" >
                <style>{'body { background-color: rgb(0, 255, 234); }'}</style>
          <h3 style={{fontFamily:"cursive",color:"white"}}>Your Details</h3>      
          <br/>
          <div className="card " style={{boxShadow:"2px 2px 5px lightblue"}}>
                  <br/>
                    <div className="card-body" style={{ color: "black" }}>
                      <h5 className="card-title">
                        Customer Name &nbsp; :&nbsp; {this.state.customer.customerName}
                      </h5>
                      <h5 className="card-text">
                        Email ID &nbsp; : &nbsp; {this.state.customer.emailId}
                      </h5>
                      <h5 className="card-text">
                        Mobile Number &nbsp; : &nbsp;{this.state.customer.mobileNumber}
                      </h5>
                      <h5 className="card-text">
                        Address &nbsp; : &nbsp;{this.state.customer.address}
                      </h5>
                      <div className="d-grid gap-2">
                        <button id="addbtn" className="btn btn-secondary" type="button">
                          <Link
                            id="addbtn"
                            style={{textDecoration:"none",color:"white"}}
                            to={`/customer/updatecustomer/${this.state.customer.customerId}`}
                          >
                           <strong>Update</strong>
                          </Link>{" "}
                        </button>
                      </div>
                    </div>
        </div>
        </div><br/>
        <br/>
        <br/>
        <br/>
        <Footer/>
        </div>      
    )
    }
}
export default CustomerComponent;