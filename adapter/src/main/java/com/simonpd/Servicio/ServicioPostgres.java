package com.simonpd.Servicio;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.simonpd.Cliente.iServicioEmpleados;
import com.simonpd.Entidad.Empleado;

public class ServicioPostgres implements iServicioEmpleados {
    private static final String HOST = "db.alnvesbehsljlyjxdxue.supabase.co";
    private static final String PORT = "5432";
    private static final String DATABASE = "postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "patronessimon2025";
    
    private Connection getConnection() throws SQLException {
        // Cargar explícitamente el driver
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver PostgreSQL no encontrado", e);
        }
        
        String url = String.format("jdbc:postgresql://%s:%s/%s", HOST, PORT, DATABASE);
        
        Properties props = new Properties();
        props.setProperty("user", USERNAME);
        props.setProperty("password", PASSWORD);
        props.setProperty("ssl", "false"); // Probar sin SSL primero
        props.setProperty("connectTimeout", "30");
        props.setProperty("loginTimeout", "30");
        
        return DriverManager.getConnection(url, props);
    }

    @Override
    public Empleado obtenerEmpleadoPorId(long id) {
        Empleado empleado = null;
        String query = "SELECT id, nombre_completo, direccion, email FROM empleado WHERE id = ?";
        
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setLong(1, id);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nombreCompleto = resultSet.getString("nombre_completo");
                    String direccion = resultSet.getString("direccion");
                    String email = resultSet.getString("email");
                    empleado = new Empleado(id, nombreCompleto, direccion, email);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
        return empleado;
    }
}
