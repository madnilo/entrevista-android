package com.popcode.madnilo.starwiki.service;

import com.popcode.madnilo.starwiki.model.SWAPIResponse;
import com.popcode.madnilo.starwiki.model.FAPIResponse;

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
    Call<FAPIResponse> favorite(@Path("id") int id);

    @GET("{page}")
    Call<SWAPIResponse> getPage(@Path("page") int page);

}
