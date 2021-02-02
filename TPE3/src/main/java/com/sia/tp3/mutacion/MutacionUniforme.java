package com.sia.tp3.mutacion;

public class MutacionUniforme implements InterfazProbabilidad {

    private double probabilidad;

    public MutacionUniforme(final double probabilidad) {
        this.probabilidad = probabilidad;
    }

    @Override
    public double obtenerProbabilidad(final int numeroDeGeneracion) {
        return probabilidad;
    }
}
