package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PokemonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);
        Pokemon pokemon = (Pokemon) getIntent().getSerializableExtra("pokemon");
        ImageView imageView =findViewById(R.id.pokemonActivityImageView);
        Glide.with(imageView.getContext())
                .load(pokemon.getImgUrl()).into(imageView);
        ((TextView) findViewById(R.id.pokemonActivityTextViewName)).setText(pokemon.getNom());
        ((TextView) findViewById(R.id.pokemonActivityTextViewWeight)).setText(pokemon.getWeight()+"KG");
        ((TextView) findViewById(R.id.pokemonActivityTextViewHeight)).setText(pokemon.getHeight()+"M");
        ((ProgressBar) findViewById(R.id.progressBarHP)).setProgress(pokemon.getHp());
        ((ProgressBar) findViewById(R.id.progressBarAttack)).setProgress(pokemon.getAttack());
        ((ProgressBar) findViewById(R.id.progressBarDefense)).setProgress(pokemon.getDefense());
        ((ProgressBar) findViewById(R.id.progressBarSpeed)).setProgress(pokemon.getSpeed());
        ((ProgressBar) findViewById(R.id.progressBarXP)).setProgress((int) pokemon.getXp());
    }
}