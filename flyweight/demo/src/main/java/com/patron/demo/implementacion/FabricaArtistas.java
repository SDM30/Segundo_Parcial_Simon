package com.patron.demo.implementacion;

import java.util.HashMap;
import java.util.Map;

public class FabricaArtistas {

    public static boolean HabilitarFlyweight = true;
    private static final Map<String, Artista> ARTISTAS = new HashMap<>();
    private static Long Secuencia = 0L;

    public static Artista CrearItem(String nombreArtista) {
        if (HabilitarFlyweight && ARTISTAS.containsKey(nombreArtista)) {
            return ARTISTAS.get(nombreArtista);
        } else {
            Artista artista = new Artista(++Secuencia, nombreArtista);
            ARTISTAS.put(nombreArtista, artista);
            return artista;
        }
    }
}
