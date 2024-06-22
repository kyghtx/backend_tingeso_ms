import React, { useEffect, useState } from "react";
import gestionService from "../services/gestion-service";
import { Select, MenuItem, Box, Popover, Typography, Button } from "@mui/material";

function CreateRepairVehicle() {
  const [repairTypes, setRepairTypes] = useState([]);
  const [vehicles, setVehicles] = useState([]);
  const [repair_type_id, setRepairId] = useState("");
  const [vehicle_id, setVehicleId] = useState("");
  const [anchorEl, setAnchorEl] = useState(null);
  const [selectedVehicle, setSelectedVehicle] = useState(null);
  const [selectedRepairs, setSelectedRepairs] = useState([]);
  /*TODO: posible inclusion de las marcas.. mas que todo para tener algo mas visual */
  const handleClick = (event, vehicle) => {
    setAnchorEl(event.currentTarget);
    setSelectedVehicle(vehicle);
  };

  const handleClose = () => {
    setAnchorEl(null);
    setSelectedVehicle(null);
    setRepairId("");
    alert("Evite cambiar de vehiculo. solo ingrese las reparaciones de un vehiculo a la vez.")
  };

  async function fetchVehicles() {
    try {
      const response = await gestionService.getVehiclesFromRepairVehicles();
      setVehicles(response.data);
    } catch (error) {
      alert("Error al obtener los vehículos.");
    }
  }

  async function fetchRepairTypes() {
    try {
      const response = await gestionService.getRepairListFromRepairVehicles();
      setRepairTypes(response.data);
    } catch (error) {
      alert("Error al obtener los tipos de reparación disponibles.");
    }
  }

  useEffect(() => {
    fetchRepairTypes();
    fetchVehicles();
  }, []);

  /*este onclick es activado por el boton para agregar una nueva reparacion al arreglo
  este es un DTO que llegara al backend */
  const handleAddRepair = () => {
    if (vehicle_id && repair_type_id) {
      const newRepair = { vehicle_id, repair_type_id };
      setSelectedRepairs([...selectedRepairs, newRepair]);
      console.log("Reparación añadida:", newRepair);
    }
  };

  const handleSubmit= async (event) =>{
    event.preventDefault();
    try {
        const response = await gestionService.createRepairVehicles(event);
        alert("Reparaciones guardadas con exito")
    
    } catch (error) {
        alert("Error al guardar reparaciones.");
        
    }
  }

  return (
    <Box>
      <Select
        labelId="repair-type-select-label"
        id="repair-type-select"
        value={repair_type_id}
        label="Tipo de Reparación"
        sx={{ color: "white", marginRight: 2 }}
        onChange={(e) => setRepairId(e.target.value)}
      >
        {repairTypes.map((repair_type) => (
          <MenuItem
            key={repair_type.repair_type_id}
            value={repair_type.repair_type_id}
          >
            {repair_type.repair_type_name}
          </MenuItem>
        ))}
      </Select>
      {/*Esto es un select para obtener los vehiculos.*/}
      <Select
        labelId="vehicle-select-label"
        id="vehicle-select"
        value={vehicle_id}
        label="Vehículo"
        sx={{ color: "white" }}
        onChange={(e) => setVehicleId(e.target.value)}
      >
        {vehicles.map((v) => (
          <MenuItem key={v.vehicle_id} value={v.vehicle_id} onClick={(event) => handleClick(event, v) /*aca se establece como vehiculo el seleccionado para useEffect*/}>
            {v.patent}
          </MenuItem>
        ))}
      </Select>
      <Button variant="contained" onClick={handleAddRepair}>
        Agregar Reparación
      </Button>
      {selectedVehicle && (
        <Popover
          id={selectedVehicle.vehicle_id.toString()}
          open={Boolean(anchorEl)}
          anchorEl={anchorEl}
          onClose={handleClose}
          anchorOrigin={{
            vertical: "bottom",
            horizontal: "center",
          }}
          transformOrigin={{
            vertical: "top",
            horizontal: "center",
          }}
        >
          {selectedVehicle && (
            <Box sx={{ p: 2 }}>
                {/*Aca muestro la informacion extra del vehiculo seleccionado para una mejor vision*/}
              <Typography variant="h6">{selectedVehicle.model}</Typography>
              <Typography>Año de fabricación: {selectedVehicle.year_of_manufacturing}</Typography>
            </Box>
          )}
        </Popover>
      )}
      <Box mt={2}>
        <Typography variant="h6">Reparaciones Seleccionadas:</Typography>
        <ul>
          {selectedRepairs.map((repair, index) => (
            <li key={index}>
              Vehicle ID: {repair.vehicle_id}, Repair Type ID: {repair.repair_type_id}
            </li>
          ))}
          {console.log(selectedRepairs)}
        </ul>
      </Box>
      <Button variant="contained" onClick={handleSubmit(selectedRepairs)}> 
        Ingresar Reparaciones
      </Button>
    </Box>
  );
}

export default CreateRepairVehicle;
