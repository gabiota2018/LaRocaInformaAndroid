package com.example.larocainforma.ui.home.MisGruposActivos;

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
import com.example.larocainforma.ui.home.Clases.AvisosSinVer;
import com.example.larocainforma.ui.home.Clases.Grupo;
import com.example.larocainforma.ui.home.Clases.GrupoVista;
import com.example.larocainforma.ui.home.Clases.Horario;
import com.example.larocainforma.ui.home.Clases.Participa;
import com.example.larocainforma.ui.home.Perfil.Usuario;
import com.example.larocainforma.ui.home.request.ApiClient;
import com.example.larocainforma.ui.home.request.SharedPreference;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleGrupoActividadVM extends AndroidViewModel {

    private MutableLiveData<Grupo> grupoMutableLiveData;
    private MutableLiveData<List<AvisosSinVer>> asvMLD;
    private Context context;
    private String accessToken;

    public DetalleGrupoActividadVM(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }


    public LiveData<Grupo> getGrupoMutableLiveData() {
        if (grupoMutableLiveData == null)
            grupoMutableLiveData = new MutableLiveData<>();
        return grupoMutableLiveData;
    }
    public  LiveData<List<AvisosSinVer>> getAsvMLD(){
        if(asvMLD==null) asvMLD=new MutableLiveData<>();
        return asvMLD;
    }

    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    public void cargarDatos(String palabra) {
        SharedPreferences sp = context.getSharedPreferences("token", 0);
        accessToken = sp.getString("token", "");
        String[] partes = palabra.split("-");
        final int idGrupo = Integer.parseInt(partes[0]);
//        Log.d("salida","entro a grupoCall");

        Call<Grupo> grupoCall=ApiClient.getMyApiClient().detalleGrupo(accessToken,idGrupo);
        grupoCall.enqueue(new Callback<Grupo>() {
            @Override
            public void onResponse(Call<Grupo> call, Response<Grupo> response) {
                if(response.isSuccessful())
                    //Toast.makeText(context,"idGrupo OK "+idGrupo, Toast.LENGTH_LONG).show();
                     grupoMutableLiveData.postValue(response.body());
                     //traer avisos sin ver por usuario y grupo
                    // cuentaAvisosSinVer(idGrupo);
            }

            @Override
            public void onFailure(Call<Grupo> call, Throwable t) {

            }
        });
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    private void cuentaAvisosSinVer(int idGrupo) {
        Call<List<AvisosSinVer>> sinVerCall=ApiClient.getMyApiClient().avisosSinVer(accessToken,idGrupo);
        sinVerCall.enqueue(new Callback<List<AvisosSinVer>>() {
            @Override
            public void onResponse(Call<List<AvisosSinVer>> call, Response<List<AvisosSinVer>> response) {
                if(response.isSuccessful())
                    asvMLD.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<AvisosSinVer>> call, Throwable t) {

            }
        });
    }

    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    public  String calculaFecha(List<Horario> listaH){
       String diaSemana,rta="Hola";
        List<Integer> distancias= new ArrayList<Integer>();
        boolean encontre=false;
        Calendar calendar=Calendar.getInstance();
        int hoy = calendar.get(Calendar.DAY_OF_WEEK);


        int contador=0;
        int salto=hoy-1;
        int pos=0;

        for (Horario h:listaH) {
           while (!encontre){
               if(salto==7){ salto=0;}
               if(contador==h.getDia()){
                    distancias.add(contador);
                    encontre=true;
                }
                contador++;
                salto++;
           }
        }
        if(distancias.size()>1) pos=buscarMenor(distancias);
        diaSemana=nombreDia(listaH.get(pos).getDia());
        rta="Próximo encuentro: "+" "+diaSemana+" a las  "+listaH.get(pos).getHora_inicio();


        return  rta;

    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    private int buscarMenor(List<Integer> distancias) {
        int pos=0,menor=distancias.get(0);
        for (int i=0;i<distancias.size();i++) {
            if(distancias.get(i)<menor) {
                menor=distancias.get(i);
                pos=i;
            }
        }
        return pos;
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
    public String calculaAvisos(List<Aviso> listaA) {
        String rta="No hay avisos";
        int cantidad=0;
        if(listaA.size()>0) {
            cantidad=listaA.size();
            rta="Hay "+cantidad+" avisos NUEVOS";
        }
        return  rta;
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    public ArrayList<String> cargaParticipantes(List<Usuario> listaU) {

        ArrayList<String> rta=new ArrayList<>();
        rta.add("Participantes del grupo");
        for (Usuario usu:listaU) {
            rta.add(usu.getNombre()+" "+usu.getApellido()+" "+usu.getTelefono());
        }
        return rta;
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    public void SalirDelGrupo(int grupoId) {
        Call<Participa> bajaCall = ApiClient.getMyApiClient().bajaParticipa(accessToken,grupoId);
        bajaCall.enqueue(new Callback<Participa>() {
            @Override
            public void onResponse(Call<Participa> call, Response<Participa> response) {
                if(response.isSuccessful())
                Toast.makeText(context, "Ya NO ESTAS en este GRUPO...", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<Participa> call, Throwable t) {
            }
        });


    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    public String ultimoAviso(List<Aviso> listaAvisos) {
        String rta="";
        if(listaAvisos.size()>0) {
            rta = "Último aviso: "+ listaAvisos.get(listaAvisos.size()-1).getContenido();
        }
        else rta="No hay notificaciones";
        return  rta;
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
}
