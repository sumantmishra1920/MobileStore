import React, { Component } from "react";
import 'bootstrap/dist//css/bootstrap.min.css';
import axios from "axios";
import Navbarcust from "../Navbar/navbarcustomer";
import OrderService from "./OrderService";
import "../CSSStyles/Styles.css";
import Footer from "../Footer/Footer";

//Showing the order
class OrderComponent extends Component{
    constructor(props){
        super(props);
        this.state={
            order:[],
        };
    }

    //Showing all the orders
    componentDidMount(){
        OrderService.showByCustomerId(1).then((Response)=> {
            this.setState({order:Response.data});
        });
    }

    //Cancelling the order
    cancelOrder = (id) =>{
        if (window.confirm("Click OK to cancel your order !") === true){
            axios.put(`http://localhost:8289/order/cancel/${id}`).then(
            (response) => {
                alert("Order cancelled Successfully");
                OrderService.showByCustomerId(1).then((Response)=> {
                    this.setState({order:Response.data});
                });
            },
            (error) => {
                alert("Operation failed here");
            }
        );
        } else {
            alert("You have cancelled the request");
          }
        
    };
    
    render(){
        return(
            <div className="mystyle">
                <Navbarcust />
                <h3 style={{color:"white",fontFamily:"cursive"}}>Your Orders</h3>
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
                        Mobile Ordered:&nbsp;{order.mobile.mobileName}
                      </h5>
                      <div className="d-grid gap-2">
                        <button
                          id="addbtn"
                          className="btn btn-outline-primary"
                          onClick={() => {
                            this.cancelOrder(order.orderId);
                          }}
                        >
                          Cancel Order
                        </button>
                      </div>
                      </div>
                  </div>
                </div>
              ))}
        </div>
        <Footer/>
      </div>
        )
    }
}
export default OrderComponent;