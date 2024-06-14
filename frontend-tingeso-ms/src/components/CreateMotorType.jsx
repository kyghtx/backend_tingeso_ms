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
      <Box className="containerFormBrands">
        <h1 className="CreateVehicleForm">Crear tipo de motor</h1>
        <form className="border row g-3 px-4" onSubmit={handleCreateMotorType}>
          <Container className="col-12">
            <TextField
              id="Outlined"
              label="Ingrese tipo de motor (ej: Gasolina)"
              InputLabelProps={{
                style: { color: '#fff' },
              }}
              sx={{ input: { color: "white" } }}
              value={motor_type_name}
              onChange={(event) => setMotorName(event.target.value)} />
          </Container>

          <Container className="col-12 mt-4 mb-4">
            <Button type="submit" className="btn btn-primary">
              Crear tipo de motor
            </Button>
          </Container>
        </form>
      </Box>

    </Box>
    <MotorTypesList /></>
    
  );
}