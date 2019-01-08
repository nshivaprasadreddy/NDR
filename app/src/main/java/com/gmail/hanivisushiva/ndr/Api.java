package com.gmail.hanivisushiva.ndr;


import com.gmail.hanivisushiva.ndr.Model.Constituency.Constituency;
import com.gmail.hanivisushiva.ndr.Model.Event.Events;
import com.gmail.hanivisushiva.ndr.Model.Login.Login;
import com.gmail.hanivisushiva.ndr.Model.Post.Posts;
import com.gmail.hanivisushiva.ndr.Model.Register.Register;
import com.gmail.hanivisushiva.ndr.Model.Update.Update;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface Api {


    //@GET("plots.php")
    //Call<Data> get_all();

    @GET("get_posts.php")
    Call<Posts> get_posts();

    @GET("get_constituency.php")
    Call<Constituency> get_constituency();

    @GET("get_events.php")
    Call<Events> get_events();


    @GET("get_trending_posts.php")
    Call<Posts> get_trending_posts();

    @GET("get_trending_events.php")
    Call<Events> get_trending_events();


    @FormUrlEncoded
    @POST("get_events_date.php")
    Call<Events> get_event_date(
            @Field("target_date") String date
    );


    @FormUrlEncoded
    @POST("request_login.php")
    Call<Login> user_login(
            @Field("txt_mobile") String mobile
    );


    @FormUrlEncoded
    @POST("update_user.php ")
    Call<Update> user_update(
            @Field("txt_name") String name,
            @Field("txt_constituency") String constituency,
            @Field("target_id") String target_id


    );


    @FormUrlEncoded
    @POST("user_registration.php")
    Call<Register> user_register(
            @Field("txt_mobile") String mobile,
            @Field("txt_name") String name,
            @Field("txt_constituency") String constituency
    );





   /* @FormUrlEncoded
    @POST("allplots.php")
    Call<Data> get_all(
            @Field("pid") String id
    );

    get_trending_posts.php

    @FormUrlEncoded
    @POST("login.php")
    Call<SignIn> user_login(
            @Field("email") String email,
            @Field("password") String password
    );



    @FormUrlEncoded
    @POST("dcompany.php")
    Call<DCompany> get_Child(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("project.php")
    Call<Project> get_projects(
            @Field("did") String did,
            @Field("pid") String pid
    );


    @FormUrlEncoded
    @POST("allprojects.php")
    Call<Project> get_all_projects(
            @Field("pid") String pid
    );

    @FormUrlEncoded
    @POST("book.php")
    Call<Book> book_plot(
            @Field("position") String position,
            @Field("pid") String pid,
            @Field("plot_no") String plot_no
    );

    */



}
