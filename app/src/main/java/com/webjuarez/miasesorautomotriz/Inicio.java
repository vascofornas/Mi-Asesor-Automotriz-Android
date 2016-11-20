package com.webjuarez.miasesorautomotriz;

/**
 * Created by Modesto on 11/14/2016.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Inicio extends Activity  {

    private TextView  agenciaId;
    private TextView  asesorId;
    private Button buttonSeleccionar;
    private Button buttonAceptar;
    private TextView textViewResult;
    private String name;
    private String codigo_agencia;



    private ProgressDialog loading;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        agenciaId = (TextView) findViewById(R.id.agenciaId);
        asesorId = (TextView) findViewById(R.id.asesorId);
        buttonAceptar = (Button) findViewById(R.id.buttonAceptar);
        buttonSeleccionar = (Button) findViewById(R.id.buttonSeleccionar);

        addListenerButtonAceptar();
        addListenerButtonSeleccionar();


        SharedPreferences prefs =
                getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        String codigo_agencia = prefs.getString("codigo_agencia", "NO HA SELECCIONADO NINGUNA AGENCIA");
        String nombre_agencia = prefs.getString("nombre_agencia", "NO HA SELECCIONADO NINGUNA AGENCIA");
        String nombre_asesor = prefs.getString("nombre_asesor", "NO HA SELECCIONADO NINGUN ASESOR");
        String id_asesor = prefs.getString("id_asesor", "NO HA SELECCIONADO NINGUN ASESOR");
        String apellidos_asesor = prefs.getString("apellidos_asesor", "NO HA SELECCIONADO NINGUN ASESOR");

        agenciaId.setText(codigo_agencia+" "+nombre_agencia );
        asesorId.setText(nombre_asesor+" "+apellidos_asesor );






    }
    @Override
    protected void onResume() {
        super.onResume();


        agenciaId = (TextView) findViewById(R.id.agenciaId);
        asesorId = (TextView) findViewById(R.id.asesorId);
        buttonAceptar = (Button) findViewById(R.id.buttonAceptar);
        buttonSeleccionar = (Button) findViewById(R.id.buttonSeleccionar);

        addListenerButtonAceptar();
        addListenerButtonSeleccionar();


        SharedPreferences prefs =
                getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        String codigo_agencia = prefs.getString("codigo_agencia", "NO HA SELECCIONADO NINGUNA AGENCIA");
        String nombre_agencia = prefs.getString("nombre_agencia", "NO HA SELECCIONADO NINGUNA AGENCIA");
        String nombre_asesor = prefs.getString("nombre_asesor", "NO HA SELECCIONADO NINGUN ASESOR");
        String id_asesor = prefs.getString("id_asesor", "NO HA SELECCIONADO NINGUN ASESOR");
        String apellidos_asesor = prefs.getString("apellidos_asesor", "NO HA SELECCIONADO NINGUN ASESOR");

        agenciaId.setText(codigo_agencia+" "+nombre_agencia );
        asesorId.setText(nombre_asesor+" "+apellidos_asesor );






    }

    public void addListenerButtonSeleccionar() {

        buttonAceptar = (Button) findViewById(R.id.buttonSeleccionar);

        buttonAceptar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Inicio.this, Agencia.class);
                startActivity(intent);
            }

        });
    }
    public void addListenerButtonAceptar() {

        buttonAceptar = (Button) findViewById(R.id.buttonAceptar);

        buttonAceptar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Inicio.this, WelcomeNoLogin.class);
                startActivity(intent);
                }

        });
    }

}