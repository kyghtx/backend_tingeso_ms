import { useState, useEffect } from "react";
import gestionService from "../services/gestion-service";
import { Box } from "@mui/material";
import { DataGrid } from "@mui/x-data-grid";
import { Description } from "@mui/icons-material";
export default function RepairsTypeList() {
  const [repairsList, setRepairs] = useState([]);

  async function fetchRepairsList() {
    try {
      const response = await gestionService.getRepairList();
      setRepairs(response.data);
    } catch (error) {
      alert("Error al obtener los tipos de reparacion.");
    }
  }

  useEffect(() => {
    fetchRepairsList();
  }, []);
  const columns = [{
    field: 'id',
    headerName: 'ID',
    width: 90
  }, {
    field: 'Repair_type',
    headerName: 'Tipo De Reparacion',
    width: 150
  },{
    field: 'Description',
    headerName: 'Descripcion',
    width: 150
  }];
  const rows = repairsList.map((rl, index) => ({
    id: index + 1,
    Repair_type: rl.repair_type_name,
    Description: rl.description

  }));
  return <Box sx={{
    height: '90%',
    width: '50%'
  }} className="BrandList">
      <h1 className="HeadersList">Tipos de reparaciones</h1>
      <DataGrid rows={rows} columns={columns} pageSize={5} rowsPerPageOptions={[5, 10]} checkboxSelection disableRowSelectionOnClick initialState={{
      pagination: {
        paginationModel: {
          page: 0,
          pageSize: 5
        }
      }
    }} sx={{
      '& .MuiDataGrid-columnHeaders': {
        backgroundColor: 'primary.light',
        // Fondo de los encabezados de las columnas
        color: 'primary.black' // Color del texto de los encabezados de las columnas

      },
      '& .MuiDataGrid-row': {
        '&:nth-of-type(odd)': {
          backgroundColor: 'grey.100' // Color de fondo para filas impares

        },
        '&:nth-of-type(even)': {
          backgroundColor: 'grey.50' // Color de fondo para filas pares

        }
      },
      '& .MuiDataGrid-cell': {
        color: 'text.primary' // Color del texto en las celdas

      },
      overflow: scroll
    }} />
    </Box>;
}