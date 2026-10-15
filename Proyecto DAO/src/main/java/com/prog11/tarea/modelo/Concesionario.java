package com.prog11.tarea.modelo;

import java.util.Arrays;
import java.util.Objects;

public class Concesionario {
    private int id;
    private String nombre;
    private String localizacion;
    private int capacidadMaxima;
    private VehiculoElectrificado[] coches;


    public Concesionario() {
        this.coches = new VehiculoElectrificado[50];

    }

    public Concesionario(String nombre, String localizacion, int capacidad) {
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.coches = new VehiculoElectrificado[capacidad];
    }

    public Concesionario(Concesionario otro) {
        this.nombre = otro.nombre;
        this.localizacion = otro.localizacion;
        this.coches = Arrays.copyOf(otro.coches, otro.coches.length);

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }


    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Concesionario that = (Concesionario) o;
        return id == that.id && capacidadMaxima == that.capacidadMaxima && Objects.equals(nombre, that.nombre) && Objects.equals(localizacion, that.localizacion) && Objects.deepEquals(coches, that.coches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, localizacion, capacidadMaxima, Arrays.hashCode(coches));
    }

    @Override
    public String toString() {
        return "Concesionario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", localización='" + localizacion + '\'' +
                ", capacidad máxima=" + capacidadMaxima +
                '}';
    }
}