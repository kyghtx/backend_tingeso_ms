import React, { useEffect, useState } from "react";
import gestionService from "../services/gestion-service";
import {
  Select,
  MenuItem,
  Box,
  Popover,
  Typography,
  Button,
  FormControl,
  InputLabel,
} from "@mui/material";
import SendIcon from "@mui/icons-material/Send";
import DeleteIcon from "@mui/icons-material/Delete";
import { DataGrid } from '@mui/x-data-grid';


function CreateRepairVehicle() {
  const [repairTypes, setRepairTypes] = useState([]);
  const [vehicles, setVehicles] = useState([]);
  const [repair_type_id, setRepairId] = useState("");
  const [vehicle_id, setVehicleId] = useState("");
  const [anchorEl, setAnchorEl] = useState(null);
  const [selectedVehicle, setSelectedVehicle] = useState(null);
  const [selectedRepairs, setSelectedRepairs] = useState([]);
  const [selectedRepairsPrices, setRepairsPrices]= useState([]);
  const [isDisabled, setIsDisabled] = useState(false);
  /*TODO: posible inclusion de las marcas.. mas que todo para tener algo mas visual */
  const handleClick = (event, vehicle) => {
    setAnchorEl(event.currentTarget);
    setSelectedVehicle(vehicle);
    console.log(vehicle);
    setIsDisabled(true);
  alert("Vehículo seleccionado. Si desea seleccionar otro... ¡recargue la página!")
  };

  const handleClose = () => {
    setAnchorEl(null);
    setRepairId("");
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
  async function fecthRepairPrices(){
    try {
      const response = await gestionService.getRepairPrices();
      setRepairsPrices(response.data);
    } catch (error) {
      alert("Error al obtener los precios de las reparaciones.");
    }
  }

  useEffect(() => {
    fetchRepairTypes();
    fetchVehicles();
    fecthRepairPrices();
  }, []);

  /*este onclick es activado por el boton para agregar una nueva reparacion al arreglo
  este es un DTO que llegara al backend */
  const handleAddRepair = () => {
    if (vehicle_id && repair_type_id) {
      const newRepair = { vehicle_id, repair_type_id };
      setSelectedRepairs([...selectedRepairs, newRepair]);
      console.log(selectedRepairs);
      console.log("Reparación añadida:", newRepair);
    }
  };
  /* para elminar una reparacion  de la lista a eleccion del usuario*/
  const handleDeleteRepair = (index) => {
    const list = [...selectedRepairs];
    list.splice(index, 1);
    setSelectedRepairs(list);
  };

  async function handleSubmit(event) {
    event.preventDefault();
    try {
      await gestionService.createRepairVehicles(selectedRepairs);
      alert("Reparaciones guardadas con exito");
      console.table(event);
    } catch (error) {
      alert("Error al guardar reparaciones.");
      console.log(error);
    }
  }
  const columns = [
    {
      field: 'repairDetails',
      headerName: 'Detalles de Reparación',
      flex: 1,
      renderCell: (params) => (
        <div style={{ display: 'flex', alignItems: 'center', justifyContent:"space-between" }}>
          <span>{`* - ${repairTypes.find((element) => element.repair_type_id === params.row.repair_type_id)?.repair_type_name} - Costo: ${selectedRepairsPrices.find((element) => element.repair_type_id === params.row.repair_type_id && element.motor_type_id === selectedVehicle.motor_type_id)?.price}`}</span>
          <Button
            variant="contained"
            color="error"
            onClick={() => handleDeleteRepair(params.rowIndex)}
            endIcon={<DeleteIcon />}
            style={{ marginLeft: '8px' }}
          >
            Borrar
          </Button>
        </div>
      ),
    },
  ];

  const rows = selectedRepairs.map((repair, index) => ({
    id: index, 
    repair_type_id: repair.repair_type_id, 
  }));


  return (
    <Box className="createRepairVehicleContainer">
      <Box className="RepairVehicleContainer">
        <Box className="SelectVehicleAndRepairContainer">
        <FormControl sx={{width:"50%"}}>
        <InputLabel id="demo-simple-select-label" sx={{color:"white"}}>Tipo de Reparacion</InputLabel>
          <Select autoWidth
            labelId="repair-type-select-label"
            id="repair-type-select"
            value={repair_type_id}
            label="Tipo de Reparación"
            sx={{ color: "white", width:"100%"}}
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
        </FormControl>
          {/*Esto es un select para obtener los vehiculos.*/}
          <FormControl sx={{width:"50%"}}>
          <InputLabel id="demo-simple-select-label" sx={{color:"white"}}>Vehiculo</InputLabel>
          <Select
            labelId="vehicle-select-label"
            id="vehicle-select"
            value={vehicle_id}
            label="Vehículo"
            sx={{ color: "white", width:"100%"}}
            onChange={(e) => setVehicleId(e.target.value)}
            disabled={isDisabled}
          >
            {vehicles.map((v) => (
              <MenuItem
                key={v.vehicle_id}
                value={v.vehicle_id}
                onClick={
                  (event) =>
                    handleClick(
                      event,
                      v
                    ) /*aca se establece como vehiculo el seleccionado para useEffect*/
                }
              >
                {v.patent}
              </MenuItem>
            ))}
          </Select>
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
                  <Typography>
                    Año de fabricación: {selectedVehicle.year_of_manufacturing}
                  </Typography>
                </Box>
              )}
            </Popover>
          )}
          </FormControl>
        </Box>
        <Box className="AddAndSumbitContainer">
        <Button variant="contained" onClick={handleAddRepair}>
            Agregar Reparación
          </Button>
        <Button
          variant="contained"
          onClick={(selectedRepairs) => handleSubmit(selectedRepairs)}
          endIcon={<SendIcon />}
        >
          Ingresar Reparaciones
        </Button>
        </Box>
      </Box>
      <Box mt={2} sx={{width: "50%", display:"flex",flexDirection:"column", justifyContent:"space-around"}}>
        <Box>HOLA
        <Button color="secondary" variant="contained">Buscar reparaciones</Button>
        </Box>
        
        <DataGrid
        rows={rows}
        columns={columns}
        pageSize={5} // Número de filas por página
        rowsPerPageOptions={[3,5,10]} // Opciones para el selector de filas por página
        checkboxSelection={false}
        disableSelectionOnClick ={true}
        initialState={{
          pagination: {
            paginationModel: { page: 0, pageSize: 5 },
          },        
        }}
        components={{
          Toolbar: () => null, 
        }}
        // Opcional: Personalización de estilos
        sx={{
          '& .MuiDataGrid-columnHeader': {
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
          overflow: 'scroll', // Habilitamos el desplazamiento horizontal si es necesario
        width: "100%"}}
      />
      </Box>
    </Box>
  );
}

export default CreateRepairVehicle;
