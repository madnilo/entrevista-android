package com.popcode.madnilo.starwiki;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Danilo Lima on 30/05/2017.
 *
 */

public class DetailsActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        People person = (People) intent.getSerializableExtra("person");

        ImageView img = (ImageView) findViewById(R.id.details_thumbnail);

        TextView details_name = (TextView) findViewById(R.id.details_name);
        TextView details_height = (TextView) findViewById(R.id.details_height);
        TextView details_mass = (TextView) findViewById(R.id.details_mass);
        TextView details_hair_color = (TextView) findViewById(R.id.details_hair_color);
        TextView details_skin_color = (TextView) findViewById(R.id.details_skin_color);
        TextView details_eye_color = (TextView) findViewById(R.id.details_eye_color);
        TextView details_birth_year = (TextView) findViewById(R.id.details_birth_year);
        TextView details_gender = (TextView) findViewById(R.id.details_gender);
        TextView details_homeworld = (TextView) findViewById(R.id.details_homeworld);

        Picasso.with(this).load(person.getThumbnail()).error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder).into(img);
        details_name.setText(person.getName());
        details_height.setText(getString(R.string.details_height, person.getHeight()));
        details_mass.setText(getString(R.string.details_mass, person.getMass()));
        details_hair_color.setText(getString(R.string.details_hair_color, person.getHairColor()));
        details_skin_color.setText(getString(R.string.details_skin_color, person.getSkinColor()));
        details_eye_color.setText(getString(R.string.details_eye_color, person.getEyeColor()));
        details_birth_year.setText(getString(R.string.details_birth_year, person.getBirthYear()));
        details_gender.setText(getString(R.string.details_gender, person.getGender()));
        details_homeworld.setText(getString(R.string.details_homeworld, person.getHomeworld()));

        Button btn = (Button) findViewById(R.id.details_voltar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
