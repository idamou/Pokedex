package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private PokemonAdapter pokemonsAdapter;
    private String urlAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = findViewById(R.id.gridViewPokemons);
        pokemonsAdapter = new PokemonAdapter(this , new ArrayList<>());
        gridView.setAdapter(pokemonsAdapter);
        urlAPI= "https://pokeapi.co/api/v2/pokemon/";
        fillPokemonsAPI(urlAPI);
    }

    private void fillPokemonsAPI(String sourcedUrl){
        new Thread(()-> {
            OkHttpClient httpClient = new OkHttpClient();
            Request request = new Request.Builder().url(sourcedUrl).build();
            try {
                Response response = httpClient.newCall(request).execute();
                String responseData = response.body().string();

                JSONObject jsonResponse = new JSONObject(responseData);
                urlAPI = jsonResponse.getString("next");
                JSONArray pokemonJSONArray =  jsonResponse.getJSONArray("results");
                pokemonsAdapter.getPokemonList().clear();
                for(int i = 0 ;i <pokemonJSONArray.length(); i++){
                    JSONObject pokemonObject = pokemonJSONArray.getJSONObject(i);
                    String name = pokemonObject.getString("name");
                    String url =  pokemonObject.getString("url");

                    request = new Request.Builder().url(url).build();
                    response = httpClient.newCall(request).execute();
                    responseData = response.body().string();

                    jsonResponse = new JSONObject(responseData);
                    String imageUrl = jsonResponse
                            .getJSONObject("sprites")
                            .getJSONObject("other")
                            .getJSONObject("official-artwork")
                            .getString("front_default");

                    float weight = Float.parseFloat(jsonResponse.getString("weight"));
                    float height = Float.parseFloat(jsonResponse.getString("height"));
                    float xp = Float.parseFloat(jsonResponse.getString("base_experience"));
                    JSONArray statsJSONArray = jsonResponse.getJSONArray("stats");
                    int hp = 0;
                    int attack = 0;
                    int defense = 0;
                    int speed = 0;
                    for (int j = 0; j <statsJSONArray.length(); j++ ){
                        JSONObject statsObject = statsJSONArray.getJSONObject(j);
                        switch (statsObject.getJSONObject("stat").getString("name")){
                            case "hp":
                                hp = Integer.parseInt(statsObject.getString("base_stat"));
                                break;
                            case "attack":
                                attack = Integer.parseInt(statsObject.getString("base_stat"));
                                break;
                            case "defense":
                                defense = Integer.parseInt(statsObject.getString("base_stat"));
                                break;
                            case "speed":
                                speed = Integer.parseInt(statsObject.getString("base_stat"));
                                break;
                        }
                    }
                    pokemonsAdapter.getPokemonList().add(new Pokemon( name, imageUrl, weight,  height,  hp,  speed,  attack,  defense, xp));
                    runOnUiThread(()->{
                        pokemonsAdapter.notifyDataSetChanged();
                    });
                }
            }catch (JSONException | IOException e){
                e.printStackTrace();
            }
        }).start();
    }
}