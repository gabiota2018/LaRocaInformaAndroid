package com.example.larocainforma.ui.home.Clases;

public class AvisosSinVer {
    private int AvisoSinVerId;
    private int UsuarioId;
    private int AvisoId;
    private int GrupoId;

    public AvisosSinVer() { }

    public AvisosSinVer(int avisoSinVerId, int usuarioId, int avisoId, int grupoId) {
        AvisoSinVerId = avisoSinVerId;
        UsuarioId = usuarioId;
        AvisoId = avisoId;
        GrupoId = grupoId;
    }

    public int getAvisoSinVerId() {
        return AvisoSinVerId;
    }

    public void setAvisoSinVerId(int avisoSinVerId) {
        AvisoSinVerId = avisoSinVerId;
    }

    public int getUsuarioId() {
        return UsuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        UsuarioId = usuarioId;
    }

    public int getAvisoId() {
        return AvisoId;
    }

    public void setAvisoId(int avisoId) {
        AvisoId = avisoId;
    }

    public int getGrupoId() {
        return GrupoId;
    }

    public void setGrupoId(int grupoId) {
        GrupoId = grupoId;
    }
}
