package com.example.larocainforma.ui.home.PosiblesActividades;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.larocainforma.R;

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
        lv=view.findViewById(R.id.lvHoy);

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
               
                darAlta();


            }
        });

        return view;
    }
    public  void darAlta(){


        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getContext(),R.style.Theme_MaterialComponents);
        dialogo1.setTitle("Puedes ser un nuevo integrarte al grupo");
        dialogo1.setMessage("Quiero participar del grupo");
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
    public void aceptar(int id) {
       vm.EntrarAlGrupo(id);

    }

    public void cancelar() {
        //finish();
        Toast.makeText(getContext(),"CANCELADO", Toast.LENGTH_SHORT).show();
    }
}
