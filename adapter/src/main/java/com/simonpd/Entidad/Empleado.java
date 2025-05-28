package com.simonpd.Entidad;

public class Empleado {
    private long id;
    private String nombre_completo;
    private String direccion;
    private String email;

    public Empleado(long id, String nombreCompleto, String direccion, String email) {
        this.id = id;
        this.nombre_completo = nombreCompleto;
        this.direccion = direccion;
        this.email = email;
    }

    public Empleado() {}

    public long getId() {
        return id;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombreCompleto='" + nombre_completo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
