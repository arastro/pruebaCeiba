package co.com.ceiba.mobile.pruebadeingreso.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

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

    private ProgressDialog dialog;
    private ArrayList<User> users;
    private EditText txtSearch;
    private RecyclerView recyclerUsers;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadDatalocal();
        txtSearch = findViewById(R.id.editTextSearch);
        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

    protected void filter (String text){
        ArrayList<User> filterList = new ArrayList<>();

        for(User user: users){
            if(user.getName().toLowerCase().contains(text.toLowerCase())){
                filterList.add(user);
            }
        }
        adapter.filterList(filterList);
    }

    protected void loadDatalocal() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("user list", null);
        Type type = new TypeToken<ArrayList<User>>() {}.getType();
        users = gson.fromJson(json, type);

        if (users == null) {
            Log.i("ENTRO1","ENTRO1");
            loadExternalData();
        }else{
            Log.i("ENTRO2","ENTRO2");
            buildRecyclerView(users);
        }
    }

    protected void saveData( ArrayList<User> userList) {
        Log.i("ENTRO3","ENTRO3");
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userList);
        editor.putString("user list", json);
        editor.apply();
    }


    protected  void loadExternalData(){
        if(InternetConnection.checkConnection(getApplicationContext())){
            showDialog();
            ApiService api = Endpoints.getApiService();
            Call<ArrayList<User>> call = api.getUsers();
            call.enqueue(new Callback<ArrayList<User>>() {
                @Override
                public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                    dialog.dismiss();

                    if(response.isSuccessful()){
                        users = response.body();
                        saveData(users);
                        buildRecyclerView(users);
                        Log.i("ENTRO5","ENTRO5");
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

    protected void buildRecyclerView(ArrayList<User> arrayUsers){
        recyclerUsers =(RecyclerView)findViewById(R.id.recyclerViewSearchResults);
        adapter        = new UserAdapter(arrayUsers, MainActivity.this);
        recyclerUsers.setAdapter(adapter);
        recyclerUsers.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        Log.i("ENTRO4","ENTRO4");
    }

    protected void showDialog(){
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setTitle("Getting JSON data");
        dialog.setMessage("Please wait...");
        dialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

}