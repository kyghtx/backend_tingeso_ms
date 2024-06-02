import React, { useState, useEffect } from "react";
import gestionService from "../services/gestion-service";
import { Box } from "@mui/material";
import { DataGrid } from "@mui/x-data-grid";

export default function MotorTypesList() {
  const [motor_types, setMotorTypes] = useState([]);

  async function fetchMotorTypes() {
    try {
      const response = await gestionService.getMotorTypes();
      setMotorTypes(response.data);
    } catch (error) {
      alert("Error al obtener los tipos de motor.");
    }
  }

  useEffect(() => {
    fetchMotorTypes();
  },[]);

  const columns = [
    { field: 'id', headerName: 'ID', width: 90 },
    { field: 'motor_type_name', headerName: 'Tipo motor', width: 150 },
  ];

  const rows = motor_types.map((motorType, index) => ({
    id: index + 1,
    motor_type_name: motorType.motor_type_name
  }));

  return (
    <Box sx={{ height: '90%', width: '50%' }} className="BrandList">
      <h1 className="HeadersLists">Tipos de motor registrados</h1>
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
