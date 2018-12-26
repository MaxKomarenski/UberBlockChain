package com.hollybits.uberblockchain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAccount extends AppCompatActivity {

    @BindView(R.id.becomeADriverButton)
    Button becomeADriverButton;

    @BindView(R.id.makeOrderButton)
    Button makeOrderButton;

    @BindView(R.id.myOrdersButton)
    Button LikeDriverIMustToDeliverButton;

    @BindView(R.id.mySendOrdersButton)
    Button mySendParcelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_account);
        ButterKnife.bind(this);

        becomeADriverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainAccount.this, AllParcelOrders.class);
                MainAccount.this.startActivity(myIntent);
            }
        });

        makeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainAccount.this, MakeOrderActivity.class);
                MainAccount.this.startActivity(myIntent);
            }
        });

        LikeDriverIMustToDeliverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainAccount.this, LikeDriverIMustToDeliver.class);
                MainAccount.this.startActivity(myIntent);
            }
        });

        mySendParcelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainAccount.this, MySentParcels.class);
                MainAccount.this.startActivity(myIntent);
            }
        });
    }
}
