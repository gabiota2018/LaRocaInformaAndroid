package com.example.larocainforma.ui.home.Clases;

public class Correlativa {

    private int correlativaId ;
    private  int precedeId;
    private int sucedeId;
    private int activo;

    public Correlativa() { }

    public Correlativa(int correlativaId, int precedeId, int sucedeId, int activo) {
        this.correlativaId = correlativaId;
        this.precedeId = precedeId;
        this.sucedeId = sucedeId;
        this.activo = activo;
    }

    public int getCorrelativaId() {
        return correlativaId;
    }

    public void setCorrelativaId(int correlativaId) {
        this.correlativaId = correlativaId;
    }

    public int getPrecedeId() {
        return precedeId;
    }

    public void setPrecedeId(int precedeId) {
        this.precedeId = precedeId;
    }

    public int getSucedeId() {
        return sucedeId;
    }

    public void setSucedeId(int sucedeId) {
        this.sucedeId = sucedeId;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
}
