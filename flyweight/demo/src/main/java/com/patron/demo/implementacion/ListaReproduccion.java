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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fabrizio Bolaño
 */
@Entity
@Table(name = "listas_reproduccion")
public class ListaReproduccion {
    @Id
    @GeneratedValue
    private Long id;

    private String NombreLista;

    @ManyToMany
    @JoinTable(
        name = "lista_canciones",
        joinColumns = @JoinColumn(name = "lista_id"),
        inverseJoinColumns = @JoinColumn(name = "cancion_id")
    )
    private List<Cancion> Canciones = new ArrayList<>();

    public ListaReproduccion() {}

    public ListaReproduccion(String NombreLista) {
        this.NombreLista = NombreLista;
    }

    public String getNombreLista() {
        return NombreLista;
    }

    public void setNombreLista(String NombreLista) {
        this.NombreLista = NombreLista;
    }

    public List<Cancion> getCanciones() {
        return Canciones;
    }

    public void setCanciones(List<Cancion> Canciones) {
        this.Canciones = Canciones;
    }
    
    public void addCancion(String NombreCancion, String nombreArtista) {
        Canciones.add(FabricaCanciones.CrearItem(NombreCancion, nombreArtista));
    }
    
    public void ImprimirLista() {
        String out = "\nPlayList > " + NombreLista;
        for (Cancion playItem : Canciones) {
            out += "\n\t" + playItem.getNombreCancion() + " - " + playItem.getAutor().getNombreArtista();
        }
        System.out.println(out);
    }
}
