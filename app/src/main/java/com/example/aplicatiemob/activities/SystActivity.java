package com.example.aplicatiemob.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.aplicatiemob.R;
import com.example.aplicatiemob.api.RetrofitClient;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SystActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {



    class MyStatus{
        String message;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syst);

        findViewById(R.id.logOut).setOnClickListener(this);
        findViewById(R.id.buttonAddPet).setOnClickListener(this);
        findViewById(R.id.buttonEditPet).setOnClickListener(this);
        findViewById(R.id.buttonAddAdmin).setOnClickListener(this);
        findViewById(R.id.buttonEditAdmin).setOnClickListener(this);

    }

    private void userLogout(){

        Bundle extras = getIntent().getExtras();
        String accToken = extras.getString("accToken");
        String csrf = extras.getString("csrf");

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .userLogout(csrf,accToken);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()){
                    try {
                        String Er = response.errorBody().string();
                        Gson gsonEr = new Gson();
                        MyStatus statusEr = gsonEr.fromJson(Er, MyStatus.class);
                        Toast.makeText(SystActivity.this, statusEr.message, Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                Toast.makeText(SystActivity.this, "Utilizator delogat!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SystActivity.this,LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(SystActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addPetFunc() {
        Bundle extras = getIntent().getExtras();
        String accToken = extras.getString("accToken");
        String csrf = extras.getString("csrf");

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .getAllPets(accToken);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()){
                    try {
                        String Er1 = response.errorBody().string();
                        Gson gsonEr1 = new Gson();
                        MyStatus statusEr = gsonEr1.fromJson(Er1, MyStatus.class);
                        Toast.makeText(SystActivity.this, statusEr.message, Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                try {
                    String info = response.body().string();
                    Gson gson = new Gson();
                    Toast.makeText(SystActivity.this,"Toast"  , Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(SystActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void editPetFunc() {
        Bundle extras = getIntent().getExtras();
        String accToken = extras.getString("accToken");
        String csrf = extras.getString("csrf");

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .getAllPets(accToken);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()){
                    try {
                        String Er1 = response.errorBody().string();
                        Gson gsonEr1 = new Gson();
                        MyStatus statusEr = gsonEr1.fromJson(Er1, MyStatus.class);
                        Toast.makeText(SystActivity.this, statusEr.message, Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                try {
                    String info = response.body().string();
                    Gson gson = new Gson();
                    Toast.makeText(SystActivity.this,"Toast"  , Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(SystActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.logOut:
                userLogout();
                break;
            case R.id.buttonAddPet:
                break;
            case R.id.buttonAddAdmin:
                break;
            case R.id.buttonEditAdmin:
                break;
            case R.id.buttonEditPet:
                Intent intent = new Intent(SystActivity.this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton s, boolean isChecked) {
        switch (s.getId()){
            case R.id.buttonEditAdmin:
                if(isChecked){
                    Bundle extras = getIntent().getExtras();
                    String accToken = extras.getString("accToken");
                    String csrf = extras.getString("csrf");

                    Call<ResponseBody> call = RetrofitClient
                            .getInstance()
                            .getApi()
                            .getAllPets(accToken);

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(!response.isSuccessful()){
                                Toast.makeText(SystActivity.this, "Eroare", Toast.LENGTH_LONG).show();
                                return;
                            }
                            Toast.makeText(SystActivity.this, "Sistem de securitate activat!", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(SystActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else {
                    Bundle extras = getIntent().getExtras();
                    String accToken = extras.getString("accToken");
                    String csrf = extras.getString("csrf");

                    Call<ResponseBody> call = RetrofitClient
                            .getInstance()
                            .getApi()
                            .getAllPets(accToken);

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(!response.isSuccessful()){
                                Toast.makeText(SystActivity.this, "Eroare", Toast.LENGTH_LONG).show();
                                return;
                            }
                            Toast.makeText(SystActivity.this, "Sistem de securitate dezactivat!", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(SystActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
                break;
            case R.id.buttonAddAdmin:
                if(isChecked){
                    Bundle extras = getIntent().getExtras();
                    String accToken = extras.getString("accToken");
                    String csrf = extras.getString("csrf");

                    Call<ResponseBody> call = RetrofitClient
                            .getInstance()
                            .getApi()
                            .getAllPets(accToken);

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(!response.isSuccessful()){
                                Toast.makeText(SystActivity.this, "Eroare", Toast.LENGTH_LONG).show();
                                return;
                            }
                            Toast.makeText(SystActivity.this, "Sistem de preventie activat!", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(SystActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else {
                    Bundle extras = getIntent().getExtras();
                    String accToken = extras.getString("accToken");
                    String csrf = extras.getString("csrf");

                    Call<ResponseBody> call = RetrofitClient
                            .getInstance()
                            .getApi()
                            .getAllPets(accToken);

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(!response.isSuccessful()){
                                Toast.makeText(SystActivity.this, "Eroare", Toast.LENGTH_LONG).show();
                                return;
                            }
                            Toast.makeText(SystActivity.this, "Sistem de preventie dezactivat!", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(SystActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
                break;
            case R.id.buttonEditPet:
                if(isChecked){
                    Bundle extras = getIntent().getExtras();
                    String accToken = extras.getString("accToken");
                    String csrf = extras.getString("csrf");

                    Call<ResponseBody> call = RetrofitClient
                            .getInstance()
                            .getApi()
                            .getAllPets(accToken);

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(!response.isSuccessful()){
                                Toast.makeText(SystActivity.this, "Eroare", Toast.LENGTH_LONG).show();
                                return;
                            }
                            Toast.makeText(SystActivity.this, "Alarma activata!", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(SystActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else {
                    Bundle extras = getIntent().getExtras();
                    String accToken = extras.getString("accToken");
                    String csrf = extras.getString("csrf");

                    Call<ResponseBody> call = RetrofitClient
                            .getInstance()
                            .getApi()
                            .getAllPets(accToken);

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(!response.isSuccessful()){
                                Toast.makeText(SystActivity.this, "Eroare", Toast.LENGTH_LONG).show();
                                return;
                            }
                            Toast.makeText(SystActivity.this, "Alarma dezactivata!", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(SystActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
                break;
        }
    }
}
