package co.com.ceiba.mobile.pruebadeingreso.holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import co.com.ceiba.mobile.pruebadeingreso.R;

public class UserHolder extends RecyclerView.ViewHolder {

    public CardView cardUser;
    public TextView txtName;
    public TextView txtPhone;
    public TextView txtEmail;

    public UserHolder(View itemView) {
        super(itemView);
        cardUser = itemView.findViewById(R.id.card_view_user);
        txtName  = itemView.findViewById(R.id.name);
        txtPhone = itemView.findViewById(R.id.phone);
        txtEmail = itemView.findViewById(R.id.email);

    }
}
