import CartService from './CartService';
import React, { Component } from 'react';
import axios from "axios";
import Navbarcust from "../Navbar/navbarcustomer";
import Button from 'react-bootstrap/Button';
import { BsFillTrashFill } from 'react-icons/bs';           //Dustbin icon
import {
  MDBCard,
  MDBCardBody,
  MDBCardImage,
  MDBCol,
  MDBContainer,
  MDBRow,
  MDBTypography,
} from "mdb-react-ui-kit";                                  //Material Design for bootstrap 
import "../CSSStyles/cart.css";
import Footer from "../Footer/Footer";

export default class UserCartComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {                                          //Initial state
      mobiles: [],
      cartInfo: []
    };
  }
  componentDidMount() {
    CartService.getMobiles().then((response) => {           //Updating the status of mobiles
      this.setState({ mobiles: response.data })
    });
    CartService.getViewCartInfo().then((response) => {      //Updating the status of cart
      this.setState({ cartInfo: response.data })
    });
  }
  deleteAll = (cartId) => {                                 //Function to Empty the Cart
    axios.delete(`http://localhost:8289/cart/removeAll/${cartId}`).then((response) => {
      CartService.getViewCartInfo().then((response) => {
        this.setState({ cartInfo: response.data })
      });
      this.setState({
        mobiles: []
      });
    },
      (error) => {
        alert("Could not empty the cart");
      }
    );
  }
  deleteMobile = (cartId, mobileId) => {                      //Function to Delete a mobile from Cart
    axios.delete(`http://localhost:8289/cart/remove/${cartId}/${mobileId}`).then((response) => {
      CartService.getViewCartInfo().then((response) => {
        this.setState({ cartInfo: response.data })
      });
      this.setState({
        mobiles: this.state.mobiles.filter(
          (mobile) => mobile.mobileId !== mobileId
        ),
      });
    },
      (error) => {
        alert("Could not delete the mobile");
      }
    );
  }
  placeOrder = (cartId, mobileId) => {                        //Function to place an order from cart
    if (window.confirm("Click OK to confirm the order !") === true) {
      axios.post(`http://localhost:8289/cart/placeOrder/${cartId}/${mobileId}`).then((response) => {
        alert("Order has been placed successfully");
        CartService.getViewCartInfo().then((response) => {
          this.setState({ cartInfo: response.data })
        });
        this.setState({
          mobiles: this.state.mobiles.filter(
            (mobile) => mobile.mobileId !== mobileId
          ),
        });
      },
        (error) => {
          alert("Could not place the order");
        }
      );
    }
    else {
      alert("You cancelled the request !");
    }
  };
  render() {
    return (
      <>
        <Navbarcust></Navbarcust>
        <div style={{ backgroundImage: "linear-gradient(rgb(19, 19, 102), rgb(31, 31, 156), rgb(44, 44, 211))", height: "100vh" }}>
          <h3 className="text-center" style={{ color: "white" }}>Shopping Cart</h3>
          <div className="container-fluid">
            <section>
              <MDBContainer >
                <MDBRow className="justify-content-center align-items-center">

                  <MDBCol>
                    <MDBCard>
                      <MDBCardBody className="p-4">
                        <MDBRow>
                          <MDBCol lg="12">
                            <div className="d-flex justify-content-between align-items-center mb-4">
                              <div>
                                <h5 className="mb-0">You have {this.state.cartInfo.quantity} items in your cart</h5>
                              </div>
                            </div>

                            {
                              this.state.mobiles.map((mobile) =>
                                <MDBCard className="mb-3">
                                  <MDBCardBody>
                                    <div key={mobile.mobileId} className="d-flex justify-content-between">
                                      <div className="d-flex flex-row align-items-center">
                                        <div>
                                          <MDBCardImage
                                            src={"/images/" + mobile.mobileName + ".jpg"}
                                            fluid className="rounded-3" style={{ width: "65px" }}
                                            alt="Image not available" />
                                        </div>
                                        <div className="ms-3 alignLeft">
                                          <MDBTypography tag="h5">
                                            {mobile.mobileName}
                                          </MDBTypography>
                                          <Button variant="success" onClick={() => {
                                            this.placeOrder(this.state.cartInfo.cartId, mobile.mobileId);
                                          }}>
                                            Proceed to Buy
                                          </Button>
                                        </div>
                                      </div>

                                      <div className="d-flex flex-row align-items-center">
                                        <div style={{ width: "80px" }}>
                                          <MDBTypography tag="h5" className="mb-0">
                                            ₹{mobile.mobileCost}
                                          </MDBTypography>
                                        </div>
                                        <Button variant="warning" onClick={() => {
                                          this.deleteMobile(this.state.cartInfo.cartId, mobile.mobileId);
                                        }}>
                                          <BsFillTrashFill />
                                        </Button>
                                      </div>
                                    </div>
                                  </MDBCardBody>
                                </MDBCard>)
                            }



                            <MDBCard className="mb-3">
                              <MDBCardBody>
                                <div className="d-flex justify-content-between">
                                  <div className="d-flex flex-row align-items-center">
                                    <div className="ms-3">
                                      <MDBTypography tag="h5">
                                        Total Cost
                                      </MDBTypography>
                                    </div>
                                  </div>
                                  <div className="d-flex flex-row align-items-center">
                                    <div style={{ width: "80px" }}>
                                      <MDBTypography tag="h5" className="mb-0">
                                        ₹{this.state.cartInfo.cost}
                                      </MDBTypography>
                                    </div>
                                  </div>
                                </div>
                              </MDBCardBody>
                            </MDBCard>

                            <Button variant="danger" onClick={() => {
                              this.deleteAll(this.state.cartInfo.cartId);
                            }}>
                              Delete All
                            </Button>
                          </MDBCol>
                        </MDBRow>
                      </MDBCardBody>
                    </MDBCard>
                  </MDBCol>
                </MDBRow>
              </MDBContainer>
            </section>
          </div>
        </div>
        <Footer />
      </>
    )
  }
}
