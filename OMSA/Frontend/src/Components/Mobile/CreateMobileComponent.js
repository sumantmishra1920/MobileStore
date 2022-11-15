
import { useState } from "react";
import MobileService from "./MobileService";
import { useNavigate } from "react-router-dom";
import "../CSSStyles/Styles.css";
import 'bootstrap/dist//css/bootstrap.min.css';
import Navbaradmin from "../Navbar/navbaradmin";
import "../CSSStyles/Styles.css";

function CreateMobileComponent() {
  const navigate = useNavigate();
  //object to pass to the database
  const [mobile, setMobile] = useState({
    mobileName: "",
    mobileCost: "",
    mfd: "",
    modelNumber: "",
    companyName: "",
    category: {
      categoryId: 1,
      categoryName: "Basic Phone"
    }
  });
  //input handler
  const onInputChange = (e) => {
    setMobile({ ...mobile, [e.target.name]: e.target.value });
  };
  const {
    mobileName,
    mobileCost,
    mfd,
    modelNumber,
    companyName,
    category
  } = mobile;

  //submit handler
  const FormHandle = (e) => {
    e.preventDefault();
    addDataToServer(mobile);
  };

  //adding to the database
  const addDataToServer = (data) => {
    MobileService.addMobile(data).then(
      (response) => {
        console.log(response);
        alert("Mobile Added Successfully");
        navigate("/mobileadmin");
      },
      (error) => {
        console.log(error.response.data);
        alert("Operation failed");
      }
    );
  };

  //dropdown change handler
  const onDropdownChange = (e) => {
    if (e.target.value === "1") {
      setMobile({
        ...mobile, category: {
          categoryId: 1,
          categoryName: "Basic Phone"
        }
      });
    }
    if (e.target.value === "2") {
      setMobile({
        ...mobile, category: {
          categoryId: 2,
          categoryName: "Feature Phone"
        }
      });
    }
    if (e.target.value === "3") {
      setMobile({
        ...mobile, category: {
          categoryId: 3,
          categoryName: "Smart Phone"
        }
      });
    }
  };
  return (
    <div className="mystyle">
      <Navbaradmin />
      <div className="container">
        <div className="w-75 mx-auto shadow p-5 mt-2 bg-light">
          <div className="jumbotron">
            <h3 class="display-4 text-center" style={{ paddingBottom: "10px", paddingTop: "10px", fontFamily: "cursive" }} >Add Mobile</h3>
            <div>
              <form onSubmit={(e) => FormHandle(e)}>
                <div class="form-group">
                  <label for="exampleInputEmail1">Mobile Name</label>
                  <input
                    type="text"
                    class="form-control"
                    name="mobileName"
                    placeholder="Enter Mobile Name"
                    required autoFocus
                    style={{ boxShadow: "0px 2px 4px lightblue" }}
                    value={mobileName}
                    onChange={(e) => onInputChange(e)}
                  />
                </div>
                <div class="form-group">
                  <label for="exampleInputPassword1">Mobile Cost</label>
                  <input
                    type="number"
                    class="form-control"
                    name="mobileCost" inputMode="numeric"
                    placeholder="Enter Mobile Cost"
                    required min="500"
                    style={{ boxShadow: "0px 2px 4px lightblue" }}
                    defaultValue={mobileCost}
                    onChange={(e) => onInputChange(e)}
                  />
                </div>
                <div class="form-group">
                  <label for="exampleInputPassword1">Manufactured Date</label>
                  <input
                    type="date"
                    class="form-control"
                    name="mfd"
                    required
                    style={{ boxShadow: "0px 2px 4px lightblue" }}
                    value={mfd}
                    onChange={(e) => onInputChange(e)}
                  />
                </div>
                <div class="form-group">
                  <label for="exampleInputPassword1">Model Number</label>
                  <input
                    type="text"
                    class="form-control"
                    name="modelNumber"
                    placeholder="Enter Model Number"
                    required
                    style={{ boxShadow: "0px 2px 4px lightblue" }}
                    value={modelNumber}
                    onChange={(e) => onInputChange(e)}
                  />
                </div>
                <div class="form-group">
                  <label for="exampleInputPassword1">Company Name</label>
                  <input
                    type="text"
                    class="form-control"
                    name="companyName"
                    placeholder="Enter Company Name"
                    required
                    style={{ boxShadow: "0px 2px 4px lightblue" }}
                    value={companyName}
                    onChange={(e) => onInputChange(e)}
                  />
                </div>

                <div class="form-group">
                  <label for="exampleInputPassword1">Category  :&emsp;  </label>
                  <select style={{ boxShadow: "0px 2px 4px lightblue" }} className="form-select" onChange={(e) => onDropdownChange(e)}>
                    <option value="1">Basic Phone</option>
                    <option value="2">Feature Phone</option>
                    <option value="3">Smart Phone</option>
                  </select>
                </div>
                <br />
                <div className="container text-center">
                  <button
                    className="btn btn-primary"
                    type="submit"
                  >
                    Add Mobile
                  </button>
                  <br />
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
export default CreateMobileComponent;