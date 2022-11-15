import React, { Component } from "react";
import Navbaradmin from "../Navbar/navbaradmin";
import CustomerService from "./CustomerService";
import { Link } from "react-router-dom";
import 'bootstrap/dist//css/bootstrap.min.css'
import "../CSSStyles/Styles.css";

//List of all customers
class CustomerComponentAdmin extends Component{
     // Step 1:
  constructor(props) {
    super(props);
    this.state = {
      customer: [],
    };
  }

  //showing all the customers List
  //Step 2:
  componentDidMount() {
    CustomerService.showAllCustomer().then((Response) => {
      this.setState({ customer: Response.data });
    });
  }

render(){
        return(
            <div className="mystyle">
                <Navbaradmin/>
                <h3 style={{color:"white",fontFamily:"cursive"}}>Customers List</h3>
                <div className="container">
                <br/>
          <div >
            <button className="btn btn-primary" id="addbtn" type="button">
              <Link id="addbtn" to={"/customer/register"} style={{textDecoration:"none",color:"white"}}>
                Create Customer
              </Link>
            </button>
          </div>
          <br/>
          {this.state.customer.length === 0
            ? "No Record "
            : this.state.customer.map((customer, index) => (
              <table class="table table-striped">
                <tbody style={{backgroundColor:"white",boxShadow:"2px 2px 5px lightblue"}}>
                  <tr>
                  <td>
                <div className="card " key={customer.id} style={{ width: "60%"}} class="container text-center">
                  <br/>
                    <div className="card-body" style={{ color: "black" }}>
                      <h5 className="card-title">{index + 1}</h5>
                      <h5 className="card-title">
                        Customer Name &nbsp; :&nbsp; {customer.customerName}
                      </h5>
                      <h5 className="card-text">
                        Email ID &nbsp; : &nbsp; {customer.emailId}
                      </h5>
                      <h5 className="card-text">
                        Mobile Number &nbsp; : &nbsp;{customer.mobileNumber}
                      </h5>
                      <h5 className="card-text">
                        Address &nbsp; : &nbsp;{customer.address}
                      </h5>
                      <div className="d-grid gap-2">
                        <button id="addbtn" className="btn btn-secondary" type="button">
                          <Link
                            id="addbtn"
                            style={{textDecoration:"none",color:"white"}}
                            to={`/customer/update/${customer.customerId}`}
                          >
                           <strong>Update</strong>
                          </Link>{" "}
                        </button>
                      </div>
                    </div>
                  </div>
                  </td>
                  </tr>
                </tbody>
              </table>
              ))}
        </div>
      </div>       
    )
    }
}
export default CustomerComponentAdmin;