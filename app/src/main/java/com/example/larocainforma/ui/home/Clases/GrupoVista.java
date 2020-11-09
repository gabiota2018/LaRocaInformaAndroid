package com.example.larocainforma.ui.home.Clases;

public class GrupoVista {
    private int grupoVistaId;
    private int grupoId;
    private String name;
    private int usuarioId;
    private String contenido;
    private int horarioId;
    private String dia;
    private String hora_inicio ;
    private String hora_fin;
    private String nombre ;
    private String apellido ;
    private String telefono;
    private String mail;
    private String nombre_usuario;

    public GrupoVista() { }

    public GrupoVista(int grupoVistaId, int grupoId, String name, int usuarioId, String contenido, int horarioId, String dia, String hora_inicio, String hora_fin, String nombre, String apellido, String telefono, String mail, String nombre_usuario) {
        this.grupoVistaId = grupoVistaId;
        this.grupoId = grupoId;
        this.name = name;
        this.usuarioId = usuarioId;
        this.contenido = contenido;
        this.horarioId = horarioId;
        this.dia = dia;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.mail = mail;
        this.nombre_usuario = nombre_usuario;
    }

    public int getGrupoVistaId() {
        return grupoVistaId;
    }

    public void setGrupoVistaId(int grupoVistaId) {
        this.grupoVistaId = grupoVistaId;
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

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getHorarioId() {
        return horarioId;
    }

    public void setHorarioId(int horarioId) {
        this.horarioId = horarioId;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }
}
