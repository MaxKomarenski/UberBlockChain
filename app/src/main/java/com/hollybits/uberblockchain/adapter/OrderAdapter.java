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
import com.hollybits.uberblockchain.model.Order;
import com.hollybits.uberblockchain.model.Parcel;

import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {

    private List<Parcel> orders;


    public OrderAdapter(List<Parcel> orders) {
        this.orders = orders;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView from, to, price;
        Button pickUpButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            from = itemView.findViewById(R.id.lonTextView);
            to = itemView.findViewById(R.id.latTextView);
            price = itemView.findViewById(R.id.priceTextView);
            pickUpButton = itemView.findViewById(R.id.pickupButton);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.raw_of_order_item, viewGroup,
                    false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final Parcel parcel = orders.get(i);

        myViewHolder.price.setText(Double.valueOf(parcel.getPrice()).toString() + " $");
        myViewHolder.from.setText("From: " + parcel.getAddressFrom().replace(",",",  "));
        myViewHolder.to.setText("To: " + parcel.getAddressTo().replace(",",",  "));

        myViewHolder.pickUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long id = Paper.book().read(MainApplication.USER_ID);
                MainApplication.getServerRequests().becomeCourier(parcel.getId(), id).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        deleteItem(parcel);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void deleteItem(Parcel p){
        orders.remove(p);
        notifyDataSetChanged();
    }
}
