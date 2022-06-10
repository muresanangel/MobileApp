package com.example.aplicatiemob.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.aplicatiemob.R;
import com.example.aplicatiemob.api.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    class MyStatus{
        String message;
    }

    private EditText editTextUsername;
    private EditText editTextPassword;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = findViewById(R.id.progressBar2);
        editTextUsername = findViewById(R.id.usernameLogin);
        editTextPassword = findViewById(R.id.passwordLogin);

        findViewById(R.id.buttonLogin).setOnClickListener(this);
    }

    private void userLogin(){

        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(username.isEmpty()){
            editTextUsername.setError("Email is required");
            editTextUsername.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .userLogin(username, password);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String s = null;
                if (!response.isSuccessful()){
                    try {
                        progressBar.setVisibility(View.GONE);
                        String Er = response.errorBody().string();
                        Gson gsonEr = new Gson();
                        MyStatus statusEr = gsonEr.fromJson(Er, MyStatus.class);
                        Toast.makeText(LoginActivity.this, statusEr.message, Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                try {
                    s = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (s!=null) {

                    try {
                        JSONObject jsonobject = new JSONObject(s);
                        String acc_token = jsonobject.getString("access_token");
                        Log.i("da", acc_token);
                        //                String acc_token = response.body();
//                String cookie = testare.get(1);
//                String csrf = cookie.substring(18);
//                csrf = csrf.substring(0, csrf.length() - 8);
//
//                String accCookie = testare.get(0);
                        //String accToken = accCookie.substring(20);
                        //accToken = accToken.substring(0, accToken.length() - 18);

                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this, "Logare realizata cu succes!", Toast.LENGTH_LONG).show();

                        Intent i = new Intent(LoginActivity.this,SystActivity.class);
                        Bundle extras = new Bundle();
//                extras.putString("csrf", csrf);
                        extras.putString("accToken", acc_token);
                        i.putExtras(extras);
                        startActivity(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
//                List<String> header_token = response.headers().values("access_token");


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonLogin:
                userLogin();
                break;
//            case R.id.textViewReg:
//                startActivity(new Intent(this,MainActivity.class));
//                break;
        }
    }
}
