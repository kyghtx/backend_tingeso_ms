import * as React from "react";
import { useEffect, useState } from "react";
import gestionService from "../services/gestion-service";
import VehicleList from "./VehiclesList";
import { Container, TextField, Box, Button } from "@mui/material";
import Select from "@mui/material/Select";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import NavBarVehicles from "./NavBarVehicles";

export default function CreateVehicle() {
  /*Atributes of a vehicle for him creation*/
  const [brand_id, setBrand] = useState("");
  const [model, setModel] = useState("");
  const [motor_type_id, setMotor] = useState("");
  const [num_of_seats, setSeats] = useState("");
  const [patent, setPatent] = useState("");
  const [vehicle_type_id, setType] = useState("");
  const [year_of_manufacturing, setYear] = useState("");

  /*for save fecth of all necesary data for the creation of a vehicle*/
  const [vehicle_types, setTypes] = useState([]);
  const [brands, setBrands] = useState([]);
  const [vehicle_motor_types, setMotorTypes] = useState([]);

  async function fetchVehicleTypes() {
    try {
      const response = await gestionService.getVehicleTypes();
      setTypes(response.data);
    } catch (error) {
      alert("Hubo un error al obtener los tipos de vehiculos");
    }
  }

  useEffect(() => {
    fetchVehicleTypes();
  }, []);

  //fetch Brands

  async function fetchBrands() {
    try {
      const response = await gestionService.getBrands();
      setBrands(response.data);
    } catch (error) {
      alert("Hubo un error al obtener las marcas de vehiculos");
    }
  }

  useEffect(() => {
    fetchBrands();
  }, []);

  async function fetchMotorTypes() {
    try {
      const response = await gestionService.getMotorTypes();
      setMotorTypes(response.data);
    } catch (error) {
      alert("Hubo un error al obtener los tipos de motor");
    }
  }

  useEffect(() => {
    fetchMotorTypes();
  }, []);

  async function handleCreateVehicle(event) {
    event.preventDefault();
    try {
      const response = await gestionService.createVehicle({
        brand_id,
        model,
        motor_type_id,
        num_of_seats,
        patent,
        vehicle_type_id,
        year_of_manufacturing
      });
      //reinicio del formulario
      setBrand("");
      setModel("");
      setMotor("");
      setSeats("");
      setPatent("");
      setType("");
      setYear("");

      alert(
        "Vehiculo de patente : " +
          response.data.patent +
          " creado exitosamente."
      );
    } catch (error) {
      alert("Error al crear el vehiculo en el sistema.");
    }
  }

  return (
    <>
      <Box className="containerBrands">
      <NavBarVehicles/>
        <Box className="containerFormBrands">
          <h1 className="CreateVehicleForm">Crear Vehiculo</h1>
          <form className="border row g-3 px-4" onSubmit={handleCreateVehicle}>

            
            <Container className="col-12">
              <FormControl fullWidth>
                <InputLabel id="demo-simple-select-label" sx={{color:"white"}}>Marca</InputLabel>
                <Select
                  labelId="demo-simple-select-label"
                  id="demo-simple-select"
                  value={brand_id}
                  label="Marca"
                  sx={{color: "white"}}
                  onChange={(e) => setBrand(e.target.value)}
                >
                  {brands.map((brand) => (
                    <MenuItem key={brand.brand_id} value={brand.brand_id}>
                      {brand.brand_name}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Container>

            <Container className="col-12">
              <FormControl fullWidth>
                <InputLabel id="demo-simple-select-label" sx={{color:"white"}}>Tipo vehiculo</InputLabel>
                <Select
                  labelId="demo-simple-select-label"
                  id="demo-simple-select"
                  value={vehicle_type_id}
                  label="Tipo vehiculo"
                  sx={{color: "white"}}
                  onChange={(e) => setType(e.target.value)}
                >
                  {vehicle_types.map((type) => (
                    <MenuItem key={type.vehicle_type_id} value={type.vehicle_type_id}>
                      {type.vehicle_type_name}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Container>

            <Container className="col-12">
              <FormControl fullWidth>
                <InputLabel id="demo-simple-select-label" sx={{color:"white"}}>Tipo de Motor</InputLabel>
                <Select
                  labelId="demo-simple-select-label"
                  id="demo-simple-select"
                  value={motor_type_id}
                  label="tipo motor"
                  sx={{color: "white"}}
                  onChange={(e) => setMotor(e.target.value)}
                >
                  {vehicle_motor_types.map((motor) => (
                    <MenuItem key={motor.motor_type_id} value={motor.motor_type_id}>
                      {motor.motor_type_name}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Container>

            <Container className="col-12">
              <TextField
                id="Outlined"
                label="Modelo"
                InputLabelProps={{
                  style: { color: "#fff" },
                }}
                sx={{ input: { color: "white" } }}
                value={model}
                onChange={(e) => setModel(e.target.value)}
              />
            </Container>

            <Container className="col-12">
              <TextField
                id="Outlined"
                label="Año vehiculo"
                InputLabelProps={{
                  style: { color: "#fff" },
                }}
                sx={{ input: { color: "white" } }}
                value={year_of_manufacturing}
                onChange={(e) => setYear(e.target.value)}
              />
            </Container>

            <Container className="col-12">
              <TextField
                id="Outlined"
                label="Patente"
                InputLabelProps={{
                  style: { color: "#fff" },
                }}
                sx={{ input: { color: "white" } }}
                value={patent}
                onChange={(e) => setPatent(e.target.value)}
              />
            </Container>

            <Container className="col-12">
              <TextField
                id="Outlined"
                label="N º Asientos"
                InputLabelProps={{
                  style: { color: "#fff" },
                }}
                sx={{ input: { color: "white" } }}
                value={num_of_seats}
                onChange={(e) => setSeats(e.target.value)}
              />
            </Container>

            <Container className="col-12 mt-4 mb-4">
              <Button type="submit" className="btn btn-primary">
                Crear Vehiculo
              </Button>
            </Container>
          </form>
        </Box>
      </Box>
      <VehicleList />
    </>
  );
}
