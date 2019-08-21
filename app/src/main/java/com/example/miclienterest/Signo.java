package com.example.miclienterest;

public class Signo {

    private String nombre;
    private String fechaSigno;
    private String amor;
    private String dinero;
    private String salud;
    private String color;
    private String numero;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaSigno() {
        return fechaSigno;
    }

    public void setFechaSigno(String fechaSigno) {
        this.fechaSigno = fechaSigno;
    }

    public String getAmor() {
        return amor;
    }

    public void setAmor(String amor) {
        this.amor = amor;
    }

    public String getDinero() {
        return dinero;
    }

    public void setDinero(String dinero) {
        this.dinero = dinero;
    }

    public String getSalud() {
        return salud;
    }

    public void setSalud(String salud) {
        this.salud = salud;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Signo{" +
                "nombre='" + nombre + '\'' +
                ", fechaSigno='" + fechaSigno + '\'' +
                ", amor='" + amor + '\'' +
                ", dinero='" + dinero + '\'' +
                ", salud='" + salud + '\'' +
                ", color='" + color + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }
}
