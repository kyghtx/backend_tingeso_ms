import React, { useState } from "react";
import gestionService from "../services/gestion-service";
import { Container, TextField,Table,TableHead,TableCell,TableRow, Box, Button} from "@mui/material";
import RepairsTypeList from "./RepairTypeList";
import NavBarRepairsList from "./NavBarRepairsList";

export default function CreateRepairType() {
  const [repair_type_name, setRepairName] = useState("");
  //Posiblemente agregar descripcion
  const [description, setDescription] = useState("");

  async function handleCreateRepairType(event) {
    event.preventDefault();
    try {
      const response = await gestionService.createRepairType({
        description,
        repair_type_name,
      });
      setRepairName("");
      setDescription("");

      alert("Tipo de reparacion creada con éxito");
    } catch (error) {
      console.log(error);
      alert("Error al crear el nuevo tipo de reparacion");
    }
  }

  return (
    <><Box className="containerBrands">
      <NavBarRepairsList/>
      <Box className="containerFormBrands">
        <h1 className="CreateVehicleForm">Crear tipo de reparacion</h1>
        <form className="border row g-3 px-4" onSubmit={handleCreateRepairType}>
          <Container className="col-12">
            <TextField
              id="Outlined"
              label="Ingrese tipo de reparacion"
              InputLabelProps={{
                style: { color: '#fff' },
              }}
              sx={{ input: { color: "white" } }}
              value={repair_type_name}
              onChange={(event) => setRepairName(event.target.value)} />
          </Container>

          <Container className="col-12">
            <TextField
              id="Outlined"
              label="Ingrese descripcion"
              InputLabelProps={{
                style: { color: '#fff' },
              }}
              sx={{ input: { color: "white" } }}
              value={description}
              onChange={(event) => setDescription(event.target.value)} />
          </Container>

          <Container className="col-12 mt-4 mb-4">
            <Button type="submit" className="btn btn-primary">
              Crear tipo de reparacion
            </Button>
          </Container>
        </form>
      </Box>

    </Box>
    <RepairsTypeList /></>
    
  );
}