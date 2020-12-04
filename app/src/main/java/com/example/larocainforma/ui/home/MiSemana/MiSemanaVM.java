package com.example.larocainforma.ui.home.MiSemana;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.larocainforma.ui.home.Clases.Grupo;
import com.example.larocainforma.ui.home.Clases.Utiliza;
import com.example.larocainforma.ui.home.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MiSemanaVM extends AndroidViewModel {

    private MutableLiveData<List<String>> utilizaMutableLiveData;
    private Context context;
    private List<Utiliza> lista;

    public MiSemanaVM(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<List<String>> getUtilizaMutableLiveData() {
        if (utilizaMutableLiveData == null) {
            utilizaMutableLiveData = new MutableLiveData<>();
        }
        return utilizaMutableLiveData;
    }

    public void cargarDatos(){
        SharedPreferences sp = context.getSharedPreferences("token", 0);
        String accessToken = sp.getString("token", "");
        Call<List<Utiliza>> utilizaCall=ApiClient.getMyApiClient().utilizaPorUsuario(accessToken);
        //Log.d("salida","entro a cargar datos");
        utilizaCall.enqueue(new Callback<List<Utiliza>>() {
            @Override
            public void onResponse(Call<List<Utiliza>> call, Response<List<Utiliza>> response) {
                Log.d("salida","entro a CALL");
                if(response.isSuccessful()){ Log.d("salida","response.isSuccessful()");
                    lista=response.body();
                    ArrayList<String> listado=new ArrayList<>();
                    String cadena="";
                    for(Utiliza aux:lista){
                        cadena=aux.getHorario().getDia()+"/"+aux.getGrupo().getName()+": "+aux.getHorario().getHora_inicio()+"-"+aux.getHorario().getHora_fin();
                        listado.add(cadena);
                        Log.d("salida",cadena);
                    };

                    utilizaMutableLiveData.setValue(listado);
                }
            }

            @Override
            public void onFailure(Call<List<Utiliza>> call, Throwable t) {

            }
        });
    }

    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

}
