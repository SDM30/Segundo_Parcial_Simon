package com.patron.demo.Repositorio;

import com.patron.demo.implementacion.ListaReproduccion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Properties;
import com.patron.demo.implementacion.Cancion;

public class ConexionPostgres {

    private static final String HOST = "db.alnvesbehsljlyjxdxue.supabase.co";
    private static final String PORT = "5432";
    private static final String DATABASE = "postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "patronessimon2025";

    private static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver PostgreSQL no encontrado", e);
        }
        String url = String.format("jdbc:postgresql://%s:%s/%s", HOST, PORT, DATABASE);
        Properties props = new Properties();
        props.setProperty("user", USERNAME);
        props.setProperty("password", PASSWORD);
        props.setProperty("ssl", "false");
        props.setProperty("connectTimeout", "30");
        props.setProperty("loginTimeout", "30");
        return DriverManager.getConnection(url, props);
    }

    public static void guardarListaReproduccionManual(com.patron.demo.implementacion.ListaReproduccion lista) {
        String insertLista = "INSERT INTO listas_reproduccion (nombre_lista) VALUES (?) RETURNING id";
        String insertRelacion = "INSERT INTO lista_canciones (lista_id, cancion_id) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement psLista = conn.prepareStatement(insertLista);
        ) {
            conn.setAutoCommit(false);
            psLista.setString(1, lista.getNombreLista());
            ResultSet rs = psLista.executeQuery();
            Long listaId = null;
            if (rs.next()) {
                listaId = rs.getLong(1);
                System.out.println("Lista insertada con id: " + listaId);
            }
            rs.close();

            try (PreparedStatement psRelacion = conn.prepareStatement(insertRelacion)) {
                for (Cancion cancion : lista.getCanciones()) {
                    psRelacion.setLong(1, listaId);
                    psRelacion.setLong(2, cancion.getId());
                    psRelacion.addBatch();
                }
                psRelacion.executeBatch();
                System.out.println("Relaciones lista-canción insertadas.");
            }
            conn.commit();
        } catch (SQLException e) {
            //System.out.println("Error al guardar la lista: " + e.getMessage());
        }
    }
}
