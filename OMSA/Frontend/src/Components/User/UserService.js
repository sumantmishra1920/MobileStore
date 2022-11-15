import React, { Component } from "react";
import axios from "axios";

//calling the backend methods
class UserService extends Component{

    //showing all the users
    showAll(){
        return axios.get(`http://localhost:8289/user/showAll`);
    }

    //Adding the users
    addUser(){
        return axios.get(`http://localhost:8289/user/register`);
    }

    //Update the user
    updateUser=(id)=>{
        return axios.get(`http://localhost:8289/user/update/${id}`);
    }

    //Delete the user
    deleteUser=(id)=>{
        return axios.get(`http://localhost:8289/user/delete/${id}`);
    }

    //validate the user
    validateUser=(name,password)=>{
        return axios.get(`http://localhost:8289/user/validate?name=${name}&password=${password}`);
    }

    
}
export default  new UserService();