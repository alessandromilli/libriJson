package com.example.librijson;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import android.util.JsonReader;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONReader {

    public static final String JSON_FILE="libri.json";

    public static void main(String[] args) throws FileNotFoundException {

        Libro libri[];



        InputStream input = new FileInputStream(JSON_FILE);

        JSONReader jsonReader = Json.createReader(input);

        JSONObject jsonObject = jsonReader.readObject();

        jsonReader.close();

        JSONObject innerJsonObject = jsonObject.getJsonObject("libreria");

        JSONArray jsonArray = innerJsonObject.getJsonArray("libri");

        libri = new Libro[jsonArray.size()];

        int index = 0;

        for (JSONValue element : jsonArray) {
            Libro libro = new Libro();

            libro.setGenere(element.asJsonObject().getString("genere"));
            libro.setTitolo(element.asJsonObject().getString("titolo"));
            libro.setAutore(element.asJsonObject().getString("autore"));
            libro.setPrezzo((float) element.asJsonObject().getJsonNumber("prezzo").doubleValue());

            libri[index++] = libro;
        }

        for (index=0; index<libri.length; index++)
            System.out.println(libri[index]);

    }

}