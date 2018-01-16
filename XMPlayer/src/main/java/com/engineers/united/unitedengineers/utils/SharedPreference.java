/*
UNITED ENGINEERS
Object to be created in order to save and read from SharedPreferences
*/
package com.engineers.united.unitedengineers.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.engineers.united.unitedengineers.beans.Station;
import com.google.gson.Gson;

/**
 * Created by darren on 2017-11-18.
 */

public class SharedPreference {

    public static final String PREFS_NAME = "STATION_APP";
    public static final String FAVORITES = "Station_Favorite";

    public SharedPreference() {
        super();
    }

    // This four methods are used for maintaining favorites.
    public void saveFavorites(Context context, List<Station> favorites) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
    }

    public void addFavorite(Context context, Station station) {
        List<Station> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<Station>();
        favorites.add(station);
        saveFavorites(context, favorites);
    }

    public void removeFavorite(Context context, Station station) {
        ArrayList<Station> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(station);
            saveFavorites(context, favorites);
        }
    }

    //get favourites in JSON format and convert to gson and and to an arraylist
    public ArrayList<Station> getFavorites(Context context) {
        SharedPreferences settings;
        List<Station> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            Station[] favoriteItems = gson.fromJson(jsonFavorites,
                    Station[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<Station>(favorites);
        } else
            return null;

        return (ArrayList<Station>) favorites;
    }
}