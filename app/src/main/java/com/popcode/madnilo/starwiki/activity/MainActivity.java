package com.popcode.madnilo.starwiki.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.popcode.madnilo.starwiki.R;
import com.popcode.madnilo.starwiki.adapter.OnItemClickListener;
import com.popcode.madnilo.starwiki.adapter.PeopleAdapter;
import com.popcode.madnilo.starwiki.model.People;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "RecyclerViewExample";

    private List<People> peopleList;
    private RecyclerView mRecyclerView;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        peopleList = new ArrayList<>();

       String url = "http://swapi.co/api/people/?page=";
        int i = 1;
        while(i<10){
            new DownloadTask().execute(url+i);
            i++;
        }

        /*Call<SWAPIResponse> call = new SWAPI().getPeopleService().getPage(1);
        call.enqueue(new Callback<SWAPIResponse>() {
            @Override
            public void onResponse(Call<SWAPIResponse> call, Response<SWAPIResponse> response) {
                Toast.makeText(MainActivity.this, response.body().getResults() == null ? "eh null" : "nao eh null", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<SWAPIResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Nao foi!!!", Toast.LENGTH_LONG).show();
                Log.e("Fail", "Fail", t);
            }
        });*/
    }


    public class DownloadTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Integer doInBackground(String... params) {
            Integer result = 0;
            HttpURLConnection urlConnection;
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int statusCode = urlConnection.getResponseCode();

                // 200 represents HTTP OK
                if (statusCode == 200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
                    parseResult(response.toString());
                    result = 1; // Successful
                } else {
                    result = 0; //"Failed to fetch data!";
                }
            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result) {
            progressBar.setVisibility(View.GONE);

            if (result == 1) {
                PeopleAdapter adapter = new PeopleAdapter(MainActivity.this, peopleList);
                mRecyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(People person) {
                        Intent showDetailsIntent = new Intent(MainActivity.this, DetailsActivity.class);
                        showDetailsIntent.putExtra("person", person);
                        startActivity(showDetailsIntent);
                    }
                });

            } else {
                Toast.makeText(MainActivity.this, "Failed to fetch data!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void parseResult(String result) {
        try {
            JSONObject response = new JSONObject(result);
            JSONArray peopleJSONArray = response.optJSONArray("results");
            //peopleList = new ArrayList<>();

            for (int i = 0; i < peopleJSONArray.length(); i++) {
                JSONObject object = peopleJSONArray.optJSONObject(i);
                People person = new People();
                person.setName(object.optString("name"));
                person.setHeight(object.optString("height"));
                person.setMass(object.optString("mass"));
                person.setHair_color(object.optString("hair_color"));
                person.setSkin_color(object.optString("skin_color"));
                person.setEye_color(object.optString("eye_color"));
                person.setBirth_year(object.optString("birth_year"));
                person.setGender(object.optString("gender"));
                person.setHomeworld(object.optString("homeworld"));
                peopleList.add(person);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

}
