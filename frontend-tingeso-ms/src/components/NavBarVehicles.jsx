import { Button } from "@mui/material";
import { Link } from "react-router-dom";

function NavBarVehicles() {
  return <nav className="NavBarVehicles">
          <Button className="nav-item">
            <Link className="nav-link" to="/vehicles/Brands">
              Marcas
            </Link>
          </Button>
          <Button className="nav-item">
            <Link className="nav-link" to="/vehicles/Types">
              Tipos de vehiculos
            </Link>
          </Button>
          <Button className="nav-item">
            <Link className="nav-link" to="/vehicles/MotorTypes">
              Tipos de motor
            </Link>
          </Button>
          <Button className="nav-item">
            <Link className="nav-link" to="/vehicles">
              Vehiculos
            </Link>
          </Button>
    </nav>;
}

export default NavBarVehicles;