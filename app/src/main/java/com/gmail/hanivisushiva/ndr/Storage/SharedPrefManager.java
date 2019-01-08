package com.gmail.hanivisushiva.ndr.Storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.gmail.hanivisushiva.ndr.Model.Login.Login;
import com.gmail.hanivisushiva.ndr.Model.Register.Data;
import com.gmail.hanivisushiva.ndr.Model.Register.Register;
import com.gmail.hanivisushiva.ndr.Model.Update.Update;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "my_shared_name";

    private static SharedPrefManager mInstance;
    private Context mCtx;


    private SharedPrefManager(Context mCtx){
        this.mCtx = mCtx;
    }

    public static  synchronized  SharedPrefManager get_mInstance(Context mCtx){
        if (mInstance == null){
            mInstance = new SharedPrefManager(mCtx);

        }
        return mInstance;
    }

    public void saveUser(Login login){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("id",login.getData().getTxtId());
        editor.putString("name",login.getData().getTxtName());
        editor.putString("mobile",login.getData().getTxtMobile());
        editor.putString("password",login.getData().getTxtPassword());
        editor.putString("constituency",login.getData().getTxtConstituency());


        editor.apply();
        editor.commit();

    }


    public void saveRegUser(Register login){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("id",login.getData().getTxtId());
        editor.putString("name",login.getData().getTxtName());
        editor.putString("mobile",login.getData().getTxtMobile());
        editor.putString("password",login.getData().getTxtPassword());
        editor.putString("constituency",login.getData().getTxtConstituency());


        editor.apply();
        editor.commit();

    }



    public void saveUpdateUser(Update login){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("id",login.getData().getTxtId());
        editor.putString("name",login.getData().getTxtName());
        editor.putString("mobile",login.getData().getTxtMobile());
        editor.putString("password",login.getData().getTxtPassword());
        editor.putString("constituency",login.getData().getTxtConstituency());
        editor.putBoolean("login_status",true);


        editor.apply();
        editor.commit();

    }


    public void set_status(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("login_status",true);

        editor.apply();
        editor.commit();

    }


    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("login_status",false) == true;

    }


    public String getName(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("name","sandy000");

    }

    public String getMobile(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("mobile","9010805692");

    }
    public String getBatch(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("constituency","B1");

    }

    public String getSid(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("id","980498");

    }



    /*

    public String getAdmissionId(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("sid","1");

    }


    public String getSid(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("id","1");

    }
    public String getImage(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("image","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT3Ehdqrk7hounfPw7CG3wax5PEIzCN0pdC1QJo2Ro7vj_PsOBx1w");

    }

    public String getName(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("name","sandy000");

    }

    public String getMobile(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("mobile","9010805692");

    }
    public String getBatch(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("batch","B1");

    }
    public String getPassword(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("password","B1");

    }
    public String getEmail(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("email","");

    }


  /*  public Login getUser(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
          return new Login(
                  sharedPreferences.getString("email",null),
                  sharedPreferences.getString("password",null),
                  sharedPreferences.getString("uid",null),
                  sharedPreferences.getBoolean("status",false)

          );
    }

    */

    public void clear(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
