package com.sia.tp3.mutacion;

public class MutacionNoUniforme implements InterfazProbabilidad {

    private double base;

    public MutacionNoUniforme(final double base) {
        this.base = base;
    }

    @Override
    public double obtenerProbabilidad(final int numeroDeGeneracion) {
        return Math.pow(base, numeroDeGeneracion);
    }
}
