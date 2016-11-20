package com.webjuarez.miasesorautomotriz;

/**
 * Created by Modesto on 11/15/2016.
 */


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Belal on 9/22/2015.
 */

public class CustomList extends ArrayAdapter<String> {
    private String[] ids;

    private String[] nombres;
    private String[] apellidos;
    private String[] tels;
    private String[] emails;
    private Activity context;

    public CustomList(Activity context, String[] ids, String[] nombres, String[] apellidos, String[] tels, String[] emails) {
        super(context, R.layout.list_view_layout, ids);
        this.context = context;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.emails = emails;
        this.tels = tels;
        this.ids = ids;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_view_layout, null, true);
        TextView textViewId = (TextView) listViewItem.findViewById(R.id.textViewId);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.textViewEmail);

       textViewId.setText("Tel: "+emails[position]);
       textViewName.setText(nombres[position]+" "+apellidos[position]);
        textViewEmail.setText("Email: "+tels[position]);

        return listViewItem;
    }
}