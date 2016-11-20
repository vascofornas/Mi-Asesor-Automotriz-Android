package com.webjuarez.miasesorautomotriz;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import static android.widget.Toast.makeText;


/**
 * Created by modestovascofornas on 11/15/15.
 */
public class CitaServicio_NoLogin_4 extends Activity {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    Button continuarButton, cancelarButton, fotoButton;
    String recipient, subject, body,random;
    String nombre, email, cel, tel, fecha, hora, vehiculo, tipo, ano, comentarios, email_envio_cita, email_emisor, kilometros;
    TextView tvNombre, tvEmail, tvCel, tvTel, tvFecha, tvHora, tvVehiculo, tvTipo, tvAno, tvKilometros;
    EditText tvComentarios;
    ImageButton homeButton, llamarButton, emailButton, citaButton,userButton;

    ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cita_no_login_4);


        addListenerHomeButton();
        addListenerLlamarButton();
        addListenerEmailButton();
        addListenerCitaButton();
        addListenerUserButton();


        //recuperar email al que enviar cita
        ParseQuery<ParseObject> query12 = ParseQuery.getQuery("datos_contacto");
        query12.whereEqualTo("tipo_contacto", "email_envio_cita");
        query12.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    int len = scoreList.size();
                    for (int i = 0; i < len; i++) {
                        ParseObject p = scoreList.get(i);
                        email_emisor = p.getString("dato_contacto");


                    }
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

        Intent intent = getIntent();
        nombre = intent.getStringExtra("nombre");
        email = intent.getStringExtra("email");
        cel = intent.getStringExtra("celular");
        tel = intent.getStringExtra("tel");
        fecha = intent.getStringExtra("fecha");
        hora = intent.getStringExtra("hora");
        vehiculo = intent.getStringExtra("vehiculo");
        tipo = intent.getStringExtra("tipo");
        ano = intent.getStringExtra("ano");
        kilometros = intent.getStringExtra("kilometros");

        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvCel = (TextView) findViewById(R.id.tvCelular);
        tvTel = (TextView) findViewById(R.id.tvTel);
        tvFecha = (TextView) findViewById(R.id.tvFecha);
        tvHora = (TextView) findViewById(R.id.tvHora);
        tvVehiculo = (TextView) findViewById(R.id.tvVehiculo);
        tvKilometros = (TextView) findViewById(R.id.tvKilometros);
        tvTipo = (TextView) findViewById(R.id.tvTipo);
        tvAno = (TextView) findViewById(R.id.tvAno);
        tvComentarios = (EditText) findViewById(R.id.tvComentarios);

        tvNombre.setText(nombre);
        tvEmail.setText(email);
        tvCel.setText(cel);
        tvTel.setText(tel);
        tvFecha.setText(fecha);
        tvHora.setText(hora);
        tvVehiculo.setText(vehiculo);
        tvTipo.setText(tipo);
        tvAno.setText(ano);
        tvKilometros.setText(kilometros);


        addListenerContinuarButton();
        addListenerCancelerButton();
        addListenerFotoButton();

        //recuperar email al que enviar cita
        ParseQuery<ParseObject> query = ParseQuery.getQuery("datos_contacto");
        query.whereEqualTo("tipo_contacto", "email_envio_cita");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    int len = scoreList.size();
                    for (int i = 0; i < len; i++) {
                        ParseObject p = scoreList.get(i);
                        email_envio_cita = p.getString("dato_contacto");


                    }
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });


    }
    public void addListenerCitaButton() {

        citaButton = (ImageButton) findViewById(R.id.citaButton);

        citaButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                ParseQuery<ParseObject> query = ParseQuery.getQuery("datos_contacto");
                query.whereEqualTo("tipo_contacto", "celular_sms");
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> scoreList, ParseException e) {
                        if (e == null) {
                            int len = scoreList.size();
                            for (int i = 0; i < len; i++) {
                                ParseObject p = scoreList.get(i);
                                String celular_sms = p.getString("dato_contacto");


                                Log.d("CELULAR", "CELULAR: " + celular_sms);

// Initialize SmsManager Object// add the phone number in the data

                                Uri uri = Uri.parse("smsto:" + celular_sms);


                                Intent smsSIntent = new Intent(Intent.ACTION_SENDTO, uri);

                                // add the message at the sms_body extra field

                                smsSIntent.putExtra("sms_body", " ");

                                try {

                                    startActivity(smsSIntent);

                                } catch (Exception ex) {

                                    Toast.makeText(CitaServicio_NoLogin_4.this, "ERROR - SMS no enviado...",

                                            Toast.LENGTH_LONG).show();

                                    ex.printStackTrace();

                                }

                            }
                        } else {
                            Log.d("score", "Error: " + e.getMessage());
                        }
                    }
                });



            }

        });

    }


    public void addListenerUserButton() {

        userButton = (ImageButton) findViewById(R.id.userButton);

        userButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                ParseQuery<ParseObject> query = ParseQuery.getQuery("datos_contacto");
                query.whereEqualTo("tipo_contacto", "URL_compartir_GooglePlay");
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> scoreList, ParseException e) {
                        if (e == null) {
                            int len = scoreList.size();
                            for (int i = 0; i < len; i++) {
                                ParseObject p = scoreList.get(i);
                                String email = p.getString("dato_contacto");


                                Log.d("EMAIL FINAL", "EMAIL: " + email);

                                subject = "Android App de PEDRO VILLAREJO";
                                body = "Te recomiendo que descargues la Android App de PEDRO VILLAREJO. Disponible en :" + email;
                                recipient = email;
                                Intent enviar = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
                                // prompts email clients only
                                enviar.setType("message/rfc822");

                                //enviar.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                                enviar.putExtra(Intent.EXTRA_SUBJECT, "Android App de PEDRO VILLAREJO");
                                enviar.putExtra(Intent.EXTRA_TEXT, "Te recomiendo que descargues la Android App de PEDRO VILLAREJO. Disponible en :" + email);

                                try {
                                    // the user can choose the email client
                                    startActivity(Intent.createChooser(enviar, "Seleccione una aplicación para enviar el email..."));

                                } catch (android.content.ActivityNotFoundException ex) {
                                    Toast.makeText(CitaServicio_NoLogin_4.this, "No dispone de aplicaciones email.",
                                            Toast.LENGTH_LONG).show();
                                }


                            }
                        } else {
                            Log.d("score", "Error: " + e.getMessage());
                        }
                    }
                });


            }

        });

    }

    public void addListenerHomeButton() {

        homeButton = (ImageButton) findViewById(R.id.homeButton);

        homeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(CitaServicio_NoLogin_4.this, WelcomeNoLogin.class);

                CitaServicio_NoLogin_4.this.startActivity(myIntent);

            }

        });

    }

    public void addListenerLlamarButton() {

        llamarButton = (ImageButton) findViewById(R.id.llamarButton);

        llamarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                ParseQuery<ParseObject> query = ParseQuery.getQuery("datos_contacto");
                query.whereEqualTo("tipo_contacto", "celular");
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> scoreList, ParseException e) {
                        if (e == null) {
                            int len = scoreList.size();
                            for (int i = 0; i < len; i++) {
                                ParseObject p = scoreList.get(i);
                                String numero = p.getString("dato_contacto");

                                Log.d("score", "Celular: " + numero);

                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numero));


                                startActivity(intent);

                            }
                        } else {
                            Log.d("score", "Error: " + e.getMessage());
                        }
                    }
                });


            }

        });

    }


    public void addListenerEmailButton() {

        emailButton = (ImageButton) findViewById(R.id.emailButton);

        emailButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                ParseQuery<ParseObject> query = ParseQuery.getQuery("datos_contacto");
                query.whereEqualTo("tipo_contacto", "email_contacto");
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> scoreList, ParseException e) {
                        if (e == null) {
                            int len = scoreList.size();
                            for (int i = 0; i < len; i++) {
                                ParseObject p = scoreList.get(i);
                                String email = p.getString("dato_contacto");


                                Log.d("EMAIL FINAL", "EMAIL: " + email);


                                Intent enviar = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
                                // prompts email clients only
                                enviar.setType("message/rfc822");

                                enviar.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                                enviar.putExtra(Intent.EXTRA_SUBJECT, "Enviado desde la Android App PEDRO VILLAREJO");
                                enviar.putExtra(Intent.EXTRA_TEXT, " ");


                                try {
                                    // the user can choose the email client
                                    startActivity(Intent.createChooser(enviar, "Seleccione una aplicación para enviar el email..."));

                                } catch (android.content.ActivityNotFoundException ex) {
                                    Toast.makeText(CitaServicio_NoLogin_4.this, "No dispone de aplicaciones email.",
                                            Toast.LENGTH_LONG).show();
                                }


                            }
                        } else {
                            Log.d("score", "Error: " + e.getMessage());
                        }
                    }
                });

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




                ParseObject cita_servicio = new ParseObject("citas_servicio");
                cita_servicio.put("Nombre_cliente", nombre);
                cita_servicio.put("email_cliente", email);
                cita_servicio.put("celular_cliente", cel);
                cita_servicio.put("tel_cliente", tel);
                cita_servicio.put("vehiculo_cliente", vehiculo);
                cita_servicio.put("ano_vehiculo", ano);
                cita_servicio.put("tipo_cita", tipo);
                cita_servicio.put("fecha_cita", fecha);
                cita_servicio.put("hora_cita", hora);
                cita_servicio.put("comentarios", comentarios);
                cita_servicio.put("kilometros", kilometros);
                cita_servicio.saveInBackground();


                progress = ProgressDialog.show(CitaServicio_NoLogin_4.this, "Cita a Servicio "+random,
                        "Enviando datos...", true);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //ENVIO DEL EMAIL


                        Mail m = new Mail("appautospv@gmail.com", "cm10052016");

                        String[] toArr = {email_envio_cita};
                        m.setTo(toArr);
                        m.setFrom(email_emisor);
                        m.setSubject("Cita No. " + random + " SOLICITUD DE CITA A SERVICIO DESDE LA ANDROID APP -PEDRO VILLAREJO- SIN FOTOS");
                        m.setBody("Cita No. " + random+" SOLICITUD DE CITA A SERVICIO, SIN FOTOS \n\n"
                                + "Número de solicitud de cita: " + random
                                + "\nCliente: " + nombre
                                + "\nEmail: " + email
                                + "\nCelular: " + cel
                                + "\nTeléfono: " + tel
                                + "\nVehiculo: " + vehiculo
                                + "\nAño: " + ano
                                + "\nKilómetros aprox.: " + kilometros
                                + "\nTipo de servicio: " + tipo
                                + "\nFecha solicitada: " + fecha
                                + "\nHora solicitada: " + hora
                                + "\nComentarios del cliente: " + comentarios);

                        try {
                            //m.addAttachment("/sdcard/filelocation");

                            if (m.send()) {

                                Toast.makeText(CitaServicio_NoLogin_4.this, "Sent Email.", Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(CitaServicio_NoLogin_4.this, "Email was not sent.", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            //Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show();
                            Log.e("PartyPlannerActivity", "Could not send email.", e);
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progress.dismiss();
                                Intent myIntent = new Intent(CitaServicio_NoLogin_4.this, WelcomeNoLogin.class);

                                CitaServicio_NoLogin_4.this.startActivity(myIntent);
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

    public void addListenerFotoButton() {


        fotoButton = (Button) findViewById(R.id.fotoButton);

        fotoButton.setOnClickListener(new View.OnClickListener() {






            @Override
            public void onClick(View arg0) {


                comentarios = tvComentarios.getText().toString();
                Log.d("CITA A SERVICIO3", "COMENTARIOS: " + comentarios);


                random = randomAlphaNumeric(6);




                ParseObject cita_servicio = new ParseObject("citas_servicio");
                cita_servicio.put("Nombre_cliente", nombre);
                cita_servicio.put("email_cliente", email);
                cita_servicio.put("celular_cliente", cel);
                cita_servicio.put("tel_cliente", tel);
                cita_servicio.put("vehiculo_cliente", vehiculo);
                cita_servicio.put("ano_vehiculo", ano);
                cita_servicio.put("tipo_cita", tipo);
                cita_servicio.put("fecha_cita", fecha);
                cita_servicio.put("hora_cita", hora);
                cita_servicio.put("comentarios", comentarios);
                cita_servicio.put("kilometros", kilometros);
                cita_servicio.saveInBackground();


                progress = ProgressDialog.show(CitaServicio_NoLogin_4.this, "Cita a Servicio "+random,
                        "Enviando datos...", true);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //ENVIO DEL EMAIL


                        Mail m = new Mail("appautospv@gmail.com", "cm10052016");

                        String[] toArr = {email_envio_cita};
                        m.setTo(toArr);
                        m.setFrom(email_emisor);
                        m.setSubject("Cita No. " + random + " SOLICITUD DE CITA A SERVICIO DESDE LA ANDROID APP -PEDRO VILLAREJO- ");
                        m.setBody("Cita No. " + random+" SOLICITUD DE CITA A SERVICIO \n\n"
                                + "Número de solicitud de cita: " + random
                                + "\nCliente: " + nombre
                                + "\nEmail: " + email
                                + "\nCelular: " + cel
                                + "\nTeléfono: " + tel
                                + "\nVehiculo: " + vehiculo
                                + "\nAño: " + ano
                                + "\nKilómetros aprox.: " + kilometros
                                + "\nTipo de servicio: " + tipo
                                + "\nFecha solicitada: " + fecha
                                + "\nHora solicitada: " + hora
                                + "\nComentarios del cliente: " + comentarios);

                        try {
                            //m.addAttachment("/sdcard/filelocation");

                            if (m.send()) {

                                Toast.makeText(CitaServicio_NoLogin_4.this, "Sent Email.", Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(CitaServicio_NoLogin_4.this, "Email was not sent.", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            //Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show();
                            Log.e("PartyPlannerActivity", "Could not send email.", e);
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progress.dismiss();
                                Intent myIntent = new Intent(CitaServicio_NoLogin_4.this, CitaServicio_Foto_1.class);
                                myIntent.putExtra("email_address", email_envio_cita);
                                myIntent.putExtra("email_subject", "Fotos correspondientes a solicitud "+random);
                                myIntent.putExtra("email_text", "Fotos correspondientes a solicitud "+random);
                                CitaServicio_NoLogin_4.this.startActivity(myIntent);
                            }
                        });
                    }
                }).start();


                //fin captura datos enviar


            }

        });


    }



    public void addListenerCancelerButton() {

        cancelarButton = (Button) findViewById(R.id.cancelar);

        cancelarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(CitaServicio_NoLogin_4.this, WelcomeNoLogin.class);


                CitaServicio_NoLogin_4.this.startActivity(myIntent);

            }

        });
    }
}
