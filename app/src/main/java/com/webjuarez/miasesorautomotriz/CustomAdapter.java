package com.webjuarez.miasesorautomotriz;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by modestovascofornas on 11/13/15.
 */
public class CustomAdapter extends BaseAdapter {

    String[] result, recipients;
    Context context;
    int[] imageId;


    String nombre_POI, latitud, longitud, direccion, recipient, subject, body;
    private static LayoutInflater inflater = null;

    public CustomAdapter(WelcomeNoLogin mainActivity, String[] prgmNameList, int[] prgmImages) {
        // TODO Auto-generated constructor stub
        result = prgmNameList;
        context = mainActivity;
        imageId = prgmImages;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //query a Parse

        ParseQuery<ParseObject> query = ParseQuery.getQuery("datos_contacto");
        query.whereEqualTo("tipo_contacto", "nombre_POI");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    int len = scoreList.size();
                    for (int i = 0; i < len; i++) {
                        ParseObject p = scoreList.get(i);
                        nombre_POI = p.getString("dato_contacto");

                        Log.d("Nombre POI", "NOMBRE POI: " + nombre_POI);


                    }
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
        ParseQuery<ParseObject> query1 = ParseQuery.getQuery("datos_contacto");
        query1.whereEqualTo("tipo_contacto", "latitud");
        query1.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    int len = scoreList.size();
                    for (int i = 0; i < len; i++) {
                        ParseObject p = scoreList.get(i);
                        latitud = p.getString("dato_contacto");

                        Log.d("LATITUD POI", "LATITUD POI: " + latitud);


                    }
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("datos_contacto");
        query2.whereEqualTo("tipo_contacto", "longitud");
        query2.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    int len = scoreList.size();
                    for (int i = 0; i < len; i++) {
                        ParseObject p = scoreList.get(i);
                        longitud = p.getString("dato_contacto");

                        Log.d("LONGITUD POI", "LONGITUD POI: " + longitud);


                    }
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
        ParseQuery<ParseObject> query5 = ParseQuery.getQuery("datos_contacto");
        query5.whereEqualTo("tipo_contacto", "direccion");
        query5.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    int len = scoreList.size();
                    for (int i = 0; i < len; i++) {
                        ParseObject p = scoreList.get(i);
                        direccion = p.getString("dato_contacto");

                        Log.d("DIRECCION POI", "DIRECCION POI: " + direccion);


                    }
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });


    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder {
        TextView tv;
        ImageView img;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.grid_single, null);
        holder.tv = (TextView) rowView.findViewById(R.id.grid_text);
        holder.img = (ImageView) rowView.findViewById(R.id.grid_image);

        holder.tv.setText(result[position]);
        holder.img.setImageResource(imageId[position]);



        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // Toast.makeText(context, "Ha seleccionado " + result[position], Toast.LENGTH_LONG).show();
                //posiciones
                //position = 0 -->cossto servicio --> CITAS A SERVICIO
                //position = 1 -->abrir web de autos nuevos
                //position = 2 -->abrir web de autos usados
                //position = 3 -->abrir web de dto a proveedores
                //position = 4 -->abrir proceso de cita
                //position = 5 -->tramites online
                //position = 6 -->auxilio vial MEX
                //position = 7 -->auxilio vial USA
                //position = 8 -->auxilio vial FIAT


                if (position == 0) {


                    Intent myIntent = new Intent(context, MiAuto.class);


                    context.startActivity(myIntent);


                }

                if (position == 1) {

                    Intent myIntent = new Intent(context, CitaServicio_NoLogin_1.class);


                    context.startActivity(myIntent);


                }
                if (position == 2) {

                    //Toast.makeText(context, "Ha seleccionado LLAMAR" + result[position], Toast.LENGTH_LONG).show();

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("datos_contacto");
                    query.whereEqualTo("tipo_contacto", "web_nuevos");
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (e == null) {
                                int len = scoreList.size();
                                for (int i = 0; i < len; i++) {
                                    ParseObject p = scoreList.get(i);
                                    String web = p.getString("dato_contacto");

                                    Log.d("score", "Web: " + web);


                                    Intent myIntent = new Intent(context, NuevosActivity.class);
                                    myIntent.putExtra("web", web); //Optional parameters

                                    context.startActivity(myIntent);

                                }
                            } else {
                                Log.d("score", "Error: " + e.getMessage());
                            }
                        }
                    });


                }

                if (position == 3) {

                    Intent myIntent = new Intent(context, CostoServicio_NoLogin_1.class);


                    context.startActivity(myIntent);
                    //Toast.makeText(context, "Ha seleccionado LLAMAR" + result[position], Toast.LENGTH_LONG).show();

                }

                if (position == 4) {

//Toast.makeText(context, "Ha seleccionado LLAMAR" + result[position], Toast.LENGTH_LONG).show();

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("datos_contacto");
                    query.whereEqualTo("tipo_contacto", "cfcredit");
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (e == null) {
                                int len = scoreList.size();
                                for (int i = 0; i < len; i++) {
                                    ParseObject p = scoreList.get(i);
                                    String numero = p.getString("dato_contacto");

                                    Log.d("score", "Celular: " + numero);

                                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numero));
                                    context.startActivity(intent);

                                }
                            } else {
                                Log.d("score", "Error: " + e.getMessage());
                            }
                        }
                    });

                }
                if (position == 5) {

                    Intent myIntent = new Intent(context, Tramites_Online_2.class);


                    context.startActivity(myIntent);
                }

                if (position == 6) {


                    Intent myIntent = new Intent(context, Auxilio_Vial_Mexico.class);


                    context.startActivity(myIntent);


                }
                if (position == 7) {

                    Intent myIntent = new Intent(context, Auxilio_Vial_USA.class);


                    context.startActivity(myIntent);

                }

                if (position == 8) {

                    Intent myIntent = new Intent(context, Seguros.class);


                    context.startActivity(myIntent);

                }
            }
        });

        return rowView;
    }

}