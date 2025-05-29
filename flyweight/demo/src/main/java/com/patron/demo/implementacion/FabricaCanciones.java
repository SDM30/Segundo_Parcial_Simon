/*
 * Asignatura: Patrones de Diseño de Software 
 * Patrón Estructural - > Flyweight
 * Tipo de Clase: Java
 */
package com.patron.demo.implementacion;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author Fabrizio Bolaño
 */
public class FabricaCanciones {

    public static boolean HabilitarFlyweight = true;
    private static final Map<String, Cancion> PLAY_CANCION = new HashMap<>();
    private static Long Secuencia = 0L;

    public static Cancion CrearItem(String NombreTema, String NombreArtista) {
        String clave = NombreTema + "|" + NombreArtista;
        if (HabilitarFlyweight && PLAY_CANCION.containsKey(clave)) {
            return PLAY_CANCION.get(clave);
        } else {
            Cancion playItem = new Cancion(++Secuencia, NombreTema, NombreArtista);
            PLAY_CANCION.put(clave, playItem);
            return playItem;
        }
    }
}