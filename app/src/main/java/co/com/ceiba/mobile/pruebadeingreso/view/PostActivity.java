package co.com.ceiba.mobile.pruebadeingreso.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import co.com.ceiba.mobile.pruebadeingreso.R;

public class PostActivity extends Activity {

    TextView txtName,txtPhone,txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        userInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void userInfo(){
        txtName  = findViewById(R.id.name);
        txtPhone = findViewById(R.id.phone);
        txtEmail = findViewById(R.id.email);
        Bundle extras = getIntent().getExtras();

        txtName.setText(extras.getString("name"));
        txtPhone.setText(extras.getString("phone"));
        txtEmail.setText(extras.getString("email"));
        Log.i("ENTRO",extras.getString("name"));

    }

}
