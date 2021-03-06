package com.popcode.madnilo.starwiki.retrofit;

import com.popcode.madnilo.starwiki.service.PeopleService;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Danilo Lima on 31/05/2017.
 *
 */

public class FAPI {
    private final Retrofit retrofit;

    public FAPI() {
        this.retrofit = new Retrofit.Builder()
                        .baseUrl("https://private-018a01-starwarsfavorites.apiary-mock.com/")
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();
    }

    public PeopleService getPeopleService(){
        return retrofit.create(PeopleService.class);
    }
}
