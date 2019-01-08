package com.gmail.hanivisushiva.ndr;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.hanivisushiva.ndr.Model.Constituency.Constituency;
import com.gmail.hanivisushiva.ndr.Model.Constituency.Datum;
import com.gmail.hanivisushiva.ndr.Model.Register.Register;
import com.gmail.hanivisushiva.ndr.Storage.SharedPrefManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPage extends AppCompatActivity {

    EditText name,mobile,constituency;
    Spinner password;
    Button register;
    String password_txt,name_txt,mobile_txt,constituency_txt;
    TextView link;
    ArrayList<Datum> constituencyArrayList = new ArrayList<>();
    ArrayList<String> country = new ArrayList<>();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        password = findViewById(R.id.password);
        name = findViewById(R.id.name);
        mobile = findViewById(R.id.phone);
        register = findViewById(R.id.register);
        link = findViewById(R.id.link);
        get_Constituency();









        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterPage.this,Login_page.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                name_txt = name.getText().toString();
                mobile_txt = mobile.getText().toString();
                constituency_txt = password.getSelectedItem().toString();



                if (TextUtils.isEmpty(name_txt)) {
                    name.setError("Enter a your name");
                    name.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(mobile_txt)) {
                    mobile.setError("Enter a mobile no.");
                    mobile.requestFocus();
                    return;
                }


                if (mobile_txt.length() != 10) {
                    mobile.setError("Mobile no. must be 10 digits");
                    mobile.requestFocus();
                    return;
                }






             register(name_txt,mobile_txt,constituency_txt);




            }
        });
    }




    private void get_Constituency(){
        progressDialog=new ProgressDialog(RegisterPage.this);
        progressDialog.setMessage("Loading,Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Call<Constituency> constituencyCall = RetrofitClient.getmInstance().getApi().get_constituency();

        constituencyCall.enqueue(new Callback<Constituency>() {
            @Override
            public void onResponse(Call<Constituency> call, Response<Constituency> response) {
                Constituency constituency_res = response.body();

                assert constituency_res != null;
                for (int i = 0; i < constituency_res.getData().size(); i++){
                     country.add(constituency_res.getData().get(i).getTxtConstituency());
                 }


                Log.e("constituency size",constituencyArrayList.toString()+"");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterPage.this,android.R.layout.simple_dropdown_item_1line,country);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                password.setAdapter(adapter);

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Constituency> call, Throwable t) {

                progressDialog.dismiss();

            }
        });
    }



    private void register(String s_name,String s_mobile,String s_constituency){

        Call<Register> registerCall = RetrofitClient.getmInstance().getApi().user_register(s_mobile,s_name,s_constituency);

        registerCall.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                Register register = response.body();

                assert register != null;
                if (register.getStatus()){
                    test_toast(register.getMessage());

                    SharedPrefManager.get_mInstance(RegisterPage.this)
                            .saveRegUser(register);

                    Intent intent = new Intent(RegisterPage.this,Otp.class);
                    intent.putExtra("otp",register.getOtp()+"");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else {
                    test_toast(register.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                test_toast(t.getMessage());
            }
        });

    }

    private void test_toast(String s){
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }
}
