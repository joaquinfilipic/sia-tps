package com.sia.tp3.cruce;

import com.sia.tp3.Genes;
import com.sia.tp3.Personaje;

import java.util.concurrent.ThreadLocalRandom;

public class EnDosPuntos implements InterfazCruce {

    public EnDosPuntos() {
    }


    @Override
    public void hacer(final Personaje personaje1, final Personaje personaje2) {

        int locus1 = generarLocus1();
        int locus2 = generarLocus2(locus1);

        double[][] aux = new double[Genes.CANTIDAD_GENES][Genes.CANTIDAD_CARACTERISTICAS];
        for (int i = locus1; i <= locus2; i++) {
            aux[i] = personaje1.getGenes()[i];
            personaje1.getGenes()[i] = personaje2.getGenes()[i];
            personaje2.getGenes()[i] = aux[i];
        }

        personaje1.recalcularDesempenio();
        personaje2.recalcularDesempenio();
    }

    private int generarLocus1(){
        return ThreadLocalRandom.current().nextInt(0, Genes.CANTIDAD_GENES);
    }

    private int generarLocus2(int locus1){
        return ThreadLocalRandom.current().nextInt(locus1, Genes.CANTIDAD_GENES);
    }
}
