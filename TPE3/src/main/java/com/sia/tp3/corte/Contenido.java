package com.sia.tp3.corte;

import com.sia.tp3.Poblacion;

public class Contenido implements InterfazCorte {

    private int cantidadGeneracionesAVerificar;

    public Contenido(final int cantidadGeneracionesAVerificar) {
        this.cantidadGeneracionesAVerificar = cantidadGeneracionesAVerificar;
    }

    @Override
    public boolean evaluar(final Poblacion poblacion) {

        if (cantidadGeneracionesAVerificar == 0) {
            return true;
        }

        if (poblacion.getMejoresDesempenios().size() < cantidadGeneracionesAVerificar || poblacion.getMejoresDesempenios().size() - 2 < 0) {
            return false;
        }

        double aux = poblacion.getMejoresDesempenios().get(poblacion.getMejoresDesempenios().size() - 1);
        int auxCantidadDeGeneracionesAVerificar = 1;
        for (int i = poblacion.getMejoresDesempenios().size() - 2; auxCantidadDeGeneracionesAVerificar <= cantidadGeneracionesAVerificar && i >= 0; i--) {
            auxCantidadDeGeneracionesAVerificar++;
            if (aux != poblacion.getMejoresDesempenios().get(i)) {
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
