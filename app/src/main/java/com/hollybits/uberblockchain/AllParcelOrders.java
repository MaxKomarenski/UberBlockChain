package com.hollybits.uberblockchain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.hollybits.uberblockchain.adapter.OrderAdapter;
import com.hollybits.uberblockchain.model.Order;
import com.hollybits.uberblockchain.model.Parcel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllParcelOrders extends AppCompatActivity {

    @BindView(R.id.orderRecyclerView)
    RecyclerView orderRecyclerView;

    @BindView(R.id.backToAccount)
    Button backToAccount;

    private ArrayList<Parcel> orders;
    private OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_parcel_orders);
        ButterKnife.bind(this);

        orders = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        orderRecyclerView.setLayoutManager(layoutManager);
        orderRecyclerView.setItemAnimator(new DefaultItemAnimator());

        backToAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(AllParcelOrders.this, MainAccount.class);
                AllParcelOrders.this.startActivity(myIntent);
            }
        });


        MainApplication.getServerRequests().getAllParcels().enqueue(new Callback<List<Parcel>>() {
            @Override
            public void onResponse(Call<List<Parcel>> call, Response<List<Parcel>> response) {
                if (response.body() != null){
                    orders.clear();
                    orders.addAll(response.body());
                    orderAdapter = new OrderAdapter(orders);
                    orderRecyclerView.setAdapter(orderAdapter);
                    orderAdapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onFailure(Call<List<Parcel>> call, Throwable t) {

            }
        });



    }
}
