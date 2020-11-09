package com.example.larocainforma.ui.home.Clases;

import com.example.larocainforma.ui.home.Perfil.Usuario;

import java.util.List;

public class Funcion {
    private int funcionId;
    private String nombre;
    private int borrado;
    private List<Usuario> listaUsuarios;

    public Funcion() { }

    public Funcion(int funcionId, String nombre, int borrado) {
        this.funcionId = funcionId;
        this.nombre = nombre;
        this.borrado = borrado;
    }

    public Funcion(int funcionId, String nombre, int borrado, List<Usuario> listaUsuarios) {
        this.funcionId = funcionId;
        this.nombre = nombre;
        this.borrado = borrado;
        this.listaUsuarios = listaUsuarios;
    }

    public int getFuncionId() {
        return funcionId;
    }

    public void setFuncionId(int funcionId) {
        this.funcionId = funcionId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getBorrado() {
        return borrado;
    }

    public void setBorrado(int borrado) {
        this.borrado = borrado;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
}
