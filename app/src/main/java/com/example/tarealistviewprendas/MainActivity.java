package com.example.tarealistviewprendas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tarealistviewprendas.WebServices.Asynchtask;
import com.example.tarealistviewprendas.WebServices.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://fakestoreapi.com/products",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {

        ListView lstListaPrendas = (ListView) findViewById(R.id.lstListaPrendas);


        JSONArray JSONlistaPrendas= new JSONArray(result);

        ArrayList<Prendas> lstPrendas = Prendas.JsonObjectsBuild(JSONlistaPrendas);
        AdaptadorPrendas adaptorPrenda = new AdaptadorPrendas(this, lstPrendas);
        lstListaPrendas.setAdapter(adaptorPrenda);

        lstListaPrendas.setAdapter(adaptorPrenda);

        lstListaPrendas.setOnItemClickListener((parent, view, position, id) -> {
            Prendas p = lstPrendas.get(position);
            Intent i = new Intent(MainActivity.this, ProductoSeleccionado.class);
            i.putExtra("title",      p.getTitulo());
            i.putExtra("price",      p.getPrecio());
            i.putExtra("description",p.getDescripcion());
            i.putExtra("category",   p.getCategoria());
            i.putExtra("imageUrl",   p.getUrlimagen());
            startActivity(i);
        });
    }
}