import { Outlet,Link } from "react-router-dom";
import NavBarVehicles from "./NavBarVehicles";
import logo from '../assets/logo.png';
import { GeneralNavBar } from "./GeneralNavBar";

function Layout() {
    return (  
    <div className = "Layout-Container">
      <header className="logo-container">
          <Link to="/"><img src={logo} alt="LogoAutofix" height="200px"/></Link>
            
          <h1></h1>
          <GeneralNavBar/>
      </header>
      
      <main className="route-container">
        <Outlet />
      </main>
    </div>
    );
}

export default Layout;