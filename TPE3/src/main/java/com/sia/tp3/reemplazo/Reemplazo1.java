package com.sia.tp3.reemplazo;

import com.sia.tp3.Personaje;
import com.sia.tp3.cruce.InterfazCruce;
import com.sia.tp3.mutacion.InterfazMutacion;
import com.sia.tp3.seleccion.InterfazSeleccion;

import java.util.ArrayList;

public class Reemplazo1 extends Reemplazo implements InterfazReemplazo {

    private double modificadorA;
    private double modificadorB;
    private int k;
    private InterfazMutacion mutacion;
    private double probabilidadCruce;

    public Reemplazo1(final InterfazSeleccion seleccion1, final InterfazSeleccion seleccion2,
                      final InterfazSeleccion seleccion3, final InterfazSeleccion seleccion4,
                      final InterfazCruce cruce, final double modificadorA, final double modificadorB, final int k,
                      final InterfazMutacion mutacion, final Double probabilidadCruce) {
        super(seleccion1, seleccion2, seleccion3, seleccion4, cruce);
        this.modificadorA = modificadorA;
        this.modificadorB = modificadorB;
        this.k = k;
        this.mutacion = mutacion;
        this.probabilidadCruce = probabilidadCruce;
    }

    @Override
    public ArrayList<Personaje> hacer(final ArrayList<Personaje> personajes, int numeroDeGeneracion) {

    	int N = personajes.size();
        int cantidadSeleccion1 = new Double(N * modificadorA).intValue();
        int cantidadSeleccion2 = N - cantidadSeleccion1;
        
        ArrayList<Personaje> individuosParaCruzar = seleccionarPadres(personajes, cantidadSeleccion1,
                cantidadSeleccion2, numeroDeGeneracion);

        ArrayList<Personaje> individuosCruzados = cruzarIndividuos(individuosParaCruzar, probabilidadCruce);
        mutacion.hacer(individuosCruzados, numeroDeGeneracion);

        recalcularTodosLosDesempenios(individuosCruzados);
        
        return individuosCruzados;
        
    	/*int cantidadSeleccion1 = new Double(k * modificadorA).intValue();
        int cantidadSeleccion2 = k - cantidadSeleccion1;

        ArrayList<Personaje> individuosParaCruzar = seleccionarPadres(personajes, cantidadSeleccion1,
                cantidadSeleccion2, numeroDeGeneracion);

        ArrayList<Personaje> individuosCruzados = cruzarIndividuos(individuosParaCruzar, probabilidadCruce);
        mutacion.hacer(individuosCruzados, numeroDeGeneracion);

        recalcularTodosLosDesempenios(individuosCruzados);

        cantidadSeleccion1 = new Double(k * modificadorB).intValue();
        cantidadSeleccion2 = k - cantidadSeleccion1;

        ArrayList<Personaje> ret = new ArrayList<>(seleccionarNuevaGeneracion(individuosCruzados, cantidadSeleccion1,
                cantidadSeleccion2, numeroDeGeneracion));

        return ret;*/
    }
}
