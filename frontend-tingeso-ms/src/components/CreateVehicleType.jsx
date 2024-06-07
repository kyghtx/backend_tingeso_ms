import React, { useState } from "react";
import gestionService from "../services/gestion-service";
import {
  Container,
  TextField,
  Table,
  TableHead,
  TableCell,
  TableRow,
  Box,
  Button,
} from "@mui/material";
import { useEffect } from "react";
import BrandList from "./BrandList";
import VehiclesList from "./VehiclesList";
import VehicleTypesList from "./VehicleTypesLists";
import NavBarVehicles from "./NavBarVehicles";

export default function CreateVehicleType() {
  const [vehicle_type_name, setType] = useState("");

  async function handleCreateVehicleType(event) {
    event.preventDefault();
    try {
      const response = await gestionService.createVehicleType({
        vehicle_type_name,
      });
      setType("");
      alert("Tipo de vehiculo creado con éxito");
    } catch (error) {
      console.log(error);
      alert("Error al crear el nuevo tipo de vehiculo (Ya existe)");
    }
  }

  return (
    <>
      <Box className="containerBrands">
        <NavBarVehicles />

        <Box className="containerFormBrands">
          <h1 className="CreateVehicleForm">Crear tipo de vehiculo</h1>
          <form
            className="border row g-3 px-4"
            onSubmit={handleCreateVehicleType}
          >
            <Container className="col-12">
              <TextField
                id="Outlined"
                label="Ingrese nombre de tipo (ej: sedan)"
                InputLabelProps={{
                  style: { color: "#fff" },
                }}
                sx={{ input: { color: "white" } }}
                value={vehicle_type_name}
                onChange={(event) => setType(event.target.value)}
              />
            </Container>

            <Container className="col-12 mt-4 mb-4">
              <Button type="submit" className="btn btn-primary">
                Crear tipo de vehiculo
              </Button>
            </Container>
          </form>
        </Box>
      </Box>
      <VehicleTypesList />
    </>
  );
}
