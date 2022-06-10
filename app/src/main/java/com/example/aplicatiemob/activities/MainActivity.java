package com.example.aplicatiemob.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.aplicatiemob.R;
import com.example.aplicatiemob.api.RetrofitClient;
import com.example.aplicatiemob.databinding.ActivityMainBinding;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    class MyStatus{
        String message;
    }

    private EditText editTextUsername, editTextPassword, editTextSysId;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int[] imageId = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j, R.drawable.k, R.drawable.l, R.drawable.m, R.drawable.n, R.drawable.o};
        String[] name = {"Lexi", "Mierlu", "Tim", "Albu", "Negro", "Rexi", "Fifi", "Lordut", "Rex", "Max", "Fiona", "Timoftel", "Maya", "Jimmy", "DJ"};
        String[] msg = {"","","","","","","","","","","","","","",""};

        ArrayList<Pet> petArrayList = new ArrayList<>();
        for(int i = 0; i < imageId.length; i++){
            Pet pet = new Pet(name[i],msg[i],imageId[i]);
            petArrayList.add(pet);
        }

        ListAdapter listAdapter = new ListAdapter(MainActivity.this,petArrayList);

        binding.listview.setAdapter(listAdapter);


    }

    private void edit(){
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String testSysId = editTextSysId.getText().toString().trim();

        if(username.isEmpty()){
            editTextUsername.setError("Username is required");
            editTextUsername.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            editTextPassword.setError("Password should be atleast 6 character long");
            editTextPassword.requestFocus();
            return;
        }

        if(testSysId.isEmpty()){
            editTextSysId.setError("System ID is required");
            editTextSysId.requestFocus();
            return;
        }

        int sysId = Integer.parseInt(editTextSysId.getText().toString().trim());
        progressBar.setVisibility(View.VISIBLE);

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .getAllPets(username);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()){
                    try {
                        progressBar.setVisibility(View.GONE);
                        String Er = response.errorBody().string();
                        Gson gsonEr = new Gson();
                        MyStatus statusEr = gsonEr.fromJson(Er, MyStatus.class);
                        Toast.makeText(MainActivity.this, statusEr.message, Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                try {
                    String s = response.body().string();
                    Gson gson = new Gson();
                    MyStatus status = gson.fromJson(s, MyStatus.class);
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, status.message, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
