import React, { Component } from "react";
import axios from "axios";
class MobileService{
    getAllMobiles() {
        return axios.get(`http://localhost:8289/mobile/showAll`);
    }
    getMobileById(id) {
        return axios.get(`http://localhost:8289/mobile/show/${id}`);
    }
    addMobile(mobile) {
        return axios.post('http://localhost:8289/mobile/add',mobile);
    }
    updateMobile(mobile,id) {
        return axios.put(`http://localhost:8289/mobile/update/${id}`,mobile);
    }
    deleteMobile= (id) => {
        return axios.delete(`http://localhost:8289/mobile/delete/${id}`);
    }
    getByName= (name) => {
        return axios.get(`http://localhost:8289/mobile/byName/${name}`);
    }
    countByName= (name) => {
        return axios.get(`http://localhost:8289/mobile/count/${name}`);
    }
    getByPriceRange= (min,max) => {
        return axios.get(`http://localhost:8289/mobile/byPriceRange?price1=${min}&price2=${max}`);
    }
    getByCategory= (id) => {
        return axios.get(`http://localhost:8289/mobile/showByCategory/${id}`);
    }
    getByCompany= (name) => {
        return axios.get(`http://localhost:8289/mobile/showByCompany/${name}`);
    }
    addToCart= (cid,id) => {
        return axios.put(`http://localhost:8289/mobile/addToCart/${cid}/${id}`);
    }
}
export default new MobileService()