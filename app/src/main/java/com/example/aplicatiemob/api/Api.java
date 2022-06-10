package com.example.aplicatiemob.api;

import com.example.aplicatiemob.models.LoginResponse;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface Api {

    @POST("add_admin")
    Call<ResponseBody> addAdmin(
            @Header("Authorization") String accToken,
            @Header("email") String username,
            @Header("password") String password,
            @Header("admin") boolean is_admin
    );

    @POST("login")
    Call<ResponseBody> userLogin(
            @Header("email") String email,
            @Header("password") String password
    );

    @POST("logout")
    Call<ResponseBody> userLogout(@Header("X-CSRF-TOKEN") String csrf,
                                  @Header("Cookie") String accToken);

    @GET("get_admin")
    Call<ResponseBody> getAdmin(@Header("Authorization") String accToken,
                                      @Header("id") int id);

    @POST("addPet")
    Call<ResponseBody> addPet(
            @Header("Authorization") String accToken,
            @Header("name") String name,
            @Header("age") int age,
            @Header("sex") String sex,
            @Header("neuter") boolean neuter,
            @Header("story") String story,
            @Header("remarks") String is_admin,
            @Field("img") String img
    );

    @PUT("edit_pet")
    Call<ResponseBody> editPet(
            @Header("Authorization") String accToken,
            @Header("name") String name,
            @Header("age") int age,
            @Header("sex") String sex,
            @Header("neuter") boolean neuter,
            @Header("story") String story,
            @Header("remarks") String is_admin,
            @Field("img") String img
    );

    @GET("get_pet")
    Call<ResponseBody> getPet(@Header("Authorization") String accToken,
                                @Header("id") int id);

    @GET("get_all_pets")
    Call<ResponseBody> getAllPets(@Header("Authorization") String accToken);


    @DELETE("delete_pet")
    Call<ResponseBody> deletePet(@Header("Authorization") String accToken,
                                  @Header("id") int id);

    @DELETE("delete_admin")
    Call<ResponseBody> deleteAdmin(@Header("Authorization") String accToken,
                                 @Header("id") int id);

    @PUT("edit_admin")
    Call<ResponseBody> editAdmin(
            @Header("Authorization") String accToken,
            @Header("email") String username,
            @Header("password") String password,
            @Header("admin") boolean is_admin
    );

    @GET("get_all_admins")
    Call<ResponseBody> getAllAdmins(@Header("Authorization") String accToken);

}
