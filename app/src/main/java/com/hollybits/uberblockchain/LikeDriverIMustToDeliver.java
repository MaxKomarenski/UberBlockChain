package com.hollybits.uberblockchain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.hollybits.uberblockchain.adapter.MustToDeliverDriverAdapter;
import com.hollybits.uberblockchain.adapter.OrderAdapter;
import com.hollybits.uberblockchain.model.Order;
import com.hollybits.uberblockchain.model.Parcel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LikeDriverIMustToDeliver extends AppCompatActivity {

    @BindView(R.id.myOrderRecyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.backToAccount)
    Button backToAccount;

    private ArrayList<Parcel> parcels;
    private MustToDeliverDriverAdapter myParcelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_send_parsels);
        ButterKnife.bind(this);

        parcels = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        backToAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(LikeDriverIMustToDeliver.this, MainAccount.class);
                LikeDriverIMustToDeliver.this.startActivity(myIntent);
            }
        });


        Long id = Paper.book().read(MainApplication.USER_ID);
        MainApplication.getServerRequests().getParcelToDeliver(id).enqueue(new Callback<List<Parcel>>() {
            @Override
            public void onResponse(Call<List<Parcel>> call, Response<List<Parcel>> response) {
                if (response.body() != null){
                    parcels.clear();
                    parcels.addAll(response.body());
                    myParcelAdapter = new MustToDeliverDriverAdapter(parcels);
                    recyclerView.setAdapter(myParcelAdapter);
                    myParcelAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<Parcel>> call, Throwable t) {

            }
        });
    }
}
