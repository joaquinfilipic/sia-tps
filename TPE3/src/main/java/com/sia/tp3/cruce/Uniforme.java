package com.sia.tp3.cruce;

import com.sia.tp3.Genes;
import com.sia.tp3.Personaje;

import java.util.Random;

public class Uniforme implements InterfazCruce {

    private double probabilidad;

    public Uniforme(final double probabilidad) {
        this.probabilidad = probabilidad;
    }

    public double getProbabilidad() {
        return probabilidad;
    }

    @Override
    public void hacer(final Personaje personaje1, final Personaje personaje2) {
        Random r = new Random();
        double[][] aux = new double[Genes.CANTIDAD_GENES][Genes.CANTIDAD_CARACTERISTICAS];
        for (int i = 0; i < Genes.CANTIDAD_GENES; i++) {
            if (probabilidad >= (1.0) * r.nextDouble()) {
                aux[i] = personaje1.getGenes()[i];
                personaje1.getGenes()[i] = personaje2.getGenes()[i];
                personaje2.getGenes()[i] = aux[i];
            }
        }

        personaje1.recalcularDesempenio();
        personaje2.recalcularDesempenio();
    }
}
