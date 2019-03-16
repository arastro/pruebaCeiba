package co.com.ceiba.mobile.pruebadeingreso.reposervice;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.models.Post;
import co.com.ceiba.mobile.pruebadeingreso.models.User;
import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET(Endpoints.GET_USERS)
    Call<ArrayList<User>>getUsers();

    @GET(Endpoints.GET_POST_USER)
    Call<ArrayList<Post>>getPosts(@Query("userId") String user);

}
