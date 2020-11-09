package com.example.larocainforma.ui.home.Clases;

public class Utiliza {
    private int utilizaId;
    private int grupoId;
    private Grupo grupo ;
    private int horarioId;
    private Horario horario;
    private int activo;

    public Utiliza() { }

    public Utiliza(int utilizaId, int grupoId, Grupo grupo, int horarioId, Horario horario, int activo) {
        this.utilizaId = utilizaId;
        this.grupoId = grupoId;
        this.grupo = grupo;
        this.horarioId = horarioId;
        this.horario = horario;
        this.activo = activo;
    }

    public int getUtilizaId() {
        return utilizaId;
    }

    public void setUtilizaId(int utilizaId) {
        this.utilizaId = utilizaId;
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

    public int getHorarioId() {
        return horarioId;
    }

    public void setHorarioId(int horarioId) {
        this.horarioId = horarioId;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
}
