package com.popcode.madnilo.starwiki.service;

import com.popcode.madnilo.starwiki.model.People;
import com.popcode.madnilo.starwiki.model.SWFAPIResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Danilo Lima on 31/05/2017.
 *
 */
public interface PeopleService {

    @POST("/favorite/{id}")
    Call<SWFAPIResponse> favorite(@Path("id") int id);

    @GET("/{page}")
    Call<List<People>> downloadList(@Path("page") int page);

}
