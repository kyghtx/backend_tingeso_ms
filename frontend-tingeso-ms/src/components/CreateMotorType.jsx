import React, { useState } from "react";
import gestionService from "../services/gestion-service";
import { Container, TextField,Table,TableHead,TableCell,TableRow, Box, Button} from "@mui/material";
import MotorTypesList from "./MotorTypesList";
import NavBarVehicles from "./NavBarVehicles";

export default function CreateMotorType() {
  const [motor_type_name, setMotorName] = useState("");

  async function handleCreateMotorType(event) {
    event.preventDefault();
    try {
      const response = await gestionService.createMotorType({
        motor_type_name,
      });
      setMotorName("");
      alert("Tipo de motor creado con éxito");
    } catch (error) {
      console.log(error);
      alert("Error al crear el nuevo tipo de motor");
    }
  }

  return (
    <><Box className="containerBrands">
      <NavBarVehicles/>
    
    </Box>
    <MotorTypesList /></>
    
  );
}