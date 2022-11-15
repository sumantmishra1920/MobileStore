import { Component } from "react";
import axios from "axios";
class CustomerService extends Component{

    //Adding the customer
    addCustomer(){
        return axios.get('http://localhost:8289/customer/register');
    }

    //Updating the cutomer
    updateCustomer=(customerId)=>{
        return axios.get(`http://localhost:8289/customer/update/${customerId}`);
    }

    //List of all cutomers
    showAllCustomer(){
        return axios.get('http://localhost:8289/customer/showAll');
    }

    //showing the customer by id
    showByCustomerId=(customerId)=>{
        return axios.get(`http://localhost:8289/customer/show/${customerId}`);
    }
    
}
export default new CustomerService();