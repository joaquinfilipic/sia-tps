package com.sia.tp3.corte;

import com.sia.tp3.Poblacion;

public class Optimo implements InterfazCorte {

    private double solucionOptima;

    public Optimo(final double solucionOptima) {
        this.solucionOptima = solucionOptima;
    }

    @Override
    public boolean evaluar(final Poblacion poblacion) {
        return poblacion.getMejorDesempenio() >= solucionOptima;
    }

    @Override
    public int getGeneracionesAVerificar() {
        return 0;
    }
}
