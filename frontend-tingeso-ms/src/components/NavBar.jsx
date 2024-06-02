import { Button } from "@mui/material";
import React from "react";
import { Link } from "react-router-dom";

function NavBar() {
  return (
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
            <Link className="nav-link" to="/api/vehicles/MotorTypes">
              Vehiculos
            </Link>
          </Button>
    </nav>
  );
}

export default NavBar;
