package com.popcode.madnilo.starwiki.retrofit;

import com.popcode.madnilo.starwiki.service.PeopleService;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Danilo Lima on 31/05/2017.
 *
 */

public class SWAPI {
    private Retrofit retrofit;

    public SWAPI(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://swapi.co/api/people/?page=")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public PeopleService getPeopleService(){
        return retrofit.create(PeopleService.class);
    }
}
