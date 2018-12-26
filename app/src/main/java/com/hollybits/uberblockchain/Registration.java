package com.hollybits.uberblockchain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hollybits.uberblockchain.model.RegistrationRequest;
import com.hollybits.uberblockchain.model.RegistrationResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity {

    @BindView(R.id.userNameEditText)
    EditText userNameEditText;

    @BindView(R.id.emailEditText)
    EditText emailEditText;

    @BindView(R.id.passwordEditText)
    EditText passwordEditText;

    @BindView(R.id.signUpButton)
    Button signUpButton;

    @BindView(R.id.moveToSignInActivity)
    TextView moveToSignInActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

        moveToSignInActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Registration.this, MainActivity.class);
                Registration.this.startActivity(myIntent);
            }
        });


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailEditText.setText("");
                userNameEditText.setText("");
                passwordEditText.setText("");
                RegistrationRequest registrationRequest = new RegistrationRequest();
                registrationRequest.setEmail(emailEditText.getText().toString());
                registrationRequest.setName(userNameEditText.getText().toString());
                registrationRequest.setPassword(passwordEditText.getText().toString());
                MainApplication.getServerRequests().registerNewUser(registrationRequest).enqueue(new Callback<RegistrationResponse>() {
                    @Override
                    public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                        RegistrationResponse res = response.body();
                        if (res != null){
                            Paper.book().write("UserID", res.getId());
                            System.err.println("here");
                            System.err.println("message ---->   " + res.getMessage());
                            System.err.println("is success ---->  " + res.isSuccess());
                            System.err.println("id ----> " + res.getId());

                            Intent myIntent = new Intent(Registration.this, MainAccount.class);
                            Registration.this.startActivity(myIntent);
                        }

                    }

                    @Override
                    public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });

    }
}

