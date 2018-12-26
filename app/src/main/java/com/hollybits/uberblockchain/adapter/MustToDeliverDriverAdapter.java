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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MustToDeliverDriverAdapter extends RecyclerView.Adapter<MustToDeliverDriverAdapter.MyViewHolder> {

    private List<Parcel> orders;


    public MustToDeliverDriverAdapter(List<Parcel> orders) {
        this.orders = orders;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView from, to, price, status;
        Button deliverOrderButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            from = itemView.findViewById(R.id.lonTextView);
            to = itemView.findViewById(R.id.latTextView);
            price = itemView.findViewById(R.id.priceTextView);
            status = itemView.findViewById(R.id.status);
            deliverOrderButton = itemView.findViewById(R.id.deliver_order);
        }
    }

    @NonNull
    @Override
    public MustToDeliverDriverAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.raw_of_order_item_not_driver, viewGroup,
                false);

        return new MustToDeliverDriverAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final Parcel parcel = orders.get(i);

        myViewHolder.price.setText(Double.valueOf(parcel.getPrice()).toString() + " $");
        myViewHolder.to.setText("From: " + parcel.getAddressFrom());
        myViewHolder.from.setText("To: " + parcel.getAddressTo());
        myViewHolder.status.setText("Status: " + parcel.getStatus());

        myViewHolder.deliverOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainApplication.getServerRequests().deliverOrder(parcel.getId()).enqueue(new Callback<Void>() {
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
