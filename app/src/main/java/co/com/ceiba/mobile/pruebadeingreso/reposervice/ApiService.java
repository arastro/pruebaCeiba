package co.com.ceiba.mobile.pruebadeingreso.reposervice;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.models.User;
import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET(Endpoints.GET_USERS)
    Call<ArrayList<User>>getUsers();

}
