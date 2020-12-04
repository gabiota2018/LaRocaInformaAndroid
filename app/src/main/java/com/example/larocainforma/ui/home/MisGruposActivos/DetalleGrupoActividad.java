package com.example.larocainforma.ui.home.MisGruposActivos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.larocainforma.R;
import com.example.larocainforma.ui.home.Clases.Aviso;
import com.example.larocainforma.ui.home.Clases.AvisosSinVer;
import com.example.larocainforma.ui.home.Clases.Grupo;
import com.example.larocainforma.ui.home.Clases.GrupoVista;
import com.example.larocainforma.ui.home.Clases.Horario;
import com.example.larocainforma.ui.home.Clases.Participa;
import com.example.larocainforma.ui.home.Perfil.Usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DetalleGrupoActividad extends Fragment {

    private EditText etNombreGrupo,etTema,etProximaReunion,etEncargado,etMensajeAviso;
    private Spinner spCompanieros;
    private Button btnVerAvisos, btnDejarGrupo;
    private DetalleGrupoActividadVM vm;
    private  String proximo;
    private Grupo miGrupo;


    public DetalleGrupoActividad() {}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vm= ViewModelProviders.of(this).get(DetalleGrupoActividadVM.class);
        View view=inflater.inflate(R.layout.fragment_detalle_grupoactividad, container, false);
        etNombreGrupo=view.findViewById(R.id.etNombreActividad);
        etTema=view.findViewById(R.id.etTema);
        etProximaReunion=view.findViewById(R.id.etProximaReunion);
        etEncargado=view.findViewById(R.id.etEncargado);
        etMensajeAviso=view.findViewById(R.id.etMensajeAvisos);
        spCompanieros=view.findViewById(R.id.spCompanieros);
        btnDejarGrupo=view.findViewById(R.id.btnDejarGrupo);
        btnVerAvisos=view.findViewById(R.id.btnVerAvisos);
       //------------------------------------------------------------------------------------------
        vm.getGrupoMutableLiveData().observe(getViewLifecycleOwner(), new Observer<Grupo>() {
            @Override
            public void onChanged(Grupo grupo) {
                etNombreGrupo.setText(grupo.getName());
                etTema.setText("Ultimo Tema: Efesios 6");
                String mensaje=  vm.calculaFecha(grupo.getListaHorarios());//proximaFecha(grupo.getListaHorarios());
                etProximaReunion.setText("Lunes de 20:30 a 21:30");
                etEncargado.setText("Coordinador: "+grupo.getCoordinador().getApellido()+""+grupo.getCoordinador().getNombre());
                cargarSpinner(grupo.getListaParticipantes());
                miGrupo=grupo;
            }
        });
        //------------------------------------------------------------------------------------------
        vm.getAsvMLD().observe(getViewLifecycleOwner(), new Observer<List<AvisosSinVer>>() {
            @Override
            public void onChanged(List<AvisosSinVer> avisosSinVers) {
                int contador=avisosSinVers.size();
                String mensaje="Ver AVISOS del Grupo";
                if(contador==1)
                    mensaje="Tienes 1 aviso NUEVO";
                else {
                    if(contador >1)
                        mensaje="Tienes "+contador+" avisos NUEVOS";}
                btnVerAvisos.setText(mensaje);
            }
        });
        //------------------------------------------------------------------------------------------
        btnDejarGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                darBaja();
            }
        });
        //------------------------------------------------------------------------------------------
        btnVerAvisos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putString("palabra",miGrupo.getGrupoId()+"");
                bundle.putString("nombreGrupo",miGrupo.getName());
                Navigation.findNavController(view).navigate(R.id.avisosActividad,bundle);
            }
        });
        //------------------------------------------------------------------------------------------
        final String palabra=getArguments().getString("palabra");
        vm.cargarDatos(palabra);
        return  view;
    }
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    private  String calculaMensajeA(List<Aviso> listaA){
        String rta="";
        rta=vm.calculaAvisos(listaA);
        return  rta;
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    private  void  cargarSpinner(List<Usuario> listaU){
        ArrayList<String> participantes=vm.cargaParticipantes(listaU);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,participantes);
        spCompanieros.setAdapter(adapter);
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    public  void darBaja( ){
            AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getContext(),R.style.Theme_MaterialComponents);
            dialogo1.setTitle("Importante, Dejarás de ser parte del grupo");
            dialogo1.setMessage("¿Seguro quieres irte :( ?");
            dialogo1.setCancelable(false);
            dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    aceptar(id);

                }
            });
            dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    cancelar();
                }
            });
            dialogo1.show();
        }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
        public void aceptar(int id) {
            vm.SalirDelGrupo(miGrupo.getGrupoId());
            //Toast.makeText(getContext(),"¡Listo!!!", Toast.LENGTH_LONG).show();
            //volver al fragment misAvtividades
            //Navigation.findNavController(view).navigate(R.id.nueva_propiedad);
           }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
        public void cancelar() {
            //finish();
            Toast.makeText(getContext(),"CANCELADO.", Toast.LENGTH_SHORT).show();
        }
}
