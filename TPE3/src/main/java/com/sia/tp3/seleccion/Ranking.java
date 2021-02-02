package com.sia.tp3.seleccion;

import com.sia.tp3.Personaje;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class Ranking implements InterfazSeleccion {

    private boolean usaBoltzmann;
    private int generaciones;

    public Ranking(final boolean usaBoltzmann, final int generaciones) {
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

        ordenarPersonajes(aux);
        PriorityQueue<Double> numerosAleatorios = generarAleatorios(cantidad);

        calcularDesempenioAcumulado(aux, (double) (aux.size() * (aux.size() + 1)) / 2);

        ArrayList<Personaje> ret = seleccionarPersonajes(aux, numerosAleatorios);

        if (usaBoltzmann) {

            for (Personaje p : ret) {
                p.recalcularDesempenio();
            }
        }

        return ret;
    }

    private void ordenarPersonajes(final ArrayList<Personaje> personajes) {

        personajes.sort(Comparator.comparingDouble(Personaje::getDesempenio));
    }

    private PriorityQueue<Double> generarAleatorios(final int cantidad) {

        PriorityQueue<Double> numeroAleatorios = new PriorityQueue<>(Double::compare);
        Random r = new Random();
        double aleatorio;
        int contador = cantidad;

        while (contador != 0) {

            do {
                aleatorio = (1.0) * r.nextDouble();

                if (!numeroAleatorios.contains(aleatorio)) {
                    numeroAleatorios.add(aleatorio);
                    break;
                }
            } while (true);

            contador--;
        }

        return numeroAleatorios;
    }

    private void calcularDesempenioAcumulado(final ArrayList<Personaje> personajes, final double sumaDesempenioTotal) {

        double acumulado = 0.0;
        int i = 1;
        for (Personaje p : personajes) {
            acumulado += i / sumaDesempenioTotal;
            p.setDesempenioAcumulado(acumulado);
            i++;
        }
    }

    private ArrayList<Personaje> seleccionarPersonajes(final ArrayList<Personaje> personajes,
                                                       final PriorityQueue<Double> numerosAleatorios) {

        ArrayList<Personaje> ret = new ArrayList<>();
        double aleatorio;
        int i = 0;

        while (!numerosAleatorios.isEmpty()) {

            aleatorio = numerosAleatorios.remove();

            while (i < personajes.size()) {

                if (personajes.get(i).getDesempenioAcumulado() > aleatorio) {

                    ret.add(personajes.get(i));
                    break;
                } else
                    i++;
            }
        }
        return ret;
    }
}
