package com.example.larocainforma.ui.home.Logueo;

public class UsuarioLogin {
     private String usuarioLogin;
     private String clave;

    public UsuarioLogin() { }

    public UsuarioLogin(String usuarioLogin, String clave) {
        this.usuarioLogin = usuarioLogin;
        this.clave = clave;
    }

    public String getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(String usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
