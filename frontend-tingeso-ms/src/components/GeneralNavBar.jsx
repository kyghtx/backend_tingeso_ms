import { Button } from "@mui/material";
import { Link } from "react-router-dom";

export function GeneralNavBar(){
    return(
        <nav className="NavBar">
          <Button className="nav-item" variant="text" size="large">
            <Link className="nav-link" to="/api/vehicles">
              Gestion Vehiculos
            </Link>
          </Button>
          <Button className="nav-item" variant="text" size="large">
            <Link className="nav-link" to="/api/repairs_types">
              Gestion de Reparaciones
            </Link>
          </Button>
          <Button className="nav-item" variant="text" size="large">
            <Link className="nav-link" to="/api/repair_vehicles/create">
              Ingresar Reparaciones
            </Link>
          </Button>
          <Button className="nav-item" variant="text" size="large">
            <Link className="nav-link" to="/api/vehicles">
              Reportes
            </Link>
          </Button>
    </nav>)

}