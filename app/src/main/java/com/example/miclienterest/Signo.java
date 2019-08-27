package com.example.miclienterest;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Signo {

    @Id(autoincrement = true)
    private Long id;

    private String nombre;
    private String fechaSigno;
    private String amor;
    private String dinero;
    private String salud;
    private String color;
    private String numero;

    @Generated(hash = 1092843618)
    public Signo(Long id, String nombre, String fechaSigno, String amor,
            String dinero, String salud, String color, String numero) {
        this.id = id;
        this.nombre = nombre;
        this.fechaSigno = fechaSigno;
        this.amor = amor;
        this.dinero = dinero;
        this.salud = salud;
        this.color = color;
        this.numero = numero;
    }

    @Generated(hash = 1411459516)
    public Signo() {
    }

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
        return nombre;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
