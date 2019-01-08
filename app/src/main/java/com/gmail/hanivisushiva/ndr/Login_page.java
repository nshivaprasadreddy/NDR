package com.gmail.hanivisushiva.ndr;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.hanivisushiva.ndr.Model.Login.Login;
import com.gmail.hanivisushiva.ndr.Storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_page extends AppCompatActivity {


    EditText email,password;
    TextView link;
    String email_txt,password_text;
    Button login_btn;


    @Override
    protected void onStart() {
        super.onStart();




        if (SharedPrefManager.get_mInstance(this).isLoggedIn()){



            boolean isLoggedIn = SharedPrefManager.get_mInstance(getApplicationContext()).isLoggedIn();

            if (isLoggedIn){
                test_toast("You are LoggedIn");
                Intent intent = new Intent(Login_page.this,Bottom.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        link = findViewById(R.id.link);
        email = findViewById(R.id.email);
        login_btn = findViewById(R.id.login);



        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_page.this,RegisterPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                email_txt = email.getText().toString();




                if (TextUtils.isEmpty(email_txt)) {
                    email.setError("Please enter your Mobile no.");
                    email.requestFocus();
                    return;
                }

                if (email_txt.length() != 10) {
                    email.setError("Mobile no. must be 10 digits");
                    email.requestFocus();
                    return;
                }


                Log.e("test",email_txt+password_text);
                getLogin(email_txt);
            }
        });










    }


    private void getLogin(String s_pass){

        final ProgressDialog progressDialog=new ProgressDialog(Login_page.this);
        progressDialog.setMessage("Logging In,Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Call<Login> loginCall = RetrofitClient.getmInstance().getApi().user_login(s_pass);

        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                Login login = response.body();


                if (login != null) {
                    if ( login.getStatus()){
                        test_toast(login.getMessage());

                        SharedPrefManager.get_mInstance(Login_page.this)
                                .saveUser(login);
                        Intent intent = new Intent(Login_page.this,Otp.class);
                        intent.putExtra("otp",login.getOtp()+"");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);


                    }else {
                        test_toast(login.getMessage());
                    }
                }


                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                test_toast(t.getMessage());
                progressDialog.dismiss();
            }
        });

    }

    private void test_toast(String s){
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }
}
