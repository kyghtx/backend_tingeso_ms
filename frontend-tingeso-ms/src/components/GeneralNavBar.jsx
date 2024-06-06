import { Button } from "@mui/material";
import { Link } from "react-router-dom";

export function GeneralNavBar(){
    return(
        <nav className="NavBar">
          <Button className="nav-item">
            <Link className="nav-link" to="/api/vehicles/Brands">
              Marcas
            </Link>
          </Button>
          <Button className="nav-item">
            <Link className="nav-link" to="/api/vehicles/Types">
              Tipos de vehiculos
            </Link>
          </Button>
          <Button className="nav-item">
            <Link className="nav-link" to="/api/vehicles/MotorTypes">
              Tipos de motor
            </Link>
          </Button>
          <Button className="nav-item">
            <Link className="nav-link" to="/api/vehicles">
              Vehiculos
            </Link>
          </Button>
    </nav>)
    
}