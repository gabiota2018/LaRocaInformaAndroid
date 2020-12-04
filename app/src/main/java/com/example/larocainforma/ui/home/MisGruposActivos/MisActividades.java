package com.example.larocainforma.ui.home.MisGruposActivos;

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
import android.widget.ListView;
import android.widget.TextView;

import com.example.larocainforma.R;

import java.util.List;


public class MisActividades extends Fragment {
    private ListView lv;
    private MisActividadesVM vm;

    public MisActividades() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm= ViewModelProviders.of(this).get(MisActividadesVM.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mis_actividades, container, false);
        vm.getListaDeGrupo().observe(getViewLifecycleOwner(),new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,strings);
                lv.setAdapter(adapter);
            }
        });
       lv=view.findViewById(R.id.lvListaActividades);
       vm.cargarDatos();
       lv.setClickable(true);
       lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               TextView tv=(TextView)view;
               Bundle bundle=new Bundle();
               bundle.putString("palabra",tv.getText().toString());
               String palabra=tv.getText().toString();
                // Toast.makeText(getContext(),palabra,Toast.LENGTH_LONG).show();
               Navigation.findNavController(view).navigate(R.id.detalleActividad, bundle);

           }
        });
      return  view;
    }

}
