package com.prog11.tarea.modelo;

public class EV extends VehiculoElectrificado {
    private double potenciaMaxCarga; // en KW

    public EV() { super(); }

    public EV(String marca, String modelo, int año, TipoEV tipo, double potencia) {
        super(marca, modelo, año, tipo);
        this.potenciaMaxCarga = potencia;
    }

    public EV(EV otro) {
        super(otro);
        this.potenciaMaxCarga = otro.potenciaMaxCarga;
    }

    public double getPotenciaMaxCarga() { return potenciaMaxCarga; }
    public void setPotenciaMaxCarga(double potencia) { this.potenciaMaxCarga = potencia; }

    @Override
    public String toString() {
        return "EV{" +
                super.toString()+
                ", potenciaMaxCarga=" + potenciaMaxCarga +
                '}';
    }

}