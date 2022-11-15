import React,{useState} from 'react';
import axios from 'axios';
import Navbarcust from "../Navbar/navbaradmin";
import {useNavigate } from "react-router-dom";
import 'bootstrap/dist//css/bootstrap.min.css'
import "./usecss.css"

//Create User
function CreateUserComponent(){

  // Navigation for the other the components
  const navigate = useNavigate();

  //object to pass to the database
  const [user, setUser] = useState({
    userName: "",
    userPassword: "",
    userRole: "Admin",
  });


  //input handler
  const onInputChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  //storing the data in user
  const { userName, userPassword, userRole} = user;

  //submit handler
  const FormHandle = (e) => {
    e.preventDefault();
    if(userName.length<=2){
      alert("userName must be at least 2 character");
    }
    else if(userPassword.length<=8)
    {
      alert("userPassword must be at least 8 character");
    }
    else
    {
    console.log(user)
    addDataToServer(user);
    }
  };

  //Adding to the database
  const addDataToServer = (data) => {
    axios.post("http://localhost:8289/user/register", data).then(
      (response) => {
        console.log(response);
        alert("User Added Successfully");
        navigate("/useradmin");
      },
      (error) => {
        console.log(error);
        alert(error.response.data.exceptionMessage);
      }
    );
  };

return(
    <div className="mic">
       <Navbarcust/>
      <div className="container">
          <h1 class="primary" style={{color:"white"}}>New User</h1>
        </div>
            <br/>
          <table class="table table-striped" variant="dark">
            <tbody>
              <tr>
                <td>
            <div>
              <form onSubmit={(e) => FormHandle(e)}>
                
                <div className="myform-group">
                  <label for="exampleInputUserName">
                    <h3 style={{color:"white"}}>User Name</h3>
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    name="userName"
                    placeholder="Enter User Name Ex: Harry_Poter"
                    required
                    value={userName}
                    onChange={(e) => onInputChange(e)}
                  />
                </div>
                <br/>
                <div className="myform-group">
                  <label for="exampleInputPassword1">
                    <h3 style={{color:"white"}}>User Password</h3></label>
                  <input
                    type="password"
                    className="form-control"
                    name="userPassword"
                    placeholder="Enter User Password Ex:Harry@638"
                    required
                    value={userPassword}
                    onChange={(e) => onInputChange(e)}
                  />
                </div>
                <br/>
                <div className="myform-group">
                <label for="exampleInputRole">
                  <h3 style={{color:"white"}}>Role</h3></label><br/>
                    <select name="userRole" onChange={(e) => onInputChange(e)}>
                        <option value="Admin">Admin</option>
                        <option value="Customer">Customer</option>
                    </select>
                </div>
                <br/>
                <div className="container text-center">
                  <button
                    id="addbtn"
                    type="submit"
                    class="btn btn-primary"
                  >
                    Add User
                  </button>
                </div>
              </form>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
);
}
export default CreateUserComponent;