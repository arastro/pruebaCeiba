package co.com.ceiba.mobile.pruebadeingreso.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.adapter.UserAdapter;
import co.com.ceiba.mobile.pruebadeingreso.models.User;
import co.com.ceiba.mobile.pruebadeingreso.reposervice.ApiService;
import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints;
import co.com.ceiba.mobile.pruebadeingreso.utils.InternetConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
    }


    @Override
    protected void onStart() {
        super.onStart();
    }


    protected  void loadData(){
        if(InternetConnection.checkConnection(getApplicationContext())){

            final ProgressDialog dialog;

            /**
             * Progress Dialog for User Interaction
             */
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setTitle("Getting JSON data");
            dialog.setMessage("Please wait...");
            dialog.show();

            ApiService api = Endpoints.getApiService();

            Call<ArrayList<User>> call = api.getUsers();

            call.enqueue(new Callback<ArrayList<User>>() {
                @Override
                public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                    dialog.dismiss();

                    if(response.isSuccessful()){
                        ArrayList<User> users = response.body();

                        RecyclerView recyclerUsers =(RecyclerView)findViewById(R.id.recyclerViewSearchResults);
                        UserAdapter adapter        = new UserAdapter(users, MainActivity.this);
                        recyclerUsers.setAdapter(adapter);
                        recyclerUsers.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    }else{
                        Snackbar.make(findViewById(R.id.layout_main), "Something going wrong!", Snackbar.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                    dialog.dismiss();
                    Log.e("ERROR_LLAMADA",t.getMessage());
                }
            });

        }else {
            Snackbar.make(findViewById(R.id.layout_main), "Check your internet connection!", Snackbar.LENGTH_LONG).show();
        }
    }


}