package com.hollybits.uberblockchain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hollybits.uberblockchain.model.Coordinates;
import com.hollybits.uberblockchain.model.ParcelCreation;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MakeOrderActivity extends AppCompatActivity {

    @BindView(R.id.fromLongitudeEditText)
    EditText fromLongitudeEditText;

    @BindView(R.id.fromLatitudeEditText)
    EditText fromLatitudeEditText;

    @BindView(R.id.toLongitudeEditText)
    EditText toLongitudeEditText;

    @BindView(R.id.toLatitudeEditText)
    EditText toLatitudeEditText;

    @BindView(R.id.priceEditText)
    EditText priceEditText;

    @BindView(R.id.toUserEditText)
    EditText email;

    @BindView(R.id.descriptionEditText)
    EditText descriptionEditText;

    @BindView(R.id.backToAccount)
    Button backToAccount;

    @BindView(R.id.SendButton)
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);
        ButterKnife.bind(this);

        backToAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MakeOrderActivity.this, MainAccount.class);
                MakeOrderActivity.this.startActivity(myIntent);
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long fromId = Paper.book().read(MainApplication.USER_ID);
                Coordinates from = new Coordinates(Double.parseDouble(fromLatitudeEditText.getText().toString()),
                        Double.parseDouble(fromLongitudeEditText.getText().toString()));
                Coordinates to = new Coordinates(Double.parseDouble(toLatitudeEditText.getText().toString()),
                        Double.parseDouble(toLongitudeEditText.getText().toString()));

                MainApplication.getServerRequests().createNewParcel(new ParcelCreation(fromId,
                        email.getText().toString(),
                        from,
                        to,
                        Double.parseDouble(priceEditText.getText().toString()),
                        descriptionEditText.getText().toString()
                )).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Intent myIntent = new Intent(MakeOrderActivity.this, MainAccount.class);
                        MakeOrderActivity.this.startActivity(myIntent);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });


    }
}
