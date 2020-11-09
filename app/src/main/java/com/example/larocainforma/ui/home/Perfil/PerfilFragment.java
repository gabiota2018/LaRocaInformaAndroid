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

import com.example.larocainforma.R;


public class PerfilFragment extends Fragment {

        private EditText etApellido,etNombre,etTelefono,etMail,etPassword,etFechaNacimiento,etNombreUsuario,etTipo;
        private Button btnActualizar;
        private PerfilViewModel vm;
        private Usuario miUsuario=null;
    //usuarioId-nombre-apellido-telefono-mail-clave-nombre_usuario-tipo_usuario-fecha_nacimiento-borrado

    public PerfilFragment() { }
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
         }

        @Override
        public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
        Bundle savedInstanceState) {
            vm= ViewModelProviders.of(this).get(PerfilViewModel.class);
            View view=inflater.inflate(R.layout.fragment_perfil, container, false);
            etApellido=view.findViewById(R.id.etApellido);
            etNombre=view.findViewById(R.id.etNombre);
            etTelefono=view.findViewById(R.id.etTelefono);
            etMail=view.findViewById(R.id.etMail);
            etFechaNacimiento=view.findViewById(R.id.etFechaNacimiento);
            etNombreUsuario=view.findViewById(R.id.etNombreUsuario);
            etPassword=view.findViewById(R.id.etPassword);
            etTipo=view.findViewById(R.id.etTipo);
            btnActualizar=view.findViewById(R.id.btnActualizarPerfil);

            etApellido.setEnabled(true);
            etNombre.setEnabled(true);
            etTelefono.setEnabled(true);
            etMail.setEnabled(true);
            etFechaNacimiento.setEnabled(true);
            etNombreUsuario.setEnabled(true);
            etPassword.setEnabled(true);
            etTipo.setEnabled(true);

         vm.getUsuarioMutableLiveData().observe(getViewLifecycleOwner(), new Observer<Usuario>() {
                @Override
                public void onChanged(Usuario usuario) {

                    etApellido.setText("Apellido: "+usuario.getApellido());
                    etNombre.setText("Nombre: "+usuario.getNombre());
                    etTelefono.setText("Telefono: "+usuario.getTelefono());
                    etMail.setText("Mail: "+usuario.getMail());
                    etFechaNacimiento.setText("Fecha de Nacimiento: "+usuario.getFecha_nacimiento());
                    etNombreUsuario.setText("Usuario: "+usuario.getNombre_usuario());
                    etPassword.setText("Password: "+usuario.getClave());
                    etTipo.setText(usuario.getTipo_usuario());

                    miUsuario=usuario;
                    vm.guardarPreferencias(miUsuario.getTipo_usuario());
                }
            });
            btnActualizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activar();
                    actualizar();

                }
            });
            vm.obtenerDatos();
            return view;
        }

        public void activar(){
            if(btnActualizar.getText()=="EDITAR")
            {
               etApellido.setEnabled(true);
                etNombre.setEnabled(true);
                etTelefono.setEnabled(true);
                etMail.setEnabled(true);
                etPassword.setEnabled(true);
                etFechaNacimiento.setEnabled(true);
                etNombreUsuario.setEnabled(true);
                etTipo.setEnabled(false);//no se puede cambiar el tipo
                //Toast.makeText(getContext(), "Tenia EDITAR", Toast.LENGTH_LONG).show();
                btnActualizar.setText("GUARDAR");
            }
            else {
                etApellido.setEnabled(false);
                etNombre.setEnabled(false);
                etTelefono.setEnabled(false);
                etMail.setEnabled(false);
                etPassword.setEnabled(false);
                etFechaNacimiento.setEnabled(false);
                etNombreUsuario.setEnabled(false);
                etTipo.setEnabled(false);
               // Toast.makeText(getContext(), "Tenia GUARDAR", Toast.LENGTH_LONG).show();
                btnActualizar.setText("EDITAR");
            }
        }

        public void actualizar(){
            //usuarioId-nombre-apellido-telefono-mail-clave-nombre_usuario-tipo_usuario-fecha_nacimiento-borrado
            miUsuario.setApellido(etApellido.getText().toString());
            miUsuario.setNombre(etNombre.getText().toString());
            miUsuario.setTelefono(etTelefono.getText().toString());
            miUsuario.setMail(etMail.getText().toString());
            miUsuario.setClave(etPassword.getText().toString());
            miUsuario.setNombre_usuario(etNombreUsuario.getText().toString());
            miUsuario.setFecha_nacimiento(etFechaNacimiento.getText().toString());
            miUsuario.setTipo_usuario(etTipo.getText().toString());
            vm.actualizar(miUsuario);
        }
}
