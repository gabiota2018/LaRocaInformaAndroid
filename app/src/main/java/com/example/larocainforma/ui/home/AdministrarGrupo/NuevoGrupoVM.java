package com.example.larocainforma.ui.home.AdministrarGrupo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.larocainforma.ui.home.Clases.Actividad;
import com.example.larocainforma.ui.home.Clases.Aviso;
import com.example.larocainforma.ui.home.Clases.Grupo;
import com.example.larocainforma.ui.home.Clases.Horario;
import com.example.larocainforma.ui.home.Clases.Utiliza;
import com.example.larocainforma.ui.home.request.ApiClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Header;

public class NuevoGrupoVM extends AndroidViewModel {
    private MutableLiveData<List<String>> listaDeActividades;
    private MutableLiveData<String> miActividadLD;
    private Context context;
    private List<Actividad> todos;
    private MutableLiveData<Grupo> miGrupoLD;


    public NuevoGrupoVM(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    public LiveData<List<String>> getListaDeActividades() {
        if(listaDeActividades==null){
            listaDeActividades=new MutableLiveData<>();
        }
        return listaDeActividades;
    }
    public LiveData<String> getMiActividadLD() {
        if(miActividadLD==null){
            miActividadLD=new MutableLiveData<>();
        }
        return miActividadLD;
    }
    public  LiveData<Grupo> getMiGrupoLD(){
        if(miGrupoLD==null){
            miGrupoLD=new MutableLiveData<>();
        }
        return  miGrupoLD;
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    public void cargarDatos() {
        SharedPreferences sp=context.getSharedPreferences("token",0);
        String accessToken=sp.getString("token","");
        Call<List<Actividad>> listaCall = ApiClient.getMyApiClient().listarActividades(accessToken);
        listaCall.enqueue(new Callback<List<Actividad>>() {
            @Override
            public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                if(response.isSuccessful()){
                    todos=response.body();
                    ArrayList<String> listado=new ArrayList<>();
                    String cadena="Lista de Actividades Disponibles";
                    listado.add(cadena);
                    for(Actividad s:todos){
                        cadena=s.getActividadId()+"- "+s.getNombre() ;
                        listado.add(cadena);
                    };

                    listaDeActividades.setValue(listado);
                }
            }

            @Override
            public void onFailure(Call<List<Actividad>> call, Throwable t) {

            }
        });
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    public void crearGrupo(Grupo miGrupo)  {
    SharedPreferences sp=context.getSharedPreferences("token",0);
    String accessToken=sp.getString("token","");
    Call<Grupo> grupoCall=ApiClient.getMyApiClient().nuevoGrupo(accessToken,miGrupo);
    grupoCall.enqueue(new Callback<Grupo>() {
        @Override
        public void onResponse(Call<Grupo> call, Response<Grupo> response) {
            if(response.isSuccessful()) {
                Toast.makeText(context, "Nuevo grupo creado ", Toast.LENGTH_SHORT).show();
               // Grupo grupoNuevo=response.body();
                //miGrupoLD.postValue(grupoNuevo);
                ultimoGrupo();
            }
            else
                Toast.makeText(context, "No se guardo ", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onFailure(Call<Grupo> call, Throwable t) {}
    });
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    public void horarioNuevo(final Horario miHorario) {
        SharedPreferences sp=context.getSharedPreferences("token",0);
        String accessToken=sp.getString("token","");

        Call<Horario> g=ApiClient.getMyApiClient().crearHorario(accessToken,miHorario);
        g.enqueue(new Callback<Horario>() {
            @Override
            public void onResponse(Call<Horario> call, Response<Horario> response) {
                if(response.isSuccessful()){
                    Toast.makeText(context, "OK", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Horario> call, Throwable t) { }
        });
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    public void guardarUtiliza(int grupoId) {
        SharedPreferences sp=context.getSharedPreferences("token",0);
        String accessToken=sp.getString("token","");
      Toast.makeText(context, "entro a utiliza idGrupo"+grupoId, Toast.LENGTH_SHORT).show();

        Call<Utiliza> utilizaCall=ApiClient.getMyApiClient().actualizarUtiliza(accessToken,grupoId);
        utilizaCall.enqueue(new Callback<Utiliza>() {
            @Override
            public void onResponse(Call<Utiliza> call, Response<Utiliza> response) {
                if(response.isSuccessful())
                    Toast.makeText(context, "El nuevo horario fue guardado", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<Utiliza> call, Throwable t) {  }
        });
    }

    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    public void ultimoGrupo() {
        SharedPreferences sp=context.getSharedPreferences("token",0);
        String accessToken=sp.getString("token","");
       Call<Grupo> ultimo=ApiClient.getMyApiClient().ultimoGrupo(accessToken);
       ultimo.enqueue(new Callback<Grupo>() {
           @Override
           public void onResponse(Call<Grupo> call, Response<Grupo> response) {
               if(response.isSuccessful())
                   miGrupoLD.postValue(response.body());
           }

           @Override
           public void onFailure(Call<Grupo> call, Throwable t) {

           }
       });
     }
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
}
