package com.webjuarez.miasesorautomotriz;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

import android.app.Application;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Add your initialization code here
        Parse.initialize(this, "BgMIR069diLQoLTF1vRm0E7IMhLQ31eQWaqba64S", "8eqKjrvR8lVyJT3qUWHfBnSxTiX2Z8CE9wWPaY3k");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
      //  defaultACL.setPublicReadAccess(true);

     ParseACL.setDefaultACL(defaultACL, true);
    }

}