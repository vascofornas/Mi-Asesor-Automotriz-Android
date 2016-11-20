package com.webjuarez.miasesorautomotriz;

/**
 * Created by Modesto on 11/14/2016.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MiAutoEditar extends Activity  {


    private String name;
    private String codigo_agencia;
    private String nombre_agencia;
    private String id_agencia;
    private String tel_asesor;
    private String email_asesor;
    private String google_play_agencia;


   private EditText txtmodelo;

    private EditText txtano;


    private TextView nombreAsesor;

    private TextView modelo;
    private TextView ano;
    private TextView placas;
    private TextView serie;
    private TextView poliza;

    String recipient, subject, body;

    private ProgressDialog loading;
    ImageButton homeButton, llamarButton, emailButton, citaButton,userButton;
    Button aceptarButton,cancelarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miautoeditar);

        SharedPreferences prefs =
                getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        String nombre_asesor = prefs.getString("nombre_asesor", "NO HA SELECCIONADO NINGUNA AGENCIA");
        String apellidos_asesor = prefs.getString("apellidos_asesor", "NO HA SELECCIONADO NINGUNA AGENCIA");
        tel_asesor = prefs.getString("tel_asesor", "NO HA SELECCIONADO NINGUNA AGENCIA");
        email_asesor = prefs.getString("email_asesor", "NO HA SELECCIONADO NINGUNA AGENCIA");
        google_play_agencia = prefs.getString("google_play_agencia", "NO HA SELECCIONADO NINGUNA AGENCIA");
        nombreAsesor = (TextView) findViewById(R.id.nombreAsesor);
        nombreAsesor.setText("Asesor: "+nombre_asesor+' '+apellidos_asesor);


        String modelo_mi_auto = prefs.getString("modelo_mi_auto", "MODELO");
        String ano_mi_auto = prefs.getString("ano_mi_auto", "AÑO");


        txtmodelo = (EditText) findViewById(R.id.txt_mi_modelo);
        txtano = (EditText) findViewById(R.id.txt_mi_ano);

        txtmodelo.setText(modelo_mi_auto);
        txtano.setText(ano_mi_auto);



        addListenerHomeButton();
        addListenerLlamarButton();
        addListenerEmailButton();
        addListenerSMSButton();
        addListenerCompartirButton();
        addListenerAceptarButton();
        addListenerCancelarButton();

    }

    public void addListenerAceptarButton() {

        aceptarButton = (Button) findViewById(R.id.buttonaceptar);

        aceptarButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                SharedPreferences prefs =
                        getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);


                String mimodelo =  txtmodelo.getText().toString();

                String miano =  txtano.getText().toString();

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("modelo_mi_auto", mimodelo);
                editor.putString("ano_mi_auto", miano);



                editor.commit();



                Intent myIntent = new Intent(MiAutoEditar.this, MiAutoEditarSegunda.class);

                MiAutoEditar.this.startActivity(myIntent);

            }

        });

    }
    public void addListenerCancelarButton() {

        cancelarButton = (Button) findViewById(R.id.buttoncambiar);

        cancelarButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(MiAutoEditar.this, MiAuto.class);

                MiAutoEditar.this.startActivity(myIntent);

            }

        });

    }

    public void addListenerHomeButton() {

        homeButton = (ImageButton) findViewById(R.id.homeButton);

        homeButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(MiAutoEditar.this, WelcomeNoLogin.class);

                MiAutoEditar.this.startActivity(myIntent);

            }

        });

    }

    public void addListenerLlamarButton() {

        llamarButton = (ImageButton) findViewById(R.id.llamarButton);

        llamarButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Log.d("score", "Celular: " + tel_asesor);

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel_asesor));


                startActivity(intent);


            }

        });

    }

    public void addListenerEmailButton() {

        emailButton = (ImageButton) findViewById(R.id.emailButton);

        emailButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {



                                Log.d("EMAIL FINAL", "EMAIL: " + email_asesor);


                                Intent enviar = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
                                // prompts email clients only
                                enviar.setType("message/rfc822");

                                enviar.putExtra(Intent.EXTRA_EMAIL, new String[]{email_asesor});
                                enviar.putExtra(Intent.EXTRA_SUBJECT, "Enviado desde la Android App Mi Asesor Automotriz");
                                enviar.putExtra(Intent.EXTRA_TEXT, " ");

                                try {
                                    // the user can choose the email client
                                    startActivity(Intent.createChooser(enviar, "Seleccione una aplicación para enviar el email..."));

                                } catch (android.content.ActivityNotFoundException ex) {
                                    Toast.makeText(MiAutoEditar.this, "No dispone de aplicaciones email.",
                                            Toast.LENGTH_LONG).show();
                                }


                            }



        });

    }

    public void addListenerSMSButton() {

        citaButton = (ImageButton) findViewById(R.id.citaButton);

        citaButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {



                                Log.d("CELULAR", "CELULAR: " + tel_asesor);

// Initialize SmsManager Object// add the phone number in the data

                                Uri uri = Uri.parse("smsto:" + tel_asesor);


                                Intent smsSIntent = new Intent(Intent.ACTION_SENDTO, uri);

                                // add the message at the sms_body extra field

                                smsSIntent.putExtra("sms_body", " ");

                                try {

                                    startActivity(smsSIntent);

                                } catch (Exception ex) {

                                    Toast.makeText(MiAutoEditar.this, "ERROR - SMS no enviado...",

                                            Toast.LENGTH_LONG).show();

                                    ex.printStackTrace();

                                }

                            }







        });

    }

    public void addListenerCompartirButton() {

        userButton = (ImageButton) findViewById(R.id.userButton);

        userButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {





                                subject = "Android App de PEDRO VILLAREJO";
                                body = "Te recomiendo que descargues la Android App de PEDRO VILLAREJO. Disponible en :" + google_play_agencia;
                                recipient = google_play_agencia;
                                Intent enviar = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
                                // prompts email clients only
                                enviar.setType("message/rfc822");

                                //enviar.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                                enviar.putExtra(Intent.EXTRA_SUBJECT, "Android App Mi Asesor Automotriz");
                                enviar.putExtra(Intent.EXTRA_TEXT, "Te recomiendo que descargues la Android App Mi Asesor Automotriz. Disponible en: " + google_play_agencia);

                                try {
                                    // the user can choose the email client
                                    startActivity(Intent.createChooser(enviar, "Seleccione una aplicación para enviar el email..."));

                                } catch (android.content.ActivityNotFoundException ex) {
                                    Toast.makeText(MiAutoEditar.this, "No dispone de aplicaciones email.",
                                            Toast.LENGTH_LONG).show();
                                }


                            }







        });

    }




}