package com.example.larocainforma.ui.home.AdministrarGrupo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.larocainforma.ui.home.Clases.Grupo;
import com.example.larocainforma.ui.home.Clases.Horario;
import com.example.larocainforma.ui.home.Clases.Utiliza;
import com.example.larocainforma.ui.home.Perfil.Usuario;
import com.example.larocainforma.ui.home.request.ApiClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModificarGrupoVM extends AndroidViewModel {

    private MutableLiveData<Grupo> grupoMutableLiveData;
    private MutableLiveData<List<String>> listaDeHorarios;
    private Context context;
    private String accessToken;

    public ModificarGrupoVM(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    public LiveData<Grupo> getGrupoMutableLiveData() {
        if (grupoMutableLiveData == null)
            grupoMutableLiveData = new MutableLiveData<>();
        return grupoMutableLiveData;
    }
    public LiveData<List<String>> getListaDeHorarios() {
        if(listaDeHorarios==null){
            listaDeHorarios=new MutableLiveData<>();
        }
        return listaDeHorarios;
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    public void cargarDatos(int idGrupo) {
        SharedPreferences sp = context.getSharedPreferences("token", 0);
        accessToken = sp.getString("token", "");
        Call<Grupo> grupoCall= ApiClient.getMyApiClient().miniDetalleGrupo(accessToken,idGrupo);
        grupoCall.enqueue(new Callback<Grupo>() {
            @Override
            public void onResponse(Call<Grupo> call, Response<Grupo> response) {
                if(response.isSuccessful())
                    grupoMutableLiveData.postValue(response.body());
            }
            @Override
            public void onFailure(Call<Grupo> call, Throwable t) { }
        });
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    public void modificarGrupo(Grupo miGrupo, final String mensaje){
        SharedPreferences sp = context.getSharedPreferences("token", 0);
        accessToken = sp.getString("token", "");

       // Toast.makeText(context, "entro a modificar"+miGrupo.getGrupoId()+" fecha inicio "+miGrupo.getFecha_inicio(), Toast.LENGTH_LONG).show();

        Call<Grupo> gCall=ApiClient.getMyApiClient().actualizarGrupo(accessToken,miGrupo);
        gCall.enqueue(new Callback<Grupo>() {
          @Override
          public void onResponse(Call<Grupo> call, Response<Grupo> response) {
              if(response.isSuccessful())
                  Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show();
          }
          @Override
          public void onFailure(Call<Grupo> call, Throwable t) { }
        });
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    public void horarioNuevo(final Horario miHorario) {
        SharedPreferences sp=context.getSharedPreferences("token",0);
        String accessToken=sp.getString("token","");

        guardarUtiliza(miHorario.getHorarioId());
        Call<Horario> g=ApiClient.getMyApiClient().crearHorario(accessToken,miHorario);
        g.enqueue(new Callback<Horario>() {
            @Override
            public void onResponse(Call<Horario> call, Response<Horario> response) {
                if(response.isSuccessful()){
                    //Toast.makeText(context, "Horario nuevo", Toast.LENGTH_LONG).show();
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
           public void onFailure(Call<Utiliza> call, Throwable t) {

           }
       });
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    public void eliminarHorario(int idHorario,int idGrupo) {
        Toast.makeText(context, "Entro a ELIMINAR "+idHorario, Toast.LENGTH_SHORT).show();
        //elimina el utiliza, en caso de
        //que quede solo el horario, si lo elimina
        Utiliza utiliza=new Utiliza();
        utiliza.setGrupoId(idGrupo);
        utiliza.setHorarioId(idHorario);
        Call<Utiliza> utilizaCall=ApiClient.getMyApiClient().desactivar(accessToken,utiliza);
        utilizaCall.enqueue(new Callback<Utiliza>() {
            @Override
            public void onResponse(Call<Utiliza> call, Response<Utiliza> response) {
                if(response.isSuccessful())
                    Toast.makeText(context, "Horario Eliminado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Utiliza> call, Throwable t) {

            }
        });

    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    public ArrayList<String> cargaHorariosGrupo(List<Horario> listaH) {
        ArrayList<String> rta=new ArrayList<>();
        rta.add("Horarios asignados a este grupo");
        for (Horario usu:listaH) {
            String proximo="";
            switch (usu.getDia()){
                case 0: proximo="DOMINGO";break;
                case 1: proximo="LUNES";break;
                case 2: proximo="MARTES";break;
                case 3: proximo="MIERCOLES";break;
                case 4: proximo="JUEVES";break;
                case 5: proximo="VIERNES";break;
                case 6: proximo="SABADO";break;
            }

            rta.add(usu.getHorarioId()+"-"+proximo+": desde "+usu.getHora_inicio()+" hs. hasta  "+usu.getHora_fin()+" hs.");
        }
        return rta;
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

}
