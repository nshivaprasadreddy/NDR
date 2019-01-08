package com.gmail.hanivisushiva.ndr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.hanivisushiva.ndr.Storage.SharedPrefManager;


public class Bottom extends AppCompatActivity {

    private TextView mTextMessage;
    private Fragment selected_fragment;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selected_fragment = new PostsFragment();
                    break;
                case R.id.navigation_dashboard:
                    selected_fragment = new EventsFragment();
                    break;
                case R.id.navigation_notifications:
                    selected_fragment = new ProfileFragment();
                    break;
                case R.id.navigation_calender:
                    selected_fragment = new CalenderFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.container,selected_fragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);

        selected_fragment = null;

        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,new PostsFragment()).commit();



    }

    private void toast(String s){
       Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            logout();
            toast("logout");
            return true;
        }

        if (id == R.id.edit_profile) {
            toast("edit");
            edit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void edit() {
        Intent intent = new Intent(Bottom.this,EditProfile.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void logout() {
        SharedPrefManager.get_mInstance(getApplicationContext()).clear();
        Intent intent = new Intent(Bottom.this,Login_page.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
