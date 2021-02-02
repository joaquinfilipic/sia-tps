package com.sia.tp3.cruce;

import com.sia.tp3.Genes;
import com.sia.tp3.Personaje;

import java.util.concurrent.ThreadLocalRandom;

public class Anular implements InterfazCruce {

    public Anular() {
    }

    @Override
    public void hacer(Personaje personaje1, Personaje personaje2) {

        int locus1 = generarLocus1();
        int contador = generarSegmento();

        double[][] aux = new double[Genes.CANTIDAD_GENES][Genes.CANTIDAD_CARACTERISTICAS];
        for (int i = locus1; 0 < contador; i++) {
            aux[i % Genes.CANTIDAD_GENES] = personaje1.getGenes()[i % Genes.CANTIDAD_GENES];
            personaje1.getGenes()[i % Genes.CANTIDAD_GENES] = personaje2.getGenes()[i % Genes.CANTIDAD_GENES];
            personaje2.getGenes()[i % Genes.CANTIDAD_GENES] = aux[i % Genes.CANTIDAD_GENES];
            contador--;
        }

        personaje1.recalcularDesempenio();
        personaje2.recalcularDesempenio();
    }

    private int generarLocus1(){
        return ThreadLocalRandom.current().nextInt(0, Genes.CANTIDAD_GENES);
    }

    private int generarSegmento(){
        return ThreadLocalRandom.current().nextInt(1, (Genes.CANTIDAD_GENES/2) + 1);
    }
}
