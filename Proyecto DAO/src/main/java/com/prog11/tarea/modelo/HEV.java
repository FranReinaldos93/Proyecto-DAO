package com.prog11.tarea.modelo;

public class HEV extends VehiculoElectrificado {
    private int voltaje; // Solo 12 o 48

    public HEV() { super(); }

    public HEV(String marca, String modelo, int año, TipoEV tipo, int voltaje) {
        super(marca, modelo, año, tipo);
        setVoltaje(voltaje);
    }

    public HEV(HEV otro) {
        super(otro);
        this.voltaje = otro.voltaje;
    }

    public int getVoltaje() { return voltaje; }
    public void setVoltaje(int voltaje) {
        if (voltaje == 12 || voltaje == 48) {
            this.voltaje = voltaje;
        } else {
            this.voltaje = 12; // Valor por defecto si es incorrecto
        }
    }

    @Override
    public String toString() {
        return "HEV{" +
                super.toString()+
                ", voltaje=" + voltaje +
                '}';
    }
}