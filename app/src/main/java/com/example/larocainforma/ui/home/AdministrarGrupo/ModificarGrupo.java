package com.example.larocainforma.ui.home.AdministrarGrupo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.larocainforma.R;
import com.example.larocainforma.ui.home.Clases.Grupo;
import com.example.larocainforma.ui.home.Clases.Horario;
import com.example.larocainforma.ui.home.Perfil.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


public class ModificarGrupo extends Fragment {

    private ModificarGrupoVM vm;
    private TextView tvNombreGrupo;
    //----------------------------------------------------------------------------------------------
    private LinearLayout lyFechasM,lyFechas2M;
    private Button btnFechaIM,btnFechaFM,btnGuardarFechaM;
    private EditText etFechaInicioM,etFechaFinM;
    private RadioButton rbFechaInicioM, rbFechaFinM, rbAgregarHorario,rbEliminarHorario;
    private RadioGroup rgModificar;
    //----------------------------------------------------------------------------------------------
    private LinearLayout lyHorariosM,lyBotonesM,lyHoraM,lyHora2M;
    private Button btnHoraIM,btnHoraFM,btnAnteriorM,btnPosteriorM,btnDiaM,btnGuardarHorarioM;
    private EditText etInicioM,etFinM;
    private Spinner spHorarios;
    //----------------------------------------------------------------------------------------------
    private int idHorario,i,opcion;
    private Grupo miGrupo;
    private Horario miHorario,nuevo;
    private List<String> arregloDias,listaHorario;
    private List<Horario> listaHorarioMiGrupo,aux;

    public ModificarGrupo() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vm=ViewModelProviders.of(this).get(ModificarGrupoVM.class);
        final View view=inflater.inflate(R.layout.fragment_modificar_grupo, container, false);
        iniciarVista(view);
//------------- SELECCIONAR RADIOBUTTON-------------------------------------------------------------------
        rgModificar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if (id ==rbFechaInicioM.getId()){
                    lyHorariosM.setVisibility(View.GONE);
                    lyFechas2M.setVisibility(View.GONE);
                    spHorarios.setVisibility(View.GONE);

                    opcion=0;
                    lyFechasM.setVisibility(View.VISIBLE);
                    btnGuardarFechaM.setVisibility(View.VISIBLE);
                }
                else if (id == rbFechaFinM.getId()) {
                    lyHorariosM.setVisibility(View.GONE);
                    lyFechasM.setVisibility(View.GONE);
                    spHorarios.setVisibility(View.GONE);

                    opcion=1;
                    lyFechas2M.setVisibility(View.VISIBLE);
                    btnGuardarFechaM.setVisibility(View.VISIBLE);
                }
                else if (id == rbAgregarHorario.getId()) {
                    lyFechasM.setVisibility(View.GONE);
                    lyFechas2M.setVisibility(View.GONE);
                    btnGuardarFechaM.setVisibility(View.GONE);
                    etInicioM.setText("");
                    etFinM.setText("");

                    opcion=2;
                    btnGuardarHorarioM.setText("Guardar Horarios");
                    lyHorariosM.setVisibility(View.VISIBLE);
                    spHorarios.setVisibility(View.VISIBLE);
                    spHorarios.setSelection(0);
                }

                else if (id == rbEliminarHorario.getId()) {
                    lyFechasM.setVisibility(View.GONE);
                    lyFechas2M.setVisibility(View.GONE);
                    btnGuardarFechaM.setVisibility(View.GONE);
                    etInicioM.setText("");
                    etFinM.setText("");

                    opcion=4;
                    btnGuardarHorarioM.setText("Eliminar Horario");
                    spHorarios.setVisibility(View.VISIBLE);
                    spHorarios.setSelection(0);
                }
                else {
                    Toast.makeText(getContext(), "NADA", Toast.LENGTH_LONG).show();
                }
            }
        });
//-------------    GRUPO MUTABLE    ----------------------------------------------------------------
        vm.getGrupoMutableLiveData().observe(getViewLifecycleOwner(), new Observer<Grupo>() {
            @Override
            public void onChanged(Grupo grupo) {
                etFechaInicioM.setText(grupo.getFecha_inicio());
                etFechaFinM.setText(grupo.getFecha_fin());
                tvNombreGrupo.setText(grupo.getName());
                cargarSpinner(grupo.getListaHorarios());

                miGrupo.setGrupoId(grupo.getGrupoId());
                miGrupo.setName(grupo.getName());
                miGrupo.setActividadId(grupo.getActividadId());
                miGrupo.setCoordinadorId(grupo.getCoordinadorId());
                miGrupo.setFecha_inicio(grupo.getFecha_inicio());
                miGrupo.setFecha_fin(grupo.getFecha_fin());
                miGrupo.setOrden(grupo.getOrden());
                miGrupo.setBorrado(grupo.getBorrado());

            }
        });
//----------------- HORARIO SELECCIONADO------------------------------------------------------------
        spHorarios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String seleccionado=adapterView.getItemAtPosition(i).toString();
                String[] partes = seleccionado.split("-");
                if(partes.length>1) {
                       idHorario = Integer.parseInt(partes[0]);
                       lyHorariosM.setVisibility(View.VISIBLE);

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

//------------ BOTON  FECHA  INICIO---------------------------------------------------------/
        btnFechaIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarFecha();
            }
        });
//------------ BOTON  FECHA  FIN---------------------------------------------------------/
        btnFechaFM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarFecha();
            }
        });
//------------ BOTON GUARDAR FECHA---------------------------------------------------------
        btnGuardarFechaM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarCampoLLenoFecha();
            }
        });
//------------ BOTON ANTERIOR  ------------------------------------------------------------------
        btnAnteriorM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==0) i=6;
                else i--;
                btnDiaM.setText(arregloDias.get(i));
            }
        });
//------------ BOTON POSTERIOR  ------------------------------------------------------------
        btnPosteriorM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==6) i=0;
                else i++;
                btnDiaM.setText(arregloDias.get(i));
            }
        });
//------------ BOTON  HORA  INICIO---------------------------------------------------------/
        btnHoraIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarHora(true);
            }
        });
//------------ BOTON  HORA  FIN---------------------------------------------------------/
        btnHoraFM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarHora(false);
            }
        });
//------------ BOTON  GUARDAR HORARIO ---------------------------------------------------------/
        btnGuardarHorarioM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarCampoLLenoHora();
            }
        });
//--------------------------------------------------------------------------------------------------
        final String palabra=getArguments().getString("palabra");
        int id = Integer.parseInt(palabra);
        vm.cargarDatos(id);
        return view;
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    private void cargarSpinner(List<Horario> listaHorarios) {
       // Toast.makeText(getContext(), "total de horarios "+listaHorarios.size(), Toast.LENGTH_LONG).show();
        ArrayList<String> horariosGrupo=vm.cargaHorariosGrupo(listaHorarios);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,horariosGrupo);
        spHorarios.setAdapter(adapter);
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    private void verificarCampoLLenoHora() {
       // Toast.makeText(getContext(), "Entro a verificarCamposHora", Toast.LENGTH_LONG).show();
        String hora1 = "",hora2="";
        if(opcion==2 ){
            hora1 = etInicioM.getText().toString();
            hora2=etFinM.getText().toString();
            if(hora1.equals("") || hora2.equals(""))
                  Toast.makeText(getContext(), "Complete las horas de inicio y fin", Toast.LENGTH_LONG).show();
            else{
                nuevo=new Horario();
                nuevo.setHorarioId(idHorario);
                nuevo.setHora_inicio(etInicioM.getText().toString());
                nuevo.setHora_fin(etFinM.getText().toString());
                nuevo.setDia(i);
                nuevo.setBorrado(0);
                vm.horarioNuevo(nuevo);
                lyHorariosM.setVisibility(View.GONE);
              }
            vm.guardarUtiliza(miGrupo.getGrupoId());
        }
       if(opcion==4){
           vm.eliminarHorario(idHorario,miGrupo.getGrupoId());
        }
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    private void verificarCampoLLenoFecha() {
        String dato = "";
        if(opcion==0){
            dato="";
            dato = etFechaInicioM.getText().toString();
            //Toast.makeText(getContext(), "dato" + dato, Toast.LENGTH_LONG).show();
            if (dato.equals(""))
                Toast.makeText(getContext(), "Complete la nueva Fecha ", Toast.LENGTH_LONG).show();
            else {
                miGrupo.setFecha_inicio(dato);
                vm.modificarGrupo(miGrupo, "Se modificó la fecha de inicio");
                lyFechasM.setVisibility(View.GONE);
                btnGuardarFechaM.setVisibility(View.GONE);
            }
        }
        else if(opcion==1){
            dato="";
            dato = etFechaFinM.getText().toString();
           // Toast.makeText(getContext(), "dato" + dato, Toast.LENGTH_LONG).show();
            if (dato.equals(""))
                Toast.makeText(getContext(), "Complete la nueva Fecha ", Toast.LENGTH_LONG).show();
            else {
                miGrupo.setFecha_fin(dato);
                vm.modificarGrupo(miGrupo, "Se modificó la fecha de finalización");
                lyFechas2M.setVisibility(View.GONE);
                btnGuardarFechaM.setVisibility(View.GONE);
            }
        }
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    private void cargarFecha() {
        int dia,mes,anio;
        final Calendar calendario= Calendar.getInstance();
        dia=calendario.get(Calendar.DAY_OF_MONTH);
        mes=calendario.get(Calendar.MONTH);
        anio=calendario.get(Calendar.YEAR);
        DatePickerDialog dpd = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                if(opcion==0)
                { etFechaInicioM.setText(i2 + "/" + (i1+1)+ "/" + i);}
                else
                { etFechaFinM.setText(i2 + "/" + (i1+1) + "/" + i);}
            }
        }, dia, mes, anio);
        dpd.show();
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    private void cargarHora(final boolean primero) {
      //  Toast.makeText(getContext(), "Entro a cargarHora", Toast.LENGTH_LONG).show();
        int hora,minutos;
        final Calendar calendario= Calendar.getInstance();
        hora=calendario.get(Calendar.HOUR_OF_DAY);
        minutos=calendario.get(Calendar.MINUTE);
        TimePickerDialog tpd = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                if(primero){etInicioM.setText(i+":"+i1);}
                else {etFinM.setText(i+":"+i1);}
            }
        },hora,minutos,false);
        tpd.show();
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    private  void iniciarVista(View view){
        rgModificar=view.findViewById(R.id.rgModificar);
        rbFechaInicioM=view.findViewById(R.id.rbFechaInicioM);
        rbFechaFinM=view.findViewById(R.id.rbFechaFinM);
        rbAgregarHorario=view.findViewById(R.id.rbAgregarHorario);
        rbEliminarHorario=view.findViewById(R.id.rdbEliminarH);
        tvNombreGrupo=view.findViewById(R.id.tvNombreGrupoM);

        lyFechasM=view.findViewById(R.id.lyFechasM);
        lyFechas2M=view.findViewById(R.id.lyFechas2M);
        btnFechaFM=view.findViewById(R.id.btnFechaFM);
        btnFechaIM=view.findViewById(R.id.btnFechaIM);
        btnGuardarFechaM=view.findViewById(R.id.btnGuardarFechaM);
        etFechaInicioM=view.findViewById(R.id.etFechaInicioM);
        etFechaFinM=view.findViewById(R.id.etFechaFinM);

        spHorarios=view.findViewById(R.id.spHorarios);
        lyHorariosM=view.findViewById(R.id.lyHorariosM);
        lyBotonesM=view.findViewById(R.id.lyBotonesM);
        lyHoraM=view.findViewById(R.id.lyHoraM);
        lyHora2M=view.findViewById(R.id.lyHora2M);
        btnAnteriorM=view.findViewById(R.id.btnAnteriorM);
        btnPosteriorM=view.findViewById(R.id.btnPosteriorM);
        btnDiaM=view.findViewById(R.id.btnDiaM);
        btnHoraIM=view.findViewById(R.id.btnHoraIM);
        btnHoraFM=view.findViewById(R.id.btnHoraFM);
        btnGuardarHorarioM=view.findViewById(R.id.btnGuardarHorarioM);
        etInicioM=view.findViewById(R.id.etInicioM);
        etFinM=view.findViewById(R.id.etFinM);

        idHorario=0;
        i=1;
        miGrupo=new Grupo();
        arregloDias = Arrays.asList("DOMINGO","LUNES","MARTES","MIERCOLES","JUEVES","VIERNES","SABADO");
        btnDiaM.setText(arregloDias.get(1));
        listaHorario=new ArrayList<String>();
        spHorarios.setSelection(0);
    }
}
