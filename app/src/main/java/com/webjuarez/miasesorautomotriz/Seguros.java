package com.webjuarez.miasesorautomotriz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
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

    private String name;
    private String codigo_agencia;
    private String nombre_agencia;
    private String id_agencia;
    private String tel_asesor;
    private String email_asesor;
    private String nombre_asesor;
    private String apellidos_asesor;
    private String google_play_agencia;
    private String aseguradora1;
    private String aseguradora2;
    private String aseguradora3;
    private String aseguradora4;
    private String aseguradora5;
    private String aseguradora6;

    private TextView nombreAsesor;

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
        addListenerSMSButton();
        addListenerCompartirButton();
        addListenerB1();
        addListenerB2();
        addListenerB3();
        addListenerB4();
        addListenerB5();
        addListenerB6();



        SharedPreferences prefs =
                getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        nombre_asesor = prefs.getString("nombre_asesor", "NO HA SELECCIONADO NINGUNA AGENCIA");
         apellidos_asesor = prefs.getString("apellidos_asesor", "NO HA SELECCIONADO NINGUNA AGENCIA");
        tel_asesor = prefs.getString("tel_asesor", "NO HA SELECCIONADO NINGUNA AGENCIA");
        email_asesor = prefs.getString("email_asesor", "NO HA SELECCIONADO NINGUNA AGENCIA");
        google_play_agencia = prefs.getString("google_play_agencia", "NO HA SELECCIONADO NINGUNA AGENCIA");
        aseguradora1 = prefs.getString("aseguradora1", "NO HA SELECCIONADO NINGUNA AGENCIA");
        aseguradora2 = prefs.getString("aseguradora2", "NO HA SELECCIONADO NINGUNA AGENCIA");
        aseguradora3 = prefs.getString("aseguradora3", "NO HA SELECCIONADO NINGUNA AGENCIA");
        aseguradora4 = prefs.getString("aseguradora4", "NO HA SELECCIONADO NINGUNA AGENCIA");
        aseguradora5 = prefs.getString("aseguradora5", "NO HA SELECCIONADO NINGUNA AGENCIA");
        aseguradora6 = prefs.getString("aseguradora6", "NO HA SELECCIONADO NINGUNA AGENCIA");
        nombreAsesor = (TextView) findViewById(R.id.nombreAsesor);
        nombreAsesor.setText("Asesor: "+nombre_asesor+' '+apellidos_asesor);


    }
    public void addListenerB6() {

        llamarButton = (ImageButton) findViewById(R.id.b6);

        llamarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {



                new AlertDialog.Builder(Seguros.this)
                        .setTitle("Marcar a tu Aseguradora")
                        .setMessage("Estas seguro de que quieres marcar a tu Aseguradora?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                Log.d("score", "Celular: " + tel_asesor);

                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + aseguradora6));


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
    public void addListenerB5() {

        llamarButton = (ImageButton) findViewById(R.id.b5);

        llamarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {



                new AlertDialog.Builder(Seguros.this)
                        .setTitle("Marcar a tu Aseguradora")
                        .setMessage("Estas seguro de que quieres marcar a tu Aseguradora?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                Log.d("score", "Celular: " + tel_asesor);

                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + aseguradora5));


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
    public void addListenerB4() {

        llamarButton = (ImageButton) findViewById(R.id.b4);

        llamarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {



                new AlertDialog.Builder(Seguros.this)
                        .setTitle("Marcar a tu Aseguradora")
                        .setMessage("Estas seguro de que quieres marcar a tu Aseguradora?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                Log.d("score", "Celular: " + tel_asesor);

                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + aseguradora4));


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
    public void addListenerB3() {

        llamarButton = (ImageButton) findViewById(R.id.b3);

        llamarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {



                new AlertDialog.Builder(Seguros.this)
                        .setTitle("Marcar a tu Aseguradora")
                        .setMessage("Estas seguro de que quieres marcar a tu Aseguradora?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                Log.d("score", "Celular: " + tel_asesor);

                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + aseguradora3));


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
    public void addListenerB2() {

        llamarButton = (ImageButton) findViewById(R.id.b2);

        llamarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {



                new AlertDialog.Builder(Seguros.this)
                        .setTitle("Marcar a tu Aseguradora")
                        .setMessage("Estas seguro de que quieres marcar a tu Aseguradora?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                Log.d("score", "Celular: " + tel_asesor);

                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + aseguradora2));


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
    public void addListenerB1() {

        llamarButton = (ImageButton) findViewById(R.id.b1);

        llamarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {



                new AlertDialog.Builder(Seguros.this)
                        .setTitle("Marcar a tu Aseguradora")
                        .setMessage("Estas seguro de que quieres marcar a tu Aseguradora?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                Log.d("score", "Celular: " + tel_asesor);

                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + aseguradora1));


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

                Intent myIntent = new Intent(Seguros.this, WelcomeNoLogin.class);

                Seguros.this.startActivity(myIntent);

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
                    Toast.makeText(Seguros.this, "No dispone de aplicaciones email.",
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

                    Toast.makeText(Seguros.this, "ERROR - SMS no enviado...",

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
                    Toast.makeText(Seguros.this, "No dispone de aplicaciones email.",
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



                new AlertDialog.Builder(Seguros.this)
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

}
