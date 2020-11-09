package com.example.larocainforma.ui.home.MisGruposActivos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.larocainforma.ui.home.Clases.Grupo;
import com.example.larocainforma.ui.home.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MisActividadesVM extends AndroidViewModel {
    private MutableLiveData<List<String>> listaDeGrupo;
    private Context context;
    private List<Grupo> todos;

    public MisActividadesVM(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }
    public LiveData<List<String>> getListaDeGrupo() {
        if(listaDeGrupo==null){
            listaDeGrupo=new MutableLiveData<>();
        }
        return listaDeGrupo;
    }

    public void cargarDatos(){

        SharedPreferences sp=context.getSharedPreferences("token",0);
        String accessToken=sp.getString("token","");
        Call<List<Grupo>> listaCall = ApiClient.getMyApiClient().listarGruposUsuario(accessToken);
        listaCall.enqueue(new Callback<List<Grupo>>() {
            @Override
            public void onResponse(Call<List<Grupo>> call, Response<List<Grupo>> response) {
                if(response.isSuccessful()){
                    todos=response.body();
                    ArrayList<String> listado=new ArrayList<>();
                    String cadena="";
                    for(Grupo s:todos){
                        cadena=s.getGrupoId()+"-"+s.getName()+"";
                        listado.add(cadena);

                    };

                    listaDeGrupo.setValue(listado);
                }
            }

            @Override
            public void onFailure(Call<List<Grupo>> call, Throwable t) {

            }
        });
    }
}
