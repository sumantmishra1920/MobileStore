import { Component } from "react";
import axios from "axios";

//calling the backend methods
class OrderService extends Component{

    //showing all the orders
    showAllOrders(){
        return axios.get('http://localhost:8289/order/showAll');
    }

    //update the order
    updateOrder=(id)=>{
        return axios.get(`http://localhost:8289/order/update/${id}`);
    }

    //cancel the order
    cancelOrder=(id)=>{
        return axios.put(`http://localhost:8289/order/remove/${id}`);
    }

    //show order by id
    showByCustomerId=(id)=>{
        return axios.get(`http://localhost:8289/order/showbyCustomer/${id}`);
    }

    //updating the dispatchDate
    updateDispatchDate=(id,dispatchDate)=>{
        return axios.put(`http://localhost:8289/order/updateDispatchDate/${id}/${dispatchDate}`);
    }

    //Updating the status
    updateStatus=(id,status)=>{
        return axios.put(`http://localhost:8289/order/updateStatus/${id}/${status}`);
    }
    
    
}
export default new OrderService();