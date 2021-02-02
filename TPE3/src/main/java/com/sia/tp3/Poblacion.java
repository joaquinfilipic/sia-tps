package com.sia.tp3;

import com.sia.tp3.items.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Poblacion {

    private String path;
    private ArrayList<Personaje> personajes;
    private Integer cantidadPersonajes;
    private int numeroDeGeneracion;
    private double mejorDesempenio;
    private double peorDesempenio;
    private double desempenioPromedio;
    private ArrayList<Double> mejoresDesempenios;
    private ArrayList<HashSet<String>> listHashSetPersonajes;
    private List<Arma> armas;
    private List<Bota> botas;
    private List<Casco> cascos;
    private List<Guante> guantes;
    private List<Pechera> pecheras;

    public Poblacion(final String personaje, final Multiplicador multiplicador, final String path, final int cantidadDePoblacion, final long semillaPoblacionInicial) {

        System.out.println("Inicializando población...");
        this.numeroDeGeneracion = 1;
        this.path = path;

        armas = leerArmas();
        botas = leerBotas();
        cascos = leerCascos();
        guantes = leerGuantes();
        pecheras = leerPecheras();
        cantidadPersonajes = cantidadDePoblacion;
        personajes = new ArrayList<>();
        listHashSetPersonajes = new ArrayList<>();

        Random r = new Random(semillaPoblacionInicial);

        Personaje aux;
        for (int i = 0; i < cantidadPersonajes; i++) {
            aux = new Personaje(personaje, multiplicador, armas.get(r.nextInt(armas.size())),
                    botas.get(r.nextInt(botas.size())), cascos.get(r.nextInt(cascos.size())),
                    guantes.get(r.nextInt(guantes.size())), pecheras.get(r.nextInt(pecheras.size())), 1.3 + (2.0 - 1.3) * r.nextDouble());

            personajes.add(aux);
        }

        recalcularDesempenios();
        mejoresDesempenios = new ArrayList<>();
        mejoresDesempenios.add(mejorDesempenio);

        System.out.println("Se terminó de inicializar la población.\n");
    }

    public ArrayList<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(ArrayList<Personaje> personajes) {
        this.personajes = personajes;
    }

    public ArrayList<Arma> leerArmas() {
        ArrayList<Arma> armas = new ArrayList<>();
        this.cantidadPersonajes = 0;
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(path + "armas.tsv"));
            String line;
            String[] datos;
            buffer.readLine();

            while ((line = buffer.readLine()) != null) {

                datos = line.split("\t");
                armas.add(new Arma(Integer.parseInt(datos[0]), Double.parseDouble(datos[1]),
                        Double.parseDouble(datos[2]), Double.parseDouble(datos[3]), Double.parseDouble(datos[4]),
                        Double.parseDouble(datos[5])));
                this.cantidadPersonajes++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return armas;
    }

    public ArrayList<Bota> leerBotas() {
        ArrayList<Bota> botas = new ArrayList<>();
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(path + "botas.tsv"));
            String line;
            String[] datos;
            buffer.readLine();

            while ((line = buffer.readLine()) != null) {

                datos = line.split("\t");
                botas.add(new Bota(Integer.parseInt(datos[0]), Double.parseDouble(datos[1]),
                        Double.parseDouble(datos[2]), Double.parseDouble(datos[3]), Double.parseDouble(datos[4]),
                        Double.parseDouble(datos[5])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return botas;
    }

    public ArrayList<Casco> leerCascos() {
        ArrayList<Casco> cascos = new ArrayList<>();
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(path + "cascos.tsv"));
            String line;
            String[] datos;
            buffer.readLine();

            while ((line = buffer.readLine()) != null) {

                datos = line.split("\t");
                cascos.add(new Casco(Integer.parseInt(datos[0]), Double.parseDouble(datos[1]),
                        Double.parseDouble(datos[2]), Double.parseDouble(datos[3]), Double.parseDouble(datos[4]),
                        Double.parseDouble(datos[5])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cascos;
    }

    public ArrayList<Guante> leerGuantes() {
        ArrayList<Guante> guantes = new ArrayList<>();
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(path + "guantes.tsv"));
            String line;
            String[] datos;
            buffer.readLine();

            while ((line = buffer.readLine()) != null) {

                datos = line.split("\t");
                guantes.add(new Guante(Integer.parseInt(datos[0]), Double.parseDouble(datos[1]),
                        Double.parseDouble(datos[2]), Double.parseDouble(datos[3]), Double.parseDouble(datos[4]),
                        Double.parseDouble(datos[5])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return guantes;
    }

    public ArrayList<Pechera> leerPecheras() {
        ArrayList<Pechera> pecheras = new ArrayList<>();
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(path + "pecheras.tsv"));
            String line;
            String[] datos;
            buffer.readLine();

            while ((line = buffer.readLine()) != null) {

                datos = line.split("\t");
                pecheras.add(new Pechera(Integer.parseInt(datos[0]), Double.parseDouble(datos[1]),
                        Double.parseDouble(datos[2]), Double.parseDouble(datos[3]), Double.parseDouble(datos[4]),
                        Double.parseDouble(datos[5])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pecheras;
    }

    public int getNumeroDeGeneracion() {
        return numeroDeGeneracion;
    }

    public void resetNumeroDeGeneracion() {
        numeroDeGeneracion = 1;
    }

    public void aumentarGeneracion() {
        numeroDeGeneracion++;
    }

    public double getMejorDesempenio() {
        return mejorDesempenio;
    }

    public void recalcularDesempenios() {
        double mejorDesempenio = 0;
        double peorDesempenio = personajes.get(0).getDesempenio();
        double sumaDesempenios = 0;
        for (Personaje personaje : personajes) {
            if (personaje.getDesempenio() > mejorDesempenio) {
                mejorDesempenio = personaje.getDesempenio();
            }

            if (personaje.getDesempenio() < peorDesempenio) {
                peorDesempenio = personaje.getDesempenio();
            }

            sumaDesempenios += personaje.getDesempenio();
        }

        this.mejorDesempenio = mejorDesempenio;
        this.peorDesempenio = peorDesempenio;
        this.desempenioPromedio = sumaDesempenios / personajes.size();
    }

    public ArrayList<Double> getMejoresDesempenios() {
        return mejoresDesempenios;
    }

    public void agregarMejorDesempenio() {
        recalcularDesempenios();
        mejoresDesempenios.add(mejorDesempenio);
    }

    public ArrayList<HashSet<String>> getListHashSetPersonajes() {
        return listHashSetPersonajes;
    }

    public void aniadirTodosLosPersonajesAListHashSet() {
        listHashSetPersonajes.add(new HashSet<>());

        for (Personaje personaje : personajes) {
            listHashSetPersonajes.get(listHashSetPersonajes.size() - 1).add(personaje.toHash());
        }
    }

    public double getPeorDesempenio() {
        return peorDesempenio;
    }

    public double getDesempenioPromedio() {
        return desempenioPromedio;
    }

    public List<Arma> getArmas() {
        return armas;
    }

    public List<Bota> getBotas() {
        return botas;
    }

    public List<Casco> getCascos() {
        return cascos;
    }

    public List<Guante> getGuantes() {
        return guantes;
    }

    public List<Pechera> getPecheras() {
        return pecheras;
    }

    public void eliminarHashesNoUtilizados(final int generacionesAVerificar) {
        if (generacionesAVerificar < listHashSetPersonajes.size()) {
            listHashSetPersonajes.remove(0);
        }
    }
}
