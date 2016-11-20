package com.webjuarez.miasesorautomotriz;

/**
 * Created by Modesto on 11/14/2016.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
public class Asesor extends Activity  {

    public static  String JSON_URL = "http://miasesorautomotriz.com/administrar/phpfiles/get_asesores.php?id=";


    private Button buttonGet;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asesor);




        SharedPreferences prefs =
                getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        String nombre_agencia = prefs.getString("nombre_agencia", "NO HA SELECCIONADO NINGUNA AGENCIA");

        buttonGet = (Button) findViewById(R.id.buttonGet);
        buttonGet.setText(nombre_agencia);

        listView = (ListView) findViewById(R.id.listView);
        sendRequest();
    }

    private void sendRequest(){
        SharedPreferences prefs =
                getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        String id_agencia = prefs.getString("id_agencia", "NO HA SELECCIONADO NINGUNA AGENCIA");
        JSON_URL = "http://miasesorautomotriz.com/administrar/phpfiles/get_asesores.php?id="+id_agencia;
        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Asesor.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();
        CustomList cl = new CustomList(this, ParseJSON.ids,ParseJSON.nombres,ParseJSON.apellidos,ParseJSON.emails,ParseJSON.tels);
        listView.setAdapter(cl);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Toast.makeText(Asesor.this, "Ha seleccionado el asesor -> "+ ParseJSON.nombres[position], Toast.LENGTH_LONG).show();
                Intent i = new Intent(Asesor.this, WelcomeNoLogin.class);

                // Pass listview item click position
                SharedPreferences prefs =
                        getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("nombre_asesor", ParseJSON.nombres[position]);
                editor.putString("id_asesor", ParseJSON.ids[position]);
                editor.putString("apellidos_asesor", ParseJSON.apellidos[position]);
                editor.putString("email_asesor", ParseJSON.emails[position]);
                editor.putString("tel_asesor", ParseJSON.tels[position]);
                editor.commit();


                i.putExtra("position", ParseJSON.nombres[position]);
                // Open SingleItemView.java Activity
                startActivity(i);
            }

        });



    }


}