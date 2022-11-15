import axios from 'axios'
const CARTS_GET_API_URL = "http://localhost:8289/cart/showAll";
const MOBILES_GET_API_URL = "http://localhost:8289/cart/viewAllMobiles/1";
const VIEWCARTINFO_GET_API_URL = "http://localhost:8289/cart/show/1";

class CartService {
    getCarts() {                                                //Retrieval of list of carts from the Database
        return axios.get(CARTS_GET_API_URL);
    }
    getMobiles() {                                              //Retrieval of list of mobiles in a Cart
        return axios.get(MOBILES_GET_API_URL);
    }
    getViewCartInfo() {                                         //Retrieval of Cart information
        return axios.get(VIEWCARTINFO_GET_API_URL);
    }
}
export default new CartService();