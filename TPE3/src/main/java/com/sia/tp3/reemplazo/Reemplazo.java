package com.sia.tp3.reemplazo;

import com.sia.tp3.Personaje;
import com.sia.tp3.cruce.InterfazCruce;
import com.sia.tp3.seleccion.InterfazSeleccion;

import java.util.*;

public class Reemplazo {

    public InterfazSeleccion seleccion1;
    public InterfazSeleccion seleccion2;
    public InterfazSeleccion seleccion3;
    public InterfazSeleccion seleccion4;
    public InterfazCruce cruce;

    public Reemplazo(final InterfazSeleccion seleccion1, final InterfazSeleccion seleccion2,
                     final InterfazSeleccion seleccion3, final InterfazSeleccion seleccion4,
                     final InterfazCruce cruce) {
        this.seleccion1 = seleccion1;
        this.seleccion2 = seleccion2;
        this.seleccion3 = seleccion3;
        this.seleccion4 = seleccion4;
        this.cruce = cruce;
    }

    public ArrayList<Personaje> seleccionarPadres(final ArrayList<Personaje> personajes, final int cantidadSeleccion1,
                                                  final int cantidadSeleccion2, final int numeroDeGeneracion) {

        return seleccionar(personajes, cantidadSeleccion1, cantidadSeleccion2, numeroDeGeneracion, seleccion1,
                seleccion2);
    }

    public ArrayList<Personaje> seleccionarNuevaGeneracion(final ArrayList<Personaje> personajes,
                                                           final int cantidadSeleccion3, final int cantidadSeleccion4,
                                                           final int numeroDeGeneracion) {

        return seleccionar(personajes, cantidadSeleccion3, cantidadSeleccion4, numeroDeGeneracion, seleccion3,
                seleccion4);
    }

    private ArrayList<Personaje> seleccionar(final ArrayList<Personaje> personajes, final int cantidadSeleccion1,
                                             final int cantidadSeleccion2, final int numeroDeGeneracion,
                                             final InterfazSeleccion seleccion1,
                                             final InterfazSeleccion seleccion2) {

        ArrayList<Personaje> individuosSeleccionados1 = seleccion1.hacer(personajes,
                cantidadSeleccion1, numeroDeGeneracion);
        ArrayList<Personaje> individuosSeleccionados2 = seleccion2.hacer(personajes,
                cantidadSeleccion2, numeroDeGeneracion);

        ArrayList<Personaje> ret = new ArrayList<>();
        ret.addAll(individuosSeleccionados1);
        ret.addAll(individuosSeleccionados2);

        return ret;
    }

    public ArrayList<Personaje> cruzarIndividuos(ArrayList<Personaje> individuosParaCruzar, Double probabilidadCruce) {
        ArrayList<Personaje> nuevaGeneracion = new ArrayList<>();

        Random r = new Random();
        r.setSeed(System.currentTimeMillis());

        boolean par = true;
        int size = individuosParaCruzar.size();

        if ((individuosParaCruzar.size() % 2) != 0) {
            par = false;
            size = individuosParaCruzar.size() + 1;
        }

        int contadorNuevaGeneracion = 0;
        int individuo1;
        int individuo2;
        HashSet<Integer> parejas = new HashSet<>();
        while (contadorNuevaGeneracion < size) {

            do {
                individuo1 = r.nextInt(individuosParaCruzar.size());
                individuo2 = r.nextInt(individuosParaCruzar.size());

                if (individuo1 != individuo2 && !parejas.contains(Objects.hash(individuo1, individuo2))
                        && !parejas.contains(Objects.hash(individuo2, individuo1))) {
                    parejas.add(Objects.hash(individuo1, individuo2));
                    break;
                }
            } while (true);

            // Importante hacer la copia, para no pisar los padres originales
            nuevaGeneracion.add(individuosParaCruzar.get(individuo1).copy());
            nuevaGeneracion.add(individuosParaCruzar.get(individuo2).copy());

            if (probabilidadCruce >= (1.0) * r.nextDouble()){

                cruce.hacer(nuevaGeneracion.get(contadorNuevaGeneracion),
                        nuevaGeneracion.get(contadorNuevaGeneracion + 1));
            }

            contadorNuevaGeneracion += 2;
        }

        if (!par) {
            nuevaGeneracion.remove(size - 1);
        }

        return nuevaGeneracion;
    }

    public void recalcularTodosLosDesempenios(ArrayList<Personaje> personajes) {

        for (Personaje personaje : personajes) {
            personaje.recalcularDesempenio();
        }
    }
}
