package com.example.miclienterest.utils;

import com.example.miclienterest.Signo;

import java.util.ArrayList;

public class ArregloSigno {

    private static ArrayList<Signo> sign;
    private static Signo elegido;

    public static void setArreglo(ArrayList<Signo> signos){
        sign = signos;
    }

    public static void setIndice(int i){
        if(sign.size()>i)
            elegido = sign.get(i);
    }

    public static Signo getElegido(){
        return elegido;
    }

    public static ArrayList<Signo> getArreglo(){
        return sign;
    }
}
