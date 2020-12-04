package com.example.larocainforma.ui.home.AdministrarGrupo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.larocainforma.ui.home.Clases.Aviso;
import com.example.larocainforma.ui.home.Clases.Grupo;
import com.example.larocainforma.ui.home.Clases.Utiliza;
import com.example.larocainforma.ui.home.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParaCoordinadoresVM extends AndroidViewModel {
    private MutableLiveData<List<String>> listaDeGrupo;
    private Context context;
    private List<Grupo> todos;
    //private String accessToken;
    private int estado;

    public ParaCoordinadoresVM(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    public LiveData<List<String>> getListaDeGrupo() {
        if(listaDeGrupo==null){
            listaDeGrupo=new MutableLiveData<>();
        }
        return listaDeGrupo;
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    public void cargarDatos(){
        SharedPreferences sp=context.getSharedPreferences("token",0);
        String accessToken=sp.getString("token","");
        Call<List<Grupo>> listaCall = ApiClient.getMyApiClient().listarGruposCoordinador(accessToken);
        listaCall.enqueue(new Callback<List<Grupo>>() {
            @Override
            public void onResponse(Call<List<Grupo>> call, Response<List<Grupo>> response) {
                if(response.isSuccessful()){
                    todos=response.body();
                    ArrayList<String> listado=new ArrayList<>();
                    String cadena="Lista de los grupos a tu cargo";
                    listado.add(cadena);
                    for(Grupo s:todos){
                        cadena=s.getGrupoId()+"-"+s.getName()+"";
                        listado.add(cadena);
                    };
                    listaDeGrupo.setValue(listado);
                }
            }
            @Override
            public void onFailure(Call<List<Grupo>> call, Throwable t) {}
        });
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    public void enviarMensaje(int idGrupo, String mensaje,int dias){
        SharedPreferences sp=context.getSharedPreferences("token",0);
        String accessToken=sp.getString("token","");

        Aviso miAviso=new Aviso();
        miAviso.setContenido(mensaje);
        miAviso.setFecha_emitido("d");
        miAviso.setFecha_fin("d");
        miAviso.setGrupoId(idGrupo);
        miAviso.setActivo(dias);

        Call<Aviso> avisoCall=ApiClient.getMyApiClient().crearAvisos(accessToken,miAviso);
        avisoCall.enqueue(new Callback<Aviso>() {
            @Override
            public void onResponse(Call<Aviso> call, Response<Aviso> response) {
                if(response.isSuccessful()){
                    Toast.makeText(context, "El mensaje fue enviado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "No se ha podido enviar el mensaje, inténtelo más tarde", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Aviso> call, Throwable t) { }
        });
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    public void estadoGrupo(int idGrupo, int esta){
        Log.d("salida","entro a estadoGrupo");
        SharedPreferences sp=context.getSharedPreferences("token",0);
        String accessToken=sp.getString("token","");

        Log.d("salida","pidio el token");

        estado=esta;
        Grupo miGrupo=new Grupo();
        miGrupo.setGrupoId(idGrupo);
        miGrupo.setName("d");
        miGrupo.setFecha_inicio("d");
        miGrupo.setBorrado(estado);

        Log.d("salida","armo el Grupo");
        Call<Grupo> gCall=ApiClient.getMyApiClient().cambiaEstado(accessToken,miGrupo);
        gCall.enqueue(new Callback<Grupo>() {
           @Override
           public void onResponse(Call<Grupo> call, Response<Grupo> response) {

           }

           @Override
           public void onFailure(Call<Grupo> call, Throwable t) {

           }
       });
    }
    private void optimizarMensaje(){
        switch (estado){
            case 0:Toast.makeText(context, "El grupo está ACTIVADO", Toast.LENGTH_SHORT).show();
            case 1:Toast.makeText(context, "El grupo está SUSPENDIDO", Toast.LENGTH_SHORT).show();
            case 2:Toast.makeText(context, "El grupo ha terminado TERMINADO su actividad", Toast.LENGTH_SHORT).show();
        }
    }

}
