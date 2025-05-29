package com.patron.demo.implementacion;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Column;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artistas")
public class Artista {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nombre_artista")
    private String NombreArtista;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cancion> Canciones = new ArrayList<>();

    public Artista() {}

    public Artista(Long id, String nombreArtista) {
        this.id = id;
        NombreArtista = nombreArtista;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombreArtista() {
        return NombreArtista;
    }
    public void setNombreArtista(String nombreArtista) {
        NombreArtista = nombreArtista;
    }

    public List<Cancion> getCanciones() {
        return Canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        Canciones = canciones;
    }

    public void ImprimirLista() {
        String out = "\nPlayList Artista > " + NombreArtista;
        for (Cancion playItem : Canciones) {
            out += "\n\t" + playItem.toString();
        }
        System.out.println(out);
    }
}
