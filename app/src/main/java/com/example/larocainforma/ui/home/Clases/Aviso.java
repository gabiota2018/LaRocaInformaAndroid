package com.example.larocainforma.ui.home.Clases;

import com.example.larocainforma.ui.home.Perfil.Usuario;

public class Aviso {
    private int avisoId;
    private String contenido;
    private String fecha_emitido;
    private String fecha_fin;
    private int usuarioId;
    private Usuario usuario;
    private int grupoId;
    private Grupo grupo;
    private int activo ;

    //avisoId-contenido-fecha_emitido-fecha_fin-usuarioId-usuario-grupoId-activo
    public Aviso() {
    }

    public Aviso(int avisoId, String contenido, String fecha_emitido, String fecha_fin, int usuarioId, Usuario usuario, int grupoId, Grupo grupo, int activo) {
        this.avisoId = avisoId;
        this.contenido = contenido;
        this.fecha_emitido = fecha_emitido;
        this.fecha_fin = fecha_fin;
        this.usuarioId = usuarioId;
        this.usuario = usuario;
        this.grupoId = grupoId;
        this.grupo = grupo;
        this.activo = activo;
    }

    public int getAvisoId() {
        return avisoId;
    }

    public void setAvisoId(int avisoId) {
        this.avisoId = avisoId;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFecha_emitido() {
        return fecha_emitido;
    }

    public void setFecha_emitido(String fecha_emitido) {
        this.fecha_emitido = fecha_emitido;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
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

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
}
