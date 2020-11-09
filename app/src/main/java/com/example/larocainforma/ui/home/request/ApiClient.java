package com.example.larocainforma.ui.home.request;

import android.util.Log;

import com.example.larocainforma.ui.home.Clases.Actividad;
import com.example.larocainforma.ui.home.Clases.Aviso;
import com.example.larocainforma.ui.home.Clases.AvisosSinVer;
import com.example.larocainforma.ui.home.Clases.Cumple;
import com.example.larocainforma.ui.home.Clases.GrupoVista;
import com.example.larocainforma.ui.home.Clases.Horario;
import com.example.larocainforma.ui.home.Clases.Participa;
import com.example.larocainforma.ui.home.Clases.Grupo;
import com.example.larocainforma.ui.home.Clases.Utiliza;
import com.example.larocainforma.ui.home.Logueo.UsuarioLogin;
import com.example.larocainforma.ui.home.Perfil.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class ApiClient {
    private static final String PATH="http://192.168.0.4:45455/api/";
    private static  MyApiInterface myApiInteface;
    private static String accessToken=null;


    public static MyApiInterface getMyApiClient(){


        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        myApiInteface=retrofit.create(MyApiInterface.class);
        Log.d("salida",retrofit.baseUrl().toString());
        return myApiInteface;
    }

    public interface MyApiInterface {
//------ USUARIO -----------------------------------------------------------------------------------
        @POST("usuario/Login")
        Call<String> login(@Body UsuarioLogin miUsuario);

        @GET("usuario")
        Call<Usuario> obtenerDatos(@Header("Authorization")String token);

        @GET("usuario/{id}")
        Call<Usuario> unUsuario(@Header("Authorization") String token,@Path("id")int id);

        @PUT("usuario/{id}")
        Call<Usuario> actualizar(@Header("Authorization")String token,@Path("id") int id,@Body Usuario p);

//------ ACTIVIDAD -----------------------------------------------------------------------------------
        @GET("actividad")
        Call<List<Actividad>> listarActividades(@Header("Authorization") String token);

        @GET("actividad/HabiliatadasPorUsuario")
        Call<List<Actividad>> listarHabilitadas(@Header("Authorization") String token);

//------ GRUPO -----------------------------------------------------------------------------------

        @GET("grupo/DetalleGrupo/{id}")
        Call<Grupo> detalleGrupo(@Header("Authorization") String token,@Path("id")int id);

        @GET("grupo/PorUsuario")
        Call<List<Grupo>> listarGruposUsuario(@Header("Authorization") String token);

        @GET("grupo/PorActividad/{id}")
        Call<List<Grupo>> gruposPorActividad(@Header("Authorization") String token,@Path("id")int id);


        //------ PARTICIPA -----------------------------------------------------------------------------------
        @DELETE("participa/BajaLogica/{id}")
        Call<Participa> bajaParticipa(@Header("Authorization")String token,@Path("id")int id);
//------ AVISO -----------------------------------------------------------------------------------

        @GET("aviso/AvisoGrupo/{id}")
        Call<List<Aviso>> activosAvisosGrupo(@Header("Authorization") String token,@Path("id")int id);

//------ AVISOsinVER -----------------------------------------------------------------------------------

        @GET("avisosSinVer/{id}")
        Call<List<AvisosSinVer>> avisosSinVer(@Header("Authorization") String token, @Path("id")int id);

        @DELETE("avisosSinVer/{id}")
        Call<AvisosSinVer> borrarAvisosSinVer(@Header("Authorization") String token, @Path("id")int id);
//------ UTILIZA -----------------------------------------------------------------------------------

        @GET("utiliza/UtilizaPorUsuario")
        Call<List<Utiliza>> utilizaPorUsuario(@Header("Authorization") String token);



        //@GET("aviso/PorGrupo")
        //Call<List<Aviso>> listarAvisosGrupo(@Header("Authorization") String token, @Path("id")int id);
/*
        @FormUrlEncoded
        @PUT("usuario/{id}")
        Call<DuenioEvento> guardar(@Header("Authorization")String authorizarion,
                                   @Path("id")int idDuenio,
                                   @Field("Nombre") String nombre,
                                   @Field("Apellido")String apellido,
                                   @Field("Correo") String correo,
                                   @Field("Clave")String clave,
                                   @Field("EstadoDuenio")int estado);

        @GET("Inmuebles")
        Call<List<Inmuebles>> listarInmuebles(@Header("Authorization") String token);

        @GET("Inmuebles/{id}")
        Call<Inmuebles> obtenerDatosInmueble(@Header("Authorization") String token,@Path("id")int id);

        @PUT("Inmuebles/{id}")
        Call<Inmuebles> actualizarInmueble(@Header("Authorization") String token, @Path("id") int id, @Body Inmuebles inmueble);

        @POST("Inmuebles")
        Call<Inmuebles> guardarInmueble(@Header("Authorization") String token, @Body Inmuebles inmueble);

        @DELETE("Inmuebles/{id}")
        Call<Inmuebles> bajaInmueble(@Header("Authorization")String token,@Path("id")int id);

       */
    }

}
