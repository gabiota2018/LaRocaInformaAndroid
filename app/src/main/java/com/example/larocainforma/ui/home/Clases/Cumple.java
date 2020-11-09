package com.example.larocainforma.ui.home.Clases;

import com.example.larocainforma.ui.home.Perfil.Usuario;

public class Cumple {
      private int cumpleId;
      private int usuarioId;
      private Usuario usuario;
      private int funcionId;
      private Funcion funcion;
      private String fecha_inicio;
      private String fecha_fin;
      private int borrado;
      private int activo;

      public Cumple() { }

      public Cumple(int cumpleId, int usuarioId, Usuario usuario, int funcionId, Funcion funcion, String fecha_inicio, String fecha_fin, int borrado, int activo) {
            this.cumpleId = cumpleId;
            this.usuarioId = usuarioId;
            this.usuario = usuario;
            this.funcionId = funcionId;
            this.funcion = funcion;
            this.fecha_inicio = fecha_inicio;
            this.fecha_fin = fecha_fin;
            this.borrado = borrado;
            this.activo = activo;
      }

      public int getCumpleId() {
            return cumpleId;
      }

      public void setCumpleId(int cumpleId) {
            this.cumpleId = cumpleId;
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

      public int getFuncionId() {
            return funcionId;
      }

      public void setFuncionId(int funcionId) {
            this.funcionId = funcionId;
      }

      public Funcion getFuncion() {
            return funcion;
      }

      public void setFuncion(Funcion funcion) {
            this.funcion = funcion;
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
