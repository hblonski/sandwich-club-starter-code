package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        final String NAME = "name";
        final String MAIN_NAME = "mainName";
        final String AKA = "alsoKnownAs";
        final String PLACE_OF_ORIGIN = "placeOfOrigin";
        final String DESCRIPTION = "description";
        final String IMAGE_URL = "image";
        final String INGREDIENTS = "ingredients";

        Sandwich sandwich = new Sandwich();

        try {
            JSONObject sandwichJson = new JSONObject(json);
            JSONObject sandwichNameJson = sandwichJson.getJSONObject(NAME);

            sandwich.setMainName(sandwichNameJson.getString(MAIN_NAME));
            sandwich.setAlsoKnownAs(extractValues(sandwichNameJson.getJSONArray(AKA), String.class));
            sandwich.setPlaceOfOrigin(sandwichJson.getString(PLACE_OF_ORIGIN));
            sandwich.setDescription(sandwichJson.getString(DESCRIPTION));
            sandwich.setImage(sandwichJson.getString(IMAGE_URL));
            sandwich.setIngredients(extractValues(sandwichJson.getJSONArray(INGREDIENTS), String.class));

        } catch (JSONException e) {
            Log.e(JsonUtils.class.toString(), e.getMessage());
            sandwich = null;
        }
        return sandwich;
    }

    private static <T> List<T> extractValues(JSONArray jsonArray, Class<T> clazz) throws JSONException {
        List<T> result = new ArrayList<T>();
        for (int i = 0; i < jsonArray.length(); i++) {
            result.add(clazz.cast(jsonArray.get(i)));
        }
        return result;
    }
}
