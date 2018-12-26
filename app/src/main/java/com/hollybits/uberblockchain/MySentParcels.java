package com.hollybits.uberblockchain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.hollybits.uberblockchain.model.Order;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MySentParcels extends AppCompatActivity {

    @BindView(R.id.myOrderRecyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.backToAccount)
    Button backToAccount;

    private ArrayList<Order> orders;
    //private MyParcelAdapter myParcelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_send_parsels);
        ButterKnife.bind(this);

        orders = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        backToAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MySentParcels.this, MainAccount.class);
                MySentParcels.this.startActivity(myIntent);
            }
        });


//        MainApplication.getServerRequests().getAllParcels().enqueue(new Callback<List<Order>>() {
//            @Override
//            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
//                if (response.body() != null){
//                    orders.clear();
//                    orders.addAll(response.body());
//                    myParcelAdapter = new MyParcelAdapter(orders);
//                    recyclerView.setAdapter(myParcelAdapter);
//                    myParcelAdapter.notifyDataSetChanged();
//
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Order>> call, Throwable t) {
//
//            }
//        });
    }
}
