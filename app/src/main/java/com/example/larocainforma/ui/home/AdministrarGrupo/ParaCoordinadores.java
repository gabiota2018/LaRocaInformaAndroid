package com.example.larocainforma.ui.home.AdministrarGrupo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.larocainforma.R;

import java.util.List;


public class ParaCoordinadores extends Fragment {

    private ParaCoordinadoresVM vm;
    private RadioButton rbNuevo, rbEnviar, rbModificar, rbSuspender, rbBorrar, rbActivar;
    private RadioGroup rgGrupo;
    private TextView tv4,tv5;
    private Spinner spActividades;
    private EditText etEnviarMensaje,etDias;
    private Button btnEnviarMensaje,btnSuspender;
    private View view;
    private List<String> miLista;
    private String opcion;
    private int idGrupo, numOpcion;
    private Boolean spinerVisible;
    private LinearLayout lyAmarillo;

    public ParaCoordinadores() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vm= ViewModelProviders.of(this).get(ParaCoordinadoresVM.class);
        view= inflater.inflate(R.layout.fragment_para_coordinadores, container, false);
        iniciarVista();
        //------------- SELECCIONAR RADIOBUTTON-------------------------------------------------------------------
        rgGrupo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if (id == rbNuevo.getId())
                {Navigation.findNavController(view).navigate(R.id.nuevoGrupo);
                    //idGrupo=0; nuevoModificar();
                }
                else if (id == rbEnviar.getId()) {
                    numOpcion=1;
                    visibilizar1("para ENVIAR el MENSAJE");
                }
                else if (id == rbModificar.getId()) {
                    invisible();
                    numOpcion=2;
                    visibilizar1("que desee MODIFICAR");
                }
                else if (id == rbSuspender.getId()) {
                    invisible();
                    numOpcion=3;
                    visibilizar1("que desee SUSPENDER");
                }
                else if (id == rbBorrar.getId()) {
                    invisible();
                    numOpcion=4;//terminar no borrar
                    visibilizar1("que va a TERMINAR su actividad");
                }
                else if (id == rbActivar.getId()) {
                    invisible();
                    numOpcion=5;
                    visibilizar1("que desee ACTIVAR");
                }
                else {
                    Toast.makeText(getContext(), "NADA", Toast.LENGTH_LONG).show();
                }
            }
        });
       //------------- CARGAR SPINNER---------------------------------------------------------------
        vm.getListaDeGrupo().observe(getViewLifecycleOwner(),new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,strings);
                spActividades.setAdapter(adapter);
            }
        });
        //----------- GRUPO SELECCIONADO------------------------------------------------------------
        spActividades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String seleccionado=adapterView.getItemAtPosition(i).toString();
               procesarItem(seleccionado);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
        //------------ BOTON ENVIAR MENSAJE---------------------------------------------------------
        btnEnviarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarCampoLLeno();
            }
        });
        //------------ BOTON SUSPENDER GRUPO---------------------------------------------------------
        btnSuspender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.estadoGrupo(idGrupo,1);
            }
        });
        //--------------------------------------------------------------------------------
        vm.cargarDatos();
        return view;
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    private void verificarCampoLLeno() {
        String mensaje="";
        mensaje=etEnviarMensaje.getText().toString();
        if(mensaje.equals("") )
            Toast.makeText(getContext(), "Escriba el mensaje ", Toast.LENGTH_LONG).show();
        else
         {
             String value= etDias.getText().toString();
             int dias=Integer.parseInt(value);
             vm.enviarMensaje(idGrupo,mensaje,dias);
             //Toast.makeText(getContext(), "idGrupo:"+idGrupo+" mensaje "+mensaje+" dias"+dias, Toast.LENGTH_LONG).show();
         }
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    private void visibilizar1(String opcion){
        tv4.setText("SELECCIONE el GRUPO "+ opcion);
        spActividades.setVisibility(View.VISIBLE);
        tv4.setVisibility(View.VISIBLE);
        lyAmarillo.setVisibility(View.VISIBLE);
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    private void  invisible(){
        spActividades.setVisibility(View.INVISIBLE);
        tv4.setVisibility(View.INVISIBLE);
        lyAmarillo.setVisibility(View.INVISIBLE);
        etEnviarMensaje.setVisibility(View.INVISIBLE);
        btnEnviarMensaje.setVisibility(View.INVISIBLE);
        tv5.setVisibility(View.INVISIBLE);
        etDias.setVisibility(View.INVISIBLE);
        lyAmarillo.setVisibility(View.GONE);
        btnEnviarMensaje.setVisibility(View.GONE);
        btnSuspender.setVisibility(View.GONE);
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    private void enviarMensaje(){
        etEnviarMensaje.setVisibility(View.VISIBLE);
        btnEnviarMensaje.setVisibility(View.VISIBLE);
        tv5.setVisibility(View.VISIBLE);
        etDias.setVisibility(View.VISIBLE);
        lyAmarillo.setVisibility(View.VISIBLE);
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    private void modificarGrupo(int idGrupo){
       // Toast.makeText(getContext(), "idGrupo:"+idGrupo, Toast.LENGTH_LONG).show();
        Bundle bundle=new Bundle();
        String palabra=Integer.toString(idGrupo);

        bundle.putString("palabra",palabra);
        Navigation.findNavController(view).navigate(R.id.modificarGrupo, bundle);
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    private void procesarItem(String seleccionado){
       // Toast.makeText(getContext(), "seleccionado "+seleccionado, Toast.LENGTH_LONG).show();

        String[] partes = seleccionado.split("-");
        if(partes.length>1)
        {
            idGrupo = Integer.parseInt(partes[0]);//Toast.makeText(getContext(), "idGrupo "+idGrupo, Toast.LENGTH_LONG).show();
            switch (numOpcion){
                case 1: btnEnviarMensaje.setVisibility(View.VISIBLE);
                        enviarMensaje();break;
                case 2: modificarGrupo(idGrupo);break;
                case 3: btnSuspender.setVisibility(View.VISIBLE);break;
                case 4: vm.estadoGrupo(idGrupo,2);break;
                case 5: vm.estadoGrupo(idGrupo,0);break;
            }
        }

    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    private  void iniciarVista(){
        rgGrupo = view.findViewById(R.id.rgGrupo);
        rbNuevo = view.findViewById(R.id.rbAgregar);
        rbEnviar = view.findViewById(R.id.rbEnviarMensaje);
        rbModificar = view.findViewById(R.id.rbModificar);
        rbSuspender = view.findViewById(R.id.rbSuspender);
        rbBorrar = view.findViewById(R.id.rbBorrar);
        rbActivar = view.findViewById(R.id.rbActivar);

        tv4 = view.findViewById(R.id.tv4);
        tv5 = view.findViewById(R.id.tv5);

        spActividades = view.findViewById(R.id.spGrupos);

        etEnviarMensaje = view.findViewById(R.id.etEnviarMensaje);
        etDias=view.findViewById(R.id.etDias);

        btnEnviarMensaje = view.findViewById(R.id.btnEnviarMensaje);
        btnSuspender = view.findViewById(R.id.btnSuspender);

        lyAmarillo=view.findViewById(R.id.lyAmarillo);
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
}
