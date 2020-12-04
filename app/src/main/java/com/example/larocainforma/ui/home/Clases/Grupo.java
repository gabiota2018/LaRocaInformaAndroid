package com.example.larocainforma.ui.home.Clases;

import com.example.larocainforma.ui.home.Clases.Actividad;
import com.example.larocainforma.ui.home.Perfil.Usuario;

import java.util.List;

public class Grupo {

    private int grupoId;
    private String name;
    private int actividadId;
    private Actividad actividad;
    private int coordinadorId;
    private Usuario coordinador;
    private String fecha_inicio;
    private String fecha_fin;
    private int orden;
    private int borrado;
    private List<Usuario> listaParticipantes;
    private List<Aviso> listaAvisos;
    private List<Horario> listaHorarios;

//grupoId-nombre-actividadId-actividad-usuarioId-usuario-fecha_inicio-fecha_fin-borrado

    public Grupo() { }

    public Grupo(int grupoId, String name, int actividadId, Actividad actividad, int coordinadorId, Usuario coordinador, String fecha_inicio, String fecha_fin, int orden, int borrado, List<Usuario> listaParticipantes, List<Aviso> listaAvisos, List<Horario> listaHorarios) {
        this.grupoId = grupoId;
        this.name = name;
        this.actividadId = actividadId;
        this.actividad = actividad;
        this.coordinadorId = coordinadorId;
        this.coordinador = coordinador;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.orden = orden;
        this.borrado = borrado;
        this.listaParticipantes = listaParticipantes;
        this.listaAvisos = listaAvisos;
        this.listaHorarios = listaHorarios;
    }

    public int getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActividadId() {
        return actividadId;
    }

    public void setActividadId(int actividadId) {
        this.actividadId = actividadId;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public int getCoordinadorId() {
        return coordinadorId;
    }

    public void setCoordinadorId(int coordinadorId) {
        this.coordinadorId = coordinadorId;
    }

    public Usuario getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(Usuario coordinador) {
        this.coordinador = coordinador;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getBorrado() {
        return borrado;
    }

    public void setBorrado(int borrado) {
        this.borrado = borrado;
    }

    public List<Usuario> getListaParticipantes() {
        return listaParticipantes;
    }

    public void setListaParticipantes(List<Usuario> listaParticipantes) {
        this.listaParticipantes = listaParticipantes;
    }

    public List<Aviso> getListaAvisos() {
        return listaAvisos;
    }

    public void setListaAvisos(List<Aviso> listaAvisos) {
        this.listaAvisos = listaAvisos;
    }

    public List<Horario> getListaHorarios() {
        return listaHorarios;
    }

    public void setListaHorarios(List<Horario> listaHorarios) {
        this.listaHorarios = listaHorarios;
    }
}