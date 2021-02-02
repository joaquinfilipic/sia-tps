package com.sia.tp3.corte;

import com.sia.tp3.Poblacion;

public class MaximaCantidadDeGeneraciones implements InterfazCorte {

    private int maximaCantidadDeGeneraciones;

    public MaximaCantidadDeGeneraciones(final int maximaCantidadDeGeneraciones) {
        this.maximaCantidadDeGeneraciones = maximaCantidadDeGeneraciones;
    }

    public int getMaximaCantidadDeGeneraciones() {
        return maximaCantidadDeGeneraciones;
    }

    @Override
    public boolean evaluar(final Poblacion poblacion) {
        return maximaCantidadDeGeneraciones == poblacion.getNumeroDeGeneracion();
    }

    @Override
    public int getGeneracionesAVerificar() {
        return 0;
    }
}
