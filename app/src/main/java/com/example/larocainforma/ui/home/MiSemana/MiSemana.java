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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

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
    //private ListView lvLunes,lvMartes,lvMiercoles,lvJueves,lvViernes,lvSabado,lvDomingo;

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

//        lvLunes=view.findViewById(R.id.lvLunes);
//        lvMartes=view.findViewById(R.id.lvMartes);
//        lvMiercoles=view.findViewById(R.id.lvMiercoles);
//        lvJueves=view.findViewById(R.id.lvJueves);
//        lvViernes=view.findViewById(R.id.lvViernes);
//        lvSabado=view.findViewById(R.id.lvSábado);
//        lvDomingo=view.findViewById(R.id.lvDomingo);

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

        lunes.add("Horarios para el Lunes");
        martes.add("Horarios para el Martes");
        miercoles.add("Horarios para el Miércoles");
        jueves.add("Horarios para el Jueves");
        viernes.add("Horarios para el Viernes");
        sabado.add("Horarios para el Sábado");
        domingo.add("Horarios para el Domingo");

        lunes.add("Libre");
        martes.add("Libre");
        miercoles.add("Libre");
        jueves.add("Libre");
        viernes.add("Libre");
        sabado.add("Libre");
        domingo.add("Libre");

        boolean lu,ma,mi,ju,vi,sa,dom;
        lu=true;
        ma=true;
        mi=true;
        ju=true;
        vi=true;
        sa=true;
        dom=true;

        int parte0;
        String mensaje;
        String[] partes;
        for (String aux: utilizas) {
            partes = aux.split("/");
            parte0 = Integer.parseInt(partes[0]);
            mensaje=partes[1];
         switch (parte0){
             case 0: lu=false; lunes.add(mensaje);break;
             case 1: ma=false;martes.add(mensaje);break;
             case 2: mi=false;miercoles.add(mensaje);break;
             case 3: ju=false;jueves.add(mensaje);break;
             case 4: vi=false;viernes.add(mensaje);break;
             case 5: sa=false;sabado.add(mensaje);break;
             case 6: dom=false;domingo.add(mensaje);break;
         }
        }
        if(lu)lunes.remove(0);
        else lunes.remove(1);

        if(ma)martes.remove(0);
        else martes.remove(1);

        if(mi)miercoles.remove(0);
        else miercoles.remove(1);

        if(ju)jueves.remove(0);
        else jueves.remove(1);

        if(vi)viernes.remove(0);
        else viernes.remove(1);

        if(sa)sabado.remove(0);
        else sabado.remove(1);

        if(dom)domingo.remove(0);
        else domingo.remove(1);

        // Toast.makeText(getContext(), "lunes "+lunes.size(), Toast.LENGTH_SHORT).show();
//        ArrayAdapter<String> adaptadorL=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,lunes);
//        lvLunes.setAdapter(adaptadorL);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, lunes);
        spL.setAdapter(adapter);

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
//        ArrayAdapter<String> adaptadorM=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,martes);
//        lvMartes.setAdapter(adaptadorM);
//
//        ArrayAdapter<String> adaptadorMi=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,miercoles);
//        lvMiercoles.setAdapter(adaptadorMi);
//
//        ArrayAdapter<String> adaptadorJ=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,jueves);
//        lvJueves.setAdapter(adaptadorJ);
//
//        ArrayAdapter<String> adaptadorV=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,viernes);
//        lvViernes.setAdapter(adaptadorV);
//
//        ArrayAdapter<String> adaptadorS=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,sabado);
//        lvSabado.setAdapter(adaptadorS);
//
//        ArrayAdapter<String> adaptadorD=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,domingo);
//        lvDomingo.setAdapter(adaptadorD);

    }


}
