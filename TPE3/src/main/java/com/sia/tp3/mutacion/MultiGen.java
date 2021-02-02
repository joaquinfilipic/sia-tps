package com.sia.tp3.mutacion;

import com.sia.tp3.Genes;
import com.sia.tp3.Personaje;
import com.sia.tp3.Poblacion;

import java.util.ArrayList;
import java.util.Random;

public class MultiGen implements InterfazMutacion {

    private InterfazProbabilidad probabilidadDeMutacion;
    private Poblacion poblacion;

    public MultiGen(final InterfazProbabilidad probabilidadDeMutacion, final Poblacion poblacion) {
        this.probabilidadDeMutacion = probabilidadDeMutacion;
        this.poblacion = poblacion;
    }

    @Override
    public void hacer(final ArrayList<Personaje> personajes, final int numeroDeGeneracion) {
        Random r = new Random();

        for (final Personaje personaje : personajes) {
            for (int i = 0; i < Genes.CANTIDAD_GENES; i++) {
                if (probabilidadDeMutacion.obtenerProbabilidad(numeroDeGeneracion) >= (1.0) * r.nextDouble()) {
                    switch (i) {
                        case Genes.ARMA:
                            mutarGen(personaje.getGenes()[i],
                                    poblacion.getArmas().get(r.nextInt(poblacion.getArmas().size())).toGen());
                            break;

                        case Genes.BOTA:
                            mutarGen(personaje.getGenes()[i],
                                    poblacion.getBotas().get(r.nextInt(poblacion.getBotas().size())).toGen());
                            break;

                        case Genes.CASCO:
                            mutarGen(personaje.getGenes()[i],
                                    poblacion.getCascos().get(r.nextInt(poblacion.getCascos().size())).toGen());
                            break;

                        case Genes.GUANTE:
                            mutarGen(personaje.getGenes()[i],
                                    poblacion.getGuantes().get(r.nextInt(poblacion.getGuantes().size())).toGen());
                            break;

                        case Genes.PECHERA:
                            mutarGen(personaje.getGenes()[i],
                                    poblacion.getPecheras().get(r.nextInt(poblacion.getPecheras().size())).toGen());
                            break;

                        case Genes.ALTURA:
                            personaje.getGenes()[i][0] = 1.3 + (2.0 - 1.3) * r.nextDouble();
                            break;
                    }
                }
            }
        }
    }

    private void mutarGen(final double[] genOriginal, final double[] genNuevo) {
        System.arraycopy(genNuevo, 0, genOriginal, 0, Genes.CANTIDAD_CARACTERISTICAS);
    }
}
