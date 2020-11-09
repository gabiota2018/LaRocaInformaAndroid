package com.example.larocainforma.ui.home.Avisos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.larocainforma.R;
import com.example.larocainforma.ui.home.MisGruposActivos.MisActividadesVM;

import java.util.List;


public class AvisosActividad extends Fragment {
    private TextView tvAvisosGrupo;
    private ListView lv;
    private AvisosActividadVM vm;



    public AvisosActividad() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm= ViewModelProviders.of(this).get(AvisosActividadVM.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_avisos_actividad, container, false);
        tvAvisosGrupo=view.findViewById(R.id.tvAvisosActividad);
        vm.getListaDeAviso().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,strings);
                lv.setAdapter(adapter);
            }
        });
        lv=view.findViewById(R.id.lvListaAvisos);
       final String palabra=getArguments().getString("palabra");
       final  String nombreGrupo=getArguments().getString("nombreGrupo");
       tvAvisosGrupo.setText(nombreGrupo);
       vm.cargarDatos(palabra);
       eliminarAvisosSinVer(palabra);
       return  view;
    }

    private void eliminarAvisosSinVer(String palabra) {
        vm.eliminarAvisosSV(palabra);
    }
}
