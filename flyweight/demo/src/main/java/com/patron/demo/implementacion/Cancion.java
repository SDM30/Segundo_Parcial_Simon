/*
 * Asignatura: Patrones de Diseño de Software 
 * Patrón Estructural - > Flyweight
 * Tipo de Clase: Java
 */
package com.patron.demo.implementacion;

/**
 *
 * @author Fabrizio Bolaño
 */
public class Cancion {
    private Long id;
    private String NombreCancion;
    private Artista autor;
    private byte[] Cancion = new byte[1000000];
    
    
    public Cancion(Long id, String NombreCancion) {
        this.id = id;
        this.NombreCancion = NombreCancion;
    }


    public Cancion(Long id, String nombreCancion, String nombreArtista) {
        this.id = id;
        NombreCancion = nombreCancion;
        this.autor = FabricaArtistas.CrearItem(nombreArtista);
    }


    public Cancion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCancion() {
        return NombreCancion;
    }

    public void setNombreCancion(String NombreCancion) {
        this.NombreCancion = NombreCancion;
    }

    public Artista getAutor() {
        return autor;
    }


    public void setAutor(String nombreAutor) {
        this.autor = FabricaArtistas.CrearItem(nombreAutor);
    }


    public byte[] getCancion() {
        return Cancion;
    }


    public void setCancion(byte[] cancion) {
        Cancion = cancion;
    }


    @Override
    public String toString() {
        return "Canciòn{" + "id=" + id + ", Tema=" + NombreCancion + '}';
    }   
}

