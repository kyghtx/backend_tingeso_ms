import React, { useState, useEffect } from 'react';
import axios from 'axios';
import gestionService from '../services/gestion-service';

function FormularioReparaciones() {
  const [tiposReparacion, setTiposReparacion] = useState([]);
  const [tiposMotor, setTiposMotor] = useState([]);
  const [precios, setPrecios] = useState([]);

  useEffect(() => {
    obtenerTiposReparacion();
    obtenerTiposMotor();
  }, []);

  const obtenerTiposReparacion = async () => {
    try {
      const response = await gestionService.getRepairList();
      setTiposReparacion(response.data);

      // Inicializar precios con la estructura correcta
      setPrecios(Array(response.data.length).fill().map(() => Array(tiposMotor.length).fill('')));
    } catch (error) {
      console.error('Error al obtener los tipos de reparación:', error);
    }
  };

  const obtenerTiposMotor = async () => {
    try {
      const response = await gestionService.getMotorTypesFromRepairListMs();
      setTiposMotor(response.data);
    } catch (error) {
      console.error('Error al obtener los tipos de motor:', error);
    }
  };

  const handleChangePrecio = (filaIndex, colIndex, event) => {
    const nuevosPrecios = [...precios];
    nuevosPrecios[filaIndex][colIndex] = event.target.value;
    setPrecios(nuevosPrecios);

    // Verificar si se necesita agregar una nueva fila
    if (filaIndex === precios.length - 1 && !nuevosPrecios[filaIndex].some(value => value !== '')) {
      // Agregar una nueva fila
      const nuevaFilaPrecios = new Array(tiposMotor.length).fill('');
      setPrecios([...nuevosPrecios, nuevaFilaPrecios]);
    }
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    const reparacionesConPrecio = [];
    for (let i = 0; i < tiposReparacion.length; i++) {
      for (let j = 0; j < tiposMotor.length; j++) {
        reparacionesConPrecio.push({
          price: precios[i][j],
          repair_type_id: tiposReparacion[i].repair_type_id,
          motor_type_id: tiposMotor[j].motor_type_id
        });
      }
    }
    console.log(reparacionesConPrecio);
    try {
      await gestionService.createRepairPrices(reparacionesConPrecio);
      alert('Reparaciones guardadas exitosamente');
    } catch (error) {
      alert('Error al guardar las reparaciones');
      console.error(error);
    }
  };

  // Función para renderizar los inputs del formulario
  const renderInputs = () => {
    return tiposReparacion.map((reparacion, filaIndex) => (
      <div key={filaIndex}>
        <p>{reparacion.repair_type_name}</p>
        {tiposMotor.map((tipoMotor, colIndex) => (
          <div key={colIndex}>
            <label htmlFor={`precio-${filaIndex}-${colIndex}`}>{tipoMotor.motor_type_name}</label>
            <input
              id={`precio-${filaIndex}-${colIndex}`}
              type="text"
              value={precios[filaIndex][colIndex]}
              onChange={(event) => handleChangePrecio(filaIndex, colIndex, event)}
            />
          </div>
        ))}
      </div>
    ));
  };

  return (
    <form onSubmit={handleSubmit}>
      {renderInputs()}
      <button type="submit">Guardar Reparaciones</button>
    </form>
  );
}

export default FormularioReparaciones;
