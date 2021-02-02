package com.sia.tp3;

public class Multiplicador {

    private Double fuerza;
    private Double agilidad;
    private Double pericia;
    private Double resistencia;
    private Double vida;

    public Multiplicador(Double fuerza, Double agilidad, Double pericia, Double resistencia, Double vida) {
        this.fuerza = fuerza;
        this.agilidad = agilidad;
        this.pericia = pericia;
        this.resistencia = resistencia;
        this.vida = vida;
    }

    public Double getFuerza() {
        return fuerza;
    }

    public void setFuerza(Double fuerza) {
        this.fuerza = fuerza;
    }

    public Double getAgilidad() {
        return agilidad;
    }

    public void setAgilidad(Double agilidad) {
        this.agilidad = agilidad;
    }

    public Double getPericia() {
        return pericia;
    }

    public void setPericia(Double pericia) {
        this.pericia = pericia;
    }

    public Double getResistencia() {
        return resistencia;
    }

    public void setResistencia(Double resistencia) {
        this.resistencia = resistencia;
    }

    public Double getVida() {
        return vida;
    }

    public void setVida(Double vida) {
        this.vida = vida;
    }
}
