package co.com.ceiba.mobile.pruebadeingreso.rest;

import co.com.ceiba.mobile.pruebadeingreso.reposervice.ApiService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Endpoints {

    /********
     * URLS
     *******/
    public static final String URL_BASE = "https://jsonplaceholder.typicode.com";
    public static final String GET_USERS = "/users";
    public static final String GET_POST_USER = "/posts?";


    /**
     * Get Retrofit Instance
     */
    private static Retrofit getRetrofitInstance() {

        return new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}
