package co.com.ceiba.mobile.pruebadeingreso.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.adapter.PostAdapter;
import co.com.ceiba.mobile.pruebadeingreso.models.Post;
import co.com.ceiba.mobile.pruebadeingreso.reposervice.ApiService;
import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends Activity {

    TextView txtName,txtPhone,txtEmail;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        userInfo();
        loadPostData();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void userInfo(){
        extras   = getIntent().getExtras();
        txtName  = findViewById(R.id.name);
        txtPhone = findViewById(R.id.phone);
        txtEmail = findViewById(R.id.email);

        txtName.setText(extras.getString("name"));
        txtPhone.setText(extras.getString("phone"));
        txtEmail.setText(extras.getString("email"));

    }

    public void loadPostData(){
        final ProgressDialog dialog;

        /**
         * Progress Dialog for User Interaction
         */
        dialog = new ProgressDialog(PostActivity.this);
        dialog.setTitle("Getting JSON data");
        dialog.setMessage("Please wait...");
        dialog.show();

        Log.i("USERID",extras.getString("userId"));

        ApiService api = Endpoints.getApiService();
        Call<ArrayList<Post>> call = api.getPosts(extras.getString("userId"));

        call.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                dialog.dismiss();

                if(response.isSuccessful()){
                    ArrayList<Post> posts = response.body();

                    RecyclerView recyclerPosts = (RecyclerView) findViewById(R.id.recyclerViewPostsResults);
                    PostAdapter adapter        = new PostAdapter(posts, PostActivity.this);
                    recyclerPosts.setAdapter(adapter);
                    recyclerPosts.setLayoutManager(new LinearLayoutManager(PostActivity.this));

                }else{
                    Snackbar.make(findViewById(R.id.layout_main), "Something going wrong!", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                dialog.dismiss();
                Log.e("ERROR_LLAMADA_2",t.getMessage());
            }
        });
    }

}
