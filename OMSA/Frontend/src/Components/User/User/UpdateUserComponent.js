import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import 'bootstrap/dist//css/bootstrap.min.css'
import Navbarcust from "../Navbar/navbaradmin";
import "./usecss.css"

//Update Function
function UpdateUserComponent() {

  const { id } = useParams();
  const URL = `http://localhost:8289/user/update/${id}`;

  // Navigation for the other the components
  const navigate = useNavigate();

  //Variables of the User
  const [user, setUser] = useState({
    userName: "",
    userPassword: "",
    userRole: "Admin",
  });

  //storing the data in user
  const { userName, userPassword, userRole } = user;

  //Update the user variables
  const onInputChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const FormHandle = (e) => {
    e.preventDefault();
    if (userName.length <= 2) {
      alert("userName must be at least 2 character");
    }
    else if (userPassword.length <= 8) {
      alert("userPassword must be at least 8 character");
    }
    else if (userRole === "Customer" || userRole === "Admin") {
      alert("userRole must be customer");
    }
    else {
      console.log(user)
    }
    updateDataToServer(user);
  };

  //Update User Details
  const updateDataToServer = (data) => {
    axios.put(URL, data).then(
      (response) => {
        alert("User Updated Successfully");
        navigate("/useradmin");
      },
      (error) => {
        alert(error.response.data.exceptionMessage);
      }
    );
  };



  return (
    <div className="mic">
      <Navbarcust />
      <div className="container">
        <style>{'body { background-color: black; }'}</style>
        <div class="jumbotron text-center">
          <h1 style={{ color: "white" }}>Update User</h1>
        </div>

        <br />
        <table class="table table-striped">
          <tbody>
            <tr>
              <td>
                <form onSubmit={(e) => FormHandle(e)}>
                  <div className="myform-group">
                    <label><h3 style={{ color: "white" }}>User Name</h3></label>
                    <input
                      type="text"
                      className="form-control"
                      name="userName"
                      placeholder="Enter User Name"
                      required
                      value={userName}
                      onChange={(e) => onInputChange(e)}
                    />
                  </div>
                  <br />
                  <div className="myform-group">
                    <label><h3 style={{ color: "white" }}>User Password</h3> </label>
                    <input
                      type="password"
                      className="form-control"
                      name="userPassword"
                      placeholder="Enter User Password"
                      required
                      value={userPassword}
                      onChange={(e) => onInputChange(e)}
                    />
                  </div>
                  <br />
                  <div className="myform-group">
                    <label><h3 style={{ color: "white" }}>Role</h3></label><br />
                    <select name="userRole" onChange={(e) => onInputChange(e)}>
                      <option value="Admin">Admin</option>
                      <option value="Customer">Customer</option>
                    </select>
                  </div>
                  <br />
                  <div className="container text-center">
                    <button
                      id="addbtn"
                      type="submit"
                      className="btn btn-danger"
                    >
                      Update user
                    </button>
                  </div>
                </form>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default UpdateUserComponent;