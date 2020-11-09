package com.example.larocainforma.ui.home.Clases;

import java.util.List;

public class Horario {
    private int horarioId;
    private int dia;
    private String hora_inicio;
    private String hora_fin;
    private int borrado;
    private List<Grupo> listaGrupos;

    public Horario() { }

    public Horario(int horarioId, int dia, String hora_inicio, String hora_fin, int borrado) {
        this.horarioId = horarioId;
        this.dia = dia;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.borrado = borrado;
    }

    public Horario(int horarioId, int dia, String hora_inicio, String hora_fin, int borrado, List<Grupo> listaGrupos) {
        this.horarioId = horarioId;
        this.dia = dia;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.borrado = borrado;
        this.listaGrupos = listaGrupos;
    }

    public int getHorarioId() {
        return horarioId;
    }

    public void setHorarioId(int horarioId) {
        this.horarioId = horarioId;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }

    public int getBorrado() {
        return borrado;
    }

    public void setBorrado(int borrado) {
        this.borrado = borrado;
    }

    public List<Grupo> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(List<Grupo> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }
}
