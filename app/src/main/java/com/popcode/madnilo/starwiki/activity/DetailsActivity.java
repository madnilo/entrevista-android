package com.popcode.madnilo.starwiki.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.popcode.madnilo.starwiki.R;
import com.popcode.madnilo.starwiki.model.FAPIResponse;
import com.popcode.madnilo.starwiki.model.People;
import com.popcode.madnilo.starwiki.model.Planet;
import com.popcode.madnilo.starwiki.retrofit.FAPI;
import com.popcode.madnilo.starwiki.retrofit.SWAPI;
import com.squareup.picasso.Picasso;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Danilo Lima on 30/05/2017.
 */

public class DetailsActivity extends AppCompatActivity {

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
        final TextView details_homeworld = (TextView) findViewById(R.id.details_homeworld);

        Picasso.with(this).load("http://sm.ign.com/ign_br/screenshot/default/darth-vader-6bda9114_h4qs.jpg").error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder).into(img);
        details_name.setText(person.getName());
        details_height.setText(getString(R.string.details_height, person.getHeight()));
        details_mass.setText(getString(R.string.details_mass, person.getMass()));
        details_hair_color.setText(getString(R.string.details_hair_color, person.getHair_color()));
        details_skin_color.setText(getString(R.string.details_skin_color, person.getSkin_color()));
        details_eye_color.setText(getString(R.string.details_eye_color, person.getEye_color()));
        details_birth_year.setText(getString(R.string.details_birth_year, person.getBirth_year()));
        details_gender.setText(getString(R.string.details_gender, person.getGender()));
        details_homeworld.setText(getString(R.string.details_homeworld, person.getHomeworld()));

        int idPlanet = Integer.parseInt(person.getHomeworld().split("/")[person.getHomeworld().split("/").length-1]);

        Log.e("olha ai", String.valueOf(idPlanet));

        Call<Planet> call = new SWAPI().getPeopleService().getPlanet(idPlanet);
        call.enqueue(new Callback<Planet>() {
            @Override
            public void onResponse(Call<Planet> call, Response<Planet> response) {
                //setContentView(R.layout.activity_details);
                //details_homeworld.setText(response.body().getName());
                Toast.makeText(DetailsActivity.this, "Planet: " + response.body().getName(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Planet> call, Throwable t) {
                Toast.makeText(DetailsActivity.this, "Failed to find planet.", Toast.LENGTH_SHORT).show();
            }
        });

        Button btn = (Button) findViewById(R.id.details_voltar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        Call<FAPIResponse> call = new Random().nextBoolean() ? new FAPI().getPeopleService().favorite(1) : new FAPI().getPeopleService().favoriteFail(1);
        call.enqueue(new Callback<FAPIResponse>() {
            @Override
            public void onResponse(Call<FAPIResponse> call, Response<FAPIResponse> response) {
                String message = response.code() == 400 ? "Only at the end do you realize the power of the Dark Side." : response.body().getMessage();
                Toast.makeText(DetailsActivity.this, message, Toast.LENGTH_LONG).show();
                item.setIcon(response.code() == 400 ? R.drawable.ic_star_black_48dp : R.drawable.ic_star_yellow_48dp);
            }

            @Override
            public void onFailure(Call<FAPIResponse> call, Throwable t) {
                Toast.makeText(DetailsActivity.this, "Falha na requisição.", Toast.LENGTH_LONG).show();
            }
        });
        return super.onOptionsItemSelected(item);
    }
}
