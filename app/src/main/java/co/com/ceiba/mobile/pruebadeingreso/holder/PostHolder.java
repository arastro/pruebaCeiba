package co.com.ceiba.mobile.pruebadeingreso.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import co.com.ceiba.mobile.pruebadeingreso.R;

public class PostHolder extends RecyclerView.ViewHolder   {

    public TextView txtTitle;
    public TextView txtBody;

    public PostHolder(View itemView){
        super(itemView);
        txtTitle  = itemView.findViewById(R.id.titlePosts);
        txtBody = itemView.findViewById(R.id.body);
    }

}
