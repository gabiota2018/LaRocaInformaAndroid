package com.example.larocainforma.ui.home.Logueo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.larocainforma.ui.home.Perfil.Usuario;
import com.example.larocainforma.ui.home.request.ApiClient;
import com.example.larocainforma.ui.home.request.SharedPreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.larocainforma.ui.home.request.SharedPreference.guardarToken;

public class LogueoViewModel extends AndroidViewModel {
    private MutableLiveData<String> etUsuario;
    private MutableLiveData<String> etPassword;
    private MutableLiveData<Integer> tvMensajeLogueo;
    private MutableLiveData<String> token;
    private Context context;
    private String miToken;
    private SharedPreference sp;

    public LogueoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Integer> getTvMensajeLogueo() {
        if (tvMensajeLogueo == null) {
            tvMensajeLogueo = new MutableLiveData<>();
        }
        return tvMensajeLogueo;
    }

    public LiveData<String> getToken() {
        if (token == null) {
            token = new MutableLiveData<>();
        }
        return token;
    }

    public LiveData<String> getEtUsuario() {
        if (etUsuario == null) {
            etUsuario = new MutableLiveData<>();
        }
        return etUsuario;
    }

    public LiveData<String> getEtPassword() {
        if (etPassword == null) {
            etPassword = new MutableLiveData<>();
        }
        return etPassword;
    }

    public void logueo(String nombre, String clave) {
        boolean rta = false;
        UsuarioLogin miUsuario = new UsuarioLogin(nombre, clave);
        Call<String> dato = ApiClient.getMyApiClient().login(miUsuario);

        dato.enqueue((new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    miToken=response.body();
                    token.postValue(miToken);
                    SharedPreferences sp = context.getSharedPreferences("token", 0);
                    SharedPreferences.Editor editor = sp.edit();
                    String t = "Bearer " + response.body();
                    editor.putString("token", t);
                    editor.commit();
                    guardarPreferenciasUsuario(miToken);
                    Log.d("salida ultimo token", miToken);




                }
                else tvMensajeLogueo.postValue(1);
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                tvMensajeLogueo.postValue(0);
                Log.d("salida Error", t.getMessage());
                Log.d("salida Error", call.request().body().toString());
                t.printStackTrace();
            }
        }));
    }
    private void guardarPreferenciasUsuario(String miToken){



    }
}
