package com.example.larocainforma.ui.home.Perfil;

import com.example.larocainforma.ui.home.Clases.Aviso;
import com.example.larocainforma.ui.home.Clases.Funcion;
import com.example.larocainforma.ui.home.Clases.Grupo;

import java.util.List;

public class Usuario {
    private int usuarioId;
    private String nombre;
    private String apellido;
    private String telefono;
    private String mail;
    private String clave;
    private String nombre_usuario;
    private String tipo_usuario;
    private String fecha_nacimiento;
    private int borrado;
    private List<Aviso> listaAvisos;
    private List<Grupo> listaCoordinacion;
    private List<Grupo> listaParticipacion;
    private List<Funcion> listaFunciones;
//usuarioId-nombre-apellido-telefono-mail-clave-nombre_usuario-tipo_usuario-fecha_nacimiento-borrado
    public Usuario() { }

    public Usuario(String clave, String nombre_usuario) {
        this.clave = clave;
        this.nombre_usuario = nombre_usuario;
    }

    public Usuario(int usuarioId, String nombre, String apellido, String telefono, String mail, String clave, String nombre_usuario, String tipo_usuario, String fecha_nacimiento, int borrado) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.mail = mail;
        this.clave = clave;
        this.nombre_usuario = nombre_usuario;
        this.tipo_usuario = tipo_usuario;
        this.fecha_nacimiento = fecha_nacimiento;
        this.borrado = borrado;
    }

    public Usuario(int usuarioId, String nombre, String apellido, String telefono, String mail, String clave, String nombre_usuario, String tipo_usuario, String fecha_nacimiento, int borrado, List<Aviso> listaAvisos, List<Grupo> listaCoordinacion, List<Grupo> listaParticipacion, List<Funcion> listaFunciones) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.mail = mail;
        this.clave = clave;
        this.nombre_usuario = nombre_usuario;
        this.tipo_usuario = tipo_usuario;
        this.fecha_nacimiento = fecha_nacimiento;
        this.borrado = borrado;
        this.listaAvisos = listaAvisos;
        this.listaCoordinacion = listaCoordinacion;
        this.listaParticipacion = listaParticipacion;
        this.listaFunciones = listaFunciones;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getBorrado() {
        return borrado;
    }

    public void setBorrado(int borrado) {
        this.borrado = borrado;
    }

    public List<Aviso> getListaAvisos() {
        return listaAvisos;
    }

    public void setListaAvisos(List<Aviso> listaAvisos) {
        this.listaAvisos = listaAvisos;
    }

    public List<Grupo> getListaCoordinacion() {
        return listaCoordinacion;
    }

    public void setListaCoordinacion(List<Grupo> listaCoordinacion) {
        this.listaCoordinacion = listaCoordinacion;
    }

    public List<Grupo> getListaParticipacion() {
        return listaParticipacion;
    }

    public void setListaParticipacion(List<Grupo> listaParticipacion) {
        this.listaParticipacion = listaParticipacion;
    }

    public List<Funcion> getListaFunciones() {
        return listaFunciones;
    }

    public void setListaFunciones(List<Funcion> listaFunciones) {
        this.listaFunciones = listaFunciones;
    }
}
