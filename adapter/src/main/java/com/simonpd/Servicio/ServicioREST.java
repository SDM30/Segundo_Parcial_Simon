package com.simonpd.Servicio;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;

import com.simonpd.Cliente.iServicioEmpleados;
import com.simonpd.Entidad.Empleado;

import java.io.InputStream;

public class ServicioREST implements iServicioEmpleados {
    private static final String URL = "https://retoolapi.dev/7oT6eo/data";

    @Override
    public Empleado obtenerEmpleadoPorId(long id) {
        Empleado empleado = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(URL + "/" + id);
            ClassicHttpResponse response = httpClient.execute(request);

            if (response.getCode() == 200) {
                InputStream content = response.getEntity().getContent();
                ObjectMapper mapper = new ObjectMapper();
                empleado = mapper.readValue(content, Empleado.class);
            } else {
                System.out.println("Error: " + response.getCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleado;
    }
}