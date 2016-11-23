package com.webjuarez.miasesorautomotriz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.widget.Toast.makeText;

/**
 * Created by modestovascofornas on 11/15/15.
 */
public class Tramites_Online_1 extends Activity {

    EditText nombreTV, emailTV, celTV, telTV;
    Button continuarButton, cancelarButton;
    String nombre, email, cel, tel,tipo;
    String recipient, subject, body;
    ImageButton homeButton, llamarButton, emailButton, citaButton,userButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tramites_online_1);

        Intent intent = getIntent();
        tipo = intent.getStringExtra("tipo");

        addListenerHomeButton();
        addListenerLlamarButton();
        addListenerEmailButton();
        addListenerCitaButton();
        addListenerUserButton();

        addListenerContinuarButton();
        addListenerCancelerButton();


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
                                    Toast.makeText(Tramites_Online_1.this, "No dispone de aplicaciones email.",
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

                Intent myIntent = new Intent(Tramites_Online_1.this, WelcomeNoLogin.class);

                Tramites_Online_1.this.startActivity(myIntent);

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

                                    Toast.makeText(Tramites_Online_1.this, "ERROR - SMS no enviado...",

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
                                    Toast.makeText(Tramites_Online_1.this, "No dispone de aplicaciones email.",
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

                EditText e = (EditText) findViewById(R.id.edithora);
                EditText n = (EditText) findViewById(R.id.editfecha);
                EditText t = (EditText) findViewById(R.id.edittel);
                String email = e.getText().toString();

                if (n.getText().length() == 0) {

                    Context context = getApplicationContext();
                    CharSequence text = "Su nombre es obligatorio";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = makeText(context, text, duration);
                    toast.show();
                    //Show Toast
                }
               else if (t.getText().length() == 0) {

                    Context context = getApplicationContext();
                    CharSequence text = "Su celular o teléfono es obligatorio";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = makeText(context, text, duration);
                    toast.show();
                    //Show Toast
                }
                else if  (!isValid(email)) {

                    Context context = getApplicationContext();
                    CharSequence text = "Su email no es válido";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = makeText(context, text, duration);
                    toast.show();
                } else

                {

                    nombreTV = (EditText) findViewById(R.id.editfecha);
                    emailTV = (EditText) findViewById(R.id.edithora);

                    telTV = (EditText) findViewById(R.id.edittel);


                    nombre = nombreTV.getText().toString();
                    email = emailTV.getText().toString();
                   // cel = celTV.getText().toString();
                    tel = telTV.getText().toString();


                    Log.d("CITA A SERVICIO", "NOMBRE: " + nombre);
                    Log.d("CITA A SERVICIO", "EMAIL: " + email);
                   // Log.d("CITA A SERVICIO", "CEL: " + cel);
                    Log.d("CITA A SERVICIO", "TEL: " + tel);

                    Intent myIntent = new Intent(Tramites_Online_1.this, Tramites_Online_3.class);


                    myIntent.putExtra("nombre", nombre);
                    myIntent.putExtra("email", email);
                   // myIntent.putExtra("celular", cel);
                    myIntent.putExtra("tel", tel);
                    myIntent.putExtra("tipo", tipo);


                    Tramites_Online_1.this.startActivity(myIntent);

                }
            }

        });




    }

    public static boolean isValid(String email)
    {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }


    public void addListenerCancelerButton() {

        cancelarButton = (Button) findViewById(R.id.cancelar);

        cancelarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(Tramites_Online_1.this, WelcomeNoLogin.class);


                Tramites_Online_1.this.startActivity(myIntent);

            }

        });
    }
}
