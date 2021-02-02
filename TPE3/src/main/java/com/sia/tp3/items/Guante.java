package com.sia.tp3.items;

import com.sia.tp3.Genes;

public class Guante {

    private int id;
    private double fuerza;
    private double agilidad;
    private double pericia;
    private double resistencia;
    private double vida;

    public Guante(final int id, final double fuerza, final double agilidad, final double pericia,
                  final double resistencia, final double vida) {
        this.id = id;
        this.fuerza = fuerza;
        this.agilidad = agilidad;
        this.pericia = pericia;
        this.resistencia = resistencia;
        this.vida = vida;
    }

    public int getId() {
        return id;
    }

    public double getFuerza() {
        return fuerza;
    }

    public double getAgilidad() {
        return agilidad;
    }

    public double getPericia() {
        return pericia;
    }

    public double getResistencia() {
        return resistencia;
    }

    public double getVida() {
        return vida;
    }

    public double[] toGen() {
        double[] ret = new double[Genes.CANTIDAD_GENES];
        ret[0] = fuerza;
        ret[1] = agilidad;
        ret[2] = pericia;
        ret[3] = resistencia;
        ret[4] = vida;
        ret[5] = id;

        return ret;
    }
}
