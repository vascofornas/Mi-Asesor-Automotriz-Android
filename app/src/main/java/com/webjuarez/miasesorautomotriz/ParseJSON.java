package com.webjuarez.miasesorautomotriz;

/**
 * Created by Modesto on 11/15/2016.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Belal on 9/22/2015.
 */
public class ParseJSON {
    public static String[] ids;
    public static String[] nombres;

    public static String[] apellidos;

    public static String[] tels;
    public static String[] emails;

    public static final String JSON_ARRAY = "result";
    public static final String KEY_ID = "id";
    public static final String KEY_NOMBRE = "nombre";

    public static final String KEY_TEL = "tel";

    public static final String KEY_APELLIDOS = "apellidos";
    public static final String KEY_EMAIL = "email";

    private JSONArray users = null;

    private String json;

    public ParseJSON(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            ids = new String[users.length()];
            nombres = new String[users.length()];
            apellidos = new String[users.length()];
            tels = new String[users.length()];

            emails = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                ids[i] = jo.getString(KEY_ID);
                nombres[i] = jo.getString(KEY_NOMBRE);
                apellidos[i] = jo.getString(KEY_APELLIDOS);
                tels[i] = jo.getString(KEY_TEL);

                emails[i] = jo.getString(KEY_EMAIL);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}