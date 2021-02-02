package com.sia.tp3.corte;

import com.sia.tp3.Personaje;
import com.sia.tp3.Poblacion;

public class Estructura implements InterfazCorte {

    private double porcentaje;
    private int cantidadGeneracionesAVerificar;

    public Estructura(final double porcentaje, final int cantidadGeneracionesAVerificar) {
        this.porcentaje = porcentaje;
        this.cantidadGeneracionesAVerificar = cantidadGeneracionesAVerificar;
    }

    @Override
    public boolean evaluar(final Poblacion poblacion) {
        if (cantidadGeneracionesAVerificar > poblacion.getListHashSetPersonajes().size()) {
            return false;
        }

        int contador;

        for (int i = 0; i < cantidadGeneracionesAVerificar; i++) {

            contador = 0;
            for (Personaje personaje : poblacion.getPersonajes()) {
                String hashPersonaje = personaje.toHash();
                if (poblacion.getListHashSetPersonajes().get(poblacion.getListHashSetPersonajes().size() - 1 - i).contains(hashPersonaje)) {
                    contador++;
                }
            }

            if ((1.0 * contador) / poblacion.getPersonajes().size() < porcentaje) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int getGeneracionesAVerificar() {
        return cantidadGeneracionesAVerificar;
    }
}
