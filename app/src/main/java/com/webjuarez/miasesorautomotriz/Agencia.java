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
import android.util.Log;
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
    private String autos_nuevos;
    private String financiera;
    private String auxilio_vial_mex;
    private String auxilio_vial_usa;
    private String aseguradora1;
    private String aseguradora2;
    private String aseguradora3;
    private String aseguradora4;
    private String aseguradora5;
    private String aseguradora6;





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
        autos_nuevos = "";
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
            financiera = collegeData.getString(Config.KEY_FIN);
            autos_nuevos = collegeData.getString(Config.KEY_AN);
            auxilio_vial_mex = collegeData.getString(Config.KEY_MEX);
            auxilio_vial_usa = collegeData.getString(Config.KEY_USA);
            aseguradora1 = collegeData.getString(Config.KEY_ASE_1);
            aseguradora2 = collegeData.getString(Config.KEY_ASE_2);
            aseguradora3 = collegeData.getString(Config.KEY_ASE_3);
            aseguradora4 = collegeData.getString(Config.KEY_ASE_4);
            aseguradora5 = collegeData.getString(Config.KEY_ASE_5);
            aseguradora6 = collegeData.getString(Config.KEY_ASE_6);
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
                editor.putString("autos_nuevos", autos_nuevos);
                editor.putString("financiera", financiera);
                editor.putString("auxilio_vial_mex", auxilio_vial_mex);
                editor.putString("auxilio_vial_usa", auxilio_vial_usa);
                editor.putString("aseguradora1", aseguradora1);
                editor.putString("aseguradora2", aseguradora2);
                editor.putString("aseguradora3", aseguradora3);
                editor.putString("aseguradora4", aseguradora4);
                editor.putString("aseguradora5", aseguradora5);
                editor.putString("aseguradora6", aseguradora6);



                Log.d("CELULAR", "M E X : " + auxilio_vial_mex);


                editor.putString("auxilio_vial_usa", auxilio_vial_usa);
                editor.commit();
                Intent intent = new Intent(Agencia.this, Asesor.class);
                startActivity(intent);
            }

        });
    }
}