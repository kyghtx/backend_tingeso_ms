import React, { useEffect, useState } from "react";
import gestionService from "../services/gestion-service";
import { Container,Select } from "@mui/material";
function CreateRepairVehicle() {
    /* need a fetch for the repair types.*/
    /*the format is a repairCehicleDto
{vehicle_id (possibly patent)
repair type
}
c
the price is setted in backend, (ideal implementar algo para mostrar el precio final de las reparaciones.)

    */
const [repairTypes, setRepairTypes]=useState([]);
const [venicles, setVehicles]=useState([]);
const [repair_type_id,setRepairId]=useState("");
async function fetchVehicles(){
    try {
        const response = await gestionService.getVehiclesFromRepairVehicles();
        setVehicles(response.data);
    } catch (error) {
        alert("Error al obtener los vehiculos.")
    }
}
async function fetchRepairTypes(){
    try {
        const response = await gestionService.getRepairListFromRepairVehicles();
        setRepairTypes(response.data);
    } catch (error) {
        alert("Error al obtener los tipos de reparación disponibles")
    }
}

useEffect(()=> {
    fetchRepairTypes();
    fetchVehicles();
})

    return ( 
        <Select
                  labelId="demo-simple-select-label"
                  id="demo-simple-select"
                  value={repair_type_id}
                  label="Tipo de Reparacion"
                  sx={{color: "white"}}
                  onChange={(e) => setRepairId(e.target.value)}
                >
                  {repairTypes.map((repair_type) => (
                    <MenuItem key={repair_type.repair_type_id} value={repair_type.repair_type_id}>
                      {repair_type.repair_type_name}
                    </MenuItem>
                  ))}
                </Select>
     );
}

export default CreateRepairVehicle;