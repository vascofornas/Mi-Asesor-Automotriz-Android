package com.webjuarez.miasesorautomotriz;

/**
 * Created by Modesto on 11/14/2016.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;

import android.view.View.OnClickListener;

public class Agencia extends Activity  {

    private EditText editTextId;
    private Button buttonGet;
    private Button buttonAceptar;
    private TextView textViewResult;
    private String name;
    private String codigo_agencia;
    private String nombre_agencia;
    private String id_agencia;
    private String google_play_agencia;



    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agencia);

        editTextId = (EditText) findViewById(R.id.editTextId);
        buttonGet = (Button) findViewById(R.id.buttonGet);
        buttonAceptar = (Button) findViewById(R.id.buttonAceptar);
        textViewResult = (TextView) findViewById(R.id.textViewResult);


        addListenerButtonGet();
        addListenerButtonAceptar();

    }





    public void addListenerButtonGet() {
        buttonGet = (Button) findViewById(R.id.buttonGet);

        buttonGet.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
                String id = editTextId.getText().toString().trim();
                if (id.equals(""))

                {
                    Toast.makeText(Agencia.this, "Introduzca el codigo de la Agencia", Toast.LENGTH_LONG).show();
                    return;
                }

                loading = ProgressDialog.show(Agencia.this, "Espere...", "Buscando datos de la Agencia...", false, false);

                String url = Config.DATA_URL + editTextId.getText().toString().trim();

                StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        showJSON(response);
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Agencia.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                            }
                        });

                RequestQueue requestQueue = Volley.newRequestQueue(Agencia.this);
                requestQueue.add(stringRequest);
            }
        });


    }


    //fin GETDATA



    private void showJSON(String response) {
        String nombre = "";
        String direccion = "";
        codigo_agencia = "";
        id_agencia = "";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);
            nombre_agencia = collegeData.getString(Config.KEY_NAME);
            direccion = collegeData.getString(Config.KEY_ADDRESS);
            id_agencia = collegeData.getString(Config.KEY_ID);
            google_play_agencia = collegeData.getString(Config.KEY_GP);
            codigo_agencia = collegeData.getString(Config.KEY_VC);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (nombre == "null") {
            nombre = "AGENCIA NO ENCONTRADA";
            textViewResult.setText("Agencia:\t" + nombre);
        }
        else {



            textViewResult.setText("Agencia:\t" + nombre_agencia + "\nDireccion:\t" + direccion + "\nCodigo:\t" + codigo_agencia );
            buttonAceptar.setVisibility(View.VISIBLE);

        }
    }
    public void addListenerButtonAceptar() {

        buttonAceptar = (Button) findViewById(R.id.buttonAceptar);

        buttonAceptar.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {


                SharedPreferences prefs =
                        getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("codigo_agencia", codigo_agencia);
                editor.putString("nombre_agencia", nombre_agencia);
                editor.putString("id_agencia", id_agencia);
                editor.putString("google_play_agencia", google_play_agencia);

                editor.commit();
                Intent intent = new Intent(Agencia.this, Asesor.class);
                startActivity(intent);
            }

        });
    }
}