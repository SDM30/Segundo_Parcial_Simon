package com.simonpd.Adaptador;

import com.simonpd.Cliente.iServicioEmpleados;
import com.simonpd.Entidad.Empleado;
import com.simonpd.Servicio.ServicioPostgres;
import com.simonpd.Servicio.ServicioREST;

public class AdapterEmpleados implements iServicioEmpleados {
    private iServicioEmpleados servicioEmpleados;

    public AdapterEmpleados(String tipoServicio) {
        if ("postgres".equalsIgnoreCase(tipoServicio)) {
            this.servicioEmpleados = new ServicioPostgres();
        } else if ("rest".equalsIgnoreCase(tipoServicio)) {
            this.servicioEmpleados = new ServicioREST();
        } else {
            throw new IllegalArgumentException("Tipo de servicio no soportado: " + tipoServicio);
        }
    }

    public Empleado obtenerEmpleadoPorId(long id) {
        return servicioEmpleados.obtenerEmpleadoPorId(id);
    }
    
}
