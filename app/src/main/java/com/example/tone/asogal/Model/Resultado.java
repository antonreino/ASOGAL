package com.example.tone.asogal.Model;

/**
 * Created by antonio.reino on 22/02/2018.
 */

public class Resultado {
    private String GolesLocales,GolesVisitantes,PalosLocales,PalosVisitantes,idResultado,ResultadoFinal;

    public Resultado() {
    }

    public String getGolesLocales() {

        return GolesLocales;
    }

    public void setGolesLocales(String golesLocales) {
        GolesLocales = golesLocales;
    }

    public String getGolesVisitantes() {
        return GolesVisitantes;
    }

    public void setGolesVisitantes(String golesVisitantes) {
        GolesVisitantes = golesVisitantes;
    }

    public String getPalosLocales() {
        return PalosLocales;
    }

    public void setPalosLocales(String palosLocales) {
        PalosLocales = palosLocales;
    }

    public String getPalosVisitantes() {
        return PalosVisitantes;
    }

    public void setPalosVisitantes(String palosVisitantes) {
        PalosVisitantes = palosVisitantes;
    }

    public String getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(String idResultado) {
        this.idResultado = idResultado;
    }

    public String getResultadoFinal() {
        return ResultadoFinal;
    }

    public void setResultadoFinal(String resultadoFinal) {
        ResultadoFinal = resultadoFinal;
    }
}
