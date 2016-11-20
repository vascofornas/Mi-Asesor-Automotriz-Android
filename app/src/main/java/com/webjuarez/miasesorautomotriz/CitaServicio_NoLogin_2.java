package com.webjuarez.miasesorautomotriz;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Calendar;
import java.util.List;

/**
 * Created by modestovascofornas on 11/15/15.
 */
public class CitaServicio_NoLogin_2 extends Activity {


    Button continuarButton, cancelarButton;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    String nombre, email, cel, tel, fecha, hora;
    String recipient, subject, body;
    private TextView displayTime;
    private Button pickTime;
    ImageButton homeButton, llamarButton, emailButton, citaButton,userButton;


    private int pHour;
    private int pMinute;
    /**
     * This integer will uniquely define the dialog to be used for displaying time picker.
     */
    static final int TIME_DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cita_no_login_2);

        addListenerHomeButton();
        addListenerLlamarButton();
        addListenerEmailButton();
        addListenerCitaButton();
        addListenerUserButton();

        Intent intent = getIntent();
        nombre = intent.getStringExtra("nombre");
        email = intent.getStringExtra("email");
        cel = intent.getStringExtra("celular");
        tel = intent.getStringExtra("tel");


        dateView = (TextView) findViewById(R.id.editfecha);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);


        /** Capture our View elements */
        displayTime = (TextView) findViewById(R.id.edithora);
        pickTime = (Button) findViewById(R.id.buttonTime);

        /** Listener for click event of the button */
        pickTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(998);
            }
        });

        /** Get the current time */
        final Calendar cal = Calendar.getInstance();
        pHour = cal.get(Calendar.HOUR_OF_DAY);
        pMinute = cal.get(Calendar.MINUTE);

        /** Display the current time in the TextView */
        updateDisplay();

        addListenerContinuarButton();
        addListenerCancelerButton();


    }


    /**
     * Callback received when the user "picks" a time in the dialog
     */
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    pHour = hourOfDay;
                    pMinute = minute;
                    updateDisplay();
                    displayToast();
                }
            };

    /**
     * Updates the time in the TextView
     */
    private void updateDisplay() {
        displayTime.setText(
                new StringBuilder()
                        .append(pad(pHour)).append(":")
                        .append(pad(pMinute)));
    }

    /**
     * Displays a notification when the time is updated
     */
    private void displayToast() {
        Toast.makeText(this, new StringBuilder().append("Time choosen is ").append(displayTime.getText()), Toast.LENGTH_SHORT).show();

    }

    /**
     * Add padding to numbers less than ten
     */
    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }


    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "Seleccione fecha", Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        if (id == 998) {
            return new TimePickerDialog(this,
                    mTimeSetListener, pHour, pMinute, false);
        }
        return null;
    }


    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2 + 1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    public void addListenerHomeButton() {

        homeButton = (ImageButton) findViewById(R.id.homeButton);

        homeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(CitaServicio_NoLogin_2.this, WelcomeNoLogin.class);

                CitaServicio_NoLogin_2.this.startActivity(myIntent);

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

                                smsSIntent.putExtra("sms_body", "  ");

                                try {

                                    startActivity(smsSIntent);

                                } catch (Exception ex) {

                                    Toast.makeText(CitaServicio_NoLogin_2.this, "ERROR - SMS no enviado...",

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
                                    Toast.makeText(CitaServicio_NoLogin_2.this, "No dispone de aplicaciones email.",
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
                                    Toast.makeText(CitaServicio_NoLogin_2.this, "No dispone de aplicaciones email.",
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


                Log.d("CITA A SERVICIO", "NOMBRE: " + nombre);
                Log.d("CITA A SERVICIO", "EMAIL: " + email);
                Log.d("CITA A SERVICIO", "CEL: " + cel);
                Log.d("CITA A SERVICIO", "TEL: " + tel);

                Intent myIntent = new Intent(CitaServicio_NoLogin_2.this, CitaServicio_NoLogin_3.class);


                fecha = dateView.getText().toString();
                hora = displayTime.getText().toString();

                myIntent.putExtra("nombre", nombre);
                myIntent.putExtra("email", email);
                myIntent.putExtra("celular", cel);
                myIntent.putExtra("tel", tel);
                myIntent.putExtra("fecha", fecha);
                myIntent.putExtra("hora", hora);

                CitaServicio_NoLogin_2.this.startActivity(myIntent);

            }

        });

    }

    public void addListenerCancelerButton() {

        cancelarButton = (Button) findViewById(R.id.cancelar);

        cancelarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(CitaServicio_NoLogin_2.this, WelcomeNoLogin.class);


                CitaServicio_NoLogin_2.this.startActivity(myIntent);

            }

        });
    }
}
