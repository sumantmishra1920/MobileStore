import React, { Component } from "react";
import Navbaradmin from "../Navbar/navbaradmin";
import axios from 'axios';
import UserService from "./UserService";
import { Link } from "react-router-dom";
import 'bootstrap/dist//css/bootstrap.min.css'
import "./usecss.css"

//List of Users
class UserComponentAdmin extends Component{
     // Step 1:
  constructor(props) {
    super(props);
    this.state = {
      user: [],
    };
  }

  //showing all the users list
  //Step 2:
  componentDidMount() {
    UserService.showAll().then((Response) => {
      this.setState({ user: Response.data });
    });
  }
  
  //Deleting the user
  deleteUser = (id) => {
    axios
      .delete(`http://localhost:8289/user/delete/${id}`)
      .then(
        (response) => {
          alert("Record Deleted Successfully");
          this.setState({
            user: this.state.user.filter(
              (user) => user.userId !== id
            ),
          });
        },
        (error) => {
          alert("Operation Failed Here");
        }
      );
  };

render(){
        return(
            <div className="mic">
                <Navbaradmin/>
                <h3 style={{paddingBottom:"10px",paddingTop:"10px",fontFamily:"cursive",color:"white"}}>Users List</h3>
                <div className="container" >
          
          <div >
            <button  type="button" className="btn btn-primary">
              <Link id="addbtn" to={"/adduser"}  style={{textDecoration:"none",color:"white"}}>
                Create User
              </Link>
            </button>
          </div>
          <br/>
          {this.state.user.length === 0
            ? "No Record "
            : this.state.user.map((user, index) => (
              <table class="table table-striped">
                <tbody>
                  <tr>
                    <td>
                <div className="card " key={user.id}>
                  <br/>
                    <div className="card-body" style={{ color: "black" }}>
                      <h5 className="card-title">{index + 1}</h5>
                      <h5 className="card-title">
                        User Name &nbsp; :&nbsp; {user.userName}
                      </h5>
                      <h5 className="card-text">
                        User Password &nbsp; : &nbsp; {user.userPassword}
                      </h5>
                      <h5 className="card-text">
                        User Role &nbsp; : &nbsp;{user.userRole}
                      </h5>
                      <div className="d-grid gap-2">
                        <button id="addbtn" className="btn btn-info" type="button">
                          <Link
                            id="addbtn"
                            to={`/update-user/${user.userId}`}
                            style={{textDecoration:"none"}}
                          >
                           Update
                          </Link>{" "}
                        </button>
                        <button
                          id="addbtn"
                          className="btn btn-danger"
                          onClick={() => {
                            this.deleteUser(user.userId);
                          }}
                        >
                          Delete
                        </button>
                      </div>
                    </div>
                  </div>
                  </td>
                  </tr>
                </tbody>
              </table>
              ))}
        </div>
      </div>       
    )
    }
}
export default UserComponentAdmin;