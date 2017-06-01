package com.popcode.madnilo.starwiki.retrofit;

import com.popcode.madnilo.starwiki.service.PeopleService;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Danilo Lima on 31/05/2017.
 *
 * A classe ainda não pôde ser utilizada.
 */


public class SWAPI {
    private final Retrofit retrofit;

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
