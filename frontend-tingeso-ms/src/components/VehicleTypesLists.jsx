import { useState, useEffect } from "react";
import gestionService from "../services/gestion-service";
import { Box } from "@mui/material";
import { DataGrid } from "@mui/x-data-grid";

export default function VehicleTypesList() {
  const [types, setTypes] = useState([]);

  async function fetchVehicleTypes() {
    try {
      const response = await gestionService.getVehicleTypes();
      setTypes(response.data);
    } catch (error) {
      alert("Error al obtener los tipos de vehiculos.");
    }
  }

  useEffect(() => {
    fetchVehicleTypes();
  },[]);

  const columns = [
    { field: 'id', headerName: 'ID', width: 90 },
    { field: 'Vehicle_type', headerName: 'Tipo vehiculo', width: 150 },
  ];

  const rows = types.map((type, index) => ({
    id: index + 1,
    Vehicle_type: type.vehicle_type_name,
  }));

  return (
    <Box sx={{ height: '90%', width: '50%' }} className="BrandList">
      <h1 className="HeadersList">Tipos de vehiculos registrados</h1>
      <DataGrid
        rows={rows}
        columns={columns}
        pageSize={5}
        rowsPerPageOptions={[5, 10]}
        checkboxSelection={false}
        disableRowSelectionOnClick={true}
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
