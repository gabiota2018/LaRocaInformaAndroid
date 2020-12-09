package com.example.larocainforma.ui.home.Perfil;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.larocainforma.R;


public class PerfilFragment extends Fragment {

        private EditText etApellido,etNombre,etTelefono,etMail,etFechaNacimiento,etNombreUsuario,etTipo;
        private Button btnGuardar;
        private PerfilViewModel vm;
        private Usuario miUsuario=null;
    //usuarioId-nombre-apellido-telefono-mail-clave-nombre_usuario-tipo_usuario-fecha_nacimiento-borrado

    public PerfilFragment() { }
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
         }
//--------------------------------------------------------------------------------------------------
        @Override
        public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
        Bundle savedInstanceState) {
            vm= ViewModelProviders.of(this).get(PerfilViewModel.class);
            View view=inflater.inflate(R.layout.fragment_perfil, container, false);
            iniciarVista(view);
//--------------------------------------------------------------------------------------------------
         vm.getUsuarioMutableLiveData().observe(getViewLifecycleOwner(), new Observer<Usuario>() {
                @Override
                public void onChanged(Usuario usuario) {
                    etApellido.setText("Apellido: "+usuario.getApellido());
                    etNombre.setText("Nombre: "+usuario.getNombre());
                    etTelefono.setText("Telefono: "+usuario.getTelefono());
                    etMail.setText("Mail: "+usuario.getMail());
                    etFechaNacimiento.setText("Fecha de Nacimiento: "+usuario.getFecha_nacimiento());
                    etNombreUsuario.setText("Usuario: "+usuario.getNombre_usuario());
                    etTipo.setText(usuario.getTipo_usuario());
                    miUsuario=usuario;
                    vm.guardarPreferencias(miUsuario.getTipo_usuario());
                }
            });
//--------------------------------------------------------------------------------------------------
            btnGuardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                actualizar();
                }
            });

//--------------------------------------------------------------------------------------------------
            vm.obtenerDatos();
            return view;
        }
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    private void iniciarVista(View view) {
        etApellido=view.findViewById(R.id.etApellido);
        etNombre=view.findViewById(R.id.etNombre);
        etTelefono=view.findViewById(R.id.etTelefono);
        etMail=view.findViewById(R.id.etMail);
        etFechaNacimiento=view.findViewById(R.id.etFechaNacimiento);
        etNombreUsuario=view.findViewById(R.id.etNombreUsuario);
        etTipo=view.findViewById(R.id.etTipo);
        btnGuardar=view.findViewById(R.id.btnGuardarPerfil);
     }
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
        public void actualizar() {
            if (etApellido.getText().toString().equals("") || etNombre.getText().toString().equals("") || etTelefono.getText().toString().equals("") ||
                    etMail.getText().toString().equals("") || etFechaNacimiento.getText().toString().equals(""))
                Toast.makeText(getContext(), "No puede haber campos vacios ", Toast.LENGTH_LONG).show();
            else {
                //usuarioId-nombre-apellido-telefono-mail-clave-nombre_usuario-tipo_usuario-fecha_nacimiento-borrado
                miUsuario.setApellido(etApellido.getText().toString());
                miUsuario.setNombre(etNombre.getText().toString());
                miUsuario.setTelefono(etTelefono.getText().toString());
                miUsuario.setMail(etMail.getText().toString());
                miUsuario.setFecha_nacimiento(etFechaNacimiento.getText().toString());
                vm.actualizar(miUsuario);
            }
        }
}
