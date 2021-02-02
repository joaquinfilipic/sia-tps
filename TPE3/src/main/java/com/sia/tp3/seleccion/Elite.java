package com.sia.tp3.seleccion;

import com.sia.tp3.Personaje;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Elite implements InterfazSeleccion {

    private boolean usaBoltzmann;
    private int generaciones;

    public Elite(final boolean usaBoltzmann, final int generaciones) {
        this.usaBoltzmann = usaBoltzmann;
        this.generaciones = generaciones;
    }

    @Override
    public ArrayList<Personaje> hacer(final ArrayList<Personaje> personajes, final int cantidad,
                                      final int numeroDeGeneracion) {

        ArrayList<Personaje> aux = personajes;

        if (usaBoltzmann) {
            aux = new ArrayList<>();
            for (Personaje personaje : personajes) {
                aux.add(personaje.copy());
            }

            Boltzmann boltzmann = new Boltzmann(generaciones);
            boltzmann.hacer(aux, numeroDeGeneracion);
        }

        ArrayList<Personaje> ret = new ArrayList<>();

        PriorityQueue<Personaje> priorityQueue =
                new PriorityQueue<>((personaje1, personaje2) -> Double.compare(personaje2.getDesempenio(),
                        personaje1.getDesempenio()));

        priorityQueue.addAll(aux);

        for (int i = 0; i < cantidad; i++) {
            ret.add(priorityQueue.remove());
        }

        if (usaBoltzmann) {

            for (Personaje p : ret) {
                p.recalcularDesempenio();
            }
        }

        return ret;
    }
}
