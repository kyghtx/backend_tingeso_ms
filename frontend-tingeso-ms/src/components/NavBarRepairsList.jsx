import { Button } from "@mui/material";
import { Link } from "react-router-dom";

function NavBarRepairsList() {
  return <nav className="NavBarVehicles">
          <Button className="nav-item">
            <Link className="nav-link" to="/api/repairs_types">
              Tipos Reparacion
            </Link>
          </Button>
          <Button className="nav-item">
            <Link className="nav-link" to="/api/repairs_types/repairs_prices">
              Precios
            </Link>
          </Button>
    </nav>;
}

export default NavBarRepairsList;