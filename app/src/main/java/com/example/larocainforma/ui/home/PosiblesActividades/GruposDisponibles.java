package com.example.larocainforma.ui.home.PosiblesActividades;

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
import android.widget.ListView;
import android.widget.TextView;

import com.example.larocainforma.R;
import com.example.larocainforma.ui.home.MisGruposActivos.DetalleGrupoActividadVM;

import java.util.List;


public class GruposDisponibles extends Fragment {

    private ListView lv;
    private GruposDisponiblesVM vm;


     public GruposDisponibles() {  }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vm= ViewModelProviders.of(this).get(GruposDisponiblesVM.class);
        View view= inflater.inflate(R.layout.fragment_grupos_disponibles, container, false);
        lv=view.findViewById(R.id.lvListaDisponibles);

        vm.getListaDeGrupo().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,strings);
                lv.setAdapter(adapter);
            }
        });

        final String palabra=getArguments().getString("palabra");
        vm.cargarDatos(palabra);


        lv.setClickable(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //dialogos
            }
        });

        return view;
    }
}
