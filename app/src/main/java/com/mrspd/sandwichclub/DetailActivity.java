package com.mrspd.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.mrspd.sandwichclub.model.Sandwich;
import com.mrspd.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    TextView origin ;

    TextView alsoKnown;

    TextView ingredients;

    TextView placeOfOrigin ;

    TextView description ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        origin = (TextView) findViewById(R.id.origin_tv);

        alsoKnown = (TextView) findViewById(R.id.also_known_tv);

        ingredients = (TextView) findViewById(R.id.ingredients_tv);

        placeOfOrigin = (TextView) findViewById(R.id.place_ofOrigin);

        description = (TextView) findViewById(R.id.description_tv);


        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwwich) {
        origin.setText(sandwwich.getMainName());
        alsoKnown.setText("AlsoKnownS: "+sandwwich.getAlsoKnownAs().toString());
        ingredients.setText("ingredients: "+sandwwich.getIngredients().toString());
        placeOfOrigin.setText("placeOfOrigin: "+sandwwich.getPlaceOfOrigin());
        description.setText("description: "+sandwwich.getDescription());

    }
}
