package com.example.larocainforma.ui.home.AdministrarGrupo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.larocainforma.R;
import com.example.larocainforma.ui.home.Clases.Actividad;
import com.example.larocainforma.ui.home.Clases.Grupo;
import com.example.larocainforma.ui.home.Clases.Horario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class NuevoGrupo extends Fragment {

    private NuevoGrupoVM vm;
    private Spinner spActividades;
    private TextView tv3crear,tv4crear,tv5crear;
    private LinearLayout lyBotones,lyHora,lyFechas,lyFinal;
    private Button btnAnterior,btnDia,btnPosterior,btnGuardarHorario,btnCrearGrupo,btnFechaI,btnFechaF,btnHoraI,btnHoraF;
    private EditText etInicio,etFinal,etNombre,etFechaInicio,etFechaFin;
    private int idActividad,i;
    private ArrayList<Horario> listaHorario;
    private Horario miHorario;
    private  Grupo miGrupo,otro;
    private List<String> arregloDias;

    public NuevoGrupo() {}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vm= ViewModelProviders.of(this).get(NuevoGrupoVM.class);
        View view=inflater.inflate(R.layout.fragment_nuevo_grupo, container, false);
        iniciarVista(view);

        //------------- CARGAR SPINNER---------------------------------------------------------------
        vm.getListaDeActividades().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,strings);
                spActividades.setAdapter(adapter);
            }
        });
        //------------- VM miGrupo  ---------------------------------------------------------------
        vm.getMiGrupoLD().observe(getViewLifecycleOwner(), new Observer<Grupo>() {
            @Override
            public void onChanged(Grupo grupo) {
                vm.guardarUtiliza(grupo.getGrupoId());

                etNombre.setText("Se creó el "+grupo.getName());
            }
        });
        //----------- ACTIVIDAD SELECCIONADA------------------------------------------------------------
        spActividades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String seleccionado=adapterView.getItemAtPosition(i).toString();
                String[] partes = seleccionado.split("-");
                if(partes.length>1) {
                    idActividad = Integer.parseInt(partes[0]);
                    tv3crear.setVisibility(View.VISIBLE);
                    lyFechas.setVisibility(View.VISIBLE);
                    tv4crear.setVisibility(View.VISIBLE);
                    lyBotones.setVisibility(View.VISIBLE);
                    lyHora.setVisibility(View.VISIBLE);
                    lyFinal.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
        //------------ BOTON GUARDAR HORARIO---------------------------------------------------------
        btnGuardarHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarCamposLLenos();
            }
        });
        //------------ BOTON ANTERIOR  ------------------------------------------------------------------
        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==0) i=6;
                else i--;
                btnDia.setText(arregloDias.get(i));
            }
        });
        //------------ BOTON POSTERIOR  ------------------------------------------------------------
        btnPosterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==6) i=0;
                else i++;
                btnDia.setText(arregloDias.get(i));
            }
        });
        //------------ BOTON CREAR GRUPO -----------------------------------------------------------
        btnCrearGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etFechaInicio.getText().equals("") || etFechaFin.getText().equals(""))
                    Toast.makeText(getContext(), "Complete la FECHA de inicio y de fin ", Toast.LENGTH_LONG).show();
                else {
                    miGrupo = new Grupo();
                    miGrupo.setActividadId(idActividad);
                    miGrupo.setName("d");
                    miGrupo.setFecha_inicio(etFechaInicio.getText().toString());
                    miGrupo.setFecha_fin(etFechaFin.getText().toString());
                    vm.crearGrupo(miGrupo);
                   // vm.ultimoGrupo();
                    etNombre.setVisibility(View.VISIBLE);
                    //guarda el grupo, horarios y utiliza
                      for (Horario h:listaHorario) {
                        vm.horarioNuevo(h);
                   }

                }
            }
        });
        //------------------------------------------------------------------------------------------
        btnFechaI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarFecha(true);
            }
        });
        //------------------------------------------------------------------------------------------
       btnFechaF.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               cargarFecha(false);
           }
       });
       //------------------------------------------------------------------------------------------
        btnHoraI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarHora(true);
            }
        });
        //------------------------------------------------------------------------------------------
        btnHoraF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarHora(false);
            }
        });
        //------------------------------------------------------------------------------------------
        vm.cargarDatos();
        return  view;
    }

    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    private void cargarHora(final boolean inicial) {
        int hora,minutos;
        final Calendar calendario= Calendar.getInstance();
        hora=calendario.get(Calendar.HOUR_OF_DAY);
        minutos=calendario.get(Calendar.MINUTE);
        TimePickerDialog tpd = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                if(inicial){etInicio.setText(i+":"+i1);}
                else {etFinal.setText(i+":"+i1);}
            }
        },hora,minutos,false);
        tpd.show();

    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    private void cargarFecha(final Boolean primero) {
        int dia,mes,anio;
        final Calendar calendario= Calendar.getInstance();
        dia=calendario.get(Calendar.DAY_OF_MONTH);
        mes=calendario.get(Calendar.MONTH);
        anio=calendario.get(Calendar.YEAR);
        DatePickerDialog dpd = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                if(primero)
                { etFechaInicio.setText(i2 + "/" + (i1+1)+ "/" + i);}
                else
                { etFechaFin.setText(i2 + "/" + (i1+1) + "/" + i);}

            }
        }, dia, mes, anio);
        dpd.show();
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    private void verificarCamposLLenos() {

        String mensaje,mensaje2;
        mensaje=etInicio.getText().toString();
        mensaje2=etFinal.getText().toString();
        if(mensaje.equals("") || mensaje2.equals(""))
             {Toast.makeText(getContext(), "Complete la hora de inicio y de fin ", Toast.LENGTH_LONG).show();}
        else if(mensaje.equals(mensaje2))
             {Toast.makeText(getContext(), "Las HORAS de inicio y de fin, deben ser distintas ", Toast.LENGTH_LONG).show();}
        else{
            miHorario=new Horario();
            miHorario.setHora_inicio(mensaje);
            miHorario.setHora_fin(mensaje2);
            miHorario.setDia(i);
            listaHorario.add(miHorario);
            Toast.makeText(getContext(), "OK", Toast.LENGTH_LONG).show();
        }

//        miHorario=new Horario();
//            miHorario.setHora_inicio(etInicio.getText().toString());
//            miHorario.setHora_fin(etFinal.getText().toString());
//            miHorario.setDia(i);
//            miHorario.setBorrado(0);
//       // Toast.makeText(getContext(), "h.dia "+miHorario.getDia()+"h.inicio "+miHorario.getHora_inicio()+"h.fin "+miHorario.getHora_fin(), Toast.LENGTH_LONG).show();
//          vm.horarioNuevo(miHorario);
//           // listaHorario.add(miHorario);
//        //Toast.makeText(getContext(), "lista elementos "+listaHorario.size(), Toast.LENGTH_LONG).show();
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    private  void iniciarVista(View view){
        spActividades = view.findViewById(R.id.spGrupos);
        tv3crear=view.findViewById(R.id.tv3crear);
        tv4crear=view.findViewById(R.id.tv4crear);
        tv5crear=view.findViewById(R.id.tv5crear);

        lyBotones=view.findViewById(R.id.lyBotones);
        lyHora=view.findViewById(R.id.lyHora);
        lyFinal=view.findViewById(R.id.lyFinal);
        lyFechas=view.findViewById(R.id.lyFechas);

        btnAnterior=view.findViewById(R.id.btnAnterior);
        btnDia=view.findViewById(R.id.btnDia);
        btnPosterior=view.findViewById(R.id.btnPosterior);
        btnGuardarHorario=view.findViewById(R.id.btnGuardarHorario);
        btnCrearGrupo=view.findViewById(R.id.btnCrearGrupo);
        btnFechaI=view.findViewById(R.id.btnFechaI);
        btnFechaF=view.findViewById(R.id.btnFechaF);
        btnHoraI=view.findViewById(R.id.btnHoraI);
        btnHoraF=view.findViewById(R.id.btnHoraF);

        etInicio=view.findViewById(R.id.etInicio);
        etFinal=view.findViewById(R.id.etFin);
        etFechaInicio=view.findViewById(R.id.etFechaInicio);
        etFechaFin=view.findViewById(R.id.etFechaFin);
        etNombre=view.findViewById(R.id.etNombre);

        idActividad=0;
        i=1;
        miHorario=new Horario();
        miGrupo=new Grupo();
        arregloDias = Arrays.asList("DOMINGO","LUNES","MARTES","MIERCOLES","JUEVES","VIERNES","SABADO");
        btnDia.setText(arregloDias.get(1));
        miHorario.setDia(i);
        listaHorario=new ArrayList<Horario>();
    }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
}
