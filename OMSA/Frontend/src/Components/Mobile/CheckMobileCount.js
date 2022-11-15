import 'bootstrap/dist//css/bootstrap.min.css';
import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Navbaradmin from '../Navbar/navbaradmin';
import MobileService from "./MobileService";
import {BsSearch, BsFillTrashFill} from "react-icons/bs";
import {AiOutlinePlus} from "react-icons/ai";
import {GrUpdate} from "react-icons/gr";
import "../CSSStyles/Styles.css";

function CheckMobileCount() {
    const {name} = useParams();
    const navigate= useNavigate();
    const [mobiles,setMobiles]= useState([]);
    
    //setting up the variables for filter and search
    const [mname,setMname]= useState("");
    const [cname,setCname]=useState("");
    const [cid,setId]= useState("1");
    const [min,setMin]= useState(5000);
    const [max,setMax]= useState(10000);

    const getMobileByName = async (e) => {
        const mobileInfo = await MobileService.getByName(name);
        setMobiles(mobileInfo.data);
    };

    //adding the mobiles from database on page load
    useEffect(() => {
        getMobileByName();
    });

    //function to delete the mobile
    const deleteMobile = (id) => {
        if (window.confirm("Click OK to confirm deletion from database !") === true){
          MobileService.deleteMobile(id).then(
            (response) => {
              alert("Record Deleted Successfully");
              getMobileByName();
            },
            (error) => {
              alert("Operation Failed Here");
            }
          );
        } else {
          alert("You have cancelled the request");
        }
    };

    //validation for search by price form
    const checkPrice= (e) => {
      e.preventDefault();
      if(min>=max){
        alert("Minimum price should be less than maximum price");
      }else {
        navigate(`/mobileadmin/byprice/${min}/${max}`);
      }
    }

    return(
        <div className='mystyle'>
        <Navbaradmin></Navbaradmin>
        <div className="col-6" style={{width:"50%",margin:"0 auto",boxShadow:"0px 2px 4px lightblue"}}>
          <details  className='card'>
            <summary>
            <label style={{color:"purple"}}>
                  &emsp;Click the arrow to search&emsp; <span id="search-addon">
                    <BsSearch/>
                  </span></label>
            </summary>
            <div className='input-group'>
            <select className='form-control rounded' style={{boxShadow:"0px 2px 4px lightblue"}} onChange={(e)=>setId(e.target.value)}>
                        <option value="1">Basic Phone</option>
                        <option value="2">Feature Phone</option>
                        <option value="3">Smart Phone</option>
            </select>
              <button className="btn btn-outline-primary" type="button" onClick={()=>{navigate(`/mobileadmin/bycategory/${cid}`)}}>
                  View by Category&emsp;
                  <span id="search-addon">
                    <BsSearch/>
                  </span>
              </button>
            </div><br/>
            <form className='input-group' onSubmit={()=>{navigate(`/mobileadmin/bycompany/${cname}`)}}>
            
            <input
                      type="text"
                      name="companyName"
                      placeholder="Enter company name here..."
                      required
                      className='form-control rounded'
                      style={{boxShadow:"0px 2px 4px lightblue"}}
                      aria-label="Search" aria-describedby="search-addon"
                      value={cname}
                      onChange={(e) =>setCname(e.target.value)}
            />
            <button className="btn btn-outline-primary" type="submit">
                  Search by Company&emsp;
                  <span id="search-addon">
                    <BsSearch/>
                  </span>
              </button>
              
            </form><br/>
            <form className='input-group' onSubmit={(e)=>{checkPrice(e)}}>
            <label className='form-control rounded'><strong>From:</strong>&emsp;</label>
            <input
                      type="number"
                      name="min"
                      className='form-control rounded'
                      placeholder="Enter minimum price"
                      style={{boxShadow:"0px 2px 4px lightblue"}}
                      required min="0" max="200000" step="1000"
                      value={min}
                      onChange={(e) =>setMin(e.target.value)}
            />
              <label className='form-control rounded'><strong>To:</strong>&emsp;</label>
            <input
                      type="number"
                      name="min"
                      className='form-control rounded'
                      placeholder="Enter maximum price"
                      style={{boxShadow:"0px 2px 4px lightblue"}}
                      required min="1000" max="200000" step="1000"
                      value={max}
                      onChange={(e) =>setMax(e.target.value)}
            />
              &emsp; &emsp;
              <button className="btn btn-outline-primary" type="submit">
                  Search by Price Range&emsp;
                  <span id="search-addon">
                    <BsSearch/>
                  </span>
              </button>
            </form><br/>
            <form className='input-group' onSubmit={()=>{navigate(`/mobileadmin/byname/${mname}`)}}>
            <input
                      type="text"
                      name="mobileName"
                      placeholder="Enter mobile name..."
                      required
                      className='form-control rounded'
                      style={{boxShadow:"0px 2px 4px lightblue"}}
                      aria-label="Search" aria-describedby="search-addon"
                      value={mname}
                      onChange={(e) =>setMname(e.target.value)}
            />
              &emsp; &emsp;
              <button className="btn btn-outline-primary" type="submit">
                  Check Mobile Count&emsp;
                  <span id="search-addon">
                    <BsSearch/>
                  </span>
              </button>
            </form><br/>
          </details>
        </div>
        <Head name={name} len={mobiles.length}></Head>
        <div className="addbutton">
            <button id="addbtn" className="btn btn-light"type="button" onClick={()=>{navigate('/mobileadmin/addmobile')}}>
                Add Mobiles<AiOutlinePlus style={{paddingBottom:"5px",verticalAlign:"middle"}}/>
            </button>
        </div>
        <div className='row row-cols-3'>
        {mobiles.length === 0
            ? <div className='col-sm-12' style={{color:"white"}}>No Record</div>
            : mobiles.map((mobile, index) => (
                <div className='col'>
                  <div className="card" style={{ margin: "2rem" }} key={mobile.mobileId}>
                    <div className="card-body" style={{ color: "purple" }}>
                      <h5 className="card-title">{index + 1}</h5>
                      <h5 className="card-title">
                        Mobile Name &nbsp; :&nbsp;{mobile.mobileName}
                      </h5>
                      <h5 className="card-text">
                        Mobile Cost &nbsp; :&nbsp;₹ {mobile.mobileCost}
                      </h5>
                      <h5 className="card-text">
                        Manufactured Date &nbsp; :&nbsp;{mobile.mfd}
                      </h5>
                      <h5 className="card-text">
                        Model Number &nbsp; :&nbsp;{mobile.modelNumber}
                      </h5>
                      <h5 className="card-text">
                        Company Name &nbsp; :&nbsp;{mobile.companyName}
                      </h5>
                      <h5 className="card-text">
                        Category &nbsp; :&nbsp;{mobile.category.categoryName}
                      </h5>
                      <div className="d-grid gap-4">
                        <button id="addbtn" className="btn btn-info" type="button" onClick={()=>{navigate(`/mobileadmin/update-mobile/${mobile.mobileId}`)}}>
                            Update&thinsp;<GrUpdate style={{paddingBottom:"3px",verticalAlign:"middle"}}/>
                        </button>
                        <button className="btn btn-warning" onClick={() => {deleteMobile(mobile.mobileId);}}>
                          Delete&thinsp; <BsFillTrashFill style={{paddingBottom:"3px",verticalAlign:"middle"}}/>
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
              ))}
        </div>
        </div>
    )
}
//To return appropriate response as header
function Head(props){
  if(props.len===0){
    return(<h3 style={{color:"white",fontFamily:"cursive"}}>No record for {props.name} present in the database</h3>)
  }
  if(props.len===1){
    return(<h3 style={{color:"white",fontFamily:"cursive"}}>Currently 1 record for {props.name} is present in the database</h3>)
  }
  return(<h3 style={{color:"white",fontFamily:"cursive"}}>Currently {props.len} records for {props.name} are present in the database</h3>)
}
export default CheckMobileCount;