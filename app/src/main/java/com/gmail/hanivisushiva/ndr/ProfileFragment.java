package com.gmail.hanivisushiva.ndr;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.hanivisushiva.ndr.Storage.SharedPrefManager;

public class ProfileFragment extends Fragment {

    TextView mobile,name,batch;
    String mobile_txt,name_txt,batch_txt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_profile,container,false);


        name = view.findViewById(R.id.name);
        batch = view.findViewById(R.id.batch);
        mobile = view.findViewById(R.id.mobile);


        mobile_txt = SharedPrefManager.get_mInstance(getContext()).getMobile();
        batch_txt = SharedPrefManager.get_mInstance(getContext()).getBatch();
        name_txt = SharedPrefManager.get_mInstance(getContext()).getName();


        name.setText(name_txt);
        batch.setText(batch_txt);
        mobile.setText(mobile_txt);



        return view;
    }
}
