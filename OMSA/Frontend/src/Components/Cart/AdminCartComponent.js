import CartService from './CartService';
import React, { Component } from 'react';
import Navbarcust from "../Navbar/navbaradmin";

export default class CartComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {                                                //Initial state
            carts: []
        }
    }
    componentDidMount() {
        CartService.getCarts().then((response) => {                   //Updating status after retrieval of list of Carts
            this.setState({ carts: response.data })
        })
    }
    render() {
        return (
            <div>
                <Navbarcust></Navbarcust>
                <div style={{ backgroundImage: "linear-gradient(rgb(19, 19, 102), rgb(31, 31, 156), rgb(44, 44, 211))", height: "100vh" }}>
                    <h3 className="text-center" style={{ color: "white" }}>Carts List</h3>
                    <table className="table" style={{ color: "white" }}>
                        <thead>
                            <tr>
                                <td><b>Cart Id</b></td>
                                <td><b>Cost</b></td>
                                <td><b>Quantity</b></td>
                                <td><b>Customer Name</b></td>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.carts.map((cart) =>
                                    <tr key={cart.cartId}>
                                        <td>{cart.cartId}</td>
                                        <td>₹{cart.cost}</td>
                                        <td>{cart.quantity}</td>
                                        <td>{cart.customer.customerName}</td>
                                    </tr>)
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}
