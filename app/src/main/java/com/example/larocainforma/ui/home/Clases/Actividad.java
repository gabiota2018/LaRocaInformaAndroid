package com.example.larocainforma.ui.home.Clases;

import java.util.List;

public class Actividad {
    private String actividadId;
    private String nombre;
    private int menor;
    private int mayor;
    private String dirigido_a;
    private int autorizacion_de;
    private int cupo_sugerido ;
    private int activo;;
    private int borrado;
    private List<Grupo> listaGrupos;
    private List<Actividad> listaSucesoras;
    private List<Actividad> listaPredecesoras;

   // ActividadId-Nombre-Menor-Mayor-Dirigido_a
   // Autorizacion_de-Cupo_sugerido,Activo,Borrado,ListaGrupos,ListaSucesoras,ListaPredecesoras


    public Actividad() {}

    public Actividad(String actividadId, String nombre, int menor, int mayor, String dirigido_a, int autorizacion_de, int cupo_sugerido, int activo, int borrado, List<Grupo> listaGrupos, List<Actividad> listaSucesoras, List<Actividad> listaPredecesoras) {
        this.actividadId = actividadId;
        this.nombre = nombre;
        this.menor = menor;
        this.mayor = mayor;
        this.dirigido_a = dirigido_a;
        this.autorizacion_de = autorizacion_de;
        this.cupo_sugerido = cupo_sugerido;
        this.activo = activo;
        this.borrado = borrado;
        this.listaGrupos = listaGrupos;
        this.listaSucesoras = listaSucesoras;
        this.listaPredecesoras = listaPredecesoras;
    }

    public String getActividadId() {
        return actividadId;
    }

    public void setActividadId(String actividadId) {
        this.actividadId = actividadId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMenor() {
        return menor;
    }

    public void setMenor(int menor) {
        this.menor = menor;
    }

    public int getMayor() {
        return mayor;
    }

    public void setMayor(int mayor) {
        this.mayor = mayor;
    }

    public String getDirigido_a() {
        return dirigido_a;
    }

    public void setDirigido_a(String dirigido_a) {
        this.dirigido_a = dirigido_a;
    }

    public int getAutorizacion_de() {
        return autorizacion_de;
    }

    public void setAutorizacion_de(int autorizacion_de) {
        this.autorizacion_de = autorizacion_de;
    }

    public int getCupo_sugerido() {
        return cupo_sugerido;
    }

    public void setCupo_sugerido(int cupo_sugerido) {
        this.cupo_sugerido = cupo_sugerido;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
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

    public List<Actividad> getListaSucesoras() {
        return listaSucesoras;
    }

    public void setListaSucesoras(List<Actividad> listaSucesoras) {
        this.listaSucesoras = listaSucesoras;
    }

    public List<Actividad> getListaPredecesoras() {
        return listaPredecesoras;
    }

    public void setListaPredecesoras(List<Actividad> listaPredecesoras) {
        this.listaPredecesoras = listaPredecesoras;
    }
}
