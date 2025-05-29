/*
 * Asignatura: Patrones de Diseño de Software 
 * Patrón Estructural - > Flyweight
 * Tipo de Clase: Java
 */
package com.patron.demo.implementacion;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;

/**
 *
 * @author Fabrizio Bolaño
 */
@Entity
@Table(name = "canciones")
public class Cancion {
    @Id
    @GeneratedValue
    private Long id;

    private String NombreCancion;

    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista autor;

    @Lob
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

    public Cancion() {}

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
