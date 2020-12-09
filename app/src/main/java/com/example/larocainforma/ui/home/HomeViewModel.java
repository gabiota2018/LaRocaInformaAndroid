package com.example.larocainforma.ui.home;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.larocainforma.ui.home.Clases.Grupo;
import com.example.larocainforma.ui.home.Clases.Utiliza;
import com.example.larocainforma.ui.home.request.ApiClient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends AndroidViewModel {

    //private MutableLiveData<String> mText;
    private MutableLiveData<List<String>> listaDeUtiliza;
    private Context context;
    private List<Utiliza> todos;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }
    public LiveData<List<String>> getListaDeUtiliza() {
        if(listaDeUtiliza==null){
            listaDeUtiliza=new MutableLiveData<>();
        }
        return listaDeUtiliza;
    }

    public void cargarDatos() {
        SharedPreferences sp=context.getSharedPreferences("token",0);
        String accessToken=sp.getString("token","");

        Call<List<Utiliza>> listaCall = ApiClient.getMyApiClient().porDia(accessToken,2);
        listaCall.enqueue(new Callback<List<Utiliza>>() {
            @Override
            public void onResponse(Call<List<Utiliza>> call, Response<List<Utiliza>> response) {
                if(response.isSuccessful()){
                    todos=response.body();
                    ArrayList<String> listado=new ArrayList<>();
                    String cadena=" ";

                    for(Utiliza s:todos){
                        cadena="";
                        cadena=s.getGrupo().getName();
                        listado.add(cadena);
                        cadena=" de "+s.getHorario().getHora_inicio() +" a "+s.getHorario().getHora_fin();
                        listado.add(cadena);
                        cadena="************************************";
                        listado.add(cadena);
                    };
                    listaDeUtiliza.setValue(listado);
                }
            }

            @Override
            public void onFailure(Call<List<Utiliza>> call, Throwable t) {

            }
        });
    }

    //-------------------------------------------------------------
}
