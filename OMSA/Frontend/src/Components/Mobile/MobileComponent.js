import 'bootstrap/dist//css/bootstrap.min.css';
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Navbarcust from '../Navbar/navbarcustomer';
import { BsSearch, BsFillCartFill } from "react-icons/bs";
import { AiOutlineEye } from "react-icons/ai";
import MobileService from "./MobileService";
import "../CSSStyles/Styles.css";
import Footer from '../Footer/Footer';
function MobileComponent() {
  const navigate = useNavigate();
  const [mobiles, setMobiles] = useState([]);

  //setting up the variables for filter and search
  const [cname, setCname] = useState("");
  const [id, setId] = useState("1");
  const [min, setMin] = useState(5000);
  const [max, setMax] = useState(10000);

  //adding the mobiles from database on page load
  const getMobiles = async (e) => {
    const mobileInfo = await MobileService.getAllMobiles();
    setMobiles(mobileInfo.data);
  };
  useEffect(() => {
    getMobiles();
  }, []);

  //function to add to cart
  const add = (id) => {
    if (window.confirm("Click OK to confirm adding to cart !") === true) {
      MobileService.addToCart(1, id).then((response) => {
        console.log(response);
        alert("Mobile Added to Your Cart Successfully");
        navigate("/mobile");
      },
        (error) => {
          console.log(error.response.data);
          alert(error.response.data.exceptionMessage);
        }
      );
    } else {
      alert("You have cancelled the request");
    }

  };

  //validate view by price form
  const checkPrice = (e) => {
    e.preventDefault();
    if (min >= max) {
      alert("Minimum price should be less than maximum price");
    } else {
      navigate(`/mobile/byprice/${min}/${max}`);
    }
  }
  return (
    <div className='mystyle'>
      <Navbarcust></Navbarcust>
      <div className="col-6" style={{ width: "50%", margin: "0 auto", boxShadow: "0px 2px 4px lightblue" }}>
        <details className='card'>
          <summary>
            <label style={{ color: "purple" }}>
              &emsp;Click the arrow to search&emsp; <span id="search-addon">
                <BsSearch />
              </span></label>
          </summary>
          <div className='input-group'>
            <select className='form-control rounded' style={{ boxShadow: "0px 2px 4px lightblue" }} onChange={(e) => setId(e.target.value)}>
              <option value="1">Basic Phone</option>
              <option value="2">Feature Phone</option>
              <option value="3">Smart Phone</option>
            </select>
            <button className="btn btn-outline-primary" type="button" onClick={() => { navigate(`/mobile/bycategory/${id}`) }}>
              View by Category&emsp;
              <span id="search-addon">
                <BsSearch />
              </span>
            </button>
          </div><br />
          <form className='input-group' onSubmit={() => { navigate(`/mobile/bycompany/${cname}`) }}>

            <input
              type="text"
              name="companyName"
              placeholder="Enter company name here..."
              required
              className='form-control rounded'
              style={{ boxShadow: "0px 2px 4px lightblue" }}
              aria-label="Search" aria-describedby="search-addon"
              value={cname}
              onChange={(e) => setCname(e.target.value)}
            />
            <button className="btn btn-outline-primary" type="submit">
              Search by Company&emsp;
              <span id="search-addon">
                <BsSearch />
              </span>
            </button>

          </form><br />
          <form className='input-group' onSubmit={(e) => { checkPrice(e) }}>
            <label className='form-control rounded'><strong>From:</strong>&emsp;</label>
            <input
              type="number"
              name="min"
              className='form-control rounded'
              placeholder="Enter minimum price"
              style={{ boxShadow: "0px 2px 4px lightblue" }}
              required min="0" max="200000" step="1000"
              value={min}
              onChange={(e) => setMin(e.target.value)}
            />
            <label className='form-control rounded'><strong>To:</strong>&emsp;</label>
            <input
              type="number"
              name="min"
              className='form-control rounded'
              placeholder="Enter maximum price"
              style={{ boxShadow: "0px 2px 4px lightblue" }}
              required min="1000" max="200000" step="1000"
              value={max}
              onChange={(e) => setMax(e.target.value)}
            />
            &emsp; &emsp;
            <button className="btn btn-outline-primary" type="submit">
              Search by Price Range&emsp;
              <span id="search-addon">
                <BsSearch />
              </span>
            </button>
          </form><br />
        </details>
      </div>
      <h3 style={{ color: "white", fontFamily: "cursive" }}>All mobiles</h3>
      <div className="row row-cols-3">
        {mobiles.length === 0
          ? <div className='col-sm-12' style={{color:"white"}}>No Record</div>
          : mobiles.map((mobile, index) => (
            <div className="col">
              <div
                className="card"
                style={{ margin: "2rem", boxShadow: "2px 2px 5px lightblue" }}
                key={mobile.id}
              >
                <img className="center" src={"/images/" + mobile.mobileName + ".jpg"} alt="Not found" style={{ height: 125, width: 125 }}></img>
                <div>
                  <div className="card-body" style={{ color: "purple" }}>
                    <h5 className="card-title">
                      Mobile Name &nbsp; :&nbsp;{mobile.mobileName}
                    </h5>
                    <h5 className="card-text">
                      Mobile Cost &nbsp; :&nbsp;???{mobile.mobileCost}
                    </h5>
                    <h5 className="card-text">
                      Company Name &nbsp; :&nbsp;{mobile.companyName}
                    </h5>
                    <div className="d-grid gap-4">
                      <button id="addbtn" className="btn btn-info" type="button" style={{ display: "flex", justifyContent: "center", verticalAlign: "middle" }} onClick={() => { navigate(`/mobile/view-mobile/${mobile.mobileId}`) }}>
                        View Details<span><AiOutlineEye style={{ paddingBottom: "3px", verticalAlign: "middle" }} /></span>
                      </button>
                      <button className="btn btn-warning" type="button" style={{ display: "flex", justifyContent: "center", verticalAlign: "middle" }} onClick={() => { add(mobile.mobileId) }}>
                        Add To Cart&thinsp;<span ><BsFillCartFill style={{ paddingBottom: "3px", verticalAlign: "middle" }} /></span>
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          ))}
      </div>
      <Footer />
    </div>
  )
}

export default MobileComponent;