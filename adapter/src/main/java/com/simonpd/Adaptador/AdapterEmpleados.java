package com.simonpd.Adaptador;

import com.simonpd.Cliente.iServicioEmpleados;
import com.simonpd.Entidad.Empleado;
import com.simonpd.Servicio.ServicioPostgres;
import com.simonpd.Servicio.ServicioREST;

public class AdapterEmpleados implements iServicioEmpleados {
    private ServicioPostgres servicioPostgres;
    private ServicioREST servicioREST;
    private String tipoServicio;

    public AdapterEmpleados(String tipoServicio) {
        this.tipoServicio = tipoServicio.toLowerCase();
        this.servicioPostgres = new ServicioPostgres();
        this.servicioREST = new ServicioREST();
    }

    // Adaptador para ServicioPostgres (firma: obtenerEmpleadoPorId(long id))
    @Override
    public Empleado obtenerEmpleadoPorId(long id) {
        if ("postgres".equals(tipoServicio)) {
            return servicioPostgres.obtenerEmpleadoPorId(id);
        } else if ("rest".equals(tipoServicio)) {
            // Adaptar la llamada a la firma diferente de REST
            return obtenerEmpleadoPorCodigo(String.valueOf(id));
        } else {
            throw new IllegalArgumentException("Tipo de servicio no soportado: " + tipoServicio);
        }
    }

    // Firma diferente para REST (por ejemplo, recibe String)
    public Empleado obtenerEmpleadoPorCodigo(String codigo) {
        if ("rest".equals(tipoServicio)) {
            return servicioREST.obtenerEmpleadoPorCodigo(codigo);
        } else if ("postgres".equals(tipoServicio)) {
            // Adaptar la llamada a la firma diferente de Postgres
            try {
                long id = Long.parseLong(codigo);
                return servicioPostgres.obtenerEmpleadoPorId(id);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Código inválido para Postgres: " + codigo);
            }
        } else {
            throw new IllegalArgumentException("Tipo de servicio no soportado: " + tipoServicio);
        }
    }
}
