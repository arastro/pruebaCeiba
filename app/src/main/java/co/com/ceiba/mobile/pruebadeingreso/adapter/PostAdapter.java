package co.com.ceiba.mobile.pruebadeingreso.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.holder.PostHolder;
import co.com.ceiba.mobile.pruebadeingreso.models.Post;
import co.com.ceiba.mobile.pruebadeingreso.models.User;

public class PostAdapter extends RecyclerView.Adapter<PostHolder>{

    List<Post> list = Collections.emptyList();
    Context context;

    public PostAdapter(List<Post> list, Context context){
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list_item,parent,false);

        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        holder.txtTitle.setText(list.get(position).getTitle());
        holder.txtBody.setText(list.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
