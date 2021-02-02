package com.sia.tp3.seleccion;

import com.sia.tp3.Personaje;

import java.util.ArrayList;

public class Boltzmann {

    private int generaciones;

    public Boltzmann(final int generaciones) {
        this.generaciones = generaciones;
    }

    public ArrayList<Personaje> hacer(final ArrayList<Personaje> personajes, final int generacion) {

        double temperatura = temperatura((double) generacion);
        double promedio = calcularPromedio(personajes, temperatura);

        recalcularDesempenio(personajes, temperatura, promedio);

        return personajes;
    }

    private double calcularPromedio(final ArrayList<Personaje> personajes, final double temperatura) {

        double suma = 0.0;

        for (Personaje p : personajes) {
            suma += Math.exp(p.getDesempenio() / temperatura);
        }

        return suma / personajes.size();
    }

    private double temperatura(final double generacion) {
        return generaciones / generacion;
    }

    private void recalcularDesempenio(final ArrayList<Personaje> personajes, final double temperatura,
                                      final double promedio) {

        double func;
        for (Personaje p : personajes) {

            func = Math.exp(p.getDesempenio() / temperatura) / promedio;
            p.setDesempenio(func);
        }
    }
}