package co.com.ceiba.mobile.pruebadeingreso.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.holder.UserHolder;
import co.com.ceiba.mobile.pruebadeingreso.models.User;
import co.com.ceiba.mobile.pruebadeingreso.view.PostActivity;

public class UserAdapter extends RecyclerView.Adapter<UserHolder> {

    List<User> list = Collections.emptyList();
    Context context;
    String name, email, phone;

    public UserAdapter(List<User> list, Context context){
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);

        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        holder.txtName.setText(list.get(position).getName());
        holder.txtPhone.setText(list.get(position).getPhone());
        holder.txtEmail.setText(list.get(position).getEmail());

        name  = list.get(position).getName();
        phone = list.get(position).getPhone();
        email = list.get(position).getEmail();

         holder.btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.i("BOTON_IVAN", "SE ACTIVO EL BOTON");
                Intent i = new Intent(context, PostActivity.class);
                i.putExtra("name", name);
                i.putExtra("phone",phone);
                i.putExtra("email", email);
                view.getContext().startActivity(i);
            }
        });

        
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}
