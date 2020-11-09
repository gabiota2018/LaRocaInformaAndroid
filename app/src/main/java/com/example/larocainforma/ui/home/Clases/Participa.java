package com.example.larocainforma.ui.home.Clases;

import com.example.larocainforma.ui.home.Perfil.Usuario;

public class Participa {
    private int participaId;
    private int usuarioId;
    private Usuario usuario;
    private int grupoId;
    private Grupo grupo;
    private String fecha_inicio;
    private String fecha_fin;
    private int borrado;
    private int activo;

    public Participa() { }

    public Participa(int participaId, int usuarioId, Usuario usuario, int grupoId, Grupo grupo, String fecha_inicio, String fecha_fin, int borrado, int activo) {
        this.participaId = participaId;
        this.usuarioId = usuarioId;
        this.usuario = usuario;
        this.grupoId = grupoId;
        this.grupo = grupo;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.borrado = borrado;
        this.activo = activo;
    }

    public int getParticipaId() {
        return participaId;
    }

    public void setParticipaId(int participaId) {
        this.participaId = participaId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
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

    public int getBorrado() {
        return borrado;
    }

    public void setBorrado(int borrado) {
        this.borrado = borrado;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
}
