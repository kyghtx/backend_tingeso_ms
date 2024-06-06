import { Outlet,Link } from "react-router-dom";
import NavBarVehicles from "./NavBarVehicles";
import logo from '../assets/logo.png';

function Layout() {
    return (  
    <div className = "Layout-Container">
      <header className="logo-container">
          <img src={logo} alt="LogoAutofix" height="200px"/>
          <h1><Link to="/">AUTOFIX</Link></h1>
          <NavBarVehicles/>
      </header>
      
      <main className="route-container">
        <Outlet />
      </main>
    </div>
    );
}

export default Layout;