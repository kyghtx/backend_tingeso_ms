import React, { useState, useEffect } from "react";
import gestionService from "../services/gestion-service";
import { Box } from "@mui/material";
import { DataGrid } from "@mui/x-data-grid";

export default function VehicleList() {
  const [vehicles, setVehicles] = useState([]);

  async function fetchVehicles() {
    try {
      const response = await gestionService.getVehicles();
      setVehicles(response.data);
    } catch (error) {
      alert("Error al obtener los tipos de vehiculos.");
    }
  }

  useEffect(() => {
    fetchVehicles();
  },[]);
/*Se renderiza la primera vez que carga el componente*/
  const columns = [
    { field: 'id', headerName: 'ID', width: 90 },
    { field: 'patent', headerName: 'Patente', width: 150 },
    { field: ""}
  ];

  const rows = types.map((vehicle, index) => ({
    id: index + 1,
    Vehicle_patent: vehicle.patent,
    Vehicle_brand:vehicle.brand_name,
    Vehicle_type: vehicle.type_vehicle_name,




  }));

  return (
    <Box sx={{ height: '90%', width: '50%' }} className="BrandList">
      <h1 className="HeadersList">Tipos de vehiculos registrados</h1>
      <DataGrid
        rows={rows}
        columns={columns}
        pageSize={5}
        rowsPerPageOptions={[5, 10]}
        checkboxSelection
        disableRowSelectionOnClick
        initialState={{
          pagination: {
            paginationModel: { page: 0, pageSize: 5 },
          },        
        }}
        sx={{
          '& .MuiDataGrid-columnHeaders': {
            backgroundColor: 'primary.light', // Fondo de los encabezados de las columnas
            color: 'primary.black', // Color del texto de los encabezados de las columnas
          },
          '& .MuiDataGrid-row': {
            '&:nth-of-type(odd)': {
              backgroundColor: 'grey.100', // Color de fondo para filas impares
            },
            '&:nth-of-type(even)': {
              backgroundColor: 'grey.50', // Color de fondo para filas pares
            },
          },
          '& .MuiDataGrid-cell': {
            color: 'text.primary', // Color del texto en las celdas
          },
          overflow:scroll
        }}
      />
    </Box>
  );
}
