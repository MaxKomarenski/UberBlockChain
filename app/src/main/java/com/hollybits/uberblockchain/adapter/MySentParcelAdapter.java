package com.hollybits.uberblockchain.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hollybits.uberblockchain.MainApplication;
import com.hollybits.uberblockchain.R;
import com.hollybits.uberblockchain.model.Parcel;

import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MySentParcelAdapter extends RecyclerView.Adapter<MySentParcelAdapter.MyViewHolder> {

    private List<Parcel> orders;


    public MySentParcelAdapter(List<Parcel> orders) {
        this.orders = orders;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView from, to, price, state;
        Button pickUpButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            from = itemView.findViewById(R.id.lonTextView);
            to = itemView.findViewById(R.id.latTextView);
            price = itemView.findViewById(R.id.priceTextView);
            state = itemView.findViewById(R.id.status);
        }
    }

    @NonNull
    @Override
    public MySentParcelAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.raw_just_item, viewGroup,
                false);

        return new MySentParcelAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final Parcel parcel = orders.get(i);

        myViewHolder.price.setText(Double.valueOf(parcel.getPrice()).toString() + " $");
        myViewHolder.from.setText("From: " + parcel.getAddressFrom());
        myViewHolder.to.setText("To: " + parcel.getAddressTo());

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
