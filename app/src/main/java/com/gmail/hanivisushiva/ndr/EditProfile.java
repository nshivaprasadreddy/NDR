package com.gmail.hanivisushiva.ndr;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.gmail.hanivisushiva.ndr.Model.Constituency.Constituency;
import com.gmail.hanivisushiva.ndr.Model.Register.Register;
import com.gmail.hanivisushiva.ndr.Model.Update.Update;
import com.gmail.hanivisushiva.ndr.Storage.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfile extends AppCompatActivity {

    EditText name;

    Spinner constituency;
    Button edit;

    String name_txt,constituency_txt;
    ArrayList<String> country = new ArrayList<>();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }
        get_Constituency();
        name = findViewById(R.id.name);
        edit = findViewById(R.id.edit);
        constituency = findViewById(R.id.constituency);




        name.setText(SharedPrefManager.get_mInstance(getApplicationContext()).getName());
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Log.e("1st",constituency.getSelectedItemPosition()+"");



                name_txt = name.getText().toString();
                constituency_txt = constituency.getSelectedItem().toString();



                if (TextUtils.isEmpty(name_txt)) {
                    name.setError("Please enter your Name");
                    name.requestFocus();
                    return;
                }

                if (constituency.getSelectedItemPosition() != 0){
                    editprofile(name_txt,constituency_txt);
                }else {


                    TextView errorText = (TextView)constituency.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("Select Constituency");

                }






            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }


    private void editprofile(String name,String con){

        String id = SharedPrefManager.get_mInstance(getApplicationContext()).getSid();
        Call<Update> updateCall = RetrofitClient.getmInstance().getApi().user_update(name,con,id);
        updateCall.enqueue(new Callback<Update>() {
            @Override
            public void onResponse(Call<Update> call, Response<Update> response) {
                Update update = response.body();

                if (update.getStatus()){
                    SharedPrefManager.get_mInstance(getApplicationContext()).saveUpdateUser(update);


                    Intent intent = new Intent(EditProfile.this,Bottom.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"something Wrong",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Update> call, Throwable t) {

            }
        });

    }




    private void get_Constituency(){
        progressDialog=new ProgressDialog(EditProfile.this);
        progressDialog.setMessage("Loading,Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Call<Constituency> constituencyCall = RetrofitClient.getmInstance().getApi().get_constituency();

        constituencyCall.enqueue(new Callback<Constituency>() {
            @Override
            public void onResponse(Call<Constituency> call, Response<Constituency> response) {
                Constituency constituency_res = response.body();

                country.add("Select Constituency");

                assert constituency_res != null;
                for (int i = 0; i < constituency_res.getData().size(); i++){
                    country.add(constituency_res.getData().get(i).getTxtConstituency());
                }


                ArrayAdapter<String> adapter = new ArrayAdapter<String>(EditProfile.this,android.R.layout.simple_dropdown_item_1line,country);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                constituency.setAdapter(adapter);

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Constituency> call, Throwable t) {

                progressDialog.dismiss();

            }
        });
    }






}
