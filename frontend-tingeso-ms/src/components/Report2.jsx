import React, { useEffect, useState } from "react";
import gestionService from "../services/gestion-service";
import { DataGrid } from "@mui/x-data-grid";
import { Box } from "@mui/material";
import { TextField, Grid } from "@mui/material";

function Report2() {
  const [entitiesForReport2, setEntities] = useState([]);
  const [month, setMonth] =
    useState(8); /*el mes que sera ingresado por pantalla*/
  const [repairTypes, setRepairTypes] = useState([]);
  const handleMonthChange = (e) => {
    const month = parseInt(e.target.value);
    setMonth(month);
  };
  //Esta funcion es para obtener el reporte 2
  async function fetchReport2() {
    try {
      const response = await gestionService.getReport2(month);
      setEntities(response.data);
    } catch (error) {
      alert("Error al obtener el recurso para el reporte solicitado");
    }
  }
  //Useefect para renderizar hacer el fecth del reporte cada vez que cambia el mes.
  useEffect(() => {
    fetchReport2();
  }, [month]);

  async function fetchRepairTypes() {
    try {
      const response = await gestionService.getRepairList();
      setRepairTypes(response.data);
      console.log(response.data);
    } catch (error) {
      console.log("Error al obtener los tipos de reparación.");
    }
  }
  useEffect(() => {
    fetchRepairTypes();
  }, []);

  //funcion para solo encontrar las entidades corresponientes a los 2 meses pasados
  //Tambien es necesario el mes actual.
  const MonthsToCalculate = entitiesForReport2.filter((e) => {
    console.log(`Evaluando mes: ${e.month}`);
    //Caso especial para el mes de enero.
    if (month == 1) {
      return e.month == 11 || e.month == 12 || e.month == 1;
    }
    if (month == 2) {
      //Aca hay un caso especial, se muestra primero enero al parecer por la naturaleza del filter
      return e.month == 12 || e.month == 1 || e.month == 2;
    }
    //Todos los demas meses desde marzo hasta diciembre.
    if (month > 2 && month <= 12) {
      return e.month == month - 1 || e.month == month - 2 || e.month == month;
    }
  });
  console.log("Reporte 2", MonthsToCalculate);

  const MonthsToShow = []; //Estos son los meses que seran mostrados por pantalla, solo fin estetico.
  MonthsToCalculate.forEach((e) => {
    if (!MonthsToShow.includes(e.month_name)) {
      MonthsToShow.push(e.month_name);
    }
    console.log("Meses para mostrar", MonthsToShow);
  });
  console.log("Meses para las columnas", MonthsToShow);

  //Reverse de MonthsToShow

  const MonthsToShowReverse= MonthsToShow.reverse();
  //Armado de filas.
  const rows = [];

  repairTypes.forEach((r) => {
    const repairRow = {
      id: `repair_${r.repair_type_id}`,
      repair_type_name: r.repair_type_name,
      total: 0, //se inicializa con 0 el valor total que luego cambiara
    };
    //Esta funcion muestra los totales de  cada tipo de reparacion en los meses determinados
    MonthsToShowReverse.forEach((element) => {
      const entity = MonthsToCalculate.find(
        (e) =>
          element === e.month_name && e.repair_type_name === r.repair_type_name
      );
      repairRow[element] = entity ? entity.repair_total_quantity : 0;
      console.log("Entity oneMonthEarly :", entity);
    });

    //Ahora veo los totales en terminos de costos.

    rows.push(repairRow);

    /*agrego la fila vacia*/
    const emptyRow = {
      id: `empty_${r.repair_type_id}`,
      repair_type_name: "",
      total: 0,
    };

    /*Para ver el total de las reparaciones realizadas */

    MonthsToShowReverse.forEach((m) => {
      const total = MonthsToCalculate.find(
        (e) => m === e.month_name && e.repair_type_name === r.repair_type_name
      ).repair_type_total_mount;
      console.log("Total de reparaciones para el mes: ",m, total);
      emptyRow[m] = total ? total : 0; 
    });

    rows.push(emptyRow);
  });
//Hasta este momento esta completo el asunto de poder mostrar la informacion de las entidades del reporte por pantalla.


 


  //Las columnas de momento solo poseen los nombres de los meses anteriores.
  const columns = [
    { field: "repair_type_name", headerName: "Tipo de Reparación", width: 350 },
    ...MonthsToShow.map((m, index) => [
      { id: index * 2 + 1, field: m, headerName: m, width: 150 },
      { id: index * 2 + 2, field: `${m}-variation`, headerName: ` % Variación`, width: 150 }
    ]).flat()
  ];
  // Eliminar la última columna, ya que con lo anterior se genera una columna de mas en variaccion.
  const columnsWithoutLast = columns.slice(0, -1);

  //Ahora se requieren calcular las variaciones y el total.
//Para ello creo una funcion que recorra cada fila para calcular las variaciones.
// Calcula la variación para cada mes y tipo de reparación
//El arreglo esta invertido... [mes actual, anterior, anterior del anterior]


//TODO: revisar para colocar la variacion de los precios....

repairTypes.forEach((r) => {
    const repairRow = rows.find((row) => row.id === `repair_${r.repair_type_id}`);
    if (repairRow ) {
        MonthsToShowReverse.forEach((m, index) => {
            if (index>=0 && index<2) {
              const currentValue = repairRow[m];
              console.log("columna",m);
              const previousValue = index >= 0 ? repairRow[MonthsToShowReverse[index + 1]] : 0;
              console.log("Columna anterior:", MonthsToShowReverse[index + 1]);
              console.log("Valor actual :", currentValue);
              console.log("Valor anterior :", previousValue);
              if (currentValue == 0) {
                const variation = ((currentValue - previousValue) / previousValue) * 100;
              repairRow[`${m}-variation`] = variation.toFixed(0) + '%';
                
              }
              if (currentValue == 0 && previousValue == 0) {
                const variation = 0;
                repairRow[`${m}-variation`] = variation.toFixed(0) + '%';

              }
              if(currentValue != 0){
                const variation = ((currentValue - previousValue) / currentValue) * 100;
                repairRow[`${m}-variation`] = variation.toFixed(0) + '%';

              
            }
              
            }
          
          });
    }
    const emptyRow = rows.find((row) => row.id === `empty_${r.repair_type_id}`);

    MonthsToShowReverse.forEach((m,index)=>{
        if (index>=0 && index<2) {
            const currentValue = emptyRow[m];
            console.log("columna",m);
            const previousValue = index >= 0 ? emptyRow[MonthsToShowReverse[index + 1]] : 0;
            console.log("Columna anterior:", MonthsToShowReverse[index + 1]);
            console.log("Valor actual :", currentValue);
            console.log("Valor anterior :", previousValue);
            if (currentValue == 0) {
              const variation = ((currentValue - previousValue) / previousValue) * 100;
            emptyRow[`${m}-variation`] = variation.toFixed(0) + '%';
              
            }
            if (currentValue == 0 && previousValue == 0) {
              const variation = 0;
              emptyRow[`${m}-variation`] = variation.toFixed(0) + '%';

            }
            if(currentValue != 0){
              const variation = ((currentValue - previousValue) / currentValue) * 100;
              emptyRow[`${m}-variation`] = variation.toFixed(0) + '%';

            
          }
            
          }
        
        
            

    })
  });
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
      </Grid>
      <div style={{ height: 400, width: "100%" }}>
        <DataGrid
          rows={rows}
          columns={columnsWithoutLast}
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

export default Report2;
