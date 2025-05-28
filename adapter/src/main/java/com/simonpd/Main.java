package com.simonpd;

import com.simonpd.Adaptador.AdapterEmpleados;
import com.simonpd.Servicio.ServicioPostgres;

public class Main {
    public static void main(String[] args) {
        System.out.println(
                """
                    PARCIAL 2 - SIMON DIAZ
                    PATRON BASE - ADAPTER
                """
            );

        // Crear instancia del adaptador postgres
        AdapterEmpleados consultaPostgres = new AdapterEmpleados("postgres");

        // Consultar empleados
        System.out.println("Empleado con id = 1 en Postgres:");
        System.out.println(consultaPostgres.obtenerEmpleadoPorId(1));
        System.out.println("Empleado con id = 10 en Postgres:");
        System.out.println(consultaPostgres.obtenerEmpleadoPorId(10));

        // Crear instancia de adaptdor rest
        AdapterEmpleados consultaRest = new AdapterEmpleados("rest");

        // Consultar empleados
        System.out.println("Empleado con id = 1 en REST:");
        System.out.println(consultaRest.obtenerEmpleadoPorId(1));
        System.out.println("Empleado con id = 10 en REST:");
        System.out.println(consultaRest.obtenerEmpleadoPorId(10));


    }
}