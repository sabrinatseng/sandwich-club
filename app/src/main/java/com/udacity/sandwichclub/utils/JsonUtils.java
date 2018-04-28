package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static final String KEY_MAIN_NAME = "mainName";
    public static final String KEY_ALSO_KNOWN_AS = "alsoKnownAs";
    public static final String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject sandwich = new JSONObject(json);
            JSONObject name = sandwich.getJSONObject("name");
            String mainName = name.optString(KEY_MAIN_NAME, "");
            JSONArray alsoKnownAsArray = name.getJSONArray(KEY_ALSO_KNOWN_AS);
            List<String> alsoKnownAs = new ArrayList<String>();

            for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                alsoKnownAs.add(alsoKnownAsArray.getString(i));
            }

            String placeOfOrigin = sandwich.optString(KEY_PLACE_OF_ORIGIN, "");
            String description = sandwich.optString(KEY_DESCRIPTION, "");
            String image = sandwich.optString(KEY_IMAGE, "");

            JSONArray ingredientsArray = sandwich.getJSONArray(KEY_INGREDIENTS);
            List<String> ingredients = new ArrayList<String>();

            for (int i = 0; i < ingredientsArray.length(); i++) {
                ingredients.add(ingredientsArray.getString(i));
            }

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
