package com.example.larocainforma.ui.home.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.larocainforma.ui.home.Perfil.Usuario;

public class SharedPreference {
    private static SharedPreferences sp;

    public static SharedPreferences conectar(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences("datos", 0);
        }
        return sp;
    }
//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    public static String traerToken(Context context)
    {
        SharedPreferences sp = conectar(context);
        String token = sp.getString("token", null);
        return token;
    }

    public static void guardarToken(Context context, String token)
    {
        SharedPreferences sp = conectar(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("token",token);
        editor.commit();
    }

 /*   public static void guardarUsuario(Context context, Usuario usuario) {
        SharedPreferences sp = conectar(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("UsuarioId",usuario.getUsuarioId());
        editor.putString("nombre",usuario.getNombre());
        editor.putString("apellido",usuario.getApellido());
        editor.putString("mail",usuario.getMail());
        editor.putString("telefono",usuario.getTelefono());
        editor.putString("tipo_usuario",usuario.getTipo_usuario());
        editor.putString("clave",usuario.getClave());
        editor.putString("nombre_usuario",usuario.getNombre_usuario());
        editor.putString("fecha_nacimiento",usuario.getFecha_nacimiento());
        editor.putInt("borrado",usuario.getBorrado());
        editor.commit();
    }
    public static Usuario traerUsuario( Context context) {
        SharedPreferences sp = conectar(context);
        Usuario usuario=null;
        usuario.setUsuarioId(sp.getInt("UsuarioId",-1));
        usuario.setNombre(sp.getString("nombre","-1"));
        usuario.setApellido(sp.getString("apellido","-1"));
        usuario.setMail(sp.getString("mail","-1"));
        usuario.setTelefono(sp.getString("telefono","-1"));
        usuario.setTipo_usuario(sp.getString("tipo_usuario","-1"));
        usuario.setClave(sp.getString("clave","-1"));
        usuario.setNombre_usuario(sp.getString("nombre_usuario","-1"));
        usuario.setFecha_nacimiento(sp.getString("fecha_nacimiento","-1"));
        usuario.setBorrado(sp.getInt("borrado",-1));
        return  usuario;
    }*/
}
