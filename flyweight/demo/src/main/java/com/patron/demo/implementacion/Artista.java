package com.patron.demo.implementacion;

import java.util.ArrayList;
import java.util.List;

public class Artista {
    private Long id;
    private String NombreArtista;
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
