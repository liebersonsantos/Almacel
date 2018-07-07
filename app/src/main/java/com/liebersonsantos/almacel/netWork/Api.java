package com.liebersonsantos.almacel.netWork;

import com.liebersonsantos.almacel.model.IpResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET()
    Call<IpResponse> getiP(@Query("format") String format);
}
