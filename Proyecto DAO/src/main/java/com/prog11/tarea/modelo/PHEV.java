package com.prog11.tarea.modelo;

public class PHEV extends VehiculoElectrificado {
    private int potenciaCombinada; // en KW

    public PHEV() { super(); }

    public PHEV(String marca, String modelo, int año, TipoEV tipo, int potencia) {
        super(marca, modelo, año, tipo);
        this.potenciaCombinada = potencia;
    }

    public PHEV(PHEV otro) {
        super(otro);
        this.potenciaCombinada = otro.potenciaCombinada;
    }

    public int getPotenciaCombinada() { return potenciaCombinada; }
    public void setPotenciaCombinada(int potencia) { this.potenciaCombinada = potencia; }

    @Override
    public String toString() {
        return "PHEV{" +
                super.toString()+
                ", potenciaCombinada=" + potenciaCombinada +
                '}';
    }
}