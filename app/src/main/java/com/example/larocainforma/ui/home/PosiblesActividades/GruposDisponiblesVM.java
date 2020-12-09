package com.example.larocainforma.ui.home.PosiblesActividades;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.larocainforma.ui.home.Clases.AvisosSinVer;
import com.example.larocainforma.ui.home.Clases.Grupo;
import com.example.larocainforma.ui.home.Clases.Horario;
import com.example.larocainforma.ui.home.Clases.Participa;
import com.example.larocainforma.ui.home.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GruposDisponiblesVM  extends AndroidViewModel {

    private MutableLiveData<List<String>> listaDeGrupo;
    private Context context;
    private List<Grupo> todos;
    private String accessToken;

    public GruposDisponiblesVM(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }
    public LiveData<List<String>> getListaDeGrupo() {
        if(listaDeGrupo==null){
            listaDeGrupo=new MutableLiveData<>();
        }
        return listaDeGrupo;
    }

    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    public void cargarDatos(String palabra) {
        SharedPreferences sp = context.getSharedPreferences("token", 0);
        String accessToken = sp.getString("token", "");

        String[] partes = palabra.split("-");
        final int idActividad = Integer.parseInt(partes[0]);
        Log.d("salida","entro a grupoCall");

        Call<List<Grupo>> grupoCall= ApiClient.getMyApiClient().gruposPorActividad(accessToken,idActividad);
        grupoCall.enqueue(new Callback<List<Grupo>>() {
            @Override
            public void onResponse(Call<List<Grupo>> call, Response<List<Grupo>> response) {
                if(response.isSuccessful()){
                    todos=response.body();
                    ArrayList<String> listado=new ArrayList<>();
                    List<Horario> listaHorarios=new ArrayList<>();
                    String proximo,cadena="";
                    for(Grupo s:todos) {
                        String[] nombre=s.getName().split("-");
                        cadena = nombre[0] + " "+nombre[1];
                        listado.add(cadena);
                        cadena = "A cargo de: " + s.getCoordinador().getNombre() + " " + s.getCoordinador().getApellido();
                        listado.add(cadena);
                        for (Horario h : s.getListaHorarios()) {
                            cadena="-"+nombreDia(h.getDia());
                            cadena+= " de "+h.getHora_inicio()+" a "+h.getHora_fin();
                            listado.add(cadena);
                        }
                        cadena="xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
                        listado.add(cadena);
                    }
                    listaDeGrupo.setValue(listado);
                };
             }
            @Override
            public void onFailure(Call<List<Grupo>> call, Throwable t) { }
        });
    }


    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    private String nombreDia(int d){
        String proximo="";
        switch (d){
            case 0: proximo="DOMINGO";break;
            case 1: proximo="LUNES";break;
            case 2: proximo="MARTES";break;
            case 3: proximo="MIERCOLES";break;
            case 4: proximo="JUEVES";break;
            case 5: proximo="VIERNES";break;
            case 6: proximo="SABADO";break;
        }
        return  proximo;
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    public void EntrarAlGrupo(int grupoId) {
        Call<Participa> altaCall = ApiClient.getMyApiClient().altaParticipa(accessToken,grupoId);
        altaCall.enqueue(new Callback<Participa>() {
            @Override
            public void onResponse(Call<Participa> call, Response<Participa> response) {
                if(response.isSuccessful())
                    Toast.makeText(context, "¡Ya eres PARTE del GRUPO!!!", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<Participa> call, Throwable t) {

            }
        });

       }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

}
