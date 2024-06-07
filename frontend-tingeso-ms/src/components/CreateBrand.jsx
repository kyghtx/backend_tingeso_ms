import React, { useState,useRef } from "react";
import gestionService from "../services/gestion-service";
import { Container, TextField, Box, Button } from "@mui/material";
import BrandList from "./BrandList";
import NavBarVehicles from "./NavBarVehicles";



export default function CreateBrand() {
  const [brand_name, setBrandName] = useState("");
  async function handleCreateBrand(event) {
    event.preventDefault();
    try {
      const response = await gestionService.createBrand({
        brand_name,
      });
      setBrandName("");
      alert("Marca creada con éxito");
      
    } catch (error) {
      console.log(error);
      alert("Error al crear la nueva marca(Ya existe)");
    }
  }

  return (
    <>
    <Box className="containerBrands">
    <NavBarVehicles/>
      <Box className="containerFormBrands">
        <h1 className="CreateVehicleForm">Crear Marca</h1>
        <form className="border row g-3 px-4" onSubmit={handleCreateBrand}>
          <Container className="col-12">
            <TextField
              id="Outlined"
              label="Ingrese nombre de marca"
              InputLabelProps={{
                style: { color: '#fff' },
              }}
              sx={{ input: { color: "white" } }}
              value={brand_name}
              onChange={(event) => setBrandName(event.target.value)} />
          </Container>

          <Container className="col-12 mt-4 mb-4">
            <Button type="submit" className="btn btn-primary">
              Crear Marca
            </Button>
          </Container>
        </form>
      </Box>

    </Box>
      <BrandList /></>

  );
}
