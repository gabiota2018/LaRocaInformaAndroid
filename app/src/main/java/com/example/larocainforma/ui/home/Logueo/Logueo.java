package com.example.larocainforma.ui.home.Logueo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.larocainforma.MainActivity;
import com.example.larocainforma.R;

public class Logueo extends AppCompatActivity {


    private EditText etUsuario;
    private EditText etPassword;
    private Button btnEnviar;
    private TextView tvMensajeLogueo;
    private LogueoViewModel vm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logueo);
        vm= ViewModelProviders.of(this).get(LogueoViewModel.class);
        iniciarVista();
    }

    private void iniciarVista(){
        etUsuario=findViewById(R.id.etUsuarioLogueo);
        etPassword=findViewById(R.id.etPasswordLogueo);
        tvMensajeLogueo=findViewById(R.id.tvMensajeLogueo);
        btnEnviar=findViewById(R.id.btnEnviarLogueo);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.logueo(etUsuario.getText().toString(),etPassword.getText().toString());
            }
        });
        vm.getTvMensajeLogueo().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tvMensajeLogueo.setVisibility(integer);
            }
        });
        vm.getToken().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

    }
}
