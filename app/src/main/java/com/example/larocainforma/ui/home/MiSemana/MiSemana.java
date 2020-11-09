package com.example.larocainforma.ui.home.MiSemana;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.larocainforma.R;
import com.example.larocainforma.ui.home.Clases.Utiliza;
import com.example.larocainforma.ui.home.Perfil.PerfilViewModel;
import com.example.larocainforma.ui.home.PosiblesActividades.PuedoHacerVM;

import java.util.ArrayList;
import java.util.List;


public class MiSemana extends Fragment {

    private MiSemanaVM vm;
    private Spinner spL,spMa,spMi,spJ,spV,spS,spD;
    private List<String> miLista;
    public MiSemana() {  }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vm= ViewModelProviders.of(this).get(MiSemanaVM.class);
        View view= inflater.inflate(R.layout.fragment_mi_semana, container, false);

        spL=view.findViewById(R.id.spL);
        spMa=view.findViewById(R.id.spMa);
        spMi=view.findViewById(R.id.spMi);
        spJ=view.findViewById(R.id.spJ);
        spV=view.findViewById(R.id.spV);
        spS=view.findViewById(R.id.spS);
        spD=view.findViewById(R.id.spD);

        vm.getUtilizaMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                cargarSpinners(strings);
                miLista=strings;
            }
        });
        vm.cargarDatos();
        return  view;
    }

    private void cargarSpinners(List<String> utilizas) {
        ArrayList<String> lunes=new ArrayList<>();
        ArrayList<String> martes=new ArrayList<>();
        ArrayList<String> miercoles=new ArrayList<>();
        ArrayList<String> jueves=new ArrayList<>();
        ArrayList<String> viernes=new ArrayList<>();
        ArrayList<String> sabado=new ArrayList<>();
        ArrayList<String> domingo=new ArrayList<>();
        int parte1;
        String mensaje;
        String[] partes;
        for (String aux: utilizas) {
            partes = aux.split("/");
            parte1 = Integer.parseInt(partes[0]);
            mensaje=partes[1];
         switch (parte1){
             case 0: lunes.add(mensaje);break;
             case 1: martes.add(mensaje);break;
             case 2: miercoles.add(mensaje);break;
             case 3: jueves.add(mensaje);break;
             case 4: viernes.add(mensaje);break;
             case 5: sabado.add(mensaje);break;
             case 6: domingo.add(mensaje);break;
         }
        }
       ArrayAdapter<String> adapterL=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,lunes);
        spL.setAdapter(adapterL);

        ArrayAdapter<String> adapterM=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,martes);
        spMa.setAdapter(adapterM);

        ArrayAdapter<String> adapterMi=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,miercoles);
        spMi.setAdapter(adapterMi);

        ArrayAdapter<String> adapterJ=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,jueves);
        spJ.setAdapter(adapterJ);

        ArrayAdapter<String> adapterV=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,viernes);
        spV.setAdapter(adapterV);

        ArrayAdapter<String> adapterS=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,sabado);
        spS.setAdapter(adapterS);

        ArrayAdapter<String> adapterD=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,domingo);
        spD.setAdapter(adapterD);
    }


}
