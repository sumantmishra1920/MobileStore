import { BrowserRouter, Route, Routes } from "react-router-dom";

import MobileComponent from "./Components/Mobile/MobileComponent";
import MobileComponentAdmin from "./Components/Mobile/MobileComponentAdmin";
import CreateMobileComponent from "./Components/Mobile/CreateMobileComponent";
import UpdateMobileComponent from "./Components/Mobile/UpdateMobileComponent";
import SearchByCategoryComponent from "./Components/Mobile/SearchByCategoryComponent";
import SearchByCategoryComponentAdmin from "./Components/Mobile/SearchByCategoryComponentAdmin";
import SearchByCompanyName from "./Components/Mobile/SearchByCompanyName";
import SearchByCompanyNameAdmin from "./Components/Mobile/SearchByCompanyNameAdmin";
import ViewMobileComponent from "./Components/Mobile/ViewMobileComponent";
import SearchByPriceRange from "./Components/Mobile/SearchByPriceRange";
import SearchByPriceRangeAdmin from "./Components/Mobile/SearchByPriceRangeAdmin";
import CheckMobileCount from "./Components/Mobile/CheckMobileCount";

import CustomerComponent from "./Components/Customer/CustomerComponent";
import CustomerComponentAdmin from "./Components/Customer/CustomerComponentAdmin";
import UpdateCustomerComponent from "./Components/Customer/UpdateCustomerComponent";
import UpdateCustomer from "./Components/Customer/UpdateCustomer";
import CreateCustomerComponent from "./Components/Customer/CreateCustomerComponent";
import CreateCustomer from "./Components/Login/CreateCustomer";

import AdminCartComponent from "./Components/Cart/AdminCartComponent";
import UserCartComponent from "./Components/Cart/UserCartComponent";

import OrderComponent from "./Components/Order/OrderComponent";
import OrderComponentAdmin from "./Components/Order/OrderComponentAdmin";
import UpdateOrderComponent from "./Components/Order/UpdateOrderComponent";

import UserComponent from "./Components/User/UserComponent";
import UserComponentAdmin from "./Components/User/UserComponentAdmin";
import UpdateUserComponent from "./Components/User/UpdateUserComponent";
import CreateUserComponent from "./Components/User/CreateUserComponent";


import AdminHome from "./Components/Home Page/AdminHome";
import CustomerHome from "./Components/Home Page/CustomerHome";
import LoginAndRegister from "./Components/Login/LoginAndRegister";
import './App.css';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
      <Routes>
          <Route path="/" exact element={<LoginAndRegister />}></Route>
          <Route path="/home" exact element={<CustomerHome/>}></Route>
          <Route path="/homeadmin" exact element={<AdminHome/>}></Route>
          <Route path="/login" exact element={<LoginAndRegister />}></Route>


          <Route path="/mobile" exact element={<MobileComponent />}></Route>
          <Route path="/mobileadmin" exact element={<MobileComponentAdmin />}></Route>
          <Route path="/mobileadmin/addmobile" exact element={<CreateMobileComponent />}></Route>
          <Route path="/mobileadmin/update-mobile/:id" exact element={<UpdateMobileComponent />}></Route>
          <Route path="/mobile/view-mobile/:id" exact element={<ViewMobileComponent />}></Route>
          <Route path="/mobile/bycategory/:id" exact element={<SearchByCategoryComponent />}></Route>
          <Route path="/mobileadmin/bycategory/:id" exact element={<SearchByCategoryComponentAdmin />}></Route>
          <Route path="/mobile/bycompany/:name" exact element={<SearchByCompanyName />}></Route>
          <Route path="/mobileadmin/bycompany/:name" exact element={<SearchByCompanyNameAdmin />}></Route>
          <Route path="/mobile/byprice/:minp/:maxp" exact element={<SearchByPriceRange />}></Route>
          <Route path="/mobileadmin/byprice/:minp/:maxp" exact element={<SearchByPriceRangeAdmin />}></Route>
          <Route path="/mobileadmin/byname/:name" exact element={<CheckMobileCount />}></Route>

          <Route path="/customer" exact element={<CustomerComponent />}></Route>
          <Route path="/customeradmin" exact element={<CustomerComponentAdmin />}></Route>
          <Route path="/customer/register" exact element={<CreateCustomerComponent />}></Route>
          <Route path="/register" exact element={<CreateCustomer />}></Route>
          <Route path="/customer/update/:customerId" exact element={<UpdateCustomerComponent />}></Route>
          <Route path="/customer/updatecustomer/:customerId" exact element={<UpdateCustomer />}></Route>

          <Route path="/cart" exact element={<UserCartComponent />}></Route>
          <Route path="/cartadmin" exact element={<AdminCartComponent />}></Route>

          <Route path="/order" exact element={<OrderComponent />}></Route>
          <Route path="/orderadmin" exact element={<OrderComponentAdmin />}></Route>
          <Route path="/order/update/:id" exact element={<UpdateOrderComponent/>}></Route>

          <Route path="/user" exact element={<UserComponent />}></Route>
          <Route path="/useradmin" exact element={<UserComponentAdmin />}></Route>
          <Route path="/adduser" exact element={<CreateUserComponent/>}></Route>
          <Route path="/update-user/:id" exact element={<UpdateUserComponent />}></Route>
      </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
