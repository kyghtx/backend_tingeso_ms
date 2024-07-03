import { useEffect, useState } from "react";
import gestionService from "../services/gestion-service";
import { DataGrid } from "@mui/x-data-grid";

function Report1() {
    const [entitiesForReport, setEntities] = useState([]);
    const [month, setMonth] = useState(7); // Mes inicial (ejemplo: julio)
    const [year, setYear] = useState(2024); // Año inicial

    async function fetchReport1() {
        try {
            const response = await gestionService.getReport1(month, year);
            setEntities(response.data);
        } catch (error) {
            alert("Error al obtener el reporte.");
        }
    }

    useEffect(() => {
        fetchReport1();
    }, []); // La dependencia vacía asegura que se ejecute solo una vez al montar el componente

    // Mapear las entidades a las filas del DataGrid
    const rows = entitiesForReport.map((entity, index) => ({
        id: index + 1,
        ...entity // Spread de todos los campos de la entidad
    }));

    // Definir las columnas del DataGrid
    const columns = [
        { field: 'vehicle_type_name', headerName: 'Vehicle Type', width: 150 },
        { field: 'repair_type_name', headerName: 'Repair Type', width: 150 },
        { field: 'vehicle_type_quantity', headerName: 'Vehicle Quantity', width: 150 },
        { field: 'total_mount_repairs', headerName: 'Total Amount Repairs', width: 200 }
    ];

    return (
        <div style={{ height: 400, width: '100%' }}>
            <DataGrid
                rows={rows}
                columns={columns}
                pageSize={5}
                rowsPerPageOptions={[5, 10, 20]}
                checkboxSelection={false}
                disableSelectionOnClick={true} // Cambiado a disableSelectionOnClick
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
                    "& .MuiDataGrid-row.Mui-selected": { // Cambiado de Mui-hovered a Mui-selected
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
    );
}

export default Report1;
