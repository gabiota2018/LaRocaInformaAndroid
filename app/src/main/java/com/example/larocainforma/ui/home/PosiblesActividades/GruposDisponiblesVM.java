package com.example.larocainforma.ui.home.PosiblesActividades;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.larocainforma.ui.home.Clases.AvisosSinVer;
import com.example.larocainforma.ui.home.Clases.Grupo;
import com.example.larocainforma.ui.home.Clases.Horario;
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
        final int idGrupo = Integer.parseInt(partes[0]);
        Log.d("salida","entro a grupoCall");

        Call<List<Grupo>> grupoCall= ApiClient.getMyApiClient().gruposPorActividad(accessToken,idGrupo);
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
                        cadena = s.getGrupoId() + "-" + nombre[0] + "";
                        listado.add(cadena);
                        cadena = "A cargo de: " + s.getCoordinador().getNombre() + " " + s.getCoordinador().getApellido();
                        listado.add(cadena);
                        cadena="";
                        listaHorarios=s.getListaHorarios();
                        for (Horario h : listaHorarios) {
                            cadena+= "*"+nombreDia(h.getDia())+" de "+h.getHora_inicio()+" a "+h.getHora_fin();
                        }
                        listado.add(cadena);
                    }
                    listaDeGrupo.setValue(listado);
                };
             }


            @Override
            public void onFailure(Call<List<Grupo>> call, Throwable t) {

            }
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
    private String cadenaHorarios(){
        String rta="";
        int contador, lineas=0;
       // contador = s.getListaHorarios().size();  Log.d("salida","size Horarios"+contador);
        String cadena = "-";
        String proximo = "";

//        if (contador != 0) {
//            if (lineas == 2) {
//                listado.add(cadena);
//                cadena = "";
//                lineas = 0;
//                Log.d("salida","lineas == 2"+cadena);
//            }
//            if (lineas < 2) {
//                cadena = cadena + proximo + " de " + h.getHora_inicio() + "-" + h.getHora_fin() + "___";
//                lineas++;
//                contador--;
//                Log.d("salida","lineas < 2"+cadena);
//            }
//        } else {
//            if (!cadena.equals("")) {listado.add(cadena);
//                Log.d("salida","si tuviera un 3 renglon"+cadena);}

        return rta;
    }

}
