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

public class MiAutoEditarSegunda extends Activity  {


    private String name;
    private String codigo_agencia;
    private String nombre_agencia;
    private String id_agencia;
    private String tel_asesor;
    private String email_asesor;
    private String google_play_agencia;


   private EditText txtmisplacas;
    private EditText txtmipoliza;
    private EditText txtmiserie;




    private TextView nombreAsesor;



    String recipient, subject, body;

    private ProgressDialog loading;
    ImageButton homeButton, llamarButton, emailButton, citaButton,userButton;
    Button aceptarButton,cancelarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miautoeditar_segunda);

        SharedPreferences prefs =
                getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        String nombre_asesor = prefs.getString("nombre_asesor", "NO HA SELECCIONADO NINGUNA AGENCIA");
        String apellidos_asesor = prefs.getString("apellidos_asesor", "NO HA SELECCIONADO NINGUNA AGENCIA");
        tel_asesor = prefs.getString("tel_asesor", "NO HA SELECCIONADO NINGUNA AGENCIA");
        email_asesor = prefs.getString("email_asesor", "NO HA SELECCIONADO NINGUNA AGENCIA");
        google_play_agencia = prefs.getString("google_play_agencia", "NO HA SELECCIONADO NINGUNA AGENCIA");
        nombreAsesor = (TextView) findViewById(R.id.nombreAsesor);
        nombreAsesor.setText("Asesor: "+nombre_asesor+' '+apellidos_asesor);


        String placas_mi_auto = prefs.getString("placas_mi_auto", "PLACAS");
        txtmisplacas = (EditText) findViewById(R.id.txt_mis_placas);
        txtmisplacas.setText(placas_mi_auto);
        String poliza_mi_auto = prefs.getString("poliza_mi_auto", "POLIZA");
        txtmipoliza = (EditText) findViewById(R.id.txt_mi_poliza);
        txtmipoliza.setText(poliza_mi_auto);
        String serie_mi_auto = prefs.getString("serie_mi_auto", "SERIE");
        txtmiserie= (EditText) findViewById(R.id.txt_mi_serie);
        txtmiserie.setText(serie_mi_auto);


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


                String misplacas =  txtmisplacas.getText().toString();
                String mipoliza =  txtmipoliza.getText().toString();
                String miserie =  txtmiserie.getText().toString();

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("placas_mi_auto", misplacas);
                editor.putString("poliza_mi_auto", mipoliza);
                editor.putString("serie_mi_auto", miserie);




                editor.commit();



                Intent myIntent = new Intent(MiAutoEditarSegunda.this, MiAuto.class);

                MiAutoEditarSegunda.this.startActivity(myIntent);

            }

        });

    }
    public void addListenerCancelarButton() {

        cancelarButton = (Button) findViewById(R.id.buttoncambiar);

        cancelarButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(MiAutoEditarSegunda.this, MiAuto.class);

                MiAutoEditarSegunda.this.startActivity(myIntent);

            }

        });

    }

    public void addListenerHomeButton() {

        homeButton = (ImageButton) findViewById(R.id.homeButton);

        homeButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(MiAutoEditarSegunda.this, WelcomeNoLogin.class);

                MiAutoEditarSegunda.this.startActivity(myIntent);

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
                                    Toast.makeText(MiAutoEditarSegunda.this, "No dispone de aplicaciones email.",
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

                                    Toast.makeText(MiAutoEditarSegunda.this, "ERROR - SMS no enviado...",

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
                                    Toast.makeText(MiAutoEditarSegunda.this, "No dispone de aplicaciones email.",
                                            Toast.LENGTH_LONG).show();
                                }


                            }







        });

    }




}