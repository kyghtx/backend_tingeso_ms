import React, { useEffect, useState } from "react";
import gestionService from "../services/gestion-service";
import { Box, Button, Popover } from "@mui/material";
import DeleteIcon from "@mui/icons-material/Delete";
import { DataGrid } from "@mui/x-data-grid";
import { useParams } from "react-router-dom";
import SendIcon from "@mui/icons-material/Send";

function CompletedRepairsVehicles() {
  const { vehicle_id } = useParams();
  const [repairVehicles, setRepairVehicles] = useState([]);
  const [repairDetails, setRepairDetails] = useState([]);
  const [selectedRepair, setSelectedRepair] = useState(null);
  const [popoverOpen, setPopoverOpen] = useState(false);
  const [selectedRepairs, setSelectedRepairs] = useState([]);
  //Para manejar el estado del boton
  const [buttonState, setButtonState] = useState({
    color: "error",
    text: "Finalizar",
    state: false,
  });
  const [repairStates, setRepairStates] = useState({});

  // Función para cargar las reparaciones de un vehículo
  async function fetchRepairOfAvehicle() {
    try {
      const response = await gestionService.getRepairsOfAVehicle(vehicle_id);
      setRepairVehicles(response.data);
      console.log("reparaciones", response.data);
    } catch (error) {
      alert("Error al obtener las reparaciones del vehiculo.");
    }
  }

  // Función para cargar los detalles de reparaciones
  async function fetchRepairDetails() {
    try {
      const response = await gestionService.getRepairDetailsVehicle(vehicle_id);
      setRepairDetails(response.data);
      console.log("Detalles", response.data);
    } catch (error) {
      alert("Error al obtener los detalles de la reparaciones");
    }
  }

  // Función para mostrar los detalles de reparaciones al hacer clic en "Detalle"
  const handleShowDetails = (params) => {
    setSelectedRepair(params.row);
    setPopoverOpen(true); // Abrir el popover
  };


  // Función para añadir una reparación seleccionada a selectedRepairs
  const handleAddRepair = (selectedRepair) => {
    if (selectedRepair) {
      setSelectedRepairs([...selectedRepairs, selectedRepair]);
      setRepairStates({
        ...repairStates,
        [selectedRepair.repair_vehicle_id]: true, // Marcar la reparación como finalizada
      });
      setButtonState({ color: "success", text: "Finalizada", state: true });
      console.log("Reparación añadida:", selectedRepair);
    }
  }; 

  async function handleSubmit(event) {
    event.preventDefault();
    try {
      await gestionService.updateRepairsState(selectedRepairs);
      alert("Reparaciones finalizadas con exito");
      console.table(event);
    } catch (error) {
      alert("Error al finalizar reparaciones.");
      console.log(error);
    }
  }

  useEffect(() => {
    fetchRepairOfAvehicle();
    fetchRepairDetails();
  }, []);
  const columns = [
    {
      field: "repair_vehicle_id",
      headerName: "ID de Reparación",
      flex: 1,
    },
    {
      field: "actions",
      headerName: "Patente",
      flex: 1,
      renderCell: (params) => {
        const isFinished = repairStates[params.row.repair_vehicle_id];

        let buttonColor;
        let buttonText;
        let disabled = false;

        if (params.row.state === 1) {
          // Si el estado de la reparación es 1 (por ejemplo, finalizada), mostramos un botón deshabilitado
          buttonColor = "primary";
          buttonText = "Finalizada";
          disabled = true; // Botón deshabilitado
        } else {
          // Si el estado no es 1, mostramos el botón normal
          buttonColor = isFinished ? "success" : "error";
          buttonText = isFinished ? "Finalizada" : "Finalizar";
          disabled = isFinished ? true : false;
        }

        return (
          <Box
            style={{
              display: "flex",
              alignItems: "center",
              justifyContent: "space-around",
            }}
          >
            <b>{`${repairDetails.find((element) => element)?.patent}`}</b>

            <Button
              variant="contained"
              color="secondary"
              onClick={() => handleShowDetails(params)}
            >
              Detalle
            </Button>

            <Button
              disabled={disabled} 
              color={buttonColor}
              onClick={() => handleAddRepair(params.row)}
              endIcon={<DeleteIcon />}
              style={{ marginLeft: "8px" }}
            >
              {buttonText}
            </Button>

            <Button
              variant="contained"
              color="success"
              onClick={() => handleClientRetire(params.row)}
              endIcon={<DeleteIcon />}
              style={{ marginLeft: "8px" }}
            >
              Validar retiro cliente
            </Button>
          </Box>
        );
      },
    },
  ];

  const rows = repairVehicles.map((repair) => ({
    id: repair.repair_vehicle_id,
    ...repair,
  }));

  return (
    <Box mt={2} sx={{ width: "100%" }}>
      <DataGrid
        rows={rows}
        columns={columns}
        pageSize={5}
        rowsPerPageOptions={[5, 10, 15]}
        checkboxSelection={false}
        disableRowSelectionOnClick={true}
        sx={{
          "& .MuiDataGrid-columnHeader": {
            backgroundColor: "primary.light", // Fondo de los encabezados de las columnas
            color: "primary.black", // Color del texto de los encabezados de las columnas
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

          "& .MuiDataGrid-row.Mui-hovered": {
            backgroundColor: "transparent",
          },
          // Take out the hover colour
          "& .MuiDataGrid:hover": {
            backgroundColor: "transparent",
          },
          overflow: "scroll",
          width: "100%",
        }}
      />

      {/* Mostrar los detalles de reparaciones seleccionados */}
      <Popover
        open={popoverOpen}
        onClose={() => setPopoverOpen(false)}
        anchorEl={selectedRepair}
        anchorOrigin={{
          vertical: "bottom",
          horizontal: "left",
        }}
        transformOrigin={{
          vertical: "top",
          horizontal: "left",
        }}
      >
        <Box p={2}>
          {repairDetails
            .filter(
              (detail) =>
                detail.repair_vehicle_id === selectedRepair?.repair_vehicle_id
            )
            .map((detail, index) => (
              <div key={index}>
                {selectedRepair.vehicle_outcome == null && (
                  <>
                    <p>{`ID de Reparación: ${detail.repair_vehicle_id}`}</p>
                    <p>{`Fecha: ${detail.repair_date}`}</p>
                    <p>{`Costo: ${detail.price}`}</p>
                    <p>{`Fecha Finalizacion: ${"En curso"}`}</p>
                    <p>{`Hora Finalizacion: ${"En curso"}`}</p>
                  </>
                )}
                {selectedRepair.vehicle_outcome != null && (
                  <>
                    <p>{`ID de Reparación: ${detail.repair_vehicle_id}`}</p>
                    <p>{`Fecha: ${detail.repair_date}`}</p>
                    <p>{`Costo: ${detail.price}`}</p>
                    <p>{`Fecha Finalizacion: ${selectedRepair.vehicle_outcome}`}</p>
                    <p>{`Hora Finalizacion: ${selectedRepair.vehicle_outcome_hour}`}</p>
                  </>
                )}
              </div>
            ))}
        </Box>
      </Popover>
      <Button
        variant="contained"
        onClick={(selectedRepairs) => handleSubmit(selectedRepairs)}
        endIcon={<SendIcon />}
      >
        Finalizar Reparaciones
      </Button>
    </Box>
  );
}

export default CompletedRepairsVehicles;
