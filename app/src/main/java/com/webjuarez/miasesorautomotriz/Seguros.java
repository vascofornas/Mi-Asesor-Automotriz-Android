package com.webjuarez.miasesorautomotriz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by modestovascofornas on 11/15/15.
 */
public class Seguros extends Activity {



    String nombre, email, cel, tel;
    String recipient, subject, body;
    ImageButton homeButton, llamarButton, emailButton, citaButton,userButton, b1,b2,b3,b4,b5,b6;
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguros);

        addListenerHomeButton();
        addListenerLlamarButton();
        addListenerEmailButton();
        addListenerCitaButton();
        addListenerUserButton();
        addListenerB1();
        addListenerB2();
        addListenerB3();
        addListenerB4();
        addListenerB5();
        addListenerB6();






    }
    public void addListenerB6() {

        b6 = (ImageButton) findViewById(R.id.b6);

        b6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                SharedPreferences prefs =
                        context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                String aseguradora6 = prefs.getString("aseguradora6", "NO HA SELECCIONADO NINGUNA WEB");

                Log.d("CELULAR", "CELULAR: " + aseguradora6);

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + aseguradora6));
                context.startActivity(intent);




            }

        });

    }

    public void addListenerB5() {

        b5 = (ImageButton) findViewById(R.id.b5);

        b5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                SharedPreferences prefs =
                        context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                String aseguradora5 = prefs.getString("aseguradora5", "NO HA SELECCIONADO NINGUNA WEB");

                Log.d("CELULAR", "CELULAR: " + aseguradora5);

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + aseguradora5));
                context.startActivity(intent);


            }

        });

    }

    public void addListenerB4() {

        b4 = (ImageButton) findViewById(R.id.b4);

        b4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                SharedPreferences prefs =
                        context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                String aseguradora4 = prefs.getString("aseguradora4", "NO HA SELECCIONADO NINGUNA WEB");

                Log.d("CELULAR", "CELULAR: " + aseguradora4);

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + aseguradora4));
                context.startActivity(intent);



            }

        });

    }

    public void addListenerB3() {

        b3 = (ImageButton) findViewById(R.id.b3);

        b3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                SharedPreferences prefs =
                        context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                String aseguradora3 = prefs.getString("aseguradora3", "NO HA SELECCIONADO NINGUNA WEB");

                Log.d("CELULAR", "CELULAR: " + aseguradora3);

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + aseguradora3));
                context.startActivity(intent);



            }

        });

    }

    public void addListenerB2() {

        b2 = (ImageButton) findViewById(R.id.b2);

        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                SharedPreferences prefs =
                        context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                String aseguradora2 = prefs.getString("aseguradora2", "NO HA SELECCIONADO NINGUNA WEB");

                Log.d("CELULAR", "CELULAR: " + aseguradora2);

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + aseguradora2));
                context.startActivity(intent);



            }

        });

    }

    public void addListenerB1() {

        b1 = (ImageButton) findViewById(R.id.b1);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                SharedPreferences prefs =
                        context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                String aseguradora1 = prefs.getString("aseguradora1", "NO HA SELECCIONADO NINGUNA WEB");

                Log.d("CELULAR", "CELULAR: " + aseguradora1);

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + aseguradora1));
                context.startActivity(intent);



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
                                    Toast.makeText(Seguros.this, "No dispone de aplicaciones email.",
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

                Intent myIntent = new Intent(Seguros.this, WelcomeNoLogin.class);

                Seguros.this.startActivity(myIntent);

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

                                    Toast.makeText(Seguros.this, "ERROR - SMS no enviado...",

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
                                    Toast.makeText(Seguros.this, "No dispone de aplicaciones email.",
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






}
