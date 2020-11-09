package com.example.larocainforma.ui.home.PosiblesActividades;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.larocainforma.ui.home.Clases.Actividad;
import com.example.larocainforma.ui.home.Clases.Cumple;
import com.example.larocainforma.ui.home.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PuedoHacerVM extends AndroidViewModel {

    private MutableLiveData<List<String>> listaDeActividades;
    private Context context;
    private List<Actividad> todas,listadoFiltrado;
    private List<Cumple> listadoCumple;

    public PuedoHacerVM(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public LiveData<List<String>> getListaDeActividades() {
        if(listaDeActividades==null){
            listaDeActividades=new MutableLiveData<>();
        }
        return listaDeActividades;
    }

    public void cargarDatos(){
        SharedPreferences sp=context.getSharedPreferences("token",0);
        String accessToken=sp.getString("token","");
        Call<List<Actividad>> listaCall = ApiClient.getMyApiClient().listarHabilitadas(accessToken);
        listaCall.enqueue(new Callback<List<Actividad>>() {
        @Override
        public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
           if(response.isSuccessful()){
                todas=response.body();
                ArrayList<String> listado=new ArrayList<>();
                String cadena="";
                for(Actividad s:todas){
                    if(s.getAutorizacion_de()!=2011)
                        cadena=s.getActividadId()+"- Necesita autorización para: "+s.getNombre() ;
                    else
                        cadena=s.getActividadId()+"- "+s.getNombre() ;
                    listado.add(cadena);
                };

                listaDeActividades.setValue(listado);
            }
        }
        @Override
            public void onFailure(Call<List<Actividad>> call, Throwable t) {Log.d("salida",t.getMessage());}});
    }

}
