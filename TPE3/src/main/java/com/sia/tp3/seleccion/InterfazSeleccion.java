package com.sia.tp3.seleccion;

import com.sia.tp3.Personaje;

import java.util.ArrayList;

public interface InterfazSeleccion {

    public ArrayList<Personaje> hacer(final ArrayList<Personaje> personajes, final int cantidad, final int numeroDeGeneracion);
}
