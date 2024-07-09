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
import { useNavigate } from "react-router-dom";
function CreateRepairVehicle() {
  const [repairTypes, setRepairTypes] = useState([]);
  const [vehicles, setVehicles] = useState([]);
  const [repair_type_id, setRepairId] = useState("");
  const [vehicle_id, setVehicleId] = useState("");
  const [searchVehicle_id, setSearchVehicleId]=useState("");
  const [anchorEl, setAnchorEl] = useState(null);
  const [selectedVehicle, setSelectedVehicle] = useState(null);
  const [selectedVehicleForSearch, setSelectedVehicleForSearch] = useState("");
  const [selectedRepairs, setSelectedRepairs] = useState([]);
  const [selectedRepairsPrices, setRepairsPrices]= useState([]);
  const [isDisabled, setIsDisabled] = useState(false);
  const navigate = useNavigate();

  const handleClick = (event, vehicle) => {
    setAnchorEl(event.currentTarget);
    setSelectedVehicle(vehicle);
    setIsDisabled(true);
    alert("Vehículo seleccionado. Si desea seleccionar otro... ¡recargue la página!");
  };

  const handleChange = (event) => {
    const selectedVehicle = vehicles.find((v) => v.vehicle_id === event.target.value);
    setSelectedVehicleForSearch(selectedVehicle); // Actualiza el vehículo seleccionado para búsqueda
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

  async function fetchRepairPrices() {
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
    fetchRepairPrices();
  }, []);

  const handleAddRepair = () => {
    if (vehicle_id && repair_type_id) {
      const newRepair = { vehicle_id, repair_type_id };
      setSelectedRepairs([...selectedRepairs, newRepair]);
      console.log("Reparación añadida:", newRepair);
    }
  }; 

  const handleSearchRepairs = () => {
    if (searchVehicle_id) {
      navigate(`/api/repair_vehicles/${searchVehicle_id}`);
    }
  };


  const handleDeleteRepair = (index) => {
    const list = [...selectedRepairs];
    list.splice(index, 1);
    setSelectedRepairs(list);
  };

  async function handleSubmit(event) {
    event.preventDefault();
    try {
      await gestionService.createRepairVehicles(selectedRepairs);
      alert("Reparaciones guardadas con éxito");
      console.table(selectedRepairs);
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
            <InputLabel id="repair-type-select-label" sx={{color:"white"}}>
              Tipo de Reparación
            </InputLabel>
            <Select
              autoWidth
              labelId="repair-type-select-label"
              id="repair-type-select"
              value={repair_type_id}
              label="Tipo de Reparación"
              sx={{ color: "white", width:"100%" }}
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
          <FormControl sx={{width:"50%"}}>
            <InputLabel id="vehicle-select-label" sx={{color:"white"}}>
              Vehículo
            </InputLabel>
            <Select
              labelId="vehicle-select-label"
              id="vehicle-select"
              value={vehicle_id}
              label="Vehículo"
              sx={{ color: "white", width:"100%" }}
              onChange={(e) => setVehicleId(e.target.value)}
              disabled={isDisabled}
            >
              {vehicles.map((v) => (
                <MenuItem
                  key={v.vehicle_id}
                  value={v.vehicle_id}
                  onClick={(event) => handleClick(event, v)}
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
        <Box className="AddAndSubmitContainer">
          <Button variant="contained" onClick={handleAddRepair}>
            Agregar Reparación
          </Button>
          <Button
            variant="contained"
            onClick={handleSubmit}
            endIcon={<SendIcon />}
          >
            Ingresar Reparaciones
          </Button>
        </Box>
      </Box>
      <Box mt={2} sx={{width: "60%", display:"flex",flexDirection:"column", justifyContent:"space-between", height:"100%"}}>
      <Box sx={{display:"flex", flexDirection:"row", justifyContent:"space-around"}}>
        <FormControl sx={{width:"50%"}}>
          <InputLabel id="search-vehicle-label" sx={{color:"white"}}>
            Buscar Reparaciones Vehículo
          </InputLabel>
          <Select
            labelId="search-vehicle-label"
            id="search-vehicle-select"
            value={searchVehicle_id}
            label="Vehículo"
            sx={{ color: "white", width:"100%" }}
            onChange={(e) => setSearchVehicleId(e.target.value)}
          >
            {vehicles.map((v) => (
              <MenuItem
                key={v.vehicle_id}
                value={v.vehicle_id}
                onClick={(event) => handleChange(event)}
              >
                {v.patent}
              </MenuItem>
            ))}
          </Select>
        </FormControl>
       
          <Button color="secondary" variant="contained"
          onClick={handleSearchRepairs}
          >
            Buscar Reparaciones 
          </Button>
        </Box>
        <DataGrid
          rows={rows}
          columns={columns}
          pageSize={5}
          rowsPerPageOptions={[3,5,10]}
          checkboxSelection={false}
          disableSelectionOnClick={true}
          initialState={{
            pagination: {
              paginationModel: { page: 0, pageSize: 5 },
            },        
          }}
          components={{
            Toolbar: () => null,
          }}
          sx={{
            '& .MuiDataGrid-columnHeader': {
              backgroundColor: 'primary.light',
              color: 'primary.black',
            },
            '& .MuiDataGrid-row': {
              '&:nth-of-type(odd)': {
                backgroundColor: 'grey.100',
              },
              '&:nth-of-type(even)': {
                backgroundColor: 'grey.50',
              },
            },
            '& .MuiDataGrid-cell': {
              color: 'text.primary',
            },
            overflow: 'scroll',
            width: "100%"
          }}
        />
      </Box>
    </Box>
  );
}

export default CreateRepairVehicle;
