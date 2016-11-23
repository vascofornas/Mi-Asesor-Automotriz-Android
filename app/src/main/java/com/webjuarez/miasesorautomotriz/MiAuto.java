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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MiAuto extends Activity  {


    private String name;
    private String codigo_agencia;
    private String nombre_agencia;
    private String id_agencia;
    private String tel_asesor;
    private String email_asesor;
    private String google_play_agencia;


    private TextView nombreAsesor;

    private TextView modelo;
    private TextView ano;
    private TextView placas;
    private TextView serie;
    private TextView poliza;


    String recipient, subject, body;

    private ProgressDialog loading;
    ImageButton homeButton, llamarButton, emailButton, citaButton,userButton;
    Button aceptarButton;
    Button cambiarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miauto);

        SharedPreferences prefs =
                getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        String nombre_asesor = prefs.getString("nombre_asesor", "NO HA SELECCIONADO NINGUNA AGENCIA");
        String apellidos_asesor = prefs.getString("apellidos_asesor", "NO HA SELECCIONADO NINGUNA AGENCIA");
        tel_asesor = prefs.getString("tel_asesor", "NO HA SELECCIONADO NINGUNA AGENCIA");
        email_asesor = prefs.getString("email_asesor", "NO HA SELECCIONADO NINGUNA AGENCIA");
        google_play_agencia = prefs.getString("google_play_agencia", "NO HA SELECCIONADO NINGUNA AGENCIA");
        nombreAsesor = (TextView) findViewById(R.id.nombreAsesor);
        nombreAsesor.setText("Asesor: "+nombre_asesor+' '+apellidos_asesor);

        String modelo_mi_auto = prefs.getString("modelo_mi_auto", "");
        String ano_mi_auto = prefs.getString("ano_mi_auto", "");
        String placas_mi_auto = prefs.getString("placas_mi_auto", "");
        String serie_mi_auto = prefs.getString("serie_mi_auto", "");
        String poliza_mi_auto = prefs.getString("poliza_mi_auto", "");

        modelo = (TextView) findViewById(R.id.txt_mi_modelo);
        ano = (TextView) findViewById(R.id.txt_mi_ano);
        placas = (TextView) findViewById(R.id.txt_mi_mis_placas);
        serie = (TextView) findViewById(R.id.txt_mi_num_serie);
        poliza = (TextView) findViewById(R.id.txt_mi_num_poliza);


        modelo.setText(modelo_mi_auto);
        ano.setText(ano_mi_auto);
        placas.setText(placas_mi_auto);
        serie.setText(serie_mi_auto);
        poliza.setText(poliza_mi_auto);

        addListenerHomeButton();
        addListenerLlamarButton();
        addListenerEmailButton();
        addListenerSMSButton();
        addListenerCompartirButton();
        addListenerAceptarButton();
        addListenerEditarButton();

    }


    public void onResume(){
       super.onResume();

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
        String placas_mi_auto = prefs.getString("placas_mi_auto", "PLACAS");
        String serie_mi_auto = prefs.getString("serie_mi_auto", "NUM. DE SERIE");
        String poliza_mi_auto = prefs.getString("poliza_mi_auto", "NUM. DE POLIZA");

        modelo = (TextView) findViewById(R.id.txt_mi_modelo);
        ano = (TextView) findViewById(R.id.txt_mi_ano);
        placas = (TextView) findViewById(R.id.txt_mi_mis_placas);
        serie = (TextView) findViewById(R.id.txt_mi_num_serie);
        poliza = (TextView) findViewById(R.id.txt_mi_num_poliza);


        modelo.setText(modelo_mi_auto);
        ano.setText(ano_mi_auto);
        placas.setText(placas_mi_auto);
        serie.setText(serie_mi_auto);
        poliza.setText(poliza_mi_auto);
    }
    public void addListenerAceptarButton() {

        aceptarButton = (Button) findViewById(R.id.buttonaceptar);

        aceptarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(MiAuto.this, WelcomeNoLogin.class);

                MiAuto.this.startActivity(myIntent);

            }

        });

    }
    public void addListenerEditarButton() {

        cambiarButton = (Button) findViewById(R.id.buttoncambiar);

        cambiarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(MiAuto.this, MiAutoEditar.class);

                MiAuto.this.startActivity(myIntent);

            }

        });

    }
    public void addListenerHomeButton() {

        homeButton = (ImageButton) findViewById(R.id.homeButton);

        homeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(MiAuto.this, WelcomeNoLogin.class);

                MiAuto.this.startActivity(myIntent);

            }

        });

    }

    public void addListenerLlamarButton() {

        llamarButton = (ImageButton) findViewById(R.id.llamarButton);

        llamarButton.setOnClickListener(new View.OnClickListener() {

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

        emailButton.setOnClickListener(new View.OnClickListener() {

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
                                    Toast.makeText(MiAuto.this, "No dispone de aplicaciones email.",
                                            Toast.LENGTH_LONG).show();
                                }


                            }



        });

    }

    public void addListenerSMSButton() {

        citaButton = (ImageButton) findViewById(R.id.citaButton);

        citaButton.setOnClickListener(new View.OnClickListener() {

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

                                    Toast.makeText(MiAuto.this, "ERROR - SMS no enviado...",

                                            Toast.LENGTH_LONG).show();

                                    ex.printStackTrace();

                                }

                            }







        });

    }

    public void addListenerCompartirButton() {

        userButton = (ImageButton) findViewById(R.id.citaButton);

        userButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {





                                subject = "Android App Mi Asesor Automotriz";
                                body = "Te recomiendo que descargues la Android App  Mi Asesor Automotriz. Disponible en :" + google_play_agencia;
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
                                    Toast.makeText(MiAuto.this, "No dispone de aplicaciones email.",
                                            Toast.LENGTH_LONG).show();
                                }


                            }







        });

    }




}