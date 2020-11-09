package com.example.larocainforma.ui.home.Perfil;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.larocainforma.R;
import com.example.larocainforma.ui.home.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    private MutableLiveData<Usuario> usuarioMutableLiveData;
    private Context context;
    private Usuario usuario;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public LiveData<Usuario> getUsuarioMutableLiveData(){
        if(usuarioMutableLiveData==null){
            usuarioMutableLiveData=new MutableLiveData<>();
        }
        return usuarioMutableLiveData;
    }

    public void obtenerDatos(){
        SharedPreferences sp=context.getSharedPreferences("token",0);
        String accessToken=sp.getString("token","");
        Call<Usuario> usuarioCall= ApiClient.getMyApiClient().obtenerDatos(accessToken);

       usuarioCall.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    usuarioMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.d("salida",t.getMessage());
            }
        });
    }


    public void actualizar(Usuario usuario){
        SharedPreferences sp=context.getSharedPreferences("token",0);
        String accessToken=sp.getString("token","");
        int id=usuario.getUsuarioId();
        Call<Usuario> usuarioActualizado= ApiClient.getMyApiClient().actualizar(accessToken,10 ,usuario);
//sacar los ancabezados...
        String[] partes = usuario.getNombre().split(":");
        usuario.setNombre((partes[1]));

        partes = usuario.getApellido().split(":");
        usuario.setApellido(partes[1]);

        partes = usuario.getTelefono().split(":");
        usuario.setTelefono(partes[1]);

        partes = usuario.getMail().split(":");
        usuario.setMail(partes[1]);

        partes = usuario.getFecha_nacimiento().split(":");
        usuario.setFecha_nacimiento(partes[1]);

        partes = usuario.getNombre_usuario().split(":");
        usuario.setNombre_usuario(partes[1]);

        partes = usuario.getClave().split(":");
        usuario.setClave(partes[1]);

       usuarioActualizado.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Log.d("salida","por actualizar");
                if(response.isSuccessful()){
                    Toast.makeText(getApplication(),"Datos actualizados",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.d("salida",t.getMessage());
            }
        });
    }
    public void guardarPreferencias(String tipoUsuario){
        SharedPreferences sp = context.getSharedPreferences("tipoUsuario", 0);
        SharedPreferences.Editor editor = sp.edit();
        String t = tipoUsuario;
        editor.putString("tipoUsuario", t);
        editor.commit();

    }
}
