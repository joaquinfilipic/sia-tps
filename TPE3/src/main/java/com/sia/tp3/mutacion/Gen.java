package com.sia.tp3.mutacion;

import com.sia.tp3.Genes;
import com.sia.tp3.Personaje;
import com.sia.tp3.Poblacion;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Gen implements InterfazMutacion {

    private InterfazProbabilidad probabilidadDeMutacion;
    private Poblacion poblacion;

    public Gen(final InterfazProbabilidad probabilidadDeMutacion, Poblacion poblacion) {
        this.probabilidadDeMutacion = probabilidadDeMutacion;
        this.poblacion = poblacion;
    }

    @Override
    public void hacer(final ArrayList<Personaje> personajes, final int numeroDeGeneracion) {
        Random r = new Random();

        int genAMutar;

        for (final Personaje personaje : personajes) {
            if (probabilidadDeMutacion.obtenerProbabilidad(numeroDeGeneracion) >= (1.0) * r.nextDouble()) {
                genAMutar = ThreadLocalRandom.current().nextInt(0, Genes.CANTIDAD_GENES);
                switch (genAMutar) {
                    case Genes.ARMA:
                        mutarGen(personaje.getGenes()[genAMutar],
                                poblacion.getArmas().get(r.nextInt(poblacion.getArmas().size())).toGen());
                        break;

                    case Genes.BOTA:
                        mutarGen(personaje.getGenes()[genAMutar],
                                poblacion.getBotas().get(r.nextInt(poblacion.getBotas().size())).toGen());
                        break;

                    case Genes.CASCO:
                        mutarGen(personaje.getGenes()[genAMutar],
                                poblacion.getCascos().get(r.nextInt(poblacion.getCascos().size())).toGen());
                        break;

                    case Genes.GUANTE:
                        mutarGen(personaje.getGenes()[genAMutar],
                                poblacion.getGuantes().get(r.nextInt(poblacion.getGuantes().size())).toGen());
                        break;

                    case Genes.PECHERA:
                        mutarGen(personaje.getGenes()[genAMutar],
                                poblacion.getPecheras().get(r.nextInt(poblacion.getPecheras().size())).toGen());
                        break;

                    case Genes.ALTURA:
                        personaje.getGenes()[genAMutar][0] = 1.3 + (2.0 - 1.3) * r.nextDouble();
                        break;

                }
            }
        }
    }

    private void mutarGen(final double[] genOriginal, final double[] genNuevo) {
        System.arraycopy(genNuevo, 0, genOriginal, 0, Genes.CANTIDAD_CARACTERISTICAS);
    }
}