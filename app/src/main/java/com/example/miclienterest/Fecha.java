package com.example.miclienterest;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Fecha {

    @Id(autoincrement = true)
    private Long id;
    private String fecha;

    @Generated(hash = 98495658)
    public Fecha(Long id, String fecha) {
        this.id = id;
        this.fecha = fecha;
    }

    @Generated(hash = 1526477317)
    public Fecha() {
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
