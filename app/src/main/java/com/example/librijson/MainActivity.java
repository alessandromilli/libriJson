package com.example.librijson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String JSON_FILE="libri.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fai un arraylist con i soli titoli
        Libro[] libri = readJson();
        ArrayList<String> titoli = new ArrayList<String>();
        for (int i = 0; i< libri.length;i++){
            titoli.add(libri[i].getTitolo());

        }
        ListView lv = (ListView) findViewById(R.id.lv1);
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, titoli);
        // Set The Adapter
        lv.setAdapter(arrayAdapter);

    }

    public Libro[] readJson(){
        String json = null;
        Libro[] libri=null;
        try {
            InputStream is = getAssets().open(JSON_FILE);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            JSONObject innerJsonObject = new JSONObject(json).getJSONObject("libreria");
            JSONArray jsonArray = innerJsonObject.getJSONArray("libri");
            libri = new Libro[jsonArray.length()];
            int index = 0;
            for (int x=0; x< jsonArray.length(); x++) {
                Libro libro = new Libro();
                JSONObject rec = jsonArray.getJSONObject(x);
                libro.setGenere(rec.getString("genere"));
                libro.setTitolo(rec.getString("titolo"));
                libro.setAutore(rec.getString("autore"));
                libro.setPrezzo((float) rec.getDouble("prezzo"));

                libri[index++] = libro;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            Toast.makeText(getApplicationContext(),(String)ex.toString(),
                    Toast.LENGTH_SHORT).show();
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(getApplicationContext(),(String)ex.toString() + ex.getLocalizedMessage(),
                    Toast.LENGTH_SHORT).show();
            return null;
        }
        return libri;
    }
}