package com.example.pokedex;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PokemonAdapter extends BaseAdapter {
    private Context context;
    private List<Pokemon> pokemonList;

    public PokemonAdapter(Context context, List<Pokemon> pokemonList) {
        this.context = context;
        this.pokemonList =   pokemonList;
    }

    @Override
    public int getCount(){return pokemonList.size();}

    @Override
    public Object getItem(int i){return pokemonList.get(i);}

    @Override
    public long getItemId(int i){return i;}

    @Override
    public View getView(int i , View view, ViewGroup viewGroup){
        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.grid_item, null);
        }
        ImageView imageView = view.findViewById(R.id.pokemonImg);
        TextView textView = view.findViewById(R.id.pokemonTxt);


        Pokemon pokemon = pokemonList.get(i);
        LinearLayout linearLayout = view.findViewById(R.id.gridItemPokemon);
        Glide.with(imageView.getContext()).load(pokemon.getImgUrl()).into(imageView);

        linearLayout.setOnClickListener(v -> {
            Intent intent = new Intent(context , PokemonActivity.class);
            intent.putExtra("pokemon", pokemon);
            context.startActivity(intent);
        });
        return view;
    }

    public  List<Pokemon> getPokemonList(){return pokemonList;}
}
