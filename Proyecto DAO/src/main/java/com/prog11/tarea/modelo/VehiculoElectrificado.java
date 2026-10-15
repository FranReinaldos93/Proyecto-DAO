package com.prog11.tarea.modelo;

public abstract class VehiculoElectrificado {
    protected String marca;
    protected String modelo;
    protected int año;
    protected TipoEV tipo;
    protected static int idVehiculo = 0; // Atributo estático según enunciado
    private int idPropio; // Para identificar la instancia actual

    // Constructor por defecto
    public VehiculoElectrificado() {
        idVehiculo++;
        this.idPropio = idVehiculo;
    }

    public static int getIdVehiculo() {
        return idVehiculo;
    }

   

    // Constructor con parámetros
    public VehiculoElectrificado(String marca, String modelo, int año, TipoEV tipo) {
        this();
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.tipo = tipo;
    }

    // Constructor de copia
    public VehiculoElectrificado(VehiculoElectrificado otro) {
        this.marca = otro.marca;
        this.modelo = otro.modelo;
        this.año = otro.año;
        this.tipo = otro.tipo;
        this.idPropio = otro.idPropio;
    }

    // Getters y Setters
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public int getAño() { return año; }
    public void setAño(int año) { this.año = año; }
    public TipoEV getTipo() { return tipo; }
    public void setTipo(TipoEV tipo) { this.tipo = tipo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehiculoElectrificado that = (VehiculoElectrificado) o;
        return idPropio == that.idPropio;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(idPropio);
    }

    @Override
    public String toString() {
        return "VehiculoElectrificado{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", año=" + año +
                ", tipo=" + tipo +
                ", idPropio=" + idPropio +
                '}';
    }
}
