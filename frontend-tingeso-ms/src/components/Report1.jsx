import React, { useEffect, useState } from "react";
import gestionService from "../services/gestion-service";
import { DataGrid } from "@mui/x-data-grid";
import { Box } from "@mui/material";
import { TextField, Grid } from '@mui/material';

function Report1() {
  const [entitiesForReport, setEntities] = useState([]);
  const [month, setMonth] = useState(1); 
  const [year, setYear] = useState(2024); /* esto estara sujeto a variables que se ingresaran por pantalla*/

  /* Tipos de vehiculos */
  const [vehicleTypes, setVehicleTypes] = useState([]);
  
  /* Tipos de reparaciones */
  const [repairTypes, setRepairTypes] = useState([]);

  async function fetchRepairTypes() {
    try {
      const response = await gestionService.getRepairList();
      setRepairTypes(response.data);
      console.log(response.data);
    } catch (error) {
      console.log("Error al obtener los tipos de reparación.");
    }
  }

  async function fetchReport1() {
    try {
      const response = await gestionService.getReport1(month, year);
      setEntities(response.data);
      console.log("Entidades para el reporte");
      console.log(response.data);
    } catch (error) {
      alert("Error al obtener el reporte.");
    }
  }

  async function fetchVehicleTypes() {
    try {
      const response = await gestionService.getVehicleTypes();
      setVehicleTypes(response.data);
      console.log(response.data);
    } catch (error) {
   
      console.log("Error al obtener los tipos de vehículos.");
    }
  }

  useEffect(() => {
    fetchRepairTypes();
    fetchVehicleTypes();
  }, []);

  useEffect(()=>{
    fetchReport1();
  },[month,year]);

  const handleMonthChange = (e) => {
    const month = parseInt(e.target.value);
    setMonth(month);
  };

  const handleYearChange = (e) => {
    const year = parseInt(e.target.value);
    setYear(year);
  };

  
  const rows = [];

// Iterar sobre cada tipo de reparación
repairTypes.forEach((r) => {
  // Agregar fila de datos para el tipo de reparación actual
  const repairRow = {
    id: `repair_${r.repair_type_id}`,
    repair_type_name: r.repair_type_name,
    total: 0, // Inicializar con valor 0 para el total
  };

  // Agregar datos de entitiesForReport para cada tipo de vehículo
  vehicleTypes.forEach((v) => {
    const entity = entitiesForReport.find((e) => e.vehicle_type_name === v.vehicle_type_name);
    repairRow[v.vehicle_type_name] = entity ? entity.vehicle_type_quantity : 0;
  });

  rows.push(repairRow);

  // Agregar fila vacía para mostrar datos de total_mount_repair
  const emptyRow = {
    id: `empty_${r.repair_type_id}`,
    repair_type_name: '',
    total: 0, 
  };

  vehicleTypes.forEach((v)=>{
    const entity = entitiesForReport.find((e) => e.vehicle_type_name === v.vehicle_type_name && e.repair_type_name === r.repair_type_name);
    emptyRow[v.vehicle_type_name] = entity ? entity.total_mount_repairs : 0 ;

  });

  rows.push(emptyRow);
});

// Calcular el total del costo por tipo de reparacion y tipo de vehiculo.
repairTypes.forEach((r) => {
  const total = vehicleTypes.reduce((acc, v) => {
    const entity = entitiesForReport.find((e) => e.vehicle_type_name === v.vehicle_type_name && e.repair_type_name === r.repair_type_name);
    return acc + (entity ? entity.total_mount_repairs : 0);
  }, 0);

  const rowIndex = rows.findIndex((row) => row.id === `empty_${r.repair_type_id}`);
  if (rowIndex !== -1) {
    rows[rowIndex].total = total;
  }
});

// Calcular el total de vehiculos por tipo de reparacion
repairTypes.forEach((r) => {
  const total = vehicleTypes.reduce((acc, v) => {
    const entity = entitiesForReport.find((e) => e.vehicle_type_name === v.vehicle_type_name && e.repair_type_name === r.repair_type_name);
    return acc + (entity ? entity.vehicle_type_quantity : 0);
  }, 0);

  const rowIndex = rows.findIndex((row) => row.id === `repair_${r.repair_type_id}`);
  if (rowIndex !== -1) {
    rows[rowIndex].total = total;
  }
});


  const columns = [
    { field: "repair_type_name", headerName: "Tipo de Reparación", width: 350 },
    ...vehicleTypes.map((v) => ({
      field: v.vehicle_type_name,
      headerName: v.vehicle_type_name,
      width: 150,
    })),
    { field: "total", headerName: "Total", width: 150 },
  ];

  return (
    <Box
    sx={{
      display: "flex",
      flexDirection: "column",
      justifyContent: "center", 
      alignItems: "center", 
    }}
  >
    <Grid container spacing={2} alignItems="center" justifyContent="center">
      <Grid item>
        <TextField
          id="monthInput"
          label="Mes"
          type="number"
          InputProps={{ inputProps: { min: 1, max: 12 } }}
          value={month}
          onChange={handleMonthChange}
        />
      </Grid>
      <Grid item>
        <TextField
          id="yearInput"
          label="Año"
          type="number"
          InputProps={{ inputProps: { min: 1900, max: 2100 } }}
          value={year}
          onChange={handleYearChange}
        />
      </Grid>
    </Grid>
    <div style={{ height: 400, width: '100%' }}>
      <DataGrid
        rows={rows}
        columns={columns}
        pageSize={5}
        rowsPerPageOptions={[5, 10, 20]}
        checkboxSelection={false}
        disableSelectionOnClick={true}
        disableRowSelectionOnClick={true}
        sx={{
          "& .MuiDataGrid-columnHeader": {
            backgroundColor: "primary.light",
            color: "primary.black",
          },
          "& .MuiDataGrid-row": {
            "&:nth-of-type(odd)": {
              backgroundColor: "grey.100",
            },
            "&:nth-of-type(even)": {
              backgroundColor: "grey.50",
            },
          },
          "& .MuiDataGrid-cell": {
            color: "text.primary",
          },
          "& .MuiDataGrid-row.Mui-selected": {
            backgroundColor: "transparent",
          },
          "& .MuiDataGrid:hover": {
            backgroundColor: "transparent",
          },
          overflow: "scroll",
          width: "100%",
        }}
      />
    </div>
  </Box>
  
  );
}

export default Report1;
