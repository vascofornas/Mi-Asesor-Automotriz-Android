package com.webjuarez.miasesorautomotriz;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Modesto on 11/19/2016.
 */



public class ConfigModelos {
    //JSON URL
Context con;

    public static final String DATA_URL = "http://miasesorautomotriz.com/administrar/phpfiles/getModelos.php";

    //Tags used in the JSON String
    public static final String TAG_MODELO  = "nombre_modelo";


    //JSON array name
    public static final String JSON_ARRAY = "result";
}