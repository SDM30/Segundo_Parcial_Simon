/*
 * Asignatura: Patrones de Diseño de Software 
 * Patrón Estructural - > Flyweight
 * Tipo de Clase: Main()
 */
package com.patron.demo.flyweight;

import java.util.*;

import com.patron.demo.Repositorio.ConexionPostgres;
import com.patron.demo.implementacion.Cancion;
import com.patron.demo.implementacion.FabricaArtistas;
import com.patron.demo.implementacion.FabricaCanciones;
import com.patron.demo.implementacion.ListaReproduccion;


/**
 *
 * @author Fabrizio Bolaño
 */
public class FlyweightMain {

    private static final String[] NombreCanciones = new String[2000];
    private static final String[] NombreArtistas = new String[1000];
    private static final String[] NombresListas = new String[41000];
    private static final List<ListaReproduccion> Listas = new ArrayList<>();
        
    public static void main(String[] args) {
        System.out.println(""
            + "Proceso de creación de listas de reproducción iniciado,\n"
            + "este proceso puede ser muy retrasado debido a la gran cantidad de objetos\n"
            + "que se creará, por favor espere un momento hasta que \n"
            + "sea notificado de que el proceso ha terminado..");
        
        Runtime runtime = Runtime.getRuntime();
        System.out.println("MaxMemory > " + (runtime.maxMemory()/1000000));
        habilitarFlyweight(true);
        InicializarArreglos();
        CrearListaDinamica();
        System.out.println("Total Listas > " + Listas.size());
        long memoryUsed = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memoria Usada => " + (memoryUsed / 1000000));
        Listas.get(0).ImprimirLista();
        liberarListasMenosUsadas(1000000, runtime, 30);
        
    }
    
    private static void CrearListaDinamica() {
        Random random = new Random();
        int p = 0;
        for (int c = 0; c < NombresListas.length; c++) {
            ListaReproduccion playList = new ListaReproduccion(NombresListas[c]);
            for (int i = 0; i < 10; i++) {
                int song = random.nextInt(NombreCanciones.length);
                String nombreCancion = NombreCanciones[song];
                String nombreArtista = NombreArtistas[song % 50];
                playList.addCancion(nombreCancion, nombreArtista);
            }
            Listas.add(playList);
            if(c!=0 && (c+1)%(NombresListas.length/10)==0){
                p+=10;
                System.out.println("Finalizado "+ p +"%");
                System.out.println("Listas Creadas " + Listas.size());
            }
        }
    }
        
    private static void InicializarArreglos() {
        for (int c = 0; c < NombreCanciones.length; c++) {
            NombreCanciones[c] = "Song " + (c + 1); 
        }

        for (int c = 0; c < NombreArtistas.length; c++) {
            NombreArtistas[c] = "Artist " + (c + 1);
        }

        for (int c = 0; c < NombresListas.length; c++) {
            NombresListas[c] = "PlayList " + (c + 1);
        }
    }

    public static void habilitarFlyweight(boolean habilitarFlyweight) {
        FabricaArtistas.HabilitarFlyweight = habilitarFlyweight;
        FabricaCanciones.HabilitarFlyweight = habilitarFlyweight;
    }

    public static List<ListaReproduccion> obtenerListasConCancionesMenosRepetidas(
            List<ListaReproduccion> listas,
            long topN
    ) {
        // Paso 1: Obtener las canciones más repetidas
        Map<String, Integer> cancionRepeticion = new HashMap<>();
        for (ListaReproduccion lista : listas) {
            for (Cancion cancion : lista.getCanciones()) {
                String clave = cancion.getNombreCancion() + "|" + cancion.getAutor().getNombreArtista();
                int valor = cancionRepeticion.getOrDefault(clave, 0);
                cancionRepeticion.put(clave, valor + 1);
            }
        }

        List<String> topCanciones = cancionRepeticion.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(topN)
                .map(entradaMap -> entradaMap.getKey())
                .toList();

        List<Integer> topRepeticiones = cancionRepeticion.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(topN)
                .map(entradaMap -> entradaMap.getValue())
                .toList();

        System.out.println("Canciones y repeticiones en fase:");
        for (int i = 0; i < topCanciones.size(); i++) {
            System.out.println("Canción: " + topCanciones.get(i) + " - Repeticiones: " + topRepeticiones.get(i));
        }

        // Paso 2: Buscar listas que no contengan las canciones más repetidas
        List<ListaReproduccion> resultado = new ArrayList<>();
        for (ListaReproduccion lista : listas) {
            boolean contieneCancionRepetida = false;
            for (Cancion cancion : lista.getCanciones()) {
                String clave = cancion.getNombreCancion() + "|" + cancion.getAutor().getNombreArtista();
                if (topCanciones.contains(clave)) {
                    contieneCancionRepetida = true;
                    break;
                }
            }
            if (!contieneCancionRepetida) {
                resultado.add(lista);
            }
        }
        return resultado;
    }

    public static void liberarListasMenosUsadas(long memoriaMaximaBytes, Runtime runtime, long topN) {
        System.out.println("Liberando listas menos usadas si la memoria usada supera " + (memoriaMaximaBytes / 1000000) + " MB");
        long memoriaUsadaBytes = runtime.totalMemory() - runtime.freeMemory();
        if (memoriaUsadaBytes > memoriaMaximaBytes) {
            List<ListaReproduccion> listasMenosUsadas = obtenerListasConCancionesMenosRepetidas(Listas, topN);

            // Elimina todas de la lista principal de una vez
            Listas.removeAll(listasMenosUsadas);

            // Inserta todas en la base de datos
            for (ListaReproduccion lista : listasMenosUsadas) {
                //SSConexionPostgres.guardarListaReproduccionManual(lista);
            }

            System.gc();
            long memoriaUsadaDespues = runtime.totalMemory() - runtime.freeMemory();
            System.out.println("Listas menos usadas liberadas. Memoria usada: " + (memoriaUsadaDespues / 1000000) + " MB");
            long memoriaLiberada = memoriaUsadaBytes - memoriaUsadaDespues;
            System.out.println("Memoria liberada: " + (memoriaLiberada / 1000000) + " MB");
        }
    }
}
