package com.webjuarez.miasesorautomotriz;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by modestovascofornas on 11/15/15.
 */
public class CostoServicio_NoLogin_3 extends Activity {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    Button continuarButton, cancelarButton;
    String recipient, subject, body,random;
    String nombre, email, cel, tel, fecha, hora, vehiculo, tipo, ano, comentarios, email_envio_cita, email_emisor, kilometros;
    TextView tvNombre, tvEmail, tvCel, tvTel, tvFecha, tvHora, tvVehiculo, tvTipo, tvAno, tvKilometros;
    EditText tvComentarios;
    ImageButton homeButton, llamarButton, emailButton, citaButton,userButton;

    ProgressDialog progress;
    private String name;
    private String codigo_agencia;
    private String nombre_agencia;
    private String id_agencia;
    private String tel_asesor;
    private String email_asesor;
    private String google_play_agencia;
    private TextView nombreAsesor;
    private String nombre_asesor;
    private String apellidos_asesor;

    private static final String REGISTER_URL = "http://miasesorautomotriz.com/administrar/phpfiles/crear_costo.php";

    public static final String KEY_NOMBRE_CLIENTE = "nombre_cliente";
    public static final String KEY_EMAIL_CLIENTE = "email_cliente";
    public static final String KEY_CEL_CLIENTE = "cel_cliente";

    public static final String KEY_MODELO = "modelo";
    public static final String KEY_ANO = "ano";


    public static final String KEY_KILOMETROS = "kilometros";

    public static final String KEY_COMENTARIOS = "comentarios";
    public static final String KEY_CODIGO = "codigo";
    public static final String KEY_AGENCIA = "agencia_costo";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costo_no_login_3);



        SharedPreferences prefs =
                getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        nombre_asesor = prefs.getString("nombre_asesor", "NO HA SELECCIONADO NINGUNA AGENCIA");
         apellidos_asesor = prefs.getString("apellidos_asesor", "NO HA SELECCIONADO NINGUNA AGENCIA");
        tel_asesor = prefs.getString("tel_asesor", "NO HA SELECCIONADO NINGUNA AGENCIA");
        email_asesor = prefs.getString("email_asesor", "NO HA SELECCIONADO NINGUNA AGENCIA");
        google_play_agencia = prefs.getString("google_play_agencia", "NO HA SELECCIONADO NINGUNA AGENCIA");



        addListenerHomeButton();
        addListenerLlamarButton();
        addListenerEmailButton();
        addListenerSMSButton();
        addListenerCompartirButton();
        addListenerCancelerButton();
        addListenerContinuarButton();



        Intent intent = getIntent();
        nombre = intent.getStringExtra("nombre");
        email = intent.getStringExtra("email");
        cel = intent.getStringExtra("celular");
        tel = intent.getStringExtra("tel");

        vehiculo = intent.getStringExtra("vehiculo");
        tipo = intent.getStringExtra("tipo");
        ano = intent.getStringExtra("ano");
        kilometros = intent.getStringExtra("kilometros");

        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvCel = (TextView) findViewById(R.id.tvCelular);


        tvVehiculo = (TextView) findViewById(R.id.tvVehiculo);
        tvKilometros = (TextView) findViewById(R.id.tvKilometros);

        tvAno = (TextView) findViewById(R.id.tvAno);
        tvComentarios = (EditText) findViewById(R.id.tvComentarios);

        tvNombre.setText(nombre);
        tvEmail.setText(email);
        tvCel.setText(cel);


        tvVehiculo.setText(vehiculo);

        tvAno.setText(ano);
        tvKilometros.setText(kilometros);







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

                    Toast.makeText(CostoServicio_NoLogin_3.this, "ERROR - SMS no enviado...",

                            Toast.LENGTH_LONG).show();

                    ex.printStackTrace();

                }

            }







        });

    }

    public void addListenerCompartirButton() {

        userButton = (ImageButton) findViewById(R.id.userButton);

        userButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {





                subject = "Android App Mi Asesor Automotriz";
                body = "Te recomiendo que descargues la Android App Mi Asesor Automotriz. Disponible en :" + google_play_agencia;
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
                    Toast.makeText(CostoServicio_NoLogin_3.this, "No dispone de aplicaciones email.",
                            Toast.LENGTH_LONG).show();
                }


            }







        });

    }

    public void addListenerLlamarButton() {

        llamarButton = (ImageButton) findViewById(R.id.llamarButton);

        llamarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {



                new AlertDialog.Builder(CostoServicio_NoLogin_3.this)
                        .setTitle("Marcar a tu Asesor")
                        .setMessage("Estas seguro de que quieres marcar a "+nombre_asesor+" "+apellidos_asesor+"?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                Log.d("score", "Celular: " + tel_asesor);

                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel_asesor));


                                startActivity(intent);



                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();






            }

        });

    }

    public void addListenerHomeButton() {

        homeButton = (ImageButton) findViewById(R.id.homeButton);

        homeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(CostoServicio_NoLogin_3.this, WelcomeNoLogin.class);

                CostoServicio_NoLogin_3.this.startActivity(myIntent);

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
                    Toast.makeText(CostoServicio_NoLogin_3.this, "No dispone de aplicaciones email.",
                            Toast.LENGTH_LONG).show();
                }


            }



        });

    }


    public void addListenerContinuarButton() {


        continuarButton = (Button) findViewById(R.id.seguir);

        continuarButton.setOnClickListener(new View.OnClickListener() {






            @Override
            public void onClick(View arg0) {


                comentarios = tvComentarios.getText().toString();
                Log.d("CITA A SERVICIO3", "COMENTARIOS: " + comentarios);


                random = randomAlphaNumeric(6);





                StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("CITA A SERVICIO3", "ESTOY AQUI: " +response);

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                  }
                        }){
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String, String>();
                        params.put(KEY_NOMBRE_CLIENTE,nombre);
                        params.put(KEY_EMAIL_CLIENTE,email);
                        params.put(KEY_CEL_CLIENTE,cel);
                        params.put(KEY_MODELO,vehiculo);
                        params.put(KEY_ANO,ano);

                        params.put(KEY_KILOMETROS,kilometros);

                        params.put(KEY_COMENTARIOS,comentarios);
                        params.put(KEY_CODIGO,random);
                        params.put(KEY_AGENCIA,codigo_agencia);




                        return params;
                    }

                };

                RequestQueue requestQueue = Volley.newRequestQueue(CostoServicio_NoLogin_3.this);
                requestQueue.add(stringRequest);


                progress = ProgressDialog.show(CostoServicio_NoLogin_3.this, "Costo de Servicio "+random,
                        "Enviando datos...", true);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //ENVIO DEL EMAIL


                        Mail m = new Mail("appautospv@gmail.com", "cm10052016");

                        String[] toArr = {email_asesor};
                        m.setTo(toArr);
                        m.setFrom(email_asesor);
                        m.setSubject("Cita No. " + random + " SOLICITUD DE COSTO DE SERVICIO DESDE LA ANDROID APP -MI ASESOR AUTOMOTRIZ- ");
                        m.setBody("Cita No. " + random+" SOLICITUD DE COSTO DE SERVICIO  \n\n"
                                + "Número de solicitud de costo: " + random
                                + "\nCliente: " + nombre
                                + "\nEmail: " + email
                                + "\nCelular: " + cel

                                + "\nVehiculo: " + vehiculo
                                + "\nAño: " + ano
                                + "\nKilómetros aprox.: " + kilometros

                                + "\nComentarios del cliente: " + comentarios);

                        try {
                            //m.addAttachment("/sdcard/filelocation");

                            if (m.send()) {


                            } else {
                            }
                        } catch (Exception e) {
                            //Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show();
                            Log.e("PartyPlannerActivity", "Could not send email.", e);
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progress.dismiss();
                                Intent myIntent = new Intent(CostoServicio_NoLogin_3.this, WelcomeNoLogin.class);

                                CostoServicio_NoLogin_3.this.startActivity(myIntent);
                            }
                        });
                    }
                }).start();


                //fin captura datos enviar


            }

        });


    }

    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }




    public void addListenerCancelerButton() {

        cancelarButton = (Button) findViewById(R.id.cancelar);

        cancelarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(CostoServicio_NoLogin_3.this, WelcomeNoLogin.class);


                CostoServicio_NoLogin_3.this.startActivity(myIntent);

            }

        });
    }
}
