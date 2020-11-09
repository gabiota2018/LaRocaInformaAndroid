package com.example.larocainforma.ui.home.Avisos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.larocainforma.ui.home.Clases.Aviso;
import com.example.larocainforma.ui.home.Clases.AvisosSinVer;
import com.example.larocainforma.ui.home.Clases.Grupo;
import com.example.larocainforma.ui.home.Clases.Participa;
import com.example.larocainforma.ui.home.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AvisosActividadVM extends AndroidViewModel {
    private MutableLiveData<List<String>> listaDeAviso;
    private Context context;
    private List<Aviso> todos;

    public AvisosActividadVM(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }
    public LiveData<List<String>> getListaDeAviso() {
        if(listaDeAviso==null){
            listaDeAviso=new MutableLiveData<>();
        }
        return listaDeAviso;
    }
    public void cargarDatos(String palabra){
        SharedPreferences sp=context.getSharedPreferences("token",0);
        String accessToken=sp.getString("token","");

        String[] partes = palabra.split("-");
        final int idGrupo = Integer.parseInt(partes[0]);

        Call<List<Aviso>> listaCall = ApiClient.getMyApiClient().activosAvisosGrupo(accessToken,idGrupo);
        listaCall.enqueue(new Callback<List<Aviso>>() {
            @Override
            public void onResponse(Call<List<Aviso>> call, Response<List<Aviso>> response) {
                int i=1;
                if(response.isSuccessful()){
                    todos=response.body();
                    ArrayList<String> listado=new ArrayList<>();
                    String cadena="";
                    for(Aviso s:todos){
                        cadena=i+") "+s.getContenido();
                        listado.add(cadena);
                        i++;
                    };
                    listaDeAviso.setValue(listado);
                }
            }
            @Override
            public void onFailure(Call<List<Aviso>> call, Throwable t) { }
        });
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    public void eliminarAvisosSV(String palabra) {
    SharedPreferences sp=context.getSharedPreferences("token",0);
    String accessToken=sp.getString("token","");

    String[] partes = palabra.split("-");
    final int idGrupo = Integer.parseInt(partes[0]);
    Call<AvisosSinVer> bajaCall=ApiClient.getMyApiClient().borrarAvisosSinVer(accessToken,idGrupo);
    bajaCall.enqueue(new Callback<AvisosSinVer>() {
        @Override
        public void onResponse(Call<AvisosSinVer> call, Response<AvisosSinVer> response) {
            if(response.isSuccessful())
                Toast.makeText(context,"¡Listo!!! ya NO quedan avisos NUEVOS", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFailure(Call<AvisosSinVer> call, Throwable t) {

        }
    });

    }
}
