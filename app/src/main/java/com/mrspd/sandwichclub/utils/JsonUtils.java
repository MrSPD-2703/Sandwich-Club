package com.mrspd.sandwichclub.utils;

import com.mrspd.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static  String name = "name";
    private static String place_of_origin = "placeOfOrigin";
    private static String image = "image";
    private  static String main_name = "mainName";
    private static String descriptionn = "description";
    private static String alsoKnownAS = "alsoKnownAs";
    private static String ingrediants = "ingredients";

    private static String ParsingName(JSONObject j) throws JSONException {
        return  j.getString(main_name);
    }

    public static Sandwich parseSandwichJson(String json) {

        try{
            JSONObject sandwichhh =  new JSONObject(json);
            JSONObject namej = sandwichhh.getJSONObject(name);
            String nameee = ParsingName(namej);
            List<String> alsoknownn= parseAlsoKnown(namej);
            String descriptionnn = sandwichhh.getString(descriptionn);
            String imagee = sandwichhh.getString(image);
            String placeOfOriginn = sandwichhh.getString(place_of_origin);
            List<String> ingrediantss = parseMyIngrediants(sandwichhh);
            return  new Sandwich(nameee, alsoknownn, placeOfOriginn, descriptionnn,imagee,ingrediantss);



        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<String> parseMyIngrediants(JSONObject sandwichhh) throws JSONException {
        JSONArray newarray = sandwichhh.getJSONArray(ingrediants);
        return populatemyList(newarray);
    }

    private static List<String> parseAlsoKnown(JSONObject namej) {
        try {
            JSONArray newjsonarray = namej.getJSONArray(alsoKnownAS);
            return populatemyList(newjsonarray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
        
    }

    private static List<String> populatemyList(JSONArray newjsonarray) throws JSONException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < newjsonarray.length(); i++) {
            list.add(newjsonarray.getString(i));
        }
        return list;
    }
}
