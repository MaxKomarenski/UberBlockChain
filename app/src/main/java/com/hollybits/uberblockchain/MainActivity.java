package com.hollybits.uberblockchain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hollybits.uberblockchain.model.LoginRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.emailEditText)
    EditText emailEditText;

    @BindView(R.id.passwordEditText)
    EditText passwordEditText;

    @BindView(R.id.signInButton)
    Button signInButton;

    @BindView(R.id.moveToSignUpActivity)
    TextView moveToSignUpActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        moveToSignUpActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, Registration.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainApplication.getServerRequests().login(new LoginRequest(emailEditText.getText().toString(), passwordEditText.getText().toString())).enqueue(new Callback<Long>() {
                    @Override
                    public void onResponse(Call<Long> call, Response<Long> response) {
                        if (response.body() != null){
                            Paper.book().write(MainApplication.USER_ID, response.body());
                            Intent myIntent = new Intent(MainActivity.this, MainAccount.class);
                            MainActivity.this.startActivity(myIntent);
                        }

                    }

                    @Override
                    public void onFailure(Call<Long> call, Throwable t) {

                    }
                });
            }
        });

    }
}
