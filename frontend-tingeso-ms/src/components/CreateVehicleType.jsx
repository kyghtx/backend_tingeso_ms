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
        
      </Box>
      <VehicleTypesList />
    </>
  );
}
