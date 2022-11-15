
import "./login.css"
import { useState } from "react"
import axios from "axios";
import { useNavigate } from "react-router-dom";
import 'bootstrap/dist//css/bootstrap.min.css'
import UserService from "../User/UserService";
//import { Link } from "react-router-dom";

 function LoginAndRegister () {
  let [authMode, setAuthMode] = useState("signin")
  const [isShown, setIsSHown] = useState(false);
  const togglePassword = () => {
    setIsSHown((isShown) => !isShown);
  };

  //change handler
  const changeAuthMode = (e) => {
    setAuthMode(authMode === "signin" ? "signup" : "signin")
  }

  //Navigation for the other the components
  const navigate = useNavigate();

  //object to pass to the database
  const [user, setUser] = useState({
    userName: "",
    userPassword: "",
    userRole: "Customer",
  });

  const {userName,userPassword,userRole}=user;

  //Change Handler
  const changeHandler=(e)=>{
    setUser({...user,[e.target.name]:e.target.value})
  }

  //submit handler
  const FormHandle = (e) => {
    setUser({...user, userRole:"Customer"});
    console.log(user);
    e.preventDefault();
    addDataToServer(user);
  };

  //adding to the database
  const addDataToServer = (data) => {
    axios.post("http://localhost:8289/user/register", data).then(
      (response) => {
        console.log(response);
        alert("You have registered as a customer successfully!! Now, proceed to fill more details.");
        navigate("/register");
      },
      (error) => {
        console.log(error.response.data);
        alert(error.response.data.exceptionMessage);
      }
    );
  };

  //submit handler
  const submitHandler=(e)=>{
    e.preventDefault()
    UserService.validateUser(user.userName,user.userPassword).then(
      (response) => {
        if(response.data==="Correct Credentials"){
          alert("Login Successful");
          if(user.userRole==="Admin"){
            navigate('/homeadmin');
          } else {
            navigate('/home');
          }
          
        } else if(response.data==="Wrong credentials"){
          alert("Wrong password");
        }
      },
      (error) => {
        console.log(error.response.data);
        alert(error.response.data.exceptionMessage);
      }
    )

  }
  

  if (authMode === "signin") {
    return (
      <div style={{position:"fixed",top:"0"}}>
      <div className="Auth-form-container" >
        <form className="Auth-form" onSubmit={(e)=>submitHandler(e)}>
          <div className="Auth-form-content">
            <h3 className="Auth-form-title">Sign In</h3>
            <div className="text-center">
              Not registered yet?{" "}
              <span className="link-primary" onClick={(e)=>changeAuthMode(e)}>
                Sign Up
              </span>
            </div>
            <div className="form-group mt-3">
              <label>Username</label>
              <input
                type="name"
                name="userName" 
                value={userName} 
                onChange={(e)=>changeHandler(e)}
                className="form-control mt-3"
                minLength="2"
                style={{margin:"0 auto",width:"50%"}}
                placeholder="Enter username..."
              />
            </div>
            <div className="form-group mt-3">
              <label>Password</label>
              <input
                type={isShown ? "text" : "password"}
                name="userPassword" 
                value={userPassword} 
                onChange={(e)=>changeHandler(e)}
                className="form-control mt-3"
                minLength="8"
                style={{margin:"0 auto",width:"50%"}}
                placeholder="Enter your password..."
              />
            </div>
            <div className="form-group mt-3">
          <label>Show password? &thinsp;</label>
          <input
            id="checkbox"
            type="checkbox"
            checked={isShown}
            onChange={togglePassword}
          />
        </div>
            <div className="form-group mt-3">
            <label>Select Role: &emsp; &emsp;</label>
            <select name="userRole" onChange={(e) => changeHandler(e)}>
                        <option value="Customer">Customer</option>
                        <option value="Admin">Admin</option>
            </select>
          </div>
            <div className="d-grid gap-2 mt-3">
              <button type="submit" className="btn btn-primary" style={{margin:"0 auto",width:"50%"}}>
                Login
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
    )
  }

  return (
    <div style={{position:"fixed",top:"0"}}>
    <div className="Auth-form-container">
      <form className="Auth-form" onSubmit={(e)=>FormHandle(e)}>
        <div className="Auth-form-content">
          <h3 className="Auth-form-title">Sign Up</h3>
          <div className="text-center">
            Already registered?{" "}
            <span className="link-primary" onClick={(e)=>changeAuthMode(e)}>
              Sign In
            </span>
          </div>
          <div className="form-group mt-3">
            <label>Username</label>
            <input
              type="name"
              name="userName" 
              value={userName} 
              onChange={(e)=>changeHandler(e)}
              className="form-control mt-1"
              minLength="2"
              style={{margin:"0 auto",width:"50%"}}
              placeholder="e.g Jane Doe"
            />
          </div>
          <div className="form-group mt-3">
            <label>Password</label>
            <input
              type={isShown ? "text" : "password"}
              name="userPassword" 
              value={userPassword} 
              onChange={(e)=>changeHandler(e)}
              className="form-control mt-1"
              minLength="8"
              style={{margin:"0 auto",width:"50%"}}
              placeholder="Password"
            />
          </div>
          <div className="form-group mt-3">
          <label>Show password? &thinsp;</label>
          <input
            id="checkbox"
            type="checkbox"
            checked={isShown}
            onChange={togglePassword}
          />
        </div>
          <br/>
          <div className="d-grid gap-2 mt-3">
            <button  type="submit" className="btn btn-primary" style={{margin:"0 auto",width:"50%"}}>
                Register
            </button>
          </div>
        </div>
      </form>
    </div>
    </div>
  )
}

export default LoginAndRegister;