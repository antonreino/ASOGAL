package com.example.tone.asogal.Model;

/**
 * Created by antonio.reino on 22/02/2018.
 */

public class Partido {
    private String IdPartido,Liga,IdEquipoLocal,IdEquipoVisitante,IdActa,IdArbitro,Campo,IdResultado,HoraInicio, HoraFinal;

    public Partido() {
    }

    public String getIdPartido() {

        return IdPartido;
    }

    public void setIdPartido(String idPartido) {
        IdPartido = idPartido;
    }

    public String getLiga() {
        return Liga;
    }

    public void setLiga(String liga) {
        Liga = liga;
    }

    public String getIdEquipoLocal() {
        return IdEquipoLocal;
    }

    public void setIdEquipoLocal(String idEquipoLocal) {
        IdEquipoLocal = idEquipoLocal;
    }

    public String getIdEquipoVisitante() {
        return IdEquipoVisitante;
    }

    public void setIdEquipoVisitante(String idEquipoVisitante) {
        IdEquipoVisitante = idEquipoVisitante;
    }

    public String getIdActa() {
        return IdActa;
    }

    public void setIdActa(String idActa) {
        IdActa = idActa;
    }

    public String getIdArbitro() {
        return IdArbitro;
    }

    public void setIdArbitro(String idArbitro) {
        IdArbitro = idArbitro;
    }

    public String getCampo() {
        return Campo;
    }

    public void setCampo(String campo) {
        Campo = campo;
    }

    public String getIdResultado() {
        return IdResultado;
    }

    public void setIdResultado(String idResultado) {
        IdResultado = idResultado;
    }

    public String getHoraInicio() {
        return HoraInicio;
    }

    public void setHoraInicio(String horaInicio) {
        HoraInicio = horaInicio;
    }

    public String getHoraFinal() {
        return HoraFinal;
    }

    public void setHoraFinal(String horaFinal) {
        HoraFinal = horaFinal;
    }
}
