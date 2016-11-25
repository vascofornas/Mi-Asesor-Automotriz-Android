package com.webjuarez.miasesorautomotriz;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

/**
 * Created by modestovascofornas on 11/13/15.
 */
public class CustomAdapter extends BaseAdapter {

    String[] result, recipients;
    Context context;
    int[] imageId;
    private String nombre_asesor;
    private String apellidos_asesor;


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

                    SharedPreferences prefs =
                            context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                    String web = prefs.getString("autos_nuevos", "NO HA SELECCIONADO NINGUNA WEB");


                    Intent myIntent = new Intent(context, NuevosActivity.class);
                                    myIntent.putExtra("web", web); //Optional parameters

                                    context.startActivity(myIntent);





                }

                if (position == 3) {

                    Intent myIntent = new Intent(context, CostoServicio_NoLogin_1.class);


                    context.startActivity(myIntent);
                    //Toast.makeText(context, "Ha seleccionado LLAMAR" + result[position], Toast.LENGTH_LONG).show();

                }

                if (position == 4) {

//Toast.makeText(context, "Ha seleccionado LLAMAR" + result[position], Toast.LENGTH_LONG).show();

                    SharedPreferences prefs =
                            context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                    final String numero_financiera = prefs.getString("financiera", "NO HA SELECCIONADO NINGUNA WEB");
                    nombre_asesor = prefs.getString("nombre_asesor", "NO HA SELECCIONADO NINGUNA WEB");
                    apellidos_asesor = prefs.getString("apellidos_asesor", "NO HA SELECCIONADO NINGUNA WEB");



                    new AlertDialog.Builder(context)
                            .setTitle("Marcar a tu Asesor")
                            .setMessage("Estas seguro de que quieres marcar a la Financiera?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    Log.d("score", "Celular: " + numero_financiera);

                                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numero_financiera));


                                    context.startActivity(intent);



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
                if (position == 5) {

                    Intent myIntent = new Intent(context, Tramites_Online_1.class);


                    context.startActivity(myIntent);
                }

                if (position == 6) {

                    SharedPreferences prefs =
                            context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                    final String auxilio_vial_mex = prefs.getString("auxilio_vial_mex", "NO HA SELECCIONADO NINGUNA WEB");
                    nombre_asesor = prefs.getString("nombre_asesor", "NO HA SELECCIONADO NINGUNA WEB");
                    apellidos_asesor = prefs.getString("apellidos_asesor", "NO HA SELECCIONADO NINGUNA WEB");



                    new AlertDialog.Builder(context)
                            .setTitle("Marcar a tu Asesor")
                            .setMessage("Estas seguro de que quieres marcar al Auxilio Vial en México?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    Log.d("score", "Celular: " + auxilio_vial_mex);

                                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + auxilio_vial_mex));


                                    context.startActivity(intent);



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
                if (position == 7) {


                    SharedPreferences prefs =
                            context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                    final String auxilio_vial_usa = prefs.getString("auxilio_vial_usa", "NO HA SELECCIONADO NINGUNA WEB");
                    nombre_asesor = prefs.getString("nombre_asesor", "NO HA SELECCIONADO NINGUNA WEB");
                    apellidos_asesor = prefs.getString("apellidos_asesor", "NO HA SELECCIONADO NINGUNA WEB");



                    new AlertDialog.Builder(context)
                            .setTitle("Marcar a tu Asesor")
                            .setMessage("Estas seguro de que quieres marcar al Auxilio Vial en USA?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    Log.d("score", "Celular: " + auxilio_vial_usa);

                                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + auxilio_vial_usa));


                                    context.startActivity(intent);



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

                if (position == 8) {

                    Intent myIntent = new Intent(context, Seguros.class);


                    context.startActivity(myIntent);

                }
            }
        });

        return rowView;
    }

}