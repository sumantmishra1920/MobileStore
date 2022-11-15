import OrderService from "./OrderService";
import 'bootstrap/dist//css/bootstrap.min.css';
import React, { Component } from "react";
import Navbaradmin from "../Navbar/navbaradmin";
import { Link } from "react-router-dom";
import "../CSSStyles/Styles.css";

//showing all the orders
class OrderComponentAdmin extends Component{
    constructor(props){
        super(props);
        this.state = {
            order: [],
        };
    }

    //showing all orders of customers
    componentDidMount(){
        OrderService.showAllOrders().then((Response) => {
            this.setState({order:Response.data});
        });
    }

    render(){
        return(
            <div className="mystyle">
                <Navbaradmin />
                <h3 style={{color:"white",fontFamily:"cursive"}}>Orders List</h3>
                <div className="container">
          {this.state.order.length === 0
            ? "No Record "
            : this.state.order.map((order, index) => (
                <div
                  className="card"
                  style={{ margin: "2rem", boxShadow:"2px 2px 5px lightblue"}}
                  key={order.id}
                >
                  <div className="jumbotron">
                    <div className="card-body" style={{ color: "black" }}>
                      <h5 className="card-title">{index + 1}</h5>
                      <h5 className="card-title">
                        Order Id:&nbsp;{order.orderId}
                      </h5>
                      <h5 className="card-text">
                        Order Date:&nbsp;{order.orderDate}
                      </h5>
                      <h5 className="card-text">
                        Dispatch Date:&nbsp;{order.dispatchDate}
                      </h5>
                      <h5 className="card-text">
                        Cost:&nbsp;₹{order.cost}
                      </h5>
                      <h5 className="card-text">
                        Total Cost:&nbsp;₹{order.totalCost}
                      </h5>
                      <h5 className="card-text">
                        Status:&nbsp;{order.status}
                      </h5>
                      <h5 className="card-text">
                        Customer:&nbsp;{order.customer.customerName}
                      </h5>
                      <h5 className="card-text">
                        Mobile:&nbsp;{order.mobile.mobileName}
                      </h5>
                      <div className="d-grid gap-2">
                      <button id="addbtn" className="btn btn-primary" type="button">
                          <Link
                            id="addbtn"
                            to={`/order/update/${order.orderId}`}
                            style={{textDecoration:"none",color:"white"}}
                          >
                           Update Order
                          </Link>
                        </button>
                      </div>
                      </div>
                  </div>
                </div>
              ))}
        </div>
        </div>
        )};
            }
    

export default OrderComponentAdmin;