package com.example.tone.asogal.Model;

/**
 * Created by antonio.reino on 22/02/2018.
 */

public class Equipo {
    private String IdEquipo,Nombre,Localidad,IdEntrenador,Telefono,Escudo;

    public Equipo() {
    }

    public String getIdEquipo() {

        return IdEquipo;
    }

    public void setIdEquipo(String idEquipo) {
        IdEquipo = idEquipo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getLocalidad() {
        return Localidad;
    }

    public void setLocalidad(String localidad) {
        Localidad = localidad;
    }

    public String getIdEntrenador() {
        return IdEntrenador;
    }

    public void setIdEntrenador(String idEntrenador) {
        IdEntrenador = idEntrenador;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getEscudo() {
        return Escudo;
    }

    public void setEscudo(String escudo) {
        Escudo = escudo;
    }
}
